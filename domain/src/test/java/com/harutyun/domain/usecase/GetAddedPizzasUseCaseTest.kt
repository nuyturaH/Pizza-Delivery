package com.harutyun.domain.usecase

import com.harutyun.domain.model.Pizza
import com.harutyun.domain.model.PizzaSize
import com.harutyun.domain.repository.PizzasRepository
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.kotlin.mock

class GetAddedPizzasUseCaseTest {

    private val pizzasRepository = mock<PizzasRepository>()

    @AfterEach
    fun tearDown() {
        Mockito.reset(pizzasRepository)
    }

    @Test
    fun `should return the same added pizzas as in repository`() {
        val testAddedPizzas = listOf(Pizza("A", 1.1.toBigDecimal(), PizzaSize.Full, true))
        Mockito.`when`(pizzasRepository.getAddedPizzas()).thenReturn(testAddedPizzas)

        val getAddedPizzasUseCase = GetAddedPizzasUseCase(pizzasRepository)

        val actual = getAddedPizzasUseCase()
        val expected = listOf(Pizza("A", 1.1.toBigDecimal(), PizzaSize.Full, true))

        Assertions.assertEquals(expected, actual)
    }

    @Test
    fun `should not return the same added pizzas as in repository`() {
        val testAddedPizzas = listOf(Pizza("A", 1.1.toBigDecimal(), PizzaSize.Full, true))
        Mockito.`when`(pizzasRepository.getAddedPizzas()).thenReturn(testAddedPizzas)

        val getAddedPizzasUseCase = GetAddedPizzasUseCase(pizzasRepository)

        val actual = getAddedPizzasUseCase()
        val expected = listOf(Pizza("B", 1.1.toBigDecimal(), PizzaSize.Full, true))

        Assertions.assertNotEquals(expected, actual)
    }
}