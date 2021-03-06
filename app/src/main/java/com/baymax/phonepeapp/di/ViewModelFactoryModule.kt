package com.baymax.phonepeapp.di

import androidx.lifecycle.ViewModelProvider
import com.baymax.phonepeapp.ui.viewmodels.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}