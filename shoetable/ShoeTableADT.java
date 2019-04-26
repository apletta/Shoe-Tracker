package shoetable;

public interface ShoeTableADT {

	/**
	 * if productNumber already exits, add shoeSize and quantity to that Shoe class
	 * if productNumber does not exit, construct a new Shoe class
	 * 
	 * @param productNumber
	 * @param name
	 * @param shoeSize
	 * @param quantity
	 */
	public void addShoe(int productNumber, String name, double shoeSize, int quantity);
  
	/**
	 * lookup shoe information from a given productNumber
	 * if product does not exit, return null
	 * 
	 * @param productNumber
	 * @return a ShoeInfo contains int productNumber, String name, int totalQuantity
	 * @throws KeyNotFoundException 
	 */
	public ShoeInfo lookupShoe(int productNumber) throws KeyNotFoundException;
  
	/**
	 * check quantity of every shoe size
	 * return a string, e.g. ¡°7.5(3) 9.5(8) 10(4) 10.5(2)¡±
	 * 
	 * @param productNumber
	 * @return a string of shoe size and quantity
	 * @throws KeyNotFoundException 
	 */
	public String checkSize(int productNumber) throws KeyNotFoundException;
	
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
			throws KeyNotFoundException, QuantityTooLargeException, ShoeSizeNotFoundException;
}
