package com.harutyun.pizzadelivery.presentation.menu

import androidx.recyclerview.widget.DiffUtil
import com.harutyun.domain.model.Pizza

internal class DiffCallback : DiffUtil.ItemCallback<Pizza>() {

    override fun areItemsTheSame(oldItem: Pizza, newItem: Pizza) =
        oldItem.name == newItem.name

    override fun areContentsTheSame(oldItem: Pizza, newItem: Pizza) =
        oldItem == newItem

}
