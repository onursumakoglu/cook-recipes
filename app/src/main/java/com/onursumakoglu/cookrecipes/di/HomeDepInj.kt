package com.onursumakoglu.cookrecipes.di

import com.onursumakoglu.cookrecipes.data.home.HomeRepository
import com.onursumakoglu.cookrecipes.data.home.api.HomeAPI
import com.onursumakoglu.cookrecipes.data.home.repository.HomeRepoImp
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HomeDepInj {
    companion object{
        val appModule = module {



            single <HomeAPI> {

                val retrofit = Retrofit.Builder()
                    .baseUrl("https://api.spoonacular.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

                retrofit.create(HomeAPI::class.java)
            }

            single <HomeRepository> {HomeRepoImp(get())}

        }

    }

}