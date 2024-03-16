package tradepackhelper.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import tradepackhelper.Place;

public class DynamicGUIExample {

	private Place startingOption;
	private Place endingOption;
	private double minimumProfit = 0.0;

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new DynamicGUIExample().createAndShowGUI());
	}

	private void createAndShowGUI() {
		// Create and set up the JFrame
		JFrame frame = new JFrame("Dynamic GUI Example");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1400, 400);

		// Create a JPanel for the main content
		JPanel mainPanel = new JPanel(new BorderLayout());

		// Create a JPanel for dropdowns with a FlowLayout
		JPanel dropdownPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 10)); // Horizontal alignment and 30
																						// pixels gap
		dropdownPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0)); // 10 pixels top margin

		// Create dropdown menu for start
		JComboBox<Place> start = new JComboBox<>(Place.values());
		start.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				startingOption = (Place) start.getSelectedItem();
			}
		});

		// Create dropdown menu for end
		JComboBox<Place> end = new JComboBox<>(Place.values());
		end.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				endingOption = (Place) end.getSelectedItem();
			}
		});

		dropdownPanel.add(start);
		dropdownPanel.add(end);

		// Create a JPanel for the minimum profit section
		JPanel profitPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JLabel minProfitLabel = new JLabel("Minimum Profit:");
		JTextField minProfitField = new JTextField(10); // Text field for minimum profit
		minProfitField.setText(String.valueOf(minimumProfit)); // Set initial value
		profitPanel.add(minProfitLabel);
		profitPanel.add(minProfitField);

		// Create an update button
		JButton updateButton = new JButton("Update");
		updateButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Update minimum profit value from the text field
				try {
					minimumProfit = Double.parseDouble(minProfitField.getText());
					// Update GUI if necessary
					updateGUI(mainPanel);
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(frame, "Invalid minimum profit value", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		// Create an exit button
		JButton exitButton = new JButton("Exit");
		exitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		// Create a JPanel for the buttons at the bottom
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		buttonPanel.add(updateButton);
		buttonPanel.add(exitButton);

		// Add components to the main panel
		mainPanel.add(dropdownPanel, BorderLayout.NORTH);
		mainPanel.add(profitPanel, BorderLayout.CENTER);
		mainPanel.add(buttonPanel, BorderLayout.SOUTH);

		// Add the main panel to the frame and make it visible
		frame.getContentPane().add(mainPanel);
		frame.setVisible(true);
	}

	private void updateGUI(JPanel mainPanel) {

		// Refresh the frame to reflect the changes
		mainPanel.revalidate();
		mainPanel.repaint();
	}
}