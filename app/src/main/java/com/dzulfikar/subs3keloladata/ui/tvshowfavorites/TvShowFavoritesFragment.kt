package com.dzulfikar.subs3keloladata.ui.tvshowfavorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.dzulfikar.subs3keloladata.data.local.entity.TvShowEntity
import com.dzulfikar.subs3keloladata.databinding.FragmentTvShowFavoritesBinding
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class TvShowFavoritesFragment : Fragment() {

    private var _binding : FragmentTvShowFavoritesBinding? = null
    private val binding get() = _binding!!
    private val tvShowFavoritesViewModel : TvShowFavoritesViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentTvShowFavoritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val tvShowFavoritesAdapter = TvShowFavoritesAdapter(requireActivity(), tvShowFavoritesViewModel)
        tvShowFavoritesViewModel.getFavoriteTvShows().observe(viewLifecycleOwner, {data ->
            if (data != emptyList<PagedList<TvShowEntity>>()){
                tvShowFavoritesAdapter.submitList(data)
            } else {
                Toast.makeText(activity, "No Favorite Tv Shows", Toast.LENGTH_LONG).show()
            }
        })

        with(binding.rvTvShowFavorites){
            layoutManager = LinearLayoutManager(context)
            adapter = tvShowFavoritesAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}