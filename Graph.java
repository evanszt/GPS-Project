import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 * This class reads in a given text file and will create an instance of map that uses vertex as the key and uses a set
 * of type Edge as the values. This allows for a graphical representation of each node on the graph and the nodes that are connected
 * to it as well as the cost (distance/time) associated with that.
 * @author zacha
 *
 */
public class Graph {

	
	public static boolean useDistCost, returnAddress;
	public Map <Vertex, Set<Edge>> graph = new HashMap<Vertex, Set<Edge>>();
	
	
	Graph(String fileName) {
		readFile(fileName);
	}
	
	public void readFile(String fileName) {
        try {
            Scanner file = new Scanner(new File(fileName));
            String line = file.nextLine();     
            Map<String, String> vertices = new HashMap<String, String>();
            while (!line.equals("<Nodes>")) { line = file.nextLine(); }
            file.nextLine();
            line = file.nextLine();
            while (!line.equals("</Nodes>")) {
                String[] s = line.split("\t");
                vertices.put(s[0], s[1]);
                line = file.nextLine();
            }
            while (!line.equals("<Edges>")) { line = file.nextLine(); }
            file.nextLine();
            line = file.nextLine();
            String[] s = line.split("\t");
            while (!line.equals("</Edges>")) {
                Vertex v = new Vertex(s[0], vertices.get(s[0]));
                Set<Edge> edges = new HashSet<Edge>();
                do {
                    Vertex destination = new Vertex(s[1], vertices.get(s[1]));
                    edges.add(new Edge(v, destination, Integer.parseInt(s[2]), Integer.parseInt(s[3])));
                    line = file.nextLine();
                    s = line.split("\t");
                } while (s[0].equals(v.getSymbol()));
                graph.put(v, edges);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception f) {
            f.printStackTrace();
        }
    }
	
	@Override
	public String toString() {
		String ret = "";
		for (Vertex key : graph.keySet()) {
			ret += (key.getSymbol() + " Connected Nodes: " + graph.get(key).toString());
			ret += "\n";
		}
		return ret;
	}
	
	public void setUseDistCost(boolean t) {
		useDistCost = t;
	}
	
	public static void setReturnAddress(boolean t) {
		returnAddress = t;
	}
	
	public Vertex getVertex(String s) {
		for (Vertex key : graph.keySet()) {
			if (key.getSymbol().equals(s)) return key;
		}
		return null;
	}
}
