public class SimpleHashTable{

    private StoredEmployee[] hashtable;

    public SimpleHashTable(){
        hashtable = new StoredEmployee[10];
    }


    // To handle collisions we use open addressing
    // if we have someone already in the position in the array we look for another position
    // we are going to use linear probing + if it is taking already we increment the hashed value by one
    //
    public void put(String key, Employee employee){
        //map the key provided to an integer
        int hashedKey = hashKey(key);
        //check if hashed key is occupied
        if(occupied(hashedKey)){
            int stopIndex = hashedKey;
            //if the position we just checked is that last position in the array we loop
            if(hashedKey == hashtable.length -1){
                hashedKey = 0;
            }//otherwise just increment
            else{
                hashedKey++;
            }
            while(occupied(hashedKey) && hashedKey != stopIndex){
                hashedKey = (hashedKey + 1) % hashtable.length;
            }

        }
        //we check that position in the array is empty we assign the employee into that possition
        // otherwise we throw a message saying can't insert etc
        if(occupied(hashedKey)){
            System.out.println("sorry, there's already an employee at position" + hashedKey);
        }
        else{
            hashtable[hashedKey] = new StoredEmployee(key, employee);
        }
    }

    private int findKey(String key){
      int hashedKey = hashKey(key);
      if(hashtable[hashedKey] != null && hashtable[hashedKey].key.equals(key)){
          return hashedKey;
      }

      //check if hashed key is occupied
      int stopIndex = hashedKey;
      //if the position we just checked is that last position in the array we loop
      if(hashedKey == hashtable.length -1){
          hashedKey = 0;
      }//otherwise just increment
      else{
          hashedKey++;
      }
      while(hashedKey != stopIndex && hashtable[hashedKey] != null &&
          !hashtable[hashedKey].key.equals(key)){
          hashedKey = (hashedKey + 1) % hashtable.length;
      }

      if(hashtable[hashedKey] != null &&
          hashtable[hashedKey].key.equals(key)){
            return hashedKey;
      }
      else{
          return -1;
      }
    }

    public Employee get(String key){
        int hashedKey = findKey(key);
        if(hashedKey == -1){
          return null;
        }
        else{
          return hashtable[hashedKey].employee;
        }
    }

    public Employee remove(String key){
      int hashedKey = findKey(key);

      if(hashedKey == -1){
        return null;
      }
      Employee employee = hashtable[hashedKey].employee;
      hashtable[hashedKey] = null;
      StoredEmployee[]  oldHashtable = hashtable;

      hashtable = new StoredEmployee[oldHashtable.length];
      for(int i = 0; i < oldHashtable.length;i++){
          if(oldHashtable[i] != null){
              put(oldHashtable[i].key, oldHashtable[i].employee);
          }
      }

      return employee;
    }

    //taking a key and hashing it to an int
    private int hashKey(String key){
        return key.length() % hashtable.length;
    }

    private boolean occupied(int index){
      return hashtable[index] != null;
    }

    public void printHashtable(){
        for(int i = 0; i < hashtable.length;i++){
          if(hashtable[i] != null){
          System.out.println("Position "+ i+ ": "+ hashtable[i].employee.getFirstName() + " " + hashtable[i].employee.getLastName());
          }
          else{
            System.out.println("Empty");
          }
        }
    }


}
