# Digimonix - Digital World Explorer

Aplikasi Android untuk eksplorasi data Digimon dari Digi-API yang dikembangkan menggunakan Kotlin, Jetpack Compose, Material Design 3, Navigation, dan arsitektur MVVM.

## Screenshot Aplikasi

| Home Screen | Detail Screen |
| :---: | :---: |
|<img width="50%" alt="home" src="https://github.com/user-attachments/assets/9ab5b01e-6a13-4cde-8d95-d4e471e62984" />| <img width="50%" alt="detail" src="https://github.com/user-attachments/assets/badcd24c-1ccb-4671-8a7e-49010f290fda" />|

## Penjelasan Teknis

Aplikasi ini dibuat untuk menyelesaikan tugas responsi pemrograman mobile dengan spesifikasi sebagai berikut:

### 1. Bahasa Pemrograman Kotlin
- Menggunakan data class untuk representasi model data yang bersih dan aman.
- Menerapkan null safety untuk menghindari error pointer.
- Menggunakan fungsi lambda dan operator koleksi seperti map, filter, dan groupBy untuk memproses data dari API.

### 2. Antarmuka (UI)
- Menggunakan 100% Jetpack Compose tanpa layout XML.
- Menerapkan komponen Material Design 3 dengan tema kustom bernuansa monokrom dan efek liquid glass.
- Mendukung mode terang dan gelap secara otomatis.

### 3. Data dan Daftar
- Menggunakan LazyColumn dan LazyRow untuk menampilkan daftar Digimon yang dikelompokkan berdasarkan abjad secara rapi dan efisien.
- Data diambil langsung dari Digi-API secara online dengan mekanisme pagination.

### 4. Networking
- Menggunakan Retrofit dan Gson untuk mengambil data dari Digi-API.
- Data utama yang ditampilkan meliputi nama Digimon, level, atribut, dan tipe, ditambah gambar asinkron, deskripsi, serta daftar kemampuan (skills).

### 5. Arsitektur MVVM
Proyek ini memisahkan logika aplikasi ke dalam beberapa lapisan:
- Model untuk struktur data dan respons API.
- Repository untuk manajemen sumber data.
- ViewModel untuk menangani state dan proses asynchronous.
- View (Compose UI) untuk menampilkan antarmuka.

### 6. Navigasi
- Menggunakan Jetpack Navigation Compose dengan dua layar utama: halaman utama (Home Screen) dan halaman detail (Detail Screen).

### 7. Manajemen State
- Menerapkan UiState untuk mengelola kondisi aplikasi, mulai dari proses pemuatan data (loading), data berhasil ditampilkan (success), hingga penanganan kesalahan koneksi (error).

## Cara Menjalankan Proyek

1. Lakukan clone repository:
   ```bash
   git clone https://github.com/empakih/H1D024068_ResponsiPemrogramanMobile.git
   ```
2. Buka folder proyek menggunakan Android Studio.
3. Tunggu proses sinkronisasi Gradle selesai.
4. Jalankan aplikasi pada emulator atau perangkat fisik Android.
