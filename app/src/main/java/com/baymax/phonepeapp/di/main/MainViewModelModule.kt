package com.baymax.weatherforecast.di.main

import androidx.lifecycle.ViewModel
import com.baymax.phonepeapp.di.ViewModelKey
import com.baymax.phonepeapp.ui.viewmodels.HomeFragmentViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MainViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(HomeFragmentViewModel::class)
    abstract fun bindHomeFragmentViewModel(viewModel: HomeFragmentViewModel): ViewModel
}