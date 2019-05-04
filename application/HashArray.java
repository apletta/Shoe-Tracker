/**
 * Filename:   HashArray.java
 * Project:    final
 * Authors:    Liang Shang
 * Date:       4/25/2019
 * 
 * Implementation of hash table
 */
package application;

import java.util.ArrayList;

public class HashArray<K extends Comparable<K>, V> {
	
	/**
	 * The inner linked list class for chained bucket
	 * Stores key and value
	 * Have insert, remove, get methods
	 * Have size, called by Bucketlist.size
	 * Traversal begins at head node, which doesn't store key
	 *
	 * @param <K> key
	 * @param <V> value
	 */
	private class BucketList<K,V> {
		
		/**
		 * The inner node class for linked list
		 * Stores key, value and the next node
		 *
		 * @param <K> key
		 * @param <V> value
		 */
		private class Node<K,V> {
			K key;
			V value;
			Node<K,V> next;
			private Node(K key, V value) {
				this.key = key;
				this.value = value;
			}
		}
		
		private Node<K,V> head;
		private int size;
		
		/**
		 * Construct a new empty linked list
		 */
		private BucketList() {
			this.size = 0;
			this.head = new Node<K,V>(null, null);
		}
		
		/**
		 * Insert method for linked list
		 * Add key at the end of linked list
		 * If key already exits, throw DuplicateKeyException
		 * 
		 * @param key
		 * @param value
		 * @throws DuplicateKeyException
		 */
		private void insert(K key, V value) throws DuplicateKeyException {
			Node<K,V> current = head;
			while (current.next != null) { // get the the end of list
				current = current.next;
				if (current.key.equals(key)) { // if key is found before the end of list
					throw new DuplicateKeyException();
				}
			}
			current.next = new Node<K,V>(key, value); // insert key, value pair
			this.size++;
		}
		
		/**
		 * Remove method for linked list
		 * If key is found, remove key, value pair and reduce size, return true
		 * If at the end of linked list key is not found, key is not found return false
		 * 
		 * @param key
		 * @return true or false
		 */
		private boolean remove(K key) {
			Node<K,V> current = head;
			while (current.next != null) { // traversal to the end of list
				if (current.next.key.equals(key)) { // if key is found, remove it
					current.next = current.next.next;
					this.size--;
					return true;
				}
				current = current.next;
			}
			return false; // if key is not found at the end of list, return false
		}
		
		
		/**
		 * Get method for linked list
		 * If key is found, return the corresponding value
		 * If at the end of linked list key is not found, return null
		 * 
		 * @param key
		 * @return value corresponding to key
		 */
		private V get(K key) {
			Node<K,V> current = head;
			while (current.next != null) { // traversal to the end of list
				current = current.next;
				if (current.key.equals(key)) { // if key is found
					return current.value;
				}
			}
			return null; // if key is not found
		}
	
	}
	
	private ArrayList<BucketList<K,V>> table; // An array of linked nodes
	private int tableSize;
	private double loadFactorThreshold;
	private double loadFactor;
	private int numKeys;
	
	/**
	 * Construct a hash table without given capacity and LF threshold
	 * Initial Capacity = 11
	 * Load factor threshold = 0.75
	 * Construct the table by a loop of constructing linked list
	 */
	public HashArray() {
		this.loadFactorThreshold = 0.75;
		this.loadFactor = 0;
		this.tableSize = 11;
		this.table = new ArrayList<BucketList<K,V>>();
		this.numKeys = 0;
		for (int i=0; i < this.tableSize; i++) {
			this.table.add(new BucketList<K,V>());
		}
	}
	
	/**
	 * Construct a hash table with given capacity and LF threshold
	 * Construct the table by a loop of constructing linked list
	 * 
	 * @param initialCapacity, loadFactorThreshold
	 */
	public HashArray(int initialCapacity, double loadFactorThreshold) {
		this.loadFactorThreshold = loadFactorThreshold;
		this.loadFactor = 0;
		this.tableSize = initialCapacity;
		this.table = new ArrayList<BucketList<K,V>>();
		this.numKeys = 0;
		for (int i=0; i < this.tableSize; i++) {
			this.table.add(new BucketList<K,V>());
		}
	}

	/** 
	 * Compute a positive hash index for any key
	 * 
	 * @param key
	 * @return positive hash index
	 */
	private int hash(K key) {
		return Math.abs(key.hashCode());
	}
	
	/**
	 * Add key, value pair to hash table where the position is given by hash index
	 * Use chained bucket to handle collisions
	 * After every insert, compute load factor
	 * If load factor is not less than load factor threshold, perform rehashing
	 * If key is null, throw IllegalNullKeyException
	 * If key is already in data structure, throw DuplicateKeyException
	 * 
	 * @param key, value
	 */
	public void insert(K key, V value) throws IllegalNullKeyException, DuplicateKeyException {
		if (key == null) {
			throw new IllegalNullKeyException();
		} else {
			int hashIndex = hash(key)%this.tableSize;
			this.table.get(hashIndex).insert(key, value); // insert key, value pair into linked list
			this.numKeys++;
			this.loadFactor = (double) this.numKeys/this.tableSize; // compute load factor
			if (this.loadFactor >= this.loadFactorThreshold) {
				rehashing(); // if load factor is not less than load factor threshold, perform rehashing
			}
		}
	}

	/**
	 * Rehash when load factor is not less than load factor threshold
	 * Enlarge the table size to 2*tableSize+1
	 * Recompute hash index for every key, value pair and reinsert them
	 */
	private void rehashing() throws DuplicateKeyException {
		ArrayList<BucketList<K,V>> oldTable = this.table;
		this.tableSize = this.tableSize*2+1;
		this.table = new ArrayList<BucketList<K,V>>(); // construct new hash table with larger size
		for (int i=0; i < this.tableSize; i++) {
			this.table.add(new BucketList<K,V>());
		}
		for(int i=0; i<oldTable.size(); i++) { // perform loop to visit every list in the old table
			BucketList<K,V>.Node<K,V> current = oldTable.get(i).head;
			while (current.next != null) { // if list contains elements, reinsert them into the new table
				current = current.next;
				int hashIndex = hash(current.key)%this.tableSize;
				this.table.get(hashIndex).insert(current.key, current.value);
			}
		}
		this.loadFactor = (double) this.numKeys/this.tableSize; // update load factor
	}
	
	/**
	 * If key is found, remove the key, value pair from hash table, reduce numKeys and return true
	 * If key is not found, do not reduce numKeys and return false
	 * If key is null, throw IllegalNullKeyException
	 * 
	 * @param key
	 * @return whether removing succeeds
	 */
	public boolean remove(K key) throws IllegalNullKeyException{
		if (key == null) {
			throw new IllegalNullKeyException();
		} else {
			int hashIndex = hash(key)%this.tableSize;		
			if (this.table.get(hashIndex).remove(key)) { // if key, value pair is removed
				this.numKeys--;
				return true;
			} else { // if key, value pair is not removed
				return false;
			}
		}
	}

	/**
	 * Return the value associated with the specified key
	 * Do not remove key or decrease number of keys
	 * 
	 * @param key
	 * @return value corresponding to key
	 */
	public V get(K key) throws IllegalNullKeyException {
		if (key == null) {
			throw new IllegalNullKeyException();
		} else {
			int hashIndex = hash(key)%this.tableSize;
			return this.table.get(hashIndex).get(key);
		}
	}
	
	/**
	 * @return the number of key,value pairs in the data structure
	 */
	public int numKeys() {
		return this.numKeys;
	}
	
	/**
	 * @return the load factor threshold that was passed into the constructor
	 */
	public double getLoadFactorThreshold() {
		return this.loadFactorThreshold;
	}
	
	/**
	 * load factor = number of items / current table size 
	 * @return the current load factor for this hash table
	 */
	public double getLoadFactor() {
		return this.loadFactor;
	}
		
	/**
	 * @return the current table size
	 */
	public int getCapacity() {
		return this.tableSize;
	}

}
