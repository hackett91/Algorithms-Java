import java.io.*;
import java.util.Scanner;

public class Kruskal{

  public static void main(String[] args) throws IOException{

    Scanner scan = new Scanner(System.in);

    System.out.print("Please enter the name of the graph file (inlcude .txt)");

    //Collecting the specifications for the graph
    String fileName = scan.nextLine();

    //Creating a graph from specifications
    Graph g = new Graph(fileName);

    //getting minimum spanning tree using kruskal algorihm
    g.Kruskal();

    g.displayMST();



  }//end main
}//end Kruskal Class






class Edge{

  public int u, v, weight;

  public Edge(){
    u = 0;
    v = 0;
    weight = 0;
  }

  public Edge(int u, int v, int w){
    this.u = u;
    this.v = v;
    this.weight = w;
  }

  // Convert int's to char for better reading.
  private char toChar(int u) {

    return (char)(u + 68);
  }

  public void display(){

    System.out.print("(" + toChar(u) + ")--" + this.weight + "--(" + toChar(v) + ")\n");
  }

}


class Graph{

  private int no_vertices, no_edges;
  private Edge[] mstAr;
  private Edge[] edgeAr;

  //constructor which will read the graph file and create an array of edges based on the specs
  public Graph(String fileName) throws IOException {

    int u;
    int v;
    int weight;
    int edge_index;

    //creating file reading and buffer reading objects
    FileReader fileR = new FileReader(fileName);
    BufferedReader buffR = new BufferedReader(fileR);

    //splitting using delimiters space
    String delimiters = " +";
    // reading the first line with the number of vertices and edges
    String line = buffR.readLine();

    /* the method split is used for splitting strings into its substrings based on
     the given delimeter. It returns an array of string after spltting the string into substrings*/
    String[] params = line.split(delimiters);

    System.out.println("\nNo. Vertices: " + params[0] +"|| No. Edges: "+ params[1]);

    //storing and coverting number of edges and vertices to an int
    no_vertices = Integer.parseInt(params[0]);
    no_edges = Integer.parseInt(params[1]);

    // creating an array that will be filled with edge object (what is the point)
    edgeAr = new Edge[no_edges + 1];

    System.out.println("\nSample of graph that was read from the file\n");
    System.out.println("(Vertex)--weight--(Vertex)");

    //reading specs of graph from buffer and creating an egde array which will be used to
    // find the minimum spanning tree
    for(edge_index = 1; edge_index <= no_edges; ++edge_index){

        line = buffR.readLine();
        params = line.split(delimiters);
        u = Integer.parseInt(params[0]);
        v = Integer.parseInt(params[1]);
        weight = Integer.parseInt(params[2]);

        System.out.println("(" + toChar(u) + ")--" + weight + "--("+toChar(v) + ")");


        //Creating edge object and filing edge object array
        edgeAr[edge_index] = new Edge(u,v,weight);

    }//end for
    System.out.println("\n");
  }//end Graph constructor

  public Edge[] Kruskal(){

    int edge_index;
    int index = 0;
    Edge temp;
    int setV, setU;
    int mstWeight = 0; // change when know MORE
    UnionFind disjoint;

    //creating somewhere to hold minimum spanning tree
    mstAr = new Edge[no_vertices-1];

    // creating a priority queue for indices of edges stored in array
    Heap prior_h = new Heap(no_edges, edgeAr);

    //creating a disjointed set of all vertices.
    disjoint = new UnionFind(no_vertices);
    //it what to show set at the moment HAVE A LOOK IN AWHILE
    System.out.println("Disjointed Sets:");
    disjoint.displaySets();

    System.out.print("\n\nUnifying and mapping: ");

    while(index < no_vertices - 1){
      //removing top index from heap
      edge_index = prior_h.remove();
      //returning object ref in edgeAr of index chosen from prior_queue
      temp = edgeAr[edge_index];

      // finding and returning the vertices which combine to make the edge
      setU = disjoint.find(temp.u);
      setV = disjoint.find(temp.v);

      //check to ensure vertices aren't the same
      if(setU != setV){

        //adding edge to egde and vertices to minimum spanning tree
        mstAr[index] = temp;
        ++index;

        System.out.print("\n" + index + ": ");

        temp.display();

        disjoint.union(setU,setV);
        disjoint.displaySets();
        disjoint.display();
        // SHOOOOOOW SET ET
        //SHOW TREEE

        mstWeight += temp.weight;

      }//end if
    }// end while

    //displaying the total weight of the minimum spanning tree
    System.out.println("\nMST weight is:" + mstWeight);

    //return the minim spanning tree
    return mstAr;
  }//end mst method

  // Convert vertex into char for pretty printing.
	private char toChar(int u) {

		return (char)(u + 68);
	}

  //a method that will display the minimum spanning tree.
  public void displayMST() {

		System.out.print("\nThe minimum spanning tree is built from the following vertices and edges: \n");

		for(int index = 0; index < no_vertices-1; ++index) {

			mstAr[index].display();
		}

    System.out.print("\n");
	}

}

/* Sorting index of edges into a heap using HeapSort siftdown method
    using the weight of each object in the edge array to sort in minimum order.
 */
class Heap{

  private int[] heap;
  Edge[] edgeAr;
  int MAX_SIZE;

  // Heap contruction which will implement bottom up Heapsort
  public Heap(int size, Edge[] _edgeAr ){

    MAX_SIZE = size;
    edgeAr = _edgeAr;
    heap = new int[MAX_SIZE +1];
    int index;

    // filling heap array with indices owith indices of edge array.
    for(index = 0; index <= MAX_SIZE; ++index){
      heap[index] = index;
    }
    //heapifying current heap using heapSort and siftDown method
    for(index = MAX_SIZE/2; index > 0; --index){
      siftDown(index);
    }
  }
    //creating a heap using the bottom up approach. Ensuring that edge with minimum is consistantly at root
    private void siftDown(int parentIn){

      int value;
      int childIn;

      value = heap[parentIn];
      // end when last child is reached
      while(MAX_SIZE >= parentIn * 2){

        //getting the left child of parent
        childIn = parentIn * 2;

        // checking to see if left child index isn't exceeding limit of array
        // comparing right and left childs weight if rights is smaller increment
        //sifting down if need be
        if((childIn < MAX_SIZE) && (edgeAr[heap[childIn + 1]].weight < edgeAr[heap[childIn]].weight)){
          ++childIn;
        }
        //comparing parent weight and child weight. if parent is smaller or equall to break
        if(edgeAr[value].weight <= edgeAr[heap[childIn]].weight){
          break;
        }
        //putting childs into parent
        heap[parentIn] = heap[childIn];
        parentIn = childIn;
      }
      //putting the value into the right place in the heap
      heap[parentIn] = value;
    }

    //removing from root from Heap
    public int remove() {
      //removing root
      heap[0] = heap[1];
      //swapping out last element in heap to root
      heap[1] = heap[MAX_SIZE--];

      //sifting down new root if need be
      siftDown(1);

      //returning root
      return heap[0];
    }
  }

class UnionFind{

  private int MAX_SIZE;
  private int[] parents;

  //constructor the which creates parent array and fills it with indices
  public UnionFind(int no_vertices){

    parents = new int[no_vertices + 1];
    MAX_SIZE = no_vertices;

    for(int index = 1; index <= MAX_SIZE; index++){
      parents[index] = index;
    }
  }

  //siving up through node to root node
  public int find(int vertex){

    while(parents[vertex] != vertex){
      vertex = parents[vertex];
    }
    return vertex;
  }

  //merging into groups if not already part of same group
  // using a mapping technique
  public void union(int set1, int set2){

    int root1 = find(set1);
    int root2 = find(set2);

    parents[root2] = root1;
  }

  //displaying all different sets as they grow to create mininum spanning tree
  public void displaySets(){

    int root;
    int index;
    int[] setAr = new int[MAX_SIZE + 1];

    for(index = 1; index <= MAX_SIZE; ++index){

      root = find(index);

      if(setAr[root] != 1){

        displaySet(root);
        setAr[root] = 1;
      }

    }
  }

  //display each individual set
  private void displaySet(int root) {

		int index;
		System.out.print("Set{");

		for(index = 1; index <= MAX_SIZE; ++index) {

			if(find(index) == root) {

				System.out.print(toChar(index) + "");
			}
		}

		System.out.print("}  ");

	}
  //display edges and vertices
  public void display(){
    System.out.println();
    //displaying the mapping
    for(int index = 1; index <= MAX_SIZE; ++index){
      System.out.print("("+toChar(index) + ")--("+ toChar(parents[index])+ ") ");
    }
    System.out.println("\n");
  }

  private char toChar(int u) {

    return (char)(u + 68);
  }

}
