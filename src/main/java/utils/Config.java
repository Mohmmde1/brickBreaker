package utils;

import game.Player;

import static javax.swing.JFrame.EXIT_ON_CLOSE;
import java.awt.Dimension;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileReader;
import java.io.PrintWriter;

import java.util.Map;
import java.util.UUID;
import java.util.LinkedHashMap;


// import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import io.github.cdimascio.dotenv.Dotenv;

@SuppressWarnings("unchecked")
public final class Config
{
    public static Dotenv dotenv = Dotenv.load(); 
    public static final String filename = "config.json";
    private static JSONObject json;
    private static Map<String, Object> windowProps;
    private static Map<String, Object> playerInfo;

    /**
     * @param fileName
     * @throws FileNotFoundException
     */
    public static void writeJson() throws FileNotFoundException {
        windowProps.put("title", "Brick Breaker");
        windowProps.put("width", 900);
        windowProps.put("height", 600);
        windowProps.put("resizable", true);
        windowProps.put("visibility", true);
        windowProps.put("OnClose", EXIT_ON_CLOSE);
        
        json.put("WindowProperties", windowProps);
        json.put("PlayerInformation", playerInfo);

        // Write JSON to file "config.json"
        PrintWriter pw = new PrintWriter(filename);
        pw.write(json.toJSONString());

        pw.flush();
        pw.close();
    } 

    /**
     * @return Dimension
     * @throws IOException
     * @throws org.json.simple.parser.ParseException
     */
    public static Dimension getWindowDimension() throws IOException, org.json.simple.parser.ParseException {
        // Parse json file
        Object obj = new JSONParser().parse(new FileReader(filename));

        // Cast to json object
        JSONObject json = (JSONObject) obj;

        // Get Window properties
        windowProps = ((Map<String, Object>)json.get("WindowProperties"));

        return new Dimension((int)(long)windowProps.get("width"), (int)(long)windowProps.get("height"));
    }

    /**
     * @return boolean
     * @throws IOException
     * @throws org.json.simple.parser.ParseException
     */
    public final static boolean resizable() throws IOException, org.json.simple.parser.ParseException {
        // Parse json file
        Object obj = new JSONParser().parse(new FileReader(filename));

        // Cast to json object
        JSONObject json = (JSONObject) obj;

        // Get Window properties
        windowProps = ((Map<String, Object>)json.get("WindowProperties"));

        return (boolean)windowProps.get("resizable");
    }

    /**
     * @return String
     * @throws IOException
     * @throws org.json.simple.parser.ParseException
     */
    public final static String title() throws IOException, org.json.simple.parser.ParseException {
        // Parse json file
        Object obj = new JSONParser().parse(new FileReader(filename));

        // Cast to json object
        JSONObject json = (JSONObject) obj;

        // Get Window properties
        windowProps = ((Map<String, Object>)json.get("WindowProperties"));

        return (String)windowProps.get("title");
    }

    /**
     * @return String
     * @throws IOException
     * @throws org.json.simple.parser.ParseException
     */
    public final static String getVersion() throws IOException, org.json.simple.parser.ParseException {
        // Parse json file
        Object obj = new JSONParser().parse(new FileReader(filename));

        // Cast to json object
        JSONObject json = (JSONObject) obj;

        return Double.toString((double)json.get("version"));
    }

    public static void updatePlayer(boolean update) throws FileNotFoundException {
        // Create a JSONObject
        json = new JSONObject();

        // Populate data
        json.put("version", 0.1);

        windowProps = new LinkedHashMap<String, Object>(6);
        playerInfo = new LinkedHashMap<String, Object>(4);
        
        // Update time
        Player.setLastUpdated();

        if (update) {
            playerInfo.put("uuid", Player.uuid);
            playerInfo.put("name", Player.name);
            playerInfo.put("highestScore", Player.highestScore);
            playerInfo.put("lastUpdated", Player.lastUpdated);
        }
        else {
            Player.uuid = UUID.randomUUID().toString();
            playerInfo.put("uuid", Player.uuid);
            playerInfo.put("name", Player.name);
            playerInfo.put("highestScore", 0);
            playerInfo.put("lastUpdated",  Player.lastUpdated);
        }
        writeJson();
    }

    /**
     * @return Map<String, Object>
     * @throws FileNotFoundException
     * @throws IOException
     * @throws ParseException
     */
    public final static Map<String, Object> playerInfo() throws FileNotFoundException, IOException, ParseException {
        File file = new File((String)dotenv.get("BASE_DIR") + filename);
        System.out.println(file.exists());
        System.out.println((String)dotenv.get("BASE_DIR") + filename);
        
        if (file.exists() == false) return new LinkedHashMap<String, Object>();;
        
        // Parse json file
        Object obj = new JSONParser().parse(new FileReader(filename));

        // Cast to json object
        JSONObject json = (JSONObject) obj;

        // Get Window properties
        return ((Map<String, Object>)json.get("PlayerInformation"));
    }
}
