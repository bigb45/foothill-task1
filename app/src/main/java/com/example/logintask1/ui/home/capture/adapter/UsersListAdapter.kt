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
    private val cardClickListener: (ListItem) -> Unit,
    private val imageClickListener: (ListItem) -> Unit,
) : ListAdapter<ListItem, UsersListAdapter.ListItemViewHolder>(UserListDiffCallback()) {
    private lateinit var binding: ListItemBinding
    class ListItemViewHolder(private val binding: ListItemBinding, private val expandCard: (ListItem) -> Unit, private val showFullImage: (ListItem) -> Unit) : ViewHolder(binding.root) {
        fun bind(item: ListItem) {
            with(binding) {
                uiModel = item.toUiModel()
                card.setOnClickListener { expandCard(item) }
                imageViewThumbnail.setOnClickListener { showFullImage(item) }
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemViewHolder {
        binding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListItemViewHolder(binding, cardClickListener, imageClickListener)
    }

    override fun onBindViewHolder(holder: ListItemViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)

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