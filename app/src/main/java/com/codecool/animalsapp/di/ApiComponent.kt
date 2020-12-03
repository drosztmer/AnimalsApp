package com.codecool.animalsapp.di

import com.codecool.animalsapp.model.AnimalApiService
import dagger.Component

@Component(modules = [ApiModule::class])
interface ApiComponent {

    fun inject(service: AnimalApiService)

}