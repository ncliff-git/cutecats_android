package com.github.ncliff.cutecats.ui.vote

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.github.ncliff.cutecats.data.network.CatImage
import com.github.ncliff.cutecats.data.network.CatVote
import com.github.ncliff.cutecats.databinding.FragmentVoteBinding
import com.github.ncliff.cutecats.ui.viewmodel.SharedCatApiViewModel

class VoteFragment : Fragment() {
    private var catImage: CatImage? = null
    private lateinit var _binding: FragmentVoteBinding
    private val catViewModel: SharedCatApiViewModel by lazy {
        ViewModelProvider(this).get(
            SharedCatApiViewModel::class.java
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentVoteBinding.inflate(inflater, container, false)
        registrationForObservables()
        return _binding.root
    }

    private fun registrationForObservables() {
        catViewModel.getRandomCatImage{}.observe(viewLifecycleOwner) {
            catImage = it
            _binding.voteCatImage.load(it.url)
        }

        _binding.btnLoveIt.setOnClickListener {
            catImage.also { img ->
                catViewModel.postCatVote(CatVote(imageId = img?.id, value = 1)) {}
            }
            catViewModel.getRandomCatImage{}.observe(viewLifecycleOwner) {
                catImage = it
                _binding.voteCatImage.load(it.url)
            }
        }
        _binding.btnNopeIt.setOnClickListener {
            catImage.also { img ->
                catViewModel.postCatVote(CatVote(imageId = img?.id, value = 0)) {}
            }
            catViewModel.getRandomCatImage{}.observe(viewLifecycleOwner) {
                catImage = it
                _binding.voteCatImage.load(it.url)
            }
        }

        _binding.btnFavIt.setOnClickListener {
            catImage?.id?.also {
                catViewModel.postSaveImageAsFavourites(it) {}
            }
        }
    }
}