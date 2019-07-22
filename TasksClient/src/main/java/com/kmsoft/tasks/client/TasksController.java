package com.kmsoft.tasks.client;

import javafx.scene.control.TextField;

import java.net.ConnectException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.kmsoft.tasks.model.Task;
import com.kmsoft.tasks.service.RestClient;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class TasksController {
	@FXML
	private TableView<Task> tasksTable;
	@FXML
	private TableColumn<Task, Number> taskIdColumn;
	@FXML
	private TableColumn<Task, String> taskNameColumn;
	@FXML
	private TableColumn<Task, LocalDate> taskDueDateColumn;
	@FXML
	private TableColumn<Task, String> taskCompletedColumn;

	@FXML
	private TextField taskNameField;
	private RestClient client;
	private ObservableList<Task> taskData = FXCollections.observableArrayList();

	public TasksController() {
		client = new RestClient();
		try {
			for (Task task : client.getList("/tasks")) {
				this.taskData.add(task);
			}
		} catch (Exception e) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText(e.getMessage());
			alert.show();
		}

	}

	/**
	 * Initializes the controller class. This method is automatically called after
	 * the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {
		// Initialize the person table with the two columns.
		taskIdColumn.setCellValueFactory(cellData -> cellData.getValue().getIdProperty());
		taskNameColumn.setCellValueFactory(cellData -> cellData.getValue().getNameProperty());
		taskDueDateColumn.setCellValueFactory(cellData -> cellData.getValue().getDueDateProperty());
		taskCompletedColumn.setCellValueFactory(cellData -> cellData.getValue().getCompletedProperty());

		tasksTable.setItems(taskData);

	}

	@FXML
	private void handleTableclick() {
		if (tasksTable.getSelectionModel().getSelectedItem() != null) {
			showUpdateDialogu(tasksTable.getSelectionModel().getSelectedItem());
		}
	}

	private void showUpdateDialogu(Task task) {
		final String[] arrayData = { "Completed", "Not completed", "Delete Task" };
		List<String> dialogData = Arrays.asList(arrayData);

		ChoiceDialog<String> dialog = new ChoiceDialog<String>(dialogData.get(0), dialogData);
		dialog.setTitle("Update doalogue");
		dialog.setHeaderText("Select your action");

		Optional<String> result = dialog.showAndWait();

		if (result.isPresent()) {
			String action = result.get();
			if (action.equalsIgnoreCase("Completed")) {
				task.setCompleted(true);
				saveTask(task);
			} else if (action.equalsIgnoreCase("Not completed")) {
				task.setCompleted(false);
				saveTask(task);
			} else {
				deleteTask(task);
			}
		}

		dialog.close();

	}

	@FXML
	private void handleNewTask() {
		String taskName = taskNameField.getText();
		if (taskName != null && !taskName.isBlank()) {
			Task task = new Task(taskName, false);
			saveTask(task);
		} else {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("Save Task");
			alert.setHeaderText("Incorrect task name");
			alert.show();
		}
		taskNameField.setText("");
	}

	private void saveTask(Task task) {
		if (task.getId() > 0) {
			client.postData("/tasks/save", task.toString());
		} else {
			this.taskData.add(0, client.postData("/tasks/save", task.toString()));		
		}
		initTableview();
	}

	private void deleteTask(Task task) {
		String deletedTask = client.deleteData("/tasks/delete/" + task.getId());
		System.out.println(deletedTask);

		taskData.remove(task);
		initTableview();
	}

	private void initTableview() {
		tasksTable.setItems(taskData);
		tasksTable.refresh();

	}
}
