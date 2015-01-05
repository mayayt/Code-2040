
import java.net.*;
import java.io.*;

public class Registration {

	/*Constants */
	private static final String EMAIL = "mayat@stanford.edu";
	private static final String GITHUB = "https://github.com/mayayt/Code2040";
	
	public static void main(String[] args) throws IOException {
		
		/**
		 * String variable containing our API Endpoint link 
		 */
		String urlText = "http://challenge.code2040.org/api/register";
		
		/**
		 * Parameters that allow me to access and retrieve my token as my unique identifier 
		 */
		String content = "{\"email\":\""+EMAIL+"\",\"github\":\""+GITHUB+"\"}";
		
		/* Sending a POST request */
		getPostInformation(urlText, content);
		
	}
	
	/**
	 * Retrieves a POST response from a specified HTTP server
	 * @param urlText server to request response from
	 * @param urlParameters the information being sent with which to request
	 * @return returns a string of the response in JSON
	 * @throws IOException
	 */
	public static String getPostInformation (String urlText, String urlParameters) throws IOException {
		URL obj = new URL(urlText);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
 
		// Add request header
		con.setRequestMethod("POST");
 
		// Send post request
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(urlParameters);
		wr.flush();
		wr.close();
 
		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'POST' request to URL : " + urlText);
		System.out.println("Post parameters : " + urlParameters);
		System.out.println("Response Code : " + responseCode);
 
		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response2 = new StringBuffer();
 
		while ((inputLine = in.readLine()) != null) {
			response2.append(inputLine);
		}
		in.close();
 
		//Print result
		System.out.println(response2.toString());
		return response2.toString();
	}
}