package com.kmsoft.task.domain;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 
 * (Description)
 *
 * @since 18 juil. 2019
 * @author mohamed.hanafi
 */
@Entity
public class Task {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @JsonFormat(pattern = "MM/dd/yyyy")
    private LocalDate dueDate;
    private Boolean completed;

    public Task() {
    }

    public Task(Long id, String name, LocalDate dueDate, Boolean completed) {
        super();
        this.id = id;
        this.name = name;
        this.dueDate = dueDate;
        this.completed = completed;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDueDate() {
        return this.dueDate;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("{ \"id\" : ").append(id).append(",");
        builder.append("\"name\" : ").append("\"").append(name).append("\"").append(",");
        builder.append("\"dueDate\" : ").append("\"").append(dueDate).append("\"").append(",");
        builder.append("\"completed\" : ").append("\"").append(completed).append("\"");
        builder.append("}");
        return builder.toString();
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public Boolean getCompleted() {
        return this.completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

}
