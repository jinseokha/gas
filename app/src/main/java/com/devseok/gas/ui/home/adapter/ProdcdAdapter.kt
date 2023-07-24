package com.devseok.gas.ui.home.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.annotation.LayoutRes
import com.devseok.gas.data.model.Prodcd
import com.devseok.gas.databinding.ItemSpinnerBinding
import java.lang.Exception

/**
 * @author Ha Jin Seok
 * @created 2023-07-24
 * @desc
 */
class ProdcdAdapter (
    context: Context,
    @LayoutRes private val resId: Int,
    private val values: MutableList<Prodcd>
) : ArrayAdapter<Prodcd>(context, resId, values) {

    override fun getCount(): Int = values.size

    override fun getItem(position: Int): Prodcd? = values[position]

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val binding = ItemSpinnerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val model = values[position]

        try {
            binding.txtName.text = model.name

        } catch (e: Exception) {
            e.printStackTrace()
        }

        return binding.root
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        val binding = ItemSpinnerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val model = values[position]
        try {
            binding.txtName.text = model.name

        } catch (e: Exception) {
            e.printStackTrace()
        }

        return binding.root
    }
}