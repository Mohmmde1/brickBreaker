package network;

import game.Player;
import utils.Config;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.io.FileInputStream;

import java.net.URL;
import java.net.URLConnection;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import org.json.simple.parser.ParseException;
import java.io.FileNotFoundException;
import java.io.IOException;
@SuppressWarnings("deprecation")
public class Firebase {
    static final String collection = "users";
    static String document;

    public static boolean initialize() throws IOException, ParseException
    {
        try {
            String filepath = Config.dotenv.get("BASE_DIR") + Config.dotenv.get("GOOGLE_APPLICATION_CREDENTIALS");

            // Check connectivity
            URL url = new URL("http://www.google.com");
            URLConnection connection = url.openConnection();
            connection.connect();

            // Firebase initialization
            FileInputStream serviceAccount = new FileInputStream(filepath);
            FirebaseOptions options = new FirebaseOptions.Builder().setCredentials(GoogleCredentials.fromStream(serviceAccount)).build();
            FirebaseApp.initializeApp(options);

            return true;
        } catch (IOException e) { 
            final boolean exists = Config.playerInfo() != null;
            Player.connected = false;

            if(exists) {
                Player.uuid = (String) Config.playerInfo().get("uuid");
                Config.updatePlayer(exists); 
                return false;
            }
    
            Config.updatePlayer(exists); 
            Player.uuid = UUID.randomUUID().toString(); 

            return false;
        }
    }

    public static Firestore db() {  
        Firestore db = FirestoreClient.getFirestore();
        return db;
    }

    private static void genDocument() throws FileNotFoundException, IOException, ParseException {
        final boolean exists = Config.playerInfo() != null;
        if(exists) {
            Player.uuid = (String) Config.playerInfo().get("uuid");
            Config.updatePlayer(exists); 
            document = Player.uuid;
            return;
        }

        Config.updatePlayer(exists); 
        Player.uuid = UUID.randomUUID().toString(); 
        document = Player.uuid;
    }

    public static void uploadPlayerInfo() throws InterruptedException, ExecutionException, FileNotFoundException, IOException, ParseException {
        genDocument();
        DocumentReference ref = db().collection(collection).document(document);
        
        Map<String, Object> player = new HashMap<>();
        player.put("uuid", Player.uuid);
        player.put("name", Player.name);
        player.put("highestScore", Player.highestScore);
        player.put("lastUpdated", Player.lastUpdated);

        // Asynchronously write data
        ApiFuture<WriteResult> result = ref.set(player);

        // result.get() blocks on response
    }
}
