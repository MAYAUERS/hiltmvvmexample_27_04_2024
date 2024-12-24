package com.example.hiltmvvmexample_27_04_2024.network

import com.example.hiltmvvmexample_27_04_2024.UserPost
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    //https://jsonplaceholder.typicode.com/posts

    @GET("posts")
    //suspend fun getUserPostData():Response<List<UserPost>>
    suspend fun getUserPostData():List<UserPost>
}