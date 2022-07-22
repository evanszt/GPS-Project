import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
/**
 * This class creates a JUnit test of the project, particularly the Dijkstra's algorithm as that is the underlying basis
 * for the functionality of the GUI. It tests that the algorithm properly returns the path for time and distance costs, it will return null when
 * no path is available between a given start and endpoint, and it is able to properly return a different path if there is a less expensive one between
 * two points if the user changes their selection.
 * @author zacha
 *
 */
class Tester {

	Graph g = new Graph("MapInformationXY.txt");
	@Test
	void testGraphRead() {
		int size = 20;
		assertTrue(g.graph.keySet().size() == size);
	}
	
	void testDijkstraDistCost() {
		Path check = new Path(new Vertex("B", "1521 Grassland Ave."), 11, "AQB");
		Path check2 =Dijkstra.shortestPath(g, new Vertex("A", "N Bend Rd."), new Vertex("B", "1521 Grassland Ave."), true);
		assertEquals(check.v, check2.v);
		assertEquals(check.cost, check2.cost);
		assertEquals(check.pathStr, check2.pathStr);
		Path check3 = null;
		Path check4 = Dijkstra.shortestPath(g, new Vertex("M", "15 Goodman Dr."), new Vertex("N", "510 Lake St."), true);
		assertEquals(check3.v, check4.v);
	}
	
	void testDijkstraTimeCost() {
		Path check = new Path(new Vertex("B", "1521 Grassland Ave."),22, "AQB" );
		Path check2 = Dijkstra.shortestPath(g, new Vertex("A", "N Bend Rd."), new Vertex("B", "1521 Grassland Ave."), false);
		assertEquals(check.v, check2.v);
		assertEquals(check.cost, check2.cost);
		assertEquals(check.pathStr, check2.pathStr);
		Path check3 = null;
		Path check4 = Dijkstra.shortestPath(g, new Vertex("M", "15 Goodman Dr."), new Vertex("N", "510 Lake St."), true);
		assertEquals(check3.v, check4.v);
	}
	
	void testDijkstraSwitch() {
		//Using Distance Cost
		Path check1 = new Path(new Vertex("N", "510 Lake St."), 42, "EOPCDN");
		Path check2 = Dijkstra.shortestPath(g, new Vertex("E", "4423 Walnut St."), new Vertex("N", "510 Lake St."), true);
		assertEquals(check1.v, check2.v);
		assertEquals(check1.cost, check2.cost);
		assertEquals(check1.pathStr, check2.pathStr);
		//Using Time Cost
		Path check3 = new Path(new Vertex("N", "510 Lake St."), 16, "EGDN");
		Path check4 = Dijkstra.shortestPath(g, new Vertex("E", "4423 Walnut St."), new Vertex("N", "510 Lake St."), false);
		assertEquals(check3.v, check4.v);
		assertEquals(check3.cost, check4.cost);
		assertEquals(check3.pathStr, check4.pathStr);
	}

}
