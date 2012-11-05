/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package iapractica.views;

import com.frre.cemami.utils.Utils;
import iapractica.views.myPanels.MyResultsPanel;
import genetics.individuos.Individuo;
import iapractica.controllers.PDFDesigner;
import java.util.LinkedList;
import javax.swing.JScrollPane;
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
    private JScrollPane jScrollPane1 = new JScrollPane();
    
    private String resultadoNro;
    private Individuo solucion;

    /**
     * Creates new form IndividualResult
     */
    public IndividualResult(String resultadoNro, Individuo solucion, LinkedList<Integer> materiasPrimas) {
        initComponents();

        this.setTitle("GA-Solver - REsultado N° " + resultadoNro);

        this.materiasPrimas = materiasPrimas;
                
        this.solucion = solucion;
        this.resultadoNro = resultadoNro;
        
        this.distribucionMateria.setText(solucion.toStringResult());
        
        this.profit.setText(" $ "+String.valueOf(solucion.getProfit()));
        this.sobrantes.setText(String.valueOf(solucion.getMateriaPrimaSobrante()));
        this.solutionNumber.setText(resultadoNro);
        
        MyResultsPanel panel = new MyResultsPanel();

        for (int i = 0; i < materiasPrimas.size(); i++) {
            Integer materiaPrima = materiasPrimas.get(i);
            // This will create the dataset 
            PieDataset dataset = createDataset(solucion, i);
            // based on the dataset we create the chart
            JFreeChart chart = createChart(dataset, "Asignacion de Materias Prima " + ( i + 1 ));
            // we put the chart into a panel
            ChartPanel chartPanel = new ChartPanel(chart);
            // default size
            chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));

            Utils.saveToFile(chart, "AsignaciondeMateriasPrima" + ( i + 1 ));
            panel.add(chartPanel);
        }

        jScrollPane1.setViewportView(panel);
        // add it to our application
        this.chartContainer.add(jScrollPane1);

       
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        distribucionMateria = new javax.swing.JTextArea();
        chartContainer = new javax.swing.JPanel();
        solutionNumber = new javax.swing.JLabel();
        profit = new javax.swing.JLabel();
        sobrantes = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(iapractica.IAPracticaApp.class).getContext().getResourceMap(IndividualResult.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setName("Form"); // NOI18N
        getContentPane().setLayout(new java.awt.GridLayout(1, 0));

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.setName("jPanel1"); // NOI18N

        jLabel1.setFont(resourceMap.getFont("jLabel1.font")); // NOI18N
        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        jLabel2.setFont(resourceMap.getFont("jLabel2.font")); // NOI18N
        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        jLabel3.setFont(resourceMap.getFont("jLabel3.font")); // NOI18N
        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N

        jLabel4.setFont(resourceMap.getFont("jLabel4.font")); // NOI18N
        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N

        jScrollPane2.setName("jScrollPane2"); // NOI18N

        distribucionMateria.setEditable(false);
        distribucionMateria.setColumns(20);
        distribucionMateria.setLineWrap(true);
        distribucionMateria.setRows(5);
        distribucionMateria.setWrapStyleWord(true);
        distribucionMateria.setName("distribucionMateria"); // NOI18N
        jScrollPane2.setViewportView(distribucionMateria);

        chartContainer.setBorder(javax.swing.BorderFactory.createTitledBorder(null, resourceMap.getString("chartContainer.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, resourceMap.getFont("chartContainer.border.titleFont"))); // NOI18N
        chartContainer.setName("chartContainer"); // NOI18N
        chartContainer.setLayout(new java.awt.GridLayout(1, 0));

        solutionNumber.setFont(resourceMap.getFont("solutionNumber.font")); // NOI18N
        solutionNumber.setText(resourceMap.getString("solutionNumber.text")); // NOI18N
        solutionNumber.setName("solutionNumber"); // NOI18N

        profit.setText(resourceMap.getString("profit.text")); // NOI18N
        profit.setName("profit"); // NOI18N

        sobrantes.setText(resourceMap.getString("sobrantes.text")); // NOI18N
        sobrantes.setName("sobrantes"); // NOI18N

        jButton1.setText(resourceMap.getString("jButton1.text")); // NOI18N
        jButton1.setName("jButton1"); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(chartContainer, javax.swing.GroupLayout.DEFAULT_SIZE, 658, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(181, 181, 181)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(solutionNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 95, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 441, Short.MAX_VALUE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 658, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(sobrantes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(profit, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(380, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(solutionNumber)
                    .addComponent(jButton1))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(profit))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(sobrantes))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chartContainer, javax.swing.GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(jPanel1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
// TODO add your handling code here:
    PDFDesigner.makePDF(Integer.parseInt(resultadoNro), solucion);
}//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel chartContainer;
    private javax.swing.JTextArea distribucionMateria;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel profit;
    private javax.swing.JLabel sobrantes;
    private javax.swing.JLabel solutionNumber;
    // End of variables declaration//GEN-END:variables
    private PieDataset createDataset(Individuo solucion, int materiaPrimaNumber) {
        DefaultPieDataset result = new DefaultPieDataset();

        double totalMateriasPrimas = materiasPrimas.get(materiaPrimaNumber);
        double sumatoriaDeMateriaPrimaUsada = 0;
        for (int i = 0; i < 4; i++) {
            double materiaPrimaUsed = solucion.getMateriaPrimaUsedPerProduct((i+1), materiaPrimaNumber)*solucion.getProductsSize(i+1);
            result.setValue("Producto "+(i+1),  materiaPrimaUsed* totalMateriasPrimas / 100);
            sumatoriaDeMateriaPrimaUsada += materiaPrimaUsed;
        }

        result.setValue("Sobrantes", (totalMateriasPrimas-sumatoriaDeMateriaPrimaUsada) * totalMateriasPrimas / 100);

        return result;
    }

    private JFreeChart createChart(PieDataset dataset, String title) {
        JFreeChart chart = ChartFactory.createPieChart3D(title, // chart title
                dataset, // data
                true, // include legend
                true,
                false);

        PiePlot3D plot = (PiePlot3D) chart.getPlot();
        plot.setStartAngle(290);
        plot.setDirection(Rotation.CLOCKWISE);
        plot.setForegroundAlpha(0.5f);
        return chart;
    }
}