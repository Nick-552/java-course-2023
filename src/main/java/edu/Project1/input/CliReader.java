package edu.Project1.input;

import java.util.Scanner;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("checkstyle:RegexpSinglelineJava")
public class CliReader implements Reader {
    private static final Scanner INPUT = new Scanner(System.in);


    @Override
    public @NotNull String getLetter() {
        System.out.println("Guess a letter:");
        return INPUT.next();
    }
}
