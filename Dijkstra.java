import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * This class implements Dijkstras algorithm using a generic heap priority queue
 * and additionally uses a linked list of nodes to store the values of the cost to each node
 * relative to the starting path for every node in the graph until the a path containing the 
 * end node is found at the beginning of the priority queue
 * @author zacha
 *
 * @param <T> Generic type
 */
public class Dijkstra<T extends Comparable <? super T>> {

	public static int totalCost;
	
	public static Path shortestPath(Graph g, Vertex start, Vertex end, boolean useDistCost) {
		HeapPriorityQueue<Path> pq = new HeapPriorityQueue<Path>();
		pq.add(new Path(start, 0, start.getSymbol()));
		Set<String> visited = new HashSet<String>();
		LinkedListClass dist = new LinkedListClass();
		for (int i = 65; i < 85; i++) {
			char c = (char) i;
			String s = Character.toString(c);
			dist.add(new Node(s, Integer.MAX_VALUE, null,null));
		}
		dist.setDist(start.getSymbol(), 0);
		while (!pq.isEmpty()) {
			Path nextEntry = pq.remove();
			if (nextEntry.v.equals(end)) {
				totalCost = dist.getDist(end.getSymbol());
				return nextEntry;
			}
			visited.add(nextEntry.v.getSymbol());
			for (Vertex key : g.graph.keySet()) {
				if (key.equals(nextEntry.v)) {
			for (Edge child : g.graph.get(key)) {
				if (!visited.contains(child.destination.getSymbol())) {
					int cost = -1;
					if (useDistCost) {
						cost = nextEntry.cost + child.distanceCost;
					}
					else {
						cost = nextEntry.cost + child.timeCost;
					}
					if (cost < dist.getDist(child.destination.getSymbol())) {
						dist.setDist(child.destination.getSymbol(), cost);
					}
					String pathStr = nextEntry.pathStr + child.destination.getSymbol();
					pq.add(new Path(child.destination, cost, pathStr));
					if (cost < dist.getDist(child.destination.getSymbol())) {
					dist.setPath(child.destination.getSymbol(), new Path(child.destination,cost,pathStr));
					}
				}
			}
		}
			}
		}
		totalCost = dist.getDist(end.getSymbol());
		if (totalCost == Integer.MAX_VALUE) {
			return null;
		}
		return dist.getPath(end.getSymbol());
	}
}
			
			
