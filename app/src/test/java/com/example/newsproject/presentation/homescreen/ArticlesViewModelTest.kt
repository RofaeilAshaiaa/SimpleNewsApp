package com.example.newsproject.presentation.homescreen

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.newsproject.createArticles
import com.example.newsproject.data.repository.NewsRepository
import com.example.newsproject.domain.GetNewsUseCase
import com.example.newsproject.domain.SearchArticlesUseCase
import com.example.newsproject.getOrAwaitValue
import kotlinx.coroutines.Dispatchers
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.kotlin.*
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
@Config(sdk = [30])
class ArticlesViewModelTest {
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var newsRepository: NewsRepository
    private lateinit var newsUseCase: GetNewsUseCase
    private lateinit var searchArticlesUseCase: SearchArticlesUseCase
    private lateinit var viewModel: ArticlesViewModel

    @Before
    fun setUp() {
        newsRepository = mock()
        newsUseCase = mock {
            onBlocking { invoke() } doReturn createArticles().toTypedArray()
        }
        searchArticlesUseCase = mock()
        viewModel = ArticlesViewModel(newsUseCase, searchArticlesUseCase, Dispatchers.IO)
    }

    @Test
    fun `performSearchQuery() when user type in search view then searchArticlesUseCase is called`() {
        //arrange
        val expected = createArticles().toTypedArray()
        whenever(searchArticlesUseCase.invoke("Nils Prat", any())) doReturn expected
        //act
        viewModel.performSearchQuery("Nils Prat")
        val result = viewModel.searchResult.getOrAwaitValue()
        //assert
        verify(searchArticlesUseCase, times(1)).invoke(any(), any())
        Assert.assertTrue(result!!.isNotEmpty())
    }
}