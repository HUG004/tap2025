package com.example.tap2025.vistas;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
public class Calculadora extends Stage{

    private Scene escena;
    private TextField txtPantalla;
    private VBox vBox;
    private GridPane gdpTeclado;
    private Button[][] arBtn;
    private String strTeclas[] = {"7", "8", "9", "*", "4", "5", "6", "/", "1", "2", "3", "+", "0", ".", "=", "-"};


    public void CrearUI(){
        CrearTeclado();
        txtPantalla = new TextField("0");
        txtPantalla.setEditable(false);
        txtPantalla.setAlignment(Pos.BASELINE_RIGHT);
        vBox = new VBox(txtPantalla, gdpTeclado);
        vBox.setSpacing(10);
        vBox.setPadding(new Insets(10));
        escena = new Scene(vBox, 200,200);


    }

    public void CrearTeclado(){
        arBtn = new Button[4][4];
        gdpTeclado = new GridPane();
        gdpTeclado.setHgap(5);
        gdpTeclado.setVgap(5);
        int pos = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                arBtn[i][j] = new Button(strTeclas[pos]); //instancia de los objetos en el arreglo
                arBtn[i][j].setPrefSize(50, 50);
                gdpTeclado.add(arBtn[i][j],j,i);
                pos++;
            }
        }
    }

    public Calculadora(){
        CrearUI();
        this.setScene(escena);
        this.setTitle("Calculadora");
        this.show();

    }
}
