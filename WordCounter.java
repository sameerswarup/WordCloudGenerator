import java.util.*;
import java.lang.*;
import java.io.File;
import java.io.FileNotFoundException;

public class WordCounter{


  public static void main(String[] args){
    if (args.length <= 1){
      System.err.println("Please input a file");
      System.exit(1);
    }
    String inputFilePath = args[1];
    File inputFile = new File(inputFilePath);
    File stopWords = new File("StopWords.txt");

    Scanner stop = null;
    try {
      stop = new Scanner(stopWords);
    }catch (FileNotFoundException e){
      System.err.println(e);
      System.exit(1);
    }

    Scanner scanner = null;
    try{
      scanner = new Scanner(inputFile);
    } catch (FileNotFoundException e){
        System.err.println(e);
        System.exit(1);
    }

    //Creates a String Array List which contains all of the stopwords
    ArrayList<String> stopArr = new ArrayList<String>(80);
    while (stop.hasNextLine()){
      String uselessWord = stop.nextLine();
      stopArr.add(uselessWord);
    }

    WordCountMap newMap = new WordCountMap();
      while (scanner.hasNext()){
        String nextWord = scanner.next();
        nextWord = nextWord.replaceAll("[^a-zA-Z]"," ");
        nextWord = nextWord.toLowerCase().trim().replaceAll("\\s{2,}", " ");
        int stopTotal = 0;
        for (int i = 0; i < stopArr.size(); i++){
          if (nextWord.equals(stopArr.get(i))){
            stopTotal++;
          }
      }
      if (stopTotal == 0){
        newMap.insert(nextWord);
        newMap.incrementCount(nextWord);
      }
  }

    if(args[0].equals("alphabetical")){
      ArrayList<WordCount> alphaOrder = new ArrayList<WordCount>(newMap.getWordCountsByWord());
      for(int i = 0; i < alphaOrder.size(); i++){
        System.out.println(alphaOrder.get(i).word + ": " + alphaOrder.get(i).count);
      }

    }else if (args[0].equals("frequency")){
      ArrayList<WordCount> freqOrder = new ArrayList<WordCount>(newMap.getWordCountsByCount());
      for(int i = 0; i < freqOrder.size(); i++){
        System.out.println(freqOrder.get(i).word + ": " + freqOrder.get(i).count);
    }

  }else if(args[0].equals("cloud")){
    ArrayList<WordCount> freqOrder = new ArrayList<WordCount>(newMap.getWordCountsByCount());
    List<WordCount> mostFrequent = new ArrayList<WordCount>();
    String number = args[2];
    int popular = Integer.parseInt(number);
    for (int i = 0; i < popular; i++){
      WordCount entry = freqOrder.get(i);
      mostFrequent.add(entry);
    }
    String htmlName = args[1];
    htmlName = htmlName.replaceAll(".txt", ".html");
    WordCloudMaker.createWordCloudHTML("Word Cloud", mostFrequent, htmlName);
    System.out.println(htmlName);
  }
 }
}
