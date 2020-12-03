package com.adrianstypinski.day2.part2;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.adrianstypinski.day2.part1.Main.readInputFile;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        List<String> records = readInputFile();
        Pattern pattern = Pattern.compile("^(\\d+)-(\\d+) (\\w): (\\w+)$");

        int validPasswords = 0;
        for (String record : records) {
            Matcher matcher = pattern.matcher(record);

            if (matcher.matches()) {
                int firstPosition = Integer.parseInt(matcher.group(1));
                int secondPosition = Integer.parseInt(matcher.group(2));
                char letter = matcher.group(3).charAt(0);
                String password = matcher.group(4);

                int occurrence = 0;
                occurrence = (password.charAt(firstPosition - 1) == letter) ? occurrence + 1 : occurrence;
                occurrence = (password.charAt(secondPosition - 1) == letter) ? occurrence + 1 : occurrence;
                validPasswords = (occurrence == 1) ? validPasswords + 1 : validPasswords;
            }
        }

        System.out.println(validPasswords);
    }
}
