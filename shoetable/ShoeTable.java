package shoetable;

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
	 * if product does not exit, return null
	 * 
	 * @param productNumber
	 * @return a ShoeInfo contains int productNumber, String name, int totalQuantity
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
	 * 
	 * @param productNumber
	 * @return a string of shoe size and quantity
	 */
	public String checkSize(int productNumber) throws KeyNotFoundException {
		try {
			Shoe current = shoeTable.get(productNumber);
			if (current == null) {
				throw new KeyNotFoundException();
			} else {
				return current.shoeSizeList.traversal();
			}
		} catch (IllegalNullKeyException e) {
			return null;
		}
	}
	
	/**
	 * decrease quantity from given shoe size
	 * if quantity to be deleted is larger than current, it will return false
	 * 
	 * @param productNumber
	 * @param shoeSize
	 * @param quantity
	 * @throws ShoeSizeNotFoundException 
	 * @throws QuantityTooLargeException 
	 * @throws KeyNotFoundException 
	 */
	public void deleteShoe(int productNumber, double shoeSize, int quantity) 
			throws KeyNotFoundException, QuantityTooLargeException, ShoeSizeNotFoundException{
		try {
			Shoe current = shoeTable.get(productNumber);
			if (current == null) {
				throw new KeyNotFoundException();
			} else {
				current.shoeSizeList.decrease(shoeSize, quantity);
				current.totalQuantity -= quantity;
			}
			
		} catch (IllegalNullKeyException e) {
			
		}
	}

}
