package com.devseok.gas.ui.bookmark

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.devseok.gas.R
import com.devseok.gas.data.db.GasDatabase
import com.devseok.gas.databinding.FragmentBookmarkBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * @auth Dev Seok
 * @email seok270@gmail.com
 * @created 2023-07-23
 * @desc
 */

@AndroidEntryPoint
class BookMarkFragment : Fragment(R.layout.fragment_bookmark) {

    private val viewModel: BookMarkViewModel by viewModels()

    


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentBookmarkBinding.bind(view)

        val detailOILDao = GasDatabase.getDatabase(requireContext()).detailOILRepository()

        CoroutineScope(Dispatchers.IO).launch {
            val listArticle = detailOILDao.findByAll()
            Log.d("test", "" + listArticle)
        }

    }
}