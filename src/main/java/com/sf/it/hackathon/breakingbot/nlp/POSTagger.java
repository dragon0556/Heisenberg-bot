package com.sf.it.hackathon.breakingbot.nlp;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.tokenize.WhitespaceTokenizer;


public class POSTagger {
	
	static POSModel model = null;
	POSTagger posInstance = null;
	static POSTaggerME tagger = null;
	public static void removeQuestionWords(String str){
		// Load POS Model
		try {
            InputStream modelStream =  new FileInputStream("en-pos-maxent.bin");
            model = new POSModel(modelStream);
            tagger = new POSTaggerME(model);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
		
		try {
			if (model != null) {
				if (tagger != null) {
					// Call Sentence Detector
					String whitespaceTokenizerLine[] = WhitespaceTokenizer.INSTANCE.tokenize(str);
						String[] tags = tagger.tag(whitespaceTokenizerLine);
						for (int i = 0; i < whitespaceTokenizerLine.length; i++) {
							String word = whitespaceTokenizerLine[i].trim();
							String tag = tags[i].trim();
							System.out.println(word + ":" + tag);
						}
					}
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*public static void main(String args[]) throws FileNotFoundException, IOException
    {		
		removeQuestionWords("How is the process to get access to Jenkins");
    }*/
}
