package edu.Project1.input;

import java.util.Scanner;

public class CliReader implements Reader {
    private static final Scanner INPUT = new Scanner(System.in);

    @Override
    public String getLetter() {
        return INPUT.next();
    }
}
