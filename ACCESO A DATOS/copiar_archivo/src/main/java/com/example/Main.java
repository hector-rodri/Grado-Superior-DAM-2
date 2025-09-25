package com.example;

import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Hello!");
        System.out.print("Enter the source file path:");
        String sourcePath = sc.nextLine();
        System.out.print("Enter the destination file path:");
        String destPath = sc.nextLine();

        File sourceFile = new File(sourcePath);
        File destFile = new File(destPath);

        if (sourcePath.endsWith(".txt") || sourcePath.endsWith(".xml")) {
            copyFile(sourceFile, destFile);
        } else {
        }

        sc.close();
    }

    public static void copyFile(File sourceFile, File destFile) {
        try (FileReader fileReader = new FileReader(sourceFile);
             FileWriter fileWriter = new FileWriter(destFile)) {

            int character;
            while ((character = fileReader.read()) != -1) {
                fileWriter.write(character);
            }

            System.out.println("Sucessful");

        } catch (IOException e) {
            System.err.println("Error");
        }
    }
}
