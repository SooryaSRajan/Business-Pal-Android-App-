package com.example.businesspal.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import com.example.businesspal.R
import com.example.businesspal.model.BusinessDataModel
import kotlinx.android.synthetic.main.activity_dissplay_data.*
import kotlinx.android.synthetic.main.fragment_display_data.*

class DisplayDataFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_display_data, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val data: BusinessDataModel =
            requireActivity().intent.getSerializableExtra("DATA") as BusinessDataModel

        businessName.text = data.BusinessName
        Caption.text = data.BusinessCaption
        description.text = data.BusinessDescription

        val navHostFragment =
            requireActivity().supportFragmentManager.findFragmentById(R.id.my_nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        viewInMap.setOnClickListener {
            Log.d("TAG", "onViewCreated: Clicked")
            navController.navigate(R.id.action_displayDataFragment_to_mapFragment2)
        }
    }

}