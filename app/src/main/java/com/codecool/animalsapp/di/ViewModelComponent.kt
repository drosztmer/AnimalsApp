package com.codecool.animalsapp.di

import com.codecool.animalsapp.viewmodel.ListViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApiModule::class, PrefsModule::class, AppModule::class])
interface ViewModelComponent {

    fun inject(viewModel: ListViewModel)

}