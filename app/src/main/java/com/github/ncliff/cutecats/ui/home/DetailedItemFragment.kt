package com.github.ncliff.cutecats.ui.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.github.ncliff.cutecats.ui.viewmodel.SharedCatApiViewModel
import com.github.ncliff.cutecats.databinding.FragmentDetailedItemBinding

class DetailedItemFragment : Fragment() {
    private var _binding: FragmentDetailedItemBinding? = null
    private val catViewModel: SharedCatApiViewModel by lazy {
        ViewModelProvider(this).get(
            SharedCatApiViewModel::class.java
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailedItemBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    fun initViewElements() {
        val breedName = arguments?.getString(BREED_NAME)
        breedName?.also {
            catViewModel.findBreedsByName(it) {}
        }

        catViewModel.breeds.observe(viewLifecycleOwner) {
            _binding?.apply {
                progressBarCatBreeds.isVisible = false
                separatorLine.isVisible = true

                tvBreedsName.text = it.name
                tvBreedsId.text = "id: ${it.id}"
                tvBreedsDescription.text = it.description
                tvBreedsOrigin.text = it.origin
                tvBreedsTemperament.text = it.temperament
                tvBreedsWeight.text = "${it.weight?.metric ?: "None"} kg"
                tvBreedsLifeSpan.text = "${it.lifeSpan} average life span"
                if (!it.wikipediaUrl.isNullOrEmpty()) {
                    btnBreedsWiki.isVisible = true
                    btnBreedsWiki.setOnClickListener { view ->
                        val url = it.wikipediaUrl
                        val intent = Intent(Intent.ACTION_VIEW)
                        intent.data = Uri.parse(url)
                        startActivity(intent)
                    }
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewElements()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val BREED_NAME = "BREED_NAME"
    }
}