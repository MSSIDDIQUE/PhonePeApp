package com.baymax.phonepeapp.di.main

import com.baymax.phonepeapp.ui.fragments.HomeFragment
import com.baymax.phonepeapp.ui.fragments.MovieDetailsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainFragmentBuilderModule {

    @ContributesAndroidInjector
    abstract fun contributeHomeFragment(): HomeFragment

    @ContributesAndroidInjector
    abstract fun contributeMovieDetailsFragment(): MovieDetailsFragment
}