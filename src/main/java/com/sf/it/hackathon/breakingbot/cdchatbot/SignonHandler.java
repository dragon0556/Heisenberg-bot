package com.sf.it.hackathon.breakingbot.cdchatbot;

import java.util.Random;

import org.json.JSONArray;
import org.json.JSONException;

import com.sf.it.hackathon.breakingbot.knowledge.KnowledgeBuilder;

public class SignonHandler {

	public static void signOn(){
		String signonMessage = null;
		try {
			 signonMessage = getSignonMessage();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Sierra: " + signonMessage);
	}
	
	static String getSignonMessage() throws JSONException{
		
		Random rand = new Random();
		JSONArray signonMessages = KnowledgeBuilder.knowledge.getJSONArray("signOnMessages");
		int index = rand.nextInt(signonMessages.length()-1);
		return signonMessages.getString(index);
	}
}
