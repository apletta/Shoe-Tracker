package shoetable;
/**
 * Filename:   ShoeInfo.java
 * Project:    final
 * Authors:    Liang Shang
 * Date:       4/25/2019
 * 
 * ShoeInfo class to store profuct number, shoe name, total quantity of shoe
 */

class ShoeInfo {
	
	int productNumber;
	String name;
	int totalQuantity;
	
	protected ShoeInfo(int productNumber, String name, int totalQuantity) {
		this.productNumber = productNumber;
		this.name = name;
		this.totalQuantity = totalQuantity;
	}

}
