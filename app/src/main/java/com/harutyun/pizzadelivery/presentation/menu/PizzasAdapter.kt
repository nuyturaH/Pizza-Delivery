package com.harutyun.pizzadelivery.presentation.menu

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.harutyun.domain.model.Pizza
import com.harutyun.pizzadelivery.databinding.ItemPizzaBinding

internal class PizzasAdapter : ListAdapter<Pizza, PizzasAdapter.BalanceViewHolder>(
    AsyncDifferConfig.Builder(DiffCallback()).build()
) {
    inner class BalanceViewHolder(val binding: ItemPizzaBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BalanceViewHolder {
        val binding =
            ItemPizzaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BalanceViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BalanceViewHolder, position: Int) {
        val pizza = getItem(position)

        holder.binding.tvNameItemPizza.text = pizza.name
        holder.binding.tvPriceItemPizza.text = pizza.price.toString()
    }

    override fun getItemCount(): Int {
        return currentList.size
    }
}
