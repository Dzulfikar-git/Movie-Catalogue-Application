package com.dzulfikar.subs3keloladata.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dzulfikar.subs3keloladata.data.local.entity.MovieEntity
import com.dzulfikar.subs3keloladata.data.local.entity.TvShowEntity

@Database(entities = [MovieEntity::class, TvShowEntity::class], version = 1, exportSchema = false)
abstract class CatalogueDatabase : RoomDatabase() {
    abstract fun catalogueDao() : CatalogueDao

}