package data;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class JsonRW {
    public String deviceFile;

    public JsonRW(String filePath) {
        this.deviceFile = filePath;
    }

//    public String readJSON(String keyElement) throws IOException {
//        JSONParser parser = new JSONParser();
//
//        try {
//            Object obj = parser.parse(new FileReader(deviceFile));
//            JSONObject jsonObject = (JSONObject) obj;
//            return String.valueOf(jsonObject.get(keyElement));
//
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//
//        return "";
//    }

    public Object readJson(String key) throws IOException {
        JSONParser parser = new JSONParser();
        try{
            Object obj = parser.parse(new FileReader(deviceFile));

            JSONObject jsonObject = (JSONObject) obj;

            JSONObject device = (JSONObject) jsonObject.get("devices");

            return device.get(key);


        }catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public void writeJSON(String keyElement, String value) throws IOException, ParseException {
        //JSONObject obj2 = new JSONObject();
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader(deviceFile));
        JSONObject jsonObject = (JSONObject) obj;
        jsonObject.remove(keyElement);
        jsonObject.put(keyElement, value);

        try (FileWriter file = new FileWriter(deviceFile)) {
            //File Writer creates a file in write mode at the given location
            file.write(jsonObject.toString());

            //write function is use to write in file,
            //here we write the Json object in the file
            file.flush();

        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }
}
