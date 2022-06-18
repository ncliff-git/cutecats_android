package com.github.ncliff.cutecats.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.ncliff.cutecats.R
import com.github.ncliff.cutecats.databinding.FragmentDetailedItemBinding

class DetailedItemFragment : Fragment() {
    private var _binding: FragmentDetailedItemBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailedItemBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}