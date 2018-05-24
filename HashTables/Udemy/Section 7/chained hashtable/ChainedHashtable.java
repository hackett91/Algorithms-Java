import java.util.LinkedList;

public class ChainedHashtable{

  private LinkedList<StoredEmployee>[] hashtable;

  /*constructor that creates a hashtable array
  and then puts a linked list in each element in
  the array.*/
  public ChainedHashtable(){
    hashtable = new LinkedList[10];
    for(int i = 0; i < hashtable.length; i++){
        hashtable[i] = new LinkedList<StoredEmployee>();
    }

  }
  /*  the put adds an employee in its proper position in the
  array*/
  public void put(String key, Employee employee){
    int hashedKey = hashKey(key);
    hashtable[hashedKey].add(new StoredEmployee(key, employee));
  }

  private int hashKey(String key){
    return key.length() % hashtable.length;
  }

}
