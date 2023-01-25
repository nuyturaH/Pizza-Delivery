package com.harutyun.pizzadelivery.presentation.menu

import androidx.lifecycle.ViewModel
import com.harutyun.domain.usecase.GetPizzasUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MenuViewModel @Inject constructor(private val getPizzasUseCase: GetPizzasUseCase) :
    ViewModel() {

}