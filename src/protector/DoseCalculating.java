
package protector;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;

/**
 *
 * @author Jakub Nowak
 */
public class DoseCalculating extends IsotopesTable
{
    float t;
    float activ;
    float dist;
    float summ;
    float activityUnitMultiplayer;
    float timeUnitMultiplayer;
    float distanceUnitMultiplayer;
    
    JPanel mainGuardingPanel = new JPanel();
    
    JTabbedPane isotopeTab = new JTabbedPane();
    
    JButton back = new JButton("Back");
    JButton calculate = new JButton("Calculate");
    JButton isotopesTable = new JButton("View isotopes table");
    
    JCheckBox unmark1 = new JCheckBox("Slider on/off");
    JCheckBox unmark2 = new JCheckBox("Slider on/off");
    JCheckBox unmark3 = new JCheckBox("Slider on/off");
    
    JTextField activity = new JTextField("Chose unit and enter Activity");
    JTextField time = new JTextField("Chose unit and enter exposure time");
    JTextField distance = new JTextField("Chose unit and enter distance from source");
    
    JSlider activitySlider = new JSlider();
    JSlider timeSlider = new JSlider();
    JSlider distanceSlider = new JSlider();
    
    
    JTextArea distanceValue = new JTextArea(""+distanceSlider.getValue());
    JTextArea activityValue = new JTextArea(""+activitySlider.getValue());
    JTextArea timeValue = new JTextArea(""+timeSlider.getValue());
    JTextArea calculated = new JTextArea();
    
    JLabel enterDistance = new JLabel("Enter distance form source");
    JLabel enterActivity = new JLabel("Enter source activity");
    JLabel enterTime = new JLabel("Enter expousure time");
    
    String [] isotopesName = {"---", "Na-22", "Na-24", "K-42"}; 
    String [] activityUnitName = {"---", "Bq", "kBq", "MBq", "GBq"}; 
    String [] timeUnitName = {"---", "s", "min", "h", "days"}; 
    String [] distanceUnitName = {"---", "cm", "m"}; 
    JComboBox isotopes = new JComboBox(isotopesName);
    JComboBox activityUnit = new JComboBox(activityUnitName);
    JComboBox timeUnit = new JComboBox(timeUnitName);
    JComboBox distanceUnit = new JComboBox(distanceUnitName);
       public DoseCalculating()
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
        mainGuardingPanel.add(activity);
        mainGuardingPanel.add(time);
        mainGuardingPanel.add(distance);
        GroupLayout layout = new GroupLayout(getContentPane());
        
        this.getContentPane().setLayout(layout);
        
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        
        
        layout.setHorizontalGroup(
            layout.createSequentialGroup()
            .addGroup(
            layout.createParallelGroup()
                    
                    .addComponent(enterActivity)
                    .addComponent(activityUnit, 50, 50 ,60)
                    .addComponent(activity, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE ,Short.MAX_VALUE)
                    .addComponent(activitySlider)
                    .addComponent(activityValue)
                    .addComponent(unmark3)
                    .addComponent(enterTime)
                    .addComponent(timeUnit, 50, 50 ,60)
                    .addComponent(time, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE ,Short.MAX_VALUE)
                    .addComponent(timeSlider)
                    .addComponent(timeValue)
                    .addComponent(unmark1)
                    .addComponent(enterDistance)
                    .addComponent(distanceUnit, 50, 50 ,60)
                    .addComponent(distance, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE ,Short.MAX_VALUE)
                    .addComponent(distanceSlider)
                    .addComponent(distanceValue)
                    .addComponent(unmark2)
                    .addComponent(calculate)
                    .addComponent(calculated)
            )
            
            .addComponent(isotopes)
                    .addComponent(isotopesTable)
            .addContainerGap(10, Short.MAX_VALUE)
            .addComponent(back) 
    );
    
    layout.setVerticalGroup(
            layout.createSequentialGroup()
                    .addComponent(isotopes)
                    .addComponent(enterTime)
                    .addComponent(timeUnit)
                    .addComponent(time)
                    .addComponent(timeSlider)
                    .addComponent(timeValue)
                    .addComponent(unmark1)
                    .addComponent(enterDistance)
                    .addComponent(distanceUnit)
                    .addComponent(distance)
                    .addComponent(distanceSlider)
                    .addComponent(distanceValue)
                    .addComponent(unmark2)
                    .addComponent(enterActivity)
                    .addComponent(activityUnit)
                    .addComponent(activity)
                    .addComponent(activitySlider)
                    .addComponent(activityValue)
                    .addComponent(unmark3)
                    .addComponent(calculate)
                    .addComponent(calculated)
            .addGroup(
            layout.createParallelGroup()
            )     
            .addComponent(isotopesTable) 
            .addContainerGap(10, Short.MAX_VALUE)
            .addComponent(back)    
    );
    back.addActionListener((ActionEvent ae) -> {
        dispose();
        });
    isotopesTable.addActionListener((ActionEvent ae) -> {
        new IsotopesTable().setVisible(true);
        });
    
    /*
    *           TextField input part (cosume letters) adding to clipboard
    */
    time.addKeyListener(new KeyAdapter() 
        {
            private boolean number(char zn)
        {
           if (zn >='0' && zn <= '9' || zn == '.')
            return true;
                
            return false;     
        }
            @Override
            public void keyTyped(KeyEvent ke)
        {
            if(!number(ke.getKeyChar()))
                ke.consume();
                
         }
            @Override
            public void keyPressed(KeyEvent ke)
            {
                if(ke.isControlDown() && ke.getKeyCode() == KeyEvent.VK_V)
                {
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                DataFlavor flavor = DataFlavor.stringFlavor;
                String clip = "";
                
                try 
                {
                    clip = (String)clipboard.getData(flavor);
                } catch (UnsupportedFlavorException ex) 
                {
                    JOptionPane.showMessageDialog(rootPane, "It's not a number");
                } catch (IOException ex) 
                {
                    JOptionPane.showMessageDialog(rootPane, "Error occured");
                }
                
                for(int i = 0; i < clip.length(); i++)
                {
                    if(!number(clip.charAt(i)))
                    {
                        ke.consume();
                        break;
                    }
                }
                }
            }            
        });
    activity.addKeyListener(new KeyAdapter() 
        {
            private boolean number(char zn)
        {
           if (zn >= '0' && zn <= '9' || zn == '.')
            return true;
                
            return false;     
        }
            @Override
            public void keyTyped(KeyEvent ke)
        {
            if(!number(ke.getKeyChar()))
                ke.consume();  
         }
            @Override
            public void keyPressed(KeyEvent ke)
            {
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                DataFlavor flavor = DataFlavor.stringFlavor;
                String clip = "";
                
                try {
                    clip = (String)clipboard.getData(flavor);
                } catch (UnsupportedFlavorException ex) 
                {
                    JOptionPane.showMessageDialog(rootPane, "It's not a number");
                } catch (IOException ex) 
                {
                    JOptionPane.showMessageDialog(rootPane, "Error occured");
                }
                
                for(int i = 0; i < clip.length(); i++)
                {
                    if(!number(clip.charAt(i)))
                    {
                        ke.consume();
                        break;
                    }
                }
            }
        });
    distance.addKeyListener(new KeyAdapter() 
        {
            private boolean number(char zn)
        {
           if (zn >='0' && zn <= '9' || zn == '.')
            return true;
                
            return false;     
        }
            @Override
            public void keyTyped(KeyEvent ke)
        {
            if(!number(ke.getKeyChar()))
                ke.consume();
                
         }
            @Override
            public void keyPressed(KeyEvent ke)
            {
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                DataFlavor flavor = DataFlavor.stringFlavor;
                String clip = "";
                
                try {
                    clip = (String)clipboard.getData(flavor);
                } catch (UnsupportedFlavorException ex) 
                {
                    JOptionPane.showMessageDialog(rootPane, "It's not a number");
                } catch (IOException ex) 
                {
                    JOptionPane.showMessageDialog(rootPane, "Error occured");
                }
                
                for(int i = 0; i < clip.length(); i++)
                {
                    if(!number(clip.charAt(i)))
                    {
                        ke.consume();
                        break;
                    }
                }
            }            
        });
    
    /*
    *               Geting valuses form isotopesTable using JTabbePane
    */
            isotopes.addActionListener((ActionEvent ae) -> {
                if(((JComboBox)ae.getSource()).getSelectedIndex() > 0)
                {
                    getHalfLife(((JComboBox)ae.getSource()).getSelectedIndex()-1,1);
                    getEnergy(((JComboBox)ae.getSource()).getSelectedIndex()-1,2);
                    getExposureRateConstant(((JComboBox)ae.getSource()).getSelectedIndex()-1,3);
                    System.out.println(energy);
                    System.out.println(halfLife);
                    System.out.println(exposureRateConstant);
                }
        });
    /*
    *               Getting unit multiplayer from UnitBox       
    */        
            activityUnit.addActionListener((ActionEvent ae) -> {
                if(((JComboBox)ae.getSource()).getSelectedIndex() == 1)
                    activityUnitMultiplayer = (float) 0.000000001;
                else if(((JComboBox)ae.getSource()).getSelectedIndex() == 2)
                    activityUnitMultiplayer = (float) 0.000001;
                else if(((JComboBox)ae.getSource()).getSelectedIndex() == 3)
                    activityUnitMultiplayer = (float) 0.001;
                else if(((JComboBox)ae.getSource()).getSelectedIndex() == 4)
                    activityUnitMultiplayer = 1;
        });
            
            timeUnit.addActionListener((ActionEvent ae) -> {
                if(((JComboBox)ae.getSource()).getSelectedIndex() == 1)
                    timeUnitMultiplayer = (float) 0.0002777778;
                else if(((JComboBox)ae.getSource()).getSelectedIndex() == 2)
                    timeUnitMultiplayer = (float) 0.0166666667;
                else if(((JComboBox)ae.getSource()).getSelectedIndex() == 3)
                    timeUnitMultiplayer = 1;
                else if(((JComboBox)ae.getSource()).getSelectedIndex() == 4)
                    timeUnitMultiplayer = 24;
        });
            
            distanceUnit.addActionListener((ActionEvent ae) -> {
                if(((JComboBox)ae.getSource()).getSelectedIndex() == 1)
                    distanceUnitMultiplayer = (float) 0.01;
                else if(((JComboBox)ae.getSource()).getSelectedIndex() == 2)
                    distanceUnitMultiplayer = 1;
        });
            
    
    /*
    *               Calculating and getting values part
    */
            calculate.addActionListener((ActionEvent ae) -> {
                if(isotopes.getSelectedIndex() == 0)
                {
                    JOptionPane.showMessageDialog(mainGuardingPanel,"Chose isotope");
                }
                else
                {
                    try
                    {

                        if(unmark1.isSelected() && unmark2.isSelected() && unmark3.isSelected())
                        {
                            t = Float.parseFloat(timeValue.getText());
                            dist = Float.parseFloat(distanceValue.getText());
                            activ = Float.parseFloat(activityValue.getText());
                        }
                        else if(!unmark1.isSelected() && unmark2.isSelected() && unmark3.isSelected())
                        {
                            t = Float.parseFloat(time.getText());
                            dist = Float.parseFloat(distanceValue.getText());
                            activ = Float.parseFloat(activityValue.getText());     
                        }
                        else if(unmark1.isSelected() && !unmark2.isSelected() && unmark3.isSelected())
                        {
                            t = Float.parseFloat(timeValue.getText());
                            dist = Float.parseFloat(distance.getText());
                            activ = Float.parseFloat(activityValue.getText());
                        }                    
                        else if(unmark1.isSelected() && unmark2.isSelected() && !unmark3.isSelected())
                        {
                            t = Float.parseFloat(timeValue.getText());
                            dist = Float.parseFloat(distanceValue.getText());
                            activ = Float.parseFloat(activity.getText());
                        }                     
                        else if(!unmark1.isSelected() && !unmark2.isSelected() && unmark3.isSelected())
                        {
                            t = Float.parseFloat(time.getText());
                            dist = Float.parseFloat(distance.getText());
                            activ = Float.parseFloat(activityValue.getText());
                        }                     
                        else if(!unmark1.isSelected() && unmark2.isSelected() && !unmark3.isSelected())
                        {
                            t = Float.parseFloat(time.getText());
                            dist = Float.parseFloat(distanceValue.getText());
                            activ = Float.parseFloat(activity.getText());
                        }                     
                        else if(unmark1.isSelected() && !unmark2.isSelected() && !unmark3.isSelected())
                        {
                            t = Float.parseFloat(timeValue.getText());
                            dist = Float.parseFloat(distance.getText());
                            activ = Float.parseFloat(activity.getText());
                        }                     
                        else if(unmark1.isSelected() && !unmark2.isSelected() && unmark3.isSelected())
                        {
                            t = Float.parseFloat(timeValue.getText());
                            dist = Float.parseFloat(distance.getText());
                            activ = Float.parseFloat(activityValue.getText());
                        }                     
                        else if(!unmark1.isSelected() && !unmark2.isSelected() && !unmark3.isSelected())
                        {
                            t = Float.parseFloat(time.getText());
                            dist = Float.parseFloat(distance.getText());
                            activ = Float.parseFloat(activity.getText());
                        }
                        
                        summ = (float) ((exposureRateConstant*activ*activityUnitMultiplayer*t*timeUnitMultiplayer)/(Math.pow((Double.parseDouble(String.valueOf(dist))*distanceUnitMultiplayer), 2)));
                        calculated.setText("Wynik: "+ Float.toString(summ) + "[cGy]");    
                    }
                    catch(NumberFormatException e)
                    {
                        JOptionPane.showMessageDialog(mainGuardingPanel,e.getMessage());
                    }
                    if(Float.isNaN(summ))
                    {
                        JOptionPane.showMessageDialog(mainGuardingPanel,"Chose correct units");
                    }
                }
        });
            /*
            *           Slider properties part
            */
        
        unmark1.addActionListener((ActionEvent ae) -> {
            if(unmark1.isSelected())
            {
                time.setEnabled(false);
                timeSlider.setEnabled(true);
            }
            else if (!unmark1.isSelected())
            {
                time.setEnabled(true);
                timeSlider.setEnabled(false);
            }
    });
        unmark2.addActionListener((ActionEvent ae) -> {
            if(unmark2.isSelected())
            {
                distance.setEnabled(false);
                distanceSlider.setEnabled(true);
            }
            else if (!unmark2.isSelected())
            {
                distance.setEnabled(true);
                distanceSlider.setEnabled(false);
            }
    });
        unmark3.addActionListener((ActionEvent ae) -> {
            if(unmark3.isSelected())
            {
                activity.setEnabled(false);
                activitySlider.setEnabled(true);
            }
            else if (!unmark3.isSelected())
            {
                activity.setEnabled(true);
                activitySlider.setEnabled(false);
            }
    });
        distanceSlider.setMajorTickSpacing(10);
        distanceSlider.setMinorTickSpacing(2);
        distanceSlider.setPaintTicks(true);
        distanceSlider.setEnabled(false);
        timeSlider.setMajorTickSpacing(10);
        timeSlider.setMinorTickSpacing(2);
        timeSlider.setPaintTicks(true);
        timeSlider.setEnabled(false);
        activitySlider.setMajorTickSpacing(10);
        activitySlider.setMinorTickSpacing(2);
        activitySlider.setPaintTicks(true);
        activitySlider.setEnabled(false);
        
        distanceSlider.addChangeListener((ChangeEvent ce) -> {
            distanceValue.setText(""+((JSlider)ce.getSource()).getValue());
        });
        activitySlider.addChangeListener((ChangeEvent ce) -> {
            activityValue.setText(""+((JSlider)ce.getSource()).getValue());
        });
        timeSlider.addChangeListener((ChangeEvent ce) -> {
            timeValue.setText(""+((JSlider)ce.getSource()).getValue());
        });
} 
}