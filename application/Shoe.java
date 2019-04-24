package application;

public class Shoe <K extends Comparable<K>, V>{
  protected final K PRODUCT_NUMBER;
  //protected final double SHOE_SIZE;
  protected final V NAME;
  
  public Shoe(K productNum, V name) {
    this.PRODUCT_NUMBER = productNum;
    //this.SHOE_SIZE = size;
    this.NAME = name;
  }
  
}
