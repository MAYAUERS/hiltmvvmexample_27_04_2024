package com.example.hiltmvvmexample_27_04_2024.repository

import com.example.hiltmvvmexample_27_04_2024.UserPost
import com.example.hiltmvvmexample_27_04_2024.network.ApiServiceImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import javax.inject.Inject

class UserPostDataRepository
@Inject constructor
    (private var apiService: ApiServiceImpl) {

  /*  suspend fun getUserPostDetails():Response<List<UserPost>>{
        return apiService.getUserPostData()
    }*/
 /* suspend fun getUserPostDetails():List<UserPost>{
      return apiService.getUserPostData()
  }*/

    fun getUserPostDetails():Flow<List<UserPost>> = flow {
        val result = apiService.getUserPostData()
        emit(result)
    }.flowOn(Dispatchers.IO)

}