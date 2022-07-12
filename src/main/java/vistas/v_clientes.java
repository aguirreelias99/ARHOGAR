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
public class v_clientes extends javax.swing.JInternalFrame implements miInterface {

    private tableController tc;
    private Map<String, String> myData;
    conexion con = null;
    String currentField;
    javax.swing.JTextField idObj;
    private String msg;
    
    /**
     * Creates new form v_clientes
     */
    public v_clientes() {
        initComponents();
        currentField = "";

        //tf_id.addActionListener(null);
        //tf_id.setVisible(false);
        tc = new tableController();
        tc.init("cliente");
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
        myData.put("id_cliente",tf_cliente.getText());
        myData.put("cedula_cliente",tf_ci.getText() );
        myData.put("nombre",tf_nombre.getText());
        myData.put("apellido",tf_apellido.getText());
        myData.put("direccion",tf_direccion.getText());
        myData.put("celular",tf_celular.getText());
        myData.put("RUC",tf_ruc.getText());
        myData.put("id_localidad",tf_localidad.getText());


        
    }
    private void resetData(){
        myData.put("id_cliente","0");
        myData.put("cedula_cliente","");
        myData.put("nombre","");
        myData.put("apellido","");
        myData.put("direccion","");
        myData.put("celular","");
        myData.put("RUC","");
        myData.put("id_localidad","");
    }
    
     private void fillView(Map<String, String> data){
        for(Map.Entry<String, String> entry : data.entrySet()){
            String key = entry.getKey();
            String value = entry.getValue();
            switch(key){
                case"id_cliente":
                    tf_cliente.setText(value);
                    break;
                case "cedula_cliente":
                    tf_ci.setText(value);
                    break;
                case"nombre":
                    tf_nombre.setText(value);
                    break;
                case"apellido":
                    tf_apellido.setText(value);
                    break;
                case"direccion":
                    tf_direccion.setText(value);
                    break;
                case"celular":
                    tf_celular.setText(value);
                    break;
                case"RUC":
                    tf_ruc.setText(value);
                    break;
                case"id_localidad":
                    tf_localidad.setText(value);
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
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        tf_cliente = new javax.swing.JTextField();
        tf_ci = new javax.swing.JTextField();
        tf_nombre = new javax.swing.JTextField();
        tf_apellido = new javax.swing.JTextField();
        tf_direccion = new javax.swing.JTextField();
        tf_celular = new javax.swing.JTextField();
        tf_ruc = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        tf_localidad = new javax.swing.JTextField();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("ABM Cliente");

        jLabel1.setText("ID CLIENTE");

        jLabel2.setText("CEDULA");

        jLabel3.setText("NOMBRE");

        jLabel4.setText("APELLIDO");

        jLabel5.setText("DIRECCION");

        jLabel6.setText("CELULAR");

        jLabel7.setText("RUC");

        tf_cliente.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tf_clienteFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                tf_clienteFocusLost(evt);
            }
        });
        tf_cliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tf_clienteKeyTyped(evt);
            }
        });

        jLabel8.setText("LOCALIDAD");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8))
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(tf_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 140, Short.MAX_VALUE))
                    .addComponent(tf_ci)
                    .addComponent(tf_ruc)
                    .addComponent(tf_direccion)
                    .addComponent(tf_apellido)
                    .addComponent(tf_nombre)
                    .addComponent(tf_celular)
                    .addComponent(tf_localidad))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(tf_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tf_ci, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tf_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tf_apellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(tf_direccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tf_celular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_ruc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_localidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tf_clienteFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tf_clienteFocusGained
        // TODO add your handling code here:
        this.currentField = "id_cliente";
        this.tf_cliente.selectAll();
    }//GEN-LAST:event_tf_clienteFocusGained

    private void tf_clienteFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tf_clienteFocusLost
        // TODO add your handling code here:
        this.currentField = "id_cliente";
    }//GEN-LAST:event_tf_clienteFocusLost

    private void tf_clienteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tf_clienteKeyTyped

        if (evt.getKeyChar()=='\n') {
            this.imBuscar();
        }
        // TODO add your handling code here:

    }//GEN-LAST:event_tf_clienteKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField tf_apellido;
    private javax.swing.JTextField tf_celular;
    private javax.swing.JTextField tf_ci;
    private javax.swing.JTextField tf_cliente;
    private javax.swing.JTextField tf_direccion;
    private javax.swing.JTextField tf_localidad;
    private javax.swing.JTextField tf_nombre;
    private javax.swing.JTextField tf_ruc;
    // End of variables declaration//GEN-END:variables

@Override
    public int imModo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void imGrabar() {
        int id, rows = 0;
        id = Integer.parseInt(tf_cliente.getText());
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
        int rows = this.tc.deleteReg(tf_cliente.getText());
        if(rows<=0){
            msg = "No se ha podido eliminar el registro: "+tf_cliente.getText();
            System.out.println(msg);
            JOptionPane.showMessageDialog(this, msg, "¡Atencion...!", JOptionPane.OK_OPTION);
            
            
        }
        if (rows>0){
            msg = "EL registro: "+tf_cliente.getText()+" se ha eliminado correctamente";
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
        this.myData = this.tc.navegationReg(tf_cliente.getText(),"FIRST");
        this.fillView(this.myData);
    }

    @Override
    public void imSiguiente() {
        this.setData();
        //en este caso el id SI importa
        this.myData = this.tc.navegationReg(tf_cliente.getText(),"NEXT");
        this.fillView(this.myData);
    }

    @Override
    public void imAnterior() {
        this.setData();
        //en este caso el id SI importa
        this.myData = this.tc.navegationReg(tf_cliente.getText(),"PRIOR");
        this.fillView(this.myData);
    }

    @Override
    public void imUltimo() {
        this.setData();
        //en este caso el id no importa, pero se pasa para tener unificado el formato
        this.myData = this.tc.navegationReg(tf_cliente.getText(),"LAST");
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
    public void imFiltrar() {

        String sql;
       sql = "";
       
       if(currentField.equals("")){
            return; 
       }
       System.out.println();
       switch (currentField) {
           case "id_cliente":
               sql = "SELECT id_cliente AS codigo ," + 
                     "CONCAT(cedula_cliente, ' - ', nombre, ' - ', apellido) AS descripcion "+ 
                     "FROM cliente " + 
                     "WHERE nombre LIKE '%";
                System.out.println(sql);
            break;
           case "idOtro":
               
               break;
       }
        
        w_buscar frame = new w_buscar(sql, this.tf_cliente);
        frame.setVisible(true);
        VentanaPrincipal.desktop.add(frame);
        try {
            frame.setSelected(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.OK_OPTION);
        }
        
    }//end imFiltrar


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
}
