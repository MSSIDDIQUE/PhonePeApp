package com.baymax.phonepeapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.baymax.phonepeapp.R
import com.baymax.phonepeapp.api.movies_api_data.MoviesResponse
import com.baymax.phonepeapp.data.UiState
import com.baymax.phonepeapp.databinding.FragmentHomeBinding
import com.baymax.phonepeapp.ui.adapters.MoviesListAdapter
import com.baymax.phonepeapp.ui.viewmodels.HomeFragmentViewModel
import com.baymax.phonepeapp.ui.viewmodels.ViewModelFactory
import dagger.android.support.DaggerFragment
import javax.inject.Inject

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class HomeFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: HomeFragmentViewModel
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)[HomeFragmentViewModel::class.java]
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewModel.fetchPopularMoviesList()
        }
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.moviesData.observe(viewLifecycleOwner) { uiState ->
            when (uiState) {
                UiState.Empty -> binding.apply {
                    recyclerView.visibility = View.GONE
                }
                is UiState.Error -> TODO()
                is UiState.Loading -> TODO()
                is UiState.Success<*> -> (uiState.data as MoviesResponse).let { data ->
                    binding.apply {
                        recyclerView.visibility = View.VISIBLE
                        recyclerView.apply {
                            layoutManager = LinearLayoutManager(requireContext())
                            adapter = MoviesListAdapter(data.results){
                                findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
                            }
                        }
                    }
                }
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}