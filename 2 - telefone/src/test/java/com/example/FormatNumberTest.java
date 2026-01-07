package com.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Optional;

public class FormatNumberTest {

	private FormatNumber formatter;

	@BeforeEach
	public void setUp() {
		formatter = new FormatNumber();
	}

	@Test
	public void testFixoSemDDD_8Digitos() {
		String input = "12345678";

		Optional<String> result = formatter.verifyNumber(input);

		assertTrue(result.isPresent());
		assertEquals("1234-5678", result.get());
	}

	@Test
	public void testCelularSemDDD_9Digitos() {
		String input = "987654321";

		Optional<String> result = formatter.verifyNumber(input);

		assertTrue(result.isPresent());
		assertEquals("98765-4321", result.get());
	}

	@Test
	public void testFixoComDDD_10Digitos() {
		String input = "1133334444";

		Optional<String> result = formatter.verifyNumber(input);

		assertTrue(result.isPresent());
		assertEquals("(11)3333-4444", result.get());
	}

	@Test
	public void testCelularComDDD_11Digitos() {
		String input = "11987654321";

		Optional<String> result = formatter.verifyNumber(input);

		assertTrue(result.isPresent());
		assertEquals("(11)98765-4321", result.get());
	}

	@Test
	public void testEntradaComLetras() {
		String input = "abc11987654321xyz";

		Optional<String> result = formatter.verifyNumber(input);

		assertTrue(result.isPresent());
		assertEquals("(11)98765-4321", result.get());
	}

	@Test
	public void testEntradaComCaracteresEspeciais() {
		String input = "(11) 98765-4321";

		Optional<String> result = formatter.verifyNumber(input);

		assertTrue(result.isPresent());
		assertEquals("(11)98765-4321", result.get());
	}

	@Test
	public void testEntradaComPontos() {
		String input = "11.98765.4321";

		Optional<String> result = formatter.verifyNumber(input);

		assertTrue(result.isPresent());
		assertEquals("(11)98765-4321", result.get());
	}

	@Test
	public void testNumeroMuitoCurto() {
		String input = "123";

		Optional<String> result = formatter.verifyNumber(input);

		assertFalse(result.isPresent());
	}

	@Test
	public void testNumeroMuitoLongo() {
		String input = "123456789012";

		Optional<String> result = formatter.verifyNumber(input);

		assertFalse(result.isPresent());
	}

	@Test
	public void testEntradaSemNumeros() {
		String input = "abcdefgh";

		Optional<String> result = formatter.verifyNumber(input);

		assertFalse(result.isPresent());
	}

	@Test
	public void testEntradaVazia() {
		String input = "";

		Optional<String> result = formatter.verifyNumber(input);

		assertFalse(result.isPresent());
	}

	@Test
	public void testEntradaNull() {
		String input = null;

		assertThrows(NullPointerException.class, () -> {
			formatter.verifyNumber(input);
		});
	}

	@Test
	public void testNumeroComEspacos() {
		String input = "11 9 8765 4321";

		Optional<String> result = formatter.verifyNumber(input);

		assertTrue(result.isPresent());
		assertEquals("(11)98765-4321", result.get());
	}

	@Test
	public void testFixoComDDDECaracteres() {
		String input = "abc(11)3333-4444xyz";

		Optional<String> result = formatter.verifyNumber(input);

		assertTrue(result.isPresent());
		assertEquals("(11)3333-4444", result.get());
	}

	@Test
	public void testSomenteZeros() {
		String input = "00000000";

		Optional<String> result = formatter.verifyNumber(input);

		assertTrue(result.isPresent());
		assertEquals("0000-0000", result.get());
	}
}