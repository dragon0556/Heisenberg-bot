package com.sf.it.hackathon.breakingbot.knowledge;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class KnowledgeBuilder {
	
	public static JSONObject knowledge;
	public static JSONObject loadKnowledge(String fileName) throws IOException, JSONException{
		
		File f = new File(fileName);
        if (f.exists()){
            InputStream is = new FileInputStream(fileName);
            @SuppressWarnings("deprecation")
			String jsonTxt = IOUtils.toString(is);
            //System.out.println(jsonTxt);
            knowledge = new JSONObject(jsonTxt);  
            
            //for (int i=0;i<topics.length();i++)
            	//System.out.println((JSONObject)topics.get(i));
        }
        return knowledge;
	}
}

