package edu.Project1.input;

import java.util.Scanner;

@SuppressWarnings("checkstyle:RegexpSinglelineJava")
public class CliReader implements Reader {
    private static final Scanner INPUT = new Scanner(System.in);


    @Override
    public String getLetter() {
        System.out.println("Guess a letter:");
        return INPUT.next();
    }
}
