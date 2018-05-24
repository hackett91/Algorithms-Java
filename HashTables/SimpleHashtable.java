public class SimpleHashtable{

    private Student[] hashtable;

    public SimpleHashtable(){
      hashtable = new Student[10];
    }
    public void put(int key, Student student){
      int hashedKey = hashKey(key);
      if(hashtable[hashedKey] != null)
      {
        System.out.println("Sorry, there is alredy an emloey at the postion " + hashedKey);
      }
      else
      {
        hashtable[hashedKey] = student;
      }
    }

    //retrieval is constant time
    public Student get( int key){
      int hashedKey = hashKey(key);
      return hashtable[hashedKey];
    }

    private int hashKey(int key){
      return key % hashtable.length;
    }

    public void printHashTable(){
      for(int i =0; i < hashtable.length; i++){
        System.out.println(hashtable[i]);
      }
    }
}
