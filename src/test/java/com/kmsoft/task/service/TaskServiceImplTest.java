package com.kmsoft.task.service;

import com.kmsoft.task.AbstractTest;
import com.kmsoft.task.TasksApplication;
import com.kmsoft.task.domain.Task;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;


public class TaskServiceImplTest  extends AbstractTest {

    @Mock
    TaskService service;


    @Test
    public  void list() {
        Task task=new Task(1L, "Write Unit Test using Junit 5 and mockito", LocalDate.now(), false);
        this.service.save(task);
        Iterable<Task> list=this.service.list();
        assertTrue(list.iterator().hasNext());

    }

    @Test
    public  void save() {
        Task task=new Task(1L, "Write Unit Test using Junit 5 and mockito", LocalDate.now(), false);

        when(this.service.save(task)).thenReturn(task);

    }
}