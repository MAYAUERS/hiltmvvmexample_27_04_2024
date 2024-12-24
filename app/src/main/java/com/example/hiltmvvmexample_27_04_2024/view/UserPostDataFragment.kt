package com.example.hiltmvvmexample_27_04_2024.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hiltmvvmexample_27_04_2024.R
import com.example.hiltmvvmexample_27_04_2024.UserPost
import com.example.hiltmvvmexample_27_04_2024.util.ApiState
import com.example.hiltmvvmexample_27_04_2024.viewmodel.UserPostDataViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class UserPostDataFragment : Fragment() {

    lateinit var userPostItemAdapter:UserPostItemAdapter
    private lateinit var userRecyclerView: RecyclerView
  //  private val userPostDataViewModel by viewModels<UserPostDataViewModel>()
  private val userPostDataViewModel:UserPostDataViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_post_data, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userRecyclerView = view.findViewById(R.id.recyclerView_list)
        userRecyclerView.layoutManager = LinearLayoutManager(context)

       /* userPostDataViewModel.userPost.observe(viewLifecycleOwner){
            userPostItemAdapter = UserPostItemAdapter(it as ArrayList<UserPost>)
            userRecyclerView.adapter =userPostItemAdapter
        }

        userPostDataViewModel.userPostError.observe(viewLifecycleOwner){
            Toast.makeText(context,it, Toast.LENGTH_SHORT).show()
        }*/
        /*userPostDataViewModel.getUserPostDataFromVM()

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){

                userPostDataViewModel.userPostFlow.collect{
                    Log.d("Data","userPostDataViewModel:=  ${it}")
                    when(it){
                        is ApiState.Loading ->{
                            userRecyclerView.isVisible = false
                        }
                        is ApiState.Failure -> {
                            userRecyclerView.isVisible = false
                            Log.d("Error","onCreate: ${it.msg}")
                        }
                        is ApiState.Success ->{
                            userRecyclerView.isVisible = true
                            userPostItemAdapter = UserPostItemAdapter(it.data as ArrayList<UserPost>)
                            userRecyclerView.adapter =userPostItemAdapter
                        }

                        else -> {
                            Log.d("Error in else","onCreate: ${it.toString()}")
                        }
                    }
                }
            }

        }*/
        // clod flow collection when emission is converted to state or shared flow
     /*   lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                userPostDataViewModel.userflow.collect{

                    when(it){
                        is ApiState.Loading ->{
                            userRecyclerView.isVisible = false
                        }
                        is ApiState.Failure -> {
                            userRecyclerView.isVisible = false
                            Log.d("Error","onCreate: ${it.msg}")
                        }
                        is ApiState.Success ->{
                            userRecyclerView.isVisible = true
                            userPostItemAdapter = UserPostItemAdapter(it.data as ArrayList<UserPost>)
                            userRecyclerView.adapter =userPostItemAdapter
                        }

                        else -> {
                            Log.d("Error in else","onCreate: ${it.toString()}")
                        }
                    }
                }
            }
        }*/

//livedata to flow
        lifecycleScope.launch {
          userPostDataViewModel.useLiveData.observe(viewLifecycleOwner, Observer {
              when(it){
                  is ApiState.Loading ->{
                      userRecyclerView.isVisible = false
                  }
                  is ApiState.Failure -> {
                      userRecyclerView.isVisible = false
                      Log.d("Error","onCreate: ${it.msg}")
                  }
                  is ApiState.Success ->{
                      userRecyclerView.isVisible = true
                      userPostItemAdapter = UserPostItemAdapter(it.data as ArrayList<UserPost>)
                      userRecyclerView.adapter =userPostItemAdapter
                  }

                  else -> {
                      Log.d("Error in else","onCreate: ${it.toString()}")
                  }
              }
          })
        }
    }
}