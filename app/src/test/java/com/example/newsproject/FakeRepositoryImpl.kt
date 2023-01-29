package com.example.newsproject

import com.example.newsproject.data.models.ArticlesResponse
import com.example.newsproject.data.repository.NewsRepository
import javax.inject.Inject


class FakeRepositoryImpl @Inject constructor() : NewsRepository {

    override suspend fun getNews(): ArticlesResponse {
        return createArticlesResponse()
    }

}