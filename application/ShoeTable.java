package application;

public class ShoeTable<K extends Comparable<K>, V> implements HashTableADT<K, V>{
  
  // the initial size of the hash table
  private int initialCapacity;
  // used to measure how full the hash table is
  private double loadFactorThreshold;
  // number of elements in hash table
  private int numKeys;
  // the current size of the hash table (used when resizing)
  private int currentCapacity;
  // the array that will store all elements in hash table
  private Shoe<K, V>[] hashArray;
  
  public ShoeTable() {
    this(11,0.75);
  }
  
  public ShoeTable(int initialCapacity, double loadFactor) {
    this.initialCapacity = initialCapacity;
    this.currentCapacity = initialCapacity;
    this.loadFactorThreshold = loadFactor;
    this.numKeys = 0;
    this.hashArray = new Shoe[initialCapacity];
  }

  @Override
  public void addShoe(K productNum, V name) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public V getShoeName(K productNum) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public boolean removeShoe(K productNum) {
    // TODO Auto-generated method stub
    return true;
  }

  @Override
  public K getProductNumber(V name) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public double getLoadFactorThreshold() {
    // TODO Auto-generated method stub
    return loadFactorThreshold;
  }

  @Override
  public double getLoadFactor() {
    // TODO Auto-generated method stub
    return (double) numKeys / currentCapacity;
  }

  @Override
  public int getCapacity() {
    // TODO Auto-generated method stub
    return this.currentCapacity;
  }


}
