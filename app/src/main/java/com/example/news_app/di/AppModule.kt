package com.example.news_app.di

import android.app.Application
import com.example.news_app.data.manger.LocalUserMangerImpl
import com.example.news_app.domain.manger.LocalUserManger
import com.example.news_app.domain.usecases.appentry.AppEntryUseCase
import com.example.news_app.domain.usecases.appentry.ReadAppEntry
import com.example.news_app.domain.usecases.appentry.SaveAppEntry
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLocalUserManger(
        application: Application
    ): LocalUserManger {
        return LocalUserMangerImpl(application)
    }

    @Provides
    @Singleton
    fun provideAppEntryUseCase(
        localUserManger: LocalUserManger
    ) = AppEntryUseCase(
        readAppEntry = ReadAppEntry(localUserManger),
        saveAppEntry = SaveAppEntry(localUserManger)
    )
}