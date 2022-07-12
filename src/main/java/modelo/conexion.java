/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author Admin
 */
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.swing.JOptionPane;

public class conexion {
    //Estas variables son las estudiadas en JDBC
    
    public static Connection con = null;
    public static Statement statement;
    public static ResultSet resultset;
    private static PreparedStatement pstm;
    public static int psi_afectados;
    private static String driver = "";
    public static String dbname="";
    public static String dbtype="";
    public static String url="";
    private static String usuario = "";
    private static String contrasena = "";
    
    //Propiedades de la clase para almacenar usuario
    
    private static int il_usu = 0;
    private static String is_usu = "";
    private static int il_grupo = 0;
    //Para leer archivo externo
    static FileInputStream inputStream = null;
    private static int ii_con = 0; //Cero si no se conecto o 1 si està conectado
    
    public static boolean Conectar(){
       boolean result = true;
       try{
            //Para acceder a los datos del .properties usaremos esta instruccion:
            inputStream =  new FileInputStream("properties.properties"); 
            //Ahora inicializamos el properties:
            Properties properties = new Properties ();
            try{		
                properties.load(inputStream);
                inputStream.close();
                //Y ahora si queremos los valores del properties:
                driver = properties.getProperty("driver");
                url = properties.getProperty("url");
                usuario = properties.getProperty("usuario");
                contrasena = properties.getProperty("contrasena");
                dbtype = properties.getProperty("dbtype");
                dbname = properties.getProperty("dbname");
                ii_con = 1;
            } catch(IOException ex) {
                ii_con = 0;
                ex.printStackTrace();
            } 
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (NullPointerException nulo){
            nulo.printStackTrace();
        } catch (Exception excep){
            excep.printStackTrace();
        }
       
        try{
            Class.forName(driver);
            con = DriverManager.getConnection(url, usuario, contrasena);
            ii_con = 1;
        }catch(ClassNotFoundException Driver){
            JOptionPane.showMessageDialog(null, "Driver no localizado: "+Driver);
            result = false;
        }catch(SQLException Fonte){
            JOptionPane.showMessageDialog(null, "Error en la conexion con la BD: "+Fonte);
            result = false;
            ii_con = 0;
        }
        
        return result;
    }
    
    public static void desconecta(){
        boolean result = true;
        try{
            con.close();
            System.out.println("BD cerrada");
            ii_con = 0;
        }catch(SQLException errorSQL){
            System.out.println("No fue posible cerrar la BD: "+errorSQL.getMessage());
            result = false;
        }
    }
    
    /* ResultSet executeQuery(String sql)
    * Devuelve un ResusltSet para su manipulación.
    */
    public static ResultSet ejecuteSQL(String sql){
	try{
            statement = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultset = statement.executeQuery(sql);
        }catch(SQLException sqlex){
            System.out.println("(conexion.ejecuteSQL)No fue posible ejecutar la instrucción QUERY: \n\r"+
                    sqlex.getMessage()+", \n\rEl sql pasado fue: "+sql);
            return null;
        }
        return resultset;
    }   
    /* int executeUpdate(String sql)
     * Devuelve la cantidad de registros afectados
     */
    public static int ejecuteUPD(String sql){
        try{
            statement = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            psi_afectados = statement.executeUpdate(sql);
        }
        catch(SQLException sqlex){
            psi_afectados = 0;
            System.out.println("(conexionejecuteUPD)No fue posible ejecutar la instrucción UPDATE: \n\r"+
                    sqlex.getMessage()+", \n\rEl sql pasado fue: "+sql);
        }
        return psi_afectados;
    }
	
    public static PreparedStatement prepStatement(String sql){
        try{
           pstm = con.prepareStatement(sql);
        } catch(SQLException sqlex){
            System.out.println("(conexionprpStatement)No fue posible ejecutar la instrucción UPDATE: \n\r"+
            sqlex.getMessage()+", \n\rEl sql pasado fue: "+sql);
        }
        return pstm;
    }
    //Una vez realizada al conexión se puede recuperar estos valores
    public static int getUserId(){
        return il_usu;
    }
    public static void setUserId(int aruser){
        il_usu = aruser;
    }

    public static String getUserName(){
        return is_usu;
    }
    public static void setUserName(String arname){
        is_usu = arname;
    }
    public static int getGrupoId(){
        return il_grupo;
    }
    public static void setGrupoId(int aruser){
        il_grupo = aruser;
    }
    public static int isConected(){
        return ii_con;
    }
    public static ArrayList getKeyColumns(String tableName, String columnKey){
        ArrayList<String> fieldsKey = new ArrayList<String>();
        String sql = "";
        if(dbtype.equals("mysql")){
            if(columnKey.equals("PRI")){
                columnKey = "PRIMARY KEY";
            }
            sql = "SELECT kcu.COLUMN_NAME " +
                "FROM information_schema.table_constraints tc " +
                "LEFT JOIN information_schema.key_column_usage kcu " +
                "ON tc.constraint_catalog = kcu.constraint_catalog " +
                "AND tc.constraint_schema = kcu.constraint_schema " +
                "AND tc.constraint_name = kcu.constraint_name " +
                "WHERE UPPER(tc.constraint_type) in ('"+columnKey+"') " +
                "AND tc.table_name = '"+tableName+"' " +
                "AND tc.table_catalog = '"+dbname+"'";          
        }
        if(dbtype.equals("mariadb")){
            sql = "SELECT c.COLUMN_NAME "
                + "FROM information_schema.COLUMNS c  "
                + "WHERE c.table_schema = '"+dbname
                +"' AND c.TABLE_NAME='"+tableName
                +"' AND c.COLUMN_KEY = '"+columnKey+"'";
        }
       System.out.println("Sql que se pasa: "+sql);
        resultset = ejecuteSQL(sql);
        try{
            statement = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultset = statement.executeQuery(sql);
            while (resultset.next()) { 
                System.out.println("key col: "+resultset.getString(1));
                fieldsKey.add(resultset.getString(1));
            }
            if(fieldsKey.isEmpty()){
                System.out.println("Conn getKeyColumns en el met esta vacio");
            }else{
                System.out.println("Conn getKeyColumns en el met "+fieldsKey.get(0));
            }
            
        }catch(SQLException sqlex){
            JOptionPane.showMessageDialog(null, "No fue posible ejecutar la instrucción QUERY: \n\r"+
                    sqlex.getMessage()+", \n\rEl sql pasado fue: "+sql);
            //return null;
        }
        return fieldsKey;
    }//fin getKeyColumns
    
    public static ArrayList getColumnNames(String tableName){
        Map<String, String> rtn = new HashMap<String, String>();
        String sql="";
        ArrayList<String> columnNames = new ArrayList<String>();
        if(dbtype.equals("mysql")){
            sql = "SELECT COLUMN_NAME, DATA_TYPE "
                + "FROM information_schema.COLUMNS "
                + "WHERE table_catalog = '"+dbname+"' AND TABLE_NAME='"+tableName+"'";
        }
        
        if(dbtype.equals("mariadb")){
            sql = "SELECT COLUMN_NAME, DATA_TYPE "
                + "FROM information_schema.COLUMNS "
                + "WHERE table_schema = '"+dbname+"' AND TABLE_NAME='"+tableName+"'";
        }
        try{
            
            statement = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
           resultset = statement.executeQuery(sql);
            resultset = ejecuteSQL(sql);
            while (resultset.next()) { 
                columnNames.add(resultset.getString(1));
            }
        }catch(SQLException sqlex){
            JOptionPane.showMessageDialog(null, "No fue posible ejecutar la instrucción QUERY: \n\r"+
                    sqlex.getMessage()+", \n\rEl sql pasado fue: "+sql);
            return null;
        }
        return columnNames;
    }//fin getColumnType
    
    public static ArrayList getColumnTypes(String tableName){
        String sql="";
        //Map<String, String> rtn = new HashMap<String, String>();
        ArrayList<String> columnTypes = new ArrayList<String>();
        if(dbtype.equals("pgsql")){
            sql = "SELECT COLUMN_NAME, DATA_TYPE " +
                "FROM information_schema.columns " +
                "WHERE table_schema = 'public' " +
                "AND table_catalog = '"+dbname+"' " +
                "AND table_name = '"+tableName+"'";
        }
        if(dbtype.equalsIgnoreCase("mariadb")){
            sql = "SELECT COLUMN_NAME, DATA_TYPE "
                + "FROM information_schema.COLUMNS "
                + "WHERE table_schema = '"+dbname+"' AND TABLE_NAME='"+tableName+"'";
        }
        try{
            statement = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultset = statement.executeQuery(sql);
            resultset = ejecuteSQL(sql);
            while (resultset.next()) { 
                columnTypes.add(resultset.getString(2));
            }
        }catch(SQLException sqlex){
            JOptionPane.showMessageDialog(null, "No fue posible ejecutar la instrucción QUERY: \n\r"+
                    sqlex.getMessage()+", \n\rEl sql pasado fue: "+sql);
            return null;
        }
        return columnTypes;
    }//fin getColumnType
}
