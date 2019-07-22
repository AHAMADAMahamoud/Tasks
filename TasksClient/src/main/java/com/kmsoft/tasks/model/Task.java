package com.kmsoft.tasks.model;

import java.time.LocalDate;

import com.kmsoft.tasks.utils.DateUtil;
import com.kmsoft.tasks.utils.ToStringBuilder;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class Task {

	private final IntegerProperty id;
	private final StringProperty name;
	private final ObjectProperty<LocalDate> dueDate;
	private final BooleanProperty completed;

	public Task(String name, boolean dueDate) {
		super();
		this.id = new SimpleIntegerProperty();
		this.name = new SimpleStringProperty(name);
		this.dueDate = new SimpleObjectProperty<LocalDate>(LocalDate.now());
		this.completed = new SimpleBooleanProperty(false);
	}

	public Task(int id, String name, LocalDate dueDate, boolean completed) {
		super();
		this.id = new SimpleIntegerProperty(id);
		this.name = new SimpleStringProperty(name);
		this.dueDate = new SimpleObjectProperty<LocalDate>(dueDate);
		this.completed = new SimpleBooleanProperty(completed);
	}

	public IntegerProperty getIdProperty() {
		return id;
	}

	public StringProperty getNameProperty() {
		return name;
	}

	public ObjectProperty<LocalDate> getDueDateProperty() {
		return dueDate;
	}

	public StringProperty getCompletedProperty() {
		if(this.completed.get())
			return new SimpleStringProperty("✓");
		return new SimpleStringProperty("x");
	}

	
	
	public int getId() {
		return id.get();
	}

	public String getName() {
		return name.get();
	}

	public Boolean getCompleted() {
		return completed.get();
	}
	
	public void setId(Integer id) {
		 this.id.set(id);
	}

	public void setName(String name) {
		 this.name.set(name);
	}

	public void setCompleted(boolean completed) {
		 this.completed.set(completed);
	}

	public LocalDate getDueDate() {
		return dueDate.get();
	}

	public void setDueDate(LocalDate birthday) {
		this.dueDate.set(birthday);
	}

	@Override
	public String toString() {
		ToStringBuilder ts = new ToStringBuilder(this);
		ts.append("id", this.getId());
		ts.append("name", this.getName());
		ts.append("dueDate",DateUtil.format(this.getDueDate()));
		ts.append("completed", this.getCompleted());
		return ts.build();
	}

	
}
