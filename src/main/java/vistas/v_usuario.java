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
public class v_usuario extends javax.swing.JInternalFrame implements miInterface {

    private tableController tc;
    private Map<String, String> myData;
    conexion con = null;
    String currentField;
    javax.swing.JTextField idObj;
    private String msg;
    /**
     * Creates new form v_usuario
     */
    public v_usuario() {
    initComponents();
        currentField = "";

        //tf_id.addActionListener(null);
        //tf_id.setVisible(false);
        tc = new tableController();
        tc.init("usuario");
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
        myData.put("id_usuario",tf_usuario.getText());
        myData.put("id_rol",tf_rol.getText() );
        myData.put("nombre_usuario",tf_nom.getText());
        myData.put("contraseña_usuario",tf_contra.getText());
    
        
    }
    private void resetData(){
        myData.put("id_usuario","0");
        myData.put("id_rol","");
        myData.put("nombre_usuario","");
        myData.put("contraseña_usuario","");
       
    }
    
     private void fillView(Map<String, String> data){
        for(Map.Entry<String, String> entry : data.entrySet()){
            String key = entry.getKey();
            String value = entry.getValue();
            switch(key){
                case"id_usuario":
                    tf_usuario.setText(value);
                    break;
                case "id_rol":
                    tf_rol.setText(value);
                    break;
                case"nombre_usuario":
                    tf_nom.setText(value);
                    break;
                case"contraseña_usuario":
                    tf_contra.setText(value);
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
        jLabel4 = new javax.swing.JLabel();
        tf_usuario = new javax.swing.JTextField();
        tf_rol = new javax.swing.JTextField();
        tf_nom = new javax.swing.JTextField();
        tf_contra = new javax.swing.JPasswordField();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("ABM Usuario");

        jLabel1.setText("ID USUARIO");

        jLabel2.setText("ROL");

        jLabel3.setText("NOMBRE");

        jLabel4.setText("CONTRASEÑA");

        tf_usuario.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tf_usuarioFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                tf_usuarioFocusLost(evt);
            }
        });
        tf_usuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tf_usuarioKeyTyped(evt);
            }
        });

        tf_contra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_contraActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3))
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(tf_usuario, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 140, Short.MAX_VALUE))
                    .addComponent(tf_rol)
                    .addComponent(tf_nom)
                    .addComponent(tf_contra))
                .addGap(16, 16, 16))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(tf_usuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tf_rol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tf_nom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(tf_contra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(39, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tf_usuarioFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tf_usuarioFocusGained
        // TODO add your handling code here:
        this.currentField = "id_cliente";
        this.tf_usuario.selectAll();
    }//GEN-LAST:event_tf_usuarioFocusGained

    private void tf_usuarioFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tf_usuarioFocusLost
        // TODO add your handling code here:
        this.currentField = "id_cliente";
    }//GEN-LAST:event_tf_usuarioFocusLost

    private void tf_usuarioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tf_usuarioKeyTyped

        if (evt.getKeyChar()=='\n') {
            this.imBuscar();
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_usuarioKeyTyped

    private void tf_contraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_contraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_contraActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField tf_contra;
    private javax.swing.JTextField tf_nom;
    private javax.swing.JTextField tf_rol;
    private javax.swing.JTextField tf_usuario;
    // End of variables declaration//GEN-END:variables

    public void imGrabar() {
        int id, rows = 0;
        id = Integer.parseInt(tf_usuario.getText());
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
        int rows = this.tc.deleteReg(tf_usuario.getText());
        if(rows<=0){
            msg = "No se ha podido eliminar el registro: "+tf_usuario.getText();
            System.out.println(msg);
            JOptionPane.showMessageDialog(this, msg, "¡Atencion...!", JOptionPane.OK_OPTION);
            
            
        }
        if (rows>0){
            msg = "EL registro: "+tf_usuario.getText()+" se ha eliminado correctamente";
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
        this.myData = this.tc.navegationReg(tf_usuario.getText(),"FIRST");
        this.fillView(this.myData);
    }

    @Override
    public void imSiguiente() {
        this.setData();
        //en este caso el id SI importa
        this.myData = this.tc.navegationReg(tf_usuario.getText(),"NEXT");
        this.fillView(this.myData);
    }

    @Override
    public void imAnterior() {
        this.setData();
        //en este caso el id SI importa
        this.myData = this.tc.navegationReg(tf_usuario.getText(),"PRIOR");
        this.fillView(this.myData);
    }

    @Override
    public void imUltimo() {
        this.setData();
        //en este caso el id no importa, pero se pasa para tener unificado el formato
        this.myData = this.tc.navegationReg(tf_usuario.getText(),"LAST");
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
    public void imFiltrar() {

        String sql;
       sql = "";
       
       if(currentField.equals("")){
            return; 
       }
       System.out.println();
       switch (currentField) {
           case "id_usuario":
               sql = "SELECT id_usuario AS codigo ," + 
                     "nombre_usuario AS descripcion "+ 
                     "FROM usuario " + 
                     "WHERE nombre_usuario LIKE '%";
                System.out.println(sql);
            break;
           case "idOtro":
               
               break;
       }
        
        w_buscar frame = new w_buscar(sql, this.tf_usuario);
        frame.setVisible(true);
        VentanaPrincipal.desktop.add(frame);
        try {
            frame.setSelected(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.OK_OPTION);
        }
        
    }//end imFiltrar

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
    public int imModo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
