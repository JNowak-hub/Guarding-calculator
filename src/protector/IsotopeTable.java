package protector;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

public class IsotopeTable extends JFrame implements TableModelListener
{
    private JPanel mainIsotopPanel = new JPanel();

    public IsotopeTable()
    {
        initComponents();
    }
    
    
    public void initComponents()
    {
        int width = Toolkit.getDefaultToolkit().getScreenSize().width-500;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height/2;
        int currentWidth = this.getSize().width;
        int currentHeight = this.getSize().height;
        
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setBounds((width-currentWidth)/4, (height-currentHeight)/2, width/2, height/2);
        this.setTitle("Obliczanie osłon stałych made by: J.N.");
        
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.getContentPane().add(mainIsotopPanel);
        GroupLayout layout = new GroupLayout(getContentPane());
        
        this.getContentPane().setLayout(layout);
        
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        
        
    }
    public void tableChanged(TableModelEvent tme) 
        {
        }
}
