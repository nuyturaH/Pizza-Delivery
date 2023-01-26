package com.harutyun.pizzadelivery.presentation.menu

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.harutyun.domain.model.Pizza
import com.harutyun.domain.model.PizzaSize
import com.harutyun.domain.usecase.GetPizzasUseCase
import com.harutyun.domain.usecase.SaveAddedPizzasUseCase
import com.harutyun.pizzadelivery.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class MenuViewModel @Inject constructor(
    private val getPizzasUseCase: GetPizzasUseCase,
    private val saveAddedPizzasUseCase: SaveAddedPizzasUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<MenuUiState>(MenuUiState.Loading)
    val uiState: StateFlow<MenuUiState> = _uiState.asStateFlow()

    private var pizzas = emptyList<Pizza>()
    private var addedPizzas = mutableListOf<Pizza>()

    init {
        getPizzas()
    }

    fun getPizzas() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                _uiState.update { MenuUiState.Loading }

                try {
                    val getPizzasUseCase = getPizzasUseCase()

                    if (getPizzasUseCase.isEmpty()) {
                        _uiState.value = MenuUiState.NoData
                    } else {
                        pizzas = getPizzasUseCase

                        var confirmButtonVisible = false
                        if (addedPizzas.isNotEmpty()) {
                            // After refreshing the list keep confirm button state
                            if (addedPizzas.size == 2 || addedPizzas.size == 1 && addedPizzas[0].pizzaSize == PizzaSize.Full) {
                                confirmButtonVisible = true
                            }

                            // After refreshing the list keep added pizzas state
                            addedPizzas.forEach { i ->
                                pizzas.find { j -> j.name == i.name }?.let {
                                    it.isAdded = true
                                    it.pizzaSize = i.pizzaSize
                                }
                            }
                        }
                        _uiState.update {
                            MenuUiState.Success(
                                pizzas,
                                isConfirmButtonVisible = confirmButtonVisible
                            )
                        }
                    }
                } catch (e: IOException) {
                    _uiState.update { MenuUiState.Error("No Internet Connection") }
                } catch (e: Exception) {
                    _uiState.update { MenuUiState.Error(e.localizedMessage ?: "") }
                }
            }
        }
    }

    fun addPizza(pizza: Pizza) {
        if (addedPizzas.isEmpty() || (pizza.pizzaSize == PizzaSize.Half && addedPizzas.size == 1 && addedPizzas[0].pizzaSize == PizzaSize.Half)) {
            pizzas.find { it.name == pizza.name }?.isAdded = true

            addedPizzas.add(pizza)

            if (addedPizzas.size == 2 || addedPizzas[0].pizzaSize == PizzaSize.Full) {
                _uiState.update {
                    (it as MenuUiState.Success).copy(pizzas = pizzas, isConfirmButtonVisible = true)
                }
                saveAddedPizzasUseCase(addedPizzas)
            }
        } else {
            _uiState.update { (it as MenuUiState.Success).copy(snackBarMessage = R.string.adding_message) }
        }
    }

    fun removePizza(pizza: Pizza) {
        pizzas.find { it.name == pizza.name }?.isAdded = false
        addedPizzas.removeAll { it.name == pizza.name }

        if (addedPizzas.size == 0 || addedPizzas.size == 1 && addedPizzas[0].pizzaSize == PizzaSize.Half) {
            _uiState.update {
                (it as MenuUiState.Success).copy(pizzas = pizzas, isConfirmButtonVisible = false)
            }
        }
    }

    fun snackBarMessageIsShown() {
        _uiState.update { (it as MenuUiState.Success).copy(snackBarMessage = null) }
    }

}