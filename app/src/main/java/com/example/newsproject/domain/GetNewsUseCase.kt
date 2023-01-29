package com.example.newsproject.domain

import com.example.newsproject.data.repository.NewsRepository
import com.example.newsproject.presentation.homescreen.Articles
import javax.inject.Inject

class GetNewsUseCase @Inject constructor(
    private val newsRepository: NewsRepository
) {
    suspend operator fun invoke(): Articles? {
        val response = newsRepository.getNews()
        if (response.status == "ok") {
            return response.articles?.toTypedArray()
        } else {
            throw Exception(response.message)
        }
    }

}