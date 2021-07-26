package panterix.CaptionGeneratorJavaAPI;
import java.util.ArrayList;

public class GetCaption {
	private static String cachedPassword;
	private static ArrayList<String> captions = new ArrayList<String>();
	
	// Returns a random caption from the list of captions
	public static String get() {
		double rand = Math.random() * captions.size();
		int index = (int)rand;
		return captions.get(index);
	}
	
	// Adds a caption if the password is correct
	public static boolean add(String caption, String password) {
		if (checkPassword(password)) {
			// If caption is not a duplicate, add it to the list
			if (notADuplicate(caption)) {
				captions.add(caption);
				return true;
			}
			return false;
		}
		
		return false;
	}
	
	// Checks and makes sure the input is not a duplicate
	private static boolean notADuplicate(String caption) {
		for (String cap : captions) {
			if (cap.compareTo(caption) == 0) {
				return false;
			}
		}
		return true;
	}

	// Updates the list of captions from the data set
	public static int update() {
		captions.clear();
		
		return captions.size();
	}
	
	// Updates the password into the local cache
	public static void updatePassword() {
		cachedPassword = PasswordGetter.password;
	}
	
	// Checks the make sure the password is correct
	private static boolean checkPassword(String password) {
		return (password.compareTo(cachedPassword) == 0);
	}
}
