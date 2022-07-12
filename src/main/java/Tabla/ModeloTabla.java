/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tabla;

/**
 *
 * @author Admin
 */


import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;


public class ModeloTabla extends DefaultTableModel{
	
    String[] titulos;
    Object[][] datos;
    ArrayList<Integer> noEditable;
    //enter como tab
  
    
     /* Determina el modelo con el que se va a construir la tabla
     * @param datos
     * @param titulos
     */


    public ModeloTabla() {
        // TODO Auto-generated constructor stub
    }

    public ModeloTabla(Object[][] data, String[] titulos/*, ArrayList<Integer> noEditable*/) {

        super();
        this.titulos = titulos;
        this.datos = datos;
        this.noEditable = noEditable;//new ArrayList<Integer>();
       /* this.noEditable.add(1);
        this.noEditable.add(6);*/
        setDataVector(datos, titulos);
        
    }

 
    public boolean isCellEditable (int row, int column){
        //Definimos si una celda puede ser o no editable
       /* if (column!=Utilidades.PERFIL && column!=Utilidades.EVENTO && column!=Utilidades.NOTA1 && column!=Utilidades.NOTA2 && column!=Utilidades.NOTA3){   
           return false; 
        }else{
           return true;
        }*/
        boolean rtn = true;
        for(int i = 0; i < this.noEditable.size(); i++){
            if(column == this.noEditable.get(i)){
                rtn = false;
            }
        }
        
        return rtn;
    }
    
    @Override
    public void addRow(Object[] rowData) {
        addRow(convertToVector(rowData));
    }
    
    @Override
    public void removeRow(int row) {
        dataVector.removeElementAt(row);
        fireTableRowsDeleted(row, row);
    }
   
}