package com.kaungmaw.mymovie.overview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kaungmaw.mymovie.databinding.MovieItemGridBinding
import com.kaungmaw.mymovie.network.Movie

class OverviewAdapter(private val onClickListener: OnClickListener): ListAdapter<Movie , OverviewAdapter.OverviewViewHolder>(DiffCallback()) {

    companion object{
        class DiffCallback: DiffUtil.ItemCallback<Movie>(){
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OverviewViewHolder {
        return OverviewViewHolder(MovieItemGridBinding.inflate(LayoutInflater.from(parent.context), parent , false))
    }

    override fun onBindViewHolder(holder: OverviewViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(item)
        }
    }

    class OverviewViewHolder(private val binding: MovieItemGridBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(item: Movie?) {
            binding.movieForGrid = item
            binding.tvVote.text = item?.voteAverage.toString()
            binding.executePendingBindings()
        }

    }

    class OnClickListener(val clickListener: (movieId: Long)-> Unit){
        fun onClick(movie: Movie){
            clickListener(movie.id)
        }
    }

}