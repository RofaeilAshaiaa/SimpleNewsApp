package com.example.newsproject.data.repository

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.newsproject.data.models.ArticlesResponse
import com.example.newsproject.data.source.remote.NewsRemoteRepo
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import retrofit2.Response

class NewsRepositoryImplTest {

    private var context: Context = ApplicationProvider.getApplicationContext()
    lateinit var newsRemoteRepo: NewsRemoteRepo
    lateinit var newsRepository: NewsRepository

    @Before
    fun setUp() {
        val response = mock<Response<ArticlesResponse>>()
        newsRemoteRepo = mock {
            onBlocking { getNews() } doReturn response
        }

        newsRepository = NewsRepositoryImpl(newsRemoteRepo, context)
    }

    @Test
    fun getNews() {
        runBlocking {
            //arrange
            //act
            newsRepository.getNews()
            //assert
            //verify(newsRemoteRepo, times(1)).getNews()
            verify(newsRepository, times(1)).getNews()
        }
    }
}