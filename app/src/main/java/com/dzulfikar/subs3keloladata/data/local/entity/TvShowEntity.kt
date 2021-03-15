package com.dzulfikar.subs3keloladata.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tvshow")
data class TvShowEntity(
        @ColumnInfo(name = "tvShowId")
        @PrimaryKey
        val id: Int,

        @ColumnInfo(name = "name")
        val name: String,

        @ColumnInfo(name = "posterPath")
        val posterPath: String?,

        @ColumnInfo(name = "firstAirDate")
        val firstAirDate: String,

        @ColumnInfo(name = "voteAverage")
        val voteAverage: Double,

        @Embedded
        val detail: DetailTvEntity?,

        @ColumnInfo(name = "isFavorite")
        val isFavorite: Boolean = false
)
