import java.util.NoSuchElementException;

/**
 * This class creates the implementation of a generic type heap priority queue that implements comparable from
 * the path class comparing cost and ordering the elements from lowest to highest
 * @author zacha
 *
 * @param <T> Generic type
 */
public class HeapPriorityQueue <T extends Comparable <? super T>> {

	
	private T[] elements;
	private int size;
	private static final int DEFAULT_CAPACITY = 10;
	
	
	public HeapPriorityQueue() {
		this(DEFAULT_CAPACITY);
	}
	
	public HeapPriorityQueue(int initialCapacity) {
		elements = (T[]) new Comparable[initialCapacity+1];
	}
	
	public HeapPriorityQueue(T[] entries) {
		this(entries.length + 1);
		size = entries.length;
		for (int index = 0; index < entries.length; index++)
			elements[index + 1] = entries[index];
		for (int rIndex = size / 2; rIndex > 0; rIndex--) 
			reheapDown(rIndex);
	}
	
	private void reheapDown(int index) {
		int child1 = index*2;
		int child2 = index*2 + 1;
		while (child1 < size) {
			int difference = elements[child1].compareTo(elements[child2]);
			if (difference >= 0) {
				swap(child1, index);
				index = child1;
				child1 = index*2;
				child2 = index*2 + 1;
			}
			if (difference < 0) {
				swap(child2,index);
				index = child2;
				child1 = index*2;
				child2 = index*2+1;
			}
		}
	}
	
	private void reheapUp(int index) {
		int parentIndex = index/2;
			while(parentIndex > 0 && elements[index].compareTo(elements[parentIndex]) > 0) {
			swap(index, parentIndex);
			index = parentIndex;
			parentIndex = index / 2;
			}
	}
	
	private void swap(int index, int parentIndex) {
		T tmp = elements[index];
		elements[index] = elements[parentIndex];
		elements[parentIndex] = tmp;
	}
	
	public boolean isEmpty() {
		return elements[1] == null;
	}

	public boolean isFull() {
		for (int index = 1; index < size/2 + 1; index++) {
			if (elements[index*2] != null && elements[index*2+1] == null) {
				return false;
			}
		}
		return true;
	}

	public void clear() {
		elements = (T[]) new Comparable[DEFAULT_CAPACITY+1];
		size=0;
	}

	public int size() {
		return size;
	}

	public void add(T newEntry) {
		verifyCapacity();
		elements[++size] = newEntry;
		if (size >= 2) {
			for (int i = 1; i < size; i++) {
				for (int j = i+1; j < size+1; j++) {
					if (elements[i].compareTo(elements[j]) < 0) {
						swap(i,j);
					}
				}
			}
		}
	}

	private void verifyCapacity() {
		if (size+1 == elements.length-1) {
			T[] tmp = (T[]) new Comparable[elements.length*2];
			tmp[0] = null;
			for (int i = 1; i < size+1; i++) {
				tmp[i] = elements[i];
			}
			elements = tmp;
		}
	}

	public T peek() {
		if (isEmpty()) return null;
		return elements[1];
	}

	public T remove() {
		if (isEmpty()) throw new NoSuchElementException();
		T tmp = elements[1];
		elements[1] = elements[size];
		elements[size--] = null;
		reheapDown(1);
		return tmp;
	}
	
	@Override
	public String toString() {
		String ret = "null";
		for (int i = 1; i < size+1; i++) {
			ret += ", " + elements[i].toString();
		}
		return ret.substring(6);
	}

}
