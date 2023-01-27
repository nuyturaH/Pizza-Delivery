package com.harutyun.pizzadelivery.presentation.ordersuccessful

import com.harutyun.domain.usecase.GetAddedPizzasUseCase
import com.harutyun.domain.usecase.GetTotalPriceUseCase
import org.junit.Assert
import org.junit.Test
import org.mockito.Mockito
import org.mockito.kotlin.any
import org.mockito.kotlin.mock

class OrderSuccessfulViewModelTest {

    private val getAddedPizzasUseCase = mock<GetAddedPizzasUseCase>()
    private val getTotalPriceUseCase = mock<GetTotalPriceUseCase>()

    fun tearDown() {
        Mockito.reset(getAddedPizzasUseCase)
        Mockito.reset(getTotalPriceUseCase)
    }

    @Test
    fun `should get total price`() {
        val testTotalPrice = 12.34.toBigDecimal()
        Mockito.`when`(getTotalPriceUseCase(any())).thenReturn(testTotalPrice)

        val orderSuccessfulViewModel =
            OrderSuccessfulViewModel(getAddedPizzasUseCase, getTotalPriceUseCase)

        val actual = orderSuccessfulViewModel.uiState.value.totalPrice
        val expected = 12.34.toBigDecimal()

        Assert.assertEquals(expected, actual)
    }
}