package com.hms.geofancingtesting

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import kotlinx.android.synthetic.main.activity_current_location.*

class CurrentLocationActivity: AppCompatActivity(){

   // private lateinit var mMap: GoogleMap




    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient

    private var mLocationPermissionGranted: Boolean = false


    companion object{
        private const val PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1
    }




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_current_location)

        btnGetCurrentLocation.setOnClickListener {

                locationPermissionAreGranted(this)

                getCurrentLocation()


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

    private fun locationPermissionAreGranted(context: Context){

        if (ContextCompat.checkSelfPermission(this.applicationContext,
                android.Manifest.permission.ACCESS_FINE_LOCATION)
            == PackageManager.PERMISSION_GRANTED
        ) {
           // buildGoogleApiClient()
            mLocationPermissionGranted = true
        } else {
            ActivityCompat.requestPermissions(this,
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION)
        }

    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        mLocationPermissionGranted = false
        when (requestCode) {
            PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION -> {
                if (grantResults.isNotEmpty()
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED
                ) {
                    mLocationPermissionGranted = true
                }
            }
        }

    }


}

