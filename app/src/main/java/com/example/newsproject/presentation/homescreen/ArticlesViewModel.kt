package com.example.newsproject.presentation.homescreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsproject.data.models.Article
import com.example.newsproject.domain.GetNewsUseCase
import com.example.newsproject.domain.SearchArticlesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

typealias Articles = Array<Article>

@HiltViewModel
class ArticlesViewModel @Inject constructor(
    val getNewsUseCase: GetNewsUseCase,
    val searchArticlesUseCase: SearchArticlesUseCase,
    val backgroundDispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _articles: MutableLiveData<Articles?> = MutableLiveData(null)
    val articles: LiveData<Articles?> = _articles

    private val _searchResult: MutableLiveData<Articles?> = MutableLiveData(null)
    val searchResult: LiveData<Articles?> = _searchResult

    private val _loading: MutableLiveData<Boolean> = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading

    private val _message: MutableLiveData<String?> = MutableLiveData(null)
    val message: LiveData<String?> = _message

    init {
        loadNewsArticles()
    }

    fun loadNewsArticles() {
        viewModelScope.launch(backgroundDispatcher) {
            _loading.postValue(true)
            runCatching {
                val arrayOfArticles = getNewsUseCase.invoke()
                _articles.postValue(arrayOfArticles)
            }.onFailure {
                _message.postValue(it.message)
            }
            _loading.postValue(false)
        }
    }

    fun performSearchQuery(query: String) {
        if (query.isEmpty()) return
        viewModelScope.launch(backgroundDispatcher) {
            val searchResultItems =
                searchArticlesUseCase.invoke(query, articles.value)
            _searchResult.postValue(searchResultItems.copyOf())
        }
    }

    fun clearSearch() {
        _searchResult.value = null
        _articles.value = articles.value?.copyOf()
    }

}