module com.example.tap2025 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.tap2025 to javafx.fxml;
    requires org.kordamp.bootstrapfx.core;
    exports com.example.tap2025;
    requires mysql.connector.j;
    requires java.sql;
    requires com.github.librepdf.openpdf;
    requires java.desktop;
    requires itextpdf;
    opens com.example.tap2025.models;
}