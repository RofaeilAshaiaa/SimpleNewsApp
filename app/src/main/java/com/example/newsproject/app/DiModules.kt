package com.example.newsproject.app

import android.content.Context
import com.example.newsproject.R
import com.example.newsproject.data.repository.NewsRepository
import com.example.newsproject.data.repository.NewsRepositoryImpl
import com.example.newsproject.data.source.remote.NewsRemoteRepo
import com.example.newsproject.data.source.remote.NewsRemoteRepoImpl
import com.example.newsproject.data.source.remote.NewsRemoteService
import com.example.newsproject.data.source.remote.NewsRemoteService.Companion.BASE_URL
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    fun provideMainService(@ApplicationContext context: Context): NewsRemoteService {
        val apiKey: String = context.getString(R.string.news_api_key)
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor { chain ->
                val original = chain.request()
                val originalHttpUrl: HttpUrl = original.url()
                val url = originalHttpUrl.newBuilder()
                    .addQueryParameter("apikey", apiKey)
                    .build()
                val requestBuilder = original.newBuilder().url(url)
                val request = requestBuilder.build()
                chain.proceed(request)
            }.build()
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsRemoteService::class.java)
    }

}

@Module
@InstallIn(SingletonComponent::class)
abstract class ReposModule {

    @Binds
    abstract fun bindNewsRepository(
        newsRepositoryImpl: NewsRepositoryImpl
    ): NewsRepository

    @Binds
    abstract fun bindNewsRemoteRepository(
        newsRemoteRepoImpl: NewsRemoteRepoImpl
    ): NewsRemoteRepo
}

@InstallIn(SingletonComponent::class)
@Module
class DispatchersModule {

    @Provides
    fun provideDefaultBackgroundDispatcher(): CoroutineDispatcher = Dispatchers.IO
}