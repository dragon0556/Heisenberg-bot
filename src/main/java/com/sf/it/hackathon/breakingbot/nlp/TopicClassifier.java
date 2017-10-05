package com.sf.it.hackathon.breakingbot.nlp;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import opennlp.tools.doccat.DoccatFactory;
import opennlp.tools.doccat.DoccatModel;
import opennlp.tools.doccat.DocumentCategorizerME;
import opennlp.tools.doccat.DocumentSample;
import opennlp.tools.doccat.DocumentSampleStream;
import opennlp.tools.ml.naivebayes.NaiveBayesTrainer;
import opennlp.tools.util.InputStreamFactory;
import opennlp.tools.util.MarkableFileInputStreamFactory;
import opennlp.tools.util.ObjectStream;
import opennlp.tools.util.PlainTextByLineStream;
import opennlp.tools.util.TrainingParameters;

public class TopicClassifier {
	DoccatModel model;

	public void trainModel(String inputFile, String modelFile){
		
		try{
			
			MarkableFileInputStreamFactory factory = new MarkableFileInputStreamFactory(new File(inputFile));
			ObjectStream<String> lineStream = new PlainTextByLineStream(
				     factory, "UTF-8");
			ObjectStream<DocumentSample> sampleStream = new DocumentSampleStream(lineStream);
			TrainingParameters params = new TrainingParameters();
			  params.put(TrainingParameters.CUTOFF_PARAM, Integer.toString(0));
			  params.put(TrainingParameters.ALGORITHM_PARAM, NaiveBayesTrainer.NAIVE_BAYES_VALUE);
			// Specifies the minimum number of times a feature must be seen
			model = DocumentCategorizerME.train("en", sampleStream, params,//TrainingParameters.defaultParams(),
					new DoccatFactory());
			
			OutputStream modelOut = null;
			File modelFileTmp = new File(modelFile);
			modelOut = new BufferedOutputStream(new FileOutputStream(modelFileTmp));
			model.serialize(modelOut);
		
		}catch (IOException e){
			e.printStackTrace();
		}
		
	}

}
