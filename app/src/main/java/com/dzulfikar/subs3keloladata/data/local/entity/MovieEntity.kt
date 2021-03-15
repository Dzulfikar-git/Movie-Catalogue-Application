package com.dzulfikar.subs3keloladata.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie")
data class MovieEntity(
	@ColumnInfo(name = "movieId")
	@PrimaryKey
	val id: Int,

	@ColumnInfo(name = "title")
	val title: String,

	@ColumnInfo(name = "posterPath")
	val posterPath: String?,

	@ColumnInfo(name = "releaseDate")
	val releaseDate: String,

	@ColumnInfo(name = "voteAverage")
	val voteAverage: Double,

	@Embedded
	val detail: DetailMovieEntity?,

	@ColumnInfo(name = "isFavorite")
	val isFavorite: Boolean = false
)
