import java.util.LinkedList;
import java.util.ListIterator;
public class ChainedHashtable {

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


  public Employee get(String key){
    int hashedKey = hashKey(key);
    ListIterator<StoredEmployee> iterator = hashtable[hashedKey].listIterator();
    StoredEmployee employee = null;
    while(iterator.hasNext()){
      employee = iterator.next();
      if(employee.key.equals(key)){
          return employee.employee;
      }
    }
      return null;
  }

  public Employee remove(String key){
    int hashedKey = hashKey(key);
    ListIterator<StoredEmployee> iterator = hashtable[hashedKey].listIterator();
    StoredEmployee employee = null;
    int index = -1;
    while(iterator.hasNext()){
      employee = iterator.next();
      index++;
      if(employee.key.equals(key)){
          break;
      }
    }
    if(employee == null || !employee.key.equals(key)){
        return null;
    }
    else
    {
      //will traverse straight to index no comparisons
      // takes less compute time than remove(employee) has comparisons
      hashtable[hashedKey].remove(index);
      return employee.employee;
    }
  }

  public void printHashtable(){
      for (int i = 0;i < hashtable.length; i++ ) {
          if(hashtable[i].isEmpty()){
            System.out.println("Position " + i + ": empty");
          }
          else
          {
              System.out.println("Position" + i + ": ");
              ListIterator<StoredEmployee> iterator = hashtable[i].listIterator();
              while(iterator.hasNext()){
                System.out.print(iterator.next().employee);
                System.out.print("->");
              }
              System.out.println("null");
          }
      }
  }

  private int hashKey(String key){
    return key.length() % hashtable.length;
  }

}
