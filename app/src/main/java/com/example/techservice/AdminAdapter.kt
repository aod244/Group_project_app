package com.example.techservice

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AdminAdapter : RecyclerView.Adapter<AdminAdapter.AdminViewHolder>() {
    private var TaskList: ArrayList<TaskModel> = ArrayList()

    fun addItems(items:ArrayList<TaskModel>) {
        this.TaskList = items
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = AdminViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.admin_recycler_view_row, parent, false)
    )

    override fun onBindViewHolder(holder: AdminViewHolder, position: Int) {
        val model = TaskList[position]
        holder.bindView(model)
    }

    override fun getItemCount(): Int {
        return TaskList.size
    }
    class AdminViewHolder(var view: View): RecyclerView.ViewHolder(view){
        private var name = view.findViewById<TextView>(R.id.Admin_view_client_name)
        private var email = view.findViewById<TextView>(R.id.Admin_view_client_email)
        fun bindView(model: TaskModel){
            name.text = model.client
            email.text = model.email
        }
    }
}