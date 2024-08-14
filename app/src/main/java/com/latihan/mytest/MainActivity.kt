package com.latihan.mytest

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.latihan.mytest.databinding.ActivityMainBinding
import com.latihan.mytest.ui.SecondActivity

class MainActivity : AppCompatActivity() {

    // Binding untuk layout activity_main.xml
    private lateinit var binding: ActivityMainBinding

    // Fungsi ini dipanggil saat activity dimulai
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Mengaktifkan edge-to-edge display untuk tampilan layar penuh
        enableEdgeToEdge()

        // Menginisialisasi binding untuk menghubungkan layout XML dengan activity
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Menangani insets untuk penyesuaian layout terhadap sistem bar
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Mengatur listener untuk tombol palindrome
        binding.palindromeButton.setOnClickListener {
            val sentence = binding.palindromeEdittext.text.toString()
            // Memeriksa apakah kalimat adalah palindrome
            if (isPalindrome(sentence)) {
                showToast("isPalindrome") // Menampilkan pesan jika palindrome
            } else {
                showToast("not palindrome") // Menampilkan pesan jika bukan palindrome
            }
        }

        // Memanggil fungsi untuk mengatur tombol berikutnya
        nextButton()
    }

    // Fungsi untuk mengatur klik listener pada tombol 'Next'
    private fun nextButton() {
        binding.nextButton.setOnClickListener {
            val name = binding.nameEdittext.text.toString()
            val sentence = binding.palindromeEdittext.text.toString()

            // Memeriksa apakah nama tidak diisi
            if (name.isEmpty()) {
                showToast("Please Enter your name") // Menampilkan pesan jika nama belum diisi
            }
            // Memeriksa apakah kalimat bukan palindrome
            else if (!isPalindrome(sentence)) {
                showToast("The sentence is not a palindrome") // Menampilkan pesan jika kalimat bukan palindrome
            }
            // Jika semua validasi lolos, lanjutkan ke activity berikutnya
            else {
                val intent = Intent(this, SecondActivity::class.java)
                // Mengirimkan nama ke SecondActivity
                intent.putExtra("name", binding.nameEdittext.text.toString())
                startActivity(intent)
            }
        }
    }

    // Fungsi untuk memeriksa apakah kalimat adalah palindrome
    private fun isPalindrome(sentence: String): Boolean {
        val cleanedSentence = sentence.replace(" ", "").lowercase()
        return cleanedSentence == cleanedSentence.reversed()
    }

    // Fungsi untuk menampilkan pesan toast
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
