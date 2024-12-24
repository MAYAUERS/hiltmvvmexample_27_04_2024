package com.example.hiltmvvmexample_27_04_2024.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.hiltmvvmexample_27_04_2024.R
import com.example.hiltmvvmexample_27_04_2024.UserPost
import java.util.ArrayList

class UserPostItemAdapter(private var user:ArrayList<UserPost>):RecyclerView.Adapter<UserPostItemAdapter.MyViewHolder>() {

     class MyViewHolder( item: View):RecyclerView.ViewHolder(item) {
         var id =item.findViewById<TextView>(R.id.textView_id)
         var userName =item.findViewById<TextView>(R.id.textView_name)

         fun bindView(user: UserPost){
             id.text= user.id.toString()
             userName.text=user.title
         }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_user_item_list,parent,false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return user.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
       holder.bindView(user[position])
    }
}