package com.example.tap2025.vistas;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class ListaTickets extends Stage {
    private ListView<String> listView;
    private VBox vbox;
    private Scene scene;

    public ListaTickets() {
        listView = new ListView<>();
        vbox = new VBox(10);
        scene = new Scene(vbox, 400, 300);

        File folder = new File("tickets/");
        if (folder.exists() && folder.isDirectory()) {
            for (File file : Objects.requireNonNull(folder.listFiles((dir, name) -> name.endsWith(".pdf")))) {
                listView.getItems().add(file.getName());
            }
        }

        listView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                String selectedFile = listView.getSelectionModel().getSelectedItem();
                if (selectedFile != null) {
                    try {
                        File fileToOpen = new File("tickets/" + selectedFile);
                        Desktop.getDesktop().open(fileToOpen);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        Button btnCerrar = new Button("Cerrar");
        btnCerrar.setOnAction(e -> this.close());

        vbox.getChildren().addAll(listView, btnCerrar);
        this.setTitle("Lista de Tickets");
        this.setScene(scene);
        this.show();
    }
}