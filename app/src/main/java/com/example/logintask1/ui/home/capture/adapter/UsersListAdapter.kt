package com.example.logintask1.ui.home.capture.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.logintask1.data.ListItem
import com.example.logintask1.data.toUiModel
import com.example.logintask1.databinding.ListItemBinding


class UsersListAdapter(
    private val cardClickListener: (ListItem, Int) -> Unit,
    private val imageClickListener: (ListItem) -> Unit,
) : ListAdapter<ListItem, UsersListAdapter.ListItemViewHolder>(UserListDiffCallback()) {
    private lateinit var binding: ListItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemViewHolder {
        binding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListItemViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
        with(binding) {
            card.setOnClickListener { cardClickListener(currentItem, position)}
            imageViewThumbnail.setOnClickListener { imageClickListener(currentItem) }
        }
    }

    class ListItemViewHolder(private val binding: ListItemBinding) : ViewHolder(binding.root) {
        fun bind(item: ListItem) {
            with(binding) {
                uiModel = item.toUiModel()
            }

        }
    }

}


class UserListDiffCallback : DiffUtil.ItemCallback<ListItem>() {
    override fun areItemsTheSame(oldItem: ListItem, newItem: ListItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ListItem, newItem: ListItem): Boolean {
        return oldItem == newItem
    }
}