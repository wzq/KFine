package com.firstlink.duo

import android.app.Application
import android.content.Context

/**
 * Created by wzq on 15/12/23.
 */
class App : Application(){
    companion object{
        var instance: Context? = null
    }

    override fun onCreate() {
        super.onCreate()
        instance = this;
    }
}