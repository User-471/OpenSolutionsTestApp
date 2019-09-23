package com.example.opensolutionstestapp.ui.main

import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.opensolutionstestapp.R
import com.example.opensolutionstestapp.ui.base.BaseActivity
import com.example.opensolutionstestapp.ui.detail.DetailActivity
import com.example.opensolutionstestapp.ui.main.adapter.CurrencyAdapter
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : BaseActivity() {

    private lateinit var adapter: CurrencyAdapter
    private lateinit var scrollListener: RecyclerView.OnScrollListener

    val sdf = SimpleDateFormat("dd/MM/yyyy")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRecycler()
    }

    private fun initRecycler() {

        val date = Calendar.getInstance()

        adapter = CurrencyAdapter(arrayListOf()) {onItemClicked(it) }
        val manager = LinearLayoutManager(this)
        rv_dates.layoutManager = manager
        rv_dates.adapter = adapter

        scrollListener = object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(rv_dates, newState)
                val totalItemCount = recyclerView.layoutManager!!.itemCount
                if (totalItemCount == manager.findLastVisibleItemPosition() + 1) {
                    adapter.addNewDates(pb_loading)
                }
            }
        }
        rv_dates.addOnScrollListener(scrollListener)

        for(i in 1..15) {
            date.add(Calendar.DATE, -1)
            adapter.list.add(sdf.format(date.time))
        }
    }

    private fun onItemClicked (date: String) {
        startActivity(DetailActivity.newIntent(this, date))
    }
}
