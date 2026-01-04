package com.example;

import java.util.Optional;

enum Operation {
	SUM(1, "Somar"),
	SUBTRACTION(2, "Subtrair"),
	EXIT(0, "Sair");

	private final int code;
	private final String description;

	Operation(int code, String description) {
		this.code = code;
		this.description = description;
	}

	public int getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}

	public static Optional<Operation> codeOption(int code) {
		for (Operation op : Operation.values()) {
			if (op.getCode() == code) {
				return Optional.of(op);
			}
		}
		return Optional.empty();
	}

	public double conversion(String entry) {
		String[] parts = entry.split(",");
		double[] numbers = new double[parts.length];

		for (int i = 0; i < parts.length; i++) {
			numbers[i] = Double.parseDouble(parts[i]);
		}

		return calculate(numbers);
	}

	public double calculate(double[] numbers) {
		if (this == EXIT) {
			throw new UnsupportedOperationException("Operação de saída não suportada");
		}

		if (numbers.length == 0)
			return 0;

		double result = numbers[0];

		for (int i = 1; i < numbers.length; i++) {
			switch (this) {
				case SUM:
					result += numbers[i];
					break;
				case SUBTRACTION:
					result -= numbers[i];
					break;
				default:
					break;
			}
		}

		return result;
	}
}