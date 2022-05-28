package com.baymax.phonepeapp.di

import com.baymax.phonepeapp.ui.activities.MainActivity
import com.baymax.phonepeapp.di.main.MainFragmentBuilderModule
import com.baymax.phonepeapp.di.main.MainModule
import com.baymax.weatherforecast.di.main.MainScope
import com.baymax.weatherforecast.di.main.MainViewModelModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {
    @MainScope
    @ContributesAndroidInjector(
        modules = [
            MainFragmentBuilderModule::class,
            MainModule::class,
            MainViewModelModule::class
        ]
    )
    abstract fun contributeMainActivity(): MainActivity
}