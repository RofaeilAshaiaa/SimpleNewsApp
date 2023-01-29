package com.example.newsproject.data.source.remote

import com.example.newsproject.data.models.ArticlesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsRemoteService {

    companion object {
        const val BASE_URL = "https://newsapi.org/v2/"
    }

    @GET("top-headlines")
    suspend fun getNews(
        @Query("language") Language: String = "en"
    ): Response<ArticlesResponse>

}