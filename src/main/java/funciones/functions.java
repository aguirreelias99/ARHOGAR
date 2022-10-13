package funciones;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import modelo.conexion;
import com.toedter.calendar.IDateEditor;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Locale;
/**
 *
 * @author RC
 */
public class functions {
    private static ResultSet rs_suc;
    protected IDateEditor dateEditor;
    public static void E_estado (javax.swing.JComboBox cb, String aTabla, String arg){
       try{
            //javax.swing.JComboBox cb_carga;
            //cb_carga = cb;
            rs_suc = conexion.ejecuteSQL("SELECT * FROM "+aTabla+" WHERE " + arg+" ORDER BY 1");
            if (!rs_suc.first()){
                return;
            }

            cb.setSelectedItem(rs_suc.getString(1)+"-"+rs_suc.getString(2));
  
        }catch(SQLException erro){
            JOptionPane.showMessageDialog(null,  "No se pudo recuperar el registro. - ERROR: "+erro);
        }
    }
    
    public static String ExtraeCodigo(String args){
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
        String rtn="";
        String password = psw;
        String encryptedpassword = null;
        try{
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.update(password.getBytes());
            byte[] bytes = m.digest();
            StringBuilder s = new StringBuilder();
            for(int i=0; i< bytes.length ;i++){
                s.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
  
            }
            encryptedpassword = s.toString();
        }
        catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }
        rtn = encryptedpassword;
        //System.out.println("Text password: "+password);
        //System.out.println("Encrypted password MD5: "+encryptedpassword);
        return rtn;
    }
   
    public static double sGetDecimalStringAnyLocaleAsDouble (String value) {        
        if (value == null) {
            //System.out.println("CORE - Null value!");
            return 0.0;
        }

        Locale theLocale = Locale.getDefault();
        NumberFormat numberFormat = DecimalFormat.getInstance(theLocale);
        Number theNumber;
        try {
            theNumber = numberFormat.parse(value);
            return theNumber.doubleValue();
        } catch (ParseException e) {
            // The string value might be either 99.99 or 99,99, depending on Locale.
            // We can deal with this safely, by forcing to be a point for the decimal separator, and then using Double.valueOf ...
            //http://stackoverflow.com/questions/4323599/best-way-to-parsedouble-with-comma-as-decimal-separator
            String valueWithDot = value.replaceAll(",",".");

            try {
              return Double.valueOf(valueWithDot);
            } catch (NumberFormatException e2)  {
                // This happens if we're trying (say) to parse a string that isn't a number, as though it were a number!
                // If this happens, it should only be due to application logic problems.
                // In this case, the safest thing to do is return 0, having first fired-off a log warning.
                System.out.println("CORE - Warning: Value is not a number" + value);
                return 0.0;
            }
        }
    }
    
    public static String decimalFormat(double numb){
        String rtn;
        DecimalFormat formatea = new DecimalFormat("#,###.##");
        rtn = formatea.format(numb);
        return rtn;
    }
     /**
     * Sets the date format string. E.g "MMMMM d, yyyy" will result in "July 21,
     * 2004" if this is the selected date and locale is English.
     *
     * @param dfString
     *            The dateFormatString to set.
     */
    public void setDateFormatString(String dfString){
        dateEditor.setDateFormatString (dfString);    
    }
    /**
     * Gets the date format string.
     *
     * @return Returns the dateFormatString.
     */
    public String getDateFormatString(){
        return dateEditor.getDateFormatString();
    }
    /**
     * Returns the date. If the JDateChooser is started with a null date and no
     * date was set by the user, null is returned.
     *
     * @return the current date
     */
    public Date getDate(){
        return dateEditor.getDate ();
    }

}//fin clase
