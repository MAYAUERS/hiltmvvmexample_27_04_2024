package com.example.hiltmvvmexample_27_04_2024.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.hiltmvvmexample_27_04_2024.UserPost
import com.example.hiltmvvmexample_27_04_2024.repository.UserPostDataRepository
import com.example.hiltmvvmexample_27_04_2024.util.ApiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject
//working code
/*
@HiltViewModel
class UserPostDataViewModel
@Inject constructor
    (private var userPostDataRepository: UserPostDataRepository):ViewModel() {

    val userPost :LiveData<List<UserPost>> get() = _userPost
    private val _userPost =MutableLiveData<List<UserPost>>()

    val userPostError :LiveData<String> get() = _userPostError
    private val _userPostError =MutableLiveData<String>()


    fun getUserPostDataFromVM(){
        viewModelScope.launch {
            val response = userPostDataRepository.getUserPostDetails()
            if (response.isSuccessful){
                _userPost.value = response.body()
            }else{
                _userPostError.value = response.errorBody().toString()
            }
        }
    }

}*/

/*
@HiltViewModel
class UserPostDataViewModel
@Inject constructor
    (private var userPostDataRepository: UserPostDataRepository):ViewModel() {

    private val _userPostFlow:MutableStateFlow<ApiState> = MutableStateFlow(ApiState.Empty)

    val userPostFlow:StateFlow<ApiState> = _userPostFlow

    fun getUserPostDataFromVM(){
        viewModelScope.launch {
            _userPostFlow.value = ApiState.Loading

            userPostDataRepository.getUserPostDetails()
                .catch { e-> _userPostFlow.value = ApiState.Failure(e)}
                .collect{data->
                    _userPostFlow.value =ApiState.Success(data)
                }
        }
    }

}*/
@HiltViewModel
class UserPostDataViewModel
@Inject constructor
    (private var userPostDataRepository: UserPostDataRepository):ViewModel() {

        //MutableStateFlow
  /*  private val _userPostFlow:MutableStateFlow<ApiState> = MutableStateFlow(ApiState.Empty)
    val userPostFlow:StateFlow<ApiState> = _userPostFlow
    fun getUserPostDataFromVM(){
        viewModelScope.launch {
            _userPostFlow.value = ApiState.Loading

            userPostDataRepository.getUserPostDetails()
                .catch { e-> _userPostFlow.value = ApiState.Failure(e)}
                .collect{data->
                    _userPostFlow.value =ApiState.Success(data)
                }
        }
    }*/

    //cold flow converted into Sharedflow
   /* val userflow:Flow<ApiState> = userPostDataRepository.getUserPostDetails().map {
        ApiState.Success(it)
    }.onCompletion {
        Log.d("Meera","Flow is completed")
    }.shareIn(viewModelScope,
        started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5000),
        replay = 1)*/

        //cold flow to state flow conversion
  /*  val userflow:Flow<ApiState> = userPostDataRepository.getUserPostDetails().map {
        ApiState.Success(it
    }.onCompletion {
        Log.d("Meera","Flow is completed")
    }.stateIn(initialValue = ApiState.Loading,
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5000))
*/
    //Live data to flow
   /* val useLiveData:LiveData<ApiState> get() = _userLiveData
    private val _userLiveData = MutableLiveData<ApiState>()
   init{
       viewModelScope.launch {
           userPostDataRepository.getUserPostDetails().collect {
               _userLiveData.value = ApiState.Success(it)
           }
       }
    }*/

        //Livedata as asLivedata() terminal operator
    val useLiveData:LiveData<ApiState> = userPostDataRepository.getUserPostDetails().map {
        ApiState.Success(it)
    }.onStart {
        Log.d("Meera","Flow is onStart")
    }.onCompletion {
            Log.d("Meera","Flow is onCompletion")
    }.asLiveData()
}