package com.harutyun.pizzadelivery.presentation.menu

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.harutyun.domain.model.Pizza
import com.harutyun.domain.model.PizzaSize
import com.harutyun.pizzadelivery.R
import com.harutyun.pizzadelivery.databinding.ItemPizzaBinding

internal class PizzasAdapter(onItemClickListener: OnItemClickListener) :
    ListAdapter<Pizza, PizzasAdapter.PizzaViewHolder>(
        AsyncDifferConfig.Builder(DiffCallback()).build()
    ) {

    private val mOnItemClickListener: OnItemClickListener = onItemClickListener


    inner class PizzaViewHolder(val binding: ItemPizzaBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PizzaViewHolder {
        val binding =
            ItemPizzaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PizzaViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PizzaViewHolder, position: Int) {
        val pizza = getItem(position)

        var selectedSize: PizzaSize = PizzaSize.Full


        holder.binding.apply {

            tvNameItemPizza.text = pizza.name
            tvPriceItemPizza.text = pizza.formattedPrice

            if (pizza.isAdded) {
                btnAddItemPizza.isEnabled = false
                btnAddItemPizza.setText(R.string.added)
                ivRemoveItemPizza.visibility = View.VISIBLE
            } else {
                btnAddItemPizza.isEnabled = true
                btnAddItemPizza.setText(R.string.add)
                ivRemoveItemPizza.visibility = View.GONE
            }

            btnAddItemPizza.setOnClickListener {
                mOnItemClickListener.onAddButtonClicked(
                    pizza,
                    selectedSize
                )
                notifyItemChanged(position)
            }

            ivRemoveItemPizza.setOnClickListener {
                mOnItemClickListener.onRemoveButtonClicked(
                    pizza,
                    selectedSize
                )
                notifyItemChanged(position)
            }

            spinnerItemPizza.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(parent: AdapterView<*>?) {

                }

                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View,
                    position: Int,
                    id: Long
                ) {
                    when (position) {
                        0 -> selectedSize = PizzaSize.Full
                        1 -> selectedSize = PizzaSize.Half
                    }
                    mOnItemClickListener.onSizeSelected(pizza, selectedSize)
                }
            }

        }
    }

    override fun getItemCount(): Int {
        return currentList.size
    }

    interface OnItemClickListener {
        fun onAddButtonClicked(pizza: Pizza, size: PizzaSize)
        fun onRemoveButtonClicked(pizza: Pizza, size: PizzaSize)
        fun onSizeSelected(pizza: Pizza, size: PizzaSize)
    }
}
