package com.github.ncliff.cutecats.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.github.ncliff.cutecats.Model.SharedCatApiViewModel
import com.github.ncliff.cutecats.databinding.FragmentHomeBinding
import com.github.ncliff.cutecats.ui.adapters.CatBreedsRVAdapter

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val catViewModel: SharedCatApiViewModel by lazy {
        ViewModelProvider(this).get(
            SharedCatApiViewModel::class.java
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val adapter = CatBreedsRVAdapter()
        _binding?.rvCatBreeds?.adapter = adapter

        catViewModel.breedsList.observe(viewLifecycleOwner) {
            adapter.setCatBreedsList(it)
        }

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        catViewModel.getBreedsList {}
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}