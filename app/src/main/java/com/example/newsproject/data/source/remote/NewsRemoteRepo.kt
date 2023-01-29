package com.example.newsproject.data.source.remote

import com.example.newsproject.data.models.ArticlesResponse
import retrofit2.Response

interface NewsRemoteRepo {
    suspend fun getNews(): Response<ArticlesResponse>
}