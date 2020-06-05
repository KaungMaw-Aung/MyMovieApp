package com.kaungmaw.mymovie

import android.view.animation.Transformation
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.Transformations
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.kaungmaw.mymovie.network.GenreDetail
import com.kaungmaw.mymovie.network.Movie
import com.kaungmaw.mymovie.overview.OverviewAdapter

@BindingAdapter("bindList")
fun bindAdapterWithList(recyclerView: RecyclerView , movieList: List<Movie>?){
    movieList?.let {
        val adapter = recyclerView.adapter as OverviewAdapter
        adapter.submitList(it)
    }
}

@BindingAdapter("loadImage")
fun loadUrl(imageView: ImageView , imgUrl : String?){
    imgUrl?.let {
        val url = "https://image.tmdb.org/t/p/w500/$it"
        Glide.with(imageView.context).load(url).apply(RequestOptions.placeholderOf(R.drawable.loading_animation).error(R.drawable.ic_broken_image)).into(imageView)
    }
}

@BindingAdapter("bindText")
fun loadText(textView: TextView , text: String?){
    text?.let {
        textView.text = it
    }
}

@BindingAdapter("bindGenres")
fun loadGenre(textView: TextView , genres: List<GenreDetail>?){
    genres?.let {
        var resultGenre = ""
        for(each in it){
            resultGenre+="${each.name} "
        }
//        val resultGenre: String = "" + genres.map {
//            "${it.name} "
//        }
        textView.text = resultGenre
    }

}