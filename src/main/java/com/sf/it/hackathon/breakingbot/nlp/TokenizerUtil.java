package com.sf.it.hackathon.breakingbot.nlp;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

import com.sf.it.hackathon.breakingbot.utils.FileUtils;

import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.tokenize.WhitespaceTokenizer;

public class TokenizerUtil {
 TokenizerModel model = null;
 Tokenizer learnableTokenizer = null;

 public TokenizerUtil(String modelFile) {
  initTokenizerModel(modelFile);
  learnableTokenizer = new TokenizerME(model);
 }

 private void initTokenizerModel(String modelFile) {
  Objects.nonNull(modelFile);

  InputStream modelIn = null;
  try {
   modelIn = new FileInputStream(modelFile);
  } catch (FileNotFoundException e) {
   System.out.println(e.getMessage());
   return;
  }

  try {
   model = new TokenizerModel(modelIn);
  } catch (IOException e) {
   e.printStackTrace();
  } finally {
   if (modelIn != null) {
    try {
     modelIn.close();
    } catch (IOException e) {
    }
   }
  }
 }

 public Tokenizer getLearnableTokenizer() {
  return learnableTokenizer;
 }

 public Tokenizer getWhitespaceTokenizer() {
  return WhitespaceTokenizer.INSTANCE;
 }

 public String[] tokenizeFileUsingLearnableTokenizer(String file) {
  String data = FileUtils.getFileDataAsString(file);
  return learnableTokenizer.tokenize(data);
 }

 public String[] tokenizeUsingLearnableTokenizer(String data) {
  return learnableTokenizer.tokenize(data);
 }

 public String[] tokenizeFileUsingWhiteSpaceTokenizer(String file) {
  String data = FileUtils.getFileDataAsString(file);
  return getWhitespaceTokenizer().tokenize(data);
 }

 public String[] tokenizeUsingWhiteSpaceTokenizer(String data) {
  return getWhitespaceTokenizer().tokenize(data);
 }
}