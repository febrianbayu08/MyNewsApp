# 📰 My News App (Berita Harian)

Aplikasi baca berita modern berbasis Android yang dibangun menggunakan **Kotlin** dan **Jetpack Compose**. Aplikasi ini mengambil data *real-time* dari NewsAPI.org dan menyajikannya dengan antarmuka yang bersih, responsif, dan ramah pengguna.

Proyek ini dibuat untuk memenuhi Tugas Akhir Mata Pelajaran Pemrograman Mobile.

## 📸 Screenshot Aplikasi
![WhatsApp Image 2025-11-26 at 12 06 29_51ca7cae](https://github.com/user-attachments/assets/90972981-4307-43f9-ad7c-70ffffc74746)


## ✨ Fitur Unggulan

### 1. 🔍 Pencarian & Kategori
* **Search Bar:** Pengguna dapat mencari berita spesifik (misal: "Bitcoin", "NASA") secara *real-time*.
* **Kategori (Chips):** Filter berita berdasarkan topik seperti *Technology, Sports, Business, Health,* dll.

### 2. 🌐 Multi-Bahasa (Localization)
Aplikasi otomatis mendeteksi pengaturan bahasa HP pengguna:
* **Bahasa Indonesia:** Judul aplikasi berubah menjadi "Berita Harian" dan memuat berita lokal.
* **English (Default):** Judul menjadi "Daily News" dan memuat berita global.

### 3. 🌙 Mode Gelap (Dark Mode)
Mendukung tema gelap otomatis (*System Default*) dengan palet warna yang nyaman di mata, memudahkan membaca berita di malam hari.

### 4. 📱 Responsif (Portrait & Landscape)
* **Portrait:** Tampilan list vertikal standar.
* **Landscape:** Tampilan list berubah menjadi layout menyamping (Gambar di kiri, Teks di kanan) agar lebih rapi dan tidak terpotong.

### 5. 🔗 Fitur Berbagi (Share)
Terdapat tombol **Share** pada setiap kartu berita untuk membagikan link berita langsung ke WhatsApp, Telegram, atau media sosial lainnya.

## 🛠️ Teknologi yang Digunakan

* **Bahasa:** Kotlin 100%
* **UI Toolkit:** Jetpack Compose (Material3)
* **Architecture:** MVVM (Model-View-ViewModel)
* **Networking:** Retrofit2 & Gson Converter
* **Image Loading:** Coil (Coroutines Image Loader)
* **Navigation:** Jetpack Navigation Compose
* **Asynchronous:** Kotlin Coroutines

## 🚀 Cara Menjalankan Project

1.  **Clone** repository ini ke komputer Anda.
2.  Buka project menggunakan **Android Studio** (Versi terbaru disarankan).
3.  **PENTING:** Konfigurasi API Key.
    * Dapatkan API Key gratis di [https://newsapi.org/](https://newsapi.org/).
    * Buka file `app/java/com.example.mynewsapp/NewsViewModel.kt`.
    * Masukkan API Key Anda pada baris berikut:
    ```kotlin
    private val apiKey = "MASUKKAN_API_KEY_ANDA_DISINI"
    ```
4.  Tunggu proses **Gradle Sync** selesai.
5.  Jalankan aplikasi (Run) di Emulator atau Perangkat Fisik.

## 👤 Identitas Pembuat

* **Nama:** [Tulis Nama Lengkap Anda Di Sini]
* **Kelas:** [Tulis Kelas Anda, Misal: XII RPL 1]
* **Sekolah:** SMKN 6 Surakarta
* **Tahun:** 2025

---
*Dibuat dengan ❤️ menggunakan Android Jetpack Compose*
