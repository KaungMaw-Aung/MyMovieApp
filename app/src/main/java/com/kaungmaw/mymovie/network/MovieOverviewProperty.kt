package com.kaungmaw.mymovie.network

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieOverviewProperty(
    @Json(name = "results") val results : List<Movie>
)

@JsonClass(generateAdapter = true)
data class Movie(
    @Json(name = "id") val id : Long,
    @Json(name = "title") val title : String,
    @Json(name = "poster_path") val posterPath : String?,
    @Json(name = "vote_average") val voteAverage : Double
)