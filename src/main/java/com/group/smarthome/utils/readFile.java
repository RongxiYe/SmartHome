package com.group.smarthome.utils;

import org.json.JSONException;
import org.json.JSONStringer;
import org.json.JSONObject;
import org.springframework.core.io.ClassPathResource;

import java.io.*;
import java.util.ArrayList;


public class readFile {
    private static String getPath(){
        return "/root/light/light.json";
    }


    public static ArrayList<String> readJsonFile() {
        String filename = getPath();
        ArrayList<String> al = new ArrayList<>();
        try{
            BufferedReader br = new BufferedReader(new FileReader(filename));
            String s = "";
            String open = "";
            String brightness = "";
            while((s = br.readLine()) != null) {
                try {
                    JSONObject js = new JSONObject(s);
                    open = (String) js.get("open");
                    brightness = (String) js.get("brightness");
                    al.add(0, open);
                    al.add(1, brightness);
                } catch (JSONException e) {
                    al = null;
                }
            }
        }catch (IOException e){
            e.printStackTrace();
            al = null;
        }
        return al;
    }

    public static String changeLight(String open,String brightness){
        String filename = getPath();
        try{
            File file = new File(filename);
            System.out.println(file.getAbsolutePath());
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            JSONStringer js = new JSONStringer();
            js.object();
            js.key("open");
            js.value(open);
            js.key("brightness");
            js.value(brightness);
            js.endObject();
            String light = js.toString();
            bw.write(light);
            bw.flush();
            bw.close();
            System.out.println("true");
            return "true";
        }catch(IOException e){
            e.printStackTrace();
            return "false";
        }
    }
}
