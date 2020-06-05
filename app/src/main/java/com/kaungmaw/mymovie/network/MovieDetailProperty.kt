package com.kaungmaw.mymovie.network

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieDetailProperty(
    @Json(name = "backdrop_path")val backdropPath: String,
    @Json(name = "genres")val genres: List<GenreDetail>,
    @Json(name = "original_title")val originalTitle: String,
    @Json(name = "overview")val overview: String,
    @Json(name = "poster_path")val posterPath: String,
    @Json(name = "release_date")val releaseDate: String,
    @Json(name = "runtime")val runtime: Int,
    @Json(name = "status")val status: String,
    @Json(name = "vote_average")val voteAverage: Double
)

@JsonClass(generateAdapter = true)
data class GenreDetail(
    @Json(name = "id")val id: Long,
    @Json(name = "name")val name: String
)
