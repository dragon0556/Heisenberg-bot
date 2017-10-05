/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sf.it.hackathon.breakingbot.textmatching;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 *
 * @author Mubin Shrestha
 */
public class TfIdfMain {
    
    /**
     * Main method
     * @param args
     * @throws FileNotFoundException
     * @throws IOException 
     */
  /*  public static void main(String args[]) throws FileNotFoundException, IOException
    {
        /*DocumentParser dp = new DocumentParser();
        dp.parseFiles("/Users/sayyar/Downloads/com/computergodzilla/tfidf/InputFiles/");
        dp.tfIdfCalculator(); //calculates tfidf
        dp.getCosineSimilarity(); //calculated cosine similarity  */ 
    //	System.out.println(cosineSimilarity("permissions have by default jenkin","url of production jenkin"));
    	//System.out.println(NGramsIntersectionMatching.findIntersectionScore("I need access to Jenkins", "Provide access to jenkins"));
  //  }*/
    
    
    /**
     * @param terms values to analyze
     * @return a map containing unique 
     * terms and their frequency
     */
    public static Map<String, Integer> getTermFrequencyMap(String[] terms) {
        Map<String, Integer> termFrequencyMap = new HashMap<>();
        for (String term : terms) {
            Integer n = termFrequencyMap.get(term);
            n = (n == null) ? 1 : ++n;
            termFrequencyMap.put(term, n);
        }
        return termFrequencyMap;
    }

    /**
     * @param text1 
     * @param text2 
     * @return cosine similarity of text1 and text2
     */
    public static double cosineSimilarity(String text1, String text2) {
        //Get vectors
        Map<String, Integer> a = getTermFrequencyMap(text1.split("\\W+"));
        Map<String, Integer> b = getTermFrequencyMap(text2.split("\\W+"));

        //Get unique words from both sequences
        HashSet<String> intersection = new HashSet<>(a.keySet());
        //intersection.retainAll(b.keySet());
        intersection.addAll(b.keySet());
        
        double dotProduct = 0, magnitudeA = 0, magnitudeB = 0;

        //Calculate dot product
        for (String item : intersection) {
        	if (a.get(item)==null)
        		dotProduct += 0 * b.get(item);
        	else if (b.get(item)==null)
        		dotProduct += a.get(item)*0;
        	else
        		dotProduct += a.get(item)*b.get(item);
        }

        //Calculate magnitude a
        for (String k : a.keySet()) {
            magnitudeA += Math.pow(a.get(k), 2);
        }

        //Calculate magnitude b
        for (String k : b.keySet()) {
            magnitudeB += Math.pow(b.get(k), 2);
        }

        //return cosine similarity
        return dotProduct / Math.sqrt(magnitudeA * magnitudeB);
    }
}
