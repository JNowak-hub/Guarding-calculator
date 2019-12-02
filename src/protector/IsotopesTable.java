
package protector;

import java.awt.Toolkit;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;
import javax.swing.event.*;

/**
 *
 * @author Jakub Nowak
 */
public class IsotopesTable extends Protector implements TableModelListener
{
    float energy;
    float halfLife;
    float exposureRateConstant;
    JTable gamma;
    String[] colums = {"Isotope","half-life","Energy used for calculations","Exposure rate constant"};
    String[][] arrays ={
        {"Na-22","949.629717","1.3","0.0296"},
        {"Na-24","0.6125","2.8","0.0449"},
        {"K-42","0.5166666667","1.5","0.0045"}
    };
        JTable gammaView;
    String[] columsView = {"Isotope","half-life","Energy used for calculations [MeV]","Exposure rate constant cGyh"};
    String[][] arraysView ={
        {"Na-22","2,6 y","1.3","0.0296"},
        {"Na-24","14,7 h","2.8","0.0449"},
        {"K-42","12,4 h","1.5","0.0045"}
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
        gammaView = new JTable(arraysView, columsView)
                {
                    @Override
                    public boolean isCellEditable(int wiersze, int columny)
                {
                    return false;
                }
                };
        gammaView.setBounds(50,50,200,200);
        JScrollPane js = new JScrollPane(gammaView);
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

