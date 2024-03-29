/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import modelo.conexion;

/**
 *
 * @author Admin
 */
public class VentanaPrincipal extends javax.swing.JFrame implements miInterfaceCon {
    private static conexion con = null;
    public JInternalFrame[] w_abiertos;

    /**
     * Creates new form VentanaPrincipal
     */
    public VentanaPrincipal() {
        initComponents();
        // desktop.setBorder(new Fondo());
        //  this.setExtendedState(VentanaPrincipal.MAXIMIZED_BOTH);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setTitle("SGA Sistema de Gesti\u00F3n Administrativa - " + " Usuario: " + con.getUserName());
  
    }

    VentanaPrincipal(conexion con_log) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
/**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        desktop = new javax.swing.JDesktopPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        miCerrar = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        miSalir = new javax.swing.JMenuItem();
        jMenu13 = new javax.swing.JMenu();
        mePrimero = new javax.swing.JMenuItem();
        meSiguiente = new javax.swing.JMenuItem();
        meAnterior = new javax.swing.JMenuItem();
        meUltimo = new javax.swing.JMenuItem();
        meBorrar = new javax.swing.JMenuItem();
        meNuevo = new javax.swing.JMenuItem();
        meGuardar = new javax.swing.JMenuItem();
        meBuscar = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        personas = new javax.swing.JMenuItem();
        empleados = new javax.swing.JMenuItem();
        moneda = new javax.swing.JMenuItem();
        usuario = new javax.swing.JMenuItem();
        categoria = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenuItem11 = new javax.swing.JMenuItem();
        jMenuItem12 = new javax.swing.JMenuItem();
        jMenuItem13 = new javax.swing.JMenuItem();
        jMenuItem14 = new javax.swing.JMenuItem();
        jMenuItem15 = new javax.swing.JMenuItem();
        jMenuItem16 = new javax.swing.JMenuItem();
        jMenuItem17 = new javax.swing.JMenuItem();
        compra = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        mmStock = new javax.swing.JMenu();
        jMenuItem18 = new javax.swing.JMenuItem();
        producto = new javax.swing.JMenuItem();
        jMenu10 = new javax.swing.JMenu();
        jMenu11 = new javax.swing.JMenu();
        jMenu6 = new javax.swing.JMenu();
        jMenu1 = new javax.swing.JMenu();
        proveedores = new javax.swing.JMenuItem();
        productos = new javax.swing.JMenuItem();
        jMenu9 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu12 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenu7 = new javax.swing.JMenu();
        jMenuItem21 = new javax.swing.JMenuItem();
        jMenuItem22 = new javax.swing.JMenuItem();
        jMenu8 = new javax.swing.JMenu();
        jMenu14 = new javax.swing.JMenu();
        jMenuItem19 = new javax.swing.JMenuItem();
        jMenuItem20 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout desktopLayout = new javax.swing.GroupLayout(desktop);
        desktop.setLayout(desktopLayout);
        desktopLayout.setHorizontalGroup(
            desktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 910, Short.MAX_VALUE)
        );
        desktopLayout.setVerticalGroup(
            desktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 590, Short.MAX_VALUE)
        );

        jMenu3.setText("Archivo");

        jMenuItem1.setText("Nuevo");
        jMenu3.add(jMenuItem1);
        jMenu3.add(jSeparator1);

        miCerrar.setText("Cerrar");
        miCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miCerrarActionPerformed(evt);
            }
        });
        jMenu3.add(miCerrar);
        jMenu3.add(jSeparator2);

        miSalir.setText("Salir");
        miSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miSalirActionPerformed(evt);
            }
        });
        jMenu3.add(miSalir);

        jMenuBar1.add(jMenu3);

        jMenu13.setText("Editar");

        mePrimero.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_HOME, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        mePrimero.setText("Primero");
        mePrimero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mePrimeroActionPerformed(evt);
            }
        });
        jMenu13.add(mePrimero);

        meSiguiente.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_UP, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        meSiguiente.setText("Siguiente");
        meSiguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                meSiguienteActionPerformed(evt);
            }
        });
        jMenu13.add(meSiguiente);

        meAnterior.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_DOWN, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        meAnterior.setText("Anterior");
        meAnterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                meAnteriorActionPerformed(evt);
            }
        });
        jMenu13.add(meAnterior);

        meUltimo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_END, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        meUltimo.setText("Ultimo");
        meUltimo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                meUltimoActionPerformed(evt);
            }
        });
        jMenu13.add(meUltimo);

        meBorrar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_DELETE, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        meBorrar.setText("Borrar");
        meBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                meBorrarActionPerformed(evt);
            }
        });
        jMenu13.add(meBorrar);

        meNuevo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        meNuevo.setText("Nuevo");
        meNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                meNuevoActionPerformed(evt);
            }
        });
        jMenu13.add(meNuevo);

        meGuardar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F5, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        meGuardar.setText("Guardar");
        meGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                meGuardarActionPerformed(evt);
            }
        });
        jMenu13.add(meGuardar);

        meBuscar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_B, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        meBuscar.setText("Buscar");
        meBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                meBuscarActionPerformed(evt);
            }
        });
        meBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                meBuscarKeyPressed(evt);
            }
        });
        jMenu13.add(meBuscar);
        jMenu13.add(jSeparator3);

        jMenuItem6.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItem6.setText("Ins. Detalle");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu13.add(jMenuItem6);

        jMenuItem7.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItem7.setText("Del. Detalle");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu13.add(jMenuItem7);

        jMenuBar1.add(jMenu13);

        jMenu5.setText("Comunes");

        personas.setText("Personas");
        personas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                personasActionPerformed(evt);
            }
        });
        jMenu5.add(personas);

        empleados.setText("Empleados");
        empleados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                empleadosActionPerformed(evt);
            }
        });
        jMenu5.add(empleados);

        moneda.setText("Moneda");
        jMenu5.add(moneda);

        usuario.setText("Usuarios");
        usuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usuarioActionPerformed(evt);
            }
        });
        jMenu5.add(usuario);

        categoria.setText("Categoria");
        categoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                categoriaActionPerformed(evt);
            }
        });
        jMenu5.add(categoria);

        jMenuBar1.add(jMenu5);

        jMenu2.setText("Fichas");

        jMenuItem8.setText("Usuario");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem8);

        jMenuItem9.setText("Rol");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem9);

        jMenuItem10.setText("Producto");
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem10ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem10);

        jMenuItem11.setText("Clientes");
        jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem11ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem11);

        jMenuItem12.setText("Proveedor");
        jMenuItem12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem12ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem12);

        jMenuItem13.setText("Condición de Pago");
        jMenu2.add(jMenuItem13);

        jMenuItem14.setText("Financiamiento");
        jMenuItem14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem14ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem14);

        jMenuItem15.setText("Cargo");
        jMenuItem15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem15ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem15);

        jMenuItem16.setText("Empleados");
        jMenuItem16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem16ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem16);

        jMenuItem17.setText("Categoria");
        jMenuItem17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem17ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem17);

        compra.setText("Compras");
        compra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                compraActionPerformed(evt);
            }
        });
        jMenu2.add(compra);

        jMenuBar1.add(jMenu2);

        jMenu4.setText("Stock");

        mmStock.setText("Tabla Base");

        jMenuItem18.setText("Categoria");
        jMenuItem18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem18ActionPerformed(evt);
            }
        });
        mmStock.add(jMenuItem18);

        producto.setText("Producto");
        producto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                productoActionPerformed(evt);
            }
        });
        mmStock.add(producto);

        jMenu4.add(mmStock);

        jMenu10.setText("Procesos");
        jMenu4.add(jMenu10);

        jMenu11.setText("Informes");
        jMenu4.add(jMenu11);

        jMenuBar1.add(jMenu4);

        jMenu6.setText("Compras");

        jMenu1.setText("Tabla Base");

        proveedores.setText("Proveedores");
        proveedores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                proveedoresActionPerformed(evt);
            }
        });
        jMenu1.add(proveedores);

        productos.setText("Productos");
        productos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                productosActionPerformed(evt);
            }
        });
        jMenu1.add(productos);

        jMenu6.add(jMenu1);

        jMenu9.setText("Procesos");

        jMenuItem3.setText("jMenuItem2");
        jMenu9.add(jMenuItem3);

        jMenu6.add(jMenu9);

        jMenu12.setText("Informe");

        jMenuItem4.setText("jMenuItem2");
        jMenu12.add(jMenuItem4);

        jMenu6.add(jMenu12);

        jMenuBar1.add(jMenu6);

        jMenu7.setText("Ventas");

        jMenuItem21.setText("Clientes");
        jMenu7.add(jMenuItem21);

        jMenuItem22.setText("Ventas");
        jMenuItem22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem22ActionPerformed(evt);
            }
        });
        jMenu7.add(jMenuItem22);

        jMenuBar1.add(jMenu7);

        jMenu8.setText("Caja");
        jMenuBar1.add(jMenu8);

        jMenu14.setText("Seguridad");

        jMenuItem19.setText("Roles");
        jMenu14.add(jMenuItem19);

        jMenuItem20.setText("Usuarios");
        jMenu14.add(jMenuItem20);

        jMenuBar1.add(jMenu14);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(desktop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(desktop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void miSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miSalirActionPerformed
        int eleccion = JOptionPane.showConfirmDialog(null, "Desea salir del Sistema?");
        if (eleccion == 0) {
            JOptionPane.showMessageDialog(null, "El Sistema1 ha finalizado...");
            System.exit(0);
        }
    }//GEN-LAST:event_miSalirActionPerformed

    private void miCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miCerrarActionPerformed
         miInterface ve = getCurrentWindow();
        if (ve == null) {
            return;
        }
        ve.imCerrar();
    }//GEN-LAST:event_miCerrarActionPerformed

    private void mePrimeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mePrimeroActionPerformed
        miInterface ve = getCurrentWindow();
        if (ve == null) {
            return;
        }
        ve.imPrimero(); //lo unico que cambia en los otros es esta parte
    }//GEN-LAST:event_mePrimeroActionPerformed

    private void meUltimoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_meUltimoActionPerformed

        miInterface ve = getCurrentWindow();
        if (ve == null) {
            return;
        }
        ve.imUltimo(); //lo unico que cambia en los otros es esta parte
    }//GEN-LAST:event_meUltimoActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItem11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem11ActionPerformed
        // TODO add your handling code here:
        v_clientes v = new v_clientes();
        showWindow(v);
    }//GEN-LAST:event_jMenuItem11ActionPerformed

    private void jMenuItem12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem12ActionPerformed

        v_proveedor v = new v_proveedor();
        showWindow(v);        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem12ActionPerformed

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
        v_rol v = new v_rol();
        showWindow(v);
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void meSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_meSiguienteActionPerformed

        miInterface ve = getCurrentWindow();
        if (ve == null) {
            return;
        }
        ve.imSiguiente(); //lo unico que cambia en los otros es esta parte
    }//GEN-LAST:event_meSiguienteActionPerformed

    private void meAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_meAnteriorActionPerformed

        miInterface ve = getCurrentWindow();
        if (ve == null) {
            return;
        }
        ve.imAnterior(); //lo unico que cambia en los otros es esta parte
    }//GEN-LAST:event_meAnteriorActionPerformed

    private void meBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_meBorrarActionPerformed

          miInterface ve = getCurrentWindow();
        if (ve == null) {
            return;
        }
        ve.imBorrar(); //lo unico que cambia en los otros es esta parte
    }//GEN-LAST:event_meBorrarActionPerformed

    private void meNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_meNuevoActionPerformed
miInterface ve = getCurrentWindow();

        if (ve == null) {

            return;

        }

        ve.imNuevo();//lo unico que cambia en los otros es esta parte
    }//GEN-LAST:event_meNuevoActionPerformed

    private void meGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_meGuardarActionPerformed
   miInterface ve = getCurrentWindow();

        if (ve == null) {

            return;

        }

        ve.imGrabar(); //lo unico que cambia en los otros es esta parte
    }//GEN-LAST:event_meGuardarActionPerformed

    private void meBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_meBuscarActionPerformed
        // TODO add your handling code here:
         miInterface ve = getCurrentWindow();
        if (ve == null) {
            return;
        }
        ve.imFiltrar();
    }//GEN-LAST:event_meBuscarActionPerformed

    private void meBuscarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_meBuscarKeyPressed
        // TODO add your handling code here:
         miInterface ve = getCurrentWindow();
        if (ve == null) {
            return;
        }
        ve.imFiltrar();
    }//GEN-LAST:event_meBuscarKeyPressed

    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem10ActionPerformed
        v_producto v = new v_producto();
        showWindow(v);
    }//GEN-LAST:event_jMenuItem10ActionPerformed

    private void jMenuItem14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem14ActionPerformed
    
        v_financiamiento v = new v_financiamiento();
        showWindow(v);
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem14ActionPerformed

    private void jMenuItem15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem15ActionPerformed

        v_cargo v = new v_cargo();
        showWindow(v);
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem15ActionPerformed

    private void jMenuItem16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem16ActionPerformed

        v_empleados v = new v_empleados();
        showWindow(v);
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem16ActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed

        v_usuario v = new v_usuario();
        showWindow(v);
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void jMenuItem17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem17ActionPerformed
        

// TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem17ActionPerformed

    private void compraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_compraActionPerformed
        // TODO add your handling code here:
        v_compra v = new v_compra();
        showWindow(v);
    }//GEN-LAST:event_compraActionPerformed

    private void jMenuItem18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem18ActionPerformed

        v_categoria v = new v_categoria();
        showWindow(v);
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem18ActionPerformed

    private void proveedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_proveedoresActionPerformed

        v_proveedor v = new v_proveedor();
        showWindow(v);
        // TODO add your handling code here:
    }//GEN-LAST:event_proveedoresActionPerformed

    private void jMenuItem22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem22ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem22ActionPerformed

    private void personasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_personasActionPerformed


        v_personas v = new v_personas();
        showWindow(v);
        // TODO add your handling code here:
    }//GEN-LAST:event_personasActionPerformed

    private void usuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usuarioActionPerformed

         v_usuario v = new v_usuario();
        showWindow(v);
        // TODO add your handling code here:
    }//GEN-LAST:event_usuarioActionPerformed

    private void categoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_categoriaActionPerformed

        v_categoria v = new v_categoria();
        showWindow(v);
        // TODO add your handling code here:
    }//GEN-LAST:event_categoriaActionPerformed

    private void empleadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_empleadosActionPerformed

        v_empleados v = new v_empleados();
        showWindow(v);
        // TODO add your handling code here:
    }//GEN-LAST:event_empleadosActionPerformed

    private void productoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_productoActionPerformed

        v_producto v = new v_producto();
        showWindow(v);
        // TODO add your handling code here:
    }//GEN-LAST:event_productoActionPerformed

    private void productosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_productosActionPerformed

        v_producto v = new v_producto();
        showWindow(v);
        // TODO add your handling code here:
    }//GEN-LAST:event_productosActionPerformed

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
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem categoria;
    private javax.swing.JMenuItem compra;
    public static javax.swing.JDesktopPane desktop;
    private javax.swing.JMenuItem empleados;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu10;
    private javax.swing.JMenu jMenu11;
    private javax.swing.JMenu jMenu12;
    private javax.swing.JMenu jMenu13;
    private javax.swing.JMenu jMenu14;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenu jMenu8;
    private javax.swing.JMenu jMenu9;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem13;
    private javax.swing.JMenuItem jMenuItem14;
    private javax.swing.JMenuItem jMenuItem15;
    private javax.swing.JMenuItem jMenuItem16;
    private javax.swing.JMenuItem jMenuItem17;
    private javax.swing.JMenuItem jMenuItem18;
    private javax.swing.JMenuItem jMenuItem19;
    private javax.swing.JMenuItem jMenuItem20;
    private javax.swing.JMenuItem jMenuItem21;
    private javax.swing.JMenuItem jMenuItem22;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JMenuItem meAnterior;
    private javax.swing.JMenuItem meBorrar;
    private javax.swing.JMenuItem meBuscar;
    private javax.swing.JMenuItem meGuardar;
    private javax.swing.JMenuItem meNuevo;
    private javax.swing.JMenuItem mePrimero;
    private javax.swing.JMenuItem meSiguiente;
    private javax.swing.JMenuItem meUltimo;
    private javax.swing.JMenuItem miCerrar;
    private javax.swing.JMenuItem miSalir;
    private javax.swing.JMenu mmStock;
    private javax.swing.JMenuItem moneda;
    private javax.swing.JMenuItem personas;
    private javax.swing.JMenuItem producto;
    private javax.swing.JMenuItem productos;
    private javax.swing.JMenuItem proveedores;
    private javax.swing.JMenuItem usuario;
    // End of variables declaration//GEN-END:variables

    @Override
    public void imConect(conexion coneccion) {
        con = coneccion;
    }
    private miInterface getCurrentWindow() {
        miInterface v = (miInterface) desktop.getSelectedFrame();
        if (v == null) {
            JOptionPane.showMessageDialog(null, "No hay ventana activa...", "A T E N C I O N...", JOptionPane.OK_OPTION);
        }
        return v;
    }//fin getCurrentWindow
    
    public void centrar(JInternalFrame internalFrame) {
        int x = (desktop.getWidth() / 2) - internalFrame.getWidth() / 2;
        int y = (desktop.getHeight() / 2) - internalFrame.getHeight() / 2;
        if (internalFrame.isShowing()) {
            internalFrame.setLocation(x, y);
        } else {
            desktop.add(internalFrame);
            internalFrame.setLocation(x, y);
            internalFrame.show();
        }
    }//fin centrar
    
    private boolean isWindowOpen(JInternalFrame w){
        w_abiertos = desktop.getAllFrames();
        int len = w_abiertos.length;
        System.out.println("cantidad abiertos "+len);
        int i;
        if (len > 0) {
            for (i = 0; i < len; i++) {
                System.out.println("Abierto Tit["+i+"]"+w_abiertos[i].getTitle()+" W Tit: "+w.getTitle());
                if (w_abiertos[i].getTitle().equals(w.getTitle())) {
                    return true;
                }
            }
        }
        return false;
    }//fin isWindowOpen
    
    private void showWindow(JInternalFrame w){
        if (isWindowOpen(w)) {
            System.out.println(" se encontró la ventana ");
            return;
        }
        desktop.add(w);
        //centrar(w);
        w.setVisible(true);
        try {
            w.setSelected(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.OK_OPTION);
        }
    }//fin showWindow

}//fin clase
