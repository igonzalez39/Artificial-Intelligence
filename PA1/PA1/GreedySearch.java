import java.util.*;

public class GreedySearch {

	public int expansionCount;
	public HashSet<String> explored = new HashSet<String>();
	public String initialLoc;
	public StreetMap graph;
	public String destinationLoc;
	public int limit;
	
	public GreedySearch (StreetMap graph, String initialLoc, String destinationLoc, int limit) {
		this.initialLoc = initialLoc;
		this.graph = graph;
		this.destinationLoc = destinationLoc;
		this.limit = limit;
		
	}
	
	public Node search (boolean repeated_state) {
		expansionCount = 0;
		SortedFrontier frontier = new SortedFrontier(SortBy.h);
		Node currNode = new Node(graph.findLocation(initialLoc),null);
		GoodHeuristic gh = new GoodHeuristic();
		Location end = graph.findLocation(destinationLoc); //goal state
		
		if (currNode.loc.name == destinationLoc) {
			return currNode;
			
		} else {
			
			frontier.addSorted(currNode);
			
			gh.HofN(graph, end); //gets H(n) value of instance
			
			
		if (repeated_state == true) {
			
			while (!frontier.isEmpty() || currNode.depth >= limit) {
				currNode = frontier.removeTop(); //might remove
				
			if (currNode.isDestination(destinationLoc) == true) {
				return currNode;
				
			} else {
				explored.add(currNode.loc.name); 
				currNode.expand(gh);
				expansionCount++;
			
			for (Node child: currNode.children) {
				
				if (explored.contains(child.loc.name) && frontier.contains(child)) {
					continue;
					} else {
						frontier.addSorted(child);
					}
				}
				
			}
			
		}
		}
			while (repeated_state == false) {
			
			if (frontier.isEmpty() || currNode.depth >= limit) {
				return null;
			}
			
			currNode = frontier.removeTop(); //might have to move this later
			
			if (currNode.isDestination(destinationLoc) == true) {
				return currNode;
			} else {
				currNode.expand(gh); // expands child nodes while calculating H(n)
				expansionCount++;
				frontier.addSorted(currNode.children);
				}
			}
			return null;
		}
		
		}
		
	

}


