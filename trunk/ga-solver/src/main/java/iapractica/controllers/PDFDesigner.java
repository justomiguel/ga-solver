/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package iapractica.controllers;

import com.frre.cemami.utils.DefaultLogguer;
import com.itextpdf.text.Anchor;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import genetics.individuos.Individuo;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author developer
 */
public class PDFDesigner {

    static DefaultLogguer logguer = DefaultLogguer.getLogger();

    public static void makePDF(int solutionNumber, Individuo solucion) {
        try {
            Document document = null;
            PdfWriter writer = null;
            try {
                document = new Document(PageSize.A4, 50, 50, 50, 50);
                writer = PdfWriter.getInstance(document, new FileOutputStream("Solucion" + solutionNumber + ".pdf"));
                document.open();
            }
            catch (FileNotFoundException ex) {
                logguer.logError(ex.getMessage());
            }
            catch (DocumentException ex) {
                logguer.logError(ex.getMessage());
            }


            //make the title
            Paragraph title1 = new Paragraph("GA-SOLVER", FontFactory.getFont(FontFactory.HELVETICA,
                    18, Font.BOLDITALIC, new CMYKColor(0, 255, 255, 17)));

            Chapter chapter1 = new Chapter(title1, 1);

            chapter1.setNumberDepth(0);


            //put the solution here
            Paragraph title11 = new Paragraph("Solucion Numero " + solutionNumber,
                    FontFactory.getFont(FontFactory.HELVETICA, 16, Font.BOLD,
                    new CMYKColor(0, 255, 255, 17)));

            Section section1 = chapter1.addSection(title11);

            //body

            Paragraph someSectionText = new Paragraph("\n Detalles de la Solucion \n");

            section1.add(someSectionText);

            someSectionText = new Paragraph("Ganancia: " + solucion.getProfit());

            section1.add(someSectionText);

            someSectionText = new Paragraph("Materia Sobrante: " + solucion.getMateriaPrimaSobrante());

            section1.add(someSectionText);



            //table
            PdfPTable t = new PdfPTable(3);

            t.setSpacingBefore(25);

            t.setSpacingAfter(25);

            //primer fila columnas

            PdfPCell c1 = new PdfPCell(new Phrase("Producto Nro"));

            t.addCell(c1);

            PdfPCell c2 = new PdfPCell(new Phrase("Cantidad"));

            t.addCell(c2);

            PdfPCell c3 = new PdfPCell(new Phrase("Ganancia x Unidad"));

            t.addCell(c3);

            PdfPCell c4 = new PdfPCell(new Phrase("Ganancia Total"));

            t.addCell(c4);

            // filas

            for (int i = 0; i < 4; i++) {
                t.addCell(String.valueOf(i + 1));
                int sizeProductos = solucion.getProductsSize(i + 1);
                t.addCell(String.valueOf(sizeProductos));
                if (sizeProductos > 0) {
                    double ganancia = 0;
                    double gananciaXunidad = 0;
                    if (solucion.getProductAt(i + 1) != null) {
                        ganancia = solucion.getProductAt(i + 1).getProfitValue() * sizeProductos;
                        gananciaXunidad = solucion.getProductAt(i + 1).getProfitValue();
                    }
                    t.addCell(String.valueOf(gananciaXunidad));
                    t.addCell(String.valueOf(ganancia));
                } else {
                    t.addCell("0");
                    t.addCell("0");
                }

            }

            section1.add(t);

            //configuration
            someSectionText = new Paragraph("Configuraciones : \n \n " + solucion.toPDF());

            section1.add(someSectionText);

            for (int i = 1; i < 9; i++) {
                //add the images
                Image image2 = Image.getInstance("images/AsignaciondeMateriasPrima" + i + ".png");
                image2.scaleAbsolute(400f, 350f);
                section1.add(image2);
            }

            //add the body
            document.add(chapter1);

            //close the doc
            document.close();

            Thread.sleep(100);
            
            if (Desktop.isDesktopSupported()) {
                try {
                    File myFile = new File("Solucion" + solutionNumber + ".pdf");
                    Desktop.getDesktop().open(myFile);
                }
                catch (IOException ex) {
                    logguer.logError(ex.getMessage());
                }
            }

        }
        catch (InterruptedException ex) {
           logguer.logError(ex.getMessage());
        }        catch (BadElementException ex) {
            logguer.logError(ex.getMessage());
        }
        catch (MalformedURLException ex) {
            logguer.logError(ex.getMessage());
        }
        catch (IOException ex) {
            logguer.logError(ex.getMessage());
        }
        catch (DocumentException ex) {
            logguer.logError(ex.getMessage());
        }



    }
}
