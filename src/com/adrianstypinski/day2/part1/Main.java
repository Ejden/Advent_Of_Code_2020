package com.adrianstypinski.day2.part1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println(checkForValidPassword(readInputFile()));

    }

    static int checkForValidPassword(List<String> passwords) {
        Pattern pattern = Pattern.compile("^(\\d+)-(\\d+) (\\w): (\\w+)$");

        int validPasswords = 0;
        for (String record : passwords) {
            if (isPasswordValid(pattern, record))
                validPasswords++;
        }

        return validPasswords;
    }

    static boolean isPasswordValid (Pattern pattern, String record) {
        Matcher matcher = pattern.matcher(record);

        if (matcher.matches()) {
            long minimumLetterOccurrence = Long.parseLong(matcher.group(1));
            long maximumLetterOccurrence = Long.parseLong(matcher.group(2));
            String letter = matcher.group(3);
            String password = matcher.group(4);

            Pattern letterPattern = Pattern.compile(letter);
            Matcher letterMatcher = letterPattern.matcher(password);

            long letterOccurrence = letterMatcher.results().count();
            return (letterOccurrence >= minimumLetterOccurrence && letterOccurrence <= maximumLetterOccurrence);
        }

        return false;
    }

    public static List<String> readInputFile() throws FileNotFoundException {
        File inputFile = new File("src/com/adrianstypinski/day2/input.txt");
        Scanner scanner = new Scanner(inputFile);

        List<String> lines = new ArrayList<>();
        while (scanner.hasNext()) {
            lines.add(scanner.nextLine());
        }

        scanner.close();
        return lines;
    }
}
