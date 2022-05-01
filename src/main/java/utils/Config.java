package utils;

import game.Player;

import java.awt.Dimension;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Map;
import java.util.LinkedHashMap;

import static javax.swing.JFrame.EXIT_ON_CLOSE;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
// import org.json.simple.JSONArray;

@SuppressWarnings("unchecked")
public final class Config
{
    public static final String filename = "config.json";
    private static Map<String, Object> windowProps;
    private static Map<String, Object> playerInfo;
    /**
     * @param fileName
     * @throws FileNotFoundException
     */
    public static void writeJson(boolean update) throws FileNotFoundException {
        // Create a JSONObject
        JSONObject json = new JSONObject();

        // Populate data
        json.put("version", 0.1);

        windowProps = new LinkedHashMap<String, Object>(6);
        playerInfo = new LinkedHashMap<String, Object>(3);

        windowProps.put("title", "Block Breaker Reloaded");
        windowProps.put("width", 900);
        windowProps.put("height", 600);
        windowProps.put("resizable", true);
        windowProps.put("visibility", true);
        windowProps.put("OnClose", EXIT_ON_CLOSE);
        
        updatePlayer(update);

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
    public final static Dimension getWindowDimension() throws IOException, org.json.simple.parser.ParseException {
        // Parse json file
        Object obj = new JSONParser().parse(new FileReader(filename));

        // Cast to json object
        JSONObject json = (JSONObject) obj;

        // Get Window properties
        windowProps = ((Map<String, Object>)json.get("WindowProperties"));

        return new Dimension((int)(long)windowProps.get("width"), (int)(long)windowProps.get("height"));
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

    private static void updatePlayer(boolean update) {
        if (update) {
            playerInfo.put("name", Player.name);
            playerInfo.put("highestScore",Player.highestScore);
            playerInfo.put("lastUpdated", Player.lastUpdated());
        }
        else {
            playerInfo.put("name", "Guest");
            playerInfo.put("highestScore", 0);
            playerInfo.put("lastUpdated",  System.currentTimeMillis());
        }
    }
}
