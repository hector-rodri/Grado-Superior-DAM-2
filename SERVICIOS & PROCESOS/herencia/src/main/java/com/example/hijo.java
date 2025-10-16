package com.example;

public class hijo {
    public static void main(String[] args) {
        long pid = ProcessHandle.current().pid();
        System.out.println("Soy el hijo y mi PID es " + pid);

        try {
            Thread.sleep(20_000);
        } catch (InterruptedException e) {
            System.out.println(pid + "Soy el hijo y me han interrumpido");
            return;
        }
        System.out.println(pid + "Soy el hijo y ya me toca morir");
    }
}
