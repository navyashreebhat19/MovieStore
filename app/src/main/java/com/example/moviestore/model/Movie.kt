package com.example.moviestore

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Movie (

    @SerializedName("poster_path") val posterPath: String,
    @SerializedName("overview") val overview: String,
    @SerializedName("original_title") val originalTitle: String,
    @SerializedName("release_date") val releaseDate: String,
    @SerializedName("id") val id: Int

//    @SerializedName("popularity") val popularity : String,
//    @SerializedName("vote_count") val voteCount : Int,
//    @SerializedName("video") val video: Boolean,
//    @SerializedName("poster_path") val posterPath: String,
//    @SerializedName("id") val id: Int,
//    @SerializedName("adult") val adult: Boolean,
//    @SerializedName("backdrop_path") val backdropPath: String,
//    @SerializedName("original_language") val originalLanguage: String,
//
//    @SerializedName("genre_ids") val genreIds: List<Int>,
//    @SerializedName("title") val title: String,
//    @SerializedName("vote_average") val voteAverage: Double,


):Serializable