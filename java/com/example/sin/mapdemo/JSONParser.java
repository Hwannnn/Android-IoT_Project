package com.example.sin.mapdemo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JSONParser {

    public String[][] mapListParserList(String pRecvServerPage){
        try{
            JSONObject json = new JSONObject(pRecvServerPage);
            JSONArray jArr = json.getJSONArray("maplist");

            String[] jsonName = {"project_no","lat","lng","addrDetail"};
            String[][] parseredData = new String[jArr.length()][jsonName.length];
            for(int i = 0; i<jArr.length();i++){
                json = jArr.getJSONObject(i);
                for (int j=0;j<jsonName.length; j++){
                    parseredData[i][j] = json.getString(jsonName[j]);
                }

            }

            return parseredData;

        }catch (JSONException e){
            e.printStackTrace();
            return null;
        }
    }

    public String[][] mapInfoParserList(String pRecvServerPage){
        try{
            JSONObject json = new JSONObject(pRecvServerPage);
            JSONArray jArr = json.getJSONArray("mapinfo");

            String[] jsonName = {"lot","roadFullAddr","addrDetail","startTime","endTime","cam","camnum","message"};
            String[][] parseredData = new String[jArr.length()][jsonName.length];
            for(int i = 0; i<jArr.length();i++){
                json = jArr.getJSONObject(i);
                for (int j=0;j<jsonName.length; j++){
                    parseredData[i][j] = json.getString(jsonName[j]);
                }

            }

            return parseredData;

        }catch (JSONException e){
            e.printStackTrace();
            return null;
        }
    }
}
