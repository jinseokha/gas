package com.devseok.gas.ui

import android.Manifest
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.view.animation.AnticipateInterpolator
import android.widget.Toast
import androidx.core.animation.doOnEnd
import androidx.core.app.ActivityCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
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
        installSplashScreen()
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            splashScreen.setOnExitAnimationListener {splashScreenView ->
                val animScaleX = PropertyValuesHolder.ofFloat(View.SCALE_X, 1f, 8f)
                val animScaleY = PropertyValuesHolder.ofFloat(View.SCALE_Y, 1f, 8f)
                val animAlpha = PropertyValuesHolder.ofFloat(View.ALPHA, 1f, 0f)

                ObjectAnimator.ofPropertyValuesHolder(
                    splashScreenView.iconView,
                    animAlpha,
                    animScaleX,
                    animScaleY
                ).run {
                    interpolator = AnticipateInterpolator()
                    duration = 300L
                    doOnEnd { splashScreenView.remove() }
                    start()
                }

            }
        }

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
                            if (ActivityCompat.checkSelfPermission(
                                    this,
                                    Manifest.permission.ACCESS_FINE_LOCATION
                                ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                                    this,
                                    Manifest.permission.ACCESS_COARSE_LOCATION
                                ) != PackageManager.PERMISSION_GRANTED
                            ) {
                                login()
                                return
                            } else {
                                fusedLocationClient.lastLocation
                                    .addOnSuccessListener { location: Location? ->
                                        // Got last known location. In some rare situations this can be null.
                                        var geocoder = Geocoder(this, Locale.KOREA)
                                        if (location != null) {
                                            /*Toast.makeText(
                                                this,
                                                "현재위치..." + location.latitude + " / " + location.longitude,
                                                Toast.LENGTH_SHORT
                                            ).show()*/

                                            login()
                                        }
                                    }
                            }


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
                        /*Toast.makeText(
                            this,
                            "현재위치..." + location.latitude + " / " + location.longitude,
                            Toast.LENGTH_SHORT
                        ).show()*/

                        login()
                    }
                }
        } else {

            var permission = ArrayList<String>()

            permission.add(Manifest.permission.ACCESS_FINE_LOCATION)
            permission.add(Manifest.permission.ACCESS_COARSE_LOCATION)

            var mPermission = permission.toArray(arrayOfNulls<String>(0))

            ActivityCompat.requestPermissions(this, mPermission, multiplePermissionsCode)
        }
    }

    private fun login() {
        val intent= Intent( this, MainActivity::class.java)
        startActivity(intent)

        finish()
    }
}