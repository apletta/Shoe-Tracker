/**
 * Filename:   ShoeTable.java
 * Project:    final
 * Authors:    Liang Shang
 * Date:       4/25/2019
 * 
 * A class implements all the methods in ShoeTableADT
 */
package shoetable;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.Image;

public class ShoeTable implements ShoeTableADT{
	
	private HashArray<Integer, Shoe> shoeTable;
	private Shoe shoe;
	
	public ShoeTable() {
		this.shoeTable = new HashArray<Integer, Shoe>();
		this.shoe = null;
	}
  
	/**
	 * if productNumber already exits, add shoeSize and quantity to that Shoe class
	 * if productNumber does not exit, construct a new Shoe class
	 * 
	 * @param productNumber
	 * @param name
	 * @param shoeSize
	 * @param quantity
	 */
	public void addShoe(int productNumber, String name, double shoeSize, int quantity) {
		try {
			Shoe current = shoeTable.get(productNumber);
			if (current == null) {
				current = new Shoe(name);
				shoeTable.insert(productNumber, current);
			}
			current.shoeSizeList.add(shoeSize, quantity);
			current.totalQuantity += quantity;
		} catch (Exception e) {
			
		}
	}
	
	/**
	 * Overloaded addShoe constructor to allow for adding image to Shoe.java object
	 * 
	 * @param productNumber
	 * @param name
	 * @param shoeSize
	 * @param quantity
	 * @param image
	 */
	public void addShoe(int productNumber, String name, double shoeSize, int quantity, Image image) {
		try {
			Shoe current = shoeTable.get(productNumber);
			if (current == null) {
				current = new Shoe(name, image);
				shoeTable.insert(productNumber, current);
			}
			current.shoeSizeList.add(shoeSize, quantity);
			current.totalQuantity += quantity;
		} catch (Exception e) {
			
		}
	}
	
	
  
	/**
	 * lookup shoe information from a given productNumber
	 * if product does not exit, throw KeyNotFoundException
	 * 
	 * @param productNumber
	 * @return a ShoeInfo contains int productNumber, String name, int totalQuantity
	 * @throws KeyNotFoundException 
	 */
	public ShoeInfo lookupShoe(int productNumber) throws KeyNotFoundException {
		try {
			Shoe current = shoeTable.get(productNumber);
			if (current == null) {
				throw new KeyNotFoundException();
			} else if(current.image != null) { // if shoe has an image linked to it, pull up ShoeInfo with image
				this.shoe = current;
				return new ShoeInfo(productNumber, current.name, current.totalQuantity, current.image);
			}
			else {
				this.shoe = current;
				return new ShoeInfo(productNumber, current.name, current.totalQuantity);
			}
		} catch (IllegalNullKeyException e) {
			return null;
		}
	}
  
	/**
	 * check quantity of every shoe size
	 * return a string, e.g. ��7.5(3) 9.5(8) 10(4) 10.5(2)��
	 * if product does not exit, throw KeyNotFoundException
	 * 
	 * @return a string of shoe size and quantity
	 */
	public String checkSize() {
		return this.shoe.shoeSizeList.traversal();
	}
	
	/**
	 * decrease quantity from given shoe size
	 * if product does not exit, throw KeyNotFoundException
	 * if shoe size does not exit, throw ShoeSizeNotFoundException
	 * if quantity to be deleted is larger than current, throw QuantityTooLargeException
	 * 
	 * @param shoeSize
	 * @param quantity
	 */
	public void deleteShoe(double shoeSize, int quantity) {
		this.shoe.shoeSizeList.decrease(shoeSize, quantity);
		this.shoe.totalQuantity -= quantity;
	}
	
	/**
	 * @return a String list of shoe sizes of given product number
	 */
	public List<String> getSizeList() {
		return this.shoe.shoeSizeList.getKeyList();
	}
	
	/**
	 * @return the quantity of given product number and shoe size
	 */
	public int getQuantity(double shoeSize) {
		return this.shoe.shoeSizeList.get(shoeSize);
	}

}
