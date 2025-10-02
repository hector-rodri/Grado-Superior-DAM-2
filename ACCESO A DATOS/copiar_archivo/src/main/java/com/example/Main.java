package com.example;

import java.io.*;//Import all libraries
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);//Scanner object to read inputs
        System.out.println("Hello!");
        System.out.print("Enter the source file path:");
        String sourcePath = sc.nextLine();//Reads the source file
        System.out.print("Enter the destination file path:");
        String destPath = sc.nextLine();//Reads the destination file
        File sourceFile = new File(sourcePath);//Creates a file with source path
        File destFile = new File(destPath);//Creates a file with dest path

        if (sourcePath.endsWith(".txt") || sourcePath.endsWith(".xml")) {//If file ends with this then is a text file
            copyFile(sourceFile, destFile);
        } else {//If file doesn't end with this then is a binary file
            copyBinariFile(sourceFile, destFile);
        }
        sc.close();
    }

    public static void copyBinariFile(File sourceFile, File destFile) {
        try {
            FileInputStream input = new FileInputStream(sourceFile);//InputStream for read bytes
            FileOutputStream output = new FileOutputStream(destFile);//OutputStream for write bytes
            byte [] data = input.readAllBytes();//The bytes of the source file is saved in array of bytes
            output.write(data);//The array of bytes is write in the destination file
            System.out.println("Succesful");
            input.close();//Close the streams
            output.close();
        } catch (IOException e) {//If an error happens then print this line
            System.out.println("Error while copying the file");
        }
    }

    public static void copyFile(File sourceFile, File destFile) {
        try {
            FileReader reader = new FileReader(sourceFile);//Reader for read characters
            FileWriter writer = new FileWriter(destFile);//Writer for write characters
            int character;//Var for store characters
            while ((character = reader.read()) != -1) {//Reads all the characters and writes each in the dest file when return -1 it's the end
                writer.write(character);
            }
            System.out.println("Successful");
            reader.close();//Close the streams
            writer.close();
        } catch (IOException e) {//If an error happens then print this line
            System.err.println("Error while copying the file");
        }
    }
}
