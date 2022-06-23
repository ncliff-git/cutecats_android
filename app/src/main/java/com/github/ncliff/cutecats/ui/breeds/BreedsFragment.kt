package com.github.ncliff.cutecats.ui.breeds

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.github.ncliff.cutecats.ui.viewmodel.SharedCatApiViewModel
import com.github.ncliff.cutecats.R
import com.github.ncliff.cutecats.databinding.FragmentBreedsBinding
import com.github.ncliff.cutecats.ui.adapters.CatBreedsRVAdapter

class BreedsFragment : Fragment() {
    private var _binding: FragmentBreedsBinding? = null
    private val binding get() = _binding!!
    private var _rvCatBreedsAdapter: CatBreedsRVAdapter? = null
    private val catViewModel: SharedCatApiViewModel by lazy {
        ViewModelProvider(this).get(
            SharedCatApiViewModel::class.java
        )
    }

    private val clickOnBreeds: (breedName: String) -> Unit = {
        val bundle = Bundle()
        bundle.putString(BREED_NAME, it)
        findNavController().navigate(R.id.action_homeFragment_to_detailedItemFragment, bundle)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBreedsBinding.inflate(inflater, container, false)
        initViewElements()
        registrationOnObservables()
        return binding.root
    }

    private fun initViewElements() {
        _rvCatBreedsAdapter = CatBreedsRVAdapter(onClick = clickOnBreeds)
        _binding?.rvCatBreeds?.adapter = _rvCatBreedsAdapter
    }

    private fun registrationOnObservables() {
        catViewModel.breedsList.observe(viewLifecycleOwner) {
            _rvCatBreedsAdapter?.setCatBreedsList(it)
            progressBarVisibility()
        }
    }

    private fun progressBarVisibility() {
        binding.progressBarCatBreedsList.isVisible = when(_rvCatBreedsAdapter?.itemCount!! >= 0) {
            true -> false
            false -> true
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // TODO: Добавить обновление списка поятнув список вверх.
        catViewModel.getBreedsList {}
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val BREED_NAME = "BREED_NAME"
    }
}