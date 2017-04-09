package com.example.sin.mapdemo;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;


public class ServerConnect {

    protected String sendByHttp(double lat, double lng) {
        String URL = "http://117.17.187.225:12345/adminWeb/appmap/appmaplist.do";
        DefaultHttpClient client = new DefaultHttpClient();
        try{
            HttpPost post = new HttpPost(URL + "?lat=" + lat + "&lng=" + lng);

            HttpResponse response = client.execute(post);

            BufferedReader bufreader = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "utf-8"));

            String line = null;
            String result = "";

            while ((line = bufreader.readLine()) != null) {
                result += line;
            }

            return result;

        } catch (Exception e) {
            e.printStackTrace();
            client.getConnectionManager().shutdown();

            return "connect fail";
        }
    }

    protected String sendByHttp(int no) {
        String URL = "http://117.17.187.225:12345/adminWeb/appmap/mapinfo.do";
        DefaultHttpClient client = new DefaultHttpClient();
        try{
            HttpPost post = new HttpPost(URL + "?project_no=" + no);

            HttpResponse response = client.execute(post);

            BufferedReader bufreader = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "utf-8"));

            String line = null;
            String result = "";

            while ((line = bufreader.readLine()) != null) {
                result += line;
            }

            return result;

        } catch (Exception e) {
            e.printStackTrace();
            client.getConnectionManager().shutdown();

            return "connect fail";
        }
    }

    public Bitmap bringImage(int project_no){
        try {
            URL url = new URL("http://117.17.187.225:12345/adminWeb/upload/" + project_no + "/" + project_no + "_" + "1.jpg");
            URLConnection conn = url.openConnection();
            conn.connect();
            BufferedInputStream bis = new BufferedInputStream(conn.getInputStream());
            Bitmap bm = BitmapFactory.decodeStream(bis);
            bis.close();
            return bm;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int availableLot(int project_no){
        try{
            String num = null;
            JSONObject json = new JSONObject("http://117.17.187.225:12345/adminWeb/upload/" + project_no + "/" + project_no + ".json");

            for(int i = 0; i<json.length();i++){
                num = json.getString("cam_num");
            }

            return Integer.parseInt(num);

        }catch (JSONException e){
            e.printStackTrace();
            return -1;
        }
    }

}
