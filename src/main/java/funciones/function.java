/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Funciones;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import javax.swing.JOptionPane;
import modelo.conexion;

/**
 *
 * @author Gregory
 */
public class function {
    private static ResultSet rs_suc;

    public static String decimalFormat(double numb) {
    String rtn;
    DecimalFormat formatea = new DecimalFormat("#,###.##");
    rtn = formatea.format(numb);
        return rtn;
        
    }

    public static Double sGetDecimalStringAnyLocaleAsDouble(String toString) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public void E_estado (javax.swing.JComboBox cb, String aTabla, String arg, conexion c){
       try{
            javax.swing.JComboBox cb_carga;
            cb_carga = cb;
            rs_suc = c.ejecuteSQL("SELECT * FROM "+aTabla+" WHERE " + arg+" ORDER BY 1");
            if (!rs_suc.first()){
                return;
            }
            if(aTabla.equals("deposito")){
            cb_carga.setSelectedItem(rs_suc.getString(1)+"-"+rs_suc.getString(3));
            }
            else{
            cb_carga.setSelectedItem(rs_suc.getString(1)+"-"+rs_suc.getString(2));
            }
        }catch(SQLException erro){
            JOptionPane.showMessageDialog(null,  "No se pudo recuperar el registro. - ERROR: "+erro);
        }
    }

    public static String ExtraeCodigo(String args){
        String codigo="";
        String caracter;
        System.out.println(args);
        System.out.println("longitud "+args.length());
        for(int i=0; i < args.length(); i++){
            caracter = args.substring(i, i+1);
            if(caracter.equals("-")){
               break;
            }else{
               codigo = codigo + caracter;
            }
        }
        return codigo;
    }
   
    public boolean fechaCorrecta(String fecha){ 
        
        if((fecha.substring(2,3)).compareTo("/")==0){ 
            int año = Integer.parseInt(fecha.substring(6)); 
            int mes = Integer.parseInt(fecha.substring(3,5)); 
            int dia = Integer.parseInt(fecha.substring(0,2)); 
            if (año > 1900) { 
                if (mes > 0 && mes < 13) { 
                    int tope; 
                    if (mes == 1 || mes == 3 || mes == 5 || mes == 7 || mes == 8 || mes == 10 || mes == 12) { 
                        tope = 31; 
                    }else if (mes == 2){ 
                        if (año % 4 == 0) { 
                            tope = 29; //es bisiesto 
                        } else tope = 28;                         
                    } else tope = 30; 
                    if (dia > 0 && dia < tope + 1) { 
                        return true; 
                    }else{
                        JOptionPane.showMessageDialog(null,  "Fecha incorrecta ");
                        return false;
                    }
                }else{
                    JOptionPane.showMessageDialog(null,  "Fecha incorrecta ");
                    return false;
                }
            }else{
                JOptionPane.showMessageDialog(null,  "Fecha incorrecta ");
                return false;
            }
        }else{
            JOptionPane.showMessageDialog(null,  "Fecha incorrecta ");
            return false;
        }
    }//fin fechacorrecta

    public static boolean hasColumn(ResultSet rs, String column){
        try{
            rs.findColumn(column);
            return true;
        } catch (SQLException sqlex){
            //JOptionPane.showMessageDialog(null,  "Fecha incorrecta ");
            return false;
        }
        //return false;
    }
}//fin funcion