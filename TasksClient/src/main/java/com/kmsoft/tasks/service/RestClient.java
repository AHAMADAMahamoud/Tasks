package com.kmsoft.tasks.service;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.ConnectException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.io.IOUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.kmsoft.tasks.model.Task;
import com.kmsoft.tasks.utils.DateUtil;

public class RestClient {

	public CloseableHttpResponse get(String path) throws Exception {
		CloseableHttpClient httpclient = HttpClients.createDefault();

		URI uri;
		try {
			uri = new URIBuilder().setScheme("http").setHost("localhost:8081").setPath("/api" + path)
					// .setParameter("oq", "")
					.build();

			HttpGet httpget = new HttpGet(uri);
			return httpclient.execute(httpget);

		} catch (URISyntaxException | IOException e) {
			throw e;
		}
	}

	public List<Task> getList(String path) throws Exception {
		CloseableHttpResponse response = get(path);
		List<Task> tasks = new ArrayList<>();
		String result = "";
		if (response != null && response.getStatusLine().getStatusCode() == 200) {

			try {
				result = IOUtils.toString(response.getEntity().getContent(), StandardCharsets.UTF_8);
			} catch (UnsupportedOperationException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		JSONArray resultArray = new JSONArray(result);
		for (int i = 0; i < resultArray.length(); i++) {
			JSONObject obj = resultArray.getJSONObject(i);

			Task task = new Task(obj.getInt("id"), getIfNotNull(obj.getString("name")),
					DateUtil.parse(getIfNotNull(obj.getString("dueDate"))), getIfNotNull(obj.getBoolean("completed")));
			tasks.add(task);
		}

		return tasks;

	}

	public CloseableHttpResponse post(String path, String jsonData) {
		CloseableHttpClient httpclient = HttpClients.createDefault();

		URI uri;
		try {
			uri = new URIBuilder().setScheme("http").setHost("localhost:8081").setPath("/api" + path).build();
			HttpPost httpPost = new HttpPost(uri);
			if (jsonData != null && !jsonData.isBlank()) {
				StringEntity entity = new StringEntity(jsonData,StandardCharsets.UTF_8);
				// entity.setContentType("application/json");
				httpPost.setEntity(entity);
			}
				httpPost.setHeader("Accept", "application/json;");
				httpPost.setHeader("Content-type", "application/json");
			
			return httpclient.execute(httpPost);

		} catch (URISyntaxException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	

	public Task postData(String path, String jsonData) {
		CloseableHttpResponse response = post(path, jsonData);
		String result = "";
		if (response != null && response.getStatusLine().getStatusCode() == 200) {

			try {
				result = IOUtils.toString(response.getEntity().getContent(), StandardCharsets.UTF_8);
			} catch (UnsupportedOperationException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		JSONObject obj = new JSONObject(result);

		Task task = new Task(obj.getInt("id"), getIfNotNull(obj.getString("name")),
				DateUtil.parse(getIfNotNull(obj.getString("dueDate"))), getIfNotNull(obj.getBoolean("completed")));
		return task;

	}

	private static String getIfNotNull(String input) {
		if (input != null && !input.isEmpty()) {
			return input.equalsIgnoreCase("null") ? "" : input;
		}
		return "";
	}

	private static boolean getIfNotNull(Boolean input) {
		return (input != null) && (input == true);
	}

	private static Integer getIfNotNull(Integer input) {
		return (input != 0) && (input > 0) ? input : 0;
	}

	public String deleteData(String path) {
		String result="";
		CloseableHttpResponse response = post(path, null);
		if (response != null && response.getStatusLine().getStatusCode() == 200) {

			try {
				result = IOUtils.toString(response.getEntity().getContent(), StandardCharsets.UTF_8);
			} catch (UnsupportedOperationException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}

}
