package com.example.opensolutionstestapp.ui.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.opensolutionstestapp.R
import com.example.opensolutionstestapp.model.ValCurs
import com.example.opensolutionstestapp.ui.base.BaseActivity
import com.example.opensolutionstestapp.ui.widget.withColor
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_detail.*
import org.json.JSONException
import org.json.JSONObject
import org.json.XML


class DetailActivity : BaseActivity() {

    companion object {
        fun newIntent(context: Context, date: String): Intent {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(DATE, date)
            return intent
        }

        const val DATE = "date"
    }

    private lateinit var date: String
    private lateinit var viewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        date = intent.getStringExtra(DATE)!!
        viewModel = ViewModelProviders.of(this).get(DetailViewModel::class.java).init(kodein)

        observeErrorMessage()
        observeLoadingData()
        observeCurrency()
        setOnClickListeners()


        viewModel.getCurrency(date)
    }

    private fun setOnClickListeners() {
        ib_toolbar_back.setOnClickListener {
            onBackPressed()
        }
    }

    private fun observeCurrency() {

        viewModel.data.observe(this, Observer {
            it?.let {

                var jsonObj: JSONObject? = null
                try {
                    jsonObj = XML.toJSONObject(it)
                } catch (e: JSONException) {
                    Log.e("JSON exception", e.message)
                    e.printStackTrace()
                }

                val valCursDataType = object : TypeToken<ValCurs>() {}.type
                val valCurs: ValCurs = Gson().fromJson(jsonObj?.getJSONObject("ValCurs").toString(), valCursDataType)

                tv_date.text = getString(R.string.date, valCurs.Date)
                tv_dollar.text = getString(R.string.dollar, valCurs.Valute.find { valute ->  valute.CharCode == "USD" }?.Value)
                tv_euro.text = getString(R.string.euro, valCurs.Valute.find { valute ->  valute.CharCode == "EUR" }?.Value)
            }
        })
    }

    private fun observeErrorMessage() {
        viewModel.errorMessageLiveData.observe(this, Observer {
            it?.let {
                Snackbar.make(
                    content,
                    it,
                    Snackbar.LENGTH_LONG)
                    .withColor(resources.getColor(R.color.red_1))
                    .show()
            }
        })
    }

    private fun observeLoadingData() {
        viewModel.loadingLiveData.observe(this, Observer {
            it?.let {
                if(it) {
                    pb_loading.visibility = View.VISIBLE
                }
                else {
                    pb_loading.visibility = View.GONE
                }
            }
        })
    }
}
