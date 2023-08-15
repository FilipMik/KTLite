package com.kaloricketabulky.ktlite.ui.foodlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.kaloricketabulky.ktlite.databinding.FragmentFoodListBinding
import dagger.hilt.android.AndroidEntryPoint


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
@AndroidEntryPoint
class FoodListFragment : Fragment() {

    private var _binding: FragmentFoodListBinding? = null

    private val foodListViewModel by viewModels<FoodListViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentFoodListBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = foodListViewModel
        }.root

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
