package com.example;

import sun.misc.*;

public class final1 {
    public static void main(String[] args) {
        Signal.handle(new Signal("INT"), new SignalHandler() {
            @Override
            public void handle(Signal sig) {
                System.out.println("Process final: Received SIGINT. Terminating");
                System.exit(0);
            }
        });

        try {
            long myPid = ProcessHandle.current().pid();
            System.out.println("Process final started.");

            ProcessBuilder pb = new ProcessBuilder("java", "final2", String.valueOf(myPid));
            pb.inheritIO().start().waitFor();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}