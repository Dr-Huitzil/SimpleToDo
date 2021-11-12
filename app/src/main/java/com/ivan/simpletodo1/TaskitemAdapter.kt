package com.ivan.simpletodo1

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TaskitemAdapter(val listOfItems: List<String>,
                      val longClickListener: OnLongClickListener) :
    RecyclerView.Adapter<TaskitemAdapter.ViewHolder>() {

    interface OnLongClickListener {
        fun OnLongClickListener(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val infLater = LayoutInflater.from(context)
        val contactView = infLater.inflate(android.R.layout.simple_list_item_1, parent, false)

        return ViewHolder(contactView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listOfItems.get(position)

        holder.textView.text = item
    }

    override fun getItemCount(): Int {
        return listOfItems.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView

        init {
            textView = itemView.findViewById(android.R.id.text1)

            itemView.setOnLongClickListener {
                longClickListener.OnLongClickListener(adapterPosition)
                true
            }
        }
    }

}