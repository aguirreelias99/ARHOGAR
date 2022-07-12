
package vistas;

import modelo.Usuario;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import modelo.conexion;
import javax.swing.ImageIcon;


public class v_login extends javax.swing.JFrame {

  public conexion con_log;
    private boolean con = false;
    public int li_cant=0;
    public int li_intento=0;
//    public String ps_tabla="USUARIO";
    public ResultSet rs_usu;
    public int ii_usu=0;
    //Constructor
    public v_login() {
        initComponents();
        tf_usuario.requestFocus();
        tf_usuario.selectAll();
        
        this.setLocationRelativeTo(null);
      setDefaultCloseOperation (WindowConstants.DO_NOTHING_ON_CLOSE);
      this.getRootPane().setDefaultButton(b_ingresar);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        b_ingresar = new javax.swing.JButton();
        b_salir1 = new javax.swing.JButton();
        tf_usuario = new javax.swing.JTextField();
        tf_contrasena = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setText("USUARIO:");

        jLabel2.setText("CONTRASEÑA:");

        b_ingresar.setText("INGRESAR");
        b_ingresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_ingresarActionPerformed(evt);
            }
        });

        b_salir1.setText("SALIR");
        b_salir1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_salir1ActionPerformed(evt);
            }
        });

        tf_usuario.setText("Elias");
        tf_usuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_usuarioActionPerformed(evt);
            }
        });

        tf_contrasena.setText("1234");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addComponent(b_ingresar)
                .addGap(31, 31, 31)
                .addComponent(b_salir1)
                .addContainerGap(79, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(22, 22, 22)))
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tf_contrasena, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
                    .addComponent(tf_usuario))
                .addGap(35, 35, 35))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(tf_usuario, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(tf_contrasena, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(b_ingresar)
                    .addComponent(b_salir1))
                .addGap(36, 36, 36))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void b_salir1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_salir1ActionPerformed
        // TODO add your handling code here:
        int eleccion = JOptionPane.showConfirmDialog(rootPane, "Desea salir del Sistema?");
        if (eleccion == 0) {
            JOptionPane.showMessageDialog(rootPane, "El Sistema1 ha finalizado...");
            System.exit(0);
        }
    }//GEN-LAST:event_b_salir1ActionPerformed

    private void b_ingresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_ingresarActionPerformed
        String user = tf_usuario.getText();
        int li_valido = 0;
        char passArray[] = tf_contrasena.getPassword();
        if (user.length() == 0) {
            JOptionPane.showMessageDialog(rootPane, "Ingrese su nombre de usuario!");
            tf_usuario.requestFocus();
            return;
        }
        for (int i = 0; i < passArray.length; i++) {
            char c = passArray[i];
            if (!Character.isLetterOrDigit(c)) {
                li_valido++;
            }
        }
        if (li_valido > 0) {
            JOptionPane.showMessageDialog(rootPane, "La contrase\u00F1a tiene carcteres inválidos!");
            tf_contrasena.requestFocus();
            return;
        }

        String pass = new String(passArray);
        if (pass.length() == 0) {
            JOptionPane.showMessageDialog(rootPane, "Ingrese su password!");
            tf_contrasena.requestFocus();
            return;
        }

        con_log = new conexion();
        con_log.Conectar();
        Usuario u = new Usuario();
        u.imConect(con_log);
        u.setDatos(0, user, pass, 0);
        li_cant = u.imBuscarUsuClave();
        if (li_cant >= 1) {
            //Asignamos datos del usuario que instanciò la conexion
            con_log.setUserId(Integer.parseInt(u.getDato("id_usuario")));
            con_log.setUserName(u.getDato("nombre_usuario"));
            con_log.setGrupoId(Integer.parseInt(u.getDato("id_rol")));
            this.setVisible(false);
            this.dispose();
            //Instanciamos la ventana principal Esta parte no hace falta proque
            //la clase conexion tiene métodos static por lo que la clase es static, pero por
            //ahora no importa
            miInterfaceCon p = new VentanaPrincipal();
            p.imConect(con_log);
            //p instaceof Principal;
            //Gracias a la interface le pasamos la conecion que abrimos para no duplicar conexiones
            //            JOptionPane.showMessageDialog(null,"Bienvenido "+user);
        } else {
            JOptionPane.showMessageDialog(rootPane, "Usuario y/o contrase\u00F1a incorrectos");
            li_intento = li_intento + 1;
            if (li_intento >= 3) {
                JOptionPane.showMessageDialog(rootPane, "Sus 3 intentos han fallado...");
                setVisible(false);
                exit();
            }
        }
    }//GEN-LAST:event_b_ingresarActionPerformed

    private void tf_usuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_usuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_usuarioActionPerformed

       private void tf_contrasenaActionPerformed(java.awt.event.ActionEvent evt) {                                              

    }                                             

    private void tf_contrasenaKeyPressed(java.awt.event.KeyEvent evt) {                                         
        if (evt.getKeyCode() == evt.VK_ENTER) {
            String user = tf_usuario.getText();
            int li_valido = 0;
            char passArray[] = tf_contrasena.getPassword();
            if (user.length() == 0) {
                JOptionPane.showMessageDialog(rootPane, "Ingrese su nombre de usuario!");
                tf_usuario.requestFocus();
                return;
            }
            for (int i = 0; i < passArray.length; i++) {
                char c = passArray[i];
                if (!Character.isLetterOrDigit(c)) {
                    li_valido++;
                }
            }
            if (li_valido > 0) {
                JOptionPane.showMessageDialog(rootPane, "La contrase\u00F1a tiene carcteres inválidos!");
                tf_contrasena.requestFocus();
                return;
            }

            String pass = new String(passArray);
            if (pass.length() == 0) {
                JOptionPane.showMessageDialog(rootPane, "Ingrese su password!");
                tf_contrasena.requestFocus();
                return;
            }

            con_log = new conexion();
            con_log.Conectar();
            Usuario u = new Usuario();
            u.imConect(con_log);
            u.setDatos(0, user, pass, 0);
            li_cant = u.imBuscarUsuClave();
            if (li_cant >= 1) {
                //Asignamos datos del usuario que instanciò la conexion
                con_log.setUserId(Integer.parseInt(u.getDato("id_usuario")));
                con_log.setUserName(u.getDato("nombre"));
                con_log.setGrupoId(Integer.parseInt(u.getDato("id_rol")));
                this.setVisible(false);
                this.dispose();
                //Instanciamos la ventana principal
                miInterfaceCon p = new VentanaPrincipal();
                p.imConect(con_log);
                //p instaceof Principal;
                //Gracias a la interface le pasamos la conecion que abrimos para no duplicar conexiones
                //            JOptionPane.showMessageDialog(null,"Bienvenido "+user);
            } else {
                JOptionPane.showMessageDialog(rootPane, "Usuario y/o contrase\u00F1a incorrectos");
                li_intento = li_intento + 1;
                if (li_intento >= 3) {
                    JOptionPane.showMessageDialog(rootPane, "Sus 3 intentos han fallado...");
                    setVisible(false);
                    exit();
                }
            }

        }
    }                                        

    private void tf_usuarioFocusGained(java.awt.event.FocusEvent evt) {                                       
        tf_usuario.selectAll();
    }                                      

                                         
    private void exit() {
        System.exit(0);
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(v_login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(v_login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(v_login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(v_login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new v_login().setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton b_ingresar;
    private javax.swing.JButton b_salir1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField tf_contrasena;
    private javax.swing.JTextField tf_usuario;
    // End of variables declaration//GEN-END:variables
}
