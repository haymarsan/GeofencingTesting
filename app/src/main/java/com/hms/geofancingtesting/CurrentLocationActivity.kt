package com.hms.geofancingtesting

import android.annotation.SuppressLint
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kotlinx.android.synthetic.main.activity_current_location.*

class CurrentLocationActivity: AppCompatActivity() {


    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_current_location)

        btnGetCurrentLocation.setOnClickListener {

            if (locationPermissionAreGranted(context = applicationContext)){

                getCurrentLocation()
            }

        }



    }

    @SuppressLint("MissingPermission")
    private fun getCurrentLocation() {
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)


        fusedLocationProviderClient.lastLocation
            .addOnSuccessListener {location: Location? ->

               tvCurrentLat.text = location!!.longitude.toString()
                tvCurrentLog.text = location.longitude.toString()

            }
            .addOnFailureListener {
                Toast.makeText(applicationContext, it.localizedMessage, Toast.LENGTH_LONG).show()
            }
    }


}