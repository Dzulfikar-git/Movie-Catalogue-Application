package com.dzulfikar.subs3keloladata.injection

import android.app.Application
import android.util.Log
import androidx.room.Room
import com.dzulfikar.subs3keloladata.data.CatalogueRepository
import com.dzulfikar.subs3keloladata.data.local.LocalDataSource
import com.dzulfikar.subs3keloladata.data.local.room.CatalogueDatabase
import com.dzulfikar.subs3keloladata.data.remote.RemoteDataSource
import com.dzulfikar.subs3keloladata.ui.detail.DetailViewModel
import com.dzulfikar.subs3keloladata.ui.movie.MovieViewModel
import com.dzulfikar.subs3keloladata.ui.moviefavorites.MovieFavoritesViewModel
import com.dzulfikar.subs3keloladata.ui.tvshowfavorites.TvShowFavoritesViewModel
import com.dzulfikar.subs3keloladata.ui.tvshows.TvShowsViewModel
import com.dzulfikar.subs3keloladata.utils.AppExecutors
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()

        val database = module {
            single{
                Room.databaseBuilder(androidApplication(), CatalogueDatabase::class.java,
                        "catalogue-db")
                        .fallbackToDestructiveMigration()
                        .build()
            }

            single { get<CatalogueDatabase>().catalogueDao() }
            Log.d("MyApp", "database loaded")
        }

        val dataSourceModule = module {
            single {
                RemoteDataSource()
            }
            single{
                LocalDataSource(get())
            }
            single {
                AppExecutors()
            }
            Log.d("MyApp", "Data Source Injected")
        }

        val repositoryModule = module {
            factory {
                CatalogueRepository(get(), get(), get())
            }
            Log.d("MyApp","Repo Injected")
        }

        val viewModelModule = module {
            viewModel {
                MovieViewModel(get())
            }
            viewModel{
                DetailViewModel(get())
            }
            viewModel {
                TvShowsViewModel(get())
            }
            viewModel {
                MovieFavoritesViewModel(get())
            }
            viewModel {
                TvShowFavoritesViewModel(get())
            }
            Log.d("MyApp","ViewModel Injected")
        }

        startKoin {
            androidLogger()
            androidContext(this@MyApp)
            modules(dataSourceModule, repositoryModule, viewModelModule, database)
            Log.d("MyApp", "Koin Started")
        }
    }
}