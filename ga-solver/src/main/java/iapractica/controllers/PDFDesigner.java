/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package iapractica.controllers;

import com.frre.cemami.utils.DefaultLogguer;
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
import genetics.productos.Producto;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.LinkedList;

/**
 *
 * @author developer
 */
public class PDFDesigner {

    static DefaultLogguer logguer = DefaultLogguer.getLogger();

    public static void makePDF(int solutionNumber, Individuo solucion, LinkedList<Integer> materiasPrimas) {
        try {
            File myFile = new File("Solucion" + solutionNumber + ".pdf");
            if (!myFile.exists()) {
                Document document = null;
                PdfWriter writer = null;
                FileOutputStream fileStream = null;
                try {
                    document = new Document(PageSize.A4, 20, 20, 20, 20);
                    fileStream = new FileOutputStream("Solucion" + solutionNumber + ".pdf");
                    writer = PdfWriter.getInstance(document, fileStream);
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
                        22, Font.BOLDITALIC, new CMYKColor(0, 255, 255, 17)));
                Chapter chapter1 = new Chapter(title1, 1);
                chapter1.setNumberDepth(0);


                //put the solution here
                Paragraph title11 = new Paragraph("Solucion Numero " + solutionNumber,
                        FontFactory.getFont(FontFactory.HELVETICA, 16, Font.BOLD,
                        new CMYKColor(83, 62, 32, 20)));
                Section section1 = chapter1.addSection(title11);

                //body

                Paragraph someSectionText = new Paragraph("\n Detalles de la Solucion: \n\n");
                section1.add(someSectionText);
                someSectionText = new Paragraph("    Ganancia: $ " + solucion.getProfit());
                section1.add(someSectionText);
                someSectionText = new Paragraph("    Materia Sobrante: " + solucion.getMateriaPrimaSobrante());
                section1.add(someSectionText);

                makeTableProductsUsed(solucion, section1);

                makeTableRestriccionesUsed(solucion, section1);

                makeTableAsignacionesUsed(solucion, section1, materiasPrimas);
                
                putImages(section1);

                //add the body
                document.add(chapter1);
                //close the doc
                document.close();
                fileStream.close();
                Thread.sleep(50);
            }

            if (Desktop.isDesktopSupported()) {
                try {
                    Desktop.getDesktop().open(myFile);
                }
                catch (IOException ex) {
                    logguer.logError(ex.getMessage());
                }
            }
        }
        catch (InterruptedException ex) {
            logguer.logError(ex.getMessage());
        }
        catch (BadElementException ex) {
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

    private static void makeTableRestriccionesUsed(Individuo solucion, Section section1) {
        //configuration
        Paragraph someSectionText = new Paragraph(" Configuraciones Usadas Por Unidad de Producto: ");
        section1.add(someSectionText);
        //table
        PdfPTable t1 = new PdfPTable(9);
        t1.setSpacingBefore(25);
        t1.setSpacingAfter(25);

        //primer fila columnas
        t1.addCell(new PdfPCell(new Phrase("Nro Prod")));
        for (int i = 0; i < 8; i++) {
            t1.addCell(new PdfPCell(new Phrase("M" + ( i + 1 ))));
        }

        for (int k = 0; k < 4; k++) {
            t1.addCell(String.valueOf(( k + 1 )));
            Producto producto = solucion.getProductAt(k + 1);
            if (producto != null) {
                int[] materiasPrimasUsed = producto.getRestriccionesUsed();
                for (int i = 0; i < materiasPrimasUsed.length; i++) {
                    int matUsed = materiasPrimasUsed[i];
                    String matUsedS = String.valueOf(matUsed);
                    t1.addCell(matUsedS);
                }
            } else {
                for (int i = 0; i < 8; i++) {
                    t1.addCell("0");
                }
            }
        }

        section1.add(t1);
    }

    private static void makeTableProductsUsed(Individuo solucion, Section section1) {
       
        //configuration
        Paragraph someSectionText = new Paragraph("\n Cantidades de producto a realizar para obtener la mayor ganancia: ");
        section1.add(someSectionText);
        
        //table
        PdfPTable t = new PdfPTable(4);
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
                t.addCell("$ "+String.valueOf(gananciaXunidad));
                t.addCell("$ "+String.valueOf(ganancia));
            } else {
                t.addCell("$ 0");
                t.addCell("$ 0");
            }

        }

        t.addCell(" ");
        t.addCell("");
        t.addCell("Totales");
        t.addCell("$ "+solucion.getProfit());
        
        section1.add(t);
    }

    private static void putImages(Section section1) throws BadElementException, MalformedURLException, IOException {
        //configuration
        Paragraph someSectionText = new Paragraph(" Graficos de Asignacion Por Materia Prima: \n ");
        section1.add(someSectionText);

        for (int i = 1; i < 9; i++) {
            //add the images
            Image image2 = Image.getInstance("images/AsignaciondeMateriasPrima" + i + ".png");
            image2.scaleAbsolute(200f, 175f);
            section1.add(image2);
        }
    }

    private static void makeTableAsignacionesUsed(Individuo solucion, Section section1, LinkedList<Integer> materiasPrimas) {
        //configuration
        Paragraph someSectionText = new Paragraph(" Materia Prima Total Usada Por Producto: ");
        section1.add(someSectionText);
        //table
        PdfPTable t1 = new PdfPTable(9);
        t1.setSpacingBefore(25);
        t1.setSpacingAfter(25);

        //primer fila columnas
        t1.addCell(new PdfPCell(new Phrase("Nro Prod")));
        for (int i = 0; i < 8; i++) {
            t1.addCell(new PdfPCell(new Phrase("M" + ( i + 1 ))));
        }

        int[] sobrantes = new int[8];
        for (int k = 0; k < 4; k++) {
            t1.addCell(String.valueOf(( k + 1 )));
            Producto producto = solucion.getProductAt(k + 1);
            if (producto != null) {
                int[] materiasPrimasUsed = producto.getRestriccionesUsed();
                for (int i = 0; i < materiasPrimasUsed.length; i++) {
                    int matUsed = materiasPrimasUsed[i]*solucion.getProductsSize((k+1));
                    sobrantes[i] += matUsed; 
                    String matUsedS = String.valueOf(matUsed);
                    t1.addCell(matUsedS);
                }
            } else {
                for (int i = 0; i < 8; i++) {
                    t1.addCell("0");
                }
            }
        }
        
        t1.addCell("Sobras");
        for (int i = 0; i < 8; i++) {
            int mySobrante = materiasPrimas.get(i) - sobrantes[i];
            t1.addCell(String.valueOf(mySobrante));
        }
        
        t1.addCell("Totales");
        for (int i = 0; i < 8; i++) {
            int mySobrante = materiasPrimas.get(i);
            t1.addCell(String.valueOf(mySobrante));
        }

        section1.add(t1);
    }
}
