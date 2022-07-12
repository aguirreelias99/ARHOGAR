/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package funciones;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import modelo.conexion;

/**
 *
 * @author Gregory
 */
public class functions {
    private static ResultSet rs_suc;
    public void E_estado (javax.swing.JComboBox cb, String aTabla, String arg, conexion con){
       try{
            javax.swing.JComboBox cb_carga;
            cb_carga = cb;
            rs_suc = conexion.ejecuteSQL("SELECT * FROM "+aTabla+" WHERE " + arg+" ORDER BY 1");
            if (!rs_suc.first()){
                return;
            }
            if(aTabla.equals("rol")){
            cb_carga.setSelectedItem(rs_suc.getString(1)+"-"+rs_suc.getString(3));
            }
            else{
            cb_carga.setSelectedItem(rs_suc.getString(1)+"-"+rs_suc.getString(2));
            }
        }catch(SQLException erro){
            JOptionPane.showMessageDialog(null,  "No se pudo recuperar el registro. - ERROR: "+erro);
        }
    }
    
    public String ExtraeCodigo(String args){
        String codigo="";
        String caracter;
        for(int i=0; i<=8; i++){
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
    public static String encryptMD5(String psw){
        String rtn;
        rtn = "";
        /* Plain-text password initialization. */  
        String password = psw;  
        String encryptedpassword = null;  
        try{  
            /* MessageDigest instance for MD5. */  
            MessageDigest m = MessageDigest.getInstance("MD5");  
              
            /* Add plain-text password bytes to digest using MD5 update() method. */  
            m.update(password.getBytes());  
              
            /* Convert the hash value into bytes */   
            byte[] bytes = m.digest();  
              
            /* The bytes array has bytes in decimal form. Converting it into hexadecimal format. */  
            StringBuilder s = new StringBuilder();  
            for(int i=0; i< bytes.length ;i++){  
                s.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));  
            }  
              
            /* Complete hashed password in hexadecimal format */  
            encryptedpassword = s.toString();  
        }   
        catch (NoSuchAlgorithmException e){  
            e.printStackTrace();  
        }  
        rtn = encryptedpassword;
        /* Display the unencrypted and encrypted passwords. */  
        System.out.println("Plain-text password: " + password);  
        System.out.println("Encrypted password using MD5: " + encryptedpassword); 
        return rtn;
    }//end
}//fin funcion
    

