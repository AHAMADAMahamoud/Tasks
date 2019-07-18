package com.kmsoft.task.service;

import org.springframework.stereotype.Service;

import com.kmsoft.task.domain.Task;
import com.kmsoft.task.repository.TaskRepository;

/**
 * 
 * (Description)
 *
 * @since 18 juil. 2019
 * @author mohamed.hanafi
 */
@Service
public class TaskServiceImpl implements TaskService {

    private TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskREpository) {
        super();
        this.taskRepository = taskREpository;
    }

    @Override
    public Iterable<Task> list() {
        return this.taskRepository.findAll();
    }

    @Override
    public Task save(Task task) {
        return this.taskRepository.save(task);
    }

    @Override
    public boolean delete(Long task) {
        try {
            this.taskRepository.deleteById(task);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
