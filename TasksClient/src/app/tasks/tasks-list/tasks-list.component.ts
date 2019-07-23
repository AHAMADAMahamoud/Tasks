import { Component, OnInit } from '@angular/core';
import { Task } from '../task.model';
import { TaskService } from '../task.service';

import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-tasks-list',
  templateUrl: './tasks-list.component.html',
  styleUrls: ['./tasks-list.component.css']
})
export class TasksListComponent implements OnInit {

  tasks: Task[] = [];

  constructor(private taskService: TaskService) { }

  ngOnInit() {
    this.taskService.getTasks().subscribe(tasks => {
      this.tasks = tasks;
    }, (error: HttpErrorResponse) => {
      console.log("Erreur ", error)
    });


    this.taskService.onTaskAdded.subscribe(
      (task: Task) => this.tasks.push(task)
    )
  }

  getDueDateLabel(task: Task) {
    return task.completed ? 'badge-success' : 'badge-primary';
  }

  onTaskChange(event, task: Task) {
    this.taskService.saveTask(task, event.target.checked)
      .subscribe(data => {
      },
        err => console.log(err)
      );
  }

  onDelClicked(event, task: Task) {
    this.taskService.delTask(task.id)
      .subscribe(data => {
      },
        err => console.log(err)
      );
      this.tasks.splice(this.tasks.indexOf(task), 1);
      return this.tasks;
  }
}
