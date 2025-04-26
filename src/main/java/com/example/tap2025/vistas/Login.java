package com.example.tap2025.vistas;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Login extends Stage {
    private Button btn_admin, btn_tomarOrden;
    private Scene escena;
    private VBox vBox;
    private HBox hbox;
    public Login(){
        CrearUI();
        this.setTitle("Login");
        this.setScene(escena);
        this.show();
    }
    private void CrearUI(){
        ImageView logo = new ImageView(getClass().getResource("/image/logo_lince.png").toExternalForm());
        logo.setFitWidth(100);
        logo.setFitHeight(100);
        logo.setPreserveRatio(true);

        HBox logoContainer = new HBox(logo);
        logoContainer.setAlignment(Pos.TOP_LEFT);
        logoContainer.setPadding(new Insets(10));

        btn_admin = new Button("ADMIN");
        btn_admin.setOnAction(event -> new viewAdmin() );
        btn_admin.getStyleClass().add("btn-primary");

        btn_tomarOrden = new Button("TOMAR ORDEN");
        btn_tomarOrden.setOnAction(e -> new TomarOrden());
        btn_tomarOrden.getStyleClass().add("btn-primary");
        vBox = new VBox(20);
        vBox.getChildren().addAll(logoContainer, btn_tomarOrden,btn_admin);
        vBox.setAlignment(Pos.TOP_CENTER);
        vBox.getStyleClass().add("background-black");

        escena = new Scene(vBox, 1150, 400);
        escena.getStylesheets().add(getClass().getResource("/styles/Listas.css").toExternalForm());

    }
}
