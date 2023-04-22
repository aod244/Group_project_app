package com.example.techservice

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
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
        private lateinit var sqLiteHelper: SQLiteHelper
        private var name = view.findViewById<TextView>(R.id.Admin_view_client_name)
        private var email = view.findViewById<TextView>(R.id.Admin_view_client_email)
        private var details = view.findViewById<TextView>(R.id.admin_view_client_details)
        private val date = view.findViewById<TextView>(R.id.admin_view_client_date)
        val deletbtn = view.findViewById<Button>(R.id.Admin_view_morebtn)

        fun bindView(model: TaskModel){
            val deleteid = model.id
            val context: Context = view.context
            sqLiteHelper = SQLiteHelper(itemView.context)
            deletbtn.setOnClickListener{
                sqLiteHelper.deleteJob(deleteid)
                val intent = Intent(itemView.context, loggedasadmin::class.java)
                context.startActivity(intent)

            }
            val datesplit = model.date
            val list: List<String> = datesplit.split(" ")
            name.text = model.client
            email.text = model.email
            details.text = model.details
            date.text = list[0]
        }
    }
}