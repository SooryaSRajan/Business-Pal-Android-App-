package com.example.businesspal

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.example.businesspal.model.BusinessDataModel
import com.example.businesspal.retrofit.RetrofitService
import kotlinx.android.synthetic.main.activity_add_new_data.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*
import android.util.Patterns
import java.util.regex.Pattern


class AddNewDataActivity : AppCompatActivity() {
    var currentLocation: Location? = null
    private lateinit var locationManager: LocationManager
    private lateinit var retrofitService: RetrofitService

    val TAG = "Add New Data"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_data)

        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        retrofitService = RetrofitService.getInstance()


    }

    @SuppressLint("MissingPermission")
    override fun onStart() {
        super.onStart()

        gpsLocationButton.setOnClickListener {
            progressBar.visibility = View.VISIBLE
            if (isLocationPermissionGranted()) {
                if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
                ) {
                    locationManager.requestLocationUpdates(
                        LocationManager.GPS_PROVIDER,
                        5000,
                        0F,
                        gpsLocationListener
                    )
                } else {
                    progressBar.visibility = View.INVISIBLE
                }
            } else {
                progressBar.visibility = View.INVISIBLE
                Toast.makeText(
                    this,
                    "Please allow GPS permissions and try again",
                    Toast.LENGTH_SHORT
                )
                    .show()
            }
        }

        SubmitButton.setOnClickListener {
            val businessName = businessNameEditText.text.toString()
            val businessSloganData = businessSloganEditText.text.toString()
            val descriptionString = descriptionEditText.text.toString()
            val emailId = businessOwnerEmailField.text.toString()

            val pattern: Pattern = Patterns.EMAIL_ADDRESS
            val isEmailValid = pattern.matcher(emailId).matches()


            if (isEmailValid && businessName.isNotEmpty() && businessSloganData.isNotEmpty() && descriptionString.isNotEmpty() && emailId.isNotEmpty() && currentLocation != null) {

                val modelData = BusinessDataModel(
                    BusinessName = businessName,
                    BusinessCaption = businessSloganData,
                    BusinessDescription = descriptionString,
                    BusinessLocationLatitude = currentLocation!!.latitude,
                    BusinessLocationLongitude = currentLocation!!.longitude,
                    _id = "",
                    CreatedTime = getDateTme(),
                    EmailID = emailId

                );

                retrofitService.writeData(modelData)?.enqueue(object :
                    Callback<BusinessDataModel?> {
                    override fun onResponse(
                        call: Call<BusinessDataModel?>,
                        response: Response<BusinessDataModel?>
                    ) {
                        if (response.code() == 200) {
                            Toast.makeText(
                                this@AddNewDataActivity,
                                "Uploaded successfully!",
                                Toast.LENGTH_SHORT
                            ).show()
                            finish()
                        } else {
                            Toast.makeText(
                                this@AddNewDataActivity,
                                "Something went wrong, please try again later!",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }

                    override fun onFailure(call: Call<BusinessDataModel?>, t: Throwable) {
                        Log.d(TAG, "onFailure: ${t.message}")
                        Toast.makeText(
                            this@AddNewDataActivity,
                            t.message,
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                })
            } else {
                if (currentLocation == null) {
                    Toast.makeText(this, "Get Location", Toast.LENGTH_SHORT).show()
                }
                if (!isEmailValid) {
                    businessOwnerEmailField.error = "Please enter a valid email ID"
                } else {
                    businessOwnerEmailField.error = null
                }
                when {
                    businessName.isEmpty() -> {
                        businessNameEditText.error = "Please enter business name"
                    }
                    businessName.length < 4 -> {
                        businessNameEditText.error =
                            "Make sure there's a atleast three characters in name"
                    }
                    else -> {
                        businessNameEditText.error = null
                    }
                }

                when {


                    businessSloganData.isEmpty() -> {
                        businessSloganEditText.error = "Please enter slogan data"
                    }
                    businessSloganData.length < 4 -> {
                        businessSloganEditText.error =
                            "Make sure there's a at least three characters in name"
                    }
                    else -> {
                        businessSloganEditText.error = null
                    }
                }

                when {
                    descriptionString.isEmpty() -> {
                        descriptionEditText.error = "Please enter description text"
                    }
                    descriptionString.length < 4 -> {
                        descriptionEditText.error =
                            "Make sure there's a at least three characters in name"
                    }
                    else -> {
                        descriptionEditText.error = null
                    }
                }
            }

        }


    }

    @SuppressLint("SimpleDateFormat")
    private fun getDateTme(): String {
        val simpleDateFormat = SimpleDateFormat("HH:mm, dd.MM.yyyy")
        return simpleDateFormat.format(Date())
    }

    private fun isLocationPermissionGranted(): Boolean {
        return if (ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(
                    android.Manifest.permission.ACCESS_FINE_LOCATION,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION
                ),
                1024
            )
            false
        } else {
            true
        }
    }

    private val gpsLocationListener: LocationListener = object : LocationListener {
        @SuppressLint("SetTextI18n")
        override fun onLocationChanged(locationData: Location) {
            currentLocation = locationData
            location.text =
                "Latitude: ${locationData.latitude}, Longitude: ${locationData.longitude}"
            progressBar.visibility = View.INVISIBLE

        }

        override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {}
        override fun onProviderEnabled(provider: String) {}
        override fun onProviderDisabled(provider: String) {}
    }

}