package com.kmsoft.task.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public List<Task> list() {
        List<Task> list = (List<Task>) this.taskRepository.findAll();
        list.sort((o1, o2) -> {
            return (o1.getId().compareTo(o2.getId()));
        });
        return list;

    }

    @Override
    public Task save(Task task) {
        return this.taskRepository.save(task);
    }

    @Override
    public Map<String, String> delete(Long task) {
        Map<String, String> result = new HashMap<String, String>();
        try {
            this.taskRepository.deleteById(task);
            result.put("message", "Object deleted succeful");
            result.put("error", "0");
            return result;
        } catch (Exception e) {
            result.put("message", e.getMessage());
            result.put("error", "1");
            return result;
        }
    }

}
