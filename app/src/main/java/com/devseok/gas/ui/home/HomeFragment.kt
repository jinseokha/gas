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

    lateinit var binding : FragmentHomeBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentHomeBinding.bind(view)

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

        viewModel.getAreaDBCode().observe(viewLifecycleOwner) {
            // 지역 코드
            Log.d("test", "" + it[0].oIL)
        }

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
                    // oil index 정보
                    // 0: 고급휘발유
                    // 1: 휘발유
                    // 2: 자동차용 경유
                    // 3: 실내등유
                    // 4: 자동차용 부탄
                    binding.tvAvgAllPrice.text = it.data!!.rESULT.oIL[1].pRICE + "원"
                }

                is Resource.Error -> {
                    Log.d("testtest", "" + "error")
                }

                is Resource.Loading -> {
                    Log.d("testtest", "" + "loading")
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