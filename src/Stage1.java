import java.io.IOException;

import javax.swing.JOptionPane;

public class Stage1 {

	/* Constants */
	public static final String TOKEN = "kwaD7Tul2y";
	
	public static void main(String[] args) throws IOException {

		/* Construct POST request*/
		String targetUrl = "http://challenge.code2040.org/api/getstring";
		String content = "{\"token\":\""+TOKEN+"\"}";
		String response = Registration.getPostInformation(targetUrl, content );
		
		/* Returns reversed string.*/
		String str = response.substring( 11, response.length() - 2);
		String result = getReverseString(str);
		
		/* Send a POST of the resultant */
		targetUrl = "http://challenge.code2040.org/api/validatestring";
		String urlParameters = "{\"token\":\""+TOKEN+"\",\"string\":\""+result+"\"}";
		Registration.getPostInformation(targetUrl, urlParameters);

	}
	
	/**
	 * Takes in a string and reverses it
	 * @param str string to be reversed
	 * reversed string
	 * After reading Java for Dummies, I realized that I really enjoy nice JFrames.
	 */
	private static String getReverseString(String str) {
		JOptionPane.showMessageDialog(null,"The given string is " + str);
		String result = new StringBuffer(str).reverse().toString();
		JOptionPane.showMessageDialog(null,"The reverse of the chosen word is " + result);
		return result;
	}
}
