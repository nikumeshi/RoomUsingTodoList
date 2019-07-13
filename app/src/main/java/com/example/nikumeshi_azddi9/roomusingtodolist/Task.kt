package com.example.nikumeshi_azddi9.roomusingtodolist

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey
    var _id: Int,
    @ColumnInfo(name = "priority")
    var priority: Int,
    @ColumnInfo(name = "title")
    var title: String,
    @ColumnInfo(name = "date")
    var date: String,
    @ColumnInfo(name = "time")
    var time: String,
    @ColumnInfo(name = "detail")
    var detail: String,
    @ColumnInfo(name = "isEnable")
    var isEnable: Boolean = true
) : Serializable

enum class PriorityEnum( val code: Int, val resourcesId: Int, val label: String) {
    IMMEDIATELY( 0 , R.id.rb_Immediately, "!!!" ),
    ASAP( 1 , R.id.rb_ASAP, "!!" ),
    SOON( 2 , R.id.rb_Soon, "!" ),
    ANYTIME( 3 , R.id.rb_AnyTime, " " );
    companion object {
        fun getPriority(resourcesId: Int ) =
            values().find { resourcesId == it.resourcesId }?.code ?:
            PriorityEnum.ANYTIME.code
        fun getLabel(code: Int ) =
            values().find { code == it.code }?.label ?:
            PriorityEnum.ANYTIME.label
        fun getResourcesId(code: Int ) =
            values().find { code == it.code }?.resourcesId ?:
            PriorityEnum.ANYTIME.code
    }
}