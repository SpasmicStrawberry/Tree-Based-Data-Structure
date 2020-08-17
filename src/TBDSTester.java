import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.ArrayList;
public class TBDSTester {
  public static void main(String[] args){
    HashSet<String> words = getWords();

    TBDSInterface tbds = new TBDS();

    //Add all words to tbds
    for(String word: words){
      tbds.add(word, word);
    }

    int countErrors = 0;
    int totalErrors = 0;

    //Check that the tbds contains all words
    for(String s: words){
      if(!tbds.containsKey(s)){
        countErrors++;
      }
    }
    System.out.println("Number of missed keys after searching all words: " + countErrors);

    totalErrors += countErrors;
    countErrors = 0;

    //Check that the correct value is associated with each key
    for(String s: words){
      if(!tbds.containsKey(s) || !tbds.get(s).equals(s)){
        countErrors++;
      }
    }
    System.out.println("Number of incorrect values for keys: " + countErrors);

    totalErrors += countErrors;
    countErrors = 0;


    //Check the values returned for a number of prefix values
    //Compares the returned result to the true result
    String[] searchPrefixes = {"add", "bril", "cri", "da", "enl", "lor", "mar", "perfection", "rit", "sim", "tra", "una", "ze"};
    for(String prefix: searchPrefixes){
      System.out.println("Getting values for prefix: " + prefix);
      ArrayList<String> realResult = matchPrefix(prefix, words);
      ArrayList<String> yourResult = tbds.getKeysForPrefix(prefix);
      System.out.println("Result should contain: " + realResult);
      System.out.println("   Your TBDS returned: " + yourResult);
      countErrors = countDifference(realResult, yourResult);
      totalErrors += countErrors;
      System.out.println("Error Count: " + countErrors);
    }
    System.out.println("Total Errors: " + totalErrors);
  }

  public static int countDifference(ArrayList<String> realResult, ArrayList<String> yourResult){
    int result = 0;
    for(String s: realResult){
      if(!yourResult.contains(s)){
        result++;
      }
    }

    for(Object s: yourResult){
      if(!realResult.contains((String)s)){
        result++;
      }
    }
    return result;
  }

  public static ArrayList<String> matchPrefix(String prefix, HashSet<String> words){
    ArrayList<String> result = new ArrayList<String>();

    for(String s: words){
      if(s.startsWith(prefix)){
        result.add(s);
      }
    }
    return result;
  }


  public static HashSet<String> getWords(){
    HashSet<String> words = new HashSet<String>();
    try {
      BufferedReader in = new BufferedReader(new FileReader("words.txt"));
      while(in.ready()){
        words.add(in.readLine());
      }
      in.close();
    }
    catch (FileNotFoundException e){

    }
    catch (IOException e){

    }
    return words;
  }
}