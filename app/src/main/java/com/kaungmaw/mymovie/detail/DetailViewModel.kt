package com.kaungmaw.mymovie.detail

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kaungmaw.mymovie.network.Movie
import com.kaungmaw.mymovie.network.MovieApi
import com.kaungmaw.mymovie.network.MovieDetailProperty
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

const val API_KEY = "b74246c01b5f0ce654971e07abfd72ce"

class DetailViewModel(val app: Application , val movieId: Long): ViewModel() {

    private val job = Job()
    private val scope = CoroutineScope(job + Dispatchers.Main)

    private val _movieKey = MutableLiveData<Long>()
    val movieKey : LiveData<Long>
        get() = _movieKey

    init {
        _movieKey.value = movieId
    }

    //network call for detail
    fun getMovieDetail(key: Long){
        scope.launch {
            val deferred = MovieApi.retrofitService.getMovieDetailAsync(key , API_KEY)
            try {
                val result = deferred.await()
                _detailResponse.value = result
            }catch (e: Exception){
                Log.e("DetailViewModel", "error: ${e.message!!}")
            }
        }
    }

    private val _detailResponse = MutableLiveData<MovieDetailProperty>()
    val detailResponse: LiveData<MovieDetailProperty>
        get() = _detailResponse

}