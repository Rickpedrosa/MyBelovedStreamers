package com.ricknardo.mybelovedstreamers.base.downgrade

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

@Suppress("UNCHECKED_CAST")
abstract class RecyclerAdapter<M, VH : RecyclerView.ViewHolder>(
    private var mData: List<M>,
    private val layoutId: Int,
    private val viewHolderClass: Class<out RecyclerView.ViewHolder>
) : RecyclerView.Adapter<VH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val view = LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
        return viewHolderClass.constructors[0].newInstance(view) as VH
    }

    override fun getItemCount() = mData.size

    fun setData(data: List<M>) {
        mData = data
        notifyDataSetChanged()
    }

    fun getItem(position: Int): M {
        return mData[position]
    }
}