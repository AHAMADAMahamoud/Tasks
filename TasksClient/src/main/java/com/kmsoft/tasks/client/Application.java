package com.kmsoft.tasks.client;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import com.kmsoft.tasks.model.Task;
import com.kmsoft.tasks.model.TasksDataModel;
import com.kmsoft.tasks.service.RestClient;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;

public class Application extends JFrame {

	private JPanel contentPane;
	private JTextField tasksNameField;
	private JTable tableTasks;

	
	private static String windowsTitle = "Java Swing Tasks client for RestFull Api";
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Application window = new Application();
					window.setTitle(windowsTitle);
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Application() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 530, 379);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		tasksNameField = new JTextField();
		tasksNameField.setBounds(29, 75, 367, 33);
		contentPane.add(tasksNameField);
		tasksNameField.setColumns(10);
		
		JButton btnSave = new JButton("Save");
		btnSave.setBounds(408, 75, 78, 33);
		contentPane.add(btnSave);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(29, 119, 457, 196);
		contentPane.add(scrollPane);
		
		tableTasks = new JTable();
		
		List<Task> taskData=new ArrayList<>();
	
		RestClient client=new RestClient();
		
		try {
			taskData=client.getList("/tasks/");
			TasksDataModel taskModel=new TasksDataModel(taskData.size(),4);
			taskModel.setData(taskData);
			tableTasks.setModel(taskModel);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
		
			
		tableTasks.getColumnModel().getColumn(0).setPreferredWidth(35);
		tableTasks.getColumnModel().getColumn(1).setPreferredWidth(286);
		tableTasks.getColumnModel().getColumn(2).setPreferredWidth(78);
		tableTasks.getColumnModel().getColumn(3).setPreferredWidth(23);
		scrollPane.setViewportView(tableTasks);
	}
}
