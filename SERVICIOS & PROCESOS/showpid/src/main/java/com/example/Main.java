package com.example;

public class Main {
    public static void main(String[] args) {
        long pid = ProcessHandle.current().pid();
        System.out.println("Id of the process (PID): " + pid);

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            System.err.println("The process was interrupted");
        }

        System.out.println("The program ended");
    }
}