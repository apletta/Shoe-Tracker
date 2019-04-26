package shoetable;

class Main {
	public static void main(String[] args) {
		try {
			ShoeTableADT shoeTable = new ShoeTable();
			shoeTable.addShoe(123456789, "Nike", 8.5, 5);
			shoeTable.addShoe(123456789, "Nike", 8.5, 5);
			
			shoeTable.addShoe(123456789, "Nike", 5.5, 3);
			shoeTable.addShoe(123456789, "Nike", 10.5, 6);
			
			ShoeInfo nike = shoeTable.lookupShoe(123456789);

			shoeTable.deleteShoe(8.5, 5);
			shoeTable.deleteShoe(10.000, 5);
			
			System.out.println(nike.productNumber);
			System.out.println(nike.name);
			System.out.println(nike.totalQuantity);
			System.out.println(shoeTable.checkSize());
			System.out.println("\n");

			System.out.println(shoeTable.getSizeList());

			shoeTable.addShoe(987654321, "Jordan", 10, 7);
			shoeTable.addShoe(987654321, "Jordan", 9, 8);
			
			ShoeInfo jordan = shoeTable.lookupShoe(987654321);
			System.out.println(jordan.productNumber);
			System.out.println(jordan.name);
			System.out.println(jordan.totalQuantity);
			System.out.println(shoeTable.checkSize());
			System.out.println("\n");
			
			System.out.println(shoeTable.getSizeList());
			
			System.out.println(shoeTable.getQuantity(9));
			System.out.println(shoeTable.getQuantity(8));
			
		} catch(Exception e) {
			System.out.println(e.getClass().getName());
		}
	}
}
