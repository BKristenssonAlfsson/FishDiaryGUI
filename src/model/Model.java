package model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Model {

    private final String url = "http://localhost:8080/fishdiary";
    private final String user = "/user";
    private final String fish = "/fish";
    private final String diary = "/diary";
    private JSONArray jsonArray;
    private JSONObject jsonObject;


    public List<String> getAllUsers() {

        List<String> users = new ArrayList<>();

        try {
            jsonArray = new JSONArray(getData(url+user));
        } catch ( Exception e ) {
            e.printStackTrace();
        }

        for(int i = 0 ; i < jsonArray.length() ; i++){
            jsonObject = jsonArray.getJSONObject(i);
            String user = jsonObject.getString("username");
            users.add(user);
        }

        return users;
    }

    public Map<Integer, Fish> loadFishes(String user){

        Map<Integer, Fish> fishes = new HashMap<>();

        try {
            jsonObject = new JSONObject(getData(url + diary + "/" + user));
        } catch ( Exception e ) {
            e.printStackTrace();
        }

        jsonArray = jsonObject.getJSONArray("catches");
        JSONObject obj;

        for (int i = 0; i < jsonArray.length(); i++) {
            obj = jsonArray.getJSONObject(i);
            Fish fish = new Fish(i, obj.getString("name"), obj.getDouble("length"), obj.getDouble("weight"));
            fishes.put(i+1, fish);
        }

        return fishes;
    }

    private String getData(String url) throws Exception {

        URL server = new URL(url);
        URLConnection connection = server.openConnection();

        BufferedReader in = new BufferedReader( new InputStreamReader(connection.getInputStream(),"UTF8"));

        StringBuilder response = new StringBuilder();
        String inputLine;

        while ((inputLine = in.readLine()) != null)
            response.append(inputLine);

        in.close();

        return response.toString();
    }
}
