//
// GoodHeuristic
//
// This class extends the Heuristic class, providing a reasonable
// implementation of the heuristic function method. The provided "good"
// heuristic function is admissible.
//
// Ilsia Gonzalez -- 10/16/2020
//


// IMPORT ANY PACKAGES THAT YOU NEED.
// import java.util.*;


public class GoodHeuristic extends Heuristic {
	
	double Maximum = 0.0;

        // YOU CAN ADD ANYTHING YOU LIKE TO THIS CLASS ... WHATEVER WOULD
        // ASSIST IN THE CALCULATION OF YOUR GOOD HEURISTIC VALUE.

	// heuristicValue -- Return the appropriate heuristic values for the
	// given search tree node. Note that the given Node should not be
	// modified within the body of this function.
	public double heuristicValue(Node thisNode) {
		
		double hVal = 0.0;

		// COMPUTE A REASONABLE HEURISTIC VALUE HERE
		hVal = dist(thisNode.loc, getDestination())/Maximum;
		return (hVal);
	}
	
	public double dist(Location st, Location end){
		
		double TotalLongitude = st.longitude - end.longitude;
		double TotalLatitude = st.latitude - end.latitude;
		double coordinates = Math.sqrt((TotalLatitude * TotalLatitude)+(TotalLongitude * TotalLongitude));
			return coordinates;
	}
	
	public double MaximumVelocity(StreetMap graph){
		
		double Velocity;
		
		for(Location n: graph.locations){
			for(Road r: n.roads){
				Velocity = dist(r.fromLocation, r.toLocation)/r.cost;
				if(Velocity > Maximum){
					Maximum = Velocity;
				}
			}
		}
		
		return Maximum
		
	}
	
	public void Hn(StreetMap graph, Location endpoint){
		
		MaximumVelocity(graph);
		setDestination(endpoint);
		
	}
	
	
	
	

}
