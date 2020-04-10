package company.vanolincikus;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import java.lang.reflect.Type;

public class LocalDatabase {

    public String database_file_location = "/Users/viktortodorov/Desktop/Database.txt";
    public Gson gson = new Gson();

    //INSERT INTO DATABASE
    public void insertIntoDatabase(String myData) {
        File txtFile = new File(database_file_location);
        if (!txtFile.exists()) {
            try {
                File directory = new File(txtFile.getParent());
                if (!directory.exists()) {
                    directory.mkdirs();
                }
                txtFile.createNewFile();
            } catch (IOException e) {
                log("Excepton Occured: " + e.toString());
            }
        }

        try {
            txtFile.delete();

            // Convenience class for writing character files
            FileWriter academyWriter;
            academyWriter = new FileWriter(txtFile.getAbsoluteFile(), true);

            // Writes text to a character-output stream
            BufferedWriter bufferWriter = new BufferedWriter(academyWriter);
            bufferWriter.write(myData.toString());
            bufferWriter.close();

            log("Sandwitches saved at file location: " + database_file_location + " Data: " + myData + "\n");
        } catch (IOException e) {
            log("Hmm.. Got an error while saving Sandwitch data to file " + e.toString());
        }
    }


    // READ FROM DATABASE
    public ArrayList<Sandwich> readDataFromDatabase() {
        File txtFile = new File(database_file_location);
        if (!txtFile.exists())
            log("File doesn't exist");

        InputStreamReader isReader;
        try {
            isReader = new InputStreamReader(new FileInputStream(txtFile), "UTF-8");

            JsonReader myReader = new JsonReader(isReader);

            Type listType = new TypeToken<ArrayList<Sandwich>>() {}.getType();
            ArrayList<Sandwich> sandwiches = new Gson().fromJson(myReader, listType);

            log("\nComapny Data loaded successfully from file " + database_file_location);
            return sandwiches;

        } catch (Exception e) {
            log("error load cache from file " + e.toString());
            return null;
        }
    }
//
//	//REMOVE USER FROM DB
//	public void removeUser(Waitress a) {
//		File txtFile = new File(database_file_location);
//		if (!txtFile.exists())
//			log("File doesn't exist");
//
//		InputStreamReader isReader;
//		try {
//			isReader = new InputStreamReader(new FileInputStream(txtFile), "UTF-8");
//
//			JsonReader myReader = new JsonReader(isReader);
//
//		   Type listType = new TypeToken<ArrayList<Waitress>>() {}.getType();
//           ArrayList<Waitress> users = new Gson().fromJson(myReader, listType);
//
//           for (Waitress user : users) {
//        	  if(user.name.equals(a.name) && user.pinCode.equals(a.pinCode)) {
//        		  users.remove(user);
//        		  insertIntoDatabase(gson.toJson(users));
//        		  log("USER: " + user.name + " " + user.pinCode + " removed from database");
//        		  break;
//        	  }
//           }
//
//
//		} catch (Exception e) {
//			log("error load cache from file " + e.toString());
//
//		}
//
//	}

    public void log(String string) {
        System.out.println(string);
    }

}