/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.showMessageDialog;
import modelo.conexion;
import static sun.jvm.hotspot.HelloWorld.e;

/**
 *
 * @author Admin
 */
public class v_producto extends javax.swing.JInternalFrame implements miInterface{

    private tableController tc;
    private Map<String, String> myData;
    conexion con = null;
    String currentField;
    javax.swing.JTextField idObj;
    private String msg;
    
    /**
     * Creates new form v_producto
     */
    public v_producto() {
        initComponents();
        currentField = "";

        //tf_id.addActionListener(null);
        //tf_id.setVisible(false);
        tc = new tableController();
        tc.init("producto");
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
        myData.put("id_producto",tf_producto.getText());
        myData.put("nombre_producto",tf_nombre_prod.getText() );
        myData.put("marca",tf_marca_prod.getText());
        myData.put("id_categoria",tf_categoria.getText());
        myData.put("stock_actual",tf_stock.getText());
        myData.put("id_proveedor",tf_proveedor.getText());


        
    }
    private void resetData(){
        myData.put("id","0");
        myData.put("nombre_producto","");
        myData.put("marca","");
        myData.put("id_categoria","");
        myData.put("stock_actual","");
        myData.put("id_proveedor","");
    }
    
    private void fillView(Map<String, String> data){
        for(Map.Entry<String, String> entry : data.entrySet()){
            String key = entry.getKey();
            String value = entry.getValue();
            switch(key){
                case"id_producto":
                    tf_producto.setText(value);
                    break;
                case "nombre_producto":
                    tf_nombre_prod.setText(value);
                    break;
                case"marca":
                    tf_marca_prod.setText(value);
                    break;

                case"id_categoria":
                    tf_categoria.setText(value);
                    break;
                case"stock_actual":
                    tf_stock.setText(value);
                    break;
                case"id_proveedor":
                    tf_proveedor.setText(value);
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
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        tf_producto = new javax.swing.JTextField();
        tf_nombre_prod = new javax.swing.JTextField();
        tf_marca_prod = new javax.swing.JTextField();
        tf_categoria = new javax.swing.JTextField();
        tf_stock = new javax.swing.JTextField();
        tf_proveedor = new javax.swing.JTextField();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("ABM Producto");

        jLabel1.setText("ID PRODUCTO");

        jLabel2.setText("NOMBRE");

        jLabel3.setText("MARCA");

        jLabel5.setText("CATEGORIA");

        jLabel6.setText("STOCK ACTUAL");

        jLabel7.setText("PROVEEDOR");

        tf_producto.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tf_productoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                tf_productoFocusLost(evt);
            }
        });
        tf_producto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tf_productoKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addGap(42, 42, 42)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(tf_producto, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 113, Short.MAX_VALUE))
                            .addComponent(tf_nombre_prod)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(jLabel3)
                        .addGap(41, 41, 41)
                        .addComponent(tf_marca_prod))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addGap(41, 41, 41)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tf_proveedor)
                            .addComponent(tf_categoria)
                            .addComponent(tf_stock))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(tf_producto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tf_nombre_prod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tf_marca_prod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(tf_categoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tf_stock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_proveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tf_productoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tf_productoFocusGained
        // TODO add your handling code here:
        this.currentField = "id_producto";
        this.tf_producto.selectAll();
    }//GEN-LAST:event_tf_productoFocusGained

    private void tf_productoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tf_productoFocusLost
        // TODO add your handling code here:
        this.currentField = "id_producto";

    }//GEN-LAST:event_tf_productoFocusLost

    private void tf_productoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tf_productoKeyTyped

        if (evt.getKeyCode() == KeyEvent.VK_ENTER){
            imBuscar();
        }
        // TODO add your handling code here:
        
    }//GEN-LAST:event_tf_productoKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField tf_categoria;
    private javax.swing.JTextField tf_marca_prod;
    private javax.swing.JTextField tf_nombre_prod;
    private javax.swing.JTextField tf_producto;
    private javax.swing.JTextField tf_proveedor;
    private javax.swing.JTextField tf_stock;
    // End of variables declaration//GEN-END:variables

    @Override
    public int imModo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void imGrabar() {
        int id, rows = 0;
        id = Integer.parseInt(tf_producto.getText());
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
        int rows = this.tc.deleteReg(tf_producto.getText());
        if(rows<=0){
            msg = "No se ha podido eliminar el registro: "+tf_producto.getText();
            System.out.println(msg);
            JOptionPane.showMessageDialog(this, msg, "¡Atencion...!", JOptionPane.OK_OPTION);
            
            
        }
        if (rows>0){
            msg = "EL registro: "+tf_producto.getText()+" se ha eliminado correctamente";
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
        this.myData = this.tc.navegationReg(tf_producto.getText(),"FIRST");
        this.fillView(this.myData);
    }

    @Override
    public void imSiguiente() {
        this.setData();
        //en este caso el id SI importa
        this.myData = this.tc.navegationReg(tf_producto.getText(),"NEXT");
        this.fillView(this.myData);
    }

    @Override
    public void imAnterior() {
        this.setData();
        //en este caso el id SI importa
        this.myData = this.tc.navegationReg(tf_producto.getText(),"PRIOR");
        this.fillView(this.myData);
    }

    @Override
    public void imUltimo() {
        this.setData();
        //en este caso el id no importa, pero se pasa para tener unificado el formato
        this.myData = this.tc.navegationReg(tf_producto.getText(),"LAST");
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
           case "id_producto":
               sql = "SELECT id_producto AS codigo ," + 
                     "nombre_producto AS descripcion "+ 
                     "FROM producto " + 
                     "WHERE nombre_producto LIKE '%";
                System.out.println(sql);
            break;
           case "idOtro":
               
               break;
       }
        
        w_buscar frame = new w_buscar(sql, this.tf_producto);
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