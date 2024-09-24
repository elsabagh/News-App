package com.example.news_app.domain.usecases.appentry

data class AppEntryUseCase(
    val readAppEntry: RadAppEntry,
    val saveAppEntry: SaveAppEntry
)