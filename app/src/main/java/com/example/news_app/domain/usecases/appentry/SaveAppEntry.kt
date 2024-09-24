package com.example.news_app.domain.usecases.appentry

import com.example.news_app.domain.manger.LocalUserManger

class SaveAppEntry(
    private val localUserManger: LocalUserManger

) {
    suspend operator fun invoke() {
        localUserManger.saveAppEntry()
    }
}