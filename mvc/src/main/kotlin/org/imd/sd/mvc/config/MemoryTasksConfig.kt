package org.imd.sd.mvc.config

import org.imd.sd.mvc.dao.TaskListMemoryDao
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * created by imd on 13.12.2020
 */

@Configuration
open class MemoryTasksConfig {

    @Bean
    open fun memoryDao() = TaskListMemoryDao()
}