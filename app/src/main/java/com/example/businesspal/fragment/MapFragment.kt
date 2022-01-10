package com.example.businesspal.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.businesspal.R
import com.example.businesspal.model.BusinessDataModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.fragment_map.*
import android.content.Intent
import android.net.Uri
import android.view.MenuItem
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment


class MapFragment() : Fragment(), OnMapReadyCallback {
    lateinit var data: BusinessDataModel
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val navHostFragment =
            requireActivity().supportFragmentManager.findFragmentById(R.id.my_nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        data =
            requireActivity().intent.getSerializableExtra("DATA") as BusinessDataModel
        return inflater.inflate(R.layout.fragment_map, container, false)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mapFragment = childFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        viewInGoogleMaps.setOnClickListener {
            val i = Intent(Intent.ACTION_VIEW)
            i.data =
                Uri.parse("https://www.google.com/maps?q=${data.BusinessLocationLatitude},${data.BusinessLocationLongitude}")
            startActivity(i)
        }
    }

    override fun onMapReady(p0: GoogleMap) {
        val coordinate = LatLng(data.BusinessLocationLatitude, data.BusinessLocationLongitude)
        p0.addMarker(
            MarkerOptions()
                .position(coordinate)
                .title(data.BusinessName)
        )
        p0.moveCamera(CameraUpdateFactory.newLatLng(coordinate))
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        navController.navigate(R.id.action_mapFragment_to_displayDataFragment)
        return true
    }

}