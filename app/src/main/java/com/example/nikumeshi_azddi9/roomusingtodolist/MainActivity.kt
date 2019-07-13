package com.example.nikumeshi_azddi9.roomusingtodolist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

const val MOD_TASK = "modTask"
const val NAME_RESULT_TASK = "resultTask"
const val LIST_SIZE = "listSize"
const val RC_ADD = 1
const val RC_MOD = 2
const val RESULT_MOD = 10
const val RESULT_DEL = 20


class MainActivity : AppCompatActivity() {

    private val taskVM by lazy { ViewModelProviders.of(this).get(TaskViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        taskVM.allTasks.observe(this, Observer {
            val enableTaskList = mutableListOf<Task>()
            for (x in it){ if(x.isEnable) enableTaskList.add(x) }

            val tAdapter = TaskAdapter(this, enableTaskList){
                val intent = Intent(this, ModTaskActivity::class.java)
                intent.putExtra(MOD_TASK, it)
                startActivityForResult(intent, RC_MOD)
            }
            taskList.run {
                setHasFixedSize(true)
                adapter = tAdapter
                layoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
            }
        })

        addBtn.setOnClickListener{
            val listSize = taskVM.allTasks.value?.size
            val intent = Intent(this, AddTaskActivity::class.java)
            intent.putExtra(LIST_SIZE, listSize)
            startActivityForResult(intent, RC_ADD)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        data?.let{
            val targetTask = it.getSerializableExtra(NAME_RESULT_TASK) as Task
            when(requestCode){
                RC_ADD -> {
                    taskVM.insert(targetTask)
                }
                else -> {
                    when(resultCode){
                        RESULT_MOD -> taskVM.update(targetTask)
                        RESULT_DEL -> taskVM.logicalDelete(targetTask._id)
                        else -> {Toast.makeText(this, "wtf! unexpected error...", Toast.LENGTH_SHORT).show()}
                    }
                }
            }
        }
    }
}
