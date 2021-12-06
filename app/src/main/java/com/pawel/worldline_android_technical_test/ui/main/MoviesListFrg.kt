package com.pawel.worldline_android_technical_test.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.pawel.worldline_android_technical_test.data.api.ApiHelperImpl
import com.pawel.worldline_android_technical_test.data.api.createNetworkService
import com.pawel.worldline_android_technical_test.databinding.MainFragmentBinding
import com.pawel.worldline_android_technical_test.di.ViewModelFactory

class MoviesListFrg : Fragment() {

    companion object {
        fun newInstance() = MoviesListFrg()
    }

    private lateinit var viewModel: MoviesViewModel
    private lateinit var binding : MainFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this,ViewModelFactory(ApiHelperImpl(createNetworkService())))[MoviesViewModel::class.java]
    }
}