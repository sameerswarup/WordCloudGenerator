import java.util.*;

public class WordCount implements Comparable<WordCount>{
  public String word;
  public int count;

  /**
  * Constructor for a WordCount object
  * @param word the word stored in the WordCount object
  * @param count the count stored in the WordCount object
  */
  public WordCount(String word, int count){
    this.word = word;
    this.count = count;
  }

  /**
  * Method which overrides compareTo of comparable interface
  * Compares the counts of two WordCount objects
  * @param comparedWord the word to compare against
  */
  public int compareTo(WordCount comparedWord){
    if(count == comparedWord.count){
      return 0;
    }else if(count < comparedWord.count){
      return -1;
    }else{
      return 1;
    }
  }

}
