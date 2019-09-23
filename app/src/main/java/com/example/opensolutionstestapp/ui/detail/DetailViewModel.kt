package com.example.opensolutionstestapp.ui.detail

import androidx.lifecycle.MutableLiveData
import com.example.opensolutionstestapp.api.service.OpenSolutionsTestService
import com.example.opensolutionstestapp.ui.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import org.kodein.di.generic.instance

class DetailViewModel : BaseViewModel<DetailViewModel>() {

    private val openSolutionsTestService: OpenSolutionsTestService by instance()

    val loadingLiveData = MutableLiveData<Boolean>()
    val errorMessageLiveData = MutableLiveData<String>()

    val data = MutableLiveData<String>()

    fun getCurrency(date: String) {
        openSolutionsTestService.getCurrency(date)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { loadingLiveData.value = true }
            .doAfterTerminate { loadingLiveData.value = false }
            .subscribeBy(onSuccess = {
                data.value = it.toString()
            }, onError = {
                errorMessageLiveData.value = it.message
            })
    }
}