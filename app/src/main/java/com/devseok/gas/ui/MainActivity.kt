package com.devseok.gas.ui

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.devseok.gas.R
import com.devseok.gas.databinding.ActivityMainBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    //변수 선언
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment
        val navController = navHostFragment.findNavController()
        binding.bottomNavigationView.setupWithNavController(navController)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        checkLocationPermission()
    }

    //퍼미션 체크 및 권한 요청 함수
    @SuppressLint("MissingPermission")
    private fun checkLocationPermission() {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            fusedLocationClient.lastLocation
                .addOnSuccessListener { location: Location? ->
                    var address: List<String> = listOf("서울특별시", "중구", "명동")

                    var geocoder = Geocoder(this, Locale.KOREA)
                    if (location != null) {
                        Toast.makeText(
                            this,
                            "현재위치..." + location.latitude + " / " + location.longitude,
                            Toast.LENGTH_SHORT
                        ).show()

                        val addrList =
                            geocoder.getFromLocation(location.latitude, location.longitude, 1)

                        if (addrList != null) {
                            for (addr in addrList) {
                                val splitedAddr = addr.getAddressLine(0).split(" ")
                                address = splitedAddr
                            }
                        }
                        //경기도, 성남시, 분당구, 삼평동
                        Log.d(
                            "testtest",
                            "현재위치 : ${address[1]}, ${address[2]}, ${address[3]}, ${address[4]}"
                        )
                    }
                }
        } else {
            Toast.makeText(this, "위치권한이 없습니다..", Toast.LENGTH_SHORT).show()
        }
    }

}