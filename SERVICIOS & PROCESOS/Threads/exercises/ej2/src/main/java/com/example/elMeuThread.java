package com.example;

/*
 *  No s'ha de modificar aquest fitxer.
*/
public class elMeuThread extends Thread {
    @Override
    public void run() {
        Singleton.getInstance();
    }
}
