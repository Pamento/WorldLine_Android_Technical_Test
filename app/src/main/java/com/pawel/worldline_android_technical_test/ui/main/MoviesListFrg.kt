package com.pawel.worldline_android_technical_test.ui.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter : MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MainFragmentBinding.inflate(inflater, container, false)
        viewModel = ViewModelProviders.of(this,
            ViewModelFactory(ApiHelperImpl(createNetworkService()))
        )[MoviesViewModel::class.java]
        adapter = MovieAdapter(requireContext())
        setRecyclerView()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setMovieObserver()
    }

    private fun setRecyclerView() {
        recyclerView = binding.listMoviesRV
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.apply {
            addItemDecoration(
                DividerItemDecoration(
                    binding.listMoviesRV.context,
                    DividerItemDecoration.VERTICAL
                )
            )
        }
        recyclerView.adapter = adapter
    }

    private fun setMovieObserver() {
        viewModel.movies.observe(viewLifecycleOwner,{
            it?.let {
                Log.i("TAG", "setMovieObserver: it.size:: ${it.size}")
                adapter.setItems(it)
            }
        })
    }
}