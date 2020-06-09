package com.kaungmaw.mymovie.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "overview_table")
data class OverviewEntity (
    @PrimaryKey
    val id : Long,
    val title : String,
    val posterPath : String?,
    val voteAverage : Double
)