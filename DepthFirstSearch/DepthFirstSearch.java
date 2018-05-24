/**

 * @author Cian Hackett
 */

import java.util.ArrayList;


public class DepthFirstSearch {
	/**
	 * The main method is responsible for creating the graph and populating it with sample 			data.
	 * It then controls searching through the resulting tree using the depth first search 			algorithm.

	 */
	public static void main(String[] args) {
		Graph g = new Graph();
		ArrayList<Node> open = new ArrayList<Node>();
		ArrayList<Node> closed = new ArrayList<Node>();
		ArrayList<Node> xChildren = new ArrayList<Node>();
		Node x;
		String result = "Fails";
		
		//populate with sample data
		g.popSampleNodes(30);
		
		//Set the goal node
		Node goal = g.findNode(30);
		
		//initialise open list with start node
		open.add(g.getRootNode());
		
		System.out.println("Depth first start state node: "+g.getRootNode().getName());

		//iterate through the open list
		while(open.size() > 0) {
			
			//remove the leftmost state from open and call it x
			x = open.get(0);
			open.remove(0);
			
			
			//if x is the goal return success else
			if(x.equals(goal)) {
				result = "Success! "+ x.getName()+" Node reached";
				break;
			}
			
			//generate children of x
			xChildren = x.getChildren();
		
			
			//put x in closed
			closed.add(x);

			
			//print list state
			System.out.print("Open: ");
			printListState(open);
			
			//print list state
			System.out.print("Closed: ");
			printListState(closed);
			
			//discard children of x if already in open or closed
			for(int i = xChildren.size()-1; i > 0 ; i--) {
				if(open.contains(xChildren.get(i)) || closed.contains(xChildren.get(i)) ) {
					xChildren.remove(i);
				}
			}
			
			// put remaining children at the start of open
			//loop decrement from last item to ensure they are
			//appear in the correct order once in open list
			for(int i = xChildren.size(); i > 0; i--) {				
				open.add(0, xChildren.get(i-1));
			}
			
			
			System.out.println("");
		}//end while
		
		System.out.println(result);
	} // end main
	
	public static void printListState(ArrayList<Node> list) {
		//output results			
		for(int i = 0; i < list.size(); i++) {
			System.out.print(list.get(i).getName()+", ");
		}
		System.out.print(" || ");
	}
	
}
