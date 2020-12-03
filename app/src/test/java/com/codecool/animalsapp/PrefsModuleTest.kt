package com.codecool.animalsapp

import android.app.Application
import com.codecool.animalsapp.di.PrefsModule
import com.codecool.animalsapp.util.SharedPreferencesHelper

class PrefsModuleTest(val mockPrefs: SharedPreferencesHelper): PrefsModule() {

    override fun provideSharedPreferences(app: Application): SharedPreferencesHelper {
        return mockPrefs
    }
}