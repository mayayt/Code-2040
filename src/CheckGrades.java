import java.io.IOException;

public class CheckGrades {
	
	private static final String TOKEN = "kwaD7Tul2y";

	public static void main(String[] args) throws IOException {

		String targetUrl = "http://challenge.code2040.org/api/status";
		String content = "{\"token\":\""+TOKEN+"\"}";
		
		Registration.getPostInformation(targetUrl, content);

	}
}

