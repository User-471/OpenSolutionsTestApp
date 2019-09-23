package com.example.opensolutionstestapp.injection.module

import com.example.opensolutionstestapp.api.interceptors.LoggingInterceptor
import com.example.opensolutionstestapp.api.service.OpenSolutionsTestService
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit


fun netModule() = Kodein.Module {
    bind<OkHttpClient>() with singleton { provideOkHttpClient() }
    bind<Retrofit>("1") with singleton { provideRetrofit(instance(), "http://www.cbr.ru/") }
    bind<OpenSolutionsTestService>() with singleton { instance<Retrofit>("1").create(OpenSolutionsTestService::class.java) }
}

private fun provideOkHttpClient(): OkHttpClient =
    OkHttpClient.Builder()
        .connectTimeout(2, TimeUnit.MINUTES)
        .readTimeout(2, TimeUnit.MINUTES)
        .writeTimeout(2, TimeUnit.MINUTES)
        .addInterceptor(LoggingInterceptor())
        .build()

var gson = GsonBuilder()
    .setLenient()
    .create()

private fun provideRetrofit(client: OkHttpClient, string: String): Retrofit =
    Retrofit.Builder()
        .baseUrl(string)
        .client(client)
//        .addConverterFactory(SimpleXmlConverterFactory.create())
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()

private fun provideRestRetrofit(client: OkHttpClient, string: String): Retrofit =
    Retrofit.Builder()
        .baseUrl(string)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()