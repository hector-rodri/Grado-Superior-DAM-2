package com.example;

import sun.misc.*;

public class captureB {
    public static void main(String[] args) {
        Signal.handle(new Signal("INT"), new SignalHandler() {
            @Override
            public void handle(Signal sig) {
                System.out.println("I received SIGINT, you can't stop me easily!");
            }
        });

        Signal.handle(new Signal("ALRM"), new SignalHandler() {
            @Override
            public void handle(Signal sig) {
                System.out.println("I received an alarm.");
            }
        });

        Signal.handle(new Signal("TERM"), new SignalHandler() {
            @Override
            public void handle(Signal sig) {
                System.out.println("Process ended");
            }
        });

        Signal.handle(new Signal("HUP"), new SignalHandler() {
            @Override
            public void handle(Signal sig) {
                System.out.println("Signal ignored");
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