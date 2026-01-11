package com.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        FormatterJSON formatter = new FormatterJSON();
        Scanner scanner = new Scanner(System.in);

        String input;
        do {
            System.out.print("> ");
            input = scanner.nextLine();

            if (!input.isEmpty()) {
                formatter.processInput(input);
            }

        } while (!input.isEmpty());

        if (formatter.hasData()) {
            String json = formatter.format();
            System.out.println(json);

            FormatterXML xmlFormatter = new FormatterXML();
            String xml = xmlFormatter.format(formatter.getData());
            System.out.println(xml);
        }
        scanner.close();
    }
}