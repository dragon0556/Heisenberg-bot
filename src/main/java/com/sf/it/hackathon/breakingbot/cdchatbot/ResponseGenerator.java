package com.sf.it.hackathon.breakingbot.cdchatbot;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.sf.it.hackathon.breakingbot.knowledge.KnowledgeBuilder;
import com.sf.it.hackathon.breakingbot.textmatching.DamerauLevenshtein;
import com.sf.it.hackathon.breakingbot.textmatching.TfIdfMain;
import com.sf.it.hackathon.breakingbot.utils.BreakingBotConstant;

public class ResponseGenerator {
	
	
	
	public JSONObject setInputCategory(String intent){
		String knowledgeFileName = null;
		String changedIntent = intent.trim().replace(" ", "_").toLowerCase().toLowerCase();
		JSONObject knowledge = null;;
			knowledgeFileName = changedIntent + "_knowledge.json";
        // TBD: implement other categories too		
		System.out.println("intent is "+ intent);
		System.out.println("knowledge file is "+ knowledgeFileName);
		try {
			knowledge = new KnowledgeBuilder().loadKnowledge(knowledgeFileName);
		} catch (IOException | JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return knowledge;
	}
	
	public String generateResponse(String sInput, JSONObject knowledge){
		String response = null;
		List<String>possibleResponses = new ArrayList<String>();
		if(knowledge == null) {
			System.out.println("knowledge is null");
			return BreakingBotConstant.DEFAULT_RESULT;
		}
		Iterator<?> keys = knowledge.keys();
		
		while(keys.hasNext()){
			String resp = (String)keys.next();
			resp = resp.replace('-', ' ');
			possibleResponses.add(resp);
		}
		
		// find the key with maximum match with user input
		int minMatch = Integer.MAX_VALUE;
		double maxMatch = Double.MIN_VALUE;
		
		Map<Double, List<String>> matchMap = new HashMap<>();
		List<String> valList = null;;
		for (String str : possibleResponses){
			double match = TfIdfMain.cosineSimilarity(str, sInput);
			valList = matchMap.get(match);
			if(valList == null){
				valList = new ArrayList<>();
				
			}
			valList.add(str);
			matchMap.put(match, valList);
			if (match > maxMatch){
				maxMatch = match;
			}
		}
		String responseMatch = useDLAlgo(matchMap.get(maxMatch), sInput);
	    
		
		try {
			response = (String)knowledge.get(responseMatch.replace(' ', '-').trim());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return response;
	}
	
	private String useDLAlgo(List<String> matchList, String sInput){
		String result = null;
		int minMatch = Integer.MAX_VALUE;
		for (String ele : matchList) {
			int match = DamerauLevenshtein.calculateDistance(ele,sInput);
			if(match < minMatch){
				minMatch = match;
				result = ele;
			}
		}
		
		return result;
	}
}
