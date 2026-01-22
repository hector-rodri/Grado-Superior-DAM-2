package com.example;

import sun.misc.*;

public class receiver {
    public static void main(String[] args) {
        Signal.handle(new Signal("INT"), new SignalHandler() {
            @Override
            public void handle(Signal sig) {
                System.out.println("Received signal: SIGINT");
            }
        });

        Signal.handle(new Signal("TERM"), new SignalHandler() {
            @Override
            public void handle(Signal sig) {
                System.out.println("Received signal: SIGTERM");
            }
        });

        Signal.handle(new Signal("HUP"), new SignalHandler() {
            @Override
            public void handle(Signal sig) {
                System.out.println("Received signal: SIGHUP");
            }
        });

        System.out.println("Waiting for signals...");

        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.err.println("Interrupted: " + e.getMessage());
            }
        }
    }
}