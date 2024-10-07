package com.example.news_app.di

import android.app.Application
import com.example.news_app.data.manger.LocalUserMangerImpl
import com.example.news_app.data.remote.NewsApi
import com.example.news_app.data.remote.repository.NewsRepositoryImpl
import com.example.news_app.domain.manger.LocalUserManger
import com.example.news_app.domain.repository.NewsRepository
import com.example.news_app.domain.usecases.appentry.AppEntryUseCase
import com.example.news_app.domain.usecases.appentry.ReadAppEntry
import com.example.news_app.domain.usecases.appentry.SaveAppEntry
import com.example.news_app.domain.usecases.news.GetNews
import com.example.news_app.domain.usecases.news.NewsUseCases
import com.example.news_app.domain.usecases.news.SearchNews
import com.example.news_app.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
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

    @Provides
    @Singleton
    fun provideNewsApi(): NewsApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideNewsRepository(
        newsApi: NewsApi
    ): NewsRepository = NewsRepositoryImpl(newsApi)

    @Provides
    @Singleton
    fun provideNewsUseCase(
        newsRepository: NewsRepository
    ): NewsUseCases {
        return NewsUseCases(
            getNews = GetNews(newsRepository),
            searchNews = SearchNews(newsRepository)
        )
    }
}