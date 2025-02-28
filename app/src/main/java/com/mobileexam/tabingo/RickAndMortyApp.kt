package com.mobileexam.tabingo

import android.app.Application
import com.mobileexam.tabingo.data.AppContainer
import com.mobileexam.tabingo.data.DefaultAppContainer

class RickAndMortyApp: Application(){
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}