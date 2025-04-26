package com.example.tap2025.vistas;
import com.example.tap2025.models.ClienteDAO;
import com.example.tap2025.models.OrdenDAO;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import com.example.tap2025.models.Detalle_OrdenDAO;
import java.io.File;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.util.List;

public class GeneradorTicketPDF {

    public static void generarTicket(int idOrden, List<Detalle_OrdenDAO> detalles, OrdenDAO orden) {
        try {
            File carpeta = new File("tickets");
            if (!carpeta.exists()) carpeta.mkdirs();

            String nombreArchivo = "tickets/orden_" + idOrden + ".pdf";
            Document doc = new Document();
            PdfWriter.getInstance(doc, new FileOutputStream(nombreArchivo));
            doc.open();

            Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);
            Font fontBold = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12);
            Font fontNormal = FontFactory.getFont(FontFactory.HELVETICA, 12);

            Paragraph titulo = new Paragraph("RESTAURANTEC", fontTitle);
            titulo.setAlignment(Element.ALIGN_CENTER);
            doc.add(titulo);
            doc.add(new Paragraph("TICKET DE ORDEN #" + idOrden, fontBold));
            doc.add(new Paragraph("--------------------------------------------------"));

            doc.add(new Paragraph("Detalles de la Compra", fontBold));

            PdfPTable tabla = new PdfPTable(3);
            tabla.setWidthPercentage(100);
            tabla.setWidths(new float[]{50, 20, 30});
            tabla.addCell(new PdfPCell(new Phrase("Producto", fontBold)));
            tabla.addCell(new PdfPCell(new Phrase("Cantidad", fontBold)));
            tabla.addCell(new PdfPCell(new Phrase("Subtotal", fontBold)));

            BigDecimal total = BigDecimal.ZERO;
            for (Detalle_OrdenDAO d : detalles) {
                String nombreProd = d.getProducto().getProducto();
                int cantidad = d.getCantidad();
                BigDecimal precio = d.getProducto().getPrecio();
                BigDecimal subtotal = precio.multiply(new BigDecimal(cantidad));
                total = total.add(subtotal);

                tabla.addCell(new PdfPCell(new Phrase(nombreProd, fontNormal)));
                tabla.addCell(new PdfPCell(new Phrase(String.valueOf(cantidad), fontNormal)));
                tabla.addCell(new PdfPCell(new Phrase("$" + subtotal.toPlainString(), fontNormal)));
            }

            doc.add(tabla);
            doc.add(new Paragraph("\n"));
            doc.add(new Paragraph("TOTAL: $" + total.toPlainString(), fontBold));
            doc.add(new Paragraph("--------------------------------------------------"));
            doc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
