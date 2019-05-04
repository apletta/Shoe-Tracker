/**
 * Filename:   ShoeInfo.java
 * Project:    final
 * Authors:    Liang Shang
 * Date:       4/25/2019
 * 
 * ShoeInfo class to store profuct number, shoe name, total quantity of shoe
 */
package application;

import javafx.scene.image.Image;

public class ShoeInfo {
	
	public int productNumber;
	public String name;
	public int totalQuantity;
	public Image image;
	public ShoeSizeList shoeSizeList;

	
	public ShoeInfo(int productNumber, String name, int totalQuantity, ShoeSizeList shoeSizeList) {
		this.productNumber = productNumber;
		this.name = name;
		this.totalQuantity = totalQuantity;
		this.shoeSizeList = shoeSizeList;
	}
	
	/**
	 * Overloaded constructor to allow for linking image to shoe
	 * 
	 * @param productNumber
	 * @param name
	 * @param totalQuantity
	 * @param image
	 */
	public ShoeInfo(int productNumber, String name, int totalQuantity, Image image, ShoeSizeList shoeSizeList) {
		this.productNumber = productNumber;
		this.name = name;
		this.totalQuantity = totalQuantity;
		this.image = image;
		this.shoeSizeList = shoeSizeList;
	}

}
