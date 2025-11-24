package com.example.mynewsapp

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class NewsViewModel : ViewModel() {

    private val _newsList = mutableStateOf<List<Article>>(emptyList())
    val newsList: State<List<Article>> = _newsList

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    // Variable untuk menyimpan teks pencarian
    private val _searchQuery = mutableStateOf("")
    val searchQuery: State<String> = _searchQuery

    // Masukkan API Key Anda di sini agar tidak perlu tulis ulang terus
    private val apiKey = "230958b2cc1249618403a00c66a8d30f"

    var selectedArticle: Article? = null
    private var searchJob: Job? = null

    init {
        fetchNews() // Load berita awal saat aplikasi dibuka
    }

    // Fungsi ganti teks search
    fun onSearchQueryChange(newQuery: String) {
        _searchQuery.value = newQuery

        // Debounce: Tunggu user selesai ngetik 1 detik baru cari (biar hemat kuota API)
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(1000L)
            if (newQuery.isNotEmpty()) {
                searchNews(newQuery)
            } else {
                fetchNews() // Kalau teks dihapus, kembali ke berita utama
            }
        }
    }

    private fun fetchNews() {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val response = RetrofitClient.apiService.getTopHeadlines(apiKey = apiKey)
                _newsList.value = response.articles
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                _isLoading.value = false
            }
        }
    }

    private fun searchNews(query: String) {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val response = RetrofitClient.apiService.searchNews(query = query, apiKey = apiKey)
                _newsList.value = response.articles
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                _isLoading.value = false
            }
        }
    }
}