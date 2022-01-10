package com.example.businesspal.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import com.example.businesspal.R
import com.example.businesspal.model.BusinessDataModel
import kotlinx.android.synthetic.main.fragment_display_data.*
import android.content.ActivityNotFoundException

import android.content.Intent
import android.net.Uri
import android.widget.Toast


class DisplayDataFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        return inflater.inflate(R.layout.fragment_display_data, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val data: BusinessDataModel =
            requireActivity().intent.getSerializableExtra("DATA") as BusinessDataModel

        businessName.text = data.BusinessName.trim()
        Caption.text = "\"${data.BusinessCaption.trim()}\""
        description.text = data.BusinessDescription.trim()
        emailId.text = data.EmailID

        emailId.setOnClickListener {
            try {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("mailto:${data.EmailID}"))
                startActivity(intent)
            } catch (e: ActivityNotFoundException) {
                Toast.makeText(requireActivity(), "Cant Open Email", Toast.LENGTH_SHORT).show()
            }
        }
        val navHostFragment =
            requireActivity().supportFragmentManager.findFragmentById(R.id.my_nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        viewInMap.setOnClickListener {
            navController.navigate(R.id.action_displayDataFragment_to_mapFragment2)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        requireActivity().finish()
        return true
    }

}