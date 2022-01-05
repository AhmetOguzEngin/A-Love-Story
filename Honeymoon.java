import java.util.Currency;
import java.util.HashMap;
import java.util.PriorityQueue;

public class Honeymoon {
	/*This part is to find mst of the second part of graph. This part finds the honeymoon route with mininmum tax cost(mst).
	 *Checks each neighbour of currentcity and puts them in a priorityqueue in which they are sorted according to distance. And finds the mst at the end.
	 *(Prim's algorithm is implemented in that part)
	 */
	public static int findRoute(City start, HashMap<String,City> cities, int numdcities) {
		int totalweight = 0;
		PriorityQueue<City> citiestovisit = new PriorityQueue<City>();
		citiestovisit.add(start);
		while(!citiestovisit.isEmpty()) {
			City currentCity = citiestovisit.poll();
			if(currentCity.visited == true) continue;
			currentCity.visited = true;
			totalweight += currentCity.dest;
			numdcities--;
			for(City neighbour:currentCity.neighbours.keySet()) {
				if(neighbour.dest > currentCity.neighbours.get(neighbour) && neighbour.visited == false) {
					neighbour.parent = currentCity.name;
					neighbour.dest = currentCity.neighbours.get(neighbour);
					if(neighbour.visited == true || neighbour.name.substring(0,1).equals("c")) continue;
					citiestovisit.remove(neighbour);
					citiestovisit.add(neighbour);
				}
			}
		}
		if(numdcities > 0) totalweight = -1;
		return 2*totalweight;
	}
	
}
