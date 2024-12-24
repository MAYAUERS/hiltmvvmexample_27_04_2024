package com.example.hiltmvvmexample_27_04_2024.util

import com.example.hiltmvvmexample_27_04_2024.UserPost

sealed class ApiState {
    object Loading :ApiState()

    class Failure(val msg:Throwable):ApiState()
    class Success(val data:List<UserPost>):ApiState()
    object Empty:ApiState()
}