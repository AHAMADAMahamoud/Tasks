package com.kmsoft.task.service;

import com.kmsoft.task.domain.Task;

/**
 * 
 * (Description)
 *
 * @since 18 juil. 2019
 * @author mohamed.hanafi
 */
public interface TaskService {

    Iterable<Task> list();

    Task save(Task task);

    boolean delete(Long id);
}