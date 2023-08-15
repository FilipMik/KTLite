package com.kaloricketabulky.ktlite.ui.fooddetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.kaloricketabulky.ktlite.databinding.FragmentFoodDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FoodDetailFragment : Fragment() {

    private var binding: FragmentFoodDetailBinding? = null

    private val foodDetailViewModel by viewModels<FoodDetailViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentFoodDetailBinding.inflate(inflater, container, false).apply {
        lifecycleOwner = viewLifecycleOwner
        viewModel = foodDetailViewModel
        binding = this
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val args: FoodDetailFragmentArgs by navArgs()
        foodDetailViewModel.loadFoodDetail(args.guidFood)

        (requireActivity() as AppCompatActivity).supportActionBar?.title = args.foodName
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
