package com.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int option;
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println("\n=== Calculadora ===");
            System.out.println("Escolha a operação: ");

            for (Operation op : Operation.values()) {
                System.out.println(op.getCode() + " - " + op.getDescription());
            }

            System.out.print("Operação: ");
            option = scanner.nextInt();
            scanner.nextLine();

            if (option == 0) {
                break;
            }

            try {
                Operation op = Operation.codeOption(option)
                        .orElseThrow(() -> new IllegalArgumentException("Opção inválida"));

                System.out.print("Digite os números separados por vírgula: ");
                String entry = scanner.nextLine();

                double result = op.conversion(entry);

                if (result == (int) result) {
                    System.out.println("Resultado: " + (int) result);
                } else {
                    System.out.println("Resultado: " + result);
                }

            } catch (NumberFormatException e) {
                System.out.println("Erro: Digite apenas números separados por vírgula");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }

        } while (option != 0);

        System.out.println("Calculadora encerrada!");
        scanner.close();
    }
}