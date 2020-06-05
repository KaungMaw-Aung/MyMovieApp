package com.kaungmaw.mymovie.overview

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.kaungmaw.mymovie.R
import com.kaungmaw.mymovie.databinding.FragmentMovieOverviewBinding
import com.kaungmaw.mymovie.network.MovieFilter

/**
 * A simple [Fragment] subclass.
 */
class MovieOverviewFragment : Fragment() {

    private val viewModel by viewModels<OverviewViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentMovieOverviewBinding.inflate(inflater, container, false)

        binding.rvMovie.adapter = OverviewAdapter(OverviewAdapter.OnClickListener {
            viewModel.doNavigationDetail(it)
        })

        viewModel.navigateDetail.observe(viewLifecycleOwner, Observer {
            it?.let {
                this.findNavController()
                    .navigate(MovieOverviewFragmentDirections.actionMovieOverviewFragmentToMovieDetailFragment(it))
                viewModel.doneDoNavigationDetail()
            }
        })

        viewModel.movieList.observe(viewLifecycleOwner, Observer {
            binding.viewModel = viewModel
        })

        viewModel.category.observe(viewLifecycleOwner , Observer {
            binding.tvCategory.text = it.capitalize()
        })

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        viewModel.getFilter(
            when (item.itemId) {
                R.id.mi_now_playing -> MovieFilter.NOW_PLAYING
                R.id.mi_top_rated -> MovieFilter.TOP_RATED
                else -> MovieFilter.UPCOMING
            }
        )
        return true
    }

}
