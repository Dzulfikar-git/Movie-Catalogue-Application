package com.dzulfikar.subs3keloladata.data.local.entity

import androidx.room.ColumnInfo

data class DetailTvEntity(
        @ColumnInfo(name = "overview")
        val overview : String? = null,

        @ColumnInfo(name = "movieUrl")
        val url : String? = null,

        @ColumnInfo(name = "genres")
        val genre : String? = null,

        @ColumnInfo(name = "seasons")
        val seasons: Int? = null
)
