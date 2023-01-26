package com.harutyun.pizzadelivery.presentation.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.harutyun.domain.model.Pizza
import com.harutyun.pizzadelivery.databinding.FragmentMenuBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MenuFragment : Fragment() {

    private var _binding: FragmentMenuBinding? = null
    private val binding get() = _binding!!

    private val menuViewModel: MenuViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMenuBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupPizzasRecyclerView()

        addViewListeners()

        observeState()
    }

    private fun observeState() {
        lifecycleScope.launchWhenStarted {
            menuViewModel.uiState.collect { uiState ->
                handleUiState(uiState)
            }
        }
    }

    private fun handleUiState(uiState: MenuUiState) = with(binding) {
        when (uiState) {
            is MenuUiState.Loading -> {
                srlPizzasMenu.isRefreshing = true
            }
            is MenuUiState.Error -> {
                srlPizzasMenu.isRefreshing = false
                tvErrorMenu.text = uiState.errorMessage
                tvErrorMenu.visibility = View.VISIBLE
                rvPizzasMenu.visibility = View.GONE
            }
            is MenuUiState.Success -> {
                srlPizzasMenu.isRefreshing = false
                tvNoDataMenu.visibility = View.GONE
                tvErrorMenu.visibility = View.GONE
                rvPizzasMenu.visibility = View.VISIBLE

                if (uiState.snackBarMessage != null) {
                    Snackbar.make(binding.root, getString(uiState.snackBarMessage), Snackbar.LENGTH_SHORT).show()
                    menuViewModel.snackBarMessageIsShown()
                }

                if (uiState.isConfirmButtonVisible) fabConfirmMenu.show() else fabConfirmMenu.hide()
                (rvPizzasMenu.adapter as PizzasAdapter).submitList(uiState.pizzas)
            }
            MenuUiState.NoData -> {
                srlPizzasMenu.isRefreshing = false
                tvNoDataMenu.visibility = View.VISIBLE
                rvPizzasMenu.visibility = View.GONE
            }
        }
    }

    private fun addViewListeners() = with(binding) {
        srlPizzasMenu.setOnRefreshListener {
            menuViewModel.getPizzas()
        }
    }

    private fun setupPizzasRecyclerView() {
        val pizzaAdapter = PizzasAdapter(object : PizzasAdapter.OnItemClickListener {
            override fun onAddButtonClicked(pizza: Pizza) {
                menuViewModel.addPizza(pizza)
            }

            override fun onRemoveButtonClicked(pizza: Pizza) {
                menuViewModel.removePizza(pizza)
            }
        })
        binding.rvPizzasMenu.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = pizzaAdapter
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}