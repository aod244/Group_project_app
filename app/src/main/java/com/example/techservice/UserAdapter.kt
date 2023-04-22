package com.example.techservice

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class UserAdapter : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {
    private var TaskList: ArrayList<TaskModel> = ArrayList()

    fun addItems(items:ArrayList<TaskModel>) {
        this.TaskList = items
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = UserViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.user_recycler_view_row, parent, false)
    )

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val model = TaskList[position]
        holder.bindView(model)
    }

    override fun getItemCount(): Int {
        return TaskList.size
    }
    class UserViewHolder(var view: View): RecyclerView.ViewHolder(view){
        private var name = view.findViewById<TextView>(R.id.user_recycle_name)
        private var email = view.findViewById<TextView>(R.id.user_recycler_email)
        private val date = view.findViewById<TextView>(R.id.user_recycler_date)

        fun bindView(model: TaskModel){
            val datesplit = model.date
            val list: List<String> = datesplit.split(" ")
            name.text = model.client
            email.text = model.email
            date.text = list[0]
        }
    }
}