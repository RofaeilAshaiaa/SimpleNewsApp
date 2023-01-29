package com.example.newsproject.domain

import com.example.newsproject.presentation.homescreen.Articles
import javax.inject.Inject

class SearchArticlesUseCase @Inject constructor() {

    operator fun invoke(query: String, articles: Articles?): Articles {
        if (articles == null) return emptyArray()
        return articles.filter {
            it.title?.lowercase()?.contains(query.lowercase()) ?: false
                    || it.title?.uppercase()?.contains(query.uppercase()) ?: false
                    || it.description?.lowercase()?.contains(query.lowercase()) ?: false
                    || it.description?.uppercase()?.contains(query.uppercase()) ?: false
                    || it.content?.lowercase()?.contains(query.lowercase()) ?: false
                    || it.content?.uppercase()?.contains(query.uppercase()) ?: false
                    || it.author?.lowercase()?.contains(query.lowercase()) ?: false
                    || it.author?.uppercase()?.contains(query.uppercase()) ?: false
                    || it.source?.name?.lowercase()?.contains(query.lowercase()) ?: false
                    || it.source?.name?.uppercase()?.contains(query.uppercase()) ?: false
        }.toTypedArray()
    }
}