package sample;

public class loginVerification {
	public static boolean verifyLogin(String login, String password){

		String correctUsername = "a";
		String correctPassword = "";
		if(login.equals(correctUsername) && password.equals(correctPassword)){
			return true;
		}else {
			return false;
			}
	}

}
