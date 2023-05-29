package com.devseok.gas.ui.home

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.devseok.gas.R
import com.devseok.gas.databinding.FragmentHomeBinding
import com.devseok.gas.ui.SearchActivity
import com.devseok.gas.util.Resource
import com.devseok.gas.util.SEARCH_TIME_DELAY
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

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

    var isLoading = false
    var isLastPage = false
    var isScrolling = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentHomeBinding.bind(view)


        var job: Job? = null
        binding.apply {
            edtSearch.addTextChangedListener { editable: Editable? ->
                job?.cancel()
                job = MainScope().launch {
                    delay(SEARCH_TIME_DELAY)
                    editable?.let {
                        if (editable.toString().isNotEmpty()) {
                            viewModel.getSearchByName(editable.toString())
                        }
                    }
                }

            }
        }

        initViewModels()
    }

    private fun initViewModels() {

        /** 상호로 주유소 검색 */
        viewModel.searchByName.observe(viewLifecycleOwner) {
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
        }

        /** 전국 주유소 평균가격 */
        viewModel.avgAllPrice.observe(viewLifecycleOwner) {
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
        }

        /** 시도별 주유소 평균가격 */
        viewModel.avgSidoPrice.observe(viewLifecycleOwner) {
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
        }

        /** 반경 내 주유소 검색 (추가필요) */

        /** 지역별 최저가 주유소 */
        viewModel.lowTop10.observe(viewLifecycleOwner) {
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
        }

        /** 최근 1주의 주간 평균유가(전국/시도별) */
        viewModel.avgLastWeek.observe(viewLifecycleOwner) {
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
        }

        /** 요소수 주유소 판매가격(지역별) */
        viewModel.ureaPrice.observe(viewLifecycleOwner) {
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
        }

        /** 지역코드 */
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
        }
    }
}