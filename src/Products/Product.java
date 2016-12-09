package Products;

import Common.Utils;
import static Common.Validation.*;
import Manufacturers.Manufacturer;
import java.math.BigDecimal;

/**
 * Creates an instance of a product
 * @author Anthony Barszcz
 */
public class Product {
    //<editor-fold desc="Class Variables">
    private String name, productID;
    private BigDecimal price, discount;
    private Manufacturer mfact;
    //</editor-fold>
    
    //<editor-fold desc="Class Constants">
    private static final BigDecimal MAX_DISCOUNT = new BigDecimal("1.00");
    //</editor-fold>
    
    public Product(String name, BigDecimal price, BigDecimal discount,
	    Manufacturer mfact) {
        this.name = name;
        this.productID = Utils.generateProductID();
	this.price = price;
	this.discount = discount;
	this.mfact = mfact;
    } //end of Product constructor
    
    //<editor-fold desc="Accessor Methods">
    public String getName() { return name; }
    public String getProductID() { return productID; }
    public BigDecimal getPrice() { return price; }
    public BigDecimal getDiscount() { return discount; }
    public Manufacturer getMfact() { return mfact; }
    @Override public String toString() {
	return "Product Name: " + getName() + "\n"
		+ "Product Price: " + getPrice() + "\n"
		+ "Product Discount: " + getDiscount() + "\n"
		+ "Product ID: " + getProductID() + "\n"
		+ "Product Manufacturer: " + getMfact() + "\n";
    }
    //</editor-fold>
    
    //<editor-fold desc="Mutator Methods">
    public void setName(String name) {
	if(isValid(name))
	    this.name = name;
    }
    
    public void setPrice(BigDecimal price) {
	if(isValid(price))
	    this.price = price;
    }
    
    public void setDiscount(BigDecimal discount) {
	if(isValid(discount, MAX_DISCOUNT))
	    this.discount = discount;
    }
    
    public void setMfact(Manufacturer mfact) {
	this.mfact = mfact;
    }
    //</editor-fold>
} //end of class
