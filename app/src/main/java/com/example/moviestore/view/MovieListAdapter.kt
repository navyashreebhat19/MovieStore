package com.example.moviestore

import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

import kotlinx.android.synthetic.main.movie_list_card_item.view.*

internal class MovieListAdapter(private var moviesList: List<Movie>) :
    RecyclerView.Adapter<MovieListAdapter.MyViewHolder>() {
    private lateinit var mClickListener: ItemClickListener
    internal inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var image : ImageView = view.findViewById(R.id.movie_image)
        var title: TextView = view.findViewById(R.id.movie_title)
        var year: TextView = view.findViewById(R.id.movie_release_date)
        var genre: TextView = view.findViewById(R.id.movie_overview)
        var cardImage : CardView = view.findViewById(R.id.movie_cardImage)
    }
    @NonNull
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.movie_list_card_item, parent, false)
        return MyViewHolder(itemView)
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val movie = moviesList[position]

        Picasso.get().load(movie.posterPath)
                .placeholder(R.mipmap.ic_launcher)
               .into(holder.image)
        holder.title.text = movie.originalTitle
        holder.genre.text = movie.overview
        holder.year.text = movie.releaseDate
        holder.cardImage.setOnClickListener {v -> mClickListener.onItemClick(v, movie)}
    }
    override fun getItemCount(): Int {
        return moviesList.size
    }
    interface ItemClickListener{
        fun onItemClick(view: View, movie: Movie)
    }

    fun setItemClickListener(itemClickListener: ItemClickListener) {
        this.mClickListener = itemClickListener
    }
}


