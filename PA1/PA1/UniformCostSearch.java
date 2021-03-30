import java.util.*;

public class UniformCostSearch {
	
	public int expansionCount;
	public HashSet<String> explored = new HashSet<String>();
	public String initialLoc;
	public StreetMap graph;
	public String destinationLoc;
	public int limit;
	//public Heuristic dest;
	
	public UniformCostSearch (StreetMap graph, String initialLoc, String destination, int limit) {
		this.initialLoc = initialLoc;
		this.graph = graph;
		this.destinationLoc = destination;
		this.limit = limit;
	}
	
	public Node search (boolean repeated_state) {
		expansionCount = 0;
		SortedFrontier frontier = new SortedFrontier(SortBy.g);
		Node currNode = new Node(graph.findLocation(initialLoc),null);
		
		
		
		if (currNode.loc.name == destinationLoc) {
			return currNode;
		} else {
			
			frontier.addSorted(currNode);
			
		if (repeated_state == true) {
			while (!frontier.isEmpty() && !(currNode.depth >= limit)) {
				currNode = frontier.removeTop(); 
				
			if (currNode.isDestination(destinationLoc) == true) {
				return currNode;
				
			} else {
				explored.add(currNode.loc.name); 
				currNode.expand();
				expansionCount++;
				Node comp = new Node();
			
			for (Node child: currNode.children) {
				
				if (!explored.contains(child.loc.name)) {
					if (frontier.contains(child) == true) {
						comp = frontier.find(child);
						
						if (child.partialPathCost < comp.partialPathCost) { //performs check to find minimum partialPathCost of each path instance 
							frontier.remove(comp); //if check is passed, remove comparison node and add new node to list 
							frontier.addSorted(child);
						}
				} else {
					frontier.addSorted(child);
					}
					}
				}
				
			}
			
		}
		}
			while (repeated_state == false) {
			
			if (!frontier.isEmpty() && !(currNode.depth >= limit-1)) {

				
			currNode = frontier.removeTop(); //might have to move this later
			
			if (currNode.isDestination(destinationLoc) == true) {
				return currNode;
			} else {
				currNode.expand();
				expansionCount++;
				frontier.addSorted(currNode.children);
				}
			}
			}
		}
		return null;
		
		}
		
	

}
