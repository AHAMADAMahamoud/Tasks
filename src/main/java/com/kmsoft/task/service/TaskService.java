package com.kmsoft.task.service;

import com.kmsoft.task.domain.Task;

public interface TaskService {

	Iterable<Task> list();

	Task save(Task task);

	boolean delete(Long id);
}