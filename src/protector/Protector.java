package protector;

import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Jakub Nowak
 */
public class Protector extends JFrame implements WindowListener
{
    JPanel mainPanel = new JPanel();
    JButton exit = new JButton("Exit");
    JButton guarding = new JButton("Calculate Guarding");
    JButton isotopesTable = new JButton("View isotopes table");
    JButton activity = new JButton("Calculate activity");
    JButton dose = new JButton("Estimate absorbed dose");
    Protector()
    {
        initComponets();
    }
    
    private void initComponets()
    {
        int width = Toolkit.getDefaultToolkit().getScreenSize().width-500;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height/2;
        int currentWidth = this.getSize().width;
        int currentHeight = this.getSize().height;
        
        this.addWindowListener(this);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setBounds((width-currentWidth)/4, (height-currentHeight)/2, width/2, height/2);
        this.setTitle("Calculating radiation guarding made by: J.N.");
        
        this.getContentPane().add(mainPanel);
        GroupLayout layout = new GroupLayout(getContentPane());
        
        this.getContentPane().setLayout(layout);
        
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        
        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                .addComponent(dose)
                .addGroup(
                layout.createParallelGroup().addComponent(activity).addComponent(isotopesTable)
                )
                .addComponent(guarding)
                
                .addContainerGap(10, Short.MAX_VALUE)
                .addComponent(exit)
                );
        
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                .addGroup(
                layout.createParallelGroup().addComponent(dose).addComponent(activity).addComponent(guarding)
                )
                .addComponent(isotopesTable)
                .addContainerGap(10, Short.MAX_VALUE)
                .addComponent(exit)
                );
        exit.addActionListener((ActionEvent ae) -> {
            int quit = JOptionPane.showConfirmDialog(rootPane, "Czy na pewno chceszs wyjść?");
            if(quit == 0)
                System.exit(0);
        });
        isotopesTable.addActionListener((ActionEvent ae) -> {
            new IsotopesTable().setVisible(true);
        });
        dose.addActionListener((ActionEvent ae) -> {
            new DoseCalculating().setVisible(true);
        });
        activity.addActionListener((ActionEvent ae) -> {
            new ActivityMenu().setVisible(true);
        });
        guarding.addActionListener((ActionEvent ae) -> {
            JOptionPane.showMessageDialog(rootPane, "This feature is in progress");
        });
        
    }
    
    public static void main(String[] args)
    {
        new Protector().setVisible(true);
    }

    @Override
    public void windowOpened(WindowEvent we) 
    {
        JOptionPane.showMessageDialog(rootPane, "Witaj, versja 0.1");
    }

    @Override
    public void windowClosing(WindowEvent we) 
    {
        int quit = JOptionPane.showConfirmDialog(rootPane, "Czy na pewno chceszs wyjść?");
        if(quit == 0)
            System.exit(0);
    }

    @Override
    public void windowClosed(WindowEvent we) 
    {
    }

    @Override
    public void windowIconified(WindowEvent we)
    {
    }

    @Override
    public void windowDeiconified(WindowEvent we) 
    {
    }

    @Override
    public void windowActivated(WindowEvent we)
    {
    }

    @Override
    public void windowDeactivated(WindowEvent we)
    {
    }
}
