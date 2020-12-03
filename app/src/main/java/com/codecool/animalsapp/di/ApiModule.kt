package com.codecool.animalsapp.di

import com.codecool.animalsapp.model.AnimalApi
import com.codecool.animalsapp.model.AnimalApiService
import com.codecool.animalsapp.util.BASE_URL
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class ApiModule {

    @Provides
    fun provideAnimalApi(): AnimalApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(AnimalApi::class.java)
    }

    @Provides
    fun provideAnimalApiService(): AnimalApiService {
        return AnimalApiService()
    }
}