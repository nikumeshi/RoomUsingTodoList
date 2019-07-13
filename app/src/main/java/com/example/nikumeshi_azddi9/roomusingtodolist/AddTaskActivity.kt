package com.example.nikumeshi_azddi9.roomusingtodolist

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_add_task.*

class AddTaskActivity : AppCompatActivity() {

    private val btnListener = View.OnClickListener {
        when(it){
            okButton -> {
                setResult(
                    Activity.RESULT_OK, Intent().putExtra(NAME_RESULT_TASK, Task(
                    _id = intent.getIntExtra(LIST_SIZE,0),
                    title = taskTitle.text.toString(),
                    date = taskDate.text.toString(),
                    time = taskTime.text.toString(),
                    detail = taskDetail.text.toString(),
                    priority = PriorityEnum.getPriority(rg_priority.checkedRadioButtonId)))
                )
            }
        }
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_task)

        cancelButton.setOnClickListener(btnListener)
        okButton.setOnClickListener(btnListener)
    }
}
