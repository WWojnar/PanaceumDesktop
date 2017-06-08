package Rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

@SuppressWarnings("unused")
public class RestController {

	private String dataTransfer(JSONObject json, String url_address) {
		String print_returned = "";

		try {
			URL url = new URL(url_address);
			URLConnection connection = url.openConnection();

			connection.setDoOutput(true);
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setConnectTimeout(5000);
			connection.setReadTimeout(5000);

			OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
			out.write(json.toString());
			out.close();

			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

			String input;
			while ((input = in.readLine()) != null) {
				print_returned += input + "\n";
			}

			in.close();
		} catch (IOException e) {
			if (e.toString().contains("java.net.ConnectException: Connection refused: connect")) {
				print_returned += "Zapomniales zalaczyc serwer" + "\n";
			} else {
				print_returned += e;
			}
		}

		return print_returned;
	}

	private String dataReceive(String url_address) {
		String print_returned = "";

		try {
			URL url = new URL(url_address);
			URLConnection connection = url.openConnection();

			connection.setDoOutput(true);
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setConnectTimeout(5000);
			connection.setReadTimeout(5000);

			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

			String input;
			while ((input = in.readLine()) != null) {
				print_returned += input + "\n";
			}

			in.close();
		} catch (IOException e) {
			if (e.toString().contains("java.net.ConnectException: Connection refused: connect")) {
				print_returned += "Zapomniales zalaczyc serwer" + "\n";
			} else {
				print_returned += e;
			}
		}

		return print_returned;
	}

	public String register(String login, String passwd) {
		JSONObject json = null;
		try {
			json = new JSONObject().put("login", login).put("password", passwd);
		} catch (JSONException e) {
			return "Klient: Blad przy tworzeniu JSONa";
		}

		// String url = "http://localhost:8084/Panaceum/user/register";
		String url = "http://panaceum.iiar.pwr.edu.pl:8080/Panaceum/user/register";

		return dataTransfer(json, url);
	}

	public String login(String login, String passwd) {
		JSONObject json = null;
		try {
			json = new JSONObject().put("login", login).put("password", passwd);
		} catch (JSONException e) {
			return "Klient: Blad przy tworzeniu JSONa";
		}

		// String url = "http://localhost:8084/Panaceum/user/login";
		String url = "http://panaceum.iiar.pwr.edu.pl:8080/Panaceum/user/login";

		return dataTransfer(json, url);
	}

	public String getPrescription(int id, String login, String token) {

		String url = "http://panaceum.iiar.pwr.edu.pl:8080/Panaceum/doctor/getPrescriptions/" + id + "/" + login + "/"
				+ token;

		return dataReceive(url);
	}

	public String getHospital(int id) {

		String url = "http://panaceum.iiar.pwr.edu.pl:8080/Panaceum/hospital/getById/" + id;

		return dataReceive(url);
	}

	public String patientsList(String login, String token) {

		String url = "http://panaceum.iiar.pwr.edu.pl:8080/Panaceum/patient/getAll/" + login + "/" + token;

		return dataReceive(url);
	}

	public String patient(String pesel, String login, String token) {

		String url = "http://panaceum.iiar.pwr.edu.pl:8080/Panaceum/patient/getByPesel/" + pesel + "/" + login + "/"
				+ token;

		return dataReceive(url);
	}

	public String getHistory(String pesel, String login, String token) {

		String url = "http://panaceum.iiar.pwr.edu.pl:8080/Panaceum/patient/getHistory/" + pesel + "/" + login + "/"
				+ token;

		return dataReceive(url);
	}

	public String doctorsList(String login, String token) {

		String url = "http://panaceum.iiar.pwr.edu.pl:8080/Panaceum/doctor/getAll/" + login + "/" + token;

		return dataReceive(url);
	}

	public String doctor(int id, String login, String token) {

		String url = "http://panaceum.iiar.pwr.edu.pl:8080/Panaceum/doctor/getById/" + id + "/" + login + "/" + token;

		return dataReceive(url);
	}
	
	public JSONObject doctorJson(int id, String login, String token) throws JSONException {
		
		String url = "http://panaceum.iiar.pwr.edu.pl:8080/Panaceum/doctor/getById/" + id + "/" + login + "/" + token;
		
		return new JSONObject(dataReceive(url));
	}

	public String getMedicineList(String login, String token) {

		String url = "http://panaceum.iiar.pwr.edu.pl:8080/Panaceum/medicine/getAll/" + login + "/" + token;

		return dataReceive(url);
	}

	public String getMedicineById(int id, String login, String token) {

		String url = "http://panaceum.iiar.pwr.edu.pl:8080/Panaceum/medicine/getById/" + id + "/" + login + "/" + token;

		return dataReceive(url);
	}
        
        public JSONObject getHospitalByDoctor(int id) throws JSONException {

		String url = "http://panaceum.iiar.pwr.edu.pl:8080/Panaceum/hospital/getByDoctor/" + id;

		return new JSONObject(dataReceive(url));
	}
	

	
        public JSONObject getPatientByPesel(String pesel, String login, String token) throws JSONException {

                        String url = "http://panaceum.iiar.pwr.edu.pl:8080/Panaceum/patient/getByPesel/" + pesel + "/" + login + "/" + token;
        System.err.println(url);
                        return new JSONObject(dataReceive(url));
                }
	
	public String postMedicine(String login, String token, String name, String activeSubstance) {

		JSONObject json = null;
		try {
			json = new JSONObject().put("login", login).put("token", token).put("name", name).put("activeSubstance", activeSubstance);
		} catch (JSONException e) {
			return "Klient: Blad przy tworzeniu JSONa addMedicine";
		}

		String url = "http://panaceum.iiar.pwr.edu.pl:8080/Panaceum/medicine/add";

		return dataTransfer(json, url);
	}

	public String deleteMedicineById(String login, String token, int id) {

		JSONObject json = null;
		try {
			json = new JSONObject().put("login", login).put("token", token);
		} catch (JSONException e) {
			return "Klient: Blad przy tworzeniu JSONa deleteMedicineById";
		}

		String url = "http://panaceum.iiar.pwr.edu.pl:8080/Panaceum/medicine/delete/" + id;

		return dataTransfer(json, url);
	}

	
	
	/*"login":"[string]",
	"token":"[string]",
	"dosage":"[string]",
	"expiryDate":"[string]",         (note:format: RRRR-MM-DD)
	"medicineName":"[string]",
	"doctorId":[integer],
	"patientId":[integer]}*/
	
	public String postReceipt(String login, String token, String dosage, String expiryDate, String medicineName, int doctorId, int patientId) {

		JSONObject json = null;
		try {
			json = new JSONObject().put("login", login).put("token", token).put("dosage", dosage).put("expiryDate", expiryDate).put("medicineName", medicineName).put("doctorId",doctorId).put("patientId", patientId);
		} catch (JSONException e) {
			return "Klient: Blad przy tworzeniu JSONa postReceipt";
		}

		String url = "http://panaceum.iiar.pwr.edu.pl:8080/Panaceum/prescription/add";

		return dataTransfer(json, url);
	}

	public String postPrescription(String login, String token, String dosage, String expiryDate, String medicineName, String doctorId, String patientId) {

		JSONObject json = null;
		try {
			json = new JSONObject().put("login", login).put("token", token).put("dosage", dosage).put("expiryDate", expiryDate).put("medicineName", medicineName).put("doctorId", doctorId).put("patientId", patientId);
		} catch (JSONException e) {
			return "Klient: Blad przy tworzeniu JSONa addPrescription";
		}

		String url = "http://panaceum.iiar.pwr.edu.pl:8080/Panaceum/prescription/add";

		return dataTransfer(json, url);
	}



	/*
	 * public static void main(String[] args) { ClientTest test = new
	 * ClientTest(); String help = "";
	 * 
	 * //help = test.register("kluski", "1234"); help = test.login("kluski",
	 * "1234");
	 * 
	 * System.out.println(help); }
	 */

}