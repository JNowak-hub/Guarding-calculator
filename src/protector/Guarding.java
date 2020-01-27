
package protector;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
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
public class Guarding extends IsotopesTable {
	float t;
	float activ;
	float dist;
	float summ;
	float activityUnitMultiplayer;
	float timeUnitMultiplayer;
	float distanceUnitMultiplayer;

	JPanel mainGuardingPanel = new JPanel();
	JPanel materialPanel = new JPanel();
	JPanel buttonPane = new JPanel();

	JTabbedPane isotopeTab = new JTabbedPane();

	JButton back = new JButton("Back");
	JButton calculate = new JButton("Calculate");
	JButton isotopesTable = new JButton("View isotopes table");
	JButton addGuardingMaterial = new JButton("Add guarding material");

	JCheckBox unmark1 = new JCheckBox("Slider on/off");
	JCheckBox unmark2 = new JCheckBox("Slider on/off");
	JCheckBox unmark3 = new JCheckBox("Slider on/off");

	JTextField activity = new JTextField("0.0");
	JTextField time = new JTextField("0.0");
	JTextField distance = new JTextField("0.0");

	JSlider activitySlider = new JSlider();
	JSlider timeSlider = new JSlider();
	JSlider distanceSlider = new JSlider();

	JLabel chooseIsotop = new JLabel("Choose isotop :");
	JLabel distanceValue = new JLabel("" + distanceSlider.getValue());
	JLabel activityValue = new JLabel("" + activitySlider.getValue());
	JLabel timeValue = new JLabel("" + timeSlider.getValue());
	JLabel calculated = new JLabel();
	JLabel enterDistance = new JLabel("Chose unit and enter distance from source :");
	JLabel enterActivity = new JLabel("Chose unit and enter Activity :");
	JLabel enterTime = new JLabel("Chose unit and enter exposure time :");

	String[] isotopesName = { "---", "Na-22", "Na-24", "K-42" };
	String[] activityUnitName = { "---", "Bq", "kBq", "MBq", "GBq" };
	String[] timeUnitName = { "---", "s", "min", "h", "days" };
	String[] distanceUnitName = { "---", "cm", "m" };
	JComboBox isotopes = new JComboBox(isotopesName);
	JComboBox activityUnit = new JComboBox(activityUnitName);
	JComboBox timeUnit = new JComboBox(timeUnitName);
	JComboBox distanceUnit = new JComboBox(distanceUnitName);

	public Guarding() {
		initComponents();
	}

	private void initComponents() {
		int width = Toolkit.getDefaultToolkit().getScreenSize().width - 500;
		int height = Toolkit.getDefaultToolkit().getScreenSize().height / 2;
		int currentWidth = this.getSize().width;
		int currentHeight = this.getSize().height;

		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setBounds((width - currentWidth) / 4, (height - currentHeight), width / 3 + 10 - 100, height + 150);
		this.setTitle("Calculating absobed dose");

		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.getContentPane().add(mainGuardingPanel);

		mainGuardingPanel.setLayout(new BoxLayout(mainGuardingPanel, BoxLayout.PAGE_AXIS));

		mainGuardingPanel.add(chooseIsotop);
		mainGuardingPanel.add(isotopes);

		mainGuardingPanel.add(Box.createRigidArea(new Dimension(0, 25)));

		mainGuardingPanel.add(enterDistance);
		mainGuardingPanel.add(distanceUnit);
		mainGuardingPanel.add(distance);
		mainGuardingPanel.add(distanceSlider);
		mainGuardingPanel.add(unmark2);
		mainGuardingPanel.add(distanceValue);

		mainGuardingPanel.add(Box.createRigidArea(new Dimension(0, 25)));

		mainGuardingPanel.add(enterTime);
		mainGuardingPanel.add(timeUnit);
		mainGuardingPanel.add(time);
		mainGuardingPanel.add(timeSlider);
		mainGuardingPanel.add(unmark1);
		mainGuardingPanel.add(timeValue);

		mainGuardingPanel.add(Box.createRigidArea(new Dimension(0, 25)));

		mainGuardingPanel.add(enterActivity);
		mainGuardingPanel.add(activityUnit);
		mainGuardingPanel.add(activity);
		mainGuardingPanel.add(activitySlider);
		mainGuardingPanel.add(unmark3);
		mainGuardingPanel.add(activityValue);

		mainGuardingPanel.add(Box.createRigidArea(new Dimension(0, 25)));

		mainGuardingPanel.add(addGuardingMaterial);

		mainGuardingPanel.add(Box.createRigidArea(new Dimension(0, 25)));
		
		mainGuardingPanel.add(calculate);
		mainGuardingPanel.add(calculated);

		mainGuardingPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		// Lay out the buttons from left to right.
		buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));
		buttonPane.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
		buttonPane.add(Box.createHorizontalGlue());
		buttonPane.add(back);
		buttonPane.add(Box.createRigidArea(new Dimension(10, 0)));
		buttonPane.add(isotopesTable);

		// Put everything together, using the content pane's BorderLayout.
		Container contentPane = getContentPane();
		contentPane.add(mainGuardingPanel, BorderLayout.CENTER);
		contentPane.add(buttonPane, BorderLayout.PAGE_END);

		back.addActionListener((ActionEvent ae) -> {
			dispose();
		});
		isotopesTable.addActionListener((ActionEvent ae) -> {
			new IsotopesTable().setVisible(true);
		});
		addGuardingMaterial.addActionListener((ActionEvent ae) -> {
			AddGuardingMaterial newMaterial = new AddGuardingMaterial(this, materialPanel, mainGuardingPanel);
		});
		/*
		 * TextField input part (cosume letters) adding to clipboard
		 */
		time.addKeyListener(new KeyAdapter() {
			private boolean number(char zn) {
				if (zn >= '0' && zn <= '9' || zn == '.')
					return true;

				return false;
			}

			@Override
			public void keyTyped(KeyEvent ke) {
				if (!number(ke.getKeyChar()))
					ke.consume();

			}

			@Override
			public void keyPressed(KeyEvent ke) {
				if (ke.isControlDown() && ke.getKeyCode() == KeyEvent.VK_V) {
					Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
					DataFlavor flavor = DataFlavor.stringFlavor;
					String clip = "";

					try {
						clip = (String) clipboard.getData(flavor);
					} catch (UnsupportedFlavorException ex) {
						JOptionPane.showMessageDialog(rootPane, "It's not a number");
					} catch (IOException ex) {
						JOptionPane.showMessageDialog(rootPane, "Error occured");
					}

					for (int i = 0; i < clip.length(); i++) {
						if (!number(clip.charAt(i)) && ke.getKeyCode() != KeyEvent.VK_BACK_SPACE) {
							ke.consume();
							break;
						}
					}
				}
			}
		});
		activity.addKeyListener(new KeyAdapter() {
			private boolean number(char zn) {
				if (zn >= '0' && zn <= '9' || zn == '.')
					return true;

				return false;
			}

			@Override
			public void keyTyped(KeyEvent ke) {
				if (!number(ke.getKeyChar()))
					ke.consume();
			}

			@Override
			public void keyPressed(KeyEvent ke) {
				Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
				DataFlavor flavor = DataFlavor.stringFlavor;
				String clip = "";

				try {
					clip = (String) clipboard.getData(flavor);
				} catch (UnsupportedFlavorException ex) {
					JOptionPane.showMessageDialog(rootPane, "It's not a number");
				} catch (IOException ex) {
					JOptionPane.showMessageDialog(rootPane, "Error occured");
				}

				for (int i = 0; i < clip.length(); i++) {
					if (!number(clip.charAt(i)) && ke.getKeyCode() != KeyEvent.VK_BACK_SPACE) {
						ke.consume();
						break;
					}
				}
			}
		});
		distance.addKeyListener(new KeyAdapter() {
			private boolean number(char zn) {
				if (zn >= '0' && zn <= '9' || zn == '.')
					return true;

				return false;
			}

			@Override
			public void keyTyped(KeyEvent ke) {
				if (!number(ke.getKeyChar()))
					ke.consume();

			}

			@Override
			public void keyPressed(KeyEvent ke) {
				Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
				DataFlavor flavor = DataFlavor.stringFlavor;
				String clip = "";

				try {
					clip = (String) clipboard.getData(flavor);
				} catch (UnsupportedFlavorException ex) {
					JOptionPane.showMessageDialog(rootPane, "It's not a number");
				} catch (IOException ex) {
					JOptionPane.showMessageDialog(rootPane, "Error occured");
				}

				for (int i = 0; i < clip.length(); i++) {
					if (!number(clip.charAt(i)) && ke.getKeyCode() != KeyEvent.VK_BACK_SPACE) {
						ke.consume();
						break;
					}
				}
			}
		});

		/*
		 * Geting valuses form isotopesTable using JTabbePane
		 */
		isotopes.addActionListener((ActionEvent ae) -> {
			if (((JComboBox) ae.getSource()).getSelectedIndex() > 0) {
				getHalfLife(((JComboBox) ae.getSource()).getSelectedIndex() - 1, 1);
				getEnergy(((JComboBox) ae.getSource()).getSelectedIndex() - 1, 2);
				getExposureRateConstant(((JComboBox) ae.getSource()).getSelectedIndex() - 1, 3);
				System.out.println(energy);
				System.out.println(halfLife);
				System.out.println(exposureRateConstant);
			}
		});
		/*
		 * Getting unit multiplayer from UnitBox
		 */
		activityUnit.addActionListener((ActionEvent ae) -> {
			if (((JComboBox) ae.getSource()).getSelectedIndex() == 1)
				activityUnitMultiplayer = (float) 0.000000001;
			else if (((JComboBox) ae.getSource()).getSelectedIndex() == 2)
				activityUnitMultiplayer = (float) 0.000001;
			else if (((JComboBox) ae.getSource()).getSelectedIndex() == 3)
				activityUnitMultiplayer = (float) 0.001;
			else if (((JComboBox) ae.getSource()).getSelectedIndex() == 4)
				activityUnitMultiplayer = 1;
		});

		timeUnit.addActionListener((ActionEvent ae) -> {
			if (((JComboBox) ae.getSource()).getSelectedIndex() == 1)
				timeUnitMultiplayer = (float) 0.0002777778;
			else if (((JComboBox) ae.getSource()).getSelectedIndex() == 2)
				timeUnitMultiplayer = (float) 0.0166666667;
			else if (((JComboBox) ae.getSource()).getSelectedIndex() == 3)
				timeUnitMultiplayer = 1;
			else if (((JComboBox) ae.getSource()).getSelectedIndex() == 4)
				timeUnitMultiplayer = 24;
		});

		distanceUnit.addActionListener((ActionEvent ae) -> {
			if (((JComboBox) ae.getSource()).getSelectedIndex() == 1)
				distanceUnitMultiplayer = (float) 0.01;
			else if (((JComboBox) ae.getSource()).getSelectedIndex() == 2)
				distanceUnitMultiplayer = 1;
		});

		/*
		 * Calculating and getting values part
		 */
		calculate.addActionListener((ActionEvent ae) -> {

			if (isotopes.getSelectedIndex() == 0) {
				JOptionPane.showMessageDialog(mainGuardingPanel, "Chose isotope");
			} else {
				try {

					if (unmark1.isSelected() && unmark2.isSelected() && unmark3.isSelected()) {
						t = Float.parseFloat(timeValue.getText());
						dist = Float.parseFloat(distanceValue.getText());
						activ = Float.parseFloat(activityValue.getText());
					} else if (!unmark1.isSelected() && unmark2.isSelected() && unmark3.isSelected()) {
						t = Float.parseFloat(time.getText());
						dist = Float.parseFloat(distanceValue.getText());
						activ = Float.parseFloat(activityValue.getText());
					} else if (unmark1.isSelected() && !unmark2.isSelected() && unmark3.isSelected()) {
						t = Float.parseFloat(timeValue.getText());
						dist = Float.parseFloat(distance.getText());
						activ = Float.parseFloat(activityValue.getText());
					} else if (unmark1.isSelected() && unmark2.isSelected() && !unmark3.isSelected()) {
						t = Float.parseFloat(timeValue.getText());
						dist = Float.parseFloat(distanceValue.getText());
						activ = Float.parseFloat(activity.getText());
					} else if (!unmark1.isSelected() && !unmark2.isSelected() && unmark3.isSelected()) {
						t = Float.parseFloat(time.getText());
						dist = Float.parseFloat(distance.getText());
						activ = Float.parseFloat(activityValue.getText());
					} else if (!unmark1.isSelected() && unmark2.isSelected() && !unmark3.isSelected()) {
						t = Float.parseFloat(time.getText());
						dist = Float.parseFloat(distanceValue.getText());
						activ = Float.parseFloat(activity.getText());
					} else if (unmark1.isSelected() && !unmark2.isSelected() && !unmark3.isSelected()) {
						t = Float.parseFloat(timeValue.getText());
						dist = Float.parseFloat(distance.getText());
						activ = Float.parseFloat(activity.getText());
					} else if (unmark1.isSelected() && !unmark2.isSelected() && unmark3.isSelected()) {
						t = Float.parseFloat(timeValue.getText());
						dist = Float.parseFloat(distance.getText());
						activ = Float.parseFloat(activityValue.getText());
					} else if (!unmark1.isSelected() && !unmark2.isSelected() && !unmark3.isSelected()) {
						t = Float.parseFloat(time.getText());
						dist = Float.parseFloat(distance.getText());
						activ = Float.parseFloat(activity.getText());
					}

					summ = (float) ((exposureRateConstant * activ * activityUnitMultiplayer * t * timeUnitMultiplayer)
							/ (Math.pow((Double.parseDouble(String.valueOf(dist)) * distanceUnitMultiplayer), 2)));
					calculated.setText("Wynik: " + Float.toString(summ) + "[cGy]");
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(mainGuardingPanel, e.getMessage());
				}
				if (Float.isNaN(summ)) {
					JOptionPane.showMessageDialog(mainGuardingPanel, "Chose correct units");
				}
			}
		});
		/*
		 * Slider properties part
		 */

		unmark1.addActionListener((ActionEvent ae) -> {
			if (unmark1.isSelected()) {
				time.setEnabled(false);
				timeSlider.setEnabled(true);
			} else if (!unmark1.isSelected()) {
				time.setEnabled(true);
				timeSlider.setEnabled(false);
			}
		});
		unmark2.addActionListener((ActionEvent ae) -> {
			if (unmark2.isSelected()) {
				distance.setEnabled(false);
				distanceSlider.setEnabled(true);
			} else if (!unmark2.isSelected()) {
				distance.setEnabled(true);
				distanceSlider.setEnabled(false);
			}
		});
		unmark3.addActionListener((ActionEvent ae) -> {
			if (unmark3.isSelected()) {
				activity.setEnabled(false);
				activitySlider.setEnabled(true);
			} else if (!unmark3.isSelected()) {
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
			distanceValue.setText("" + ((JSlider) ce.getSource()).getValue());
		});
		activitySlider.addChangeListener((ChangeEvent ce) -> {
			activityValue.setText("" + ((JSlider) ce.getSource()).getValue());
		});
		timeSlider.addChangeListener((ChangeEvent ce) -> {
			timeValue.setText("" + ((JSlider) ce.getSource()).getValue());
		});
	}
}
