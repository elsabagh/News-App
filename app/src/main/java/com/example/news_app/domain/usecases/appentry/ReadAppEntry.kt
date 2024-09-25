package com.example.news_app.domain.usecases.appentry

import com.example.news_app.domain.manger.LocalUserManger
import kotlinx.coroutines.flow.Flow

class ReadAppEntry(
    private val localUserManger: LocalUserManger

) {
    operator fun invoke(): Flow<Boolean> {
        return localUserManger.readAppEntry()
    }

}