package com.example.hiltmvvmexample_27_04_2024.network

import com.example.hiltmvvmexample_27_04_2024.UserPost
import javax.inject.Inject


class ApiServiceImpl
@Inject constructor(private val apiService: ApiService)
{
    suspend fun getUserPostData():List<UserPost> = apiService.getUserPostData()
}