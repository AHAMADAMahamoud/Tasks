package com.kmsoft.tasks.model;

import java.util.List;

import javax.swing.table.AbstractTableModel;

public class TasksDataModel extends AbstractTableModel {

	Object[][] data ;
	public TasksDataModel(int rows, int cols) {
		super();
	this.data = new Object[rows][cols];
	}

	String[] columnNames = { "Id", "Task Name", "Due Date", "" };

	

	public int getColumnCount() {
		return columnNames.length;
	}

	public int getRowCount() {
		return data.length;
	}

	public String getColumnName(int col) {
		return columnNames[col];
	}

	public Object getValueAt(int row, int col) {
		return data[row][col];
	}

	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}

	public boolean isCellEditable(int row, int col) {
		if (col < 1) {
			return false;
		} else {
			return true;
		}
	}

	public void setValueAt(Object value, int row, int col) {

		data[row][col] = value;
		fireTableCellUpdated(row, col);
	}
	
	public void setData(List<Task> taskData) {

		for(int i=0; i<taskData.size(); i++) {
			Task task=taskData.get(i);
			data[i][0]=task.getId();
			data[i][1]=task.getName();
			data[i][2]=task.getDueDate();
			data[i][3]=task.getCompleted();
		}
		
	}

}