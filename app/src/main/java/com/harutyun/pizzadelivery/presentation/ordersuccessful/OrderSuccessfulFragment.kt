package com.harutyun.pizzadelivery.presentation.ordersuccessful

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.harutyun.pizzadelivery.databinding.FragmentOrderSuccessfulBinding

class OrderSuccessfulFragment : Fragment() {

    private var _binding: FragmentOrderSuccessfulBinding? = null
    private val binding get() = _binding!!

    private val orderSuccessfulViewModel: OrderSuccessfulViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOrderSuccessfulBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}