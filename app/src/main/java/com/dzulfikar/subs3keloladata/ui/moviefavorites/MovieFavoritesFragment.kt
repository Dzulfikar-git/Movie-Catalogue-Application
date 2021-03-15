package com.dzulfikar.subs3keloladata.ui.moviefavorites

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.dzulfikar.subs3keloladata.data.local.entity.MovieEntity
import com.dzulfikar.subs3keloladata.databinding.FragmentMovieFavoritesBinding
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class MovieFavoritesFragment : Fragment() {

    private var _binding : FragmentMovieFavoritesBinding? = null
    private val binding get() = _binding
    private val movieFavoritesViewModel : MovieFavoritesViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        _binding = FragmentMovieFavoritesBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val movieFavoritesAdapter = MovieFavoritesAdapter(activity, movieFavoritesViewModel)
        movieFavoritesViewModel.getFavoriteMovies().observe(viewLifecycleOwner, {data ->
            if(data != emptyList<PagedList<MovieEntity>>()){
                movieFavoritesAdapter.submitList(data)
                Log.d("MovieFavorites", data.toString())
            } else {
                Toast.makeText(activity, "No Favorite Movies", Toast.LENGTH_LONG ).show()
            }
        })

        binding?.let {
            with(it.rvMovieFavorites){
                layoutManager = LinearLayoutManager(context)
                adapter = movieFavoritesAdapter
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}