package com.example.logintask1.ui.home.userpost

import android.os.Bundle
import android.util.Log.d
import android.util.Log.e
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.logintask1.data.UserPost
import com.example.logintask1.data.toUiModel
import com.example.logintask1.databinding.FragmentPostsBinding
import com.example.logintask1.network.UserPostApiInterface
import com.example.logintask1.ui.home.userpost.adapter.PostListAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
const val BASE_URL = "https://64fce528605a026163aedf15.mockapi.io/"
class PostsFragment : Fragment() {
    companion object {
        fun newInstance() = PostsFragment()
    }

//    private var viewModel = ViewModelProvider(this)[PostsViewModel::class.java]

    private lateinit var binding: FragmentPostsBinding
    private val postList: ArrayList<UserPost> = arrayListOf()
    private val adapter = PostListAdapter()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPostsBinding.inflate(inflater, container, false)
        with(binding){
            recyclerViewPosts.adapter = this@PostsFragment.adapter
            recyclerViewPosts.layoutManager = LinearLayoutManager(this@PostsFragment.context, LinearLayoutManager.VERTICAL, false)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.button2.setOnClickListener {
            getPostsRequest()
        }
    }

    private fun getPostsRequest() {
        val userPostsService = Retrofit
            .Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(UserPostApiInterface::class.java)


        val posts = userPostsService.getPosts()
        posts.enqueue(object : Callback<List<UserPost>?> {
            override fun onResponse(
                call: Call<List<UserPost>?>,
                response: Response<List<UserPost>?>
            ) {
                val responseBody = response.body()
                for (i in responseBody!!) {
                    postList.add(i)

                }
                adapter.submitList(postList)


            }

            override fun onFailure(call: Call<List<UserPost>?>, t: Throwable) {
                e("error", t.message.toString())
            }
        })


    }


}