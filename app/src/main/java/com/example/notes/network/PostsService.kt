package com.example.notes.network

import com.example.notes.dto.GetPost
import com.example.notes.dto.SetPost
import com.example.notes.dto.SetPostResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface PostsService {
    @GET("posts")
    fun getPost() : Call<GetPost>

    @POST("posts")
    fun setPost(@Body post: SetPost) : Call<SetPostResponse>
}