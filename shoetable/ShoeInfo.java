/**
 * Filename:   ShoeInfo.java
 * Project:    final
 * Authors:    Liang Shang
 * Date:       4/25/2019
 * 
 * ShoeInfo class to store profuct number, shoe name, total quantity of shoe
 */
package shoetable;

class ShoeInfo {
	
	public int productNumber;
	public String name;
	public int totalQuantity;
	
	public ShoeInfo(int productNumber, String name, int totalQuantity) {
		this.productNumber = productNumber;
		this.name = name;
		this.totalQuantity = totalQuantity;
	}

}
