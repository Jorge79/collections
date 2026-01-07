package com.example;

import java.util.Optional;

public class FormatNumber {
	public Optional<String> verifyNumber(String number) {
		StringBuilder result = new StringBuilder();

		for (char charactere : number.toCharArray()) {
			if (Character.isDigit(charactere)) {
				result.append(charactere);
			}
		}

		if (result.length() < 8 || result.length() > 11) {
			return Optional.empty();
		}

		if (result.length() == 10 || result.length() == 11) {
			insertDDD(result);
		}

		insertHifen(result);
		return Optional.of(result.toString());
	}

	public void insertHifen(StringBuilder number) {
		number.insert(number.length() - 4, "-");
	}

	public void insertDDD(StringBuilder number) {
		number.insert(0, "(");
		number.insert(3, ")");
	}
}
