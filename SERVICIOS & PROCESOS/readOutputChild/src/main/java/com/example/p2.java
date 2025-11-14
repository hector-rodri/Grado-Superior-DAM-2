package com.example;

public class p2 {
    public static void main(String[] args) {
        long pid = ProcessHandle.current().pid();
        String pidParent = args[0];

        System.out.println("Child PID: " + pid);
        System.out.println("Parent PID : " + pidParent);
    }
}