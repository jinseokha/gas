package com.devseok.gas.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.devseok.gas.data.model.SearchByName
import com.devseok.gas.databinding.ActivitySearchBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * @auth Dev Seok
 * @email seok270@gmail.com
 * @created 2023-04-16
 * @desc
 */

@AndroidEntryPoint
class SearchActivity : AppCompatActivity() {

    lateinit var binding : ActivitySearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val searchByName = intent.getSerializableExtra("searchByName") as SearchByName
        Log.d("test", "" + searchByName)

    }
}