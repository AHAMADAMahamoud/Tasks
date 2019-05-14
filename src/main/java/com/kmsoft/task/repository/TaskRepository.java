package com.kmsoft.task.repository;

import org.springframework.data.repository.CrudRepository;

import com.kmsoft.task.domain.Task;

public interface TaskRepository extends CrudRepository<Task, Long> {

}
