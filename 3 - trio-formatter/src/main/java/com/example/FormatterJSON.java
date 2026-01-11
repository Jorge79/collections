package com.example;

import java.util.Map;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class FormatterJSON extends DataProcessor {

	private final Gson gson;

	public FormatterJSON() {
		super();
		this.gson = new GsonBuilder().setPrettyPrinting().create();
	}

	@Override
	public String format(Map<String, Object> data) {
		return gson.toJson(data);
	}
}