package com.latihan.mytest.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.latihan.mytest.data.adapter.UserAdapter
import com.latihan.mytest.databinding.ActivityThirdBinding
import com.latihan.mytest.di.Injection


/**
 * ThirdActivity adalah activity yang menampilkan daftar pengguna dalam bentuk list.
 * Activity ini memungkinkan pengguna untuk memilih pengguna dari daftar, dan hasil pilihannya akan dikembalikan ke Activity sebelumnya (SecondActivity).
 */
class ThirdActivity : AppCompatActivity() {

    private lateinit var binding: ActivityThirdBinding
    private lateinit var userViewModel: UserViewModel

    private lateinit var userAdapter: UserAdapter
    private var isLoading = false
    private var currentPage = 1
    private val perPage = 10

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Injeksi ViewModel secara manual
        userViewModel = Injection.provideUserViewModel()

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.toolbar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        setupRecyclerView()
        setupObservers()
        loadUsers()

        binding.swipeRefreshLayout.setOnRefreshListener {
            currentPage = 1
            userAdapter.clearData()
            loadUsers()
        }
    }

    private fun setupRecyclerView() {
        userAdapter = UserAdapter(emptyList()) { user ->
            val intent = Intent()
            intent.putExtra("selectedUserName", "${user.firstName} ${user.lastName}")
            setResult(RESULT_OK, intent)
            finish()
        }
        binding.rvUsers.apply {
            layoutManager = LinearLayoutManager(this@ThirdActivity)
            adapter = userAdapter
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    if (!recyclerView.canScrollVertically(1) && !isLoading) {
                        loadUsers()
                    }
                }
            })
        }
    }

    private fun setupObservers() {
        userViewModel.users.observe(this, Observer { users ->
            binding.swipeRefreshLayout.isRefreshing = false
            isLoading = false

            if (users.isEmpty() && currentPage == 1) {
                binding.tvEmptyState.visibility = View.VISIBLE
            } else {
                binding.tvEmptyState.visibility = View.GONE
                userAdapter.addData(users)
            }
        })

        userViewModel.error.observe(this, Observer { errorMessage ->
            isLoading = false
            binding.swipeRefreshLayout.isRefreshing = false
            Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
        })
    }

    private fun loadUsers() {
        isLoading = true
        userViewModel.fetchUsers(page = currentPage, perPage = perPage)
        currentPage++
    }
}