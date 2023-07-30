package com.devseok.gas.ui.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.devseok.gas.R
import com.devseok.gas.data.model.AroundAll
import com.devseok.gas.databinding.ItemHomeBinding
import com.devseok.gas.util.Utils

/**
 * @author Ha Jin Seok
 * @created 2023-07-24
 * @desc
 */
class HomeAdapter : RecyclerView.Adapter<HomeAdapter.MyViewHolder>() {

    private var resultData: List<AroundAll.RESULT.OIL>? = null

    fun setResultData(resultData: List<AroundAll.RESULT.OIL>?) {
        this.resultData = resultData
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(resultData?.get(position)!!)
    }

    override fun getItemCount(): Int {
        if (resultData == null) return 0
        return resultData?.size!!
    }

    class MyViewHolder(val binding : ItemHomeBinding): RecyclerView.ViewHolder(binding.root) {


        fun bind(data : AroundAll.RESULT.OIL) {
            binding.txtTitle.text = data.OS_NM
            binding.imgBrand.setBackgroundResource(Utils.pollConvert(data.POLL_DIV_CD))

        }
    }
}