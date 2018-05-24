// Heap.java

class Heap {
  private
	int[] a, hPos;
	int N;
	static int maxH = 100;

// two constructors
public Heap() {
   N = 0;
   a = new int[maxH+1];
}


public Heap(int size) {
   N = 0;
   a = new int[size + 1];
}



public void insert( int x) {
   a[++N] = x;
   siftUp( N);
}


public void siftUp( int k) {
   int v = a[k];
   a[0] = Integer.MAX_VALUE;

   while( v > a[k/2]) {
      a[k] = a[k/2];
	  //hPos[]
      k = k/2;
   }
   a[k] = v;
  // hPos[]
}




public void siftDown( int k) {
 // do yourself
 int parent = k;
 int child = k*2;
 int temp;
 while(a[child] > a[parent]){

     temp = a[child];
     a[child] = a[parent];
     a[parent] = temp;
     parent = child;
     child = child*2;
 }
}


public int remove() {
   // do yourself
   int temp = a[1];
   a[1] = a[N];
   a[N]= 0;
   siftDown(1);
   return temp;
}



public void display() {
   System.out.println("\n\nThe tree structure of the heaps is:");
   System.out.println( a[1] );
   for(int i = 1; i<= N/2; i = i * 2) {
      for(int j = 2*i; j < 4*i && j <= N; ++j)
         System.out.print( a[j] + "  ");
       System.out.println();
   }
}



public static void main(String args[]) {

   Heap h = new Heap();
   int r; double x;

   // insert random numbers between 0 and 99 into heap
   for(int i = 1; i <= 10; ++i)  {
	  x =  (Math.random()*100.0);
      r = (int) x;
      System.out.println("Inserting " + r);
      h.insert(r);
	  h.display();
   }
   System.out.println("Removing.....");

   h.remove();
   h.display();

}


} // end of Heap class
