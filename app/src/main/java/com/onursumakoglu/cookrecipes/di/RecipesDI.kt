package com.onursumakoglu.cookrecipes.di

import com.onursumakoglu.cookrecipes.data.home.remote.api.HomeAPI
import com.onursumakoglu.cookrecipes.data.home.repository.CommonRepoImpl
import com.onursumakoglu.cookrecipes.data.home.repository.CommonRepository
import com.onursumakoglu.cookrecipes.domain.usecase.RecipesUseCase
import com.onursumakoglu.cookrecipes.presentation.recipes.RecipesViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RecipesDI {
    companion object{
        val appModule = module {

            viewModel { RecipesViewModel(get()) }

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

            single <CommonRepository> { CommonRepoImpl(get(), get()) }

            single { RecipesUseCase(get()) }
        }

    }

}