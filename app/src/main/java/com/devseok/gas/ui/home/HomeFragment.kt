package com.devseok.gas.ui.home

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.devseok.gas.R
import com.devseok.gas.data.model.Prodcd
import com.devseok.gas.data.model.Radius
import com.devseok.gas.data.model.Sort
import com.devseok.gas.databinding.FragmentHomeBinding
import com.devseok.gas.ui.SearchActivity
import com.devseok.gas.ui.home.adapter.HomeAdapter
import com.devseok.gas.ui.home.adapter.ProdcdAdapter
import com.devseok.gas.ui.home.adapter.RadiusAdapter
import com.devseok.gas.ui.home.adapter.SortAdapter
import com.devseok.gas.util.Resource
import com.devseok.gas.util.SEARCH_TIME_DELAY
import com.devseok.gas.util.Utils
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject
import java.util.Locale

/**
 * @auth Dev Seok
 * @email seok270@gmail.com
 * @created 2023-03-27
 * @desc
 */
private const val TAG = "HomeFragment"

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private val viewModel: HomeViewModel by viewModels()

    private lateinit var homeAdapter: HomeAdapter

    private lateinit var sortAdapter : SortAdapter
    private lateinit var radiusAdapter: RadiusAdapter
    private lateinit var prodcdAdapter: ProdcdAdapter

    var sortValue = Sort(1, "가격순")
    var radiusValue = Radius(1000, "1km")
    var prodcdValue = Prodcd("B027", "휘발유")

    //변수 선언
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    lateinit var binding : FragmentHomeBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentHomeBinding.bind(view)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext());

        initViewModels()

        checkLocationPermission()

        setupSpinnerSort()
        setupSpinnerProdcd()
        setupSpinnerRadius()

        setupSpinnerHandler()

        initAdapter()
    }

    private fun initViewModels() {

        /** 상호로 주유소 검색 */
        /*viewModel.searchByName.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Success -> {
                    it.data?.let { response ->
                        val intent = Intent(requireContext(), SearchActivity::class.java)
                        intent.putExtra("searchByName", response)
                        startActivity(intent)
                    }
                }

                is Resource.Error -> {
                    Log.d("test", "" + "error")
                }

                is Resource.Loading -> {
                    Log.d("test", "" + "loading")
                }
            }
        }*/



        /** ROOM DB 지역코드 *//*
        viewModel.getAreaDBCode().observe(viewLifecycleOwner) {
            // 지역 코드
            if (it != null) {
                Log.d("test", "" + it[0].oIL)



            }
        }

        *//** API 지역코드 *//*
        viewModel.areaCode.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Success -> {
                    Log.d("test", "" + it)

                }

                is Resource.Error -> {
                    Log.d("test", "" + "error")
                }

                is Resource.Loading -> {
                    Log.d("test", "" + "loading")
                }
            }
        }*/

        viewModel.resultData.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Success -> {
                    Log.d("test", "" + it)
                    homeAdapter.setResultData(it.data?.result?.oIL)
                    homeAdapter.notifyDataSetChanged()
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

    @SuppressLint("MissingPermission", "SetTextI18n")
    private fun checkLocationPermission() {
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            fusedLocationClient.lastLocation
                .addOnSuccessListener { location: Location? ->
                    var address: List<String> = listOf("서울특별시", "중구", "명동")

                    var geocoder = Geocoder(requireContext(), Locale.KOREA)
                    if (location != null) {

                        val addrList = geocoder.getFromLocation(location.latitude, location.longitude, 1)

                        Utils.latitude = location.latitude
                        Utils.longitude = location.longitude

                        if (addrList != null) {
                            for (addr in addrList) {
                                val splitedAddr = addr.getAddressLine(0).split(" ")
                                address = splitedAddr
                            }
                        }

                        binding.tvAddress.text = "${address[1]} ${address[2]} ${address[3]} ${address[4]} ${address[5]}"

                        viewModel.getAroundAll(Utils.latitude, Utils.longitude, radiusValue.value.toString(), prodcdValue.value, sortValue.value.toString())
                    }
                }
        }
    }

    private fun setupSpinnerSort() {
        var sortArray: MutableList<Sort> = arrayListOf()

        sortArray.add(Sort(1, "가격순"))
        sortArray.add(Sort(2, "거리순"))

        sortAdapter = SortAdapter(requireContext(), R.layout.item_spinner, sortArray)
        binding.spinnerSort.adapter = sortAdapter
    }

    private fun setupSpinnerRadius() {
        var radiusArray: MutableList<Radius> = arrayListOf()

        radiusArray.add(Radius(1000, "1km"))
        radiusArray.add(Radius(2000, "2km"))
        radiusArray.add(Radius(3000, "3km"))
        radiusArray.add(Radius(5000, "5km"))

        radiusAdapter = RadiusAdapter(requireContext(), R.layout.item_spinner, radiusArray)
        binding.spinnerRadius.adapter = radiusAdapter
    }

    private fun setupSpinnerProdcd() {
        var prodcdArray: MutableList<Prodcd> = arrayListOf()

        prodcdArray.add(Prodcd("B027", "휘발유"))
        prodcdArray.add(Prodcd("D047", "경유"))
        prodcdArray.add(Prodcd("B034", "고급휘발유"))
        prodcdArray.add(Prodcd("C004", "등유"))
        prodcdArray.add(Prodcd("K015", "LPG"))

        prodcdAdapter = ProdcdAdapter(requireContext(), R.layout.item_spinner, prodcdArray)
        binding.spinnerProdcd.adapter = prodcdAdapter
    }

    private fun setupSpinnerHandler() {

        binding.spinnerSort.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val value = binding.spinnerSort.getItemAtPosition(position) as Sort
                sortValue= value

                viewModel.getAroundAll(Utils.latitude, Utils.longitude, radiusValue.value.toString(), prodcdValue.value, sortValue.value.toString())
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }

        binding.spinnerRadius.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val value = binding.spinnerRadius.getItemAtPosition(position) as Radius
                radiusValue = value

                viewModel.getAroundAll(Utils.latitude, Utils.longitude, radiusValue.value.toString(), prodcdValue.value, sortValue.value.toString())
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }

        binding.spinnerProdcd.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val value = binding.spinnerProdcd.getItemAtPosition(position) as Prodcd
                prodcdValue = value

                viewModel.getAroundAll(Utils.latitude, Utils.longitude, radiusValue.value.toString(), prodcdValue.value, sortValue.value.toString())
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }
    }

    private fun initAdapter() {
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        homeAdapter = HomeAdapter()
        binding.recyclerView.adapter = homeAdapter
    }

}