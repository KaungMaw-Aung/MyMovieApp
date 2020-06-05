package com.kaungmaw.mymovie.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer

import com.kaungmaw.mymovie.R
import com.kaungmaw.mymovie.databinding.FragmentMovieDetailBinding
import com.kaungmaw.mymovie.databinding.FragmentMovieOverviewBinding

/**
 * A simple [Fragment] subclass.
 */
class MovieDetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentMovieDetailBinding.inflate(inflater,container,false)

        val argument = MovieDetailFragmentArgs.fromBundle(requireArguments()).movieId
        val application = requireNotNull(activity).application
        val viewModel by viewModels<DetailViewModel> { DetailViewModelFactory(application,argument) }

        viewModel.movieKey.observe(viewLifecycleOwner , Observer {
            it?.let {
                viewModel.getMovieDetail(it)
            }
        })

        viewModel.detailResponse.observe(viewLifecycleOwner , Observer {
            binding.viewModel = viewModel
        })

        return binding.root
    }

}
