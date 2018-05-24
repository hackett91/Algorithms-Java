import java.io.*;
import java.util.Scanner;

public class Prims{

  public static void main(String[] args) throws IOException {

      Scanner scan = new Scanner(System.in);

      // Requesting file name
      System.out.println("Please enter file name of graph file (include .txt) : ");
      String fileName = scan.nextLine();

      // Entering the starting vertex.
      System.out.println("Please eneter the name of the vertex you wish to begin at : ");
      int sVertex = scan.nextInt();

      Graph graph = new Graph(fileName);

      //displaying file that is read
      graph.display();
      //Using prims Algorithm to find Minimum spanning tree
      graph.prim_MST(sVertex);
      // displaying Minimum spanning tree
      graph.displayMST();

  }
}

class Heap {

    private int[] heap;
    private int[] hPos;
    private int[] dist;
    private int MAX_SIZE;

    // Parameterised heap constructor to implement Graph:
    // ref to Hpos array, Max size of heap, ref to distance array
    public Heap(int[] _hPos, int maxSize, int[] _dist) {
        // constructing heap with passed params
        MAX_SIZE = 0;
        heap = new int[maxSize + 1];
        dist = _dist;
        hPos = _hPos;
    }

    //heck if heap is empty
    public boolean isEmpty() {

        return MAX_SIZE == 0;
    }

    // method to move up in heap (sorting)
    public void siftUp(int parent) {

        //storing current position
        int curr_ind = heap[parent];
        dist[0] = 0;

        //continue while parent is bigger than child
        while(dist[curr_ind] < dist[ heap[parent / 2] ]) {

            //sift up and increment tracker
            heap[parent] = heap[parent / 2];
            hPos[ heap[parent] ] = parent;

            //Go up to parent
            parent = parent / 2;
        }

        // inserting into right position in heap
        heap[parent] = curr_ind;
        // keeping track of where value is in heap
        hPos[curr_ind] = parent;
    }


    public void siftDown(int parent) {

        int curr_ind;
        int child;

        curr_ind = heap[parent];

        child = 2 * parent;

        //sift down
        while(child <= MAX_SIZE) {

            //comparing dist of right and left childs to find right position
            if( (child + 1 <= MAX_SIZE) && dist[ heap[child] ] > dist[ heap[child + 1] ] ) {

                child++;
            }

            //breaking when right positon is found
            if( dist[ heap[child] ] >= dist[curr_ind] ) {

                break;
                //other wise sift down
            } else {

                heap[parent] = heap[child];
                parent = child;
                child = parent * 2;
            }
        }

        //recording changes in heap and keeping record of changes in hPos
        heap[parent] = curr_ind;
        hPos[curr_ind] = parent;
    }


    public void insert( int value) {

        //addinga new value to the end of the heap
        heap[++MAX_SIZE] = value;

        //sifting up value just entered
        siftUp(MAX_SIZE);
    }

    public int remove() {

        //assign value to be zero then sift it down
        int re_value = heap[1];

        //removing record of removed item
        hPos[re_value] = 0;

        //adding empty node to empty position
        heap[MAX_SIZE+1] = 0;

        heap[1] = heap[MAX_SIZE--];

        siftDown(1);

        return re_value;
    }

}

class Graph {

    class Node {

        public int vertex;
        public Node nextPtr;
        public int weight;
    }

    private Node[] adjacency;
    private int[] mst;
    private int no_vertices;
    private int no_edges;
    private Node z;


    // Constructor which will read graph file and construct an
    //WHAT DOES IT DO
    public Graph(String graphFile)  throws IOException {

      int u;
      int v;
      int edge_index;
      int weight;
      Node t_node;

      FileReader fileReader = new FileReader(graphFile);
      BufferedReader bufReader = new BufferedReader(fileReader);
      // Multiple whitespace as delimiter.
      String delimiter = " +";
      String line = bufReader.readLine();
      String[] params = line.split(delimiter);
      System.out.println("\nNo. Vertices: " + params[0] + " || No. Edges:" + params[1]);

      no_vertices = Integer.parseInt(params[0]);
      no_edges = Integer.parseInt(params[1]);

      // Creating a sentinel node so we dont go outside of the bounds
      z = new Node();
      z.nextPtr = z;

      // Create adjacency lists that is initialised to sentinel node z
      adjacency = new Node[no_vertices+1];

      for(v = 1; v <= no_vertices; ++v) {
          adjacency[v] = z;
      }

      System.out.println("\nSample of graph that was read from the file\n");
      System.out.println("(Vertex)--weight--(Vertex)");

      for(edge_index = 1; edge_index <= no_edges; ++edge_index) {

          //reading an displaying graph
          line = bufReader.readLine();
          params = line.split(delimiter);
          u = Integer.parseInt(params[0]);
          v = Integer.parseInt(params[1]);
          weight = Integer.parseInt(params[2]);

          System.out.println("(" + u + ")--" + weight + "--(" + v +")");

          // creating link list adjacency list
          t_node = new Node();
          t_node.weight = weight;
          t_node.vertex = v;
          t_node.nextPtr = adjacency[u];
          adjacency[u] = t_node ;

          t_node = new Node();
          t_node.weight = weight;
          t_node.vertex = u;
          t_node.nextPtr = adjacency[v];
          adjacency[v] = t_node;

        }
    }



    // display graph and also representation in adjacency list
    public void display() {
        int vertex_index;
        Node d_temp;

        System.out.println("\n3. An adjacency lists diagram showing the graph representation of your sample graph");

        for(vertex_index = 1; vertex_index <= no_vertices; ++vertex_index) {
            System.out.print("\nAdjacencyList [" + vertex_index + "] ->" );

            // going through each vertices and then going trough link list to see adjacency
            for(d_temp = adjacency[vertex_index]; d_temp != z; d_temp = d_temp.nextPtr) {

                System.out.print(" |" + d_temp.vertex + " | " + d_temp.weight + "| ->");
            }
        }
        System.out.print("\n");
    }

    public void prim_MST(int sVer) {

        int vertex;
        int u;
        int weight;
        int mstWeight = 0;
        int[]  dist;
        int[] parent;
        int[] hPos;

        // weight from node to node
        dist = new int[no_vertices + 1];
        // parent node array
        parent = new int[no_vertices + 1];
        //recording postion in heap
        hPos = new int[no_vertices + 1];


        for(vertex = 0; vertex <= no_vertices; vertex++) {
            //setting to max value
            dist[vertex] = Integer.MAX_VALUE;
            // zero will be a special vertex
            parent[vertex] = 0;
            // impling it's not on the heap
            hPos[vertex] = 0;
        }
        // Creating priority queue using params
        Heap priority_q =  new Heap(hPos, no_vertices, dist);

        // insert root into node
        priority_q.insert(sVer);

        // setting root at start
        dist[sVer] = 0;

        while (!priority_q.isEmpty()) {
            //creating mst by taking minimun from root of queue
            vertex = priority_q.remove();
            dist[vertex] = -dist[vertex];

            Node p_temp;
            int wgt;

            // checking which is smallest fo each neighbour and Ensuring no cycles are created
            // incrementing through each node/vertex
            for (p_temp = adjacency[vertex]; p_temp != z; p_temp = p_temp.nextPtr) {

                u = p_temp.vertex;
                wgt = p_temp.weight;

                //adding smallest weight edge to mst
                if (wgt < dist[u]) {

                    if (dist[u] != Integer.MAX_VALUE) {

                        mstWeight -= dist[u];
                    }
                    //swapping
                    dist[u] = wgt;
                    parent[u] = vertex;
                    mstWeight += wgt;

                    if (hPos[u] == 0) {
                        //inserting into priority queue
                        priority_q.insert(u);

                    } else {
                        //sifting up in priorty
                        priority_q.siftUp(hPos[u]);
                    }
                }
            }
        }

        //displaying the total weight of the minimum spanning tree
        System.out.println("\nMST weight is:" + mstWeight);
        mst = parent;
    }

    /* Displaying minimum spanning tree */
    public void displayMST() {

        System.out.print("\n\nMinimum Spanning Tree Structure:\n");

        for(int vertex_index = 1; vertex_index <= no_vertices; ++vertex_index) {
            System.out.println("(" + mst[vertex_index] + ") -> (" + mst[++vertex_index] + ")");
        }

        System.out.print("\n");
    }


}
