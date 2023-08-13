package com.devseok.gas.ui

import android.os.Bundle
import android.os.Process
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.devseok.gas.R
import com.devseok.gas.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment
        val navController = navHostFragment.findNavController()
        binding.bottomNavigationView.setupWithNavController(navController)

    }

    override fun onBackPressed() {

        AlertDialog.Builder(this)
            .setTitle("안내")
            .setMessage("앱을 종료하시겠습니까?")
            .setPositiveButton(
                "종료"
            ) { dialog, which -> // 프로세스 종료.
                moveTaskToBack(true)
                finish()
                Process.killProcess(Process.myPid())
            }.setNegativeButton("취소", null).show()
    }


}