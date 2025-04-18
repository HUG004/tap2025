package com.example.tap2025.vistas;

import com.example.tap2025.componentes.ButtonCell;
import com.example.tap2025.models.EmpleadoDAO;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

public class ListaEmpleados extends Stage {
    private ToolBar tlbMenu;
    private TableView<EmpleadoDAO> tbvEmpleados;
    private VBox vBox;
    private Scene escena;
    private Button btnAgregar;

    public ListaEmpleados() {
        CrearUI();
        this.setTitle("Listado de Empleados");
        this.setScene(escena);
        this.show();
    }

    private void CrearUI() {
        tbvEmpleados = new TableView<>();
        btnAgregar = new Button();
        btnAgregar.setOnAction(actionEvent -> new FormEmpleados(tbvEmpleados, null));
        ImageView img = new ImageView(getClass().getResource("/image/agregar.png").toString());
        img.setFitWidth(30);
        img.setFitHeight(30);
        btnAgregar.setGraphic(img);
        tlbMenu = new ToolBar(btnAgregar);
        CreateTable();
        vBox = new VBox(tlbMenu, tbvEmpleados);
        escena = new Scene(vBox, 900, 500);
    }

    private void CreateTable() {
        EmpleadoDAO objE = new EmpleadoDAO();

        TableColumn<EmpleadoDAO, String> tbcNombre = new TableColumn<>("Nombre");
        tbcNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));

        TableColumn<EmpleadoDAO, String> tbcApellido = new TableColumn<>("Apellido");
        tbcApellido.setCellValueFactory(new PropertyValueFactory<>("apellido"));

        TableColumn<EmpleadoDAO, String> tbcCurp = new TableColumn<>("CURP");
        tbcCurp.setCellValueFactory(new PropertyValueFactory<>("curp"));

        TableColumn<EmpleadoDAO, String> tbcRfc = new TableColumn<>("RFC");
        tbcRfc.setCellValueFactory(new PropertyValueFactory<>("rfc"));

        TableColumn<EmpleadoDAO, String> tbcNss = new TableColumn<>("NSS");
        tbcNss.setCellValueFactory(new PropertyValueFactory<>("nss"));

        TableColumn<EmpleadoDAO, Double> tbcSueldo = new TableColumn<>("Sueldo");
        tbcSueldo.setCellValueFactory(new PropertyValueFactory<>("sueldo"));

        TableColumn<EmpleadoDAO, String> tbcPuesto = new TableColumn<>("Puesto");
        tbcPuesto.setCellValueFactory(new PropertyValueFactory<>("puesto"));

        TableColumn<EmpleadoDAO, String> tbcTelefono = new TableColumn<>("Teléfono");
        tbcTelefono.setCellValueFactory(new PropertyValueFactory<>("tel_empleado"));

        TableColumn<EmpleadoDAO, String> tbcHorario = new TableColumn<>("Horario");
        tbcHorario.setCellValueFactory(new PropertyValueFactory<>("horario"));

        TableColumn<EmpleadoDAO, String> tbcEditar = new TableColumn<>("Editar");
        tbcEditar.setCellFactory(col -> new ButtonCell<>(
                "Editar",
                (tableView, empleado) -> new FormEmpleados(tableView, empleado),
                e -> null, // No hace nada en "Editar"
                EmpleadoDAO::SELECT
        ));

        TableColumn<EmpleadoDAO, String> tbcEliminar = new TableColumn<>("Eliminar");
        tbcEliminar.setCellFactory(col -> new ButtonCell<>(
                "Eliminar",
                (tableView, empleado) -> {}, // No hace nada en "Eliminar"
                empleado -> {
                    empleado.DELETE();
                    return null;
                },
                EmpleadoDAO::SELECT
        ));

        tbvEmpleados.getColumns().addAll(
                tbcNombre, tbcApellido, tbcCurp, tbcRfc, tbcNss,
                tbcSueldo, tbcPuesto, tbcTelefono, tbcHorario,
                tbcEditar, tbcEliminar
        );
        tbvEmpleados.setItems(objE.SELECT());
    }
}
