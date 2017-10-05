package com.sf.it.hackathon.breakingbot.textmatching;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NGramsIntersectionMatching {

	public static int findIntersectionScore(String str1,String str2){
		List<String> str1Grams = generateNGrams(str1, 2, 3);
		List<String> str2Grams = generateNGrams(str2,2,3);
		Set<String> intersectionOfGrams = new HashSet<String>(str1Grams);
		intersectionOfGrams.addAll(str2Grams);
		return intersectionOfGrams.size();
	}
	
	public static List<String> generateNGrams(String str1, int minSize, int maxSize){
		List<String> result = new ArrayList<String>();
		
		for (int i=minSize;i<=maxSize;i++){
			result.addAll(ngrams(i,str1));
		}
		return result;
	}
	public static List<String> ngrams(int n, String str) {
        List<String> ngrams = new ArrayList<String>();
        String[] words = str.split(" ");
        for (int i = 0; i < words.length - n + 1; i++)
            ngrams.add(concat(words, i, i+n));
        return ngrams;
    }

    public static String concat(String[] words, int start, int end) {
        StringBuilder sb = new StringBuilder();
        for (int i = start; i < end; i++)
            sb.append((i > start ? " " : "") + words[i]);
        return sb.toString();
    }
 }
