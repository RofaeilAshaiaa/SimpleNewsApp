package com.example.newsproject.domain

import com.example.newsproject.createArticles
import com.example.newsproject.presentation.homescreen.Articles
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class SearchArticlesUseCaseTest {

    val articles: Articles = createArticles().toTypedArray()

    lateinit var useCase: SearchArticlesUseCase

    @Before
    fun setUp() {
        //arrange for all tests
        useCase = SearchArticlesUseCase()
    }


    @Test
    fun `invoke(), with articles equals null, then return empty array`() {
        // arrange

        //act
        val result = useCase.invoke(articles = null, query = "Nils")
        //assert
        assertTrue(result.isEmpty())
    }

    @Test
    fun `invoke(), with query is empty string, then return empty array`() {
        // arrange

        //act
        val result = useCase.invoke(articles = null, query = "")
        //assert
        assertTrue(result.isEmpty())
    }

    @Test
    fun `invoke(), with articles list where query is found in author of article`() {
        // arrange

        //act
        val result = useCase.invoke(articles = articles, query = "Nils")
        //assert
        assertTrue(result.isNotEmpty() && result.size == 1)
    }

    @Test
    fun `invoke(), with articles list where query is not in author of article`() {
        // arrange

        //act
        val result = useCase.invoke(articles = articles, query = "Not Found Query")
        //assert
        assertTrue(result.isEmpty())
    }

    @Test
    fun `invoke(), with articles list where query is found in title of article`() {
        // arrange

        //act
        val result = useCase.invoke(articles = articles, query = "hackers")
        //assert
        assertTrue(result.isNotEmpty() && result.size == 1)
    }

    @Test
    fun `invoke(), with articles list where query is not in title of article`() {
        // arrange

        //act
        val result = useCase.invoke(articles = articles, query = "Not Found Query")
        //assert
        assertTrue(result.isEmpty())
    }

    @Test
    fun `invoke(), with articles list where query is found in description of article`() {
        // arrange

        //act
        val result = useCase.invoke(articles = articles, query = "FBI")
        //assert
        assertTrue(result.isNotEmpty() && result.size == 1)
    }

    @Test
    fun `invoke(), with articles list where query is not in description of article`() {
        // arrange

        //act
        val result = useCase.invoke(articles = articles, query = "Not Found Query")
        //assert
        assertTrue(result.isEmpty())
    }

    @Test
    fun `invoke(), with articles list where query is found in content of article`() {
        // arrange

        //act
        val result = useCase.invoke(articles = articles, query = "WASHINGTON")
        //assert
        assertTrue(result.isNotEmpty() && result.size == 1)
    }

    @Test
    fun `invoke(), with articles list where query is not in content of article`() {
        // arrange

        //act
        val result = useCase.invoke(articles = articles, query = "Not Found Query")
        //assert
        assertTrue(result.isEmpty())
    }

}