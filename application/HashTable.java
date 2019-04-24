/*
 * Filename:   HashTable.java
 * Project:    Final Project
 * Date:       Apr 24, 2019
 * Authors:    Lu Duan
 *
 * Semester:   Spring 2019
 * Course:     CS400
 * Instructor: Deppeler (deppeler@cs.wisc.edu)
 *
 */


/*
 * Hash table use array of linked node (strategy 5) 
 * 
 * Collision resolution: bucket
 * 
 * Hashing algorithm:
 * 		key.hashCode % tableSize to get the hash index
 * 
 * When insert, check if head node is null, if yes, insert to table, otherwise append to the end of the bucket
 * When get and remove, check if the spot has been taken, if yes, get or remove from the bucket, otherwise remove from table
 * 
 * If load factor = # key,value pairs / tableSize exceeds loadFactorThreshold, table will be resize
 * During resize, table size expend to 2 * tableSize + 1, and all key,value pairs will be rehashed
 */

package application;

public class HashTable<K extends Comparable<K>, V> implements HashTableADT<K, V> {
	
	@SuppressWarnings("hiding")
	protected class hashNode<K, V> {
		private K key;
		private V value;
		private hashNode<K, V> next;  // Use LinkedList in bucket
		
		public hashNode(K key, V value) {
			this.key = key;
			this.value = value;
			this.next = null;
		}
		
	}
	
	/*
	 * table: array of hashNode<K, V>
	 * numKeys: number of <key, value> pair in table
	 * tableSize: the size of table. When loadFactor reach its threshold, tableSize * 2 + 1
	 * loadFactorThres: threshold for doubling tableSize
	 */
	private hashNode<K, V>[] table;
	private int numKeys;
	private int tableSize;
	private double loadFactorThres;
	
	private static final int INIT_SIZE = 31;  // 31 is prime
	private static final double INIT_LF_THRESHOLD = 0.75; 
		

	// Initialize with default size and threshold
	@SuppressWarnings("unchecked")
	public HashTable() {
		numKeys = 0;
		tableSize = INIT_SIZE;
		loadFactorThres = INIT_LF_THRESHOLD;
		table = (hashNode[]) new hashNode[tableSize];
		
		for (int i = 0; i < tableSize; i++) {
			table[i] = null;
		}
	}
	
	/*
	 * Initialize with given initialCapacity and loadFactorThreshold
	 */
	@SuppressWarnings("unchecked")
	public HashTable(int initialCapacity, double loadFactorThreshold) {
		numKeys = 0;
		tableSize = initialCapacity;
		loadFactorThres = loadFactorThreshold;
		table = (hashNode[]) new hashNode[tableSize];
		
		// initialize table to be empty
		for (int i = 0; i < tableSize; i++) {
			table[i] = null;
		}
	}

	
	/*
	 * Get the hash index based on key.hashCode()
	 */
	private int getHashIndex(K key) {
		return key.hashCode() % tableSize;
	}
	
	/*
	 * Resize and rehash when loadFactorThres has been reached
	 */
	@SuppressWarnings("unchecked")
	private void resize() throws DuplicateKeyException {
		
		numKeys = 0;
		tableSize = tableSize * 2 + 1;
		
		hashNode<K, V>[] temp = table;
		table = (hashNode[]) new hashNode[tableSize];

		// initialize
		for (int i = 0; i < tableSize; i++) {
			table[i] = null;
		}
		
		// rehash
		for (hashNode<K, V> node : temp) {
			while(node != null) {
				put(table, node.key, node.value);
				node = node.next;
			}
		}
	}
	
	

	public void insert(K key, V value) throws IllegalNullKeyException, DuplicateKeyException {
		
		if ((key == null || value == null )) throw new IllegalNullKeyException();
		
		if ((double)(numKeys + 1) / (double)tableSize >= loadFactorThres) 
			resize();
		
		put(table, key, value);  // insert to the table

	}
	
	private void put(hashNode<K, V>[] ht, K key, V value) throws DuplicateKeyException {
		
		int hashIndex = Math.abs(getHashIndex(key));
		
		hashNode<K, V> newNode = new hashNode<K, V>(key, value);  // Construct the new hashNode for insert
		hashNode<K, V> head = ht[hashIndex];
		
		// Check if collision exist, if yes insert to the end of bucket
		// otherwise insert at position of hashIndex in the table
		if (head != null) {
			
			if (head.key == key)
				throw new DuplicateKeyException();
			
			while (head.next != null) {		// find the end node of bucket
				head = head.next;
			}
			
			head.next = newNode;  // insert at the end of bucket
			numKeys++;
			
		} else {
			ht[hashIndex] = newNode;  // insert at the index of table
			numKeys++;
		}
	}


	public boolean remove(K key) throws IllegalNullKeyException {
		return delete(key);
	}	
	
	
	/*
	 * If key is found, 
	 * 		remove the key,value pair from the table
	 * 		decrease number of keys.
	 * 		return true
	 * If key is null, throw IllegalNullKeyException
	 * If key is not found, return false
	 */
	private boolean delete(K key) throws IllegalNullKeyException {
		
		if (key == null) throw new IllegalNullKeyException();
		
		int hashIndex = Math.abs(getHashIndex(key));
		hashNode<K, V> node = table[hashIndex];
		
		if (node == null) 
			return false;  // key not found
		else {
			hashNode<K, V> nextNode = node.next;  // to trace the previous node in case current node need to be deleted
			
			if (node.key.equals(key)) {  // remove head node in bucket
				node = nextNode;
				numKeys--;
				return true;
			} else {
				while (nextNode != null) {  // remove non-head node in bucket
					if (nextNode.key.equals(key)) {
						node.next = nextNode.next;
						numKeys--;
						return true;
					} else {
						node = nextNode;
						nextNode = nextNode.next;
					}
					
				}
			}
		}
		
		return false;
	}
	
	/*
	 * Returns the value associated with the specified key
	 * Does not remove key or decrease number of keys
	 * 
	 * If key is null, throw IllegalNullKeyException 
	 * If key is not found, throw KeyNotFoundException()
	 */
	public V get(K key) throws IllegalNullKeyException, KeyNotFoundException {
		
		if (key == null) throw new IllegalNullKeyException();
		
		int hashIndex = Math.abs(getHashIndex(key));
		hashNode<K, V> node = table[hashIndex];
		
		while (node != null) {
			if (node.key == key) {
				return node.value;
			} else
				node = node.next;
			}
		
		throw new KeyNotFoundException();
		
	}
	
	
	public int numKeys() {
		return numKeys;
	}
	
	
	public double getLoadFactorThreshold() {
		return loadFactorThres;
	}
	
	
	
	public double getLoadFactor() {
		double loadFactor = (double)numKeys / (double)tableSize;
		return loadFactor;
	}
	
	
	
	public int getCapacity() {
		return tableSize;
	}
	
	


	@Override
	public void addShoe(K productNum, V name) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean removeShoe(K productNum) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public V getShoeName(K productNum) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public K getProductNumber(V name) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
