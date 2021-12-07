package com.pawel.worldline_android_technical_test.ui.detailMovie

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pawel.worldline_android_technical_test.R
import com.pawel.worldline_android_technical_test.databinding.DetailMovieFragmentBinding

class DetailMovieFragment : Fragment() {

    companion object {
        private const val MOVIE_ID = "movie_id"
        fun newInstance(movieID: Int) = DetailMovieFragment().apply {
            arguments = Bundle().apply {
                putInt(MOVIE_ID,movieID)
            }
        }
    }

    private lateinit var viewModel: DetailMovieViewModel
    private var binding: DetailMovieFragmentBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.detail_movie_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[DetailMovieViewModel::class.java]
    }

}