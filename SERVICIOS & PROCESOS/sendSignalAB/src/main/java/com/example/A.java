package com.example;

import sun.misc.*;

public class A {
    public static void main(String[] args) {
        Signal.handle(new Signal("ALRM"), new SignalHandler() {
            @Override
            public void handle(Signal sig) {
                System.out.println("Process A: Received SIGALRM");
            }
        });

        Signal.handle(new Signal("HUP"), new SignalHandler() {
            @Override
            public void handle(Signal sig) {
                System.out.println("Process A: Received SIGHUP");
            }
        });

        System.out.println("Process A PID: " + ProcessHandle.current().pid());

        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.err.println("Interrupted: " + e.getMessage());
            }
        }
    }
}