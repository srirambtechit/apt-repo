package com.aptikraft.spring.ui.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonResponse {

    private String status = ""; // either success or error
    private List<Map<String, String>> data;

    public JsonResponse(String status) {
	super();
	this.status = status;
	this.data = new ArrayList<>();
    }

    public String getStatus() {
	return status;
    }

    public void setStatus(String status) {
	this.status = status;
    }

    public List<Map<String, String>> getData() {
	return data;
    }

    public void setData(List<Map<String, String>> data) {
	this.data = data;
    }

    @Override
    public String toString() {
	return "JsonResponse [status=" + status + ", data=" + data + "]";
    }

    public static void main(String[] args) {
	JsonResponse j = new JsonResponse("OK");
	j.setData(new ArrayList<Map<String, String>>());
	Map<String, String> map = new HashMap<>();
	map.put("name", "errorMessage");
	map.put("value", "Invalid username or password");
	j.getData().add(map);

	map = new HashMap<>();
	map.put("name", "errorMessage");
	map.put("value", "Invalid username or password");
	j.getData().add(map);
	System.out.println(j);
    }

}