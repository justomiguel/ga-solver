/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package iapractica.views;

import genetics.individuos.Individuo;
import genetics.individuos.IndividuoSobrComparator;
import iapractica.controllers.MainPanelController;
import java.util.Collections;

import java.util.LinkedList;


/**
 *
 * @author Justo Vargas
 */
public final class FinalResultsView extends javax.swing.JFrame {

    private LinkedList<Individuo> myFinalPopulation;
    
    private LinkedList<Integer> materiasPrimas;
    
    private IndividualResult myWindow;
    
    private MainPanelController controller ;

    /**
     * Creates new form ResultsView
     */
    public FinalResultsView(MainPanelController aThis, LinkedList<Individuo> myFinalPopulation,  LinkedList<Integer> materiasPrimas) {
        initComponents();
        
        controller = aThis;
        
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(iapractica.IAPracticaApp.class).getContext().getResourceMap(FinalResultsView.class);
        this.setIconImage(resourceMap.getImageIcon("image.icon").getImage());
        
        Collections.sort(myFinalPopulation, new IndividuoSobrComparator(true));

        this.myFinalPopulation = myFinalPopulation;
        
        Object[][] objectToModel = new Object[myFinalPopulation.size()][8];

        for (int i = 0; i < myFinalPopulation.size(); i++) {
            Object[] filas = objectToModel[i];
            filas[0] = i + 1;
            Individuo cromosoma = myFinalPopulation.get(i);
            filas[1] = cromosoma.getFitnessValue();
            filas[2] = cromosoma.getProfit();
            filas[3] = cromosoma.getMateriaPrimaSobrante();
            filas[4] = cromosoma.getProductsSize(1);
            filas[5] = cromosoma.getProductsSize(2);
            filas[6] = cromosoma.getProductsSize(3);
            filas[7] = cromosoma.getProductsSize(4);
        }

        myTable.setModel(new javax.swing.table.DefaultTableModel(
                objectToModel,
                new String[]{
                    "Solucion Nro", "Valor Aptitud", "Ganancia","Materia Prima Sobrante", "Productos 1", "Productos 2", "Productos 3", "Productos 4"
                }));
        
       this.materiasPrimas = materiasPrimas;
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
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        myTable = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false; //Disallow the editing of any cell
            }
        };
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(iapractica.IAPracticaApp.class).getContext().getResourceMap(FinalResultsView.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setName("Form"); // NOI18N
        setResizable(false);
        getContentPane().setLayout(new java.awt.GridLayout(1, 0));

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.setAutoscrolls(true);
        jPanel1.setName("jPanel1"); // NOI18N
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setName("jPanel2"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        myTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"sr", "fv", null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Valor Aptitud", "Profit", "m1", "Title 4", "Title 5", "Title 6", "Title 7", "Title 8", "Title 9", "Title 10"
            }
        ));
        myTable.setName("myTable"); // NOI18N
        myTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                myTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(myTable);
        myTable.getColumnModel().getColumn(0).setHeaderValue(resourceMap.getString("myTable.columnModel.title0")); // NOI18N
        myTable.getColumnModel().getColumn(1).setHeaderValue(resourceMap.getString("myTable.columnModel.title1")); // NOI18N
        myTable.getColumnModel().getColumn(2).setHeaderValue(resourceMap.getString("myTable.columnModel.title2")); // NOI18N

        jLabel1.setFont(resourceMap.getFont("jLabel1.font")); // NOI18N
        jLabel1.setIcon(resourceMap.getIcon("jLabel1.icon")); // NOI18N
        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        jLabel3.setFont(resourceMap.getFont("jLabel3.font")); // NOI18N
        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 751, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(148, 148, 148)
                .addComponent(jLabel1)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 2, -1, -1));

        jButton1.setIcon(resourceMap.getIcon("jButton1.icon")); // NOI18N
        jButton1.setText(resourceMap.getString("jButton1.text")); // NOI18N
        jButton1.setName("jButton1"); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 380, 170, -1));

        jButton2.setIcon(resourceMap.getIcon("jButton2.icon")); // NOI18N
        jButton2.setText(resourceMap.getString("jButton2.text")); // NOI18N
        jButton2.setName("jButton2"); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 360, 230, -1));

        jButton3.setIcon(resourceMap.getIcon("jButton3.icon")); // NOI18N
        jButton3.setText(resourceMap.getString("jButton3.text")); // NOI18N
        jButton3.setName("jButton3"); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 400, 230, -1));

        jButton4.setIcon(resourceMap.getIcon("jButton4.icon")); // NOI18N
        jButton4.setText(resourceMap.getString("jButton4.text")); // NOI18N
        jButton4.setName("jButton4"); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 360, 229, -1));

        jButton5.setIcon(resourceMap.getIcon("jButton5.icon")); // NOI18N
        jButton5.setText(resourceMap.getString("jButton5.text")); // NOI18N
        jButton5.setName("jButton5"); // NOI18N
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 400, 230, -1));

        getContentPane().add(jPanel1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void myTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_myTableMouseClicked
        // TODO add your handling code here:
        int rowNumber = myTable.getSelectedRow();
        //if (rowNumber > 0){
        if (myWindow == null || !myWindow.isVisible()){
            myWindow = null;
            myWindow = new IndividualResult(String.valueOf(rowNumber), myFinalPopulation.get(rowNumber), materiasPrimas);
        }
         myWindow.setVisible(true);
         myWindow.setFocusable(true);
        //PopUpFactory.showConfirmPopUP(this, "Solucion Nro" + ( rowNumber + 1 ) + " \n " + myFinalPopulation.get(rowNumber));
        //}
    }//GEN-LAST:event_myTableMouseClicked

    @Override
    public void dispose() {
        super.dispose();
        controller.notifyClosed();
    }

    

    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        new VerImagen("images/poblacionAptitud.png");
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        new VerImagen("images/poblacionGanancias.png");
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        new VerImagen("images/promedioAptitud.png");
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        new VerImagen("images/promedioGanancias.png");
    }//GEN-LAST:event_jButton5ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable myTable;
    // End of variables declaration//GEN-END:variables

    private Individuo getIndBySobrante(LinkedList<Individuo> myFinalPopulation, Integer sobrante) {
        for (Individuo individuo : myFinalPopulation) {
            if (individuo.getMateriaPrimaSobrante() == sobrante){
                return individuo;
            }
        }
        return null;
    }
}
