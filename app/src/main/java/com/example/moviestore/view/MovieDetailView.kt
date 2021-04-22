package com.example.moviestore



interface MovieDetailView {
    fun setData(movieDetail: Movie)
    fun showErrorToast(msg: String)

}