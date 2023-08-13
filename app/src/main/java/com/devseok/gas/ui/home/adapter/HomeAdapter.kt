package com.devseok.gas.ui.home.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.devseok.gas.data.model.AroundAll
import com.devseok.gas.databinding.ItemHomeBinding
import com.devseok.gas.ui.DetailActivity
import com.devseok.gas.util.Utils
import okhttp3.internal.format

/**
 * @author Ha Jin Seok
 * @created 2023-07-24
 * @desc
 */
class HomeAdapter(context: Context) : RecyclerView.Adapter<HomeAdapter.MyViewHolder>() {

    private var resultData: List<AroundAll.RESULT.OIL>? = null
    lateinit var context: Context
    init {
        this.context = context
    }

    fun setResultData(resultData: List<AroundAll.RESULT.OIL>?) {
        this.resultData = resultData
    }

    fun getResultData() : List<AroundAll.RESULT.OIL>? = resultData

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(context, resultData?.get(position)!!)
    }

    override fun getItemCount(): Int {
        if (resultData == null) return 0
        return resultData?.size!!
    }

    class MyViewHolder(val binding : ItemHomeBinding): RecyclerView.ViewHolder(binding.root) {


        fun bind(context :Context, data : AroundAll.RESULT.OIL) {
            // 상호
            binding.imgBrand.setBackgroundResource(Utils.pollConvert(data.POLL_DIV_CD))

            // 제목
            binding.txtTitle.text = data.OS_NM

            // 정보


            // 가격
            binding.txtPrice.text = data.PRICE.toString() + "원"

            // 거리
            //binding.txtDistance.text = data.DISTANCE.toString()
            binding.txtDistance.text = kmConvert(data.DISTANCE)

            if (data.detailById != null) {
                // 주소
                binding.txtAddress.text = data.detailById?.result?.oIL!![0].VAN_ADR

                // 날짜
                binding.txtDate.text = dateConvert(data.detailById?.result?.oIL!![0].OIL_PRICE[0].TRADE_DT)
            }

            binding.layout.setOnClickListener {
                var intent = Intent(context, DetailActivity::class.java)
                intent.putExtra("data", data.UNI_ID)
                context.startActivity(intent)
            }


        }

        fun kmConvert(m: Double) : String {
            var mF = m.toFloat()

            var toKilioMeters = format("%.2f", mF/1000F).toString() + " km"
            return toKilioMeters
        }

        fun dateConvert(date: String) : String {

            return date.substring(0,4) +"."+date.substring(4,6)+"."+date.substring(6,8)
        }

    }
}