/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import modelo.conexion;
import modelo.tableModel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author user
 */
public class tableController {
    //Claves primarias de la tabla
    private ArrayList<String> tableKeys;
    //El modelo para nuestra tabla
    tableModel tm;
    public void init(String table){
        this.tableKeys = new ArrayList<String>();
        this.tableKeys = conexion.getKeyColumns(table, "PRI");       
        tm = new tableModel();
        tm.init(table);
    }//fin init
    
    /**
     * Prepara y ejecuta sentencia para insertar un registro. Utiliza el método de saveRegister de tableModel
     * @param viewRegister Map<String, String> para clave valor de datos de la vista
     * @return rtn int valor según la ejecución haya sido exitoso 1 o no.
     */
    public int createReg(Map<String, String> viewRegister){
        int rtn, id;
        rtn = 0;    
        String idname = this.tableKeys.get(0);
        id = this.tm.getMaxId();
        id = id + 1;
        viewRegister.put(idname, id+"");
        rtn = this.tm.saveRegister(viewRegister);
        return rtn;
    }//en createReg
    
    /**
     * Método que permite la búsqueda de un registro por el id. Recurre al método interno createIdVal
     * y a readRegisterById de la clase tableModel.
     * @param viewRegister Map par de campos y valores que se pasa desde la vista
     * @return rtn Map con el registro recuperado
     */
    public Map<String, String> searchById(Map<String, String> viewRegister){ //ok
        Map<String, String> rtn;
        Map<String, String> where;
        rtn = new HashMap<String, String>();
        where = this.createIdVal(viewRegister);
        rtn = this.tm.readRegisterById(viewRegister, where);
        return rtn;
    }//fin searchById
    
    /**
     * Méttodo que prepara la navegación entre registros.
     * @param id String en el que se pasa el número de registro actual
     * @param goTo String en que se dice a qué posición se desea mover (FIRST, NEXT, PRIOR, LAST)
     * @return rtn Map que contiene el registro recuperado
     */
    public Map<String, String> navegationReg(String id, String goTo){
        Map<String, String> rtn;
        rtn = new HashMap<String, String>();
        rtn = tm.readNavetionReg (id, goTo);      
        return rtn;
    }//fin searchById
    
    /**
     * Método que prepara para la eliminación de registro. Usa el método deleteRegister de la clase tableModel
     * @param id Sring el código actual en la vista
     * @return rtn int devuelve las filas afectadas
     */
    public int deleteReg(String id){//ok
        int rows = 0;
        rows = this.tm.deleteRegister(id);
        //Siempre hay que controlar lo que retorna para poder resetear la vista
        return rows;
    }//fin deleteReg
    
    /**
     * Método que prepara la actualiación de un registro. Invoca métodos propios de la clase createIdVal, createSetFieldsValues; así como
     * el método updateRegister de la clase tableModel
     * @param viewRegister Map con los datos de la vista.
     * @return rtn int cantidad de filas afectadas.
     */
    public int updateReg(Map<String, String> viewRegister){
        Map<String, String> fieldsValues; //Separar los campos y valores a actualizar
        Map<String, String> keysValues; //Separar las claves y valores respecto a los cuales actualizar
        int rows = 0;
        keysValues = this.createIdVal(viewRegister);
        fieldsValues = this.createSetFieldsValues(viewRegister);
        rows = this.tm.updateRegister(fieldsValues, keysValues);
        return rows;
    }//end updateReg
    
    /**
     * Método que construye un Map con sólo los campos de clave primar con sus respectivos valores.
     * @param viewRegister Map con los pares campo-valor de claves primarias
     * @return rtn Map de las claves de tabla con sus valores si los tiene
     */
    public Map<String, String> createIdVal(Map<String, String> viewRegister){
         Map<String, String> rtn;
        rtn = new HashMap<String, String>();
        Iterator<String> arrayIterator = this.tableKeys.iterator();
        while(arrayIterator.hasNext()){
            String elemento = arrayIterator.next();
            if(viewRegister.containsKey(elemento)){
                rtn.put(elemento, viewRegister.get(elemento));
            }
        }//fin while    
        return rtn;
    }//createIdVal
    
    /**
     * Método que construye un Map de pares campos-valores que no sean clave primaria en la tabla.
     * @param viewRegister Map de pareas campos-valores enviados desde la vista
     * @return rtn Map de pares campos-valores que no sean clave primaria en la tabla
     */
    public Map<String, String> createSetFieldsValues(Map<String, String> viewRegister){
        Map<String, String> rtn;
        rtn = new HashMap<String, String>();
        for (Map.Entry<String, String> entry : viewRegister.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            if(!this.tableKeys.contains(key)){
                rtn.put(key, value);
            }
        }  
        System.out.println("C createSetFieldsValues RETURN "+rtn.toString());
        return rtn;
    }//createSetFieldsValues

    int updateReg(ArrayList<Map<String, String>> alCabecera) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    int deleteReg(ArrayList<Map<String, String>> alCabecera) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    ArrayList<Map<String, String>> searchListById(Map<String, String> fields, Map<String, String> where) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


   
}//fin de la clase
