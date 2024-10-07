package com.example.news_app.presentation.home

data class HomeState(
    val newsTicker: String = "",
    val isLoading: Boolean = false,
)
