package com.example;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TypeConverter {

	private Map<String, Function<String, Object>> converters;

	private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private static final DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

	public TypeConverter() {
		converters = new HashMap<>();

		converters.put("texto", str -> str);
		converters.put("inteiro", str -> Integer.parseInt(str));
		converters.put("float", str -> Double.parseDouble(str));
		converters.put("booleano", str -> Boolean.parseBoolean(str));
		converters.put("data", str -> parseDate(str));
		converters.put("datahora", str -> parseDateTime(str));
	}

	public Object convertValue(String valueStr, String type) {
		type = type.toLowerCase();

		boolean isArray = type.startsWith("array:");
		String baseType = isArray ? type.substring(6) : type;
		Function<String, Object> converter = converters.getOrDefault(baseType, str -> str);

		Object value;
		if (isArray) {
			value = convertArrayOfType(valueStr, converter);
		} else {
			value = converter.apply(valueStr);
		}

		return value;
	}

	private List<Object> convertArrayOfType(String str, Function<String, Object> converter) {
		return Arrays.stream(str.split(","))
				.map(String::trim)
				.map(converter)
				.collect(Collectors.toList());
	}

	private String parseDate(String dateStr) {
		try {
			LocalDate date = LocalDate.parse(dateStr, DATE_FORMATTER);
			return date.format(DATE_FORMATTER);
		} catch (DateTimeParseException e) {
			throw new IllegalArgumentException("Formato de data inválido. Use: dd/MM/yyyy (ex: 25/12/2024)");
		}
	}

	private String parseDateTime(String dateTimeStr) {
		try {
			LocalDateTime dateTime = LocalDateTime.parse(dateTimeStr, DATETIME_FORMATTER);
			return dateTime.format(DATETIME_FORMATTER);
		} catch (DateTimeParseException e) {
			throw new IllegalArgumentException(
					"Formato de data/hora inválido. Use: dd/MM/yyyy HH:mm:ss (ex: 25/12/2024 14:30:00)");
		}
	}
}