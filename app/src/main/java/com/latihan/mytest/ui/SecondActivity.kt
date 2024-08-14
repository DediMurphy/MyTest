package com.latihan.mytest.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.latihan.mytest.databinding.ActivitySecondBinding

/**
 * SecondActivity adalah activity yang menangani tampilan kedua dalam aplikasi.
 * Activity ini memungkinkan pengguna untuk memilih pengguna dari daftar yang disediakan di ThirdActivity.
 * Setelah pengguna memilih pengguna, nama pengguna yang dipilih akan ditampilkan di SecondActivity.
 */
class SecondActivity : AppCompatActivity() {

    // Objek binding untuk mengakses elemen UI dari layout activity_second.xml
    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Inflate layout dan inisialisasi binding
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Mengatur toolbar sebagai ActionBar dan menambahkan tombol kembali
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Mengatur listener untuk tombol kembali di toolbar
        binding.toolbar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        // Mengambil nama dari intent yang diteruskan dari activity sebelumnya (FirstActivity)
        val name = intent.getStringExtra("name")
        binding.tvName.text = name

        // Menangani klik tombol "Choose User"
        binding.btnChooseUser.setOnClickListener {
            // Memulai ThirdActivity dengan ActivityResultLauncher
            val intent = Intent(this, ThirdActivity::class.java)
            getResultLauncher.launch(intent)
        }
    }

    // ActivityResultLauncher untuk memulai ThirdActivity dan menangani hasilnya
    private val getResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            // Mengambil nama pengguna yang dipilih dari hasil
            val selectedUserName = result.data?.getStringExtra("selectedUserName")
            // Memperbarui TextView dengan nama pengguna yang dipilih
            binding.selectedUserTextView.text = selectedUserName
        }
    }
}
