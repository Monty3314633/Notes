package com.example.notes.navigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.notes.R
import com.example.notes.databinding.FragmentAddPostBinding
import com.example.notes.dto.SetPost
import com.example.notes.viewModel.PostsState
import com.example.notes.viewModel.PostsViewModel

class AddPostFragment : Fragment(R.layout.fragment_add_post) {
    lateinit var binding: FragmentAddPostBinding

    private val viewModel : PostsViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAddPostBinding.bind(view)

        binding.done.setOnClickListener {
            val author : String = binding.author.text.toString()
            val content : String = binding.content.text.toString()
            if (author.isNotEmpty() && content.isNotEmpty()){
                val post = SetPost(author,content)
                viewModel.setPost(post)
            }
        }
        viewModel.liveData.observe(viewLifecycleOwner){state->
            when(state){
                is PostsState.Done->{
                    findNavController().navigate(R.id.action_addPostFragment_to_mainFragment)
                }
                else->{

                }
            }
        }
    }
}