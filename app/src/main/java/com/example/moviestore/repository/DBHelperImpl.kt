package com.example.moviestore



class DBHelperImpl(private val favoriteMovieDB: FavoriteMovieDB) : DBHelper {
    private fun convertModelToEntity(movieDetail: Movie): FavoriteMovie {
        return FavoriteMovie(
            movieDetail.posterPath,
            movieDetail.overview,
            movieDetail.originalTitle,
            movieDetail.releaseDate,

                    movieDetail.id
//            movieDetail.popularity.toString(),
//            movieDetail.voteCount.toInt(),
//            movieDetail.video,

//            movieDetail.adult,
//            movieDetail.backdropPath,
//            movieDetail.originalLanguage,
//
//            movieDetail.title,
//            movieDetail.voteAverage,


        )
    }

    override suspend fun getFavoriteMovies(): List<FavoriteMovie> {
        return favoriteMovieDB.favoriteMovieDao().getFavoriteMovies()
    }

    override suspend fun insertMovie(movie: Movie) {
        favoriteMovieDB.favoriteMovieDao().insertMovie(
            convertModelToEntity(movie)
        )
    }

    override suspend fun deleteMovie(id: Int) {
        favoriteMovieDB.favoriteMovieDao().deleteMovieById(id)
    }

    override suspend fun isFavoriteMovie(id: Int): Boolean {
        return favoriteMovieDB.favoriteMovieDao().isFavoriteMovie(id)
    }
}