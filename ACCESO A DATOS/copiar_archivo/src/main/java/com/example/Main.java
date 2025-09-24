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

        try {
            FileReader fileReader = new FileReader(sourceFile);
            FileWriter fileWriter = new FileWriter(destFile);
            

        } catch (Exception e) {
            // TODO: handle exception
        }

        try {
            FileInputStream fileInputStream = new FileInputStream(sourceFile);
            FileOutputStream fileOutputStream = new FileOutputStream(destFile);
        } catch (Exception e) {
            // TODO: handle exception
        }
        sc.close();
    }
}