package com.example.newsproject.domain

import com.example.newsproject.createArticlesResponse
import com.example.newsproject.data.models.ArticlesResponse
import com.example.newsproject.data.repository.NewsRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.*

class GetNewsUseCaseTest {
    private lateinit var newsRepository: NewsRepository
    private lateinit var newsUseCase: GetNewsUseCase

    @Before
    fun setUp() {
        //arrange for all tests
        newsRepository = mock {
            onBlocking { getNews() } doReturn createArticlesResponse()
        }
        newsUseCase = GetNewsUseCase(newsRepository)
    }

    @Test
    fun `invoke(), then getNews() in repo called once`() {
        runBlocking {
            //act
            newsUseCase.invoke()
            //assert
            verify(newsRepository, times(1)).getNews()
        }
    }

    @Test
    fun `invoke(), then return result`() {
        runBlocking {
            //act
            val result = newsUseCase.invoke()
            //assert
            Assert.assertTrue(result!!.isNotEmpty())
        }
    }

    @Test(expected = Exception::class)
    fun `invoke(), when getNews() in repo throws exception then throws exception`() {

        runBlocking {
            // arrange
            whenever(newsRepository.getNews()).thenThrow(Exception())
            //act
            val result = newsUseCase.invoke()
            //assert
            //should throw exception
        }
    }

    @Test(expected = Exception::class)
    fun `invoke(), when status in articleResponse is not ok, then throws exception`() {
        runBlocking {
            // arrange
            whenever(newsRepository.getNews()).doReturn(
                ArticlesResponse(status = "error", message = "error loading")
            )
            //act
            val result = newsUseCase.invoke()
            //assert
            //should throw exception
        }
    }

    @Test
    fun `invoke(), when status in articleResponse is not ok, then throws exception with exception message`() {
        runBlocking {
            // arrange
            val expected = ArticlesResponse(status = "error", message = "error loading")
            whenever(newsRepository.getNews()).doReturn(expected)
            //act
            try {
                newsUseCase.invoke()
            } catch (ex: Exception) {
                //assert
                assertEquals(expected.message, ex.message)
            }
        }
    }

}