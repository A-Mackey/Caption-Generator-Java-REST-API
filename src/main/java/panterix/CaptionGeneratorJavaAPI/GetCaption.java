package panterix.CaptionGeneratorJavaAPI;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class GetCaption {
	private static String captionFile = "./src/main/java/panterix/CaptionGeneratorJavaApi/captions.txt";
	private static ArrayList<String> captions = new ArrayList<String>();
	
	//Returns a random caption from the list of captions
	public static String get() {
		double rand = Math.random() * captions.size();
		int index = (int)rand;
		return captions.get(index);
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
}
