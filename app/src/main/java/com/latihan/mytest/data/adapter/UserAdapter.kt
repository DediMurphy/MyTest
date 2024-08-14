package com.latihan.mytest.data.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.latihan.mytest.data.remote.DataItem
import com.latihan.mytest.databinding.ItemUserBinding

/**
 * Adapter untuk RecyclerView yang menampilkan daftar pengguna.
 * @property userList Daftar pengguna yang akan ditampilkan di RecyclerView.
 * @property onItemClick Lambda function yang dipanggil ketika sebuah item diklik.
 */
class UserAdapter(
    private var userList: List<DataItem>,
    private val onItemClick: (DataItem) -> Unit
) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    /**
     * ViewHolder untuk item pengguna di RecyclerView.
     * @param binding Binding yang menghubungkan layout item_user.xml dengan data.
     */
    inner class UserViewHolder(private val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {

        /**
         * Mengikat data pengguna ke tampilan item.
         * @param user Data pengguna yang akan ditampilkan.
         */
        fun bind(user: DataItem) {
            binding.tvFirstName.text = user.firstName
            binding.tvLastName.text = user.lastName
            binding.tvEmail.text = user.email

            // Menggunakan Glide untuk memuat gambar avatar dengan pemotongan lingkaran
            Glide.with(binding.root.context)
                .load(user.avatar)
                .circleCrop()
                .into(binding.ivAvatar)

            // Menangani klik pada item
            binding.root.setOnClickListener {
                onItemClick(user)
            }
        }
    }

    /**
     * Membuat instance UserViewHolder dengan layout item_user.xml.
     * @param parent Parent view group untuk layout inflater.
     * @param viewType Tipe view untuk adapter.
     * @return Instance UserViewHolder.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding)
    }

    /**
     * Mengikat data pengguna ke UserViewHolder pada posisi tertentu.
     * @param holder ViewHolder yang akan digunakan untuk mengikat data.
     * @param position Posisi item di daftar.
     */
    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(userList[position])
    }

    /**
     * Mengembalikan jumlah item dalam daftar pengguna.
     * @return Jumlah item dalam userList.
     */
    override fun getItemCount(): Int = userList.size

    /**
     * Menambahkan data pengguna baru ke dalam daftar dan memperbarui tampilan RecyclerView.
     * @param newUsers Daftar pengguna baru yang akan ditambahkan.
     */
    fun addData(newUsers: List<DataItem>) {
        userList = userList + newUsers
        notifyDataSetChanged()
    }

    /**
     * Menghapus semua data pengguna dari daftar dan memperbarui tampilan RecyclerView.
     */
    fun clearData() {
        userList = emptyList()
        notifyDataSetChanged()
    }
}
