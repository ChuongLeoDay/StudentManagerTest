package com.example.studentmanager.adapter

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.studentmanager.R
import com.example.studentmanager.StudentModel
import java.util.Base64


class StuAdapter(private val stuList : ArrayList<StudentModel>) : RecyclerView.Adapter<StuAdapter.MyViewHolder>(){
    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val image : ImageView = itemView.findViewById(R.id.imageStudentCard)
        val name : TextView = itemView.findViewById(R.id.nameStudentCard)
        val id : TextView = itemView.findViewById(R.id.IdStudentCard)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.layout_item_student_view, parent,false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return stuList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = stuList[position]
        val decodedBytes = android.util.Base64.decode(currentItem.Image, android.util.Base64.DEFAULT)
        val decodedBitmap = BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.size)
        holder.image.setImageBitmap(decodedBitmap)
        holder.name.text = currentItem.Name
        holder.id.text = currentItem.idStudent
    }


}

