package com.harutyun.pizzadelivery.di

import com.harutyun.domain.repository.PizzasRepository
import com.harutyun.domain.usecase.GetPizzasUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class DomainModule {

    @Provides
    fun provideGetPizzasUseCase(pizzasRepository: PizzasRepository): GetPizzasUseCase {
        return GetPizzasUseCase(pizzasRepository)
    }
}