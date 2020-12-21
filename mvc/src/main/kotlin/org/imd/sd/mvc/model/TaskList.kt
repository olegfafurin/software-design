package org.imd.sd.mvc.model

/**
 * created by imd on 13.12.2020
 */

data class TaskList(var name: String, var data: MutableList<Task> = mutableListOf())