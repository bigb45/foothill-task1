package com.example.logintask1.ui.home.userpost.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.logintask1.data.user.UserPost
import com.example.logintask1.data.user.toUiModel
import com.example.logintask1.databinding.PostBinding


class PostListAdapter(private val likePost: (UserPost) -> Unit) : ListAdapter<UserPost, PostListAdapter.PostListViewHolder>(PostListDiffCallback()) {
    private lateinit var binding: PostBinding

    class PostListViewHolder(private val binding: PostBinding, private val likePost: (UserPost) -> Unit) : ViewHolder(binding.root) {
        fun bind(currentPost: UserPost) {
            with(binding) {
                model = currentPost.toUiModel()
                binding.buttonLike.setOnClickListener {
                    likePost(currentPost)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostListViewHolder {
        binding = PostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostListViewHolder(binding, likePost)
    }

    override fun onBindViewHolder(holder: PostListViewHolder, position: Int) {
        val currentPost = getItem(position)
        holder.bind(currentPost)
    }

}


class PostListDiffCallback : DiffUtil.ItemCallback<UserPost>() {
    override fun areItemsTheSame(oldItem: UserPost, newItem: UserPost): Boolean {
        return oldItem.postId == newItem.postId
    }

    override fun areContentsTheSame(oldItem: UserPost, newItem: UserPost): Boolean {
        return oldItem == newItem
    }
}