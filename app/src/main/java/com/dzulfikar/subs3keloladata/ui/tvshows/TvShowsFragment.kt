package com.dzulfikar.subs3keloladata.ui.tvshows

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.dzulfikar.subs3keloladata.databinding.FragmentTvShowsBinding
import com.dzulfikar.subs3keloladata.vo.Status
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class TvShowsFragment : Fragment() {

    private var _binding: FragmentTvShowsBinding? = null
    private val binding get() = _binding!!
    private val tvShowsViewModel : TvShowsViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment

        _binding = FragmentTvShowsBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tvShowAdapter = TvShowsAdapter(context, tvShowsViewModel)

        tvShowsViewModel.getTvShows().observe(viewLifecycleOwner, {data ->
            when(data.status) {
                Status.SUCCESS -> {
                    binding.tvShowProgressbar.visibility = View.GONE
                    binding.rvTvShows.visibility = View.VISIBLE
                    tvShowAdapter.setData(data.data)
                    tvShowAdapter.notifyDataSetChanged()
                }
                Status.LOADING -> {
                    binding.tvShowProgressbar.visibility = View.VISIBLE
                    binding.rvTvShows.visibility = View.GONE
                }
                Status.ERROR -> {
                    binding.tvShowProgressbar.visibility = View.VISIBLE
                    binding.rvTvShows.visibility = View.GONE
                    Toast.makeText(activity, data.message.toString(), Toast.LENGTH_LONG).show()
                }
            }
        })

        with(binding.rvTvShows){
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = tvShowAdapter
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}