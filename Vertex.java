/**
 * This class creates a vertex that will be used as the key in the graph and as one of the inputs for the edge class.
 * These objects hold the symbol and address associated with that point on the graph.
 * @author zacha
 *
 */
public class Vertex {

	public String symbol, address;
	
	Vertex(String symbol, String address) {
		this.symbol = symbol;
		this.address = address;
	}
	
	public String getSymbol() {
		return symbol;
	}
	
	public String getAddress() {
		return address;
	}
	
	public String toString() {
		return symbol;
	}
	public boolean equals(Vertex o) {
		return symbol.equals(o.getSymbol()) && address.equals(o.getAddress());
	}
}
