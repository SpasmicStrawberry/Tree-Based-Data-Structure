//Note: All of your TBDSInterface method implementations must function recursively
//I have left the method signatures from my own solution, which may be useful hints in how to approach the problem
//You are free to change/remove/etc. any of the methods, as long as your class still supports the TBDSInterface

import java.util.ArrayList;
import java.util.Map;

public class TBDS implements TBDSInterface {
  TBDSNode root;

  public TBDS() {
      root = new TBDSNode();
  }

  //Indirectly recursive method to meet definition of interface
  public void add(String key, String value) {
    add(root, key, value);
  }

  //Recursive method
  //Note: arguments are only a suggestion, you may use your own if you devise a different recursive solution
  public void add(TBDSNode current, String curKey, String value) {
    //if the key already exists
    if(current.getChildren().containsKey(curKey.charAt(0))) {
      //if we are at the last letter of the word
      if (curKey.length() == 1) {
        current.getChildren().get(curKey.charAt(0)).setValue(value);
        return;
      }
    }
    else {
      //create a new node
      TBDSNode newNode = new TBDSNode();
      //if we are at the last letter of the word, set the node to the value with the associated key
      if (curKey.length() == 1) {
        newNode.setValue(value);
        current.getChildren().put(curKey.charAt(0),newNode);
        return;
      }
      //Otherwise, set the node to null with the associated key
      newNode.setValue(null);
      current.getChildren().put(curKey.charAt(0), newNode);
    }
    //traverse through the rest of the key excluding the first letter
    add(current.getChildren().get(curKey.charAt(0)), curKey.substring(1), value);
  }

  //Indirectly recursive method to meet definition of interface
  public String get(String key) {
    return get(root, key);
  }

  //Recursive method
  //Note: arguments are only a suggestion, you may use your own if you devise a different recursive solution
  public String get(TBDSNode current, String curKey) {
    //if we have reached the end of the word
    if(curKey.length() == 1){
      return current.getChildren().get(curKey.charAt(0)).getValue();
    }
    return get(current.getChildren().get(curKey.charAt(0)), curKey.substring(1));
  }

  //Indirectly recursive method to meet definition of interface
  public boolean containsKey(String key) {
    return containsKey(root, key);
  }

  //Recursive method
  //Note: arguments are only a suggestion, you may use your own if you devise a different recursive solution
  public boolean containsKey(TBDSNode current, String curKey) {
    //if we have reached the end of the key
    if(curKey.length() == 1){
      //if the node contains no value
      if(current.getChildren().get(curKey.charAt(0)).getValue().equals(null)){
        return false;
      }
      return true;
    }
    return containsKey(current.getChildren().get(curKey.charAt(0)), curKey.substring(1));
  }

  //Indirectly recursive method to meet definition of interface
  public ArrayList<String> getKeysForPrefix(String prefix) {
    ArrayList<String> values = new ArrayList<String>();
    //Find the node the prefix points to
    TBDSNode prefixNode = findNode(root, prefix);
    if(prefixNode != null){
      //get the value of the prefix node and every single value after it
      values.addAll(getSubtreeKeys(prefixNode));
    }
    return values;
  }

  //Recursive helper function to find node that matches a prefix
  //Note: only a suggestion, you may solve the problem in any recursive manner
  public TBDSNode findNode(TBDSNode current, String curKey) {
    //if we have reached the end of the key
    if(curKey.length() == 1){
      return current.getChildren().get(curKey.charAt(0));
    }
    return findNode(current.getChildren().get(curKey.charAt(0)), curKey.substring(1));
  }

  //Recursive helper function to get all keys in a node's subtree
  //Note: only a suggestion, you may solve the problem in any recursive manner
  public ArrayList<String> getSubtreeKeys(TBDSNode current) {
    ArrayList<String> values = new ArrayList<String>();
    //if the node has a value add it to the list
    if(current.getValue() != null){
      values.add(current.getValue());
    }
    //if the node has children, traverse each one of them
    if(!current.getChildren().isEmpty()){
      for(Map.Entry<Character,TBDSNode> entry: current.getChildren().entrySet()){
        Character key = entry.getKey();
        values.addAll(getSubtreeKeys(current.getChildren().get(key)));
      }
    }
    return values;
  }

  //Indirectly recursive method to meet definition of interface
  public void print() {
    print(root);
  }

  //Recursive method to print values in tree
  public void print(TBDSNode current) {
    //print if the node has no children
    if(current.getChildren().isEmpty()){
      System.out.println(current.getValue());
      return;
    }
    for(Map.Entry<Character,TBDSNode> entry: current.getChildren().entrySet()){
      Character key = entry.getKey();
      print(current.getChildren().get(key));
    }
  }

  public static void main(String[] args) {
    //You can add some code in here to test out your TBDS initially
    //The TBDSTester includes a more detailed test

  }
}