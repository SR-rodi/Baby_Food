package com.example.artyomkafood.core.basemodel

import androidx.recyclerview.widget.RecyclerView

abstract class RecyclerViewListAdapter<I : Any, VH : RecyclerView.ViewHolder> :
    RecyclerView.Adapter<VH>() {

    protected var list = emptyList<I>()

    fun submitList(items: List<I>) {
        list = items
    }

    override fun getItemCount() = list.size
}