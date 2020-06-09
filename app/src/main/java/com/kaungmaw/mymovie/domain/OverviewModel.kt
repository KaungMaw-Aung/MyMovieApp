package com.kaungmaw.mymovie.domain

data class OverviewModel (
    val id : Long,
    val title : String,
    val posterPath : String?,
    val voteAverage : Double
)