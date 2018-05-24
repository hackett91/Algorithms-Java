package Labs.Lab2;

// Exercise to separate ADT Queue from its implementation
// and to provide 2 implementations. Also exception handling.

class QueueException extends Exception {
    // do as in stack example
    public QueueException(String s){
        super(s);
    }
}    

// In Java an interface can often be the best way to 
// describe an Abstract Data Type (ADT) such as Queue or Stack
interface Queue {
    public void enQueue(int x) throws QueueException;
    public int deQueue() throws QueueException;
    public boolean isEmpty();   
}



class QueueLL implements Queue {

        private Node front;
        private Node tail;
        private int n;
    

    private class Node{
        int data;
        Node next;
    }

    public QueueLL(){
        front = null;
        tail = null;
        n =0;
    }

    
 
   //assume the queue is non-empty when this method is called, otherwise thro exception
   public int deQueue() throws QueueException
   {
        Node temp = new Node();

        if(isEmpty())
        {
            throw new QueueException("Illegal queue is empty");
        }
        else
        {   
            temp = front;
            front = temp.next;
            n--;  
        }
        return   temp.data;
        
    }
    //enqueu
    public void enQueue(int x) throws QueueException
    {
        if(isFull())
        {
            throw new QueueException("Illegal to enQueue a full Queue");         
        }
        else
        {
            Node temp = tail;
            tail = new Node();
            tail.data = x;

            if(isEmpty())
            {
                front = tail;
            }
            else
            {
                temp.next = tail;
            }
            n++;
        }

    }
    public boolean isEmpty()
    {
        return front == null;
    }
    public boolean isFull()
    {
        return false;
    }

 

} // end of QueueLL class



class QueueCB implements Queue {

    private int q[], rear, front;
    private int qmax, size;

 
    public QueueCB() {
        qmax = 4;
        size = front = 0;
        rear = -1;
        q = new int[qmax];
    }

    public void enQueue(int x) throws QueueException  {
        // do it

        if(isFull())
        {
            throw new QueueException("Illegal to enQueue a full Queue");         
        }
        else
        {            
                rear = (rear + 1 ) % qmax;
                this.q[rear] = x;
                size++;
            
        }
    }
    
  
    public int deQueue()  throws QueueException 
    {
        int temp;
        // do it
        if(isEmpty())
        {
            throw new QueueException("Illegal to deQueue an empty Queue"); 
        }
        else
        {
            temp = this.q[front];
            this.q[front] = 0;
            front = (front+1)%qmax;
            size--;   
        }
        return temp;
    }

    
    public void display()
    {

        for(int i = front; i< rear; i = (front +1) % qmax)
        {
            System.out.println(this.q[i]);
        }
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull(){
        return front == (rear +1) % qmax;
    }
}


// here we test both implementations
class QueueTest2 {
    public static void main(String[] arg) {
        //Queue q1, q2;
        //q1 = new QueueLL();
        Queue q2;

        q2 = new QueueCB();
        
       // for(int i = 1; i<6; ++i)
     try { 
           q2.enQueue(1);   
           }         
         catch (QueueException e) {
            System.out.println("Exception thrown: " + e); 
        }
        // more test code
       q2.display();
    }   
}

