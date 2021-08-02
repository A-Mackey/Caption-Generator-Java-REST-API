package panterix.CaptionGeneratorJavaAPI;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.CharBuffer;
import java.nio.file.Paths;
import java.util.ArrayList;

public class GetCaption {
	private static String currentPath = Paths.get(".").toAbsolutePath().normalize().toString();
	private static String database = currentPath + "/textFiles/captionDatabase.txt";
	
	private static FileReader in;
	
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
				
				try {
					FileWriter out = new FileWriter(database, true);
					out.write(caption + '\n');
					
					out.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
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
		
		readFromDatabase();
		
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
	
	public static String readFromDatabase() {
		String r = "";
		String all = "";
		try {
			in = new FileReader(database);
			
			int i;
			while((i = in.read()) != -1) {
				all += (char)i;
			}
			
			for(int o = 0; o < all.length(); o++) {
				if(all.charAt(o) == '\n' || o == all.length() - 1) {
					r = r.replaceAll("(\\r\\n|\\n|\\r)", "");
					//captions.add(r + (o == all.length() - 1 ? all.charAt(o) : ""));
					captions.add(r);
					r = "";
				} else {
					r += all.charAt(o);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("--------------");
		for(int o = 0; o < captions.size(); o++) {
			System.out.println(captions.get(o));
		}
		System.out.println("--------------");
		
		return r;
	}
	
	private static void clearDatabaseAndWriteCaptions() {
		
	}
}
