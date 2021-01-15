package com.example.fooddeliverydemo.di

import com.example.fooddeliverydemo.network.NetworkSimulator
import com.example.fooddeliverydemo.network.NetworkSimulatorImpl
import com.example.fooddeliverydemo.repository.Repository
import com.example.fooddeliverydemo.repository.RepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
abstract class BindingModule {

    @Singleton
    @Binds
    abstract fun bindRepository(repository: RepositoryImpl): Repository

    @Singleton
    @Binds
    abstract fun bindNetworkSimulator(networkSimulatorImpl: NetworkSimulatorImpl): NetworkSimulator

//    @Binds
//    abstract fun viewModel(factory: MainViewModel_AssisstedFactory): MainViewModel.Factory
}