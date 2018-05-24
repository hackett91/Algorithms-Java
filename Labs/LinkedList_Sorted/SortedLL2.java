// Sorted linked list with a sentinel node
// Skeleton code
package Labs.Lab3;
import java.util.Scanner;

class SortedLL2
{
    // internal data structure and

    class Node
    {
        Node next;
        int data;
    }

    private Node head = null;
    private Node z = null;
     // constructor code to be added here
    
    public SortedLL2()
    {
        z = new Node();
        head = z;
        z.next = z;

    }


    
    // this is the crucial method
    public void insert(int x)
    {
        // first create 2 pointer node previous and current
        Node prev, curr;
        // then create a newNode to insert into linked list
        Node newNode = new Node();
        // then assign the head of the linked list to current node
        // this will help with traversal
        curr = head;
        // assign prev to null
        // this will help with inserting into start of list
        prev = null;
        // then we assign the value to be added to 
        // linked list to the date field in the newNode
        newNode.data = x;

        /* first thing todo is check if linked list is empty
            is so we then assign the address of the newNode to
            the head and the sentinel to the pointer of the newNode 
        */
        if(isEmpty())
        {
            head = newNode;
            newNode.next = z;
        }
        /* otherwise i.e. there is nodes in the linked list
            we traverse the linked list until value enter is no 
            longer greater than current data field or current isn't the
            sentinel node i.e havent't reached final node*/
        else
        {
            //traversal and incrementation of prev and current    
            while(x > curr.data && curr != z)
            {
                prev = curr;
                curr = curr.next;
            }
            //if previous is equalled to null this means that no incrementation
            // has happen i.e new value must be smaller than first data field of node
            // there fore the link has to be created by 
            if(prev == null)
            {
                // setting the pointer of the new node to the addres of head(first node in list)
                newNode.next = head;
                // then setting the head ponter to the address of the newNode
                head = newNode;
            }
            else
            {
                //otherwise we are inserting a node into the middle or end of list
                // we set the newNodes pointer to current
                // and previous node pointer to newNode
                newNode.next = curr;
                prev.next = newNode;
            }
        }
           
             
    }    
    
    
   public boolean remove(int x) 
   {
      Node prev, curr;

      prev = null;
      curr = head;
      //check is Empty
      if(isEmpty())
      {
        System.out.println("You cannot remove from empty list");
        return false;
      }
      else
      {
        while(curr.data != x && curr != z)
        {
            prev = curr;
            curr = curr.next;
        }

        if(curr == z)
        {
            return false;
        }

        if(prev == null)
        {
            head = curr.next;
            return true;
        }
        else
        {
            prev.next = curr.next;
            return true;
        }
      }
        
    }
    
    public boolean isEmpty() 
    {
        return head==z;
        
    }
    
    
    public void display()
    {
        Node t = head;
        System.out.print("\nHead -> ");
        while( t != z) 
        {
            System.out.print(t.data + " -> ");
            t = t.next;
        }
        System.out.println("Z\n");
    }
    
    public static void main(String args[])   
    {
        SortedLL2 list = new SortedLL2();
        //list.display();
        
        double x;
        int i, r;
        
        
        for(i = 1; i <= 5; ++i)  
        {
           x =  (Math.random()*100.0);
           r = (int) x; 
           System.out.println("Inserting " + r);
           list.insert(r);
           list.display();            
        }
        
        
        
        while(!list.isEmpty())  
        {
            System.out.println("\nInput a value to remove: ");
            Scanner in = new Scanner(System.in);
            r = in.nextInt();

            if(list.remove(r)) 
            {
                System.out.println("\nSuccessfully removed: " + r);
                list.display(); 
            }
            else
            {
                 System.out.println("\nValue not in list");   
            }                     
        }
    
    }
}