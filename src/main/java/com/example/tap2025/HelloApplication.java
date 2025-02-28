package com.example.tap2025;

import com.example.tap2025.models.conexion;
import com.example.tap2025.vistas.Calculadora;
import com.example.tap2025.vistas.ListaClientes;
import com.example.tap2025.vistas.VentasRestaurante;
import com.example.tap2025.vistas.rompecabezas;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    private VBox vBox;
    private MenuBar mnbPrincipal;
    private Menu menCompentencia1, menCompetencia2;
    private MenuItem mitCalculadora, mitRestautante, mitRompecabezas;
    private Scene escena;

    void CrearUI(){
        mitCalculadora = new MenuItem("Calculadora");
        mitCalculadora.setOnAction(event -> new Calculadora());
        mitRestautante = new MenuItem("Restautante");
        mitRestautante.setOnAction(actionEvent -> new ListaClientes());
        mitRompecabezas = new MenuItem("Rompecabezas");
        mitRompecabezas.setOnAction(actionEvent -> new rompecabezas());
        menCompentencia1 = new Menu("Competencia 1");
        menCompentencia1.getItems().addAll(mitCalculadora,mitRestautante, mitRompecabezas);
        mnbPrincipal = new MenuBar();
        mnbPrincipal.getMenus().addAll(menCompentencia1);
        vBox = new VBox(mnbPrincipal);
        escena = new Scene(vBox);
        System.out.println(getClass().getResource("/image/Wallpaper.png"));
        escena.getStylesheets().add(getClass().getResource("/image/Wallpaper.png").toString());
    }

    @Override
    public void start(Stage stage) throws IOException {
        conexion.createConnection();
        CrearUI();
        stage.setTitle("Hola Mundo de Eventos :)");
        stage.setScene(escena);
        stage.show();
        stage.setMaximized(true);
    }

    public static void main(String[] args) {
        launch();
    }
    void clickEvent(){
        System.out.println("Evento desde un metodo :)");
    }
}