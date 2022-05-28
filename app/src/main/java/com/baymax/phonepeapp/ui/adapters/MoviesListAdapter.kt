package com.baymax.phonepeapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.baymax.phonepeapp.R
import com.baymax.phonepeapp.api.movies_api_data.Constants
import com.baymax.phonepeapp.api.movies_api_data.Result
import com.baymax.phonepeapp.databinding.ItemViewMoivieBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

class MoviesListAdapter(
    private val data: List<Result>,
    private val onClick: () -> Unit
) : RecyclerView.Adapter<MoviesListAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val movieRowItemBinding: ItemViewMoivieBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_view_moivie,
            parent,
            false
        )
        return MovieViewHolder(movieRowItemBinding)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val weatherData = data[position]
        holder.bindData(weatherData, onClick)
    }

    class MovieViewHolder(private val binding: ItemViewMoivieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(data: Result, onClick: () -> Unit) {
            loadImageIntoView(data.backdropPath, binding.ivMovie)
            binding.tvMovieTitle.text = data.title
            binding.tvMovieDescription.text = data.overview
            binding.root.setOnClickListener { onClick() }
        }

        private fun loadImageIntoView(backdropPath: String, ivMovie: AppCompatImageView) {
            Glide.with(itemView.context)
                .load(Constants.MOVIES_API_BASE_URL + backdropPath)
                .fitCenter()
                .centerCrop()
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(ivMovie)
        }
    }
}