import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

public class ShortestPathtoLeyla {
	/*
	 * Dijkstra's algorithm is implemented in this part. Finds the path with minimum cost from starting city to destination.
	 * Returns the appropriate path.
	 * (ID's of cities are also involved while sorting to get same input)
	 */
	public static ArrayList<String> findShortestPath(City start, City dest, HashMap<String,City> cities) {
		PriorityQueue<City> citiestovisit = new PriorityQueue<City>();
		start.dest = 0;
		citiestovisit.add(start);
		while(!citiestovisit.isEmpty()) {
			City currcity = citiestovisit.poll();
			if(currcity.visited == true || currcity.name.equals(dest.name)) continue;
			
			currcity.visited = true;
			for(City neighbour:currcity.neighbours.keySet()) {
				int length = currcity.neighbours.get(neighbour);
				if(currcity.dest + length < neighbour.dest) {
					neighbour.parent = currcity.name;
					neighbour.dest = currcity.dest + length;
					citiestovisit.remove(neighbour);
					citiestovisit.add(neighbour);
				}
				
			}
		}
		ArrayList<String> shortestPath = new ArrayList<String>();
		if(dest.dest!=Integer.MAX_VALUE) {
			City curcity = dest;
			shortestPath.add(curcity.name);
			while(curcity.parent != "") {
				shortestPath.add(curcity.parent);
				curcity = cities.get(curcity.parent);
			}
		}
		return shortestPath;
	}
}
