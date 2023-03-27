package com.example.listuserssample

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.listuserssample.databinding.UserListItemBinding
import com.example.model.User
import com.squareup.picasso.Picasso

class UserListAdapter :
    RecyclerView.Adapter<UserListAdapter.ViewHolder>() {

    private val data = mutableListOf<User>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = UserListItemBinding.inflate(inflater, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user: User = data[position]
        holder.bind(user)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun replaceAll(item: List<User>) {
        data.clear()
        data.addAll(item)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: UserListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(user: User) {
            Picasso.get().load(user.avatarUrl).into(binding.imageView)
            binding.textView.text = user.login
        }
    }
}
