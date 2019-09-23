package com.example.opensolutionstestapp.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.recyclerview.widget.RecyclerView
import com.example.opensolutionstestapp.R
import java.text.SimpleDateFormat
import java.util.*

class CurrencyAdapter(var list: ArrayList<String>,
                      val onItemClickListener: (String) -> Unit): RecyclerView.Adapter<CurrencyHolder>() {

    val sdf = SimpleDateFormat("dd/MM/yyyy")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_date, parent, false)
        return CurrencyHolder(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: CurrencyHolder, position: Int) = holder.bind(list[position], onItemClickListener)

    fun updateData(list: ArrayList<String>) {
        this.list = list
        notifyDataSetChanged()
    }

    fun addNewDates(progressBar: ProgressBar) {

        progressBar.visibility = View.VISIBLE

        val lastDate = list[list.size - 1]
        val date = Calendar.getInstance()
        date.time = (sdf.parse(lastDate)!!)

        for(i in 1..10) {
            date.add(Calendar.DATE, -1)
            this.list.add(sdf.format(date.time))
        }

        progressBar.visibility = View.GONE

        notifyDataSetChanged()
    }
}