package com.dzulfikar.subs3keloladata.utils

import com.dzulfikar.subs3keloladata.data.local.entity.DetailMovieEntity
import com.dzulfikar.subs3keloladata.data.local.entity.DetailTvEntity
import com.dzulfikar.subs3keloladata.data.local.entity.MovieEntity
import com.dzulfikar.subs3keloladata.data.local.entity.TvShowEntity
import com.dzulfikar.subs3keloladata.data.remote.entity.*

object DataDummy {

    fun dummyApiMovieDiscover() : List<MovieEntity> {
        val movieResults = ArrayList<MovieEntity>()

        movieResults.add(
            MovieEntity(
                581389,
                "Space Sweepers",
                "/y2Yp7KC2FJSsdlRM5qkkIwQGCqU.jpg",
                "2021-02-05",
                7.4,
                DetailMovieEntity(
                    "When the crew of a space junk collector ship called The Victory discovers a humanoid robot named Dorothy that's known to be a weapon of mass destruction, they get involved in a risky business deal which puts their lives at stake.",
                    "https://www.netflix.com/title/81094067",
                    "Drama, Fantasy, Science Fiction"
                ),
                false
            )
        )

        return movieResults
    }

    fun dummyApiTvShowDiscover() : List<TvShowEntity>{
        val tvResults = ArrayList<TvShowEntity>()

        tvResults.add(
            TvShowEntity(
                85271,
                "WandaVision",
                "/glKDfE6btIRcVB5zrjspRIs4r52.jpg",
                "2021-01-15",
                8.5,
                DetailTvEntity(
                    "Wanda Maximoff and Vision—two super-powered beings living idealized suburban lives—begin to suspect that everything is not as it seems.",
                    "https://www.disneyplus.com/series/wandavision/4SrN28ZjDLwH",
                    "Sci-Fi & Fantasy, Mystery, Drama"
                ),
                false
            )
        )

        return tvResults
    }

    fun dummyApiDetailMovie() : MovieEntity {
        return MovieEntity(
            581389,
            "Space Sweepers",
            "/y2Yp7KC2FJSsdlRM5qkkIwQGCqU.jpg",
            "2021-02-05",
            7.4,
            DetailMovieEntity(
                "When the crew of a space junk collector ship called The Victory discovers a humanoid robot named Dorothy that's known to be a weapon of mass destruction, they get involved in a risky business deal which puts their lives at stake.",
                "https://www.netflix.com/title/81094067",
                "Drama, Fantasy, Science Fiction"
            ),
            false
        )
    }
    
    fun dummyApiDetailTvShow() : TvShowEntity {
        return TvShowEntity(
            85271,
            "WandaVision",
            "/glKDfE6btIRcVB5zrjspRIs4r52.jpg",
            "2021-01-15",
            8.5,
            DetailTvEntity(
                "Wanda Maximoff and Vision—two super-powered beings living idealized suburban lives—begin to suspect that everything is not as it seems.",
                "https://www.disneyplus.com/series/wandavision/4SrN28ZjDLwH",
                "Sci-Fi & Fantasy, Mystery, Drama"
            ),
            false
        )
    }

    fun dummyEmptyDetailMovie() : DetailMovieResponse{
        return DetailMovieResponse("",
                "",
                false,
                "",
                "",
                0,
                emptyList(),
                0.0,
                emptyList(),
                0,
                0,
                0,
                "",
                "",
                0,
                "",
                emptyList(),
                emptyList(),
                "",
                0.0,
                "",
                "",
                false,
                "",
                "")
    }

    fun dummyEmptyDetailTvShow() : DetailTvResponse {
        return DetailTvResponse(
                "",
                0,
                emptyList(),
                "",
                "",
                emptyList(),
                0.0,
                emptyList(),
                0,
                0,
                0,
                "",
                "",
                emptyList(),
                emptyList(),
                emptyList(),
                LastEpisodeToAir("","", "",0,0.0, "", 0,0, "",0),
                "",
                emptyList(),
                emptyList(),
                emptyList(),
                "",
                0.0,
                "",
                "",
                emptyList(),
                NextEpisodeToAir("","2021-02-26","",8,0.0, "",1,2639821,"null",0),
                true,
                "",
                "",
                ""
        )
    }

}