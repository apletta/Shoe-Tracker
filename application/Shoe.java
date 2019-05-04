/**
 * Filename:   Shoe.java
 * Project:    final
 * Authors:    Liang Shang
 * Date:       4/25/2019
 * 
 * Shoe class to store shoe name, total quantity of shoe
 * and a linked list contains the quantity of every shoe size
 * 
 * P.S. the product number of shoe is the key of hash table, 
 * and class Shoe is the value of that key in hash table
 */
package application;

import javafx.scene.image.Image;

class Shoe {
	protected final String name;
	protected ShoeSizeList shoeSizeList;
	protected int totalQuantity;
	protected Image image;
	
	protected Shoe(String name) {
		this.name = name;
		this.totalQuantity = 0;
		this.shoeSizeList = new ShoeSizeList();
	}
	
	/**
	 * Overloaded constructor to allow for saving image to associated shoe
	 * 
	 * @param name
	 * @param image
	 */
	protected Shoe(String name, Image image) {
		this.name = name;
		this.totalQuantity = 0;
		this.shoeSizeList = new ShoeSizeList();
		this.image = image;
	}
}
