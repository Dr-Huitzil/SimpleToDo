package com.ivan.simpletodo1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.FileUtils
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.apache.commons.io.FileUtils.writeLines
import org.apache.commons.io.IOUtils.writeLines
import java.io.File
import java.io.IOException
import java.nio.charset.Charset

class MainActivity : AppCompatActivity() {
    var listOfTasks = mutableListOf<String>()
    lateinit var adapter: TaskitemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val onLongClickListener = object : TaskitemAdapter.OnLongClickListener{
            override fun OnLongClickListener(position: Int) {
                // 1. remove the item of the list
                listOfTasks.removeAt(position)
                //2. notify the adapter
                adapter.notifyDataSetChanged()

                saveItems()
            }
        }

        loadItems()


        // look up the recycler layout
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        // create an adapter that passes the data
        adapter = TaskitemAdapter(listOfTasks, onLongClickListener)
        // attach the adapter to the recyclerView
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        // set up the button adn the text field

        val inputTextField = findViewById<EditText>(R.id.task)

        findViewById<Button>(R.id.addButton).setOnClickListener {
            // grab the text the user has inputted
            val userInputtedText = inputTextField.text.toString()
            // add the string to out list of tasks
            listOfTasks.add(userInputtedText)
            // notify the adapter that the data has been updated
            adapter.notifyItemInserted(listOfTasks.size - 1)
            // reset the text field
            inputTextField.setText("")
            saveItems()
        }
    }

    // save the data
    // by reading and writing a file
    // create a method that grabs data file
    fun getDataFile() : File {
        // every line is going to represent a task
        return File(filesDir, "data.txt")
    }
    // load the items by reading lines
    fun loadItems(){
        try{
        listOfTasks = org.apache.commons.io.FileUtils.readLines(getDataFile(), Charset.defaultCharset())
        } catch (ioException: IOException){
            ioException.printStackTrace()
        }
    }
    // save the items by writing into the file
    fun saveItems() {
        try {
            org.apache.commons.io.FileUtils.writeLines(getDataFile(), listOfTasks)
        } catch (ioException: IOException){
            ioException.printStackTrace()
        }
    }
}