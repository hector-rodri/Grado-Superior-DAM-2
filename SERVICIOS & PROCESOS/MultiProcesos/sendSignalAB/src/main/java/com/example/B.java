package com.example;

import java.io.*;
import java.util.*;

public class B {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.print("Enter the PID of process A: ");
            String pid = sc.nextLine();

            System.out.println("Sending SIGALRM to " + pid);
            ProcessBuilder p1 = new ProcessBuilder("kill", "-ALRM", pid);
            p1.inheritIO().start().waitFor();

            Thread.sleep(2000);

            System.out.println("Sending SIGHUP to " + pid);
            ProcessBuilder p2 = new ProcessBuilder("kill", "-HUP", pid);
            p2.inheritIO().start().waitFor();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        sc.close();
    }
}