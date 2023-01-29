package com.example.newsproject.data.models

import com.google.gson.annotations.SerializedName

data class ArticlesResponse(
    @SerializedName("status") var status: String? = null,
    @SerializedName("totalResults") var totalResults: Int? = null,
    @SerializedName("articles") var articles: ArrayList<Article>? = null,
    @SerializedName("message") var message: String? = null,
)