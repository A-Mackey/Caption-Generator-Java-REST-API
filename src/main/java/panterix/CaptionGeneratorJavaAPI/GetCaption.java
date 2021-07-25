package panterix.CaptionGeneratorJavaAPI;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Scanner;

public class GetCaption {
	private static String captionFile = "./src/main/java/panterix/CaptionGeneratorJavaApi/captions.txt";
	private static String passwordFile = "./src/main/java/panterix/CaptionGeneratorJavaApi/password.txt";
	private static String cachedPassword;
	private static ArrayList<String> captions = new ArrayList<String>();
	
	//Returns a random caption from the list of captions
	public static String get() {
		double rand = Math.random() * captions.size();
		int index = (int)rand;
		return captions.get(index);
	}
	
	public static boolean add(String caption, String password) {
		if (checkPassword(password)) {
			// If caption is not a duplicate, add it to the list
			if (notADuplicate(caption)) {
				captions.add(caption);
				caption += '\n';
				try {
		            Files.write(Paths.get(captionFile), caption.getBytes(), StandardOpenOption.APPEND);
		        } catch (IOException e) { return false; }
				return true;
			}
			return false;
		}
		
		return false;
	}
	
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
		
		// Updates the cache of captions by reading from file
		try {
		      File myObj = new File(captionFile);
		      Scanner myReader = new Scanner(myObj);
		      while (myReader.hasNextLine()) {
		        String data = myReader.nextLine();
		        captions.add(data);
		      }
		      myReader.close();
		    } catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		
		return captions.size();
	}
	
	public static void updatePassword() {
		try {
			File pass = new File(passwordFile);
			Scanner reader = new Scanner(pass);
			
			if (reader.hasNextLine()) {
				cachedPassword = reader.nextLine();
			}
			
			reader.close();
		} catch (Exception e) {}
	}
	
	private static boolean checkPassword(String password) {
		return (password.compareTo(cachedPassword) == 0);
	}
}
