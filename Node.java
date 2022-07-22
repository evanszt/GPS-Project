/**
 * This node class creates nodes that will hold the string letter of the symbol as well
 * as the distance and path associated with the lowest cost to that address relative to the starting point
 * @author zacha
 *
 */
public class Node {
		String letter;
		int distance;
		Node next;
		Path p;
		
		public Node(String letter, int distance, Path p, Node next) {
			this.letter = letter;
			this.next = next;
			this.p = p;
			this.distance = distance;
		}
	}

