import { Component, OnInit } from '@angular/core';
import { TaskService } from '../task.service';
import { Task } from '../task.model';
import { formatDate } from '@angular/common';

@Component({
  selector: 'app-tasks-add',
  templateUrl: './tasks-add.component.html',
  styleUrls: ['./tasks-add.component.css']
})
export class TasksAddComponent implements OnInit {

  addTaskValue: string = null;

  constructor(private taskService: TaskService) { }

  ngOnInit() {
  }

  onTaskAdd(event) {
    let task = new Task(event.target.value, false, this.getToDayAsString())
    this.taskService.addTask(task).subscribe(task => {
      this.addTaskValue = '';
      this.taskService.onTaskAdded.emit(task)

    },
      err => console.log(err)
    );
  }
  getToDayAsString() {
    return formatDate(new Date(), 'MM/dd/yyyy', 'en');
  }
}
