package com.onursumakoglu.cookrecipes

import android.app.Application
import com.onursumakoglu.cookrecipes.di.HomeDI
import com.onursumakoglu.cookrecipes.di.RecipesDI
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class AppModule : Application(){

    override fun onCreate() {
        super.onCreate()

        startKoin{
            androidLogger(Level.ERROR)
            androidContext(this@AppModule)
            modules(listOf(HomeDI.appModule, RecipesDI.appModule))
        }

    }
}