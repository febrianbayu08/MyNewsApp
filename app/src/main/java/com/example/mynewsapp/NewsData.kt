package com.example.mynewsapp // PENTING: Sesuaikan ini dengan nama package Anda sendiri!

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

// --- 1. MODEL DATA ---
// Ini adalah bentuk data yang kita harapkan dari Internet (NewsAPI)
data class NewsResponse(
    val articles: List<Article>
)

data class Article(
    val title: String?,       // Judul Berita
    val description: String?, // Ringkasan
    val urlToImage: String?,  // Link Gambar
    val publishedAt: String?  // Tanggal
)

// --- 2. API SERVICE ---
// Ini adalah daftar alamat/link yang akan kita panggil
interface NewsApiService {
    // 1. Untuk Berita Utama (Yang sudah ada)
    @GET("v2/top-headlines")
    suspend fun getTopHeadlines(
        @Query("country") country: String = "us",
        @Query("apiKey") apiKey: String
    ): NewsResponse

    // 2. TAMBAHAN BARU: Untuk Pencarian
    @GET("v2/everything")
    suspend fun searchNews(
        @Query("q") query: String, // 'q' adalah kata kunci pencarian
        @Query("apiKey") apiKey: String
    ): NewsResponse
}

// --- 3. RETROFIT INSTANCE ---
// Ini adalah "mesin" yang menghubungkan aplikasi ke internet
object RetrofitClient {
    // Alamat utama NewsAPI
    private const val BASE_URL = "https://newsapi.org/"

    val apiService: NewsApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApiService::class.java)
    }
}