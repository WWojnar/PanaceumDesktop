package sample;

import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class mainGUI extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JButton btnMedicines;
	private JButton btnPatients;
	private JPanel pnlMedicine;
	private JPanel pnlPatients;
	private JButton btnPrescriptions;
	private JPanel pnlPrescriptions;
	

	/**
	 * Launch the application.
	 */

	/*
	 * public static void main(String[] args) { EventQueue.invokeLater(new
	 * Runnable() {
	 * 
	 * }); }
	 */

	public void run() {
		try {
			mainGUI frame = new mainGUI();
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the frame.
	 */
	public mainGUI() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1380, 900);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		this.setTitle("Panaceum");
		contentPane.setLayout(null);

		JLabel lblWelcome = new JLabel("Welcome " + Controller.name);
		lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcome.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblWelcome.setBounds(288, 0, 1076, 47);
		contentPane.add(lblWelcome);

		JLabel lblWyszukaj = new JLabel("Search:");
		lblWyszukaj.setBounds(10, 15, 63, 14);
		contentPane.add(lblWyszukaj);

		textField = new JTextField();
		textField.setBounds(69, 0, 224, 34);
		contentPane.add(textField);
		textField.setColumns(10);

		//
		//
		// BUTTON PART



		//
		//
		//

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 45, 1364, 2);
		contentPane.add(separator_1);

		JSeparator separator = new JSeparator();
		separator.setToolTipText("");
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(298, 0, 2, 871);
		contentPane.add(separator);

		// *********************************************
		// PANEL MEDICINES
		
		btnMedicines = new JButton("Medicine database");
		btnMedicines.setBounds(10, 87, 283, 23);
		contentPane.add(btnMedicines);
		btnMedicines.addActionListener(this);
		
		
		pnlMedicine = new JPanel();
		pnlMedicine.setBounds(303, 54, 1071, 817);
		contentPane.add(pnlMedicine);

		JLabel lblLabelOne = new JLabel("label one");
		pnlMedicine.add(lblLabelOne);
		// koniec przykladu
		// *********************************************

		// *********************************************
		// PANEL PATIENTS
		
		btnPatients = new JButton("Patients");
		btnPatients.setBounds(10, 120, 283, 23);
		contentPane.add(btnPatients);
		btnPatients.addActionListener(this);
		
		pnlPatients = new JPanel();
		pnlPatients.setBounds(303, 54, 1071, 817);
		contentPane.add(pnlPatients);

		JLabel lblLabelTwo = new JLabel("label two");
		pnlPatients.add(lblLabelTwo);
		

		// koniec przykladu
		// *********************************************
		
		
		// *********************************************
		// PANEL PRESCRIPTIONS
		
		btnPrescriptions = new JButton("Prescriptions");
		btnPrescriptions.setBounds(10, 154, 283, 23);
		contentPane.add(btnPrescriptions);
		btnPrescriptions.addActionListener(this);
		
		pnlPrescriptions = new JPanel();
		pnlPrescriptions.setBounds(303, 54, 1071, 817);
		contentPane.add(pnlPrescriptions);

		JLabel lblLabelThree = new JLabel("label three");
		pnlPrescriptions.add(lblLabelThree);
		

		// koniec przykladu
		// *********************************************
		
		

	}

	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == btnMedicines) {
			for (Component c : contentPane.getComponents()) {
				if (c instanceof JPanel) {
					((JPanel) c).setVisible(false);
				}
			}
			pnlMedicine.setVisible(true);
		} else if (e.getSource() == btnPatients) {
			for (Component c : contentPane.getComponents()) {
				if (c instanceof JPanel) {
					((JPanel) c).setVisible(false);
				}
			}
			
			// Json object = restpatients();
			
			
			pnlPatients.setVisible(true);
		}
			else if (e.getSource() == btnPrescriptions) {
				for (Component c : contentPane.getComponents()) {
					if (c instanceof JPanel) {
						((JPanel) c).setVisible(false);
					}
				}
				pnlPrescriptions.setVisible(true);
			}
		
	}
}