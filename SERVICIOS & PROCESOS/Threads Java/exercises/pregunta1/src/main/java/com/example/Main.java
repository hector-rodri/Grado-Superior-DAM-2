package com.example;

public class Main {
    public static void main(String[] args) {

        int hilos = 12;
        for (int i = 0; i < hilos; i++) {
            int id = i;
            Thread thread = new Thread(() -> {
                System.out.println("Hola des del fil id: " + id);
            });
            thread.start();
        }
    }
}