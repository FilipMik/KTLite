package com.kaloricketabulky.ktlite.ui.foodlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.kaloricketabulky.ktlite.R
import com.kaloricketabulky.ktlite.databinding.FragmentFoodListBinding
import com.kaloricketabulky.ktlite.domain.model.Food
import com.kaloricketabulky.ktlite.tools.observeNonNull
import com.kaloricketabulky.ktlite.ui.foodlist.adapter.FoodListAdapter
import com.kaloricketabulky.ktlite.ui.foodlist.adapter.FoodListAdapterFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FoodListFragment : Fragment(), FoodListView {

    private var binding: FragmentFoodListBinding? = null

    private val foodListViewModel by viewModels<FoodListViewModel>()

    @Inject lateinit var adapterFactory: FoodListAdapterFactory

    private val adapter: FoodListAdapter by lazy {
        adapterFactory.create(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentFoodListBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = foodListViewModel
            binding = this
        }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding?.let {
            it.foodListRecycler.adapter = adapter
        }

        observeSearchChange()
        observeListChange()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    override fun onFoodClick(food: Food) {
        val bundle = Bundle().apply {
            putString("guidFood", food.guid)
            putString("foodName", food.name)
        }
        findNavController().navigate(R.id.action_ListFragment_to_DetailFragment, bundle)
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
