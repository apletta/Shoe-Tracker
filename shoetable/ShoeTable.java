/**
 * Filename:   ShoeTable.java
 * Project:    final
 * Authors:    Liang Shang
 * Date:       4/25/2019
 * 
 * A class implements all the methods in ShoeTableADT
 */
package shoetable;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javafx.scene.image.Image;

public class ShoeTable implements ShoeTableADT {

	private HashArray<Integer, Shoe> shoeTable;
	private Shoe shoe;
	private int size;

	public ShoeTable() {
		this.shoeTable = new HashArray<Integer, Shoe>();
		this.shoe = null;
		this.size = 0;
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
			if (current == null) { // only make new shoe if an existing one with same number doesn't exist
				current = new Shoe(name);
				shoeTable.insert(productNumber, current);
				this.size++;
			}
			// whether a shoe exists or not, add the quantity desired to shoe with that
			// product number
			current.shoeSizeList.add(shoeSize, quantity);
			current.totalQuantity += quantity;
		} catch (Exception e) {

		}
	}

	/**
	 * Overloaded addShoe constructor to allow for adding image to Shoe.java object
	 * 
	 * @param productNumber
	 * @param name
	 * @param shoeSize
	 * @param quantity
	 * @param image
	 */
	public void addShoe(int productNumber, String name, double shoeSize, int quantity, Image image) {
		try {

			Shoe current = shoeTable.get(productNumber);

			if (current == null) { // make new shoe if it's new or has a non-default image
				current = new Shoe(name, image);
				shoeTable.insert(productNumber, current);
				this.size++;
			} else { // if an existing shoe may want to apply new image
				Image currImage = current.image; // current image
				boolean sameImage = true;

				try {
					// check for non-stock image
					for (int i = 0; i < currImage.getWidth(); i++) {
						for (int j = 0; j < currImage.getHeight(); j++) {
							// check if images are the same or not
							if (currImage.getPixelReader().getArgb(i, j) != image.getPixelReader().getArgb(i, j)) {
								sameImage = false;
							}
						}
					}

				} catch (IndexOutOfBoundsException e) { // error will be thrown for any two images of different dimensions
					// check for stock image, don't let it go back to no pic
					try {
					Image stockImage = new Image("no-pic.png"); // default pic is that image not available
					for (int i = 0; i < stockImage.getWidth(); i++) {
						for (int j = 0; j < stockImage.getHeight(); j++) {
							// check if images are the same or not
							if (stockImage.getPixelReader().getArgb(i, j) == image.getPixelReader().getArgb(i, j)) {
								sameImage = true; // same image as stock, don't let it go back
							} else {
								sameImage = false; // treat as different images
							}
						}
					}
					} catch (IndexOutOfBoundsException e2) {
						sameImage = false;
					}
				}

				if (!sameImage) {
					current.image = image; // if not the same, update image
				}
			}

			current.shoeSizeList.add(shoeSize, quantity);
			current.totalQuantity += quantity;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void writeToJSON() {
		
		int numKeys =  this.size();
		int i = 0;
		int numFound = 0;
		ArrayList<Integer> productNumbers = new ArrayList<Integer>();

		// Find and save all shoes to list
		while ( numFound < numKeys) {
		    try {
		    	while(this.shoeTable.get(i) == null) {
		    		i ++;
		    	}
		    	
		    	// Add shoes to list for saving
		    	productNumbers.add(i);
			
				numFound++;
				i++;
				
			} catch (IllegalNullKeyException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		// Add shoe info to JSONArray
		
		JSONObject savedShoes = new JSONObject();
        JSONArray shoeArray = new JSONArray();
        
		for(int j=0;j<numKeys;j++) {
			
			ShoeInfo shoe;
			try {
				shoe = this.lookupShoe(productNumbers.get(j));
				List<String> list = shoe.shoeSizeList.getKeyList();
		       
				int count = 0;
				for( String sizeString : list) {
					//ShoeSizeList shoeSizes = shoe.shoeSizeList;
					Double size = Double.parseDouble(sizeString);
					
			        JSONObject shoeDetails = new JSONObject();
			        shoeDetails.put("productNum", Integer.toString(shoe.productNumber));
			        shoeDetails.put("productName", shoe.name);
			        shoeDetails.put("quantity", Integer.toString(shoe.totalQuantity));
			     // cannot save image because file path to user image not saved, could improve
			        shoeDetails.put("image", "no-pic.png"); 
			        shoeDetails.put("size", list.get(count));
			        shoeDetails.put("quantity", Integer.toString(shoe.shoeSizeList.get(size)));
			        count++;
			        
			        shoeArray.add(shoeDetails);
			        
				}
				
		        savedShoes.put("shoes", shoeArray);
				

		        
			} catch (KeyNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			

		}
		
        //Write JSON file
        try (FileWriter file = new FileWriter("mySavedShoes.json")) {
 
            file.write(savedShoes.toJSONString());
            file.flush();
 
        } catch (IOException e) {
            e.printStackTrace();
        }
        
	}

	/**
	 * lookup shoe information from a given productNumber if product does not exit,
	 * throw KeyNotFoundException
	 * 
	 * @param productNumber
	 * @return a ShoeInfo contains int productNumber, String name, int totalQuantity
	 * @throws KeyNotFoundException
	 */
	public ShoeInfo lookupShoe(int productNumber) throws KeyNotFoundException {
		try {
			Shoe current = shoeTable.get(productNumber);

			if (current == null) {
				throw new KeyNotFoundException();
			} else if (current.image != null) { // if shoe has an image linked to it, pull up ShoeInfo with image
				this.shoe = current;
				return new ShoeInfo(productNumber, current.name, current.totalQuantity, current.image, current.shoeSizeList);
			} else {
				this.shoe = current;
				return new ShoeInfo(productNumber, current.name, current.totalQuantity, current.shoeSizeList);
			}
		} catch (IllegalNullKeyException e) {
			return null;
		}
	}

	/**
	 * check quantity of every shoe size return a string, e.g. ��7.5(3) 9.5(8) 10(4)
	 * 10.5(2)�� if product does not exit, throw KeyNotFoundException
	 * 
	 * @return a string of shoe size and quantity
	 */
	public String checkSize() {
		return this.shoe.shoeSizeList.traversal();
	}

	/**
	 * decrease quantity from given shoe size if product does not exist, throw
	 * KeyNotFoundException if shoe size does not exist, throw
	 * ShoeSizeNotFoundException if quantity to be deleted is larger than current,
	 * throw QuantityTooLargeException
	 * 
	 * @param shoeSize
	 * @param quantity
	 */
	public void deleteShoe(double shoeSize, int quantity) {
		this.shoe.shoeSizeList.decrease(shoeSize, quantity);
		this.shoe.totalQuantity -= quantity;
	}

	/**
	 * @return a String list of shoe sizes of given product number
	 */
	public List<String> getSizeList() {
		return this.shoe.shoeSizeList.getKeyList();
	}

	/**
	 * @return the quantity of given product number and shoe size
	 */
	public int getQuantity(double shoeSize) {
		return this.shoe.shoeSizeList.get(shoeSize);
	}
	
	public int size() {
		return this.size;
	}

}
