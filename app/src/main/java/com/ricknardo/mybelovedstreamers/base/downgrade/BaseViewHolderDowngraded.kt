package com.ricknardo.mybelovedstreamers.base.downgrade

import android.view.View
import androidx.recyclerview.widget.RecyclerView


abstract class BaseViewHolderDowngraded<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(type: T)
}