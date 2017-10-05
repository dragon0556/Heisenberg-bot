package com.sf.it.hackathon.breakingbot.cdchatbot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import org.json.JSONException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.sf.it.hackathon.breakingbot.knowledge.KnowledgeBuilder;
import com.sf.it.hackathon.breakingbot.knowledge.PreprocessingUtil;

@Component
public class ChatbotFacade {
	
	private static String  	sInput = new String("");
	private static boolean	bQuitProgram = false;
	Context currentContext = new Context();
	ResponseGenerator responseGen = new ResponseGenerator();
	InputPreprocessor preProc = null;
	
	public String start(String input, String intent){
		System.out.println("Chatbot Copyright (C) Salesforce\n");
		String result = null;
		try {
			//SignonHandler.signOn();
			preProc = InputPreprocessor.getPreprocessor();
			configurePreprocessor();
			sInput = input;
			result = preprocess_input(intent);
				//respond();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println("Facade: "+ result);
		return result;
	}
	
	public void get_input() throws Exception 
	{
		System.out.print(">");
		// saves the previous input
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		sInput = in.readLine();
		//preprocess_input();
	}
	
	public boolean quit(){
		return bQuitProgram==true;
	}
	
	public void respond(){
		
	}
	
	void configurePreprocessor(){
		preProc.setSentDetectorModel("en-sent.bin");
		preProc.setTokenizerModel("en-token.bin");
		preProc.setposTaggerModel("en-pos-maxent.bin");
	}
	
	private String preprocess_input(String intent){
		
		String[] sentenses = preProc.extractSentenses(sInput);
		
		// TBD: Handle multiple sentences scenario
		sInput = InputPreprocessor.cleanString(sentenses[0]); 
		sInput = sInput.toLowerCase();
		
		sInput = InputPreprocessor.lemmatizeInput(sInput);
		
		try {
			sInput = InputPreprocessor.removeStopWords(sInput.split(" "));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		//String category = InputPreprocessor.detectCategory(sInput);
		
		
		if (!StringUtils.isEmpty(intent)){
			responseGen.setInputCategory(intent);
		}
		else{ // TBD: Chatbot is rude. Make him decent.
			System.out.println("I dont know man what the hell you are talking about. I can only help you with CD team stuff.");
		}
		return responseGen.generateResponse(sInput);
	}
}
