package com.devseok.gas.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.devseok.gas.R
import com.devseok.gas.data.db.DetailOIL
import com.devseok.gas.data.db.GasDatabase
import com.devseok.gas.data.model.DetailById
import com.devseok.gas.databinding.ActivityDetailBinding
import com.devseok.gas.util.Resource
import com.devseok.gas.util.Utils
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {

    private val viewModel: DetailViewModel by viewModels()
    lateinit var detailByOil : DetailById.RESULT.OIL

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        val data = intent.getStringExtra("data")

        viewModel.getDetailById(data!!)


        initViewModels()

        initIntent()
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initViewModels() {
        binding.tbGasoline.visibility = View.GONE
        binding.tbDiesel.visibility = View.GONE
        binding.tbPremiumgasoline.visibility = View.GONE
        binding.tbLampoil.visibility = View.GONE
        binding.tbLpg.visibility = View.GONE

        viewModel.detailById.observe(this) {
            when (it) {
                is Resource.Success -> {

                    val data = it.data?.result?.oIL!![0]

                    if (data != null) {
                        detailByOil = data

                        binding.imgLogo.setBackgroundResource(Utils.pollConvert(data.POLL_DIV_CO))

                        binding.txtTitle.text = data.OS_NM
                        binding.txtPoll.text = data.POLL_DIV_CO
                        binding.txtNumber.text = data.TEL
                        binding.txtAddress.text = data.VAN_ADR

                        var oilPrice = data.OIL_PRICE

                        for (oil in oilPrice) {
                            when(oil.PRODCD) {
                                "B027" -> {
                                    // 휘발유
                                    binding.tbGasoline.visibility = View.VISIBLE
                                    binding.txtGasoline.text = oil.PRICE.toString() +"원"
                                    binding.txtGasolineDate.text = dateTagConvert(oil.TRADE_DT)
                                }

                                "D047" -> {
                                    // 경유
                                    binding.tbDiesel.visibility = View.VISIBLE
                                    binding.txtDiesel.text = oil.PRICE.toString() +"원"
                                    binding.txtDieselDate.text = dateTagConvert(oil.TRADE_DT)
                                }

                                "B034" -> {
                                    // 고급휘발유
                                    binding.tbPremiumgasoline.visibility = View.VISIBLE
                                    binding.txtPremiumgasoline.text = oil.PRICE.toString() +"원"
                                    binding.txtPremiumgasolineDate.text = dateTagConvert(oil.TRADE_DT)
                                }

                                "C004" -> {
                                    // 실내등유
                                    binding.tbLampoil.visibility = View.VISIBLE
                                    binding.txtLampoil.text = oil.PRICE.toString() +"원"
                                    binding.txtLampoilDate.text = dateTagConvert(oil.TRADE_DT)
                                }

                                "K015" -> {
                                    // LPG
                                    binding.tbLpg.visibility = View.VISIBLE
                                    binding.txtLpg.text = oil.PRICE.toString() +"원"
                                    binding.txtLpgDate.text = dateTagConvert(oil.TRADE_DT)
                                }
                            }

                        }
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

    }

    private fun initIntent() {
        binding.btnCall.setOnClickListener {
            val dialIntent = Intent(Intent.ACTION_DIAL)
            dialIntent.data = Uri.parse("tel:"+detailByOil.TEL)
            startActivity(dialIntent)
        }

        binding.btnScrap.setOnClickListener {
            val detailOILDao = GasDatabase.getDatabase(this).detailOILRepository()

            CoroutineScope(Dispatchers.IO).launch {
                val detailOIL = DetailOIL(
                    detailByOil.CAR_WASH_YN,
                    detailByOil.CVS_YN,
                    detailByOil.GIS_X_COOR,
                    detailByOil.GIS_Y_COOR,
                    detailByOil.GPOLL_DIV_CO,
                    detailByOil.KPETRO_YN,
                    detailByOil.LPG_YN,
                    detailByOil.MAINT_YN,
                    detailByOil.NEW_ADR,
                    detailByOil.OS_NM,
                    detailByOil.POLL_DIV_CO,
                    detailByOil.SIGUNCD,
                    detailByOil.TEL,
                    detailByOil.UNI_ID,
                    detailByOil.VAN_ADR)
                detailOILDao.detailOILInsert(detailOIL)
                CoroutineScope(Dispatchers.Main).launch {
                    Toast.makeText(this@DetailActivity, "즐겨찾기에 추가되었습니다.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    fun dateTagConvert(date: String) : String {
        return date.substring(0,4) +"-"+date.substring(4,6)+"-"+date.substring(6,8)
    }
}