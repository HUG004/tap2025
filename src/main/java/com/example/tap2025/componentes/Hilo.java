package com.example.tap2025.componentes;

import javafx.scene.control.ProgressBar;

import java.util.Random;

public class Hilo extends Thread{
    private ProgressBar pgbRuta;
    public Hilo(String nOmbre, ProgressBar pgb){
        super(nOmbre);
        this.pgbRuta = pgb;
    }

    @Override
    public void run() {
        super.run();
        double avance = 0;
        while (avance<1){
            avance += Math.random() * .01;
            this.pgbRuta.setProgress(avance);
            try {
                sleep((long) (Math.random() * 500));
            }
            catch (InterruptedException i){}
        }
    }
    /*@Override
    public void run() {
        super.run();
        for (int i = 1; i<= 10; i++ ){
            try {
                sleep((long) (Math.random() * 3000));
            }
            catch (InterruptedException e){
                System.out.println("El corredor " + this.getName() + " llego al KM " + i);
            }
        }
    }*/
}
