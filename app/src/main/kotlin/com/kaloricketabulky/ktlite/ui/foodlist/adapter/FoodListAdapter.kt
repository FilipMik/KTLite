package com.kaloricketabulky.ktlite.ui.foodlist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kaloricketabulky.ktlite.databinding.ListItemFoodBinding
import com.kaloricketabulky.ktlite.domain.model.Food
import com.kaloricketabulky.ktlite.ui.foodlist.FoodListView
import javax.inject.Inject

class FoodListAdapter @Inject constructor(
    private val foodListView: FoodListView
)
    : ListAdapter<Food, FoodListAdapter.FoodViewHolder>(DiffUtilCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return FoodViewHolder(ListItemFoodBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) =
        holder.bind(getItem(position))

    inner class FoodViewHolder(private val binding: ListItemFoodBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Food) {
            binding.food = item
            binding.view = foodListView
            binding.executePendingBindings()
        }
    }

    object DiffUtilCallback : DiffUtil.ItemCallback<Food>() {

        override fun areContentsTheSame(
            oldItem: Food,
            newItem: Food
        ): Boolean = oldItem == newItem

        override fun areItemsTheSame(
            oldItem: Food,
            newItem: Food
        ): Boolean = oldItem.guid == newItem.guid
    }
}
