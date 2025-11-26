package com.example.mynewsapp

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

// --- 1. MODEL DATA LENGKAP ---
data class NewsResponse(
    val articles: List<Article>
)

data class Article(
    val source: Source?,      // Info Sumber (CNN, BBC, dll)
    val author: String?,      // Nama Penulis
    val title: String?,
    val description: String?,
    val url: String?,         // Link asli untuk tombol "Baca Selengkapnya"
    val urlToImage: String?,
    val publishedAt: String?,
    val content: String?      // Isi berita (biasanya potongan paragraf awal)
)

data class Source(
    val id: String?,
    val name: String?
)

// --- 2. API SERVICE ---
interface NewsApiService {
    @GET("v2/top-headlines")
    suspend fun getTopHeadlines(
        @Query("country") country: String = "us",
        @Query("apiKey") apiKey: String
    ): NewsResponse

    @GET("v2/everything")
    suspend fun searchNews(
        @Query("q") query: String,
        @Query("apiKey") apiKey: String
    ): NewsResponse
}

// --- 3. RETROFIT ---
object RetrofitClient {
    private const val BASE_URL = "https://newsapi.org/"

    val apiService: NewsApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApiService::class.java)
    }
}