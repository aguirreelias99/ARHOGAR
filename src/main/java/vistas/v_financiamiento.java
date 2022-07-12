/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import modelo.conexion;

/**
 *
 * @author Admin
 */
public class v_financiamiento extends javax.swing.JInternalFrame implements miInterface {

     private tableController tc;
    private Map<String, String> myData;
    conexion con = null;
    String currentField;
    javax.swing.JTextField idObj;
    private String msg;
    /**
     * Creates new form v_financiamiento
     */
    public v_financiamiento() {
        initComponents();
        currentField = "";

        //tf_id.addActionListener(null);
        //tf_id.setVisible(false);
        tc = new tableController();
        tc.init("financiamiento");
        myData = new HashMap<String, String>();
        //this.tf_id.addKeyListener(escucha);
//        this.tf_id.addKeyListener(new KeyAdapter(){
//            public void keyPressed(KeyEvent e){
//                System.out.println(e.getKeyCode());
//                if(e.getKeyCode() == 13){
//                    System.out.println("enter ");
//                }
//            }
//        });
    }

     private void setData(){
        myData.put("id_producto",tf_prod_fin.getText());
        myData.put("cantidad_cuota",tf_cant_cuotas.getText() );
        myData.put("monto_cuota",tf_monto_cuota.getText());

        
    }
    private void resetData(){
        myData.put("id_producto","");
        myData.put("cantidad_cuota","");
        myData.put("monto_cuota","");

    }
    
    private void fillView(Map<String, String> data){
        for(Map.Entry<String, String> entry : data.entrySet()){
            String key = entry.getKey();
            String value = entry.getValue();
            switch(key){
                case"id_producto":
                    tf_prod_fin.setText(value);
                    break;
                case "cantidad_cuota":
                    tf_cant_cuotas.setText(value);
                    break;
                case"monto_cuota":
                    tf_monto_cuota.setText(value);
                    break;

            }//end swich
        }//end for
    }//end fillView
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
        tf_prod_fin = new javax.swing.JTextField();
        tf_cant_cuotas = new javax.swing.JTextField();
        tf_monto_cuota = new javax.swing.JTextField();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("ABM Financiamiento");

        jLabel1.setText("ID PRODUCTO");

        jLabel2.setText("CANTIDAD DE CUOTAS");

        jLabel3.setText("MONTO DE CUOTAS");

        tf_prod_fin.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tf_prod_finFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                tf_prod_finFocusLost(evt);
            }
        });
        tf_prod_fin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tf_prod_finKeyTyped(evt);
            }
        });

        tf_cant_cuotas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_cant_cuotasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3))
                .addGap(42, 42, 42)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tf_monto_cuota)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(tf_prod_fin, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 158, Short.MAX_VALUE))
                    .addComponent(tf_cant_cuotas))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(tf_prod_fin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tf_cant_cuotas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tf_monto_cuota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(180, 180, 180))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tf_prod_finFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tf_prod_finFocusGained
        // TODO add your handling code here:
        this.currentField = "id_producto";
        this.tf_prod_fin.selectAll();
    }//GEN-LAST:event_tf_prod_finFocusGained

    private void tf_prod_finFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tf_prod_finFocusLost
        // TODO add your handling code here:
        this.currentField = "id_producto";
    }//GEN-LAST:event_tf_prod_finFocusLost

    private void tf_prod_finKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tf_prod_finKeyTyped

        if (evt.getKeyChar()=='\n') {
            this.imBuscar();
        }
        // TODO add your handling code here:

    }//GEN-LAST:event_tf_prod_finKeyTyped

    private void tf_cant_cuotasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_cant_cuotasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_cant_cuotasActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField tf_cant_cuotas;
    private javax.swing.JTextField tf_monto_cuota;
    private javax.swing.JTextField tf_prod_fin;
    // End of variables declaration//GEN-END:variables

    @Override
    public int imModo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void imGrabar() {
        int id, rows = 0;
        id = Integer.parseInt(tf_prod_fin.getText());
        if(id>0){
            this.imActualizar();
            return;
            
        }
        this.setData();
        rows = this.tc.createReg(this.myData);
        this.fillView(myData);
    }

    @Override
    public void imActualizar() {

        System.out.println("V imActualizar");
        this.setData();
        int rows = this.tc.updateReg(myData);
        this.resetData();
        this.fillView(myData);
    }

    @Override
    public void imBorrar() {

        this.setData();
        int rows = this.tc.deleteReg(tf_prod_fin.getText());
        if(rows<=0){
            msg = "No se ha podido eliminar el registro: "+tf_prod_fin.getText();
            System.out.println(msg);
            JOptionPane.showMessageDialog(this, msg, "¡Atencion...!", JOptionPane.OK_OPTION);
            
            
        }
        if (rows>0){
            msg = "EL registro: "+tf_prod_fin.getText()+" se ha eliminado correctamente";
            System.out.println(msg);
            JOptionPane.showMessageDialog(this, msg, "¡Atención...!", JOptionPane.YES_OPTION);
        }
        this.resetData();
        this.fillView(myData);
    }

    @Override
    public void imNuevo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void imBuscar() {
        this.setData();
        //Aqui podriamos verificar que tfid no sea cero
        this.myData = this.tc.searchById(this.myData);
        //también aqui se podría verificar que traiga los datos, sino invocar resteData  antes del fill
        this.fillView(myData);
             
    }

    @Override
    public void imPrimero() {
        this.setData();
        //en este caso el id no importa, pero se pasa para tener unificado el formato
        this.myData = this.tc.navegationReg(tf_prod_fin.getText(),"FIRST");
        this.fillView(this.myData);
    }

    @Override
    public void imSiguiente() {
        this.setData();
        //en este caso el id SI importa
        this.myData = this.tc.navegationReg(tf_prod_fin.getText(),"NEXT");
        this.fillView(this.myData);
    }

    @Override
    public void imAnterior() {
        this.setData();
        //en este caso el id SI importa
        this.myData = this.tc.navegationReg(tf_prod_fin.getText(),"PRIOR");
        this.fillView(this.myData);
    }

    @Override
    public void imUltimo() {
        this.setData();
        //en este caso el id no importa, pero se pasa para tener unificado el formato
        this.myData = this.tc.navegationReg(tf_prod_fin.getText(),"LAST");
        this.fillView(this.myData);
    }

    @Override
    public void imImprimir() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void imInsDet() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void imDelDet() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void imCerrar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int imBuscarUsuClave() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }



    @Override
    public boolean imAbierto() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void imAbrir() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void imConect(conexion coneccion) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void imFiltrar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
