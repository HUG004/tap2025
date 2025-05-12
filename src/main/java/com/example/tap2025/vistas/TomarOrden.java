package com.example.tap2025.vistas;

import com.example.tap2025.models.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class TomarOrden extends Stage {
    private VBox root;
    private HBox panelCategorias;
    private FlowPane panelProductos;
    private TableView<Detalle_OrdenDAO> tablaTicket;
    private ObservableList<Detalle_OrdenDAO> detalles = FXCollections.observableArrayList();
    private Label lblTotal;
    private Label lblTitulo;
    private int idOrdenActual = -1;
    private OrdenDAO ordenActual;  // Aquí guardamos la orden creada

    public TomarOrden() {
        CrearUI();
        this.setTitle("Toma de Orden");
        Scene escena = new Scene(root, 1200, 700);
        escena.getStylesheets().add(getClass().getResource("/styles/Listas.css").toExternalForm());
        this.setScene(escena);
        this.show();
    }

    private void CrearUI() {
        root = new VBox(10);
        root.setPadding(new Insets(10));

        // LOGO
        ImageView logo = new ImageView(getClass().getResource("/image/logo_lince.png").toExternalForm());
        logo.setFitWidth(50);
        logo.setFitHeight(50);
        logo.setPreserveRatio(true);
        HBox logoContainer = new HBox(logo);
        logoContainer.setAlignment(Pos.TOP_LEFT);
        logoContainer.setPadding(new Insets(5));
        lblTitulo = new Label("RESTAURANTEC");
        lblTitulo.getStyleClass().add("title");

        // BOTÓN ADMIN
        Button btn_admin = new Button("ADMIN");
        btn_admin.setOnAction(e -> new viewAdmin());
        btn_admin.getStyleClass().add("btn-primary");
        HBox adminContainer = new HBox(btn_admin);
        adminContainer.setAlignment(Pos.TOP_RIGHT);
        adminContainer.setPadding(new Insets(5));

        // HEADER con logo y botón admin
        HBox header = new HBox();
        header.setPadding(new Insets(10));
        header.setSpacing(10);
        header.setAlignment(Pos.CENTER);
        header.getChildren().addAll(logoContainer,lblTitulo, new Region(), adminContainer);
        HBox.setHgrow(header.getChildren().get(1), Priority.ALWAYS); // Espaciador flexible

        panelCategorias = new HBox(10);
        panelCategorias.setAlignment(Pos.CENTER);
        cargarCategorias();

        panelProductos = new FlowPane(10, 10);
        panelProductos.setPrefWrapLength(600);
        panelProductos.setPadding(new Insets(10));

        tablaTicket = new TableView<>(detalles);
        TableColumn<Detalle_OrdenDAO, String> colProd = new TableColumn<>("Producto");
        colProd.setCellValueFactory(param ->
                new SimpleStringProperty(param.getValue().getProducto().getProducto()));
        TableColumn<Detalle_OrdenDAO, Integer> colCant = new TableColumn<>("Cantidad");
        colCant.setCellValueFactory(param ->
                new javafx.beans.property.ReadOnlyObjectWrapper<>(param.getValue().getCantidad())
        );
        TableColumn<Detalle_OrdenDAO, String> colPrecio = new TableColumn<>("Precio");
        colPrecio.setCellValueFactory(param ->
                        new SimpleStringProperty("$" + String.format("%.2f",
                                param.getValue().getProducto().getPrecio()
                                        .multiply(new java.math.BigDecimal(param.getValue().getCantidad()))
                                        .doubleValue())) );

                tablaTicket.getColumns().addAll(colProd, colCant, colPrecio);
        tablaTicket.setPrefHeight(150);
        lblTotal = new Label("Total: $0.00");
        lblTotal.setStyle("-fx-font-size: 16px");
        lblTotal.getStyleClass().add("layout-visible");


        HBox panelInferior = new HBox(10);
        panelInferior.setAlignment(Pos.CENTER);
        Button btnNueva = new Button("Nueva Orden");
        btnNueva.setOnAction(e -> nuevaOrden());
        btnNueva.getStyleClass().add("btn-primary");

        Button btnGuardar = new Button("Guardar Orden");
        btnGuardar.setOnAction(e -> guardarOrden());
        btnGuardar.getStyleClass().add("btn-primary");

        Button btnCancelar = new Button("Cancelar");
        btnCancelar.setOnAction(e -> limpiar());
        btnCancelar.getStyleClass().add("btn-primary");

        panelInferior.getChildren().addAll(btnNueva, btnGuardar, btnCancelar);

        Label lblTicket = new Label("Ticket:");
        lblTicket.getStyleClass().add("layout-visible");

        root.getChildren().addAll(header, panelCategorias, panelProductos, lblTicket, tablaTicket, lblTotal, panelInferior);
        root.getStyleClass().add("background-black");
    }

    private void cargarCategorias() {
        ObservableList<CategoriaDAO> categorias = CategoriaDAO.SELECT();
        for (CategoriaDAO cat : categorias) {
            Button btnCat = new Button(cat.getDescripcion());
            btnCat.setOnAction(e -> cargarProductos(cat.getId_categoria()));
            btnCat.getStyleClass().add("btn-primary");
            panelCategorias.getChildren().add(btnCat);
        }
    }

    private void cargarProductos(int idCategoria) {
        panelProductos.getChildren().clear();
        ObservableList<ProductoDAO> productos = ProductoDAO.selectByCategoria(idCategoria);
        for (ProductoDAO prod : productos) {
            Button btnProd = new Button(prod.getProducto());
            btnProd.setOnAction(e -> agregarProducto(prod));
            btnProd.getStyleClass().add("btn-primary");
            panelProductos.getChildren().add(btnProd);
        }
    }

    private void agregarProducto(ProductoDAO producto) {
        for (Detalle_OrdenDAO d : detalles) {
            if (d.getId_producto() == producto.getId_producto()) {
                d.setCantidad(d.getCantidad() + 1);
                tablaTicket.refresh();
                monto();
                return;
            }
        }
        detalles.add(new Detalle_OrdenDAO(idOrdenActual, producto, 1));
        monto();
    }

    private void nuevaOrden() {
        FormOrden form = new FormOrden();
        form.showAndWait(); // Espera que se cierre

        OrdenDAO orden = form.getNuevaOrden();
        if (orden != null) {
            ordenActual = orden;
            idOrdenActual = orden.getId_orden();
            detalles.clear();
            tablaTicket.refresh();
        }
    }

    private void guardarOrden() {
        if (idOrdenActual == -1) {
            new Alert(Alert.AlertType.WARNING, "Primero crea una orden").show();
            return;
        }

        for (Detalle_OrdenDAO detalle : detalles) {
            detalle.setId_orden(idOrdenActual);
            detalle.INSERT();
        }
        GeneradorTicketPDF.generarTicket(idOrdenActual, detalles, ordenActual);
        new Alert(Alert.AlertType.INFORMATION, "Orden guardada correctamente").show();
        detalles.clear(); //  limpiar para tomar otra orden
        tablaTicket.refresh();
        lblTotal.setText("Total: $0.00");
        idOrdenActual = -1;
    }
    private void monto() {
        double total = 0;
        for (Detalle_OrdenDAO d : detalles) {
            total += d.getProducto().getPrecio()
                    .multiply(new java.math.BigDecimal(d.getCantidad()))
                    .doubleValue();

        }
        lblTotal.setText("Total: $" + String.format("%.2f", total));
    }

    private void limpiar() {
        // Limpiar la lista de detalles
        detalles.clear();
        tablaTicket.refresh();

        // Resetear el total a cero
        lblTotal.setText("Total: $0.00");

        // Resetear idOrdenActual y ordenActual
        idOrdenActual = -1;
        ordenActual = null;

        panelProductos.getChildren().clear();
        panelCategorias.getChildren().clear();
        cargarCategorias();

    }

}
