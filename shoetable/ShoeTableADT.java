package shoetable;
/**
 * Filename:   ShoeTableADT.java
 * Project:    final
 * Authors:    Liang Shang
 * Date:       4/25/2019
 * 
 * The ADT connected to GUI
 */

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
	 * if product does not exit, throw KeyNotFoundException
	 * 
	 * @param productNumber
	 * @return a ShoeInfo contains int productNumber, String name, int totalQuantity
	 * @throws KeyNotFoundException 
	 */
	public ShoeInfo lookupShoe(int productNumber) throws KeyNotFoundException;
  
	/**
	 * check quantity of every shoe size
	 * return a string, e.g. ��7.5(3) 9.5(8) 10(4) 10.5(2)��
	 * if product does not exit, throw KeyNotFoundException
	 * 
	 * @param productNumber
	 * @return a string of shoe size and quantity
	 * @throws KeyNotFoundException 
	 */
	public String checkSize(int productNumber) throws KeyNotFoundException;
	
	/**
	 * decrease quantity from given shoe size
	 * if product does not exit, throw KeyNotFoundException
	 * if shoe size does not exit, throw ShoeSizeNotFoundException
	 * if quantity to be deleted is larger than current, throw QuantityTooLargeException
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
