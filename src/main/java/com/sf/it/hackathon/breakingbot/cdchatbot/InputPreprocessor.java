package com.sf.it.hackathon.breakingbot.cdchatbot;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.sf.it.hackathon.breakingbot.nlp.*;
import com.sf.it.hackathon.breakingbot.stanfordnlp.*;

public class InputPreprocessor {
	
	String sentDetectorModelFile;
	String posTaggerModelFile;
	String tokenizerModelFile;
	
	final static String delim = "?!.";

	private static InputPreprocessor processor = null;
	private InputPreprocessor(){
		
	}
	
	public static InputPreprocessor getPreprocessor(){
		if (processor == null)
			processor = new InputPreprocessor();
		return processor;
	}
	
	public String[] extractSentenses(String input){
		
		String[] result = null;
		SentenceDetector sentDetect = new SentenceDetector(sentDetectorModelFile);
		result = sentDetect.getSentences(input);
		
		// TBD: Implement lexical analysis to figure out context in two sentences.
		if(result.length > 1) System.out.println("Hey as of now I can only understand one sentence. Please hold on for sometime, my team is working on making me more intelligent");
		return result;
	}
	
	
	// removes punctuation and redundant
	// spaces from the user's input
	public static String cleanString(String str) {
		StringBuffer temp = new StringBuffer(str.length());
		char prevChar = 0;
		for(int i = 0; i< str.length(); ++i) {
			if((str.charAt(i) == ' ' && prevChar == ' ' ) || !isPunc(str.charAt(i))) {
				temp.append(str.charAt(i));
				prevChar = str.charAt(i);
			}
			else if(prevChar != ' ' && isPunc(str.charAt(i))){
				temp.append(' ');
			}
		}
			return temp.toString();
	}
	
	public static String lemmatizeInput(String sInput){
		
		StringBuilder result = new StringBuilder();
		StanfordLemmatizer lemmatizer = new StanfordLemmatizer();
		List<String> resultList = lemmatizer.lemmatize(sInput);
		for (String str : resultList)
			result.append(str+" ");
		return result.toString().trim();
	}
	
	public static String removeStopWords(String[] sInput) throws IOException{
		
		int k=0;
		List<String> resultList = new ArrayList<String>();
		BufferedReader br = null;
		String sCurrentLine;
		StringBuilder result = new StringBuilder();
		String[] stopwords = new String[2000];
		boolean add = true;
		try{
		        FileReader fr=new FileReader("StopWords.txt");
		        br= new BufferedReader(fr);
		        while ((sCurrentLine = br.readLine()) != null){
		            stopwords[k]=sCurrentLine;
		            k++;
		        }
		        for(int ii = 0; ii < sInput.length; ii++){
		            for(int jj = 0; jj < k; jj++){
		                if(stopwords[jj].equals((sInput[ii].toLowerCase()))){
		                	add=false;
		                    break;
		                }
		             }
		            if(add){resultList.add(sInput[ii]);}
		            add=true;
		        }
		        
                	
		        for (String str : resultList){
		            result.append(str+" ");
		        }   
		    }catch(Exception ex){
		        System.out.println(ex);
		    }
		finally{
			br.close();
		}
		return result.toString().trim();
	}
	
	public static String detectCategory(String sInput){
		return CategoryDetector.detectCategory(sInput);
	}
	
	static boolean isPunc(char ch) {
			return delim.indexOf(ch) != -1;
	}
	
	public void setSentDetectorModel(String modelFile){
		this.sentDetectorModelFile=modelFile;
	}
	
	public void setposTaggerModel(String modelFile){
		this.posTaggerModelFile = modelFile;
	}
	
	public void setTokenizerModel(String modelFile){
		this.tokenizerModelFile = modelFile;
	}
	
	
}
