package com.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        FormatNumber number = new FormatNumber();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite um número de telefone: ");
        String testNumber = scanner.nextLine();

        number.verifyNumber(testNumber)
                .ifPresentOrElse(
                        num -> System.out.println("Número formatado: " + num),
                        () -> System.out.println("Número inválido"));
        scanner.close();
    }
}