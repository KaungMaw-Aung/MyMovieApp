package com.kaungmaw.mymovie.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

private const val BASE_URL = "https://api.themoviedb.org/3/"

enum class MovieFilter(val value: String){
    NOW_PLAYING("now_playing") ,
    TOP_RATED("top_rated"),
    UPCOMING("upcoming")
}

val moshi = Moshi.Builder().build()

val retrofit =
    Retrofit.Builder().addCallAdapterFactory(CoroutineCallAdapterFactory()).addConverterFactory(
        MoshiConverterFactory.create(moshi)
    ).baseUrl(BASE_URL).build()

interface MovieApiService {

    @GET("movie/{category}")
    fun getMovieListAsync(@Path("category") categoryType: String, @Query(value = "api_key") apiKey: String): Deferred<MovieOverviewProperty>

    @GET("movie/{movie_id}")
    fun getMovieDetailAsync(@Path("movie_id")movieId: Long, @Query(value = "api_key") apiKey: String):Deferred<MovieDetailProperty>

}

object MovieApi{
    val retrofitService: MovieApiService by lazy {
        retrofit.create(MovieApiService::class.java)
    }
}