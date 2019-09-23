package com.example.opensolutionstestapp.ui.main.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_date.view.*

class CurrencyHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(data: String, onItemClickListener: (String) -> Unit) {
        itemView.setOnClickListener { onItemClickListener(data) }

        itemView.tv_date.text = data
    }

}