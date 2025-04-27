package com.example.tap2025.componentes;

import com.example.tap2025.models.Detalle_OrdenDAO;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;

import java.io.FileOutputStream;

public class ReportePDF {

    public void generarReporte() {
        Document document = new Document();
        try {
            String ruta = "C:\\Users\\Usuario\\IdeaProjects\\tap2025\\src\\main\\java\\com\\example\\tap2025\\ReportePDF\\Reporte_Estadisticas.pdf";
            PdfWriter.getInstance(document, new FileOutputStream(ruta));
            document.open();

            // Título principal
            Font tituloFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
            Paragraph titulo = new Paragraph("Reporte de Ventas", tituloFont);
            titulo.setAlignment(Element.ALIGN_CENTER);
            document.add(titulo);
            document.add(Chunk.NEWLINE);

            // -------- Tabla de Productos ----------
            document.add(new Paragraph("Productos más vendidos", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14)));
            PdfPTable tablaProductos = new PdfPTable(2);
            tablaProductos.addCell(encabezado("Producto"));
            tablaProductos.addCell(encabezado("Cantidad Vendida"));

            ObservableList<PieChart.Data> productos = Detalle_OrdenDAO.obtenerProductosConMasVentas();
            for (PieChart.Data data : productos) {
                tablaProductos.addCell(data.getName());
                tablaProductos.addCell(String.valueOf((int) data.getPieValue()));
            }
            document.add(tablaProductos);
            document.add(Chunk.NEWLINE);

            // -------- Tabla de Fechas ----------
            document.add(new Paragraph("Días con más ventas", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14)));
            PdfPTable tablaFechas = new PdfPTable(2);
            tablaFechas.addCell(encabezado("Fecha"));
            tablaFechas.addCell(encabezado("Total Ventas"));

            ObservableList<XYChart.Data<String, Number>> fechas = Detalle_OrdenDAO.obtenerFechasConMasVentas();
            for (XYChart.Data<String, Number> data : fechas) {
                tablaFechas.addCell(data.getXValue());
                tablaFechas.addCell(String.valueOf(data.getYValue().intValue()));
            }
            document.add(tablaFechas);
            document.add(Chunk.NEWLINE);

            // -------- Tabla de Empleados ----------
            document.add(new Paragraph("Empleados con más ventas", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14)));
            PdfPTable tablaEmpleados = new PdfPTable(2);
            tablaEmpleados.addCell(encabezado("Empleado"));
            tablaEmpleados.addCell(encabezado("Total Ventas"));

            ObservableList<XYChart.Data<String, Number>> empleados = Detalle_OrdenDAO.obtenerEmpleadosConMasVentas();
            for (XYChart.Data<String, Number> data : empleados) {
                tablaEmpleados.addCell(data.getXValue());
                tablaEmpleados.addCell(String.valueOf(data.getYValue().intValue()));
            }
            document.add(tablaEmpleados);

            document.close();
            System.out.println("Reporte generado exitosamente: " + ruta);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private PdfPCell encabezado(String texto) {
        PdfPCell celda = new PdfPCell(new Phrase(texto, FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, BaseColor.WHITE)));
        celda.setBackgroundColor(BaseColor.DARK_GRAY);
        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
        celda.setPadding(10);
        return celda;
    }
}
