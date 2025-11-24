package com.example.mynewsapp

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

@Composable
fun NewsItem(
    article: Article,
    onClick: (Article) -> Unit
) {
    // Deteksi orientasi layar (Potrait/Landscape)
    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
            // Jika Landscape, tinggi kartu lebih pendek (160dp) agar muat banyak
            // Jika Portrait, tinggi kartu 320dp biar gambar puas
            .height(if (isLandscape) 160.dp else 320.dp)
            .clickable { onClick(article) },
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        if (isLandscape) {
            // --- LAYOUT LANDSCAPE (Samping-sampingan) ---
            Row(modifier = Modifier.fillMaxSize()) {
                // Gambar di Kiri (35% lebar kartu)
                Box(
                    modifier = Modifier
                        .weight(0.35f)
                        .fillMaxHeight()
                ) {
                    NewsImage(article.urlToImage, isLandscape = true)
                }

                // Teks di Kanan (65% lebar kartu)
                Column(
                    modifier = Modifier
                        .weight(0.65f)
                        .padding(16.dp)
                ) {
                    NewsContent(article)
                }
            }
        } else {
            // --- LAYOUT PORTRAIT (Atas-bawah) ---
            Column(modifier = Modifier.fillMaxSize()) {
                // Gambar di Atas
                Box(
                    modifier = Modifier
                        .weight(0.6f) // Gambar ambil 60% tinggi kartu
                        .fillMaxWidth()
                ) {
                    NewsImage(article.urlToImage, isLandscape = false)
                }

                // Teks di Bawah
                Column(
                    modifier = Modifier
                        .weight(0.4f)
                        .padding(16.dp)
                ) {
                    NewsContent(article)
                }
            }
        }
    }
}

// Fungsi Pembantu untuk Menampilkan Gambar
@Composable
fun NewsImage(url: String?, isLandscape: Boolean) {
    AsyncImage(
        model = url,
        contentDescription = "News Image",
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop
    )
}

// Fungsi Pembantu untuk Menampilkan Teks
@Composable
fun NewsContent(article: Article) {
    Column(verticalArrangement = Arrangement.Center) {
        // Tanggal
        Text(
            text = article.publishedAt?.take(10) ?: "Unknown Date",
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(4.dp))

        // Judul
        Text(
            text = article.title ?: "No Title",
            style = MaterialTheme.typography.titleLarge.copy(
                fontSize = 16.sp, // Font sedikit lebih kecil agar muat
                fontWeight = FontWeight.Bold
            ),
            maxLines = 2, // Batasi 2 baris saja
            overflow = TextOverflow.Ellipsis,
            color = MaterialTheme.colorScheme.onSurface
        )

        // Deskripsi (Hanya tampil jika muat/tidak null)
        if (article.description != null) {
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = article.description,
                style = MaterialTheme.typography.bodySmall,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                color = MaterialTheme.colorScheme.secondary
            )
        }
    }
}