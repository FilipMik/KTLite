package com.kaloricketabulky.ktlite.ui.foodlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.kaloricketabulky.ktlite.databinding.FragmentFoodListBinding
import com.kaloricketabulky.ktlite.domain.model.Food
import com.kaloricketabulky.ktlite.tools.observeNonNull
import com.kaloricketabulky.ktlite.ui.foodlist.adapter.FoodListAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FoodListFragment : Fragment(), FoodListView {

    private var _binding: FragmentFoodListBinding? = null

    private val foodListViewModel by viewModels<FoodListViewModel>()

    @Inject lateinit var adapter: FoodListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentFoodListBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = foodListViewModel
            _binding = this
        }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding?.let {
            it.foodListRecycler.adapter = adapter
        }

        observeSearchChange()
        observeListChange()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onFoodClick(food: Food) {
        food
        //TODO navigate to food detail
    }

    private fun observeListChange() {
        foodListViewModel.foodList.observeNonNull(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    private fun observeSearchChange() {
        foodListViewModel.apply {
            searchQuery.observeNonNull(viewLifecycleOwner) {
                getFoodList(it)
            }
        }
    }
}
