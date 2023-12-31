package com.example.logintask1.ui.home.userpost

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.logintask1.databinding.FragmentPostsBinding
import com.example.logintask1.ui.home.userpost.adapter.PostListAdapter
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class PostsFragment : Fragment() {

    private val viewModel: PostsViewModel by viewModels()
    private lateinit var binding: FragmentPostsBinding
    private lateinit var adapter: PostListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPostsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initView()
    }

    private fun initView() {
        adapter = PostListAdapter { post, position ->
            viewModel.likePost(post, position)
        }

        with(binding) {
            viewModel = this@PostsFragment.viewModel
            lifecycleOwner = this@PostsFragment
            recyclerViewPosts.adapter = this@PostsFragment.adapter
            swipeRefereshLayoutPosts.setOnRefreshListener {
                this@PostsFragment.viewModel.fetchPosts()
                adapter.submitList(emptyList())
                swipeRefereshLayoutPosts.isRefreshing = false
            }

        }

        viewModel.posts.observe(viewLifecycleOwner) { posts ->
            adapter.submitList(posts)
        }



    }

    companion object {
        fun newInstance() = PostsFragment()
    }

}