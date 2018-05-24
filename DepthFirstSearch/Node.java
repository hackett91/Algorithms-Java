import java.util.ArrayList;
/**
 * Represent a node in the network. With fields representing it's state and children.
 * 
 * @author Shaun Plummer
 */
public class Node {
	int name;
	ArrayList<Node> children = new ArrayList<Node>();
	
	/**
	 * Adds the data used to represent the node state.
	 * 
	 * @param i	-Data stored in the node 
	 */
	public Node(int i) {
		this.name = i;
	}
		
	/**
	 * Allows the children of a given node to be specified.
	 * 
	 * @param n
	 */
	public void setChild(Node n){
		children.add(n);
	}
	
	/**
	 * Returns all children for the given node
	 * @return 	ArrayList	Contains all children as node objects	
	 */
	public ArrayList<Node> getChildren() {
		return this.children;
	}
	
	/**
	 * Returns the data representing the node state
	 * 
	 * @return String state information.
	 */
	public int getName() {
		return this.name;
	}

}
