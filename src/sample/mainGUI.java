package sample;

import java.awt.Color;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import Rest.RestController;
import javax.swing.ListSelectionModel;

public class mainGUI extends JFrame implements ActionListener {

	RestController restController;
	private JSONObject PatientJSON = new JSONObject();
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JButton btnMedicines;
	private JPanel pnlMedicine;

	// Medicine fields

	private JTextField txtSearchMedicine;
	private JTable medicineTable;
	private JButton btnSearchMedicine;

	// Single Medicine
	private JPanel pnlSingleMedicine;
	private JSONObject jsonMedicine = new JSONObject();
	private int medId = 0;
	private String medName = new String();
	private String medActiveSubstance = new String();
	int idMedicine = 1;

	private JLabel lblMedicineDetails;
	private JLabel lblName_2;
	private JTextPane txtpnSingleMedicineName;
	private JTextPane txtpnSingleMedicineActive;
	private JTextPane txtpnMedicineRelationsWith;
	private JButton btnCancelSingleMedicine;

	// AddMedicine
	private JPanel pnlAddMedicine;
	private JTextPane txtpnAddMedicineName;
	private JLabel lblActiveSubstances;
	private JTextPane txtpnAddMedicineActive;
	private JLabel lblRelations;
	private JTextPane txtpnAddRelationsWith;
	private JButton btnSubmitNewMedicine;
	private JButton btnCancelAddMedicine;
	private JLabel lblToAddNew;
	private JLabel lblName_1;

	// patient fields
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
	private JSONObject jsonPatient = new JSONObject();
	private String pesel = new String();
	private String firstname = new String();
	private String lastname = new String();
	private String phone = new String();
	private String email = new String();
	private String bloodtype = new String();
	private String city = new String();
	private String street = new String();
	private String buildingnumber = new String();
	private String flatnumber = "";
	private String zipcode = new String();
	private String sex = new String();
	private int age = 0;
	private boolean searchConfirmed = false;
	private Object[][] data = null; // do tabeli historii pacjenta
	private JSONArray jsonHistories = new JSONArray();

	// patient list
	private JSONArray jsonPatients = new JSONArray();
	private String[] PeselTable;
	private JSONArray jsonHistory = new JSONArray();

	// panel user
	private JButton btnUser;
	private JPanel pnlUser;
	private JLabel lblDoctorName;
	private JLabel lblDoctorLastName;
	private JLabel lblSpecialityStr;
	private JLabel lblSpeciality;
	private JLabel lblLicenceStr;
	private JLabel lblLicence;
	private JButton btnAddPatient;
	private JLabel lblDoctorEmailStr;
	private JLabel lblDoctorEmail;
	private JLabel lblDoctorPhoneStr;
	private JLabel lblDoctorPhone;
	private int[] doctors = null; // do tabeli historii pacjenta
	private JSONArray jsonDoctors = new JSONArray();
	private int ifdoctor = -1;
	private JSONObject doctor = new JSONObject();

	// single history
	private JLabel lblHistory;
	private JPanel pnlSingleHistory;

	// Prescription panel
	private JButton btnPrescriptions;
	private JButton btnPrescriptionBack;
	private JButton btnPrescriptionAdd;
	private JButton btnPrescriptionModify;
	private JButton btnPrescriptionPrint;
	private JButton btnPrescriptionAccept;
	private JPanel pnlPrescriptions;
	private JPanel pnlPrescriptionAdd;
	private JPanel pnlPrescriptionDetails;
	private JTable tblPrescription;
	private JScrollPane scrollPane;
	private DefaultTableModel dmPrescription;
	private JSONArray prescriptionJson = new JSONArray();
	private JSONObject prescriptionJsonDet = new JSONObject();
	private JSONObject prescriptionJsonDoctor = new JSONObject();
	private JTextField searchPrescriptionField;
	private JTextField txtfdPrescriptionName;
	private JTextField txtfdPrescriptionSurname;
	private JTextField txtfdPrescriptionPesel;
	private JTextField txtfdPrescriptionDate;
	private JTextField txtfdPrescriptionExpiry;
	private JTextField txtfdPrescriptionMedicine;
	private JTextField txtfdPrescriptionDosage;
	private JTextField txtfdPrescriptionIName;
	private JTextField txtfdPrescriptionILicence;
	private JLabel lblPrescriptionName;
	private JLabel lblPrescriptionSurname;
	private JLabel lblPrescriptionPesel;
	private JLabel lblPrescriptionIName;
	private JLabel lblPrescriptionDosage;
	private JLabel lblPrescriptionMedicine;
	private JLabel lblPrescriptionExpiry;
	private JLabel lblPrescriptionDate;
	private JLabel lblPrescriptionDetails;
	private JLabel lblPrescriptionILicence;

	/**
	 * Launch the application.
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
	 * 
	 * @throws JSONException
	 */
	public mainGUI() throws JSONException {

		
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (Exception e) {
		    // If Nimbus is not available, you can set the GUI to another look and feel.
		}
		
		
		restController = new RestController();

		// lista pacjentow musi zostac utworzona tutaj, bo ich pesele moga
		// zostac wyszukane z kazdego miejsca
		// rest Get list of patients - zwraca JSONArray
		try {
			jsonPatients = new JSONArray(restController.patientsList(Controller.name, Controller.token));

			System.out.println(jsonPatients);

		} catch (JSONException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		PeselTable = new String[jsonPatients.length()];

		for (int i = 0; i < jsonPatients.length(); i++) {
			JSONObject t;
			try {
				t = (JSONObject) jsonPatients.get(i);
				PeselTable[i] = t.getString("pesel");
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		setResizable(true);
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

		String[] medicineColumnNames = { "Id:", "Name:" };

		// tutaj bedzie rest do lek�w
		// Object[][] medicineData = { { "001", "Apap" }, { "002", "Ibuprom" }
		// };
		Object[][] medicineData;
		JSONArray jsonMedicineList = null;
		// JSON do tablicy

		try {
			jsonMedicineList = new JSONArray(restController.getMedicineList(Controller.name, Controller.token));
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			System.out.println("Fail Json Medicine list");
		}
		medicineData = new Object[jsonMedicineList.length()][2];

		for (int i = 0; i < jsonMedicineList.length(); i++) {
			JSONObject t;
			try {
				t = (JSONObject) jsonMedicineList.get(i);
				medicineData[i][0] = t.getString("id");
				medicineData[i][1] = t.getString("name");

			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				System.out.println("Fail Json Medicine list");
				e1.printStackTrace();
			}
		}

		JButton btnAddNewMedicine = new JButton("Add new");
		btnAddNewMedicine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (Component c : contentPane.getComponents()) {
					if (c instanceof JPanel) {
						((JPanel) c).setVisible(false);
					}
				}
				pnlAddMedicine.setVisible(true);

			}
		});
		pnlMedicine.setLayout(null);
		btnAddNewMedicine.setBounds(933, 8, 128, 23);
		pnlMedicine.add(btnAddNewMedicine);

		// Pole tekstowe do wyszukiwania
		txtSearchMedicine = new JTextField();
		txtSearchMedicine.setText("Search..");
		txtSearchMedicine.setBounds(10, 9, 86, 20);
		pnlMedicine.add(txtSearchMedicine);
		txtSearchMedicine.setColumns(10);

		// przycisk do wyszukiwania
		btnSearchMedicine = new JButton("Search");
		btnSearchMedicine.setBounds(120, 8, 107, 23);
		pnlMedicine.add(btnSearchMedicine);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 42, 1051, 764);
		pnlMedicine.add(scrollPane_1);

		medicineTable = new JTable(medicineData, medicineColumnNames);
		medicineTable.enable(false);
		medicineTable.getColumn(medicineColumnNames[0]).setWidth(10);
		scrollPane_1.setViewportView(medicineTable);

		// 2 clicks on row initializes action
		medicineTable.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent me) {
				JTable table = (JTable) me.getSource();
				Point p = me.getPoint();
				int row = table.rowAtPoint(p);
				String idMed;
				if (me.getClickCount() == 2) {

					// Here find by id and go to single medicine panel
					idMedicine = Integer.valueOf((String) table.getValueAt(row, 0));
					System.out.println("Row " + row + " selected" + "id: " + idMedicine);
					// JSON here
					try {
						jsonMedicine = new JSONObject(
								restController.getMedicineById(idMedicine, Controller.name, Controller.token));
						medId = jsonMedicine.getInt("id");
						medName = jsonMedicine.getString("name");
						medActiveSubstance = jsonMedicine.getString("activeSubstance");
					} catch (JSONException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						System.out.println("failxd");
					}
					txtpnSingleMedicineName.setText(medName);
					txtpnSingleMedicineActive.setText(medActiveSubstance);
					lblMedicineDetails.setText("Mecicine Id: " + medId);

					for (Component c : contentPane.getComponents()) {
						if (c instanceof JPanel) {
							((JPanel) c).setVisible(false);
						}
					}
					pnlSingleMedicine.setVisible(true);

				}
			}
		});

		// koniec MEDICINES
		// *********************************************

		// *********************************************
		// PANEL SINGLE MEDICINES

		///

		pnlSingleMedicine = new JPanel();
		pnlSingleMedicine.setBounds(303, 54, 1071, 817);
		contentPane.add(pnlSingleMedicine);
		pnlSingleMedicine.setLayout(null);

		lblMedicineDetails = new JLabel("Medicine Id: " + medId);
		lblMedicineDetails.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMedicineDetails.setBounds(10, 11, 603, 37);
		pnlSingleMedicine.add(lblMedicineDetails);

		lblName_2 = new JLabel("Name: ");
		lblName_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblName_2.setBounds(10, 73, 55, 37);
		pnlSingleMedicine.add(lblName_2);

		txtpnSingleMedicineName = new JTextPane();
		txtpnSingleMedicineName.setText("No information");
		txtpnSingleMedicineName.setBounds(10, 113, 354, 37);
		pnlSingleMedicine.add(txtpnSingleMedicineName);

		// 2 times
		lblActiveSubstances = new JLabel("Active Substances:");
		lblActiveSubstances.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblActiveSubstances.setBounds(10, 179, 130, 37);
		pnlSingleMedicine.add(lblActiveSubstances);

		txtpnSingleMedicineActive = new JTextPane();
		txtpnSingleMedicineActive.setText("No information");
		txtpnSingleMedicineActive.setBounds(10, 232, 354, 460);
		pnlSingleMedicine.add(txtpnSingleMedicineActive);

		// 2 times
		lblRelations = new JLabel("Relations");
		lblRelations.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblRelations.setBounds(572, 73, 157, 37);
		pnlSingleMedicine.add(lblRelations);

		txtpnMedicineRelationsWith = new JTextPane();
		txtpnMedicineRelationsWith.setText("Do not mix with alcohol...");
		txtpnMedicineRelationsWith.setBounds(572, 113, 430, 579);
		pnlSingleMedicine.add(txtpnMedicineRelationsWith);

		// Go back to Medicine view
		btnCancelSingleMedicine = new JButton("Back");
		btnCancelSingleMedicine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (Component c : contentPane.getComponents()) {
					if (c instanceof JPanel) {
						((JPanel) c).setVisible(false);
					}
				}
				pnlMedicine.setVisible(true);
			}
		});
		btnCancelSingleMedicine.setBounds(10, 743, 141, 44);
		pnlSingleMedicine.add(btnCancelSingleMedicine);

		// koniec SINGLE MEDICINES
		// *********************************************

		// *********************************************
		// PANEL ADD MEDICINES

		pnlAddMedicine = new JPanel();
		pnlAddMedicine.setBounds(303, 54, 1071, 817);
		contentPane.add(pnlAddMedicine);
		pnlAddMedicine.setLayout(null);

		lblToAddNew = new JLabel("To add new medicine, fill all the fields and press \"Add\" button.");
		lblToAddNew.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblToAddNew.setBounds(10, 11, 603, 37);
		pnlAddMedicine.add(lblToAddNew);

		lblName_1 = new JLabel("Name: ");
		lblName_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblName_1.setBounds(10, 73, 55, 37);
		pnlAddMedicine.add(lblName_1);

		txtpnAddMedicineName = new JTextPane();
		txtpnAddMedicineName.setText("Type medicine name...");
		txtpnAddMedicineName.setBounds(10, 113, 354, 37);
		pnlAddMedicine.add(txtpnAddMedicineName);

		lblActiveSubstances = new JLabel("Active Substances:");
		lblActiveSubstances.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblActiveSubstances.setBounds(10, 179, 130, 37);
		pnlAddMedicine.add(lblActiveSubstances);

		txtpnAddMedicineActive = new JTextPane();
		txtpnAddMedicineActive.setText("Type medicine active substances...");
		txtpnAddMedicineActive.setBounds(10, 232, 354, 460);
		pnlAddMedicine.add(txtpnAddMedicineActive);

		lblRelations = new JLabel("Relations");
		lblRelations.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblRelations.setBounds(572, 73, 157, 37);
		pnlAddMedicine.add(lblRelations);

		txtpnAddRelationsWith = new JTextPane();
		txtpnAddRelationsWith.setText("Type relations with other medicines...");
		txtpnAddRelationsWith.setBounds(572, 113, 430, 579);
		pnlAddMedicine.add(txtpnAddRelationsWith);

		// Adding new medicine
		btnSubmitNewMedicine = new JButton("Add");
		btnSubmitNewMedicine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Add function here...(txtpnAddMedicineName.getText(),
				// txtpnAddMedicineActive.getText());
				JOptionPane.showMessageDialog(null, "New medicine has been added");
				for (Component c : contentPane.getComponents()) {
					if (c instanceof JPanel) {
						((JPanel) c).setVisible(false);
					}
				}
				pnlMedicine.setVisible(true);
			}
		});
		btnSubmitNewMedicine.setBounds(861, 732, 141, 44);
		pnlAddMedicine.add(btnSubmitNewMedicine);

		// Go back to Medicine view
		btnCancelAddMedicine = new JButton("Cancel");
		btnCancelAddMedicine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (Component c : contentPane.getComponents()) {
					if (c instanceof JPanel) {
						((JPanel) c).setVisible(false);
					}
				}
				pnlMedicine.setVisible(true);
			}
		});
		btnCancelAddMedicine.setBounds(10, 743, 141, 44);
		pnlAddMedicine.add(btnCancelAddMedicine);

		// koniec przykladu ADD medicine
		// ********************* ************************

		// *********************************************
		// PANEL USER (DODAWANIE PACJENTA I WSZYSTKIE AKCJE, KTORE NIE PASUJA DO
		// INNYCH PANELI)

		btnUser = new JButton("User panel");
		btnUser.setBounds(10, 86, 283, 23);
		contentPane.add(btnUser);
		btnUser.addActionListener(this);

		pnlUser = new JPanel();
		pnlUser.setLayout(null);
		pnlUser.setBounds(303, 54, 1071, 817);
		contentPane.add(pnlUser);

		lblDoctorName = new JLabel("xxxxxxxxxxxxxxxxxxxxx");
		lblDoctorName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDoctorName.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblDoctorName.setBounds(71, 41, 237, 66);
		pnlUser.add(lblDoctorName);

		lblDoctorLastName = new JLabel("xxxxxxxxxxxxxxxx");
		lblDoctorLastName.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblDoctorLastName.setBounds(375, 41, 385, 66);
		pnlUser.add(lblDoctorLastName);

		lblSpecialityStr = new JLabel("Speciality:");
		lblSpecialityStr.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblSpecialityStr.setBounds(71, 148, 89, 32);
		pnlUser.add(lblSpecialityStr);

		lblSpeciality = new JLabel("xxxxxxxxxxxxxxxxxxxxx");
		lblSpeciality.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblSpeciality.setBounds(193, 148, 303, 32);
		pnlUser.add(lblSpeciality);

		lblLicenceStr = new JLabel("Licence number:");
		lblLicenceStr.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblLicenceStr.setBounds(71, 208, 112, 32);
		pnlUser.add(lblLicenceStr);

		lblLicence = new JLabel("xxxxxxxxxxxxxxxxxxxxx");
		lblLicence.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblLicence.setBounds(193, 208, 205, 32);
		pnlUser.add(lblLicence);

		btnAddPatient = new JButton("Add Patient");
		btnAddPatient.setBounds(789, 155, 153, 32);
		pnlUser.add(btnAddPatient);

		lblDoctorEmailStr = new JLabel("Email:");
		lblDoctorEmailStr.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDoctorEmailStr.setBounds(71, 308, 112, 32);
		pnlUser.add(lblDoctorEmailStr);

		lblDoctorEmail = new JLabel("xxxxxxxxxxxxxxxxxxxxx");
		lblDoctorEmail.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDoctorEmail.setBounds(193, 308, 205, 32);
		pnlUser.add(lblDoctorEmail);

		lblDoctorPhoneStr = new JLabel("Phone:");
		lblDoctorPhoneStr.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDoctorPhoneStr.setBounds(71, 369, 112, 32);
		pnlUser.add(lblDoctorPhoneStr);

		lblDoctorPhone = new JLabel("xxxxxxxxxxxxxxxxxxxxx");
		lblDoctorPhone.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDoctorPhone.setBounds(193, 369, 205, 32);
		pnlUser.add(lblDoctorPhone);

		// JSON doctors list
		try {
			jsonDoctors = new JSONArray(restController.doctorsList(Controller.name, Controller.token));
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			System.out.println("failxd");
		}

		if (Controller.doctorId != 0) {

			try {
				doctor = new JSONObject(restController.doctor(Controller.doctorId, Controller.name, Controller.token));

				System.out.println(doctor);

			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			lblDoctorName.setText(doctor.getString("firstName"));
			lblDoctorLastName.setText(doctor.getString("lastName"));
			lblSpeciality.setText(doctor.getString("speciality"));
			lblLicence.setText(doctor.getString("licenceNumber"));
			lblDoctorEmail.setText(doctor.getString("email"));
			lblDoctorPhone.setText(doctor.getString("phone"));
		} else {
			lblDoctorName.setText("You are not");
			lblDoctorLastName.setText("a doctor.");
			lblSpeciality.setText("");
			lblLicence.setText("");
			lblDoctorEmail.setText("");
			lblDoctorPhone.setText("");
		}

		// koniec USER
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

		String[] columnNames = { "Recognition", "Begin of hospitalization", "End of hospitalization" };

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

				searchConfirmed = false;
				// sprawdzanie, czy podany pesel jest zgodny z ktoryms w tabeli
				for (String i : PeselTable) {

					if (textField.getText().equals(i)) {
						searchConfirmed = true;

						for (Component c : contentPane.getComponents()) {
							if (c instanceof JPanel) {
								((JPanel) c).setVisible(false);
							}
						}

						pnlPatient.setVisible(true);
						/************************************/

						// TU ZOSTANIE WYWOLANE ZAPYTANIE RESTOWE
						// Get information about specific patient using pesel
						try {
							jsonPatient = new JSONObject(
									restController.patient(textField.getText(), Controller.name, Controller.token));
							pesel = jsonPatient.getString("pesel");
							firstname = jsonPatient.getString("firstName");
							lastname = jsonPatient.getString("lastName");
							phone = jsonPatient.getString("phone");
							email = jsonPatient.getString("email");
							bloodtype = jsonPatient.getString("bloodType");
							city = jsonPatient.getString("city");
							street = jsonPatient.getString("street");
							buildingnumber = jsonPatient.getString("buildingNumber");
							if (jsonPatient.has(flatnumber))
								flatnumber = jsonPatient.getString("flatnumber");
							zipcode = jsonPatient.getString("zipCode");
							age = jsonPatient.getInt("age");
							sex = jsonPatient.getString("sex");
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

						// tutaj bedzie rest do historii
						// getHistory/{pesel}/{login}/{token}

						try {
							jsonHistories = new JSONArray(
									restController.getHistory(pesel, Controller.name, Controller.token));

							System.out.println(jsonHistory);

						} catch (JSONException e1) {
							// TODO Auto-generated catch block
							System.out.println("failxd");
						}

						data = new Object[jsonHistories.length()][3];

						for (int j = 0; j < jsonHistories.length(); j++) {
							JSONObject t;
							try {
								t = (JSONObject) jsonHistories.get(j);
								data[j][0] = t.getString("excerptRecognition");
								data[j][1] = t.getString("interviewDate");
								data[j][2] = t.getString("excerptDate");
							} catch (JSONException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}

						// tabela jest dynamiczna, wiec musi byc tworzona w tym
						// miejscu
						// moze w sumie nie musi, ale tak dziala
						// tak, wiem, ze uzywam deprecjonowanej metody
						tblHistory = new JTable(data, columnNames);

						tblHistory.setBounds(0, 343, 1071, 463);
						tblHistory.enable(false);

						tblHistory.getColumn(columnNames[0]).setWidth(10);
						pnlPatient.add(tblHistory);

						JScrollPane scrollPane = new JScrollPane(tblHistory);
						scrollPane.setBounds(0, 343, 1071, 463);
						tblHistory.setFillsViewportHeight(true);
						pnlPatient.add(scrollPane);

						// 2 clicks on row initializes action
						tblHistory.addMouseListener(new MouseAdapter() {
							public void mousePressed(MouseEvent me) {
								JTable table = (JTable) me.getSource();
								Point p = me.getPoint();
								int row = table.rowAtPoint(p);
								if (me.getClickCount() == 2) {
									// your valueChanged overridden method
									System.out.println("Row " + row + " selected");

									for (Component c : contentPane.getComponents()) {
										if (c instanceof JPanel) {
											((JPanel) c).setVisible(false);
										}
									}
									pnlSingleHistory.setVisible(true);

									lblHistory.setText((String) data[row][0]);

								}
							}
						});

					}
				} // sprawdzenie tabeli peseli
					// jesli pesel nie zawiera sie w tabeli peseli
				if (!searchConfirmed)
					JOptionPane.showMessageDialog(null, "Given PESEL is incorrect");

			}// klikniecie buttnou search
		});

		// koniec PATIENTS
		// *********************************************

		// *********************************************
		// PANEL SINGLE HISTORY

		///

		pnlSingleHistory = new JPanel();
		pnlSingleHistory.setBounds(303, 54, 1071, 817);
		contentPane.add(pnlSingleHistory);
		pnlSingleHistory.setLayout(null);

		// WYBRANA HISTORIA HERE

		lblHistory = new JLabel("xxxxxxxxxxxxxxx");
		lblHistory.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblHistory.setBounds(10, 11, 962, 37);
		pnlSingleHistory.add(lblHistory);

		// Go back to history view

		JButton btnCancelHistory = new JButton();

		btnCancelHistory = new JButton("Back");
		btnCancelHistory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (Component c : contentPane.getComponents()) {
					if (c instanceof JPanel) {
						((JPanel) c).setVisible(false);
					}
				}
				pnlPatient.setVisible(true);
			}
		});
		btnCancelHistory.setBounds(10, 743, 141, 44);
		pnlSingleHistory.add(btnCancelHistory);

		JButton btnInfection = new JButton("Infection card");
		btnInfection.setBounds(10, 231, 191, 44);
		pnlSingleHistory.add(btnInfection);

		JButton btnFirstExamination = new JButton("First Examination");
		btnFirstExamination.setBounds(10, 156, 191, 44);
		pnlSingleHistory.add(btnFirstExamination);

		JButton btnInterview = new JButton("Interview");
		btnInterview.setBounds(10, 81, 191, 44);
		pnlSingleHistory.add(btnInterview);

		JButton btnExcerpt = new JButton("Excerpt");
		btnExcerpt.setBounds(10, 307, 191, 44);
		pnlSingleHistory.add(btnExcerpt);

		// koniec SINGLE HISTORY
		// *********************************************

		// *********************************************
		// PANEL PRESCRIPTIONS

		fillPrescriptionJPanel();

		// SECTION OF ADDING PRESCRIPTION

		fillPrescriptionAddJPanel();

		// SECTION OF MODIFYING PRESCRIPTION

		fillPrescriptionDetailsJPanel();

		// END OF PRESCRIPTIONS
		// *********************************************

		// panel user shows after logging in
		for (Component c : contentPane.getComponents()) {
			if (c instanceof JPanel) {
				((JPanel) c).setVisible(false);
			}
		}

		pnlUser.setVisible(true);

	}

	private void fillPrescriptionJPanel() {

		btnPrescriptions = new JButton("Prescriptions");
		btnPrescriptions.setBounds(10, 154, 283, 23);
		contentPane.add(btnPrescriptions);
		btnPrescriptions.addActionListener(this);

		pnlPrescriptions = new JPanel();
		pnlPrescriptions.setBounds(303, 54, 1071, 817);
		contentPane.add(pnlPrescriptions);
		pnlPrescriptions.setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 42, 1051, 764);
		pnlPrescriptions.add(scrollPane);

		tblPrescription = new JTable() {
			private static final long serialVersionUID = 4466748965117973844L;

			public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
				Component c = super.prepareRenderer(renderer, row, column);

				this.setRowSelectionAllowed(true);

				return c;
			}
		};
		tblPrescription.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		;
		scrollPane.setViewportView(tblPrescription);
		tblPrescription.setFillsViewportHeight(true);
		tblPrescription.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));

		createPrescriptionColumns();

		tblPrescription.addMouseListener(new MouseAdapter() {
			private Object prescriptionId;

			public void mousePressed(MouseEvent me) {
				JTable table = (JTable) me.getSource();
				Point p = me.getPoint();
				int row = table.rowAtPoint(p);

				if (me.getClickCount() == 2) {

					// Here find by id and go to single medicine panel
					prescriptionId = tblPrescription.getModel().getValueAt(row, 0);
					// JSON here
					try {
						refreshPrescriptionData(prescriptionId.toString());
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					for (Component c : contentPane.getComponents()) {
						if (c instanceof JPanel) {
							((JPanel) c).setVisible(false);
						}
					}
					pnlPrescriptionDetails.setVisible(true);

				}
			}
		});

		btnPrescriptionModify = new JButton("Details");
		btnPrescriptionModify.addActionListener(new ActionListener() {
			private Object prescriptionId;

			public void actionPerformed(ActionEvent arg0) {

				prescriptionId = tblPrescription.getModel().getValueAt(tblPrescription.getSelectedRow(), 0);
				try {
					refreshPrescriptionData(prescriptionId.toString());
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				for (Component c : contentPane.getComponents()) {
					if (c instanceof JPanel) {
						((JPanel) c).setVisible(false);
					}
				}
				pnlPrescriptionDetails.setVisible(true);

			}
		});
		btnPrescriptionModify.setBounds(155, 8, 128, 23);
		pnlPrescriptions.add(btnPrescriptionModify);

		btnPrescriptionAdd = new JButton("Add");
		btnPrescriptionAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				for (Component c : contentPane.getComponents()) {
					if (c instanceof JPanel) {
						((JPanel) c).setVisible(false);
					}
				}
				try {
					clearPrescriptionData();
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				pnlPrescriptionAdd.setVisible(true);

			}
		});
		btnPrescriptionAdd.setBounds(933, 8, 128, 23);
		pnlPrescriptions.add(btnPrescriptionAdd);

		searchPrescriptionField = new JTextField();
		searchPrescriptionField.setBounds(10, 8, 122, 23);
		pnlPrescriptions.add(searchPrescriptionField);
		searchPrescriptionField.setColumns(3);

	}

	private void refreshPrescriptionData(String excerptId) throws JSONException {

		for (int i = 0; i < prescriptionJson.length(); i++) {
			prescriptionJsonDet = prescriptionJson.getJSONObject(i);
			System.out.println(prescriptionJsonDet.getString("excerptId"));
			if (prescriptionJsonDet.getString("excerptId").toString().equals(excerptId)) {
				prescriptionJsonDoctor = restController.doctorJson(
						Integer.parseInt(prescriptionJsonDet.getString("doctorid")), Controller.name, Controller.token);
				txtfdPrescriptionName.setText(prescriptionJsonDet.getString("patientFirstName"));
				txtfdPrescriptionSurname.setText(prescriptionJsonDet.getString("patientLastName"));
				txtfdPrescriptionPesel.setText(prescriptionJsonDet.getString("patientPesel"));
				txtfdPrescriptionMedicine.setText(prescriptionJsonDet.getString("medicineName"));
				txtfdPrescriptionDosage.setText(prescriptionJsonDet.getString("dosage"));
				txtfdPrescriptionDate.setText(prescriptionJsonDet.getString("prescriptionDate"));
				txtfdPrescriptionExpiry.setText(prescriptionJsonDet.getString("expiryDate"));
				txtfdPrescriptionIName.setText(prescriptionJsonDoctor.getString("firstName") + " "
						+ prescriptionJsonDoctor.getString("lastName"));
				txtfdPrescriptionILicence.setText(prescriptionJsonDoctor.getString("licenceNumber"));

				disablePrescriptionDetails();
				break;
			}
		}

	}

	private void clearPrescriptionData() throws JSONException {

		txtfdPrescriptionName.setText("");
		txtfdPrescriptionSurname.setText("");
		txtfdPrescriptionPesel.setText("");
		txtfdPrescriptionMedicine.setText("");
		txtfdPrescriptionDosage.setText("");
		txtfdPrescriptionDate.setText("");
		txtfdPrescriptionExpiry.setText("");
		txtfdPrescriptionIName.setText("");
		txtfdPrescriptionILicence.setText("");

	}

	private void createPrescriptionColumns() {
		dmPrescription = (DefaultTableModel) tblPrescription.getModel();
		dmPrescription.addColumn("excerptId");
		dmPrescription.addColumn("Surname");
		dmPrescription.addColumn("Name");
		dmPrescription.addColumn("Issue date");

	}

	private void populatePrescriptionTable() throws JSONException {

		/*
		 * [{"id":1,"dosage":"3 na h","prescriptionDate":"2017-04-26",
		 * "expiryDate":"2017-05-26","medicineId":1,"medicineName":"Medicine1",
		 * "activeSubstance":"ActiveSubstance1","therapyPlanId":1,"excerptId":4,
		 * "doctorid":2,"patientId":1,"patientPesel":"55555555555",
		 * "patientFirstName":"Pacjent","patientLastName":"NumerJeden"}]
		 */

		prescriptionJson = new JSONArray(
				restController.getPrescription(Controller.doctorId, Controller.name, Controller.token));
		for (int i = 0; i < prescriptionJson.length(); i++) {
			dmPrescription.addRow(new Object[] { prescriptionJson.getJSONObject(i).getInt("excerptId"),
					prescriptionJson.getJSONObject(i).getString("patientFirstName"),
					prescriptionJson.getJSONObject(i).getString("patientLastName"),
					prescriptionJson.getJSONObject(i).getString("prescriptionDate") });
		}
		dmPrescription.fireTableDataChanged();

	}

	private void fillPrescriptionDetailsJPanel() {

		pnlPrescriptionDetails = new JPanel();
		pnlPrescriptionDetails.setBounds(303, 54, 1071, 817);
		contentPane.add(pnlPrescriptionDetails);
		pnlPrescriptionDetails.setLayout(null);

		btnPrescriptionBack = new JButton("Back");
		btnPrescriptionBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (Component c : contentPane.getComponents()) {
					if (c instanceof JPanel) {
						((JPanel) c).setVisible(false);
					}
				}
				pnlPrescriptions.setVisible(true);
				btnPrescriptionPrint.setVisible(true);
			}
		});
		btnPrescriptionBack.setBounds(10, 743, 141, 44);
		pnlPrescriptionDetails.add(btnPrescriptionBack);

		
		
		btnPrescriptionAccept = new JButton("Accept");
		btnPrescriptionAccept.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				//JSONquery to modify
				disablePrescriptionDetails();
				btnPrescriptionAccept.setVisible(false);
				btnPrescriptionPrint.setVisible(true);
				btnPrescriptionModify.setVisible(true);
			}
		});
		btnPrescriptionAccept.setBounds(200, 743, 141, 44);
		pnlPrescriptionDetails.add(btnPrescriptionAccept);
		btnPrescriptionAccept.setVisible(false);
		
		
		
		
		btnPrescriptionModify = new JButton("Modify");
		btnPrescriptionModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				enablePrescriptionDetails();
				btnPrescriptionAccept.setVisible(true);
				btnPrescriptionModify.setVisible(false);
				btnPrescriptionPrint.setVisible(false);
			}
		});
		btnPrescriptionModify.setBounds(200, 743, 141, 44);
		pnlPrescriptionDetails.add(btnPrescriptionModify);

		btnPrescriptionPrint = new JButton("Print");
		btnPrescriptionPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// Modify the Prescription JSON
			}
		});
		btnPrescriptionPrint.setBounds(390, 743, 141, 44);
		pnlPrescriptionDetails.add(btnPrescriptionPrint);

		txtfdPrescriptionName = new JTextField();
		txtfdPrescriptionName.setDisabledTextColor(Color.BLACK);
		txtfdPrescriptionName.setBounds(211, 172, 264, 35);
		pnlPrescriptionDetails.add(txtfdPrescriptionName);
		txtfdPrescriptionName.setColumns(10);

		txtfdPrescriptionSurname = new JTextField();
		txtfdPrescriptionSurname.setDisabledTextColor(Color.BLACK);
		txtfdPrescriptionSurname.setBounds(211, 237, 264, 35);
		pnlPrescriptionDetails.add(txtfdPrescriptionSurname);
		txtfdPrescriptionSurname.setColumns(10);

		txtfdPrescriptionPesel = new JTextField();
		txtfdPrescriptionPesel.setDisabledTextColor(Color.BLACK);
		txtfdPrescriptionPesel.setBounds(211, 298, 264, 35);
		pnlPrescriptionDetails.add(txtfdPrescriptionPesel);
		txtfdPrescriptionPesel.setColumns(10);

		lblPrescriptionName = new JLabel("Name");
		lblPrescriptionName.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrescriptionName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPrescriptionName.setBounds(86, 170, 115, 35);
		pnlPrescriptionDetails.add(lblPrescriptionName);

		lblPrescriptionSurname = new JLabel("Surname");
		lblPrescriptionSurname.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrescriptionSurname.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPrescriptionSurname.setBounds(86, 237, 115, 35);
		pnlPrescriptionDetails.add(lblPrescriptionSurname);

		lblPrescriptionPesel = new JLabel("Pesel");
		lblPrescriptionPesel.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrescriptionPesel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPrescriptionPesel.setBounds(86, 298, 115, 35);
		pnlPrescriptionDetails.add(lblPrescriptionPesel);

		lblPrescriptionIName = new JLabel("Issuer name");
		lblPrescriptionIName.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrescriptionIName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPrescriptionIName.setBounds(539, 170, 115, 35);
		pnlPrescriptionDetails.add(lblPrescriptionIName);

		lblPrescriptionDosage = new JLabel("Dosage");
		lblPrescriptionDosage.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrescriptionDosage.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPrescriptionDosage.setBounds(86, 420, 115, 35);
		pnlPrescriptionDetails.add(lblPrescriptionDosage);

		lblPrescriptionMedicine = new JLabel("Medicine");
		lblPrescriptionMedicine.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrescriptionMedicine.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPrescriptionMedicine.setBounds(86, 353, 115, 35);
		pnlPrescriptionDetails.add(lblPrescriptionMedicine);

		txtfdPrescriptionMedicine = new JTextField();
		txtfdPrescriptionMedicine.setDisabledTextColor(Color.BLACK);
		txtfdPrescriptionMedicine.setColumns(10);
		txtfdPrescriptionMedicine.setBounds(211, 355, 264, 35);
		pnlPrescriptionDetails.add(txtfdPrescriptionMedicine);

		txtfdPrescriptionDosage = new JTextField();
		txtfdPrescriptionDosage.setDisabledTextColor(Color.BLACK);
		txtfdPrescriptionDosage.setColumns(10);
		txtfdPrescriptionDosage.setBounds(211, 420, 264, 35);
		pnlPrescriptionDetails.add(txtfdPrescriptionDosage);

		txtfdPrescriptionIName = new JTextField();
		txtfdPrescriptionIName.setDisabledTextColor(Color.BLACK);
		txtfdPrescriptionIName.setColumns(10);
		txtfdPrescriptionIName.setBounds(664, 172, 264, 35);
		pnlPrescriptionDetails.add(txtfdPrescriptionIName);

		lblPrescriptionExpiry = new JLabel("Expiry date");
		lblPrescriptionExpiry.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrescriptionExpiry.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPrescriptionExpiry.setBounds(568, 360, 115, 35);
		pnlPrescriptionDetails.add(lblPrescriptionExpiry);

		lblPrescriptionDate = new JLabel("Date of issue");
		lblPrescriptionDate.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrescriptionDate.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPrescriptionDate.setBounds(568, 314, 115, 35);
		pnlPrescriptionDetails.add(lblPrescriptionDate);

		txtfdPrescriptionDate = new JTextField();
		txtfdPrescriptionDate.setDisabledTextColor(Color.BLACK);
		txtfdPrescriptionDate.setColumns(10);
		txtfdPrescriptionDate.setBounds(693, 315, 140, 37);
		pnlPrescriptionDetails.add(txtfdPrescriptionDate);

		txtfdPrescriptionExpiry = new JTextField();
		txtfdPrescriptionExpiry.setDisabledTextColor(Color.BLACK);
		txtfdPrescriptionExpiry.setColumns(10);
		txtfdPrescriptionExpiry.setBounds(693, 362, 140, 35);
		pnlPrescriptionDetails.add(txtfdPrescriptionExpiry);

		lblPrescriptionDetails = new JLabel("Prescription Details");
		lblPrescriptionDetails.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrescriptionDetails.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPrescriptionDetails.setBounds(290, 29, 468, 35);
		pnlPrescriptionDetails.add(lblPrescriptionDetails);

		txtfdPrescriptionILicence = new JTextField();
		txtfdPrescriptionILicence.setDisabledTextColor(Color.BLACK);
		txtfdPrescriptionILicence.setColumns(10);
		txtfdPrescriptionILicence.setBounds(664, 237, 264, 35);
		pnlPrescriptionDetails.add(txtfdPrescriptionILicence);

		lblPrescriptionILicence = new JLabel("Issuer licence");
		lblPrescriptionILicence.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrescriptionILicence.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPrescriptionILicence.setBounds(539, 235, 115, 35);
		pnlPrescriptionDetails.add(lblPrescriptionILicence);

	}

	private void fillPrescriptionAddJPanel() {

		pnlPrescriptionAdd = new JPanel();
		pnlPrescriptionAdd.setBounds(303, 54, 1071, 817);
		contentPane.add(pnlPrescriptionAdd);
		pnlPrescriptionAdd.setLayout(null);

		btnPrescriptionBack = new JButton("Back");
		btnPrescriptionBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (Component c : contentPane.getComponents()) {
					if (c instanceof JPanel) {
						((JPanel) c).setVisible(false);
					}
				}
				pnlPrescriptions.setVisible(true);
			}
		});
		btnPrescriptionBack.setBounds(10, 743, 141, 44);
		pnlPrescriptionAdd.add(btnPrescriptionBack);

		btnPrescriptionAdd = new JButton("Add");
		btnPrescriptionAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Add the prescription JSON
			}
		});
		btnPrescriptionAdd.setBounds(200, 743, 141, 44);
		pnlPrescriptionAdd.add(btnPrescriptionAdd);

		txtfdPrescriptionName = new JTextField();
		txtfdPrescriptionName.setBounds(211, 172, 264, 35);
		pnlPrescriptionAdd.add(txtfdPrescriptionName);
		txtfdPrescriptionName.setColumns(10);

		txtfdPrescriptionSurname = new JTextField();
		txtfdPrescriptionSurname.setBounds(211, 237, 264, 35);
		pnlPrescriptionAdd.add(txtfdPrescriptionSurname);
		txtfdPrescriptionSurname.setColumns(10);

		txtfdPrescriptionPesel = new JTextField();
		txtfdPrescriptionPesel.setBounds(211, 298, 264, 35);
		pnlPrescriptionAdd.add(txtfdPrescriptionPesel);
		txtfdPrescriptionPesel.setColumns(10);

		lblPrescriptionName = new JLabel("Name");
		lblPrescriptionName.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrescriptionName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPrescriptionName.setBounds(86, 170, 115, 35);
		pnlPrescriptionAdd.add(lblPrescriptionName);

		lblPrescriptionSurname = new JLabel("Surname");
		lblPrescriptionSurname.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrescriptionSurname.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPrescriptionSurname.setBounds(86, 237, 115, 35);
		pnlPrescriptionAdd.add(lblPrescriptionSurname);

		lblPrescriptionPesel = new JLabel("Pesel");
		lblPrescriptionPesel.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrescriptionPesel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPrescriptionPesel.setBounds(86, 298, 115, 35);
		pnlPrescriptionAdd.add(lblPrescriptionPesel);

		lblPrescriptionIName = new JLabel("Issuer name");
		lblPrescriptionIName.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrescriptionIName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPrescriptionIName.setBounds(539, 170, 115, 35);
		pnlPrescriptionAdd.add(lblPrescriptionIName);

		lblPrescriptionDosage = new JLabel("Dosage");
		lblPrescriptionDosage.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrescriptionDosage.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPrescriptionDosage.setBounds(86, 420, 115, 35);
		pnlPrescriptionAdd.add(lblPrescriptionDosage);

		lblPrescriptionMedicine = new JLabel("Medicine");
		lblPrescriptionMedicine.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrescriptionMedicine.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPrescriptionMedicine.setBounds(86, 353, 115, 35);
		pnlPrescriptionAdd.add(lblPrescriptionMedicine);

		txtfdPrescriptionMedicine = new JTextField();
		txtfdPrescriptionMedicine.setColumns(10);
		txtfdPrescriptionMedicine.setBounds(211, 355, 264, 35);
		pnlPrescriptionAdd.add(txtfdPrescriptionMedicine);

		txtfdPrescriptionDosage = new JTextField();
		txtfdPrescriptionDosage.setColumns(10);
		txtfdPrescriptionDosage.setBounds(211, 420, 264, 35);
		pnlPrescriptionAdd.add(txtfdPrescriptionDosage);

		txtfdPrescriptionIName = new JTextField();
		txtfdPrescriptionIName.setColumns(10);
		txtfdPrescriptionIName.setBounds(664, 172, 264, 35);
		pnlPrescriptionAdd.add(txtfdPrescriptionIName);

		lblPrescriptionExpiry = new JLabel("Expiry date");
		lblPrescriptionExpiry.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrescriptionExpiry.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPrescriptionExpiry.setBounds(568, 360, 115, 35);
		pnlPrescriptionAdd.add(lblPrescriptionExpiry);

		lblPrescriptionDate = new JLabel("Date of issue");
		lblPrescriptionDate.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrescriptionDate.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPrescriptionDate.setBounds(568, 314, 115, 35);
		pnlPrescriptionAdd.add(lblPrescriptionDate);

		txtfdPrescriptionDate = new JTextField();
		txtfdPrescriptionDate.setColumns(10);
		txtfdPrescriptionDate.setBounds(693, 315, 140, 37);
		pnlPrescriptionAdd.add(txtfdPrescriptionDate);

		txtfdPrescriptionExpiry = new JTextField();
		txtfdPrescriptionExpiry.setColumns(10);
		txtfdPrescriptionExpiry.setBounds(693, 362, 140, 35);
		pnlPrescriptionAdd.add(txtfdPrescriptionExpiry);

		lblPrescriptionDetails = new JLabel("Prescription Details");
		lblPrescriptionDetails.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrescriptionDetails.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPrescriptionDetails.setBounds(290, 29, 468, 35);
		pnlPrescriptionAdd.add(lblPrescriptionDetails);

		txtfdPrescriptionILicence = new JTextField();
		txtfdPrescriptionILicence.setColumns(10);
		txtfdPrescriptionILicence.setBounds(664, 237, 264, 35);
		pnlPrescriptionAdd.add(txtfdPrescriptionILicence);

		lblPrescriptionILicence = new JLabel("Issuer licence");
		lblPrescriptionILicence.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrescriptionILicence.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPrescriptionILicence.setBounds(539, 235, 115, 35);
		pnlPrescriptionAdd.add(lblPrescriptionILicence);

	}

	private void enablePrescriptionDetails() {

		txtfdPrescriptionName.setEnabled(true);
		txtfdPrescriptionSurname.setEnabled(true);
		txtfdPrescriptionPesel.setEnabled(true);
		txtfdPrescriptionMedicine.setEnabled(true);
		txtfdPrescriptionDosage.setEnabled(true);
		txtfdPrescriptionDate.setEnabled(true);
		txtfdPrescriptionExpiry.setEnabled(true);
		txtfdPrescriptionIName.setEnabled(true);
		txtfdPrescriptionILicence.setEnabled(true);

	}

	private void disablePrescriptionDetails() {

		txtfdPrescriptionName.setEnabled(false);
		txtfdPrescriptionSurname.setEnabled(false);
		txtfdPrescriptionPesel.setEnabled(false);
		txtfdPrescriptionMedicine.setEnabled(false);
		txtfdPrescriptionDosage.setEnabled(false);
		txtfdPrescriptionDate.setEnabled(false);
		txtfdPrescriptionExpiry.setEnabled(false);
		txtfdPrescriptionIName.setEnabled(false);
		txtfdPrescriptionILicence.setEnabled(false);

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

			pnlUser.setVisible(true);

			// tu bedzie weryfikacja, czy zalogowany jest doktor
			// rest do doctora
			// Get information about specific doctor using id

		} else if (e.getSource() == btnPrescriptions) {
			for (Component c : contentPane.getComponents()) {
				if (c instanceof JPanel) {
					((JPanel) c).setVisible(false);
				}
			}
			try {
				populatePrescriptionTable();
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			pnlPrescriptions.setVisible(true);
		}

	}
}