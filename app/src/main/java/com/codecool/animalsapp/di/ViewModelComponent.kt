package com.codecool.animalsapp.di

import com.codecool.animalsapp.viewmodel.ListViewModel
import dagger.Component

@Component(modules = [ApiModule::class])
interface ViewModelComponent {

    fun inject(viewModel: ListViewModel)

}