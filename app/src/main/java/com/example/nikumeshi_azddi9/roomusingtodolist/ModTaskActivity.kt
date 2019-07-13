package com.example.nikumeshi_azddi9.roomusingtodolist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_mod_task.*

class ModTaskActivity : AppCompatActivity() {

    private val btnListener = View.OnClickListener {
        val targetTask = intent.getSerializableExtra(MOD_TASK) as Task
        when (it){
            deleteButton_mod -> {
                setResult(RESULT_DEL, Intent().putExtra(NAME_RESULT_TASK,targetTask))
            }
            modifyButton_mod -> {
                setResult(
                    RESULT_MOD, Intent().putExtra(NAME_RESULT_TASK,
                    Task(
                        _id = targetTask._id,
                        title = taskTitle_mod.text.toString(),
                        date = taskDate_mod.text.toString(),
                        time = taskTime_mod.text.toString(),
                        detail = taskDetail_mod.text.toString(),
                        priority = PriorityEnum.getPriority(rg_priority.checkedRadioButtonId)
                    ))
                )
            }
        }
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mod_task)

        val taskInfo = intent.getSerializableExtra(MOD_TASK) as Task
        taskTitle_mod.setText(taskInfo.title)
        taskDate_mod.setText(taskInfo.date)
        taskTime_mod.setText(taskInfo.time)
        taskDetail_mod.setText(taskInfo.detail)
        rg_priority.check(PriorityEnum.getResourcesId(taskInfo.priority))

        cancelButton_mod.setOnClickListener(btnListener)
        deleteButton_mod.setOnClickListener(btnListener)
        modifyButton_mod.setOnClickListener(btnListener)
    }
}
