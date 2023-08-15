package com.kaloricketabulky.ktlite.ui.fooddetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.kaloricketabulky.ktlite.databinding.FragmentFoodDetailBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class FoodDetailFragment : Fragment() {

    private var binding: FragmentFoodDetailBinding? = null

    private val foodDetailViewModel by viewModels<FoodDetailViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentFoodDetailBinding.inflate(inflater, container, false).apply {
        lifecycleOwner = viewLifecycleOwner
        viewModel = foodDetailViewModel
    }.root

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
