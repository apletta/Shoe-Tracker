package application;

public interface ShoeTableADT<K extends Comparable<K>, V> {

  public void addShoe(K productNum, V name);
  
  public boolean removeShoe(K productNum);
  
  public V getShoeName(K productNum);
  
  public K getProductNumber(V name);
}
