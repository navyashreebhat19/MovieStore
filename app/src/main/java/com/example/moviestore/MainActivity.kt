package com.example.moviestore

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private val movieList = ArrayList<Movie>()
    private lateinit var moviesAdapter: MovieListAdapter
    private lateinit var dbHelper: DBHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toolbar_title.text = "Movie Store"
        dbHelper = DBHelperImpl(FavoriteMovieDB.getDB(applicationContext))


        favorite_button.setOnClickListener {
            val intent = Intent(applicationContext, FavoriteMovieActivity::class.java)
            startActivity(intent)
        }




        val recyclerView: RecyclerView = findViewById(R.id.movie_recycler_view)
        moviesAdapter = MovieListAdapter(movieList)
        val layoutManager = LinearLayoutManager(applicationContext)
        recyclerView.layoutManager = layoutManager
        gridview.setOnClickListener{
            recyclerView.layoutManager =  GridLayoutManager(applicationContext, 2)
            listview.visibility = View.VISIBLE
            gridview.visibility = View.GONE
        }

        listview.setOnClickListener {
            recyclerView.layoutManager = LinearLayoutManager(applicationContext)
            gridview.visibility = View.VISIBLE
            listview.visibility = View.GONE
        }

        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.adapter = moviesAdapter

        prepareMovieData()


    }

    private fun prepareMovieData(){
        var movie = Movie("https://m.media-amazon.com/images/M/MV5BZmYzMzU4NjctNDI0Mi00MGExLWI3ZDQtYzQzYThmYzc2ZmNjXkEyXkFqcGdeQXVyMTEyMjM2NDc2._V1_.jpg", " happy with his overall life, Steven Schoichet (Adrien Brody) decides to pursue his dream to be a professional ventriloquist. His family is neither supportive nor particularly critical. Luckily, Steven finds work through an attractive unemployment counselor (Vera Farmiga). While any potential romance is complicated by Steven's personal baggage -- and friends like Fangora (Milla Jovovich), who can't help giving bad relationship advice -- things start to change, thanks to his devotion to his art.", "Gozilla vs king","01-10-2021",1)
        movieList.add(movie);

        movie = Movie("https://www.denofgeek.com/wp-content/uploads/2019/11/marvel-mcu-movies-on-disney-plus.jpeg?resize=768%2C432", "I really love this Most Anticipated and Must-play AAA Superhero blockbuster team-up video-game because you'll never know what in store and the action-packed gameplay cutscenes are soooooooo very incredibly jaw-dropping and mind-blowing.", "Marvel","20-09-2020",2)
        movieList.add(movie)

        movie = Movie("https://lumiere-a.akamaihd.net/v1/images/p_luca_date_20945_35046d50.jpeg", "The animation is top-notch and visually appealing. The characters are adequately cartoonish and also highly relatable and humane", "Luca","11-01-2022",3)
        movieList.add(movie)


        movie = Movie("https://i.pinimg.com/originals/ea/e7/b7/eae7b762f801feab1f7dde0309f8519e.jpg", "The animation is top-notch and visually appealing. The characters are adequately cartoonish and also highly relatable and humane", "Avengers - Infinity War","11-01-2020",4)
        movieList.add(movie)


        movie = Movie("https://i.pinimg.com/originals/81/e3/fb/81e3fbba5f7a15034a8b4cfbda6c3079.jpg", "The animation is top-notch and visually appealing. The characters are adequately cartoonish and also highly relatable and humane", "Mickey Mouse","11-01-1996",5)
        movieList.add(movie)



        moviesAdapter.notifyDataSetChanged();
        moviesAdapter.setItemClickListener(object : MovieListAdapter.ItemClickListener {
            override fun onItemClick(view: View, movie: Movie) {
                val intent = Intent(applicationContext, MovieDetailActivity::class.java)
                intent.putExtra(Constant.MOVIE_IMAGE, movie.posterPath);
                intent.putExtra(Constant.MOVIE_TITLE, movie.originalTitle);
                intent.putExtra(Constant.MOVIE_OVERVIEW,movie.overview);
                intent.putExtra(Constant.MOVIE_RELEASEDATE,movie.releaseDate);
                intent.putExtra(Constant.MOVIE_KEY,movie.id);
                intent.putExtra(Constant.MOVIE,movie)
                startActivity(intent)
            }
        })
    }




}





