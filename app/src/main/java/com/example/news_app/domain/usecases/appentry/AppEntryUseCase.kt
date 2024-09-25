package com.example.news_app.domain.usecases.appentry

data class AppEntryUseCase(
    val readAppEntry: ReadAppEntry,
    val saveAppEntry: SaveAppEntry
)