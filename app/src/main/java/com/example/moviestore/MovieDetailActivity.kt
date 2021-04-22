package com.example.moviestore

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso

import kotlinx.android.synthetic.main.activity_movie_detail.*
import kotlinx.coroutines.*
import java.io.Serializable

class MovieDetailActivity : AppCompatActivity(){

    private lateinit var dbHelper: DBHelper
    private var myJob: Job? = null
    private var isLiked = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)
        dbHelper = DBHelperImpl(FavoriteMovieDB.getDB(applicationContext))
        toolbar_title.text = "Movie Description"
        val movieImage = intent.getStringExtra(Constant.MOVIE_IMAGE)
        val movieTilte = intent.getStringExtra(Constant.MOVIE_TITLE)
        val movieOverview = intent.getStringExtra(Constant.MOVIE_OVERVIEW)
        val movieReleaseDate = intent.getStringExtra(Constant.MOVIE_RELEASEDATE)
        val movieid = intent.getIntExtra(Constant.MOVIE_KEY, 0)
        val movieWhole = intent.getSerializableExtra(Constant.MOVIE)
        if(movieid != null){
            setDescrptionMovie(movieImage,movieTilte,movieOverview,movieReleaseDate,movieid,movieWhole);
        }


//        if(movieID != 0) {
//            presenter.getMovieDetail(movieID)
//        }
        back_button.setOnClickListener {
            onBackPressed()
        }


    }

    private fun setDescrptionMovie(
        movieImage: String?,
        movieTilte: String?,
        movieOverview: String,
        movieReleaseDate: String?,
        movieid: Int,
        movieWhole: Serializable

    ) {
        val picasso = Picasso.get()

        picasso.load(movieImage)
            .placeholder(R.mipmap.ic_launcher)
            .into(movie_backdrop)
        picasso.load( movieImage)
            .placeholder(R.mipmap.ic_launcher)
            .into(movie_poster)


        movie_title.text = movieTilte
        movie_release_date.text = movieReleaseDate
        movie_overview.text = movieOverview
       // movie_rating.rating = (movieDetail.voteAverage / 2).toFloat()
        like_button.visibility = View.VISIBLE
        like_button.setOnClickListener {
            if (isLiked){
                deleteMovie(movieid)
            } else {
                insertMovie(movieWhole)
            }
        }


    }

    private fun insertMovie(movieDetail: Serializable) {
        myJob = CoroutineScope(Dispatchers.IO).launch {
            dbHelper.insertMovie(movieDetail as Movie)
            withContext(Dispatchers.Main){
                isLiked = true
                like_button.setImageResource(R.drawable.icn_like_red_16px)
            }
        }
    }


//    override fun showErrorToast(msg: String) {
//        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
//    }

    private fun setLikeButton(id: Int){
        myJob = CoroutineScope(Dispatchers.IO).launch {
                    val result = dbHelper.isFavoriteMovie(id)
                    withContext(Dispatchers.Main) {
                        like_button.visibility = View.VISIBLE
                        if (result){
                            isLiked = true
                            like_button.setImageResource(R.drawable.icn_like_red_16px)
                        } else {
                            isLiked = false
                            like_button.setImageResource(R.drawable.icn_like_gray_16px)
                        }
                    }
                }
    }

    private fun deleteMovie(id: Int) {
        myJob = CoroutineScope(Dispatchers.IO).launch {
                    dbHelper.deleteMovie(id)
                    withContext(Dispatchers.Main){
                        isLiked = false
                        like_button.setImageResource(R.drawable.icn_like_gray_16px)
                    }
                }
    }

    private fun insertMovie(movieDetail: Movie) {

        myJob = CoroutineScope(Dispatchers.IO).launch {
                    dbHelper.insertMovie(movieDetail)
                    withContext(Dispatchers.Main){
                        isLiked = true
                        like_button.setImageResource(R.drawable.icn_like_red_16px)
                    }
                }
    }

    override fun onDestroy() {
        myJob?.cancel()
        super.onDestroy()
    }





}


