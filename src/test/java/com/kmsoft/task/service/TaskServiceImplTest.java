package com.kmsoft.task.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.kmsoft.task.AbstractTest;
import com.kmsoft.task.domain.Task;

/**
 * 
 * (Description)
 *
 * @since 18 juil. 2019
 * @author mohamed.hanafi
 */
public class TaskServiceImplTest extends AbstractTest {

    @Autowired
    TaskService taskService;
    private List<Task> list = new ArrayList<>();

    @Before
    public void setUp() throws Exception {
        taskService.save(new Task(1L, "Definir les fonctionalités attendues", LocalDate.now(), false));
        taskService.save(new Task(2L, "Faire la conception technique", LocalDate.now(), false));
        taskService.save(new Task(3L, "Preparer l'environnement de travail", LocalDate.now(), true));
        taskService.save(new Task(4L, "Créer l'architecture du projet et des bases de données ", LocalDate.now(), false));
        this.taskService.list().iterator().forEachRemaining(task -> {
            list.add(task);
        });
    }

    @After
    public void tearDown() throws Exception {
        list.forEach(task -> {
            taskService.delete(task.getId());
        });

    }

    @Test
    public void listNotEmpty() {
        assertTrue(!list.isEmpty());
    }

    @Test
    public void updatePerformed() {

        Task task = new Task(1L, "Write Unit Test using Junit 5 and mockito", LocalDate.now(), false);
        this.taskService.save(task);
        List<Task> list1 = new ArrayList<>();
        this.taskService.list().iterator().forEachRemaining(task1 -> {
            list1.add(task1);
        });
        assertTrue(list.size() < list1.size());

    }

    @Test
    public void savePerformed() {

        Task task = new Task(5L, "Write Unit Test using Junit 5 and mockito", LocalDate.now(), false);
        this.taskService.save(task);
        List<Task> list1 = new ArrayList<>();
        this.taskService.list().iterator().forEachRemaining(task1 -> {
            list1.add(task1);
        });
        assertFalse(list.size() < list1.size());

    }

}