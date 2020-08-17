import java.util.HashMap;
public class TBDSNode {
  //Keys are characters
  //Values are other TBDSNodes
  //This way, you can use the next character in a String to determine the next node
  //This allows you to progress deeper into the tree
  private HashMap<Character, TBDSNode> children;
  private String value;
  
  public TBDSNode(){
   children = new HashMap<Character, TBDSNode>();
   value = null;
  }
  
  public String getValue(){
    return value;
  }
  
  public void setValue(String newVal){
    value = newVal;
  }
  
  public HashMap<Character, TBDSNode> getChildren(){
    return children;
  }  
}