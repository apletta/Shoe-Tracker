/**
 * Filename:   ShoeInfo.java
 * Project:    final
 * Authors:    Liang Shang
 * Date:       4/25/2019
 * 
 * ShoeInfo class to store profuct number, shoe name, total quantity of shoe
 */
package shoetable;

import javafx.scene.image.Image;

public class ShoeInfo {
	
	public int productNumber;
	public String name;
	public int totalQuantity;
	public Image image;
	
	public ShoeInfo(int productNumber, String name, int totalQuantity) {
		this.productNumber = productNumber;
		this.name = name;
		this.totalQuantity = totalQuantity;
	}
	
	/**
	 * Overloaded constructor to allow for linking image to shoe
	 * 
	 * @param productNumber
	 * @param name
	 * @param totalQuantity
	 * @param image
	 */
	public ShoeInfo(int productNumber, String name, int totalQuantity, Image image) {
		this.productNumber = productNumber;
		this.name = name;
		this.totalQuantity = totalQuantity;
		this.image = image;
	}

}
