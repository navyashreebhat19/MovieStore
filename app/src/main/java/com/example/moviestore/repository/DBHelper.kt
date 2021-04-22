package com.example.moviestore



interface DBHelper {
    suspend fun getFavoriteMovies(): List<FavoriteMovie>
    suspend fun insertMovie(movie: Movie)

    suspend fun deleteMovie(id: Int)
    suspend fun isFavoriteMovie(id: Int): Boolean
}