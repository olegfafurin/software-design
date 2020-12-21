package org.imd.sd.mvc.dao

import org.imd.sd.mvc.model.Task
import org.imd.sd.mvc.model.TaskList

/**
 * created by imd on 13.12.2020
 */


class TaskListMemoryDao : TaskListDao {

    private val taskLists: MutableMap<String, TaskList> = mutableMapOf()


    override fun addList(list: TaskList): String? = synchronized(taskLists) {
        if (taskLists.putIfAbsent(list.name, list) != null) return null
        return list.name
    }

    override fun removeList(id: String) {
        synchronized(taskLists) {
            taskLists.remove(id)
        }
    }

    override fun removeTask(id: String, listId: String) {
        synchronized(taskLists) {
            taskLists[listId]?.data?.removeIf { it.title == id }
        }
    }

    override fun addTask(id: String, listId: String) {
        synchronized(taskLists) {
            if (taskLists[listId]?.data?.any { it.title == id } == false)
                taskLists[listId]?.data?.add(Task(id))
        }
    }

    override fun getLists(): List<TaskList> {
        return taskLists.values.toList()
    }
}