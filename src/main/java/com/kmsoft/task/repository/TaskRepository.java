package com.kmsoft.task.repository;

import org.springframework.data.repository.CrudRepository;

import com.kmsoft.task.domain.Task;

/**
 * 
 * (Description)
 *
 * @since 18 juil. 2019
 * @author mohamed.hanafi
 */
public interface TaskRepository extends CrudRepository<Task, Long> {

}
