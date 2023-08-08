package net.slimou.askyeknod;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Logger;

import static net.slimou.askyeknod.YeknodUtil.*;

@Service
public class YeknodService {

    private static final Logger logger = Logger.getLogger(YeknodService.class.getName());

    public String startRequest(String text) {
        logger.info(String.format("request %s", text));
        HttpURLConnection con = null;
        String response = "";
        try{
            con = (HttpURLConnection) new URL(API_URL).openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Authorization", "Bearer " + YEKNOD_KEY);

            JSONObject data = new JSONObject();
            data.put("model", MODEL);
            data.put("prompt", text);
            data.put("max_tokens", 10);
            data.put("temperature", 1.0);

            con.setDoOutput(true);
            con.getOutputStream().write(data.toString().getBytes());

            String output = new BufferedReader(new InputStreamReader(con.getInputStream())).lines()
                    .reduce((a, b) -> a + b).get();
            response = new JSONObject(output).getJSONArray("choices").getJSONObject(0).getString("text");
        }catch(Exception e){
            logger.info("Error in connection " + e.getStackTrace());
        }
        return response;
    }

}
