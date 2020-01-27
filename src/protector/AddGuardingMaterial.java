package protector;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AddGuardingMaterial{
	
	AddGuardingMaterial(JFrame frame, JPanel panel, JPanel mainPanel){
		
		String [] distanceUnitName = {"---", "cm", "m"};
		
		JLabel chooseMaterial = new JLabel("Choose material:");
		JLabel chooseThicnes = new JLabel("Enter thicnes:");
		
		JComboBox thicnesUnit = new JComboBox(distanceUnitName);
		JTextField enterThicnes = new JTextField("Enter thicnes:");
		
		panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
		panel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
		panel.add(Box.createHorizontalGlue());
		panel.add(thicnesUnit);
		panel.add(enterThicnes);

		// Put everything together, using the content pane's BorderLayout.
		Container contentPane = frame.getContentPane();
		contentPane.add(mainPanel, BorderLayout.SOUTH);
		contentPane.add(panel, BorderLayout.PAGE_START);
		
		
		frame.repaint();
	}

}
