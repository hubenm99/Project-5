import java.text.NumberFormat;
import java.util.Locale;

/**
 * CS 180 - Project 5
 *
 * This is the Package Class
 *
 * @author Nick Huber, Jon Bradbury, Gabe Efsits
 * @version 12/9/18
 */

public class Package {
    private String id;
    private String product;
    private double weight;
    private double price;
    private ShippingAddress destination;

    /**
     * Default Constructor
     */
    //============================================================================
    public Package() {
        this.id = "";
        this.product = "";
        this.weight = 0;
        this.price = 0;
        this.destination = new ShippingAddress();
    } // end Package

    //============================================================================
    /**
     * Constructor
     *
     * @param id          id number of product
     * @param product     name of product in package
     * @param weight      weight of package
     * @param price       price of product
     * @param destination the destination of the package
     *
     */
    //============================================================================
    public Package(String id, String product, double weight, double price, ShippingAddress destination) {
        this.id = id;
        this.product = product;
        this.weight = weight;
        this.price = price;
        this.destination = destination;
    } // end Package

    //============================================================================

    /**
     * @return id of package
     */
    public String getID() {
        return id;
    } // end getID



    /**
     * @return Name of product in package
     */
    public String getProduct() {
        return product;
    } // end getProduct




    /**
     * @param product the product name to set
     */
    public void setProduct(String product) {
        this.product = product;
    } // end setProduct




    /**
     * @return price of product in package
     */
    public double getPrice() {
        return price;
    } // end getPrice




    /**
     * @param price the price to set
     */
    public void setPrice(double price) {
        this.price = price;
    } // end setPrice




    /**
     * @return Package weight
     */
    public double getWeight() {
        return weight;
    } // end getWeight




    /**
     * @param weight the weight to set
     */
    public void setWeight(double weight) {
        this.weight = weight;
    } // end setWeight



    /**
     * @return The shipping address of package
     */
    public ShippingAddress getDestination() {
        return destination;
    } // end getDestination




    /**
     * @param destination the shipping address to set
     */
    public void setDestination(ShippingAddress destination) {
        this.destination = destination;
    } // end setDestination



    /**
     * @return The package's shipping label.
     */
    public String shippingLabel() {
        NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.US);

        return "====================\n" +
                "TO: " + destination.getName() + "\n" +
                destination.getAddress() + "\n" +
                destination.getCity() + ", " + destination.getState() + ", " +
                Integer.toString(destination.getZipCode()) + "\n" +
                "Weight:" + String.format("%1$13s", String.format("%.2f", weight)) + "\n" +
                "Price:" + String.format("%1$14s", formatter.format(price)) + "\n" +
                "Product:" + product + " \n" +
                "====================";
    }

}