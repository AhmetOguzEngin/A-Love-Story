import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class project3main {
	public static void main(String[] args) {
		File inputfile = new File(args[0]);
		Scanner sc;
		try {
			sc = new Scanner(inputfile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return;
		}
		int timeLimit, numofcities;
		int numdcities = 1;
		String startname, destname;
		City start, destination;
		HashMap<String,City> cities = new HashMap<String,City>();
		//Input handling part
		timeLimit = sc.nextInt();	numofcities = sc.nextInt();
		startname = sc.next(); destname = sc.next();
		
		while(sc.hasNext()) {
			String cityline = sc.nextLine();
			if(cityline == "") {
				continue;
			}
			String[] cityneighbours = cityline.split(" ");
			City currentcity;
			if(!cities.keySet().contains(cityneighbours[0])) {
				currentcity = new City(cityneighbours[0]);
				cities.put(cityneighbours[0], currentcity);
				
			}else {
				currentcity = cities.get(cityneighbours[0]);
				
			}
			if(currentcity.name.charAt(0) == 'd') {
				numdcities++;
			}
			for(int kk = 1; kk<cityneighbours.length; kk+=2) {
				City ncity;
				
				if(!cities.keySet().contains(cityneighbours[kk])) {
					ncity = new City(cityneighbours[kk]);
					cities.put(cityneighbours[kk], ncity);
				
				}else {
					ncity = cities.get(cityneighbours[kk]);
				
				}
				//This part converts directed graph to undirected one.				
				if(currentcity.name.charAt(0) == 'd' || ncity.name.charAt(0) == 'd') {
					if(ncity.neighbours.keySet().contains(currentcity)) {
						currentcity.addNeighbour(ncity, Math.min(Integer.parseInt(cityneighbours[kk+1])
								,ncity.neighbours.get(currentcity)));	
					}
					else {
						currentcity.addNeighbour(ncity, Integer.parseInt(cityneighbours[kk+1]));
					}
					if(currentcity.neighbours.keySet().contains(ncity)) {
						ncity.addNeighbour(currentcity, Math.min(Integer.parseInt(cityneighbours[kk+1])
								,currentcity.neighbours.get(ncity)));	
					}
					else {
						ncity.addNeighbour(currentcity, Integer.parseInt(cityneighbours[kk+1]));
					}
					
				}
				
				else {
					currentcity.addNeighbour(ncity, Integer.parseInt(cityneighbours[kk+1]));
				}
				
			}
		}
		sc.close();
		//Checks if start and destination cities exist in the graph.	
		if(cities.containsKey(startname)) {
			start = cities.get(startname);
		}
		else {
			return;
		}
		if(cities.containsKey(destname)) {
			destination = cities.get(destname);
		}
		else {
			return;
		}
		//This part is to call the function to find the shortest path.		
		ArrayList<String> shortestPath = ShortestPathtoLeyla.findShortestPath(start,destination,cities);
		int taxamount = 0;
		if(shortestPath.size() == 0 || timeLimit < destination.dest) {
			taxamount = -1;
		}
		else {
			destination.dest = 0;
			taxamount = Honeymoon.findRoute(destination, cities,numdcities);
		}
		
		//Output part
		File outfile = new File(args[1]);
		PrintStream out;
		try {
			out = new PrintStream(outfile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return;
		}
		if(shortestPath.size() == 0) {
			out.println("-1");
			
		}
		else{
			out.print(shortestPath.get(shortestPath.size()-1));
			for(int i = shortestPath.size()-2; i>=0;i--) {
				out.print(" "+shortestPath.get(i));
			}
			out.println();
		}
		out.print(taxamount);
		out.close();
	}
}
