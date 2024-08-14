# MyTestApp

MyTestApp adalah aplikasi Android yang menyediakan fungsionalitas untuk memeriksa apakah sebuah kalimat adalah palindrom dan menampilkan daftar pengguna dari API. Aplikasi ini memanfaatkan arsitektur MVVM, Coroutine, dan Flow untuk pengelolaan data dan UI.

## Fitur

- **Palindrome Check**: Memeriksa apakah kalimat yang dimasukkan adalah palindrom.
- **User List**: Menampilkan daftar pengguna yang diambil dari API dengan fitur scroll infinitive dan pull-to-refresh.

## Teknologi yang Digunakan

- **Android SDK**: 30
- **Kotlin**: 1.4.10
- **Retrofit**: Untuk melakukan permintaan jaringan.
- **Coroutines**: Untuk operasi asinkron.
- **Flow**: Untuk pengelolaan data reaktif.
- **LiveData**: Untuk observasi data.
- **Material Design Components**: Untuk UI.
- **Glide**: Untuk memuat gambar.

## Prasyarat

- Android Studio
- JDK 8 atau lebih baru

## Instalasi dan Pengaturan

1. **Clone Repository**
    ```bash
    git clone https://github.com/username/mytestapp.git
    ```

2. **Buka Proyek di Android Studio**
   - Buka Android Studio dan pilih "Open an existing project".
   - Pilih folder proyek yang telah Anda kloning.

3. **Tambahkan Kunci API**
   - Jika aplikasi memerlukan kunci API, tambahkan kunci ke dalam file `local.properties` atau gunakan metode lain sesuai kebutuhan.

4. **Sinkronisasi Proyek**
   - Pastikan semua dependensi sudah terpasang dengan menjalankan gradle sync di Android Studio.

## Struktur Proyek

- **`MainActivity.kt`**: Aktivitas utama yang menangani input palindrome dan navigasi.
- **`SecondActivity.kt`**: Aktivitas kedua yang menampilkan hasil dari `MainActivity`.
- **`ThirdActivity.kt`**: Aktivitas ketiga yang menampilkan daftar pengguna dengan fitur scroll dan pull-to-refresh.
- **`UserViewModel.kt`**: ViewModel untuk mengelola data pengguna dan menghubungkan UI dengan repository.
- **`UserRepository.kt`**: Repository untuk mengambil data pengguna dari API.
- **`ApiService.kt`**: Interface untuk mendefinisikan endpoint API menggunakan Retrofit.
- **`UserAdapter.kt`**: Adapter untuk menampilkan data pengguna di RecyclerView.
- **`ItemUserBinding.kt`**: Binding layout untuk item pengguna di RecyclerView.
