package com.sf.it.hackathon.breakingbot.cdchatbot;

import java.security.KeyStore.Entry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CategoryDetector {

	//enum Categories {JENKINS,ApexUnit,Saunce_Connect,iPromote, Maven, Nexus, DevOps_best_practices,CD_HYD_TEAM}
	
	static Map<String,List<String>> categoryToPatternsMap = new HashMap<String,List<String>>();
	
	static{
		
		buildcategoryMap();
	}
	
	public static String detectCategory(String sInput){
		
		for(Map.Entry<String, List<String>> categoryToPatternsPair : categoryToPatternsMap.entrySet()){
			for(String str: categoryToPatternsPair.getValue()){
				if(sInput.contains(str))
					return categoryToPatternsPair.getKey();
			}
		}
		return null; // TBD: Implement fallback case. Check if the context already exists, return that context. Implement context before this.
	}
	
	static void buildcategoryMap(){
		
		// TBD: Add more categories here.
		categoryToPatternsMap.put("jenkins", new ArrayList<String>());
		categoryToPatternsMap.get("jenkins").add("jenkins");
		categoryToPatternsMap.get("jenkins").add("jenkin");
		categoryToPatternsMap.get("jenkins").add("jenkins server");
		categoryToPatternsMap.get("jenkins").add("jenkins build server");
		
		categoryToPatternsMap.put("apexunit", new ArrayList<String>());
		categoryToPatternsMap.get("apexunit").add("apexunit");
		categoryToPatternsMap.get("apexunit").add("apex unit");
		categoryToPatternsMap.get("apexunit").add("apexunit framework");
		
	}
}
