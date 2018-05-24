import java.util.Arrays;

public class HashFunction{

  String[] theArray;
  int arraySize;
  int itemsInArray =0;

  public HashFunction(int size ){

    arraySize = size;
    theArray = new String[size];
    Arrays.fill(theArray,"-1");
  }

  /* This function stores the value of arrays in a hashTable
      stringForArray = values you want to put inot hashTable
      theArray = the hashTable
      newElementVal = each element you want to put into the hashTable

      */
  public void  hashFunction1(String[] stringForArray, String[] theArray){
      for(int n = 0; n<stringForArray.length; n++){
          String newElementVal = stringForArray[n];
          //this line is the hashFuction( no duplication)
          theArray[Integer.parseInt(newElementVal)-1] = newElementVal;
      }
  }

  /* in this example I will need to hold numbers between 0-999
    we couldn't have a hashTable that long
    we never plan on having more than 15 items
    we can use the mode function of the arraySize
    */

  public void  hashFunction2(String[] stringForArray, String[] theArray){

      //go through each element of the array which you want to put in the hash hasTable
      for (int n =0; n <stringForArray.length;n++) {
          //getting each element
          String newElementVal = stringForArray[n];
          //getting each elements
          int arrayIndex = Integer.parseInt(newElementVal) % 29;
          //showing what is the module value for element
          System.out.println("Module Index= " + arrayIndex + " for value " + newElementVal);

          while(theArray[arrayIndex] != "-1"){
            //keep incrementing throught array
            ++arrayIndex;
            //if you are in here that means that your moded index already has a value stored in it
            System.out.println("Collision Try" + arrayIndex + " Instead");
            // keep sifting throught the array
            arrayIndex = arrayIndex % arraySize;
          }
          //if you find an empty space put it in
          theArray[arrayIndex] = newElementVal;
      }

  }
  public String findKey(String key){
      //parsing and getting the module representation of key
      int arrayIndexHash = Integer.parseInt(key) % 29;
      //checking if the hashtables value is not empty
      while(theArray[arrayIndexHash] != "-1"){

          //if you find the match return the value
          if(theArray[arrayIndexHash] == key){

            System.out.println(key + " was Found in Index " + arrayIndexHash);

            return theArray[arrayIndexHash];
          }
          //if not keep searching
          ++arrayIndexHash;
          arrayIndexHash = arrayIndexHash % arraySize;
      }
      //if fail to find return null
      return null;
  }

  public static void main(String[] args) {

      // instaniate a new object hashFuction object which will have an array and size as fields
      HashFunction theFuc = new HashFunction(30);
/*      //creating a list of numbers you would like to store in a hasTable
  String[] elementsToAdd = {"4","10","1","17"};
      //hashing numbers
      theFuc.hashFunction1(elementsToAdd, theFuc.theArray);

      for(int i = 0; i < theFuc.theArray.length; i++){
          System.out.print(theFuc.theArray[i]+" ");
      }*/

      String[] elementsToAdd2 = {"100", "510", "170", "214", "268", "398",
                "235", "802", "900", "723", "699", "1", "16", "999", "890",
                "725", "998", "978", "988", "990", "989", "984", "320", "321",
                "400", "415", "450", "50", "660", "624" };

        theFuc.hashFunction2(elementsToAdd2, theFuc.theArray);

        System.out.println();
        System.out.println();

        for(int i = 0; i < theFuc.theArray.length; i++){
            System.out.print(theFuc.theArray[i]+" ");
        }

  }




}
