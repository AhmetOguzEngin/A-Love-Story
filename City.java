import java.util.HashMap;

public class City implements Comparable<City>{
	/*
	 * This class is to keep cities informations and also helps implementing algorithms.
	 * This class keeps neighbors, distance, id, name of the cities.
	 */
	String name;
	int id;
	HashMap<City,Integer> neighbours;
	String parent;
	boolean visited;
	int dest;
	public City(String name) {
		this.name = name;
		this.visited = false;
		this.dest = Integer.MAX_VALUE;
		this.neighbours = new HashMap<City,Integer>(); 
		this.parent = "";
		this.id = Integer.parseInt(name.substring(1));
	}
	
	public void addNeighbour(City city, int length) {
		neighbours.put(city, length);
	}

	@Override
	public int compareTo(City o) {
		if(this.dest< o.dest) return -1;
		else if(o.dest < this.dest) return 1;
		return this.id - o.id;
	}
}
