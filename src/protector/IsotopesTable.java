/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package protector;

import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;
import javax.swing.event.*;

/**
 *
 * @author admin
 */
public class IsotopesTable extends JFrame implements TableModelListener
{
    float energy;
    float halfLife;
    float exposureRateConstant;
    JTable gamma;
    String[] colums = {"Isotope","half-life","Energy used for calculations","Exposure rate constant"};
    String[][] arrays ={
        {"Na-22","949","1.3","0.0296"},
        {"Na-24","0.6125","2.8","0.0449"},
        {"K-42","0.517","1.5","0.0045"}
    };
    
    public float getHalfLife(int a, int b)
    {
        this.halfLife = Float.valueOf(gamma.getModel().getValueAt(a, b).toString());
        return halfLife;
    }
    public float getEnergy(int a, int b)
    {
        this.energy = Float.valueOf(gamma.getModel().getValueAt(a, b).toString());
        return energy;
    }
    public float getExposureRateConstant(int a, int b)
    {
        this.exposureRateConstant = Float.valueOf(gamma.getModel().getValueAt(a, b).toString());
        return exposureRateConstant;
    }
    
    public IsotopesTable()
    {
        this.setIconImage(Toolkit.getDefaultToolkit().getImage("tableIcon.bmp"));
        gamma = new JTable(arrays, colums)
                {
                    @Override
                    public boolean isCellEditable(int wiersze, int columny)
                {
                    return false;
                }
                };
        gamma.setBounds(50,50,200,200);
        JScrollPane js = new JScrollPane(gamma);
        this.add(js);
        this.setSize(300,400);
    }
    
//      public Object GetData(JTable table, int row_index, int col_index){
//        return table.getModel().getValueAt(row_index, col_index);
//  }

    @Override
    public void tableChanged(TableModelEvent e)
    {
        int row = e.getFirstRow();
        int column = e.getColumn();
        TableModel model = (TableModel)e.getSource();
        String columnName = model.getColumnName(column);
        Object data = model.getValueAt(row, column);
       throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

    }
}

