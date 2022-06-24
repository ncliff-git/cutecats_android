package com.github.ncliff.cutecats.ui.view.favourites

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.github.ncliff.cutecats.databinding.FragmentFavouritesBinding
import com.github.ncliff.cutecats.ui.adapters.CatFavRVAdapter
import com.github.ncliff.cutecats.ui.viewmodel.SharedCatApiViewModel

class FavouritesFragment : Fragment() {
    private lateinit var _binding: FragmentFavouritesBinding
    private val catViewModel: SharedCatApiViewModel by lazy {
        ViewModelProvider(this).get(
            SharedCatApiViewModel::class.java
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavouritesBinding.inflate(inflater, container, false)
        initRecyclerView()
        catViewModel.getCatFavList {  }
        return _binding.root
    }

    private fun initRecyclerView() {
        val adapter = CatFavRVAdapter()
        _binding.rvCatFav.also {
            it.adapter = adapter
            it.layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        }

        catViewModel.favList.observe(viewLifecycleOwner) { favList ->
            adapter.setCatFavList(favList)
            Log.d("favList", "${favList.size}")
            if (favList.isNotEmpty()) {
                _binding.pbCatFav.isVisible = false
            }
        }
    }

}