/**
 * Filename:   ShoeSizeList.java
 * Project:    final
 * Authors:    Liang Shang
 * Date:       4/25/2019
 * 
 * An ordered linked list to store shoe size and corresponding quantity
 */
package shoetable;

class ShoeSizeList {
	
	/**
	 * The inner node class for linked list
	 * Stores shoeSize as key, quantity as value and the next node
	 *
	 */
	private class Node {
		double key;
		int value;
		Node next;
		private Node(double key, int value) {
			this.key = key;
			this.value = value;
		}
	}
	
	
	private Node head;
	private int size;
	
	/**
	 * Construct a new empty linked list
	 */
	protected ShoeSizeList() {
		this.size = 0;
		this.head = new Node(-1, 0);
	}
	
	/**
	 * Add method for linked list
	 * Add key at the end of linked list
	 * If key already exits, increase the quantity of that key
	 * All the key is placed in numeric order
	 * 
	 * @param key
	 * @param value
	 */
	protected void add(double key, int value) {
		Node current = head;
		while (current.next != null) { // traversal to the end of list
			double compare = current.next.key-key;
			if (compare < 0) { // next key is small than the key to be added
				current = current.next;
			} else if (compare == 0) {
				current.next.value += value;
				return;
			} else { // next key is larger than the key to be added
				Node temp = new Node(key, value);
				temp.next = current.next;
				current.next = temp;
				return;
			}
		}
		current.next = new Node(key, value); // insert key, value pair
		this.size++;
	}
	
	/**
	 * Decrease method for linked list
	 * If key is found, decrease quantity of that key
	 * If quantity to be decreased is larger than current quantity, throws QuantityTooLargeException
	 * If at the end of linked list key is not found, throws ShoeSizeNotFoundException
	 * 
	 * @param key
	 * @param value
	 * @throws QuantityTooLargeException
	 * @throws ShoeSizeNotFoundException
	 */
	protected void decrease(double key, int value) throws QuantityTooLargeException, ShoeSizeNotFoundException {
		Node current = head;
		while (current.next != null) { // traversal to the end of list
			double compare = current.next.key-key;;
			if (compare < 0) { // next key is small than the key to be added
				current = current.next;
			} else if (compare == 0) {
				
				if (current.next.value > value) { // if current quantity is larger than quantity to be decreased
					current.next.value -= value;
					return;
				} else if (current.next.value == value) { // if current quantity equals to quantity to be decreased
					current.next = current.next.next; // remove this node
					this.size--;
					return;
				} else { // if current quantity is smaller than quantity to be decreased
					throw new QuantityTooLargeException();
				}
				
			} else { // next key is larger than the key to be added
				throw new ShoeSizeNotFoundException();
			}
		}
		throw new ShoeSizeNotFoundException(); // if key is not found at the end of list
	}
	
	/**
	 * Traversal the whole list and return the quantity of every shoe size
	 * The string is in increasing order of shoe size
	 * e.g. 1.5(2) 5.5(6) 6.5(5) 8.5(7) 
	 * 
	 * @return a string contains the quantity of every shoe size
	 */
	protected String traversal() {
		String list = "";
		Node current = head;
		while (current.next != null) { // traversal to the end of list
			current = current.next;
			double key = current.key;
			if (key-(double)((int)key)==0) {
				list += Integer.toString((int)current.key)+"("+Integer.toString(current.value)+") ";
			} else {
				list += Double.toString(current.key)+"("+Integer.toString(current.value)+") ";
			}
			
		}
		return list;
	}

}
