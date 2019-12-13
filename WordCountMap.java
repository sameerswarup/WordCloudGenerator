import java.util.*;
import java.lang.*;
import java.io.File;
import java.io.FileNotFoundException;
/**
* @author Nacho Rodriguez
* @author Sameer Swarup
*/

public class WordCountMap{
    private Node root;
  private class Node {
    private String word;
    private int count;
    private Node left;
    private Node right;

    /**
    * Constructor for a node type object
    * @param word The word stored in the node
    */
    private Node(String word){
      this.word = word;
      count = 0;
      left = null;
      right = null;
    }
  }


  //Constructor for a WordCountMap
  public WordCountMap(){
    root = null;
  }

  /**
  * Constructor for a WordCountMap
  * @param Node The root node
  */
  public WordCountMap(Node node){
    root = node;

  }
  /**
  *Method to insert node into BST
  *@param Word The word to be inserted into BST
  */
  public void insert(String word){
      root = addHelper(root, word);
  }
  /**
  *Helper method for insert
  *@param Node The node to be inserted
  *@param Word The word stored in the node to be inserted
  */
  private Node addHelper(Node n, String word){
    if (n == null){
      return new Node(word);
    }else{
      if (word.compareTo(n.word) > 0){
        n.right = addHelper(n.right, word);
        return n;
      }else if (word.compareTo(n.word) < 0){
        n.left = addHelper(n.left, word);
        return n;
      }else{
        n.word = word;
        return n;
      }
    }

  }

  /**
   * If the specified word is already in this WordCountMap, then its
   * count is increased by one. Otherwise, the word is added to this map
   * with a count of 1.
   *@param Word The word whose count is incremented by 1.
   */
  public void incrementCount(String word){
      if (root == null){
        insert(word);
        root.count++;
      } else{
          incrementCountHelper(root, word);
      }
  }
  /**
  *Helper method for incrementCount
  *@param Node The root node to traverse BST in order.
  *@param targetWord The target word to increment count
  */
  private void incrementCountHelper(Node n, String targetWord){
    if(n == null){
      WordCount newItem = new WordCount(targetWord, 1);
    }else{
      incrementCountHelper(n.left, targetWord);
      if(n.word.equals(targetWord)){
        n.count++;
      }
      incrementCountHelper(n.right, targetWord);
    }
  }

  /**
   * Returns an array list of WordCount objects, one per word stored in this
   * WordCountMap, sorted in decreasing order by count.
   */
  public ArrayList<WordCount> getWordCountsByCount() {
    ArrayList<WordCount> freqOrder = new ArrayList<WordCount>();
    getWordCountsByCountHelper(root, freqOrder);
    ArrayList<WordCount> quickSortFreqOrder = quickSort(freqOrder);
    return quickSortFreqOrder;

  }
  /**
  *Method to sort Array List by quick sort.
  *@param freqOrder The WordCount Array List to be sorted.
  */
  private ArrayList<WordCount> quickSort(ArrayList<WordCount> freqOrder){
    if (freqOrder.isEmpty()){
      return freqOrder;
    }
    ArrayList<WordCount> sorted;
    ArrayList<WordCount> smallerFreqOrder = new ArrayList<WordCount>();
    ArrayList<WordCount> greaterFreqOrder = new ArrayList<WordCount>();
    WordCount pivot = freqOrder.get(0);
    WordCount j;
      for (int i = 1; i < freqOrder.size(); i++){
        j = freqOrder.get(i);
        if (j.compareTo(pivot) < 0){
          smallerFreqOrder.add(j);
        }else{
          greaterFreqOrder.add(j);
      }
    }
    smallerFreqOrder = quickSort(smallerFreqOrder);
    greaterFreqOrder = quickSort(greaterFreqOrder);
    greaterFreqOrder.add(pivot);
    greaterFreqOrder.addAll(smallerFreqOrder);
    sorted = greaterFreqOrder;
    return sorted;
  }
  /**
  *Helper method for getWordCountsByCount
  *@param n The root node to help traverse BST in order.
  @param freqOrder The WordCount Array List to be populated.
  */
  private void getWordCountsByCountHelper(Node n, ArrayList<WordCount> freqOrder){
    if (n == null){
      return;
    }else{
      getWordCountsByCountHelper(n.left, freqOrder);
      WordCount wc = new WordCount(n.word, n.count);
      freqOrder.add(wc);
      getWordCountsByCountHelper(n.right, freqOrder);
    }
  }

 /**
  * Returns a list of WordCount objects, one per word stored in this
  * WordCountMap, sorted alphabetically by word.
  */

  public ArrayList<WordCount> getWordCountsByWord(){
    ArrayList<WordCount> alphaOrder = new ArrayList<WordCount>();
    getWordCountsByWordHelper(root, alphaOrder);
    return alphaOrder;

  }
  /**
  *Helper method for getWordCountsByWord
  *@param n The root node to help traverse BST in order.
  *@param alphaOrder The WordCount Array List to be populated.
  */
  private void getWordCountsByWordHelper(Node n, ArrayList<WordCount> alphaOrder) {
    if (n == null){
      return;
    }else{
      getWordCountsByWordHelper(n.left, alphaOrder);
      WordCount wc = new WordCount(n.word, n.count);
      alphaOrder.add(wc);
      getWordCountsByWordHelper(n.right, alphaOrder);
    }

  }
}
