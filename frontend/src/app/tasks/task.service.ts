import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Task } from './task.model';
import { map } from 'rxjs/operators';
import { Component, OnInit, Output, EventEmitter } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class TaskService {

  onTaskAdded = new EventEmitter<Task>();

  constructor(private http: HttpClient) { }

  getTasks() {
    return this.http.get<Task[]>('/api/tasks', { observe: 'response' }).pipe(
      map(response => response.body)
    );
  }

  saveTask(task: Task, checked: boolean) {
    task.completed = checked;
    return this.http.post('/api/tasks/save', task, { observe: 'response' }).pipe(
      map(response => response.body)
    );
  }
  addTask(task: Task) {
    return this.http.post<Task>('/api/tasks/save', task, { observe: 'response' }).pipe(
      map(response => response.body)
    );
  }
}
