package com.example.newsproject.data.repository

import android.content.Context
import com.example.newsproject.R
import com.example.newsproject.data.models.ArticlesResponse
import com.example.newsproject.data.source.remote.NewsRemoteRepo
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsRepositoryImpl @Inject constructor(
    private val newsRemoteRepo: NewsRemoteRepo, @ApplicationContext val context: Context
) : NewsRepository {

    override suspend fun getNews(): ArticlesResponse {
        val weatherDataResponse = newsRemoteRepo.getNews()
        if (weatherDataResponse.body() != null) {
            return weatherDataResponse.body()!!
        } else {
            throw Exception(context.getString(R.string.something_went_wrong))
        }
    }

}