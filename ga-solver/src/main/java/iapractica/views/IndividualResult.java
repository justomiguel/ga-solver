/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package iapractica.views;

import genetics.individuos.Individuo;
import java.util.LinkedList;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.util.Rotation;

/**
 *
 * @author developer
 */
public class IndividualResult extends javax.swing.JFrame {

    private LinkedList<Integer> materiasPrimas;
    /**
     * Creates new form IndividualResult
     */
    public IndividualResult(String resultadoNro, Individuo solucion, LinkedList<Integer> materiasPrimas) {
        initComponents();
        
        this.setTitle("GA-Solver - REsultado N° "+resultadoNro);
        
        this.materiasPrimas = materiasPrimas;
        
        // This will create the dataset 
        PieDataset dataset = createDataset(solucion);
        // based on the dataset we create the chart
        JFreeChart chart = createChart(dataset, "Asignacion de Materias Primas");
        // we put the chart into a panel
        ChartPanel chartPanel = new ChartPanel(chart);
        // default size
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        // add it to our application
        setContentPane(chartPanel);
        
        
        
        this.setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(iapractica.IAPracticaApp.class).getContext().getResourceMap(IndividualResult.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setName("Form"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

    private PieDataset createDataset(Individuo solucion) {
        DefaultPieDataset result = new DefaultPieDataset();
        
        double totalMateriasPrimas = 0;
        for (Integer integer : materiasPrimas) {
            totalMateriasPrimas+= integer;
        }
         
        result.setValue("Producto 1", solucion.getMateriaPrimaUsedPerProduct(1) *totalMateriasPrimas/100);
        result.setValue("Producto 2", solucion.getMateriaPrimaUsedPerProduct(2)*totalMateriasPrimas/100);
        result.setValue("Producto 3", solucion.getMateriaPrimaUsedPerProduct(3)*totalMateriasPrimas/100);
        result.setValue("Producto 4", solucion.getMateriaPrimaUsedPerProduct(4)*totalMateriasPrimas/100);
        result.setValue("Sobrantes", solucion.getMateriaPrimaSobrante()*totalMateriasPrimas/100);
        
        return result;
    }

    private JFreeChart createChart(PieDataset dataset, String title) {
        JFreeChart chart = ChartFactory.createPieChart3D(title,          // chart title
            dataset,                // data
            true,                   // include legend
            true,
            false);

        PiePlot3D plot = (PiePlot3D) chart.getPlot();
        plot.setStartAngle(290);
        plot.setDirection(Rotation.CLOCKWISE);
        plot.setForegroundAlpha(0.5f);
        return chart;
    }
}
