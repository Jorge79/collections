package com.example;

import java.util.LinkedHashMap;
import java.util.Map;

public abstract class DataProcessor {

	protected TypeConverter typeConverter;
	protected Map<String, Object> allData;

	public DataProcessor() {
		this.typeConverter = new TypeConverter();
		this.allData = new LinkedHashMap<>();
	}

	public boolean processInput(String input) {
		if (input == null || input.isEmpty()) {
			return false;
		}

		String[] parts = input.split(";");

		if (parts.length != 3) {
			System.out.println("Formato inválido (use: nome;valor;tipo)");
			return false;
		}

		String name = parts[0].trim();
		String valueStr = parts[1].trim();
		String type = parts[2].trim();

		try {
			Object value = typeConverter.convertValue(valueStr, type);
			allData.put(name, value);
			System.out.println("Campo '" + name + "' adicionado");
			return true;
		} catch (Exception e) {
			System.out.println("Erro ao converter: " + e.getMessage());
			return false;
		}
	}

	public boolean hasData() {
		return !allData.isEmpty();
	}

	public Map<String, Object> getData() {
		return allData;
	}

	public String format() {
		return format(allData);
	}

	public abstract String format(Map<String, Object> data);
}