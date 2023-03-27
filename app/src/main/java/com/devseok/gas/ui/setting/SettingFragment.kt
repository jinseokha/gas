package com.devseok.gas.ui.setting

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.devseok.gas.R
import com.devseok.gas.databinding.FragmentSettingBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * @auth Dev Seok
 * @email seok270@gmail.com
 * @created 2023-03-27
 * @desc
 */
private const val TAG = "SettingFragment"

@AndroidEntryPoint
class SettingFragment : Fragment(R.layout.fragment_setting) {

    private val viewModel: SettingViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentSettingBinding.bind(view)

    }
}