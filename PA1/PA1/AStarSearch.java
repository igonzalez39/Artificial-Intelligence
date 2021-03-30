import java.util.*;

public class AStarSearch {

	public int expansionCount;
	public HashSet<String> explored = new HashSet<String>();
	public String initialLoc;
	public StreetMap graph;
	public String destinationLoc;
	public int limit;
	public Location dest;
	
	public AStarSearch (StreetMap graph, String initialLoc, String destinationLoc, int limit){
		
		this.initialLoc = initialLoc;
		this.graph = graph;
		this.destinationLoc = destinationLoc;
		this.limit = limit;
		
	}
	
	public Node search (boolean repeated_state){
		
		expansionCount = 0;
		SortedFrontier frontier = new SortedFrontier(SortBy.f); 
		Node currNode = new Node(graph.findLocation(initialLoc),null);
		GoodHeuristic H = new GoodHeuristic(); 
		
		if (currNode.loc.name == destinationLoc){
			
			return currNode;
			
		}else{
			
		frontier.addSorted(currNode);
		dest = graph.findLocation(destinationLoc);
		H.Hn(graph, dest); 
			
		if (repeated_state == true){
			
			while (!frontier.isEmpty() || currNode.depth > limit){
				currNode = frontier.removeTop(); 
				
			if (currNode.isDestination(destinationLoc) == true){
				return currNode;
				
			} else{
				explored.add(currNode.loc.name); 
				currNode.expand(H);
				expansionCount++;
				Node comp = new Node(); 
			
			for (Node child: currNode.children) {
				
				if (!explored.contains(child.loc.name) && frontier.contains(child) == true) {
					comp = frontier.find(child); //compares heurisitc val of each child to find the lowest 
					if (child.partialPathCost < comp.partialPathCost) {
						frontier.remove(comp);
						frontier.addSorted(child);
						}
					} else {
						frontier.addSorted(child);
					}
				}
				
			}
			
		}
		 
		return null;
		}
		
			while (repeated_state == false) {
				
			if (frontier.isEmpty() || currNode.depth >= limit - 1) {
				break;
			}
			
			currNode = frontier.removeTop(); //might have to move this later
			
			if (currNode.isDestination(destinationLoc) == true) {
				return currNode;
			} else {
				currNode.expand(H);
				expansionCount++;
				frontier.addSorted(currNode.children);
				}
			}
		}
		return null; 

		
	}

}
	
		
		
		
	



