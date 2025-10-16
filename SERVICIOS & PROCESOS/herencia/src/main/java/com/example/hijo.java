package com.example;

public class hijo {
    public static void main(String[] args) {
        long pid = ProcessHandle.current().pid();
        System.out.println(pid + "Soy el hijo y estoy vivo");
        System.out.flush();
        try {
            Thread.sleep(20_000);
        } catch (InterruptedException e) {
            System.out.println(pid + "Soy el hijo y me han interrumpido");
            System.out.flush();
            return;
        }
        System.out.println(pid + "Soy el hijo y ya me toca morir");
        System.out.flush();
    }
}

