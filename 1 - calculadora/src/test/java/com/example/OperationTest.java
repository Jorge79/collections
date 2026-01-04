package com.example;

import org.junit.jupiter.api.Test;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

class OperationTest {

	@Test
	void testCodeOptionValid_Sum() {
		int code = 1;
		Optional<Operation> result = Operation.codeOption(code);

		assertTrue(result.isPresent(), "Deveria encontrar a operação SOMA");
		assertEquals(Operation.SUM, result.get());
	}

	@Test
	void testCodeOptionValid_Subtraction() {
		int code = 2;
		Optional<Operation> result = Operation.codeOption(code);

		assertTrue(result.isPresent(), "Deveria encontrar a operação SUBTRAÇÃO");
		assertEquals(Operation.SUBTRACTION, result.get());
	}

	@Test
	void codeOptionValid_Exit() {
		int code = 0;
		Optional<Operation> result = Operation.codeOption(code);

		assertTrue(result.isPresent(), "Deveria encontrar a operação SAIR");
		assertEquals(Operation.EXIT, result.get());
	}

	@Test
	void testCodeOptionInvalid() {
		int code = 99;
		Optional<Operation> result = Operation.codeOption(code);

		assertTrue(result.isEmpty(), "Não deveria encontrar operação com código 99");
	}

	@Test
	void testSimpleSum() {
		double[] numbers = { 10, 5, 3 };
		double result = Operation.SUM.calculate(numbers);

		assertEquals(18, result, "A soma de 10, 5 e 3 deveria ser 18");
	}

	@Test
	void testSimpleSubtraction() {
		double[] numbers = { 10, 5, 3 };
		double result = Operation.SUBTRACTION.calculate(numbers);

		assertEquals(2, result, "A subtração de 10, 5 e 3 deveria ser 2");
	}

	@Test
	void testSimpleSumWithNegatives() {
		double[] numbers = { 10, -4 };
		double result = Operation.SUM.calculate(numbers);

		assertEquals(6, result, "A soma de 10 e -4 deveria ser 6");
	}

	@Test
	void testSimpleSubtractionWithNegatives() {
		double[] numbers = { 10, -4 };
		double result = Operation.SUBTRACTION.calculate(numbers);

		assertEquals(14, result, "A subtração de 10 e -4 deveria ser 14");
	}

	@Test
	void testSumWithDecimals() {
		double[] numbers = { 5.5, 4.5 };
		double result = Operation.SUM.calculate(numbers);

		assertEquals(10.0, result, "A soma de 5.5 e 4.5 deveria ser 10.0");
	}

	@Test
	void testSubtractionWithDecimals() {
		double[] numbers = { 5.5, 2.5 };
		double result = Operation.SUBTRACTION.calculate(numbers);

		assertEquals(3.0, result, "A subtração de 5.5 e 2.5 deveria ser 3.0");
	}

	@Test
	void testSomaUmNumero() {
		double[] numbers = { 42 };

		double result = Operation.SUM.calculate(numbers);

		assertEquals(42, result, "Soma de um único número deveria retornar ele mesmo");
	}

	@Test
	void testSomaArrayVazio() {
		double[] numbers = {};

		double result = Operation.SUM.calculate(numbers);

		assertEquals(0, result, "Soma de array vazio deveria ser 0");
	}
}