package com.example;

import sun.misc.*;

public class capture {
    public static void main(String[] args) {
        Signal.handle(new Signal("INT"), new SignalHandler() {
            @Override
            public void handle(Signal sig) {
                System.out.println("I received SIGINT, you can't stop me easily!");
            }
        });

        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}