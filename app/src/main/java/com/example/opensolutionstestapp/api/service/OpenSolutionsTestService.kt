package com.example.opensolutionstestapp.api.service

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenSolutionsTestService {

    @GET("scripts/XML_daily.asp")
    fun getCurrency(@Query("date_req") date: String): Single<String>
}