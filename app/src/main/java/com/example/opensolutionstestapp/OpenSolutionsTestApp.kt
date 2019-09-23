package com.example.opensolutionstestapp

import android.app.Application
import com.example.opensolutionstestapp.injection.initKodein
import com.example.opensolutionstestapp.injection.module.appModule
import com.example.opensolutionstestapp.injection.module.netModule
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware

class OpenSolutionsTestApp : Application(), KodeinAware {

    override val kodein: Kodein = Kodein.lazy {
        import(appModule(this@OpenSolutionsTestApp))
        import(netModule())
    }

    override fun onCreate() {
        super.onCreate()

        initKodein(this)
    }
}