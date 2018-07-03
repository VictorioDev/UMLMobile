package zansavio.victorio.com.umlmobile.utils;

import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Victorio Zansavio on 01/07/2018.
 */

public class NetworkUtils {

    private final static String TAG = "App";
    public static String getJson(String stringUrl){
        String json = "";
        HttpURLConnection conn = null;
        try {
            URL url = new URL(stringUrl);
            conn = (HttpURLConnection) url.openConnection();
            //InputStream in = new BufferedInputStream(conn.getInputStream());
            InputStream in = conn.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            StringBuilder sb = new StringBuilder();
            String data;
            while ((data = br.readLine()) != null){
                sb.append(data);
            }
            json = sb.toString();
            in.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }

}
