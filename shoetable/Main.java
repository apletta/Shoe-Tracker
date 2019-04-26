package shoetable;


class Stock {
  public static ShoeTableADT shoeTable = new ShoeTable();
}

class Main {
	public static void main(String[] args) {
		try {
		  
		  Stock.shoeTable.addShoe(123456789, "Nike", 8.5, 5);
		  Stock.shoeTable.addShoe(123456789, "Nike", 5.5, 3);
		  Stock.shoeTable.addShoe(123456789, "Nike", 10.5, 6);
		  Stock.shoeTable.deleteShoe(123456789, 8.5, 4);
		  System.out.println(Stock.shoeTable.checkSize(123456789));
			
		} catch(Exception e) {
			System.out.println(e.getClass().getName());
		}
	}
}
