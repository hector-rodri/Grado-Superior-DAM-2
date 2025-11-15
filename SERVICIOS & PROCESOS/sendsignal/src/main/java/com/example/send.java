package com.example;

import java.io.*;
import java.util.Scanner;

public class send {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.print("Enter the PID of the process: ");
            String pid = sc.nextLine();

            System.out.print("Enter the signal: ");
            String signal = sc.nextLine().toUpperCase();

            ProcessBuilder pb = new ProcessBuilder("kill", "-" + signal, pid);
            pb.redirectErrorStream(true);
            Process process = pb.start();
            process.waitFor();

            System.out.println("Signal " + signal + " sent to process " + pid);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        sc.close();
    }
}