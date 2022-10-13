/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JTable;

/**
 *
 * @author RC
 */
public class NextCellAction extends AbstractAction {
  private JTable table;
    
    public NextCellAction(JTable table){
        this.table = table;
    }

    @Override
    public boolean accept(Object sender) {
        return super.accept(sender); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int col = table.getSelectedColumn();
        int row = table.getSelectedRow();
        
        int colCount = table.getColumnCount();
        int rowCount = table.getRowCount();
        
        col++;
        if(col >= colCount){
            col = 0;
            row++;
        }
        if(row >= rowCount){
            col = 0;
        }
        table.getSelectionModel().setSelectionInterval(row, row);
        table.getColumnModel().getSelectionModel().setSelectionInterval(col, col);
    }
}
