import java.util.ArrayList;
public interface TBDSInterface {
  //Adds the key-value pair to the TBDS
  public void add(String key, String value);
  
  //Returns the object value associated with the given key
  //If the key is not present in the TBDS, returns null
  public String get(String key);
  
  //Returns true if key is in the TBDS, false otherwise
  public boolean containsKey(String key);
  
  //Returns an ArrayList of objects containing all keys that start with prefix
  public ArrayList<String> getKeysForPrefix(String prefix);
  
  //Prints all values stored inside the TBDS
  public void print();
  
}