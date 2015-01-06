import java.io.IOException;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;
import org.json.JSONException;
import org.json.JSONObject;


public class Stage4 {
	/* Constants */
	public static final String TOKEN = "kwaD7Tul2y";

	public static void main(String[] args) throws IOException {

		String targetUrl = "http://challenge.code2040.org/api/time";
		String content = "{\"token\":\""+TOKEN+"\"}";

		/* Referencing POST Requesting method from Registration */
		String response = Registration.getPostInformation(targetUrl, content);

		/* Returns newly summed datestamp*/
		String datestamp = getNewDateTime(response);
		
		content = "{\"token\":\""+TOKEN+"\",\"datestamp\":\""+datestamp+"\"}";
		targetUrl = "http://challenge.code2040.org/api/validatetime";
		Registration.getPostInformation(targetUrl, content);
	}
	/**
	 * This method receives a dateTime object from the response and 
	 * adds to it the given interval in seconds before converting
	 * back to the specified ISO8601 dateTime format
	 * @param response JSON response from HTTP server
	 * @return a string of the datestamp
	 */
	private static String getNewDateTime(String response) {
		String result = null;
		try {
			JSONObject obj = new JSONObject(response).getJSONObject("result");
			String datestamp = obj.getString("datestamp");
			
			try {
				DateTimeFormatter iso = ISODateTimeFormat.dateTime();
				DateTime date = new DateTime(datestamp);
				int interval = obj.getInt("interval");
				DateTime summedDate = date.plusSeconds(interval);
				result = summedDate.toString(iso);
			} catch (NumberFormatException nfe) {
				System.out.println("Number Format Exception Error");
			}
		} catch (JSONException ex ) {
			System.out.println("JSON Object Error");
		} 
		return result;
	}
}

