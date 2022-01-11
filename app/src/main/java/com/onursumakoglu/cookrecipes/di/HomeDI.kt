package com.onursumakoglu.cookrecipes.di

import androidx.room.Room
import com.onursumakoglu.cookrecipes.data.home.local.HomeDatabase
import com.onursumakoglu.cookrecipes.data.home.repository.CommonRepository
import com.onursumakoglu.cookrecipes.data.home.remote.api.HomeAPI
import com.onursumakoglu.cookrecipes.data.home.repository.CommonRepoImpl
import com.onursumakoglu.cookrecipes.domain.usecase.HomeUseCase
import com.onursumakoglu.cookrecipes.presentation.home.HomeViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
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

            single <CommonRepository> {CommonRepoImpl(get(), get())}

            single {HomeUseCase(get())}

            single {
                Room.databaseBuilder(
                    androidContext(),
                    HomeDatabase::class.java, "HomeDatabase"
                ).allowMainThreadQueries().build()
            }

        }
    }
}