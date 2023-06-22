package com.devseok.gas.ui

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.devseok.gas.databinding.ActivityMainBinding
import com.devseok.gas.databinding.ActivitySplashBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    private val multiplePermissionsCode = 100

    //변수 선언
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    private lateinit var binding : ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 일정 시간 지연 이후 실행하기 위한 코드
        Handler(Looper.getMainLooper()).postDelayed({

            // 일정 시간이 지나면 MainActivity로 이동
            val intent= Intent( this, MainActivity::class.java)
            startActivity(intent)

            // 이전 키를 눌렀을 때 스플래스 스크린 화면으로 이동을 방지하기 위해
            // 이동한 다음 사용안함으로 finish 처리
            finish()

        }, 500) // 시간 0.5초 이후 실행

        //등록
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        checkLocationPermission()

    }

    //권한 요청 결과 처리 함수
    @SuppressLint("MissingSuperCall")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>, grantResults: IntArray
    ) {
        when (requestCode) {
            multiplePermissionsCode -> {
                if (grantResults.isNotEmpty()) {
                    for ((i, permission) in permissions.withIndex()) {
                        if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                            //권한 획득 실패시 동작
                            Toast.makeText(
                                this,
                                "The user has denied to $permission",
                                Toast.LENGTH_SHORT
                            ).show()
                            Log.i("TAG", "I can't work for you anymore then. ByeBye!")
                        } else {
                            //gpsGranted = true
                        }
                    }
                }
            }
        }
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
                    // Got last known location. In some rare situations this can be null.
                    var geocoder = Geocoder(this, Locale.KOREA)
                    if (location != null) {
                        Toast.makeText(
                            this,
                            "현재위치..." + location.latitude + " / " + location.longitude,
                            Toast.LENGTH_SHORT
                        ).show()

                    }
                }
        } else {
            Toast.makeText(this, "위치권한이 없습니다..", Toast.LENGTH_SHORT).show()

        }
    }
}