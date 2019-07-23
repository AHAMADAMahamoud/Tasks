package com.kmsoft.tasks.model;

import java.time.LocalDate;

import com.kmsoft.tasks.utils.DateUtil;
import com.kmsoft.tasks.utils.ToStringBuilder;

public class Task {

	private Long id;
	private String name;
	private LocalDate dueDate;
	private Boolean completed;

	public Task() {
	}

	public Task(String name, LocalDate dueDate, Boolean completed) {
		super();
		this.name = name;
		this.dueDate = dueDate;
		this.completed = completed;
	}

	public Task(Long id, String name, LocalDate dueDate, Boolean completed) {
		this.id = id;
		this.name = name;
		this.dueDate = dueDate;
		this.completed = completed;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}

	public Boolean getCompleted() {
		return completed;
	}

	public void setCompleted(Boolean completed) {
		this.completed = completed;
	}

	@Override
	public String toString() {
		ToStringBuilder ts = new ToStringBuilder(this);
		ts.append("id", this.getId());
		ts.append("name", this.getName());
		ts.append("dueDate", DateUtil.format(this.getDueDate()));
		ts.append("completed", this.getCompleted());
		return ts.build();
	}

}
