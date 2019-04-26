package shoetable;

class Shoe {
	protected final String name;
	protected ShoeSizeList shoeSizeList;
	protected int totalQuantity;
	
	protected Shoe(String name) {
		this.name = name;
		this.totalQuantity = 0;
		this.shoeSizeList = new ShoeSizeList();
	}
}
