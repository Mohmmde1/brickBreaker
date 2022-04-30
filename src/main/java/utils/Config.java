package utils;

import game.Player;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.Map;

// import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

@SuppressWarnings("unchecked")
public final class Config
{
    public static final String filename = "config.json";
    private static final int EXIT_ON_CLOSE = 3;
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
        windowProps.put("width", 800);
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
