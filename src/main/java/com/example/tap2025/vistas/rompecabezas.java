package com.example.tap2025.vistas;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.*;

public class rompecabezas extends Stage {
    private static final String IMAGE_PATH = "/image/sadboyz.png";
    private int rows;
    private int cols;
    private Image image;
    private List<ImageView> piezas = new ArrayList<>();
    private GridPane gdp;
    private ImageView piezaSeleccionada = null;
    private Instant startTime;

    public rompecabezas() {
        Stage stage = new Stage(); // Crear un nuevo Stage internamente
        mostrarPantallaSeleccion(stage);
    }

    private void mostrarPantallaSeleccion(Stage stage) {
        VBox vbox = new VBox(10);
        vbox.setSpacing(10);
        Label label = new Label("Selecciona el tamaño del rompecabezas:");
        Button btn3x3 = new Button("3x3");
        Button btn4x4 = new Button("4x4");
        Button btn5x5 = new Button("5x5");

        btn3x3.setOnAction(e -> iniciarJuego(stage, 3, 3));
        btn4x4.setOnAction(e -> iniciarJuego(stage, 4, 4));
        btn5x5.setOnAction(e -> iniciarJuego(stage, 5, 5));

        vbox.getChildren().addAll(label, btn3x3, btn4x4, btn5x5);
        Scene scene = new Scene(vbox, 300, 200);
        stage.setScene(scene);
        stage.setTitle("Seleccionar Tamaño");
        stage.show();
    }

    private void iniciarJuego(Stage stage, int filas, int columnas) {
        this.rows = filas;
        this.cols = columnas;
        gdp = new GridPane();
        stage.close();

        try {
            image = new Image(getClass().getResourceAsStream(IMAGE_PATH));
        } catch (Exception e) {
            System.out.println("Error al cargar la imagen: " + e.getMessage());
            return;
        }

        double pieceWidth = image.getWidth() / cols;
        double pieceHeight = image.getHeight() / rows;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                ImageView pieza = new ImageView(image);
                pieza.setViewport(new Rectangle2D(j * pieceWidth, i * pieceHeight, pieceWidth, pieceHeight));
                pieza.setUserData(new int[]{i, j});
                pieza.setOnMouseClicked(event -> intercambiarPiezas(pieza));
                piezas.add(pieza);
            }
        }

        Collections.shuffle(piezas);
        actualizarGrid();

        startTime = Instant.now();

        Stage gameStage = new Stage();
        Scene scene = new Scene(gdp, image.getWidth(), image.getHeight());
        gameStage.setTitle("Rompecabezas Interactivo");
        gameStage.setScene(scene);
        gameStage.show();
    }

    private void intercambiarPiezas(ImageView piezaActual) {
        if (piezaSeleccionada == null) {
            piezaSeleccionada = piezaActual;
        } else {
            int index1 = piezas.indexOf(piezaSeleccionada);
            int index2 = piezas.indexOf(piezaActual);
            Collections.swap(piezas, index1, index2);
            actualizarGrid();
            if (esRompecabezasCompleto()) {
                mostrarMensajeVictoria();
            }
            piezaSeleccionada = null;
        }
    }

    private void actualizarGrid() {
        gdp.getChildren().clear();
        int index = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                gdp.add(piezas.get(index), j, i);
                index++;
            }
        }
    }

    private boolean esRompecabezasCompleto() {
        for (int index = 0; index < piezas.size(); index++) {
            ImageView pieza = piezas.get(index);
            int[] posicionCorrecta = (int[]) pieza.getUserData();
            Integer col = GridPane.getColumnIndex(pieza);
            Integer fila = GridPane.getRowIndex(pieza);
            if (col == null) col = 0;
            if (fila == null) fila = 0;
            if (posicionCorrecta[0] != fila || posicionCorrecta[1] != col) {
                return false;
            }
        }
        return true;
    }

    private void mostrarMensajeVictoria() {
        Instant endTime = Instant.now();
        long elapsedTime = Duration.between(startTime, endTime).getSeconds();

        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("¡Felicidades!");
        alerta.setHeaderText(null);
        alerta.setContentText("¡Has completado el rompecabezas en " + elapsedTime + " segundos!");
        alerta.showAndWait();

        guardarTiempoEnArchivo(elapsedTime);
    }

    private void guardarTiempoEnArchivo(long tiempo) {
        try (FileWriter writer = new FileWriter("registro_tiempos.txt", true)) {
            writer.write("Tiempo: " + tiempo + " segundos\n");
        } catch (IOException e) {
            System.out.println("Error al guardar el tiempo: " + e.getMessage());
        }
    }
}
// C:\Users\Usuario\IdeaProjects\tap2025\registro_tiempos.txt la ruta para ver el archivo
