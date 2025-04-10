package com.example.tap2025.vistas;

import com.example.tap2025.componentes.Hilo;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Celayork extends Stage {
    private Scene escena;
    private VBox vBox;
    private GridPane gdpCalles;
    private Button btnIniciar;
    private Label[] lblRutas;
    private ProgressBar[] pgbRutas;
    private Hilo[] thrRutas;
    private String[] strRutas = {"Ruta pinos", "Ruta Laureles", "Ruta Monte Blanco","Ruta Teneria","CHECO PEREZ"};
    public  Celayork(){
        CrearUI();
        this.setTitle("Calles de Celaya");
        this.setScene(escena);
        this.show();
    }
    private void CrearUI(){
        lblRutas = new Label[5];
        pgbRutas = new ProgressBar[5];
        thrRutas = new Hilo[5];
        gdpCalles = new GridPane();
        for (int i = 0; i<pgbRutas.length; i++){
            lblRutas[i] = new Label(strRutas[i]);
            gdpCalles.add(lblRutas[i],0,i);
            pgbRutas[i] = new ProgressBar(0);
            thrRutas[i] = new Hilo(strRutas[i], pgbRutas[i]);
            gdpCalles.add(pgbRutas[i],0,i);
        }
        btnIniciar = new Button("iniciar");
        btnIniciar.setOnAction(actionEvent ->{
            for(int i= 0; i < pgbRutas.length; i++){
                thrRutas[i].start();
            }
        });
        vBox = new VBox(gdpCalles,btnIniciar);
        escena = new Scene(vBox,300,200);
    }

}