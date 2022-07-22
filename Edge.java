/**
 * This class creates a representation of the edges on the graph with a source point, a destination from that source,
 * and the costs associated with the edge be it distance or time
 * @author zacha
 *
 */
public class Edge {

	public Vertex source, destination;
	public int timeCost, distanceCost;
	
	public Edge(Vertex source, Vertex destination, int timeCost, int distanceCost) {
		this.source = new Vertex("","");
		this.destination = new Vertex("","");
		this.source = source;
		this.destination = destination;
		this.timeCost = timeCost;
		this.distanceCost = distanceCost;
	}
	
	public String toString() {
		return destination.toString();
	}
}
