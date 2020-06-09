package com.kaungmaw.mymovie

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.kaungmaw.mymovie.network.GenreDetail
import com.kaungmaw.mymovie.network.Movie
import com.kaungmaw.mymovie.overview.OverviewAdapter

@BindingAdapter("bindList")
fun bindAdapterWithList(recyclerView: RecyclerView, movieList: List<Movie>?) {
    movieList?.let {
        val adapter = recyclerView.adapter as OverviewAdapter
        adapter.submitList(it)
    }
}

@BindingAdapter("loadImage")
fun loadUrl(imageView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val url = "https://image.tmdb.org/t/p/w500/$it"
        Glide.with(imageView.context).load(url)
            .apply(RequestOptions.placeholderOf(R.drawable.loading_animation).error(R.drawable.ic_broken_image))
            .into(imageView)
    }
}

@BindingAdapter("bindText")
fun loadText(textView: TextView, text: String?) {
    text?.let {
        textView.text = it
    }
}

@BindingAdapter("bindGenres")
fun loadGenre(textView: TextView, genres: List<GenreDetail>?) {
    genres?.let {
        val resultGenre = mutableListOf<String>()
        for (each in it) {
            resultGenre.add(each.name)
        }
        val builder = StringBuilder()
        var count = 1
        for (each in resultGenre) {
            builder.append(each)
            if (resultGenre.size > count) {
                builder.append(",")
                count++
            }
        }
        textView.text = builder.toString()
    }

}

@BindingAdapter("formatDuration")
fun bindDuration(textView: TextView , duration: Int?){
    duration?.let {
        val builder = StringBuilder()
        var hour = 0
        var minute = 0
        if (it >= 60){
            hour = it/60
            builder.append(hour.toString())
            builder.append("hr ")
            if (it % 60 != 0){
                minute = it%60
                builder.append(minute.toString())
                builder.append("min")
            }else{}
        } else{
            minute = it
            builder.append(minute.toString())
            builder.append("min")
        }
        textView.text = builder.toString()
    }
}

@BindingAdapter("bindVote")
fun bindDouble(textView: TextView , vote: Double?){
    vote?.let {
        textView.text = it.toString()
    }
}