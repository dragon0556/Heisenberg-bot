package com.sf.it.hackathon.breakingbot.utils;

import static java.nio.file.Files.readAllBytes;
import static java.nio.file.Paths.get;

import java.io.IOException;
import java.util.Objects;

public class FileUtils {
 /**
  * Get file data as string
  * 
  * @param fileName
  * @return
  */
 public static String getFileDataAsString(String fileName) {
  Objects.nonNull(fileName);
  try {
   String data = new String(readAllBytes(get(fileName)));
   return data;
  } catch (IOException e) {
   System.out.println(e.getMessage());
   return null;
  }
 }
}