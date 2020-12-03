package com.codecool.animalsapp

import com.codecool.animalsapp.di.ApiModule
import com.codecool.animalsapp.model.AnimalApiService

class ApiModuleTest(val mockService: AnimalApiService): ApiModule() {

    override fun provideAnimalApiService(): AnimalApiService {
        return mockService
    }
}