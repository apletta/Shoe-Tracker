package application;

public interface HashTableADT<K extends Comparable<K>, V> extends ShoeTableADT<K,V> {

  // Returns the load factor threshold that was 
  // passed into the constructor when creating 
  // the instance of the HashTable.
  // When the current load factor is greater than or 
  // equal to the specified load factor threshold,
  // the table is resized and elements are rehashed.
  public double getLoadFactorThreshold() ;

  // Returns the current load factor for this hash table
  // load factor = number of items / current table size 
  public double getLoadFactor() ;

  // Return the current Capacity (table size)
  // of the hash table array.
  //
  // The initial capacity must be a positive integer, 1 or greater
  // and is specified in the constructor.
  // 
  // REQUIRED: When the load factor threshold is reached, 
  // the capacity must increase to: 2 * capacity + 1
  //
  // Once increased, the capacity never decreases
  public int getCapacity() ;
 

  // Returns the collision resolution scheme used for this hash table.
  // Implement with one of the following collision resolution strategies.
  // Define this method to return an integer to indicate which strategy.
  //
   // 1 OPEN ADDRESSING: linear probe
   // 2 OPEN ADDRESSING: quadratic probe
   // 3 OPEN ADDRESSING: double hashing
   // 4 CHAINED BUCKET: array list of array lists
   // 5 CHAINED BUCKET: array list of linked lists
   // 6 CHAINED BUCKET: array list of binary search trees
   // 7 CHAINED BUCKET: linked list of array lists
   // 8 CHAINED BUCKET: linked list of linked lists
   // 9 CHAINED BUCKET: linked list of of binary search trees

}
