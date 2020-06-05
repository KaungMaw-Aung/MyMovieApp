package com.kaungmaw.mymovie.overview

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kaungmaw.mymovie.network.Movie
import com.kaungmaw.mymovie.network.MovieApi
import com.kaungmaw.mymovie.network.MovieFilter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

const val API_KEY = "b74246c01b5f0ce654971e07abfd72ce"
class OverviewViewModel: ViewModel() {

    private val viewModelJob = Job()
    private val scope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private val _category = MutableLiveData<String>()
    val category : LiveData<String>
        get() = _category

    init {
        getMovies(MovieFilter.NOW_PLAYING)
        _category.value = MovieFilter.NOW_PLAYING.value
    }

    fun getFilter(filter: MovieFilter){
        getMovies(filter)
        _category.value = filter.value
    }

    private fun getMovies(filter: MovieFilter){
        scope.launch {
            val deferred = MovieApi.retrofitService.getMovieListAsync(filter.value ,API_KEY)
            try {
                val response = deferred.await()
                _movieList.value = response.results
                Log.i("OverviewViewModel" , "response is ${response.results.size}")
            }catch (e: Exception){
                _movieList.value = ArrayList()
                Log.i("OverviewViewModel" , "error ${e.message!!}")
            }
        }
    }

    //list to observe
    private val _movieList = MutableLiveData<List<Movie>>()
    val movieList : LiveData<List<Movie>>
        get() = _movieList

    //navigate to detail
    private val _navigateDetail = MutableLiveData<Long>()
    val navigateDetail : LiveData<Long>
        get() = _navigateDetail

    fun doNavigationDetail(key: Long){
        _navigateDetail.value = key
    }

    fun doneDoNavigationDetail(){
        _navigateDetail.value = null
    }

}