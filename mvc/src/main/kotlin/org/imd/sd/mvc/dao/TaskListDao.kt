package org.imd.sd.mvc.dao

import org.imd.sd.mvc.model.TaskList

/**
 * created by imd on 13.12.2020
 */

interface TaskListDao {
    fun addList(list: TaskList): String?

    fun removeList(id: String)

    fun removeTask(id: String, listId: String)

    fun addTask(id: String, listId: String)

    fun getLists(): List<TaskList>


}