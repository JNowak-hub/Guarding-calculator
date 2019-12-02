
package protector;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Jakub Nowak
 */
public class ActivityMenu extends JFrame{
    JPanel mainGuardingPanel = new JPanel();
    
    JButton referenceActivity = new JButton("Calculate reference Activity");
    JButton currentActivity = new JButton("Calculate current Activity");
    JButton indentyfyIsotop = new JButton("Indentyfy Isotop");
    JButton back = new JButton("Back");
    
    public ActivityMenu()
    {
        initComponents();
    }
        private void initComponents()
    {
        int width = Toolkit.getDefaultToolkit().getScreenSize().width-500;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height/2;
        int currentWidth = this.getSize().width;
        int currentHeight = this.getSize().height;
        
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setBounds((width-currentWidth)/4, (height-currentHeight), width/3+10, height+150);
        this.setTitle("Calculating radiation guarding made by: J.N.");
        
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.getContentPane().add(mainGuardingPanel);
        mainGuardingPanel.add(referenceActivity);
        mainGuardingPanel.add(currentActivity);
        mainGuardingPanel.add(indentyfyIsotop);
        GroupLayout layout = new GroupLayout(getContentPane());
        
        this.getContentPane().setLayout(layout);
        
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);   
                layout.setHorizontalGroup(
            layout.createSequentialGroup()
            .addGroup(
            layout.createParallelGroup()
                    .addComponent(referenceActivity, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE ,Short.MAX_VALUE)
                    .addComponent(currentActivity, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE ,Short.MAX_VALUE)
                    .addComponent(indentyfyIsotop, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE ,Short.MAX_VALUE)

            )
            .addContainerGap(10, Short.MAX_VALUE)
            .addComponent(back) 
    );
    
    layout.setVerticalGroup(
            layout.createSequentialGroup()
                    .addComponent(referenceActivity)
                    .addComponent(currentActivity)
                    .addComponent(indentyfyIsotop)

            .addGroup(
            layout.createParallelGroup()
            )
            .addContainerGap(10, Short.MAX_VALUE)
            .addComponent(back)    
    );
    referenceActivity.addActionListener((ActionEvent ae) -> {
        new ReferenceActivityCalculator().setVisible(true);
        });
    back.addActionListener((ActionEvent ae) -> {
        new Protector().setVisible(true);
        dispose();
        });
}
    
}

