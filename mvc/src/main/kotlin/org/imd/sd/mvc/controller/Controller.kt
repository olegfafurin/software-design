package org.imd.sd.mvc.controller

import org.imd.sd.mvc.dao.TaskListDao
import org.imd.sd.mvc.model.TaskList
import org.springframework.stereotype.Controller
import org.springframework.ui.ModelMap
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam

/**
 * created by imd on 13.12.2020
 */
@Controller
class Controller(private val dao: TaskListDao) {

    @RequestMapping(value = ["/add-tasklist"], method = [RequestMethod.POST])
    fun addTaskList(@RequestParam("newTaskListName") newTaskListName: String): String {
        dao.addList(TaskList(newTaskListName))
        return "redirect:/get-tasklists"
    }

    @RequestMapping(value = ["/delete-tasklist"], method = [RequestMethod.POST])
    fun deleteTaskList(@RequestParam("deleteTaskListName") deleteTaskListName: String): String {
        dao.removeList(deleteTaskListName)
        return "redirect:/get-tasklists"
    }

    @RequestMapping(value = ["/delete-task"], method = [RequestMethod.POST])
    fun deleteTask(
        @RequestParam("deleteTaskName") deleteTaskName: String,
        @RequestParam("deleteTaskListName") deleteTaskListName: String
    ): String {
        dao.removeTask(deleteTaskName, deleteTaskListName)
        return "redirect:/get-tasklists"
    }

    @RequestMapping(value = ["/add-task"], method = [RequestMethod.POST])
    fun addTask(
        @RequestParam("addTaskName") addTaskName: String,
        @RequestParam("addTaskListName") addTaskListName: String
    ): String {
        dao.addTask(addTaskName, addTaskListName)
        return "redirect:/get-tasklists"
    }

    @RequestMapping(value = ["/get-tasklists"], method = [RequestMethod.GET])
    fun getLists(map: ModelMap): String {
        prepareModelMap(map, dao.getLists())
        return "index"
    }

    private fun prepareModelMap(map: ModelMap, lists: List<TaskList>) {
        map.addAttribute("lists", lists)
//        map.addAttribute("listToAdd", TaskList("defaultList"))
    }
}