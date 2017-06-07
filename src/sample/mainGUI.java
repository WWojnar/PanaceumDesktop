package sample;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import Rest.RestController;
import net.miginfocom.swing.MigLayout;

import javax.swing.ListSelectionModel;
import java.awt.FlowLayout;

public class mainGUI extends JFrame implements ActionListener {

	RestController restController;
	private JSONObject PatientJSON = new JSONObject();
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JButton btnMedicines;
	private JPanel pnlMedicine;
	private JPanel mainPanelStorage;
	private JPanel sideBar;
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
	private JSONObject jsonAddMedicine = new JSONObject();
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
	private CardLayout cardLayout = new CardLayout();
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
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		this.setTitle("Panaceum");
		contentPane.setLayout(new MigLayout("insets 10", "[5%!][10%!]20[60%!, center]", "[]20[grow]"));
		setBounds(100, 100, 1380, 900);
		contentPane.setBackground(Color.LIGHT_GRAY);
		
		
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		
		
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image image = toolkit.getImage("images/cursos2.png");
		Cursor c = toolkit.createCustomCursor(image , new Point(contentPane.getX(), 
		           contentPane.getY()), "img");
		contentPane.setCursor (c);
		
		
		
		
		
		//////////////////////////////////////////////////
		//Layout Creation
		
		
		
		
		
		btnSearch = new JButton("Search:");
		contentPane.add(btnSearch);
		
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 13));
		contentPane.add(textField, "grow");
		textField.setColumns(10);
		
		
		JLabel lblWelcome = new JLabel("Welcome " + Controller.name);
		lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcome.setFont(new Font("Tahoma", Font.BOLD, 20));
		contentPane.add(lblWelcome, "alignx center,aligny center,wrap");

		
		
		
		/*JSeparator separator_1 = new JSeparator();
		contentPane.add(separator_1);

		JSeparator separator = new JSeparator();
		separator.setToolTipText("");
		separator.setOrientation(SwingConstants.VERTICAL);
		contentPane.add(separator);*/
		
		sideBar = new JPanel();
		contentPane.add(sideBar, "flowy,spanx 2,alignx center,aligny top,grow");
		sideBar.setLayout(new MigLayout("", "[100%]", "[]20[]20[]"));
		
		btnMedicines = new JButton("Medicine database");
		sideBar.add(btnMedicines, "wrap, grow");
		btnMedicines.addActionListener(this);
		
		btnUser = new JButton("User panel");
		sideBar.add(btnUser, "wrap, grow");
		btnUser.addActionListener(this);
		
		btnPrescriptions = new JButton("Prescriptions");
		sideBar.add(btnPrescriptions, "grow");
		btnPrescriptions.addActionListener(this);

		mainPanelStorage = new JPanel(cardLayout);
		mainPanelStorage.setPreferredSize(new Dimension( 1071, 817));
		contentPane.add(mainPanelStorage, "alignx left");

		
		
		
		pnlMedicine = new JPanel();
		mainPanelStorage.add(pnlMedicine);
		pnlMedicine.setLayout(new MigLayout("", "[][]5[]", "[][][]"));
		
		pnlSingleMedicine = new JPanel();
		mainPanelStorage.add(pnlSingleMedicine);
		pnlSingleMedicine.setLayout(new MigLayout("","[]40[]40[]", "[]40[][][]"));
		
		pnlAddMedicine = new JPanel();
		mainPanelStorage.add(pnlAddMedicine);
		pnlAddMedicine.setLayout(new MigLayout("","[]40[]40[]", "[]40[][][]"));
		
		pnlUser = new JPanel();
		mainPanelStorage.add(pnlUser);
		pnlUser.setLayout(new MigLayout());
		
		pnlPatient = new JPanel();
		mainPanelStorage.add(pnlPatient);
		pnlPatient.setLayout(new MigLayout());
		
		pnlSingleHistory = new JPanel();
		mainPanelStorage.add(pnlSingleHistory);
		pnlSingleHistory.setLayout(new MigLayout("insets 40", "[30%!]", "[30:40:50]10:20:30[30:40:50]10:20:30[30:40:50]10:20:30[30:40:50]10:20:30[30:40:50]10:20:30[30:40:50]"));

		pnlPrescriptions = new JPanel();
		mainPanelStorage.add(pnlPrescriptions);
		pnlPrescriptions.setLayout(new MigLayout("", "[]20[30%][20%]", "[][]"));
		
		pnlPrescriptionDetails = new JPanel();
		mainPanelStorage.add(pnlPrescriptionDetails);
		pnlPrescriptionDetails.setLayout(new MigLayout());
		
		pnlPrescriptionAdd = new JPanel();
		mainPanelStorage.add(pnlPrescriptionAdd);
		pnlPrescriptionAdd.setLayout(new MigLayout());
	
		/////////////////////////////////////////////

		// *********************************************
		// PANEL MEDICINES

		String[] medicineColumnNames = { "Id:", "Name:" };

		// tutaj bedzie rest do leków
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
				for (Component c : mainPanelStorage.getComponents()) {
					if (c instanceof JPanel) {
						((JPanel) c).setVisible(false);
					}
				}
				pnlAddMedicine.setVisible(true);

			}
		});

		//btnAddNewMedicine.setBounds(933, 8, 128, 23);
		pnlMedicine.add(btnAddNewMedicine);

		// Pole tekstowe do wyszukiwania
		txtSearchMedicine = new JTextField();
		txtSearchMedicine.setText("Search..");
		//txtSearchMedicine.setBounds(10, 9, 86, 20);
		pnlMedicine.add(txtSearchMedicine);
		txtSearchMedicine.setColumns(10);

		// przycisk do wyszukiwania
		btnSearchMedicine = new JButton("Search");
		//btnSearchMedicine.setBounds(120, 8, 107, 23);
		pnlMedicine.add(btnSearchMedicine, "wrap");

		JScrollPane scrollPane_1 = new JScrollPane();
		//scrollPane_1.setBounds(10, 42, 1051, 764);
		pnlMedicine.add(scrollPane_1, "span");

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
				//String idMed;
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

					for (Component c : mainPanelStorage.getComponents()) {
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

		

		lblMedicineDetails = new JLabel("Medicine Id: " + medId);
		lblMedicineDetails.setFont(new Font("Tahoma", Font.PLAIN, 14));
		//lblMedicineDetails.setBounds(10, 11, 603, 37);
		pnlSingleMedicine.add(lblMedicineDetails, "cell 0 0 2 0");

		lblName_2 = new JLabel("Name: ");
		lblName_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		//lblName_2.setBounds(10, 73, 55, 37);
		pnlSingleMedicine.add(lblName_2, "cell 0 1");

		txtpnSingleMedicineName = new JTextPane();
		txtpnSingleMedicineName.setText("No information");
		//txtpnSingleMedicineName.setBounds(10, 113, 354, 37);
		pnlSingleMedicine.add(txtpnSingleMedicineName, "cell 0 2");

		// 2 times
		lblActiveSubstances = new JLabel("Active Substances:");
		lblActiveSubstances.setFont(new Font("Tahoma", Font.PLAIN, 14));
		//lblActiveSubstances.setBounds(10, 179, 130, 37);
		pnlSingleMedicine.add(lblActiveSubstances, "cell 1 1");

		txtpnSingleMedicineActive = new JTextPane();
		txtpnSingleMedicineActive.setText("No information");
		//txtpnSingleMedicineActive.setBounds(10, 232, 354, 460);
		pnlSingleMedicine.add(txtpnSingleMedicineActive, "cell 1 2");

		// 2 times
		lblRelations = new JLabel("Relations");
		lblRelations.setFont(new Font("Tahoma", Font.PLAIN, 14));
		//lblRelations.setBounds(572, 73, 157, 37);
		pnlSingleMedicine.add(lblRelations, "cell 2 1");

		txtpnMedicineRelationsWith = new JTextPane();
		txtpnMedicineRelationsWith.setText("Do not mix with alcohol...");
		//txtpnMedicineRelationsWith.setBounds(572, 113, 430, 579);
		pnlSingleMedicine.add(txtpnMedicineRelationsWith, "cell 2 2");

		// Go back to Medicine view
		btnCancelSingleMedicine = new JButton("Back");
		btnCancelSingleMedicine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (Component c : mainPanelStorage.getComponents()) {
					if (c instanceof JPanel) {
						((JPanel) c).setVisible(false);
					}
				}
				pnlMedicine.setVisible(true);
			}
		});
		//btnCancelSingleMedicine.setBounds(10, 743, 141, 44);
		pnlSingleMedicine.add(btnCancelSingleMedicine, "cell 0 4");

		// koniec SINGLE MEDICINES
		// *********************************************

		// *********************************************
		// PANEL ADD MEDICINES

		

		lblToAddNew = new JLabel("To add new medicine, fill all the fields and press \"Add\" button.");
		lblToAddNew.setFont(new Font("Tahoma", Font.PLAIN, 14));
		//lblToAddNew.setBounds(10, 11, 603, 37);
		pnlAddMedicine.add(lblToAddNew, "cell 0 0 2 0");

		lblName_1 = new JLabel("Name: ");
		lblName_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		//lblName_1.setBounds(10, 73, 55, 37);
		pnlAddMedicine.add(lblName_1, "cell 0 1");

		txtpnAddMedicineName = new JTextPane();
		txtpnAddMedicineName.setText("");
		//txtpnAddMedicineName.setBounds(10, 113, 354, 37);
		pnlAddMedicine.add(txtpnAddMedicineName, "cell 0 2");

		lblActiveSubstances = new JLabel("Active Substances:");
		lblActiveSubstances.setFont(new Font("Tahoma", Font.PLAIN, 14));
		//lblActiveSubstances.setBounds(10, 179, 130, 37);
		pnlAddMedicine.add(lblActiveSubstances, "cell 1 1");

		txtpnAddMedicineActive = new JTextPane();
		txtpnAddMedicineActive.setText("");
		//txtpnAddMedicineActive.setBounds(10, 232, 354, 460);
		pnlAddMedicine.add(txtpnAddMedicineActive, "cell 1 2");

		lblRelations = new JLabel("Relations");
		lblRelations.setFont(new Font("Tahoma", Font.PLAIN, 14));
		//lblRelations.setBounds(572, 73, 157, 37);
		pnlAddMedicine.add(lblRelations, "cell 2 1");

		txtpnAddRelationsWith = new JTextPane();
		txtpnAddRelationsWith.setText("");
		//txtpnAddRelationsWith.setBounds(572, 113, 430, 579);
		pnlAddMedicine.add(txtpnAddRelationsWith, "cell 2 2");

		// Adding new medicine
		btnSubmitNewMedicine = new JButton("Add");
		btnSubmitNewMedicine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Add function here...(txtpnAddMedicineName.getText(), ---------------------------------------------------------------
//				(txtpnAddMedicineActive.getText());
				try {
					jsonAddMedicine = new JSONObject(
							restController.postMedicine(Controller.name, Controller.token, txtpnAddMedicineName.getText(), txtpnAddMedicineActive.getText()));
				} catch (JSONException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					System.out.println("Error json, add medicine wywolanie");
				}
				JOptionPane.showMessageDialog(null, "New medicine has been added");
				for (Component c : mainPanelStorage.getComponents()) {
					if (c instanceof JPanel) {
						((JPanel) c).setVisible(false);
					}
				}
				pnlMedicine.setVisible(true);
			}
		});
		//btnSubmitNewMedicine.setBounds(861, 732, 141, 44);
		pnlAddMedicine.add(btnSubmitNewMedicine, "cell 2 3");

		// Go back to Medicine view
		btnCancelAddMedicine = new JButton("Cancel");
		btnCancelAddMedicine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (Component c : mainPanelStorage.getComponents()) {
					if (c instanceof JPanel) {
						((JPanel) c).setVisible(false);
					}
				}
				pnlMedicine.setVisible(true);
			}
		});
		//btnCancelAddMedicine.setBounds(10, 743, 141, 44);
		pnlAddMedicine.add(btnCancelAddMedicine, "cell 0 3");

		// koniec przykladu ADD medicine
		// ********************* ************************

		// *********************************************
		// PANEL USER (DODAWANIE PACJENTA I WSZYSTKIE AKCJE, KTORE NIE PASUJA DO
		// INNYCH PANELI)


		lblDoctorName = new JLabel("xxxxxxxxxxxxxxxxxxxxx");
		lblDoctorName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDoctorName.setFont(new Font("Tahoma", Font.PLAIN, 25));
		pnlUser.add(lblDoctorName, "spanx 2,wrap");

		lblDoctorLastName = new JLabel("xxxxxxxxxxxxxxxx");
		lblDoctorLastName.setFont(new Font("Tahoma", Font.BOLD, 25));
		pnlUser.add(lblDoctorLastName, "spanx 2,wrap");

		lblSpecialityStr = new JLabel("Speciality:");
		lblSpecialityStr.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnlUser.add(lblSpecialityStr);

		lblSpeciality = new JLabel("xxxxxxxxxxxxxxxxxxxxx");
		lblSpeciality.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnlUser.add(lblSpeciality, "wrap");

		lblLicenceStr = new JLabel("Licence number:");
		lblLicenceStr.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnlUser.add(lblLicenceStr, "");

		lblLicence = new JLabel("xxxxxxxxxxxxxxxxxxxxx");
		lblLicence.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnlUser.add(lblLicence, "wrap");

		

		lblDoctorEmailStr = new JLabel("Email:");
		lblDoctorEmailStr.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnlUser.add(lblDoctorEmailStr);

		lblDoctorEmail = new JLabel("xxxxxxxxxxxxxxxxxxxxx");
		lblDoctorEmail.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnlUser.add(lblDoctorEmail, "wrap");

		lblDoctorPhoneStr = new JLabel("Phone:");
		lblDoctorPhoneStr.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnlUser.add(lblDoctorPhoneStr);

		lblDoctorPhone = new JLabel("xxxxxxxxxxxxxxxxxxxxx");
		lblDoctorPhone.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnlUser.add(lblDoctorPhone, "wrap");

		btnAddPatient = new JButton("Add Patient");
		pnlUser.add(btnAddPatient);
		
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

		JLabel lblName = new JLabel("New label");
		lblName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 28));
		pnlPatient.add(lblName, "spanx 2");

		String[] columnNames = { "Recognition", "Begin of hospitalization", "End of hospitalization" };

		lblSurname = new JLabel("New label");
		lblSurname.setHorizontalAlignment(SwingConstants.LEFT);
		lblSurname.setFont(new Font("Tahoma", Font.BOLD, 28));
		pnlPatient.add(lblSurname, "spanx 2,wrap");

		lblBloodStr = new JLabel("Blood Type:");
		lblBloodStr.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnlPatient.add(lblBloodStr);

		lblBloodType = new JLabel("xx");
		lblBloodType.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnlPatient.add(lblBloodType, "wrap");

		lblPhoneStr = new JLabel("Phone number:");
		lblPhoneStr.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnlPatient.add(lblPhoneStr);

		lblPhone = new JLabel("xxxxxx");
		lblPhone.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnlPatient.add(lblPhone, "wrap");

		lblEmailStr = new JLabel("Email:");
		lblEmailStr.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnlPatient.add(lblEmailStr);

		lblEmail = new JLabel("xxxxxxxxxxxxxxxxxxxxxxxxx");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnlPatient.add(lblEmail, "wrap");

		lblAddressStr = new JLabel("Address:");
		lblAddressStr.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnlPatient.add(lblAddressStr, "split 2,wrap");

		lblSexStr = new JLabel("Sex:");
		lblSexStr.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnlPatient.add(lblSexStr);

		lblSex = new JLabel("yes");
		lblSex.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnlPatient.add(lblSex, "wrap");

		lblAgeStr = new JLabel("Age:");
		lblAgeStr.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnlPatient.add(lblAgeStr);

		lblAge = new JLabel("xx");
		lblAge.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnlPatient.add(lblAge, "wrap");

		lblStreet = new JLabel("xxxxxxxxxxxxxxxxxxxxxxxxx");
		lblStreet.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnlPatient.add(lblStreet, "wrap");

		lblFlat = new JLabel("xx");
		lblFlat.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnlPatient.add(lblFlat);

		lblBuilding = new JLabel("xx");
		lblBuilding.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnlPatient.add(lblBuilding);

		lblZip = new JLabel("xxxxxx");
		lblZip.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnlPatient.add(lblZip);

		lblCity = new JLabel("xxxxxxxxxxxxxxxxxxxxxxxxx");
		lblCity.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnlPatient.add(lblCity, "wrap");

		JLabel lblHistoryStr = new JLabel("History of hospitalization:");
		lblHistoryStr.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnlPatient.add(lblHistoryStr);

		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				searchConfirmed = false;
				// sprawdzanie, czy podany pesel jest zgodny z ktoryms w tabeli
				for (String i : PeselTable) {

					if (textField.getText().equals(i)) {
						searchConfirmed = true;

						for (Component c : mainPanelStorage.getComponents()) {
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

						tblHistory.enable(false);

						tblHistory.getColumn(columnNames[0]).setWidth(10);
						pnlPatient.add(tblHistory);

						JScrollPane scrollPane = new JScrollPane(tblHistory);
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

									for (Component c : mainPanelStorage.getComponents()) {
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

		// WYBRANA HISTORIA HERE

		lblHistory = new JLabel("xxxxxxxxxxxxxxx");
		lblHistory.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlSingleHistory.add(lblHistory, "wrap");

		// Go back to history view

		JButton btnCancelHistory = new JButton();

		btnCancelHistory = new JButton("Back");
		btnCancelHistory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (Component c : mainPanelStorage.getComponents()) {
					if (c instanceof JPanel) {
						((JPanel) c).setVisible(false);
					}
				}
				pnlPatient.setVisible(true);
			}
		});
		pnlSingleHistory.add(btnCancelHistory, "grow,wrap");

		JButton btnInfection = new JButton("Infection card");
		pnlSingleHistory.add(btnInfection, "grow,wrap");

		JButton btnFirstExamination = new JButton("First Examination");
		pnlSingleHistory.add(btnFirstExamination, "grow,wrap");

		JButton btnInterview = new JButton("Interview");
		pnlSingleHistory.add(btnInterview, "grow,wrap");

		JButton btnExcerpt = new JButton("Excerpt");
		pnlSingleHistory.add(btnExcerpt, "grow,wrap");

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
		for (Component d : mainPanelStorage.getComponents()) {
			if (d instanceof JPanel) {
				((JPanel) d).setVisible(false);
			}
		}

		pnlUser.setVisible(true);

	}

	private void fillPrescriptionJPanel() {

		
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

				for (Component c : mainPanelStorage.getComponents()) {
					if (c instanceof JPanel) {
						((JPanel) c).setVisible(false);
					}
				}
				pnlPrescriptionDetails.setVisible(true);

			}
		});
		pnlPrescriptions.add(btnPrescriptionModify);

		
		searchPrescriptionField = new JTextField();
		pnlPrescriptions.add(searchPrescriptionField, "width 50:60:70,alignx center,aligny center");
		searchPrescriptionField.setColumns(3);
		
		
		btnPrescriptionAdd = new JButton("Add");
		btnPrescriptionAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				for (Component c : mainPanelStorage.getComponents()) {
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
		pnlPrescriptions.add(btnPrescriptionAdd, "wrap");

		
		
		
		scrollPane = new JScrollPane();
		pnlPrescriptions.add(scrollPane, "dock south");

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

					for (Component c : mainPanelStorage.getComponents()) {
						if (c instanceof JPanel) {
							((JPanel) c).setVisible(false);
						}
					}
					pnlPrescriptionDetails.setVisible(true);

				}
			}
		});


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

		
		lblPrescriptionDetails = new JLabel("Prescription Details");
		lblPrescriptionDetails.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrescriptionDetails.setFont(new Font("Tahoma", Font.PLAIN, 18));
		pnlPrescriptionDetails.add(lblPrescriptionDetails, "span 4, wrap");
		
		
		lblPrescriptionName = new JLabel("Name");
		lblPrescriptionName.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrescriptionName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnlPrescriptionDetails.add(lblPrescriptionName);

		txtfdPrescriptionName = new JTextField();
		txtfdPrescriptionName.setDisabledTextColor(Color.BLACK);
		pnlPrescriptionDetails.add(txtfdPrescriptionName, "");
		txtfdPrescriptionName.setColumns(10);
		
		lblPrescriptionIName = new JLabel("Issuer name");
		lblPrescriptionIName.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrescriptionIName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnlPrescriptionDetails.add(lblPrescriptionIName);
		
		txtfdPrescriptionIName = new JTextField();
		txtfdPrescriptionIName.setDisabledTextColor(Color.BLACK);
		txtfdPrescriptionIName.setColumns(10);
		pnlPrescriptionDetails.add(txtfdPrescriptionIName, "wrap");

		lblPrescriptionSurname = new JLabel("Surname");
		lblPrescriptionSurname.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrescriptionSurname.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnlPrescriptionDetails.add(lblPrescriptionSurname);
		
		txtfdPrescriptionSurname = new JTextField();
		txtfdPrescriptionSurname.setDisabledTextColor(Color.BLACK);
		pnlPrescriptionDetails.add(txtfdPrescriptionSurname, "");
		txtfdPrescriptionSurname.setColumns(10);
		
		lblPrescriptionILicence = new JLabel("Issuer licence");
		lblPrescriptionILicence.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrescriptionILicence.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnlPrescriptionDetails.add(lblPrescriptionILicence);
		
		txtfdPrescriptionILicence = new JTextField();
		txtfdPrescriptionILicence.setDisabledTextColor(Color.BLACK);
		txtfdPrescriptionILicence.setColumns(10);
		pnlPrescriptionDetails.add(txtfdPrescriptionILicence, "wrap");
		
		lblPrescriptionPesel = new JLabel("Pesel");
		lblPrescriptionPesel.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrescriptionPesel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnlPrescriptionDetails.add(lblPrescriptionPesel);

		txtfdPrescriptionPesel = new JTextField();
		txtfdPrescriptionPesel.setDisabledTextColor(Color.BLACK);
		pnlPrescriptionDetails.add(txtfdPrescriptionPesel, "");
		txtfdPrescriptionPesel.setColumns(10);
		
		lblPrescriptionDate = new JLabel("Date of issue");
		lblPrescriptionDate.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrescriptionDate.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnlPrescriptionDetails.add(lblPrescriptionDate);

		txtfdPrescriptionDate = new JTextField();
		txtfdPrescriptionDate.setDisabledTextColor(Color.BLACK);
		txtfdPrescriptionDate.setColumns(10);
		pnlPrescriptionDetails.add(txtfdPrescriptionDate, "wrap");
		
		lblPrescriptionMedicine = new JLabel("Medicine");
		lblPrescriptionMedicine.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrescriptionMedicine.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnlPrescriptionDetails.add(lblPrescriptionMedicine);

		txtfdPrescriptionMedicine = new JTextField();
		txtfdPrescriptionMedicine.setDisabledTextColor(Color.BLACK);
		txtfdPrescriptionMedicine.setColumns(10);
		pnlPrescriptionDetails.add(txtfdPrescriptionMedicine, "");

		lblPrescriptionExpiry = new JLabel("Expiry date");
		lblPrescriptionExpiry.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrescriptionExpiry.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnlPrescriptionDetails.add(lblPrescriptionExpiry);
		
		txtfdPrescriptionExpiry = new JTextField();
		txtfdPrescriptionExpiry.setDisabledTextColor(Color.BLACK);
		txtfdPrescriptionExpiry.setColumns(10);
		pnlPrescriptionDetails.add(txtfdPrescriptionExpiry, "wrap");
		
		lblPrescriptionDosage = new JLabel("Dosage");
		lblPrescriptionDosage.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrescriptionDosage.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnlPrescriptionDetails.add(lblPrescriptionDosage);

		txtfdPrescriptionDosage = new JTextField();
		txtfdPrescriptionDosage.setDisabledTextColor(Color.BLACK);
		txtfdPrescriptionDosage.setColumns(10);
		pnlPrescriptionDetails.add(txtfdPrescriptionDosage, "wrap");
		






		




		
		btnPrescriptionBack = new JButton("Back");
		btnPrescriptionBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (Component c : mainPanelStorage.getComponents()) {
					if (c instanceof JPanel) {
						((JPanel) c).setVisible(false);
					}
				}
				pnlPrescriptions.setVisible(true);
				btnPrescriptionPrint.setVisible(true);
			}
		});
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
		pnlPrescriptionDetails.add(btnPrescriptionModify);

		btnPrescriptionPrint = new JButton("Print");
		btnPrescriptionPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// Modify the Prescription JSON
			}
		});
		pnlPrescriptionDetails.add(btnPrescriptionPrint);
		

	}

	private void fillPrescriptionAddJPanel() {


		btnPrescriptionBack = new JButton("Back");
		btnPrescriptionBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (Component c : mainPanelStorage.getComponents()) {
					if (c instanceof JPanel) {
						((JPanel) c).setVisible(false);
					}
				}
				pnlPrescriptions.setVisible(true);
			}
		});
		pnlPrescriptionAdd.add(btnPrescriptionBack);

		btnPrescriptionAdd = new JButton("Add");
		btnPrescriptionAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Add the prescription JSON
			}
		});
		pnlPrescriptionAdd.add(btnPrescriptionAdd);

		txtfdPrescriptionName = new JTextField();
		pnlPrescriptionAdd.add(txtfdPrescriptionName);
		txtfdPrescriptionName.setColumns(10);

		txtfdPrescriptionSurname = new JTextField();
		pnlPrescriptionAdd.add(txtfdPrescriptionSurname);
		txtfdPrescriptionSurname.setColumns(10);

		txtfdPrescriptionPesel = new JTextField();
		pnlPrescriptionAdd.add(txtfdPrescriptionPesel);
		txtfdPrescriptionPesel.setColumns(10);

		lblPrescriptionName = new JLabel("Name");
		lblPrescriptionName.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrescriptionName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnlPrescriptionAdd.add(lblPrescriptionName);

		lblPrescriptionSurname = new JLabel("Surname");
		lblPrescriptionSurname.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrescriptionSurname.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnlPrescriptionAdd.add(lblPrescriptionSurname);

		lblPrescriptionPesel = new JLabel("Pesel");
		lblPrescriptionPesel.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrescriptionPesel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnlPrescriptionAdd.add(lblPrescriptionPesel);

		lblPrescriptionIName = new JLabel("Issuer name");
		lblPrescriptionIName.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrescriptionIName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnlPrescriptionAdd.add(lblPrescriptionIName);

		lblPrescriptionDosage = new JLabel("Dosage");
		lblPrescriptionDosage.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrescriptionDosage.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnlPrescriptionAdd.add(lblPrescriptionDosage);

		lblPrescriptionMedicine = new JLabel("Medicine");
		lblPrescriptionMedicine.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrescriptionMedicine.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnlPrescriptionAdd.add(lblPrescriptionMedicine);

		txtfdPrescriptionMedicine = new JTextField();
		txtfdPrescriptionMedicine.setColumns(10);
		pnlPrescriptionAdd.add(txtfdPrescriptionMedicine);

		txtfdPrescriptionDosage = new JTextField();
		txtfdPrescriptionDosage.setColumns(10);
		pnlPrescriptionAdd.add(txtfdPrescriptionDosage);

		txtfdPrescriptionIName = new JTextField();
		txtfdPrescriptionIName.setColumns(10);
		pnlPrescriptionAdd.add(txtfdPrescriptionIName);

		lblPrescriptionExpiry = new JLabel("Expiry date");
		lblPrescriptionExpiry.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrescriptionExpiry.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnlPrescriptionAdd.add(lblPrescriptionExpiry);

		lblPrescriptionDate = new JLabel("Date of issue");
		lblPrescriptionDate.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrescriptionDate.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnlPrescriptionAdd.add(lblPrescriptionDate);

		txtfdPrescriptionDate = new JTextField();
		txtfdPrescriptionDate.setColumns(10);
		pnlPrescriptionAdd.add(txtfdPrescriptionDate);

		txtfdPrescriptionExpiry = new JTextField();
		txtfdPrescriptionExpiry.setColumns(10);
		pnlPrescriptionAdd.add(txtfdPrescriptionExpiry);

		lblPrescriptionDetails = new JLabel("Prescription Details");
		lblPrescriptionDetails.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrescriptionDetails.setFont(new Font("Tahoma", Font.PLAIN, 18));
		pnlPrescriptionAdd.add(lblPrescriptionDetails);

		txtfdPrescriptionILicence = new JTextField();
		txtfdPrescriptionILicence.setColumns(10);
		pnlPrescriptionAdd.add(txtfdPrescriptionILicence);

		lblPrescriptionILicence = new JLabel("Issuer licence");
		lblPrescriptionILicence.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrescriptionILicence.setFont(new Font("Tahoma", Font.PLAIN, 15));
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
			for (Component c : mainPanelStorage.getComponents()) {
				if (c instanceof JPanel) {
					((JPanel) c).setVisible(false);
				}
			}

			pnlMedicine.setVisible(true);
		} else if (e.getSource() == btnUser) {
			for (Component c : mainPanelStorage.getComponents()) {
				if (c instanceof JPanel) {
					((JPanel) c).setVisible(false);
				}
			}

			pnlUser.setVisible(true);

			// tu bedzie weryfikacja, czy zalogowany jest doktor
			// rest do doctora
			// Get information about specific doctor using id

		} else if (e.getSource() == btnPrescriptions) {
			for (Component c : mainPanelStorage.getComponents()) {
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




