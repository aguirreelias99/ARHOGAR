/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import Funciones.function;
import Tabla.GestionCeldas;
import Tabla.GestionEncabezadoTabla;
import Tabla.ModeloTabla;
import funciones.cargaComboBox;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import modelo.conexion;
import modelo.pojoCompraDetalle;
import modelo.tableModel;

/**
 *
 * @author Admin
 */
public class v_compra extends javax.swing.JInternalFrame implements miInterface, MouseListener, KeyListener {
    private tableController tc;
    private tableController tcdet;
    private Map<String, String> myData;
    conexion con = null;
    String currentField;
    javax.swing.JTextField idObj;
    private String msg;
    
    private tableModel tmMoneda;
    Map<String, String> mapMoneda; //= new HashMap<String, String>();    
    private tableModel tmProducto;
    Map<String, String> mapProducto; // = new HashMap<String, String>();    
    private tableModel tmProductoDet;
    Map<String, String> mapProductoDet;// = new HashMap<String, String>();    

    ArrayList<pojoCompraDetalle> lista; //= new ArrayList<>();
    public static int filaSeleccionada;
    ArrayList<pojoCompraDetalle> listaDetalles;//lista que simula la informacion de la BD

    private HashMap<String, String> myDet;
    private ArrayList<Map<String, String>> columnData;
    
    ModeloTabla modelo;//modelo definido en la clase ModeloTabla
    private int filasTabla;
    private int columnasTabla;
    
    private Date fecha; //= jdcFechaProceso.getDate();
    private DateFormat dateFormat, dateTimeFormat, dateIns;// = new SimpleDateFormat("dd/MM/yyyy HH:mm"); 
    /**
     * Creates new form v_compra
     */
    public v_compra() {
        initComponents();
        myData = new HashMap<String,String>();
        listaDetalles = new ArrayList<pojoCompraDetalle>();
        lista = new ArrayList();
        columnData = new ArrayList<Map<String,String>>();
        //Para menejo de Monedas
        mapMoneda = new HashMap<String, String>();

        tmMoneda = new tableModel();
        tmMoneda.init("monedas");
        // PARA EL DETALLE
        mapProducto = new HashMap<String, String>();
        tmProducto = new tableModel();
        tmProducto.init("productos");
        
        mapProductoDet = new HashMap<String, String>();
        tmProductoDet = new tableModel();
        tmProductoDet.init("detalle_producto");
        //setLocationRelativeTo(null);
	construirTabla();//Prepara la tabla con los valores iniciales
      
        //Estos son lísteners, es decir, esta misma clase implementa 
        //las interfaces línener para mouse y key y por tanto, la clase se escucha a sí misma
        jt_detalle.addMouseListener(this);
        jt_detalle.addKeyListener(this);
        jt_detalle.setOpaque(false);
        jt_detalle.getInputMap(JTable.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER,0), "selectNextColumnCell");
        //seleccionable cell
//        this.jtDetalle.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
//        this.jtDetalle.setColumnSelectionAllowed(true);
//        this.jtDetalle.setRowSelectionAllowed(true);
        
       // Unificamos tipo y tamaÃ±o de fuente
        FontUIResource font = new FontUIResource("Times New Roman", Font.PLAIN, 12);
        UIManager.put("Table.font", font);
        UIManager.put("Table.foreground", Color.RED);
        
       //  COMBO BOX DESPLEGABLES DE LAS TABLAS
        cargaComboBox.pv_cargar(cb_plazopago, "plazo_pago", " id, plazo ", "id", ""); 
        cargaComboBox.pv_cargar(cb_moneda, "monedas", " id, moneda ", "id", "");
        cargaComboBox.pv_cargar(cb_proveedor, "proveedor", "id_proveedor, nombre", "id_proveedor", "");
    //    cargaComboBox.pv_cargar(cb_tcompra, "proveedor", "id_proveedor, nombre", "id_proveedor", "");

 //   Inicializamos las fechas
        dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateTimeFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm"); 
        dateIns = new SimpleDateFormat("yyyy-MM-dd HHmm");
        
        dc_llegada.setDate(new Date());
        dc_proceso.setDate(new Date());
        dc_vence.setDate(new Date());
        dc_factura.setDate(new Date());
        getMoneda();
        
        tc = new tableController();
        tc.init("compras");
        tcdet = new tableController();
        tcdet.init("compras_detalle");
    }//fin constructor wCompra
    
    
    private void construirTabla() {
        /**Aquí se inicializan los valores. El metodo consultar tiene la forma
         * como debería ser en el metodo de recuperar registro.
         */
        listaDetalles.clear();
        listaDetalles = consultarListaDetalles();
        //Este array cambiará de valores según la tabla que querramos representar
        //en este caso nuestro detalle tiene esa estructura de columnas
        ArrayList<String> titulosList = new ArrayList<>();

        titulosList.add("Cod Barra");
        titulosList.add("Descripcion");
        titulosList.add("Precio");
        titulosList.add("Cantidad");
        titulosList.add("Descuento");
        titulosList.add("Bonificado");
        titulosList.add("Total");

        //se asignan las columnas al arreglo para enviarse al momento de construir la tabla
        //Esto es porque la tabla recibe arreglo [] y no un ArrayList, bien se pudo ya contruir de esa manera 
        //System.out.println("lista titulos "+titulosList.toString());
        String titulos[] = new String[titulosList.size()];
        
        for (int i = 0; i < titulos.length; i++) {
            titulos[i] = titulosList.get(i);
        }
        /*obtenemos los datos de la lista y los guardamos en la matriz
         * que luego se manda a construir la tabla
         */
        Object[][] data = obtenerMatrizDatos(titulosList);
        construirTabla(titulos, data);
        titulosList.clear();
    }

        /**
    * Permite simular el llenado de personas en una lista
    * que posteriormente alimentará la tabla
    * @return
    */
    private ArrayList<pojoCompraDetalle> consultarListaDetalles() {
        //ArrayList<pojoCompraDetalle> lista = new ArrayList<>();
        this.lista.add(new pojoCompraDetalle(0, "0", "Descripcion", 0, 0, 0, 0, 0));
        return lista;
    }
       /**
    * Llena la información de la tabla usando la lista de personas trabajada 
    * anteriormente, guardandola en una matriz que se retorna con toda 
    * la información para luego ser asignada al modelo
    * @param titulosList
    * @return
    */
    private Object[][] obtenerMatrizDatos(ArrayList<String> titulosList) {

        /*se crea la matriz donde las filas son dinamicas pues corresponde
         * a todos los usuarios, mientras que las columnas son estaticas
         * correspondiendo a las columnas definidas por defecto
         */
//        System.out.println("lista det size "+listaDetalles.size());
//        System.out.println("title info "+titulosList.size());
        String informacion[][] = new String[listaDetalles.size()][titulosList.size()];
        
        for (int x = 0; x < informacion.length; x++) {
            //Poner los nombres de los campos de la tabla de la bd
            informacion[x][0] = listaDetalles.get(x).getString("cod_barra");
            informacion[x][1] = listaDetalles.get(x).getString("descripcion");
            informacion[x][2] = listaDetalles.get(x).getDouble("precio_neto")+ "";
            informacion[x][3] = listaDetalles.get(x).getDouble("cantidad")+ "";
            informacion[x][4] = listaDetalles.get(x).getDouble("descuento")+ "";
            informacion[x][5] = listaDetalles.get(x).getDouble("cantbonificado")+ "";
            informacion[x][6] = listaDetalles.get(x).getDouble("total")+ "";

        }
        return informacion;
    }
    
    /**
    * Con los titulos y la información a mostrar se crea el modelo para 
    * poder personalizar la tabla, asignando tamaño de celdas tanto en ancho como en alto
    * así como los tipos de datos que va a poder soportar.
    * @param titulos
    * @param data
    */
    private void construirTabla(String[] titulos, Object[][] data) {
       /* ArrayList<Integer> noEditable = new ArrayList<Integer>();
        noEditable.add(1);
        noEditable.add(6);*/
        modelo = new ModeloTabla(data, titulos/*, noEditable*/);
        //se asigna el modelo a la tabla
        jt_detalle.setModel(modelo);

        filasTabla = jt_detalle.getRowCount();
        System.out.println("FILAFILAFILA");
        columnasTabla = jt_detalle.getColumnCount();

        //se asigna el tipo de dato que tendrán las celdas de cada columna definida respectivamente para validar su personalización
        jt_detalle.getColumnModel().getColumn(0).setCellRenderer(new GestionCeldas("texto"));
        jt_detalle.getColumnModel().getColumn(1).setCellRenderer(new GestionCeldas("texto"));
        jt_detalle.getColumnModel().getColumn(2).setCellRenderer(new GestionCeldas("numerico"));
        jt_detalle.getColumnModel().getColumn(3).setCellRenderer(new GestionCeldas("numerico"));
        jt_detalle.getColumnModel().getColumn(4).setCellRenderer(new GestionCeldas("numerico"));
        jt_detalle.getColumnModel().getColumn(5).setCellRenderer(new GestionCeldas("numerico"));
        jt_detalle.getColumnModel().getColumn(6).setCellRenderer(new GestionCeldas("numerico"));

        //se recorre y asigna el resto de celdas que serian las que almacenen datos de tipo texto
        /*for (int i = 0; i < titulos.length-5; i++) {//se resta 5 porque las ultimas 5 columnas se definen arriba
                System.out.println(i);
                jtDetalle.getColumnModel().getColumn(i).setCellRenderer(new GestionCeldas("texto"));
        }*/

        jt_detalle.getTableHeader().setReorderingAllowed(false);
        jt_detalle.setRowHeight(25);//tamaño de las celdas
        jt_detalle.setGridColor(new java.awt.Color(0, 0, 0)); 
        //Se define el tamaño de largo para cada columna y su contenido
        jt_detalle.getColumnModel().getColumn(0).setPreferredWidth(150);//cod_barra
        jt_detalle.getColumnModel().getColumn(1).setPreferredWidth(400);//descripcion
        jt_detalle.getColumnModel().getColumn(2).setPreferredWidth(150);//precio
        jt_detalle.getColumnModel().getColumn(3).setPreferredWidth(150);//cantidad
        jt_detalle.getColumnModel().getColumn(4).setPreferredWidth(150);//descuento
        jt_detalle.getColumnModel().getColumn(5).setPreferredWidth(150);//bonificado
        jt_detalle.getColumnModel().getColumn(6).setPreferredWidth(150);//total

        //personaliza el encabezado
        JTableHeader jtableHeader = jt_detalle.getTableHeader();
        jtableHeader.setDefaultRenderer(new GestionEncabezadoTabla());
        jt_detalle.setTableHeader(jtableHeader);
        //ystem.out.println("FILAFILAFILAFILA");

       //se asigna la tabla al scrollPane
       //scrollPaneTabla.setViewportView(jtDetalle);
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
        tf_compra = new javax.swing.JTextField();
        dc_proceso = new com.toedter.calendar.JDateChooser();
        dc_llegada = new com.toedter.calendar.JDateChooser();
        dc_factura = new com.toedter.calendar.JDateChooser();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        tf_tipodoc = new javax.swing.JTextField();
        tf_cotizacion = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        cb_moneda = new javax.swing.JComboBox<>();
        cb_plazopago = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        cb_proveedor = new javax.swing.JComboBox<>();
        cb_tcompra = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        tf_ndoc = new javax.swing.JTextField();
        tf_timbrado = new javax.swing.JTextField();
        dc_vence = new com.toedter.calendar.JDateChooser();
        tf_serie = new javax.swing.JTextField();
        tf_pagoinicial = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        tf_obs = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        tf_tcosto = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        tf_tneto = new javax.swing.JTextField();
        tf_timpuesto = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        tf_texento = new javax.swing.JTextField();
        tf_tgral = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jt_detalle = new javax.swing.JTable();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("COMPRA");

        jLabel1.setText("COMPRA");

        jLabel2.setText("FECHA PROCESO");

        jLabel3.setText("FECHA LLEGADA");

        jLabel4.setText("FECHA FACTURA");

        tf_compra.setText("0");
        tf_compra.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tf_compraFocusGained(evt);
            }
        });
        tf_compra.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tf_compraKeyPressed(evt);
            }
        });

        dc_factura.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                dc_facturaFocusGained(evt);
            }
        });

        jLabel5.setText("TIPO COMPRA");

        jLabel6.setText("TIPO DOC");

        jLabel7.setText("COTIZACION");

        tf_cotizacion.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tf_cotizacionFocusGained(evt);
            }
        });

        jLabel9.setText("MONEDA");

        cb_moneda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_monedaActionPerformed(evt);
            }
        });

        jLabel10.setText("PLAZO PAGO");

        jLabel23.setText("PROVEEDOR");

        cb_tcompra.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1- CONTADO", "2- FINANCIADO" }));
        cb_tcompra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_tcompraActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(dc_llegada, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(dc_factura, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel1))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tf_compra, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(dc_proceso, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(34, 34, 34)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(21, 21, 21))
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(tf_tipodoc, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cb_tcompra, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)
                        .addComponent(tf_cotizacion))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel9)
                            .addComponent(jLabel23))
                        .addGap(21, 21, 21)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cb_moneda, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cb_plazopago, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cb_proveedor, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(tf_tipodoc, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(tf_cotizacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(cb_tcompra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cb_moneda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(cb_plazopago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(cb_proveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel1))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(tf_compra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(dc_proceso, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(dc_llegada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(dc_factura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jLabel11.setText("N° DOCUMENTO");

        jLabel12.setText("TIMBRADO");

        jLabel13.setText("VENCE");

        jLabel14.setText("SERIE");

        jLabel15.setText("PAGO INICIAL");

        tf_timbrado.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tf_timbradoFocusGained(evt);
            }
        });

        tf_serie.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tf_serieFocusGained(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(tf_ndoc, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
                        .addComponent(tf_timbrado))
                    .addComponent(dc_vence, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(tf_pagoinicial, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
                        .addComponent(tf_serie, javax.swing.GroupLayout.Alignment.LEADING)))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(tf_ndoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(tf_timbrado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addComponent(dc_vence, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14)
                    .addComponent(tf_serie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(tf_pagoinicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel16.setText("OBS:");

        jLabel17.setText("TOTAL COSTO");

        jLabel19.setText("TOTAL IMPUESTO");

        jLabel20.setText("TOTAL NETO");

        jLabel21.setText("TOTAL EXENTO");

        jLabel22.setText("TOTAL GRAL");

        jt_detalle.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Cod Barra", "Descripción", "Precio", "Cantidad", "Descuento", "Bonificado", "Total"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jt_detalle);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel17)
                                .addGap(18, 18, 18)
                                .addComponent(tf_tcosto, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel20)
                                    .addComponent(jLabel19))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tf_timpuesto, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tf_tneto, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel21)
                                    .addComponent(jLabel22))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tf_texento, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tf_tgral, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(77, 77, 77)
                                .addComponent(jLabel16)
                                .addGap(18, 18, 18)
                                .addComponent(tf_obs, javax.swing.GroupLayout.PREFERRED_SIZE, 462, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 24, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(tf_obs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(tf_tcosto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20)
                    .addComponent(tf_tneto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21)
                    .addComponent(tf_texento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(tf_timpuesto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22)
                    .addComponent(tf_tgral, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 19, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tf_compraKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tf_compraKeyPressed

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            this.imBuscar();
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_compraKeyPressed

    private void tf_timbradoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tf_timbradoFocusGained
        tf_timbrado.selectAll();
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_timbradoFocusGained

    private void cb_monedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_monedaActionPerformed
this.getMoneda();        // TODO add your handling code here:
    }//GEN-LAST:event_cb_monedaActionPerformed

    private void tf_cotizacionFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tf_cotizacionFocusGained
        tf_cotizacion.selectAll();
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_cotizacionFocusGained

    private void tf_serieFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tf_serieFocusGained
        tf_serie.selectAll();
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_serieFocusGained

    private void dc_facturaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_dc_facturaFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_dc_facturaFocusGained

    private void tf_compraFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tf_compraFocusGained
        tf_compra.selectAll();
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_compraFocusGained

    private void cb_tcompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_tcompraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cb_tcompraActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cb_moneda;
    private javax.swing.JComboBox<String> cb_plazopago;
    private javax.swing.JComboBox<String> cb_proveedor;
    private javax.swing.JComboBox<String> cb_tcompra;
    private com.toedter.calendar.JDateChooser dc_factura;
    private com.toedter.calendar.JDateChooser dc_llegada;
    private com.toedter.calendar.JDateChooser dc_proceso;
    private com.toedter.calendar.JDateChooser dc_vence;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jt_detalle;
    private javax.swing.JTextField tf_compra;
    private javax.swing.JTextField tf_cotizacion;
    private javax.swing.JTextField tf_ndoc;
    private javax.swing.JTextField tf_obs;
    private javax.swing.JTextField tf_pagoinicial;
    private javax.swing.JTextField tf_serie;
    private javax.swing.JTextField tf_tcosto;
    private javax.swing.JTextField tf_texento;
    private javax.swing.JTextField tf_tgral;
    private javax.swing.JTextField tf_timbrado;
    private javax.swing.JTextField tf_timpuesto;
    private javax.swing.JTextField tf_tipodoc;
    private javax.swing.JTextField tf_tneto;
    // End of variables declaration//GEN-END:variables

    
    private void validarSeleccionMouse(int fila) {
        this.filaSeleccionada = fila;
        //teniendo la fila entonces se obtiene el objeto correspondiente para enviarse como parammetro o imprimir la información
        pojoCompraDetalle rowDetalle = new pojoCompraDetalle();
        rowDetalle.setString("cod_barra", jt_detalle.getValueAt(fila, 0).toString());
        rowDetalle.setString("descripcion", jt_detalle.getValueAt(fila, 1).toString());

        String info="INFO PERSONA\n";
        info+="Código: "+rowDetalle.getString("cod_barra")+"\n";
        info+="Descripción: "+rowDetalle.getString("descripcion")+"\n";
        System.out.println("FILAFILAFILAFILA en validar");
   }
    
        public int setTotalGral(){
        int rtnint = 0, monedaDecimal = 0;
        int rows = this.jt_detalle.getRowCount();
        String codbar, sql;
        codbar = ""; sql = "";
        
        Double precio, cantidad, descuento, bonificado, totalrow;
        Double totBruto, totiva, totNeto, totExenta, impuesto, base, iva, exenta;
        totBruto=0.0; totiva=0.0; totNeto=0.0; totExenta=0.0; impuesto=0.0; base=0.0; iva=0.0; exenta=0.0;
        
        monedaDecimal = Integer.parseInt(mapMoneda.get("decimales"));
       // mapMoneda.put("decimales","0.00");
        System.out.println(mapMoneda.get("decimales"));
        

        Map<String, String> rtn = new HashMap<String, String>();
        Map<String, String> select = new HashMap<String, String>();
        Map<String, String> where = new HashMap<String, String>();
        columnData.clear();
        for(int row = 0; row < rows; row++){   
            codbar = this.jt_detalle.getModel().getValueAt(row, 0).toString();
            if(codbar.equals("0") || codbar.equals("")){ //Si no se especifica códio, no tiene sentido continuar
                continue; 
            }
            
            //Por cada codigo de barras 
            select.put("*", "*");
            where.put("cod_barra", codbar);
            this.mapProductoDet = this.tmProductoDet.readRegisterById(select, where);//Recupera el id de producto
            //Para recuperar el producto
            where.clear();
            where.put("id", this.mapProductoDet.get("productoid"));
            
            this.mapProducto = this.tmProducto.readRegisterById(select, where);
            iva = Double.parseDouble(this.mapProducto.get("impuesto"));

            precio = function.sGetDecimalStringAnyLocaleAsDouble(this.jt_detalle.getModel().getValueAt(row, 2).toString());
            cantidad = function.sGetDecimalStringAnyLocaleAsDouble(this.jt_detalle.getModel().getValueAt(row, 3).toString());
            descuento = function.sGetDecimalStringAnyLocaleAsDouble(this.jt_detalle.getModel().getValueAt(row, 4).toString());
            bonificado = function.sGetDecimalStringAnyLocaleAsDouble(this.jt_detalle.getModel().getValueAt(row, 5).toString());
            if(precio <= 0 || cantidad <= 0){
                continue;
            }
            if(iva > 0.0){
                base = precio / (1+(iva/100));
                //Redondea la base a la canitdad de decimales de la moneda
                BigDecimal bd = new BigDecimal(base).setScale(monedaDecimal, RoundingMode.HALF_UP);
                base = bd.doubleValue();
                impuesto = precio - base;
                exenta = 0.0;
            }else{
               base = 0.0;
               impuesto = 0.0;
               exenta = precio; 
            }

            totalrow = (precio - descuento) * (cantidad - bonificado);
            totBruto = totBruto + totalrow;
            totNeto = totNeto + ((base - descuento) * (cantidad - bonificado));
            totiva = totiva + (impuesto * cantidad);
            totExenta = totExenta + ((exenta - descuento) * (cantidad - bonificado));
            
            myDet = new HashMap<String, String>();
            String id = tf_compra.getText();
            //System.out.println("el facking id "+id);
            myDet.put("compraid", id);
            myDet.put("cod_barra", codbar);
            myDet.put("cantidad", cantidad+"");
            myDet.put("precio_bruto", precio+"");
            myDet.put("precio_neto", ((base - descuento) * (cantidad - bonificado))+"");
            myDet.put("cantbonificado", bonificado+"");
            myDet.put("descuento", (descuento * (cantidad - bonificado))+"");
            myDet.put("impuesto", (impuesto * (cantidad - bonificado))+"");
            myDet.put("total", totalrow+"");
            myDet.put("descripcion", this.jt_detalle.getModel().getValueAt(row, 1).toString());

            //limpiar
            columnData.add(myDet);
        }
        //String totBrut = functions.decimalFormat(totBruto);

        //System.out.println("total "+functions.decimalFormat(totalrow).toString()+" fila "+row);
        this.tf_tneto.setText(function.decimalFormat(totNeto));
        this.tf_timpuesto.setText(function.decimalFormat(totiva));
        this.tf_texento.setText(function.decimalFormat(totExenta));
        this.tf_tgral.setText(function.decimalFormat(totBruto));
        return rtnint;
    }//end setTotalGral
    
    
    
    private void setData(){ 
        //System.out.println("fecha proceso "+dateTimeFormat.format(jdcProceso.getDate()));
        //System.out.println("setData id "+jtfId.getText());
        myData.put("id", tf_compra.getText());
        myData.put("fecha_proceso", dateIns.format(dc_proceso.getDate()));
        myData.put("fecha_factura", dateIns.format(dc_factura.getDate()));
        myData.put("fecha_llegada", dateIns.format(dc_llegada.getDate()));
        myData.put("nro_documento", tf_ndoc.getText());
        myData.put("serie", tf_serie.getText());
        myData.put("timbrado", tf_timbrado.getText());
        myData.put("vence", dateIns.format(dc_vence.getDate()));
        myData.put("proveedorid", function.ExtraeCodigo(cb_proveedor.getSelectedItem().toString()));
        myData.put("plazoid", function.ExtraeCodigo(cb_plazopago.getSelectedItem().toString()));
        myData.put("monedaid", function.ExtraeCodigo(cb_moneda.getSelectedItem().toString()));
        myData.put("cotizacion", tf_cotizacion.getText().replace(".", "").replace(",", "."));
        myData.put("pago_inicial", tf_pagoinicial.getText().replace(".", "").replace(",", "."));
        myData.put("tot_exento", tf_texento.getText().replace(".", "").replace(",", "."));
        myData.put("tot_precioneto", tf_tneto.getText().replace(".", "").replace(",", "."));
        myData.put("tot_impuesto", tf_timpuesto.getText().replace(".", "").replace(",", "."));
        myData.put("tipocompra", function.ExtraeCodigo(cb_tcompra.getSelectedItem().toString()));
        myData.put("observacion", tf_obs.getText());
        //Recorre el detalle y guarda cada fila
        System.out.println("SETDATA");
        //columndata ya se procesó con el metodo setTotalGral
        this.setTotalGral();
    }//fin setData
    
            
    private void resetData(){        
        myData.clear();
        Date d = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm");
        String strDate = dateFormat.format(d);
        System.out.println("fecha "+strDate);
        myData.put("id", "0");
        myData.put("fecha_proceso", dateTimeFormat.format(strDate));
        myData.put("fecha_factura", dateTimeFormat.format(strDate));
        myData.put("fecha_llegada", dateTimeFormat.format(strDate));
        myData.put("nro_documento", "0");
        myData.put("serie", "001001");
        myData.put("timbrado", "0");
        myData.put("vence", dateTimeFormat.format(strDate));
        myData.put("proveedorid", "0");
        myData.put("plazoid", "0");
        myData.put("monedaid", "0");
        myData.put("cotizacion", "0.0");
        myData.put("tot_preciobruto", "0.0");
        myData.put("pago_inicial", "0.0");
        myData.put("tot_exento", "0.0");
        myData.put("tot_precioneto", "0.0");
        myData.put("tot_impuesto", "0.0");
        myData.put("tipocompra", "0");
        myData.put("observacion", "Obs");
       // myData.put("depositoid", "1");
        
      //  Detalle
        this.myDet = new HashMap<String, String>(); 
        myDet.put("compraid", "0");
        myDet.put("cod_barra", "0");
        myDet.put("cantidad", "0");
        myDet.put("precio_bruto", "0");
        myDet.put("precio_neto", "0");
        myDet.put("cantbonificado", "0");
        myDet.put("descuento", "0");
        myDet.put("impuesto", "0");
        myDet.put("total", "0");
            
        this.columnData.add(this.myDet);
            
  }
            
    
            
     public int getProducto(int row, int col){
            System.out.println("DE GET PRODUCTO");

        String codbar, sql;
        codbar = ""; sql = "";
        //System.out.println("Fila : "+row+" Column :"+col+ " valor "+this.jt_detalle.getModel().getValueAt(row, col));
        codbar = this.jt_detalle.getModel().getValueAt(row, col).toString();
                
        //System.out.println("codigo barra "+codbar);
        if(codbar.equals("0") || codbar.equals("")){
            return 0; 
        }
        System.out.println("ESTOY EN GETPRODUCTO ");
        sql = "SELECT CONCAT(p.nombre, ' - ', "
                + "p.descripcion, ' - ', m.nombre_marca, ' - ', "
                + "c.color, ' - ', t.tamano, ' - ', s.diseno) AS descripcion "
                + "FROM productos p, detalle_producto d, marcas m, colores c, tamanos t, disenos s "
                + "WHERE p.id = d.productoid "
                + "AND p.marca = m.cod_marca "  
                + "AND d.colorid = c.id "
                + "AND d.tamanoid = t.id "
                + "AND d.disenoid = s.id "
                + "AND d.cod_barra = '"+codbar+"'";
         Map<String, String> rtn = new HashMap<String, String>();
        ResultSet rs;
                System.out.println("FILAFILAFILAFILA");

        System.out.println("sql "+sql);
        try {
            rs = conexion.ejecuteSQL(sql); //Esto devuelve un ResultSet
            ResultSetMetaData metaData = rs.getMetaData();
            int colCount = metaData.getColumnCount();
           // while(rs.next()){
            if(rs.next()){
                for (int r = 1; r <= colCount; r++) {
                    //System.out.println("column "+metaData.getColumnName(r)+" valor "+rs.getString(metaData.getColumnName(r)));
                    rtn.put( metaData.getColumnName(r), rs.getString("descripcion"));
                    this.jt_detalle.getModel().setValueAt( rs.getString(metaData.getColumnName(r)), row, 1);
                }
            }else{
                this.jt_detalle.getModel().setValueAt("0", row, 0);
                this.jt_detalle.getModel().setValueAt("", row, 1);
            }
        } //fin deleteRegister
        catch (SQLException ex) {
            Logger.getLogger(tableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
       return 0;
    }       
     
     
         public void limpiarCelda(JTable tabla){
        tabla.setValueAt("", tabla.getSelectedRow(), tabla.getSelectedColumn());
    }
    
    /**
     * Calucula el total para una fila específica
     * @param row int index de la fila a ser procesada
     * @return rtn int que devuelve el estado de la operación, falta completar
     */
    public int setTotalRow(int row){
        int rtn = 0;
        Double precio, cantidad, descuento, bonificado, totalrow;
        precio = function.sGetDecimalStringAnyLocaleAsDouble(this.jt_detalle.getModel().getValueAt(row, 2).toString());
        cantidad = function.sGetDecimalStringAnyLocaleAsDouble(this.jt_detalle.getModel().getValueAt(row, 3).toString());
        descuento = function.sGetDecimalStringAnyLocaleAsDouble(this.jt_detalle.getModel().getValueAt(row, 4).toString());
        bonificado = function.sGetDecimalStringAnyLocaleAsDouble(this.jt_detalle.getModel().getValueAt(row, 5).toString());
        
        //System.out.println("");
        if(precio <= 0 || cantidad <= 0){
            return rtn;
        }
        
        totalrow = (precio - descuento) * (cantidad - bonificado);
        //System.out.println("totalrow sin cambio "+totalrow);        
        String totrow = function.decimalFormat(totalrow);
        //System.out.println("totalrow formateado "+totrow);
        
        String woDot =""; 
        woDot = totrow.replace(".", "");
        //System.out.println("Sin puntos "+woDot);
        String woComa ="";
        woComa = woDot.replace(",", ".");
        //System.out.println("Coma por punto "+woComa);
        double tr = function.sGetDecimalStringAnyLocaleAsDouble(totrow);

        //System.out.println("total string tr "+tr+" fila "+row);

       this.jt_detalle.getModel().setValueAt( woComa , row, 6);
  
        return rtn;
    }//fin setTotalRow
    
    

    
    
    
       /**
     * Controla que todos los datos obligatorios de la cabecera tengan los valores requeridos
     * En caso contrario se devuelve el foco a la cabecera. Este método se llama en el 
     * evento focus de la tabla
     * @return boolean que indica si las condiciones se cumplen o no, true/false
     */
    public boolean validarCabecera(){
        boolean rtn;
        rtn = true;
        Date fecha;
        fecha = dc_proceso.getDate();
        if(fecha == null){
            JOptionPane.showMessageDialog(this, "Favor ingrese una fecha de operación válida!", "¡A T E N C I O N!", JOptionPane.WARNING_MESSAGE);
            dc_proceso.requestFocus();
            dc_proceso.setDate(new Date());
            rtn = false;
        }
        
        fecha = dc_llegada.getDate();
        
        if(fecha == null){
            JOptionPane.showMessageDialog(this, "Favor ingrese una fecha de llegada válida!", "¡A T E N C I O N!", JOptionPane.WARNING_MESSAGE);
            dc_llegada.requestFocus();
            dc_llegada.setDate(new Date());
            rtn = false;
        }
        
        //Aquí verificar que la fecha de llegada no sea inferior que la fecha de proceso
        //continuar con los demás controles
        
        return rtn;
    }
    /**
     * Recupera los datos de la moneda que se usa en el proceso
     * @return int rtn estado del proceso de recuperación, falta completar
     */
    public int getMoneda(){
        int rtn=0;
        Map<String, String> where = new HashMap<String, String>();
        String idMoneda;
        idMoneda = "";
        idMoneda = function.ExtraeCodigo(this.cb_moneda.getSelectedItem().toString());
        this.mapMoneda.clear();
        this.mapMoneda.put("*", "*");
        where.put("id", idMoneda);
        this.mapMoneda = tmMoneda.readRegisterById(this.mapMoneda, where);
        return rtn;
    }
    @Override
    public int imModo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void imGrabar() {
       
        this.setData();
        if(!myData.get("id").equals("0")){
            this.imActualizar();
            return;
        }
        int rowsAffected = this.tc.createReg(myData); //Está guardando igual si en el detalle hay error
        //hay que recorrer el detalle y envira de a uno.
        for(Map<String,String> myRow : columnData){
            myRow.put("compraid", myData.get("id"));
            int affected = this.tcdet.createReg(myRow);
        }
        this.resetData();
        this.fillView(myData, columnData);
    }

    @Override
    public void imActualizar() {

        this.setData();
        ArrayList<Map<String,String>> alCabecera;         //Declara array de Map, cada Map es para un registro
        alCabecera = new ArrayList<Map<String,String>>(); //Instancia array
        alCabecera.add(this.myData);                      //agrega el Map al array, para la cabecera será el mejor de los casos, es decir 1 registro 
        int rowsAffected = this.tc.updateReg(alCabecera); //Está guardando igual si en el detalle hay error
        //Para el DETALLE
        ArrayList<Map<String,String>> alDetalle;         //Declara array de Map, cada Map es para un registro
        alDetalle = new ArrayList<Map<String,String>>(); //Instancia array
        
        for(Map<String,String> myRow : columnData){       //hay que recorrer el detalle y envira de a uno.
            //System.out.println("ENVIAMOS "+myData.get("id"));
            //myRow.put("compraid", myData.get("id"));      //asignamos el id de la cabecera como el fk del detalle
            alDetalle.add(myRow);
        }
        int affected = this.tcdet.updateReg(alDetalle);   //Recordar que el modelo sólo procesa de a uno los registros
        
        this.resetData();
        this.fillView(myData, columnData);
    }

    @Override
    public void imBorrar() {

        
    }

    @Override
    public void imNuevo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void imBuscar() {

        this.setData(); //Hace tomar los datos de la vista
        this.myData = this.tc.searchById(myData);                     //Usa el mismo myData para devolver los valores de la cabecera
        Map<String,String> where = new HashMap<String,String>();      //Por qué campo buscar los registros
        where.put("compraid", this.myData.get("id"));
        //Los campos que vamos a recuperar
        Map<String,String> fields = new HashMap<String,String>();
        fields.put("*", "*");
        
    }

    @Override
    public void imPrimero() {


    }

    @Override
    public void imSiguiente() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void imAnterior() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void imUltimo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void imImprimir() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void imInsDet() {

        int currentRow = jt_detalle.getSelectedRow();
        if(currentRow == -1){
            //System.out.println("no hay fila seleccionada imInsDet 1062");
            modelo.addRow(new Object[]{"", "Descripcion",  "0.0",  "0.0",  "0.0",  "0.0",  "0.0"});
            return;
        }
        String cod = this.jt_detalle.getValueAt(currentRow, 0).toString();
                
        //System.out.println("Codigo "+cod);
        if(cod.equals("0") || cod.equals("") || cod == null){
            JOptionPane.showMessageDialog(this, "Favor ingrese un producto!", "¡A T E N C I O N!", JOptionPane.INFORMATION_MESSAGE);
            return;
        }else{
            //System.out.println("entro en imInsDet");
            modelo.addRow(new Object[]{"0", "Descripcion",  "0.0",  "0.0",  "0.0",  "0.0",  "0.0"});
             /**
            * LUEGO DE CARGAR LA INFORMACIÓN DE LA SOLICITUD EN LA VISTA
            * NOS POSICIONAMOS EN LA PRIMERA CELDA DE LA SIGUIENTE FILA
            * DE LA TABLA DE LA VENTANA PRINCIPAL
            */

            /**
             * DEBEMOS DEVOLVERLE EL FOCO A LA TABLA
             */
             this.jt_detalle.requestFocus ();

            /**
             * tabla.getRowCount () - 1 -> PARA INDICAR QUE ES LA ULTIMA FILA
             * 0 -> EN MI CASO PARA INDICAR QUE DEBE SER EN LA PRIMERA COLUMNA
             * false, false -> LOS DEJO ASÍ PUES NO NECESITO LA FUNCIONALIDAD DE ESOS PARÁMETROS
             */
            /* toggle: false, extend: false. Clear the previous selection and ensure the new cell is selected.
            * toggle: false, extend: true. Extend the previous selection from the anchor to the specified cell, clearing all other selections.
            * toggle: true, extend: false. If the specified cell is selected, deselect it. If it is not selected, select it.
            * toggle: true, extend: true. Apply the selection state of the anchor to all cells between it and the specified cell.
            */
            int toRow = this.jt_detalle.getRowCount()- 1;
            //System.out.println("a la fila "+toRow);
             this.jt_detalle.changeSelection ( toRow, 0, false, false );    
        }
    }

    @Override
    public void imDelDet() {

        int currentRow = jt_detalle.getSelectedRow();
        if(currentRow == -1){
            System.out.println("no hay fila seleccionada");
            return;
        }
        
        modelo.removeRow(currentRow);
        //Si al eliminar queda vacía, habrá que insertar una nueva
        int rows = jt_detalle.getRowCount();
        
        if(rows == 0){
            System.out.println("Se eliminaron todas las filas");
            this.imInsDet();
        }
    }

    @Override
    public void imCerrar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void imFiltrar() {
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

    private void setLocationRelativeTo(Object object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }




    private void limpiarTabla() {

        try {
            DefaultTableModel modelo = (DefaultTableModel)jt_detalle.getModel();
            int filas = jt_detalle.getRowCount();
            for (int i = 0;filas>i; i++) {
                modelo.removeRow(0);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al limpiar la tabla.");
        }
    }


    @Override
    public void mouseClicked(MouseEvent e) {

        //capturo fila o columna dependiendo de mi necesidad
        
        //OBS: Aquí debemos llamar a un método que controle que los campos de la cabecera estén completos
        int fila = jt_detalle.rowAtPoint(e.getPoint());
        int columna = jt_detalle.columnAtPoint(e.getPoint());
        
        /*uso la columna para valiar si corresponde a la columna de perfil garantizando
         * que solo se produzca algo si selecciono una fila de esa columna
         */
        if (columna == 0) { //0 corresponde a cod barra
            //sabiendo que corresponde a la columna de perfil, envio la posicion de la fila seleccionada
            validarSeleccionMouse(fila);
        }else if (columna == 2){//se valida que sea la columna del otro evento 2 que corresponde a precio
            //JOptionPane.showMessageDialog(null, "Evento del otro icono");
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyTyped(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent e) {

        //System.out.print("\n Estoy en el keyListener, keyPressed");
        int row = jt_detalle.getSelectedRow();
        int rows = jt_detalle.getRowCount();
        int col = jt_detalle.getSelectedColumn();
        
        int key = e.getKeyChar();
        //System.out.println("tecla pulsada "+key);
        
        boolean numeros = key >= 48 && key <= 57;
        boolean decimalPoint = key == 46;
        boolean erraser = key == 8;
        
        if (!numeros && !decimalPoint && !erraser && key != 10){
            //e.consume();
        }else{
            if(numeros){
                if(jt_detalle.getModel().isCellEditable(row, col)){
                    this.limpiarCelda(jt_detalle);
                }
            }
            
        }
        
        //System.out.println("key code "+e.getKeyCode());
        //System.out.println("Fila : "+row+ "/"+rows+" Column :"+col);
        if (key == 10 || key == 9 || (key >= 37 && key <=40)) {//10 es enter
            if (jt_detalle.isEditing()){
                jt_detalle.getCellEditor().stopCellEditing();
            }

            if (col == 0) {
                this.getProducto(row, col);
                return;
            }
            if (col == 2 || col == 3 || col == 4 || col == 5) {
                this.setTotalRow(row);
                this.setTotalGral();
            }
            //System.out.println("Col "+col+ " key "+key);
            if (col == 6 && key == 10 && (row == (rows - 1))) { //Si está en la última columna y presiona enter, inserta una nueva fila
                //Podría controlarse que se haya ingresado previamente el codigo
                String cod = this.jt_detalle.getValueAt(row, 0).toString();
                
                //System.out.println("Codigo "+cod);
                if(cod.equals("0") || cod.equals("") || cod == null){
                    JOptionPane.showMessageDialog(this, "Favor ingrese un producto!", "¡A T E N C I O N!", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }else{
                    this.imInsDet();
                }
                
            }
        }
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

private void fillView(Map<String, String> data, List<Map<String,String>> colData ) {
        java.util.Date df;
        for (Map.Entry<String, String> entry : data.entrySet()) {
            try {
                String key = entry.getKey();
                String value = entry.getValue();
                
                System.out.println("fecha "+dateTimeFormat.format(value.toString()));
                switch(key) {
                    case "id":
                        tf_compra.setText(value);
                        break;
                    case "fecha_proceso":
                        df = dateTimeFormat.parse(value);
                        dc_proceso.setDate(df);
                        break;
                    case "fecha_llegada":
                        df = dateTimeFormat.parse(value);
                        dc_llegada.setDate(df);
                        break;
                    case "fecha_factura":
                        df = dateTimeFormat.parse(value);
                        dc_factura.setDate(df);
                        break;
                    case "nro_documento":
                        tf_ndoc.setText(value);
                        break;
                    case "serie":
                        tf_serie.setText(value);
                        break;
                    case "vence":
                        df = dateTimeFormat.parse(value);
                        dc_vence.setDate(df);
                        break;
                    case "timbrado":
                        tf_timbrado.setText(value);
                        break;
                    
                    case "pago_inicial":
                        tf_pagoinicial.setText(function.decimalFormat(Double.parseDouble(value)));
                        break;
                    case "tot_exento":
                        tf_texento.setText(function.decimalFormat(Double.parseDouble(value)));
                        break;
                    case "tot_precioneto":
                        tf_tneto.setText(function.decimalFormat(Double.parseDouble(value)));
                        break;
                    case "tot_impuesto":
                        tf_timpuesto.setText(function.decimalFormat(Double.parseDouble(value)));
                        break;
                    case "tot_costo":
                        //tfcosto.setText(value);
                        break;
                    case "cotizacion":
                        tf_cotizacion.setText(function.decimalFormat(Double.parseDouble(value)));
                        break;
                    case "observacion":
                        tf_obs.setText(value);
                        break;
                   /* case "tipocompra":
                        cb_tipocompra.setSelectedItem(Integer.parseInt(value));
                        break;*/
                    case "plazoid":
                        cb_plazopago.setSelectedItem(Integer.parseInt(value));
                        break;
                    case "proveedorid":
                        cb_proveedor.setSelectedItem(Integer.parseInt(value));
                        break;
                    case "monedaid":
                        cb_moneda.setSelectedItem(Integer.parseInt(value));
                        break;
                    case "tipodoc":
                        //tftipodoc.setText(value);
                        break;
                /*    case "deposito":
                        tf_deposito.setText(value);
                        break;*/
                }//end switch
            } //end for
            catch (ParseException ex) {
                Logger.getLogger(v_compra.class.getName()).log(Level.SEVERE, null, ex);
            }
         }//end for 1    
            
            
        int row;
        row = 0;
        limpiarTabla();

        for(Map<String,String> myRow : columnData){
            //this.imInsDet();  
            this.modelo.addRow(new Object[]{
                myRow.get("cod_barra"), 
                "Descripcion",  
                myRow.get("precio_bruto"),  
                myRow.get("cantidad"),  
                myRow.get("descuento"),  
                myRow.get("cantbonificado"),  
                myRow.get("total")
            });
            this.jt_detalle.getSelectionModel().setSelectionInterval(row,0);
            int exist = this.getProducto(row, 0);
            row++;
        }//end for 2
    }//end fill            
    

}
