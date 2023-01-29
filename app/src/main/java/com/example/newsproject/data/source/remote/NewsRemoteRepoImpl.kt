package com.example.newsproject.data.source.remote

import com.example.newsproject.data.models.ArticlesResponse
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsRemoteRepoImpl @Inject constructor(
    private val newsRemoteService: NewsRemoteService,
) : NewsRemoteRepo {
    override suspend fun getNews(): Response<ArticlesResponse> {
        return newsRemoteService.getNews()
    }

}