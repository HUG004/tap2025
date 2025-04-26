package com.example.tap2025.vistas;

import com.example.tap2025.models.*;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class FormOrden extends Stage {
    private ClienteDAO clienteSeleccionado = null;
    private MesaDAO mesaSeleccionada = null;
    private EmpleadoDAO empleadoSeleccionado = null;
    private Button btnGuardar;
    private OrdenDAO nuevaOrden = null;
    private Scene escena;

    public FormOrden() {
        setTitle("Nueva Orden");

        VBox clienteBox = crearBotonesClientes();
        VBox mesaBox = crearBotonesMesas();
        VBox empleadoBox = crearBotonesEmpleados();

        btnGuardar = new Button("Crear Orden");
        btnGuardar.setOnAction(e -> guardarOrden());

        Label lblCte = new Label("Cliente:");
        lblCte.getStyleClass().add("layout-visible");
        VBox clienteSection = new VBox(5, lblCte, clienteBox);
        Label lblMesa = new Label("Mesa: ");
        lblMesa.getStyleClass().add("layout-visible");
        VBox mesaSection = new VBox(5, lblMesa, mesaBox);
        Label lblEmp = new Label("Empleado: ");
        lblEmp.getStyleClass().add("layout-visible");
        VBox empleadoSection = new VBox(5, lblEmp, empleadoBox);

        HBox seccionesTop = new HBox(15, clienteSection, mesaSection, empleadoSection, btnGuardar);
        seccionesTop.setPadding(new Insets(10));

        VBox root = new VBox(10, seccionesTop);
        root.setPadding(new Insets(10));
        root.getStyleClass().add("background-charcoal");
        escena = new Scene(root, 600, 400);
        escena.getStylesheets().add(getClass().getResource("/styles/Listas.css").toExternalForm());

        this.setScene(escena);
    }

    private VBox crearBotonesClientes() {
        VBox box = new VBox(5);
        for (ClienteDAO c : ClienteDAO.SELECT()) {
            Button btn = new Button(c.getNom_cliente());
            btn.setMaxWidth(Double.MAX_VALUE);
            btn.setOnAction(e -> {
                clienteSeleccionado = c;
                destacarBotonSeleccionado(box, btn);
            });
            btn.getStyleClass().add("btn-primary");
            box.getChildren().add(btn);
        }
        return box;
    }

    private VBox crearBotonesMesas() {
        VBox box = new VBox(5);
        for (MesaDAO m : MesaDAO.SELECT()) {
            Button btn = new Button("Mesa " + m.getId_mesa());
            btn.setMaxWidth(Double.MAX_VALUE);
            btn.setOnAction(e -> {
                mesaSeleccionada = m;
                destacarBotonSeleccionado(box, btn);
            });
            btn.getStyleClass().add("btn-primary");
            box.getChildren().add(btn);
        }
        return box;
    }

    private VBox crearBotonesEmpleados() {
        VBox box = new VBox(5);
        for (EmpleadoDAO e : EmpleadoDAO.SELECT()) {
            Button btn = new Button(e.getNombre());
            btn.setMaxWidth(Double.MAX_VALUE);
            btn.setOnAction(ev -> {
                empleadoSeleccionado = e;
                destacarBotonSeleccionado(box, btn);
            });
            btn.getStyleClass().add("btn-primary");
            box.getChildren().add(btn);
        }
        return box;
    }

    private void destacarBotonSeleccionado(VBox box, Button seleccionado) {
        for (var node : box.getChildren()) {
            if (node instanceof Button) {
                node.setStyle("");
            }
        }
        seleccionado.setStyle("-fx-background-color: lightgreen;");
    }

    private void guardarOrden() {
        if (clienteSeleccionado == null || mesaSeleccionada == null || empleadoSeleccionado == null) {
            new Alert(Alert.AlertType.WARNING, "Por favor selecciona todos los datos").show();
            return;
        }

        OrdenDAO orden = new OrdenDAO();
        orden.setId_cliente(clienteSeleccionado.getId_cliente());
        orden.setId_mesa(mesaSeleccionada.getId_mesa());
        orden.setId_empleado(empleadoSeleccionado.getId_empleado());
        orden.INSERT();
        this.nuevaOrden = orden;
        this.close();
    }

    public OrdenDAO getNuevaOrden() {
        return nuevaOrden;
    }
}
