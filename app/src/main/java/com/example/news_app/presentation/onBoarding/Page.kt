package com.example.news_app.presentation.onBoarding

import androidx.annotation.DrawableRes
import com.example.news_app.R

data class Page(
    val title: String,
    val description: String,
    @DrawableRes val image: Int
)

val pages = listOf(
    Page(
        title = "Lorem ipsum is simply dummy",
        description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry",
        image = R.drawable.onboarding1
    ),
    Page(
        title = "Lorem ipsum is simply dummy",
        description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry",
        image = R.drawable.onboarding2
    ),
    Page(
        title = "Lorem ipsum is simply dummy",
        description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry",
        image = R.drawable.onboarding3
    )

)