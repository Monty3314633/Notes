package com.example.notes.navigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notes.R
import com.example.notes.adapter.CardAdapter
import com.example.notes.databinding.FragmentMainBinding
import com.example.notes.dto.Author
import com.example.notes.dto.Post
import com.example.notes.viewModel.PostsState
import com.example.notes.viewModel.PostsViewModel

class MainFragment : Fragment(R.layout.fragment_main) {

    private val viewModel : PostsViewModel by viewModels()

    private lateinit var binding : FragmentMainBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainBinding.bind(view)

        val list = listOf<Post>(
            Post(1,"hello", Author("hi",""),2,2,4)
        )

        binding.recycler.layoutManager = LinearLayoutManager(requireContext())


        viewModel.liveData.observe(viewLifecycleOwner) { state ->
            when(state) {
                is PostsState.Success -> {
                    binding.recycler.adapter = CardAdapter(state.posts.posts)
                }
                is PostsState.Error ->{
                    Toast.makeText(requireContext(), "while", Toast.LENGTH_SHORT).show()
                }
                is PostsState.Loading->{

                }
                is PostsState.Done->{

                }
            }
        }
        viewModel.load()

        binding.add.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_addPostFragment)

        }
    }
}