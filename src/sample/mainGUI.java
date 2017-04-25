package sample;

import java.awt.Component;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

public class mainGUI extends JFrame implements ActionListener {

	private JSONObject PatientJSON = new JSONObject();
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JButton btnMedicines;
	private JButton btnUser;
	private JPanel pnlMedicine;
	private JButton btnPrescriptions;
	private JPanel pnlPrescriptions;

	//patient fields
	private JPanel pnlPatient;
	private JButton btnSearch;
	private JTable tblHistory;
	private JLabel lblSurname;
	private JLabel lblBloodStr;
	private JLabel lblBloodType;
	private JLabel lblPhoneStr;
	private JLabel lblPhone;
	private JLabel lblEmailStr;
	private JLabel lblEmail;
	private JLabel lblAddressStr;
	private JLabel lblSexStr;
	private JLabel lblSex;
	private JLabel lblAgeStr;
	private JLabel lblAge;
	private JLabel lblStreet;
	private JLabel lblFlat;
	private JLabel lblBuilding;
	private JLabel lblZip;
	private JLabel lblCity;
	private JSONObject jsonPatient = null;
	private String pesel = new String();
	private String firstname = new String();
	private String lastname = new String();
	private String phone = new String();
	private String email = new String();
	private String bloodtype = new String();
	private String city = new String();
	private String street = new String();
	private String buildingnumber = new String();
	private String flatnumber = new String();
	private String zipcode = new String();
	private String sex = new String();
	private int age = 0;

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
		btnMedicines.setBounds(10, 120, 283, 23);
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
		// PANEL USER (DODAWANIE PACJENTA I WSZYSTKIE AKCJE, KTORE NIE PASUJA DO INNYCH PANELI)
		
		btnUser = new JButton("User panel");
		btnUser.setBounds(10, 86, 283, 23);
		contentPane.add(btnUser);
		btnUser.addActionListener(this);
		
		// koniec przykladu
		// *********************************************
		
		
		
		
		
		// *********************************************
				// PANEL PATIENT
				
				btnSearch = new JButton("Search:");	
				btnSearch.setBounds(10, 0, 82, 30);
				contentPane.add(btnSearch);
				
				textField = new JTextField();
				textField.setFont(new Font("Tahoma", Font.PLAIN, 13));
				textField.setBounds(91, 0, 202, 30);
				contentPane.add(textField);
				textField.setColumns(10);
				
				
				
				pnlPatient = new JPanel();
				pnlPatient.setBounds(303, 54, 1071, 817);
				contentPane.add(pnlPatient);
				pnlPatient.setLayout(null);
				
				JLabel lblName = new JLabel("New label");
				lblName.setHorizontalAlignment(SwingConstants.RIGHT);
				lblName.setFont(new Font("Tahoma", Font.PLAIN, 28));
				lblName.setBounds(10, 12, 251, 83);
				pnlPatient.add(lblName);
				

				String[] columnNames = {"Recognition",
		                "Begin of hospitalization",
		                "End of hospitalization"};


				
				

				
				
				lblSurname = new JLabel("New label");
				lblSurname.setHorizontalAlignment(SwingConstants.LEFT);
				lblSurname.setFont(new Font("Tahoma", Font.BOLD, 28));
				lblSurname.setBounds(295, 12, 742, 83);
				pnlPatient.add(lblSurname);
				
				lblBloodStr = new JLabel("Blood Type:");
				lblBloodStr.setFont(new Font("Tahoma", Font.PLAIN, 15));
				lblBloodStr.setBounds(32, 106, 82, 24);
				pnlPatient.add(lblBloodStr);
				
				lblBloodType = new JLabel("xx");
				lblBloodType.setFont(new Font("Tahoma", Font.PLAIN, 15));
				lblBloodType.setBounds(135, 106, 82, 24);
				pnlPatient.add(lblBloodType);
				
				lblPhoneStr = new JLabel("Phone number:");
				lblPhoneStr.setFont(new Font("Tahoma", Font.PLAIN, 15));
				lblPhoneStr.setBounds(564, 106, 113, 24);
				pnlPatient.add(lblPhoneStr);
				
				lblPhone = new JLabel("xxxxxx");
				lblPhone.setFont(new Font("Tahoma", Font.PLAIN, 15));
				lblPhone.setBounds(687, 106, 113, 24);
				pnlPatient.add(lblPhone);
				
				lblEmailStr = new JLabel("Email:");
				lblEmailStr.setFont(new Font("Tahoma", Font.PLAIN, 15));
				lblEmailStr.setBounds(564, 141, 113, 24);
				pnlPatient.add(lblEmailStr);
				
				lblEmail = new JLabel("xxxxxxxxxxxxxxxxxxxxxxxxx");
				lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 15));
				lblEmail.setBounds(687, 141, 363, 24);
				pnlPatient.add(lblEmail);
				
				lblAddressStr = new JLabel("Address:");
				lblAddressStr.setFont(new Font("Tahoma", Font.PLAIN, 15));
				lblAddressStr.setBounds(564, 176, 113, 24);
				pnlPatient.add(lblAddressStr);
				
				lblSexStr = new JLabel("Sex:");
				lblSexStr.setFont(new Font("Tahoma", Font.PLAIN, 15));
				lblSexStr.setBounds(32, 157, 82, 24);
				pnlPatient.add(lblSexStr);
				
				lblSex = new JLabel("yes");
				lblSex.setFont(new Font("Tahoma", Font.PLAIN, 15));
				lblSex.setBounds(135, 157, 82, 24);
				pnlPatient.add(lblSex);
				
				lblAgeStr = new JLabel("Age:");
				lblAgeStr.setFont(new Font("Tahoma", Font.PLAIN, 15));
				lblAgeStr.setBounds(32, 207, 82, 24);
				pnlPatient.add(lblAgeStr);
				
				lblAge = new JLabel("xx");
				lblAge.setFont(new Font("Tahoma", Font.PLAIN, 15));
				lblAge.setBounds(135, 207, 82, 24);
				pnlPatient.add(lblAge);
				
				lblStreet = new JLabel("xxxxxxxxxxxxxxxxxxxxxxxxx");
				lblStreet.setFont(new Font("Tahoma", Font.PLAIN, 15));
				lblStreet.setBounds(687, 183, 261, 24);
				pnlPatient.add(lblStreet);
				
				lblFlat = new JLabel("xx");
				lblFlat.setFont(new Font("Tahoma", Font.PLAIN, 15));
				lblFlat.setBounds(958, 183, 49, 24);
				pnlPatient.add(lblFlat);
				
				lblBuilding = new JLabel("xx");
				lblBuilding.setFont(new Font("Tahoma", Font.PLAIN, 15));
				lblBuilding.setBounds(1017, 183, 49, 24);
				pnlPatient.add(lblBuilding);
				
				lblZip = new JLabel("xxxxxx");
				lblZip.setFont(new Font("Tahoma", Font.PLAIN, 15));
				lblZip.setBounds(687, 218, 82, 24);
				pnlPatient.add(lblZip);
				
				lblCity = new JLabel("xxxxxxxxxxxxxxxxxxxxxxxxx");
				lblCity.setFont(new Font("Tahoma", Font.PLAIN, 15));
				lblCity.setBounds(779, 218, 261, 24);
				pnlPatient.add(lblCity);
				
				JLabel lblHistoryStr = new JLabel("History of hospitalization:");
				lblHistoryStr.setFont(new Font("Tahoma", Font.PLAIN, 15));
				lblHistoryStr.setBounds(32, 310, 185, 24);
				pnlPatient.add(lblHistoryStr);
				
				
				
				btnSearch.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						for (Component c : contentPane.getComponents()) {
							if (c instanceof JPanel) {
								((JPanel) c).setVisible(false);
							}
						}
						
						pnlPatient.setVisible(true);
						/************************************/
				

				
			//PRZYKLADOWY JSON
				//TU ZOSTANIE WYWOLANE ZAPYTANIE RESTOWE
				try {
					jsonPatient = new JSONObject("{\"id\":2,\"sex\":\"female\",\"age\":50,\"bloodType\":\"Z\",\"pesel\":\"997\",\"firstName\":\"Adrona\",\"lastName\":\"Stsaa\",\"phone\":\"38483493\",\"email\":\"dsbfidfdis\",\"addressId\":5,\"city\":\"townsville\",\"street\":\"adsdsadasd\",\"buildingNumber\":\"53\",\"flatNumber\":\"xD\",\"zipCode\":\"53-632\"}");
					pesel = jsonPatient.getString("pesel");
					firstname = jsonPatient.getString("firstName");
					lastname = jsonPatient.getString("lastName");
					phone = jsonPatient.getString("phone");
					email = jsonPatient.getString("email");
					bloodtype = jsonPatient.getString("bloodType");
					city = jsonPatient.getString("city");
					street = jsonPatient.getString("street");
					buildingnumber = jsonPatient.getString("buildingNumber");
					flatnumber = jsonPatient.getString("flatNumber");
					zipcode = jsonPatient.getString("zipCode");
					age = jsonPatient.getInt("age");
				} catch (JSONException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					System.out.println("failxd");
				}

				lblName.setText(firstname);
				lblSurname.setText(lastname);
				lblPhone.setText(phone);
				lblEmail.setText(email);
				lblBloodType.setText(bloodtype);
				lblCity.setText(city);
				lblStreet.setText(street);
				lblFlat.setText(flatnumber);
				lblBuilding.setText(buildingnumber);
				lblZip.setText(zipcode);
				lblAge.setText(Integer.toString(age));
				lblSex.setText(sex);
				
				//tutaj bedzie rest do historii
				Object[][] data = {
					    {"kupa w gaciassssssssssssssssssssssssssssssssssssssssssssssssssch", "10.07.1410",
					     "10.04.2010"},
					    {"sidi", "10.07.1410",
					     "10.04.20110"},
					    {"cos inene", "10.07.1410",
					     "10.04.2010"}
					};
				
				//tabela jest dynamiczna, wiec musi byc tworzona w tym miejscu
				//moze w sumie nie musi, ale tak dziala
				//tak, wiem, ze uzywam deprecjonowanej metody
				tblHistory = new JTable(data, columnNames);;
				tblHistory.setBounds(0, 343, 1071, 463);
				tblHistory.enable(false);
			
				tblHistory.getColumn(columnNames[0]).setWidth(10);
				pnlPatient.add(tblHistory);
				
				JScrollPane scrollPane = new JScrollPane(tblHistory);
				scrollPane.setBounds(0, 343, 1071, 463);
				tblHistory.setFillsViewportHeight(true);
				pnlPatient.add(scrollPane);
				
				
			//2 clicks on row initializes action	
				tblHistory.addMouseListener(new MouseAdapter() {
				    public void mousePressed(MouseEvent me) {
				        JTable table =(JTable) me.getSource();
				        Point p = me.getPoint();
				        int row = table.rowAtPoint(p);
				        if (me.getClickCount() == 2) {
				            // your valueChanged overridden method 
				        	System.out.println("Row " + row +" selected");
				        }
				    }
				});
				
				}
			});
			

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
		} else if (e.getSource() == btnUser) {
			for (Component c : contentPane.getComponents()) {
				if (c instanceof JPanel) {
					((JPanel) c).setVisible(false);
				}
			}	
			
		//	pnlPatient.setVisible(true);
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