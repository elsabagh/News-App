package com.example.news_app.domain.usecases.news

import com.example.news_app.data.local.NewsDao
import com.example.news_app.domain.model.Article
import kotlinx.coroutines.flow.Flow

class SelectArticle(
    private val newsDao: NewsDao
) {
    operator fun invoke(): Flow<List<Article>> {
        return newsDao.getArticles()
    }

}