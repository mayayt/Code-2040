
import java.io.IOException;
import org.json.*;

public class Stage2 {

	/* Constants */
	public static final String TOKEN = "kwaD7Tul2y";

	public static void main(String[] args) throws IOException {

		String targetUrl = "http://challenge.code2040.org/api/haystack";
		String content = "{\"token\":\""+TOKEN+"\"}";

		/* Referencing POST Request method from Registration */
		String response = Registration.getPostInformation(targetUrl, content);

		/* Returns the index of the needle */
		int result = getNeedleIndex(response);
		
		content = "{\"token\":\""+TOKEN+"\",\"needle\":\""+result+"\"}";
		targetUrl = "http://challenge.code2040.org/api/validateneedle";
		Registration.getPostInformation(targetUrl, content);
	}

	/**
	 * This method retrieves the components of 
	 * the JSON dictionary: the needle and array. It then
	 * iterates through the array, until 
	 * finding a value that matches the needle
	 * @param response JSON response from HTTP server
	 * @return returns the index of the needle
	 */
	private static int getNeedleIndex (String response) {
		int result = 0;
		try {
			JSONObject obj = new JSONObject(response).getJSONObject("result");
			JSONArray haystack = obj.getJSONArray("haystack");
			String needle = obj.getString("needle");

			for(int i = 0; i < haystack.length() - 1; i ++) {
				if( haystack.get(i).equals(needle) ) {
					result = i; 
					break;
				}
			}
		} catch (JSONException ex ) {
			System.out.println("JSON Object Error");
		} return result;
	}

}