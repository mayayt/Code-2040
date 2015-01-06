
import java.io.IOException;
import java.util.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Stage3 {
	
	/* Constants */
	public static final String TOKEN = "kwaD7Tul2y";

	public static void main(String[] args) throws IOException {

		String targetUrl = "http://challenge.code2040.org/api/prefix";
		String content = "{\"token\":\""+TOKEN+"\"}";

		/* Referencing POST Requesting method from Registration */
		String response = Registration.getPostInformation(targetUrl, content);

		/* Returns the array of correct words */
		ArrayList<String> result = getArray(response);
		
		content = toJsonString(result);
		targetUrl = "http://challenge.code2040.org/api/validateprefix";
		Registration.getPostInformation(targetUrl, content);
	}
	
	/**
	 * Retrieves the prefix and array from the response,
	 * and iterates through the array, checking for 
	 * a word that begins with the prefix
	 * @param response JSON response from the HTTP server
	 * @return the corrected array
	 */
	private static ArrayList<String> getArray(String response) {
		ArrayList<String> result = new ArrayList<String>();
		try {
			JSONObject obj = new JSONObject(response).getJSONObject("result");
			String prefix = obj.getString("prefix");
			JSONArray array = obj.getJSONArray("array");

			for(int i = 0; i < array.length(); i++ ) {
				String word = array.getString(i);
				if(word.length() > prefix.length() ) {
					String wordPrefix = word.substring(0, prefix.length());
					if(!wordPrefix.equals(prefix) ) {
						result.add(word);
					}
				}
			}
		} catch (JSONException ex ) {
			System.out.println("JSON Object Error");
		} return result;
	}
	
	/**
	 * Iterates through an array and converts it to JSON
	 */
	private static String toJsonString(ArrayList<String> result) {
		Iterator<String> it = result.iterator();
		String content = "{\"token\":\""+TOKEN+"\",\"array\":[\"";
		while(it.hasNext()){
			content += it.next()+"\"";
			if(it.hasNext() ){
				content += ",\"";
			}
		}
		content += "]}";
		return content;
	}
}
