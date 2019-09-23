package com.example.opensolutionstestapp.injection

import android.app.Application
import com.example.opensolutionstestapp.injection.module.appModule
import com.example.opensolutionstestapp.injection.module.netModule
import org.kodein.di.Kodein

lateinit var di : Kodein

fun initKodein(app: Application) {

    di = Kodein {
        import(appModule(app))
        import(netModule())
    }
}