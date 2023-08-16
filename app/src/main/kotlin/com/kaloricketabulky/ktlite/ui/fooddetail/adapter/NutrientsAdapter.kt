package com.kaloricketabulky.ktlite.ui.fooddetail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kaloricketabulky.ktlite.databinding.ListItemNutrientBinding
import com.kaloricketabulky.ktlite.domain.model.Nutrient
import javax.inject.Inject

class NutrientsAdapter @Inject constructor() :
    ListAdapter<Nutrient, NutrientsAdapter.NutrientViewHolder>(DiffUtilCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NutrientViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return NutrientViewHolder(ListItemNutrientBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: NutrientViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class NutrientViewHolder(private val binding: ListItemNutrientBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(nutrient: Nutrient) {
            binding.nutrient = nutrient
            binding.executePendingBindings()
        }
    }

    object DiffUtilCallback : DiffUtil.ItemCallback<Nutrient>() {

        override fun areContentsTheSame(
            oldItem: Nutrient,
            newItem: Nutrient
        ): Boolean = oldItem == newItem

        override fun areItemsTheSame(
            oldItem: Nutrient,
            newItem: Nutrient
        ): Boolean = oldItem.nutrientNameId == newItem.nutrientNameId
    }
}
