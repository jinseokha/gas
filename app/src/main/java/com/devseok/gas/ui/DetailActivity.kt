package com.devseok.gas.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.devseok.gas.databinding.ActivityDetailBinding
import com.devseok.gas.util.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {

    private val viewModel: DetailViewModel by viewModels()

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val data = intent.getStringExtra("data")
        Log.d("test", "" + data)

        viewModel.getDetailById(data!!)


        initViewModels()
    }

    private fun initViewModels() {
        viewModel.detailById.observe(this) {
            when (it) {
                is Resource.Success -> {
                    Log.d("test", "" + it.data?.result?.oIL!![0])
                }

                is Resource.Error -> {
                    Log.d("test", "" + "error")
                }

                is Resource.Loading -> {
                    Log.d("test", "" + "loading")
                }
            }
        }

    }
}