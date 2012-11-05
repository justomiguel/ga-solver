/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package iapractica.views;

import genetics.individuos.Individuo;
import iapractica.views.popups.PopUpFactory;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;

/**
 *
 * @author Justo Vargas
 */
public class ResultsView extends javax.swing.JFrame {

    private LinkedList<Individuo> myFinalPopulation;

    /**
     * Creates new form ResultsView
     */
    public ResultsView() {
        initComponents();
        
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(iapractica.IAPracticaApp.class).getContext().getResourceMap(MainPanelView.class);
        this.setIconImage(resourceMap.getImageIcon("image.icon").getImage());
    }

    public void setViewContents(LinkedList<Individuo> myFinalPopulation) {

        Collections.sort(myFinalPopulation);

        this.myFinalPopulation = myFinalPopulation;

        LinkedList<Integer> nuevaLista = new LinkedList<Integer>();

        for (int i = 0; i < 10; i++) {
            nuevaLista.add(myFinalPopulation.get(i).getMateriaPrimaSobrante());
        }
                
        Collections.sort(nuevaLista);
        
        Object[][] objectToModel = new Object[10][7];

        for (int i = 0; i < 10; i++) {
            Object[] filas = objectToModel[i];
            filas[0] = i + 1;
            Individuo cromosoma = getIndBySobrante(myFinalPopulation, nuevaLista.get(i));
            filas[1] = cromosoma.getFitnessValue();
            filas[2] = cromosoma.getProfit();
            filas[3] = cromosoma.getProductsSize(1);
            filas[4] = cromosoma.getProductsSize(2);
            filas[5] = cromosoma.getProductsSize(3);
            filas[6] = cromosoma.getProductsSize(4);
        }

        myTable.setModel(new javax.swing.table.DefaultTableModel(
                objectToModel,
                new String[]{
                    "Solucion Nro", "Valor Aptitud", "Ganancia", "Productos 1", "Productos 2", "Productos 3", "Productos 4"
                }));

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
        jScrollPane1 = new javax.swing.JScrollPane();
        myTable = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false; //Disallow the editing of any cell
            }
        };
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(iapractica.IAPracticaApp.class).getContext().getResourceMap(ResultsView.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setName("Form"); // NOI18N
        setResizable(false);
        getContentPane().setLayout(new java.awt.GridLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.setAutoscrolls(true);
        jPanel1.setName("jPanel1"); // NOI18N

        jLabel1.setFont(resourceMap.getFont("jLabel1.font")); // NOI18N
        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

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
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(171, 171, 171)
                        .addComponent(jLabel1)))
                .addContainerGap(195, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(jButton1)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void myTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_myTableMouseClicked
        // TODO add your handling code here:
        int rowNumber = myTable.getSelectedRow();
        //if (rowNumber > 0){
        PopUpFactory.showConfirmPopUP(this, "Solucion Nro" + ( rowNumber + 1 ) + " \n " + myFinalPopulation.get(rowNumber));
        //}
    }//GEN-LAST:event_myTableMouseClicked
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
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
