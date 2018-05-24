import java.util.ArrayList;

/**
 * The graph class represents the collection of nodes. 
 * 
 * @author Shaun Plummer
 */
public class Graph {
    private Node rootNode;
    private ArrayList<Node> nodes= new ArrayList<Node>();
    
    /**
     * Returns the root node of the graph 
     * 
     * @return Node	
     */
    public Node getRootNode() {
		return rootNode;
	}

    /**
     * Allows the root node of the graph to be specified.
     * 
     * @param rootNode
     */
	public void setRootNode(Node rootNode) {
		this.rootNode = rootNode;
	}

	/**
	 * Add a new node to the graph
	 * 
	 * @param n	The node to be added
	 */
	public void addNode(Node n) {
    	this.nodes.add(n);
    }
	
	/**
	 * Returns the node at the requested position in the children array
	 * 
	 * @param i	the index of the requested node in the array
	 * @return	The node found at the position specified in the index.
	 */
	public Node getNode(int i) {
		return nodes.get(i);
		
	}
	/**
	 * Searches searches each node. If it cannot be found the null is returned. 
	 * 
	 * @param j The node to be searched for
	 * @return	The node found at the position specified in the index.
	 */
	public Node findNode(Integer j) {
		Node goal = null;
		for(Node cNode : nodes) {
			if(j == cNode.getName() ) {
				goal = cNode;
				break;
			}
		}
		return goal;
	}
	
	/**
	 * Populates the graph with sample data. 
	 */
	public void popSampleNodes(int total) {
		for(int i = 1; i < total; i++) {
			
			this.nodes.add(new Node(i));
			if(i > 3) {
				Node parent = nodes.get(i-4);
				parent.setChild(nodes.get(i-3));
				parent.setChild(nodes.get(i-2));
			}
		}
		setRootNode(nodes.get(0));
	}
	
}
