/**
 * This class creates the implementation for a linked list using the node class that will 
 * be used in Dijkstra's algorithm
 * @author zacha
 *
 */

public class LinkedListClass {
	Node head;
		
		public LinkedListClass() {
			this.head = new Node(null,0,null,null);
		}
		
		public boolean add(Node n) {
			if (head.letter == null) {
				head = n;
				return true;
			}
			Node tmp = head;
			while (tmp.next != null) {
				tmp = tmp.next;
			}
			tmp.next = n;
			return true;
		}
		
		public int getDist(String letter) {
			if (head.letter.equals(letter)) return head.distance;
			Node tmp = head;
			while (!tmp.letter.equals(letter)) tmp = tmp.next;
			return tmp.distance;
		}
		
		public void setDist(String letter, int distance) {
			if (head.letter.equals(letter)) { 
				head.distance = distance;
				return;
			}
			Node tmp = head;
			while (!tmp.letter.equals(letter)) {
				tmp = tmp.next;
			}
			tmp.distance = distance;
		}
		
		public void setPath(String letter, Path p) {
			if (head.letter.equals(letter)) head.p = p;
			Node tmp = head;
			while (!tmp.letter.equals(letter)) tmp = tmp.next;
			tmp.p = p;
		}
		
		public Path getPath(String letter) {
			if (head.letter.equals(letter)) return head.p;
			Node tmp = head;
			while (!tmp.letter.equals(letter)) tmp = tmp.next;
			return tmp.p;
		}

	
}
