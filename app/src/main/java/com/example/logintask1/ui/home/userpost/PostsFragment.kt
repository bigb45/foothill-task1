package com.example.logintask1.ui.home.userpost

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.logintask1.databinding.FragmentPostsBinding
import com.example.logintask1.ui.home.userpost.adapter.PostListAdapter

const val BASE_URL = "https://64fce528605a026163aedf15.mockapi.io/"

class PostsFragment : Fragment() {
    companion object {
        fun newInstance() = PostsFragment()
    }

    private var viewModel = PostsViewModel()
    private lateinit var binding: FragmentPostsBinding
    private val adapter = PostListAdapter()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPostsBinding.inflate(inflater, container, false)
        with(binding) {
            recyclerViewPosts.adapter = this@PostsFragment.adapter
            recyclerViewPosts.layoutManager =
                LinearLayoutManager(this@PostsFragment.context, LinearLayoutManager.VERTICAL, false)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.posts.observe(viewLifecycleOwner) { posts ->
            adapter.submitList(posts)
        }

    }


}