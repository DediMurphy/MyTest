package com.latihan.mytest.data.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.latihan.mytest.data.remote.DataItem
import com.latihan.mytest.databinding.ItemUserBinding

class UserAdapter(private var userList: List<DataItem>, private val onItemClick: (DataItem) -> Unit) :
    RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    inner class UserViewHolder(private val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(user: DataItem) {
            binding.tvFirstName.text = user.firstName
            binding.tvLastName.text = user.lastName
            binding.tvEmail.text = user.email
            Glide.with(binding.root.context)
                .load(user.avatar)
                .circleCrop()
                .into(binding.ivAvatar)

            binding.root.setOnClickListener {
                onItemClick(user)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(userList[position])
    }

    override fun getItemCount(): Int = userList.size

    fun addData(newUsers: List<DataItem>) {
        userList = userList + newUsers
        notifyDataSetChanged()
    }

    fun clearData() {
        userList = emptyList()
        notifyDataSetChanged()
    }
}