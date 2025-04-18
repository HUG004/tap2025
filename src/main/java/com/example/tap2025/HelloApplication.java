package com.example.tap2025;

import com.example.tap2025.componentes.Hilo;
import com.example.tap2025.models.conexion;
import com.example.tap2025.vistas.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    private static final String IMAGE_PATH = "/image/Wallpaper.png";
    private ImageView imageView;
    private Image image;
    private VBox vBox;
    private MenuBar mnbPrincipal;
    private Menu menCompentencia1, menCompetencia2;
    private MenuItem mitCalculadora, mitRestautante, mitRompecabezas,mitCelayork;
    private Scene escena;

    void CrearUI(){
        mitCalculadora = new MenuItem("Calculadora");
        mitCalculadora.setOnAction(event -> new Calculadora());
        mitRestautante = new MenuItem("Restaurante");
        mitRestautante.setOnAction(actionEvent -> new viewAdmin());
        mitRompecabezas = new MenuItem("Rompecabezas");
        mitRompecabezas.setOnAction(actionEvent -> new rompecabezas());

        menCompentencia1 = new Menu("Competencia 1");
        menCompentencia1.getItems().addAll(mitCalculadora, mitRestautante, mitRompecabezas);

        mitCelayork = new MenuItem("Celayork");
        mitCelayork.setOnAction(actionEvent -> new Celayork());
        menCompetencia2 = new Menu("Competencia 2");
        menCompetencia2.getItems().addAll(mitCelayork);

        mnbPrincipal = new MenuBar();
        mnbPrincipal.getMenus().addAll(menCompentencia1,menCompetencia2);

        try {
            image = new Image(getClass().getResourceAsStream(IMAGE_PATH));
            imageView = new ImageView(image);
            imageView.setFitWidth(1400); // Ajusta el tamaño
            imageView.setPreserveRatio(true);
        } catch (Exception e) {
            System.out.println("Error al cargar la imagen: " + e.getMessage());
            imageView = new ImageView(); // Evita errores si la imagen no carga
        }

        vBox = new VBox(mnbPrincipal, imageView);
        escena = new Scene(vBox, 800, 600); // Ajusta el tamaño de la ventana

        // escena.getStylesheets().add(getClass().getResource("/image/Wallpaper.png").toString());
    }

    @Override
    public void start(Stage stage) throws IOException {
        /*new Hilo("Ruta pinos").start();
        new Hilo("Ruta Laureles").start();
        new Hilo("Ruta Monte Blanco").start();
        new Hilo("Ruta Teneria").start();
        new Hilo("CHECO PEREZ").start();

         */

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