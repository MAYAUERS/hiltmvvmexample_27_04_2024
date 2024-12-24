package com.example.hiltmvvmexample_27_04_2024.view


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import androidx.fragment.app.Fragment

import com.example.hiltmvvmexample_27_04_2024.R

import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
       //  val viewModel:UserPostDataViewModel by viewModels()
        val userfragment : Fragment =UserPostDataFragment()
        val fragment : Fragment? = supportFragmentManager.findFragmentByTag(UserPostDataFragment::class.java.simpleName)

        if (fragment !is UserPostDataFragment){
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container,
                    userfragment,UserPostDataFragment::class.java.simpleName)
                .commit()
        }
    }

  /*  private lateinit var postAdapter: UserPostItemAdapter
    private val viewModel:UserPostDataViewModel by viewModels()
    lateinit var recyclerview :RecyclerView
    lateinit var progressBar:ProgressBar
    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerview = findViewById(R.id.recyclerview)
        recyclerview.layoutManager = LinearLayoutManager(this)
        progressBar = findViewById(R.id.progress_bar)

        viewModel.getUserPostDataFromVM()


        lifecycleScope.launchWhenStarted {
            viewModel.userPostFlow.collect {it->
                when(it){
                    is ApiState.Loading->{
                       recyclerview.visibility = View.GONE
                       progressBar.visibility= View.VISIBLE
                    }
                    is ApiState.Failure -> {
                        recyclerview.visibility = View.GONE
                        progressBar.visibility = View.GONE
                        Log.d("main", "onCreate: ${it.msg}")
                    }
                    is ApiState.Success->{
                        recyclerview.visibility = View.VISIBLE
                        progressBar.visibility =View.GONE
                        postAdapter = UserPostItemAdapter(it.data as ArrayList<UserPost>)
                        recyclerview.adapter = postAdapter
                        postAdapter.notifyDataSetChanged()
                    }
                    is ApiState.Empty->{

                    }
                }
            }
        }

    }*/
}