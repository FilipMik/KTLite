package com.kaloricketabulky.ktlite.ui.foodlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.kaloricketabulky.ktlite.R

import com.kaloricketabulky.ktlite.databinding.FragmentFoodListBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
@AndroidEntryPoint
class FoodListFragment : Fragment() {

    private var _binding: FragmentFoodListBinding? = null

    private val binding get() = _binding
    private val foodListViewModel by viewModels<FoodListViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFoodListBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.let {
            it.buttonFirst.setOnClickListener {
                //findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
                foodListViewModel.getFoodList()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
