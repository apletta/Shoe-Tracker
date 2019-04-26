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

public class ShoeTable implements ShoeTableADT{
	
	private HashArray<Integer, Shoe> shoeTable;
	
	public ShoeTable() {
		this.shoeTable = new HashArray<Integer, Shoe>();
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
			} else {
				return new ShoeInfo(productNumber, current.name, current.totalQuantity);
			}
		} catch (IllegalNullKeyException e) {
			return null;
		}
	}
  
	/**
	 * check quantity of every shoe size
	 * return a string, e.g. ¡°7.5(3) 9.5(8) 10(4) 10.5(2)¡±
	 * if product does not exit, throw KeyNotFoundException
	 * 
	 * @param productNumber
	 * @return a string of shoe size and quantity
	 */
	public String checkSize(int productNumber) {
		try {
			Shoe current = shoeTable.get(productNumber);
			if (current == null) {
				return "";
			} else {
				return current.shoeSizeList.traversal();
			}
		} catch (IllegalNullKeyException e) {
			return null;
		}
	}
	
	/**
	 * decrease quantity from given shoe size
	 * if product does not exit, throw KeyNotFoundException
	 * if shoe size does not exit, throw ShoeSizeNotFoundException
	 * if quantity to be deleted is larger than current, throw QuantityTooLargeException
	 * 
	 * @param productNumber
	 * @param shoeSize
	 * @param quantity
	 */
	public void deleteShoe(int productNumber, double shoeSize, int quantity) {
		try {
			Shoe current = shoeTable.get(productNumber);
			if (current == null) {
				return;
			} else {
				current.shoeSizeList.decrease(shoeSize, quantity);
				current.totalQuantity -= quantity;
			}
			
		} catch (IllegalNullKeyException e) {
			
		}
	}
	
	/**
	 * @return a String list of shoe sizes of given product number
	 */
	public List<String> getSizeList(int productNumber) {
		try {
			Shoe current = shoeTable.get(productNumber);
			if (current != null) {
				return current.shoeSizeList.getKeyList();
			}
		} catch (IllegalNullKeyException e) {
			
		}
		return new ArrayList<String>();
	}
	
	/**
	 * @return the quantity of given product number and shoe size
	 */
	public int getQuantity(int productNumber, double shoeSize) {
		try {
			Shoe current = shoeTable.get(productNumber);
			if (current != null) {
				return current.shoeSizeList.get(shoeSize);
			}
		} catch (IllegalNullKeyException e) {
			
		}
		return 0;
	}

}
