package com.dzulfikar.subs3keloladata.ui.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.dzulfikar.subs3keloladata.databinding.FragmentMovieBinding
import com.dzulfikar.subs3keloladata.vo.Status
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class MovieFragment : Fragment() {

    private var _binding: FragmentMovieBinding? = null
    private val binding get() = _binding!!
    private val movieViewModel : MovieViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentMovieBinding.inflate(inflater, container, false)


        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val movieFragmentAdapter = MovieFragmentAdapter(context, movieViewModel)
        movieViewModel.getMovies().observe(viewLifecycleOwner, {data ->
            when(data.status){
                Status.SUCCESS -> {
                    binding.movieProgressBar.visibility = View.GONE
                    binding.rvMovie.visibility = View.VISIBLE
                    movieFragmentAdapter.setData(data.data)
                    movieFragmentAdapter.notifyDataSetChanged()
                }
                Status.LOADING -> {
                    binding.movieProgressBar.visibility = View.VISIBLE
                    binding.rvMovie.visibility = View.GONE
                }
                Status.ERROR -> {
                    binding.movieProgressBar.visibility = View.VISIBLE
                    binding.rvMovie.visibility = View.GONE
                    Toast.makeText(activity, data.message.toString(), Toast.LENGTH_LONG).show()
                }
            }
        })

        with(binding.rvMovie){
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = movieFragmentAdapter
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}