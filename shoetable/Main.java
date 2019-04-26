package shoetable;

class Main {
	public static void main(String[] args) {
		try {
			ShoeTableADT shoeTable = new ShoeTable();
			shoeTable.addShoe(123456789, "Nike", 8.5, 5);
			shoeTable.addShoe(123456789, "Nike", 8.5, 5);
			
			shoeTable.addShoe(987654321, "Jordan", 10, 7);
			shoeTable.addShoe(987654321, "Jordan", 9, 8);
			
			shoeTable.addShoe(123456789, "Nike", 5.5, 3);
			shoeTable.addShoe(123456789, "Nike", 10.5, 6);
			
			shoeTable.deleteShoe(123456789, 8.5, 9);
			shoeTable.deleteShoe(987654321, 10, 3);
			
			ShoeInfo nike = shoeTable.lookupShoe(123456789);
			System.out.println(nike.productNumber);
			System.out.println(nike.name);
			System.out.println(nike.totalQuantity);
			System.out.println(shoeTable.checkSize(123456789));
			
			ShoeInfo jordan = shoeTable.lookupShoe(987654321);
			System.out.println(jordan.productNumber);
			System.out.println(jordan.name);
			System.out.println(jordan.totalQuantity);
			System.out.println(shoeTable.checkSize(987654321));
		} catch(Exception e) {
			System.out.println(e.getClass().getName());
		}
	}
}
