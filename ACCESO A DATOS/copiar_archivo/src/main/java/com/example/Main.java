package com.example;
import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //var
        Scanner sc = new Scanner(System.in);
        String fileName;


        System.out.println("Hello!");
        System.out.println("Dime el nombre de tu archivo");
        fileName = sc.nextLine();
        
        try {
            FileWriter fileWriter = new FileWriter(fileName + ".xml");

        } catch (Exception e) {
            // TODO: handle exception
        }

    }
}