package com.example.moviestore

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_favorite_movie.*
import kotlinx.coroutines.*


class FavoriteMovieActivity : AppCompatActivity(){
    private lateinit var adapter: FavoriteMovieListAdapter
    private lateinit var dbHelper: DBHelper
    private var myJob: Job? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite_movie)
        dbHelper = DBHelperImpl(FavoriteMovieDB.getDB(applicationContext))
        toolbar_title.text = "Movie Favourites!!"
        setRecyclerView()
        back_button.setOnClickListener {
            onBackPressed()
        }
    }

    override fun onResume() {
        super.onResume()
        getAllMovies()
    }

    private fun setRecyclerView() {
        val linearLayoutManager =
            LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        adapter = FavoriteMovieListAdapter()
        movie_recycler_view.layoutManager = linearLayoutManager
        movie_recycler_view.adapter = adapter


    }

    private fun getAllMovies() {
        myJob = CoroutineScope(Dispatchers.IO).launch {
                    val result = dbHelper.getFavoriteMovies()

                    withContext(Dispatchers.Main) {
                        if (result.isEmpty()){
                            no_data_view.visibility = View.VISIBLE
                        }
                        adapter.setData(result)
                        loading_view.visibility = View.GONE
                    }
                }
    }

    override fun onDestroy() {
        myJob?.cancel()
        super.onDestroy()
    }
}