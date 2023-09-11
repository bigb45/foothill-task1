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

class PostsFragment : Fragment(), PutRequestInterface {

    private var viewModel = PostsViewModel()
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
        adapter = PostListAdapter { userPost, index ->
            viewModel.likePost(post = userPost, position = index)
        }

        with(binding.recyclerViewPosts) {
            adapter = this@PostsFragment.adapter
            //TODO add layout manager through the xml file
            layoutManager =
                LinearLayoutManager(this@PostsFragment.context, LinearLayoutManager.VERTICAL, false)
        }

        viewModel.posts.observe(viewLifecycleOwner) { posts ->
            adapter.submitList(posts)
        }


    }

    override fun updatePost(postId: Int) {
        //TODO update post
    }

    companion object {
        fun newInstance() = PostsFragment()
    }

}