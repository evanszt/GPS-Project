/**
 * This class will create a path from the starting node to the end node and hold its total cost
 * @author zacha
 *
 */
public class Path implements Comparable<Path>{

	public Vertex v;
	public String pathStr;
	public int cost;
	
	public Path() {
		v = null;
		pathStr = null;
		cost = 0;
	}
	
	public Path(Vertex v, int cost, String pathStr) {
		this.v = v;
		this.cost = cost;
		this.pathStr = pathStr;
	}
	
	
	
	@Override
	public int compareTo(Path o) {
		return o.cost-cost;
	}
	
	@Override
	public String toString() {
		return pathStr + " Cost: " + cost;
	}

	
}
