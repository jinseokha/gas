package com.devseok.gas.ui.bookmark

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.devseok.gas.R
import com.devseok.gas.databinding.FragmentBookmarkBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * @auth Dev Seok
 * @email seok270@gmail.com
 * @created 2023-07-23
 * @desc
 */

private const val TAG = "BookMarkFragment"

@AndroidEntryPoint
class BookMarkFragment : Fragment(R.layout.fragment_bookmark) {

    private val viewModel: BookMarkViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentBookmarkBinding.bind(view)



    }
}