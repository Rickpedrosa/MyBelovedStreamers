package com.ricknardo.mybelovedstreamers.base.downgrade

import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerViewAdapterDowngraded<M, VH : BaseViewHolderDowngraded<M>> :
    RecyclerView.Adapter<VH>() {

    var data: List<M> = mutableListOf()

    override fun getItemCount(): Int {
        return data.size
    }

    fun getItem(position: Int): M {
        return data[position]
    }

    fun submitList(list: List<M>) {
        data = list
        notifyDataSetChanged()
    }
}