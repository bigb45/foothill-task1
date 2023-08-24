package com.example.logintask1.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.logintask1.data.ListItem
import com.example.logintask1.databinding.ListItemBinding

class UsersListAdapter(
    private val clickListener: (ListItem, Int) -> Unit
): ListAdapter<ListItem, UsersListAdapter.ListItemViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemViewHolder {
        val binding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListItemViewHolder, position: Int) {


        val currentItem = getItem(position)
        holder.bind(currentItem)
        holder.itemView.setOnClickListener { clickListener(currentItem, position) }
    }

    class ListItemViewHolder(private val binding: ListItemBinding) : ViewHolder(binding.root) {
        fun bind(item: ListItem) {
//            set the text for each item
            Glide.with(binding.root)
                .load(item.imageUri)
                .into(binding.imageViewThumbnail)
            binding.textViewTitle.text = item.title
            binding.textViewDetails.text = item.imageUri.toString()
//            toggle visibility of details when expanded or collapsed
            binding.textViewDetails.visibility = if (item.isExpanded) View.VISIBLE else View.GONE
        }
    }
}


class DiffCallback : DiffUtil.ItemCallback<ListItem>() {
    override fun areItemsTheSame(oldItem: ListItem, newItem: ListItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ListItem, newItem: ListItem): Boolean {
        return oldItem == newItem
    }
}