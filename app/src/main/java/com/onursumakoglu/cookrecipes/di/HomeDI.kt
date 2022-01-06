package com.onursumakoglu.cookrecipes.di

import com.onursumakoglu.cookrecipes.data.home.HomeRepository
import com.onursumakoglu.cookrecipes.data.home.api.HomeAPI
import com.onursumakoglu.cookrecipes.data.home.repository.HomeRepoImp
import com.onursumakoglu.cookrecipes.domain.usecase.HomeUseCase
import com.onursumakoglu.cookrecipes.presentation.home.HomeViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HomeDI {
    companion object{
        val appModule = module {

            viewModel { HomeViewModel(get()) }

            single <HomeAPI> {

                val interceptor = HttpLoggingInterceptor()
                interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
                val client: OkHttpClient = OkHttpClient.Builder().addInterceptor(interceptor).build()

                val retrofit = Retrofit.Builder()
                    .baseUrl("https://api.spoonacular.com")
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

                retrofit.create(HomeAPI::class.java)
            }

            single <HomeRepository> {HomeRepoImp(get())}

            single {HomeUseCase(get())}
        }

    }

}