// StackTest.java
// Linked list implementation of Stack
package Labs.Lab1;

class Stack <T> {
    
    class Node {
        public T data;
        public Node next;  
    }
    private Node top;
      
    public Stack()
    { 
        top = null;
    }
        
    public void push(T x) {
        Node  t = new Node();
        t.data = x;
        t.next = top;
        top = t;
    }

    // only to be called if list is non-empty.
    // Otherwise an exception should be thrown.
   public T pop(){

      
        boolean empt = isEmpty();
        Node temp;
       
        if(empt){
            System.out.println("Stack is empty");
            return null;
        }
        else{

            temp = top;
            top = temp.next;
            return temp.data;
        }
    
        
    }

   
    public boolean isEmpty(){

        if(top == null)
        {
            return true;
        }
        else
        {
            return false;
        }
    
    }


    public void display() {
        Node t = top;
        //Console.Write("\nStack contents are:  ");
        System.out.println("\nStack contents are:  ");
        
        while (t != null) {
            //Console.Write("{0} ", t.data);
            System.out.print(t.data + " ");
            t = t.next;
        }
        //Console.Write("\n");
        System.out.println("\n");
    }

    public boolean isMember(T x){

        Node temp = top;

        if(temp == null ){
            System.out.println("No elements in stack\n");
            return false;
        }
        else
        {

            while(temp != null)
            {
                if(temp.data == x)
                {
                    System.out.println("Found "+x+" in stack\n");
                    return true;
                }
                temp = temp.next;
            }
        }
        System.out.println(x+" isn't in stack");
        return false;
    }

    public int size(){
        Node temp = top;
        int i = 0;

        if(isEmpty()){
            
        }
        else
        {
            while(temp != null){
                temp = temp.next;
                i++;
            }
        }
        return i;
    }

}

   

public class StackTest
{
    public static void main( String[] arg){

        // you hava to tell the stack what type it will be taking
       Stack<Integer> s = new Stack<Integer>();
        //Console.Write("Stack is created\n");
        System.out.println("Stack is created\n");
        
        s.push(10); 
        s.push(3); 
        s.push(11); 
        s.push(7);
        s.display();
        
        
        boolean isMem = s.isMember(7);

        int j = s.size();

        System.out.println("there are "+j+" elements in the stack\n");

        int i = s.pop();

        System.out.println("Just popped " + i);
        s.display();

        isMem = s.isMember(6);

        j = s.size();

        System.out.println("there are "+j+" elements in the stack");
        


        //read this: https://docs.oracle.com/javase/tutorial/java/generics/types.html
    
    }
}


