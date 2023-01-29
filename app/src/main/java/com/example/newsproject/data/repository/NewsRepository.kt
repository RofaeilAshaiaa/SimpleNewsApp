package com.example.newsproject.data.repository

import com.example.newsproject.data.models.ArticlesResponse

interface NewsRepository {

    suspend fun getNews(): ArticlesResponse

}