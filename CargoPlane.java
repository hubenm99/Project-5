import java.util.ArrayList;


/**
 * CS 180 - Project 5
 *
 * This is the CargoPlane Class
 *
 * @author Nick Huber, Jon Bradbury, Gabe Efsits
 * @version 12/9/18
 */

public class CargoPlane extends Vehicle {
    final double gasRate = 2.33;
    private double currentWeight;
    private String licensePlate;
    private double maxWeight;
    private int range;

    /**
     * Default Constructor
     */
    //============================================================================
    public CargoPlane() {
        super();
    } // end CargoPlane

    //============================================================================

    /**
     * Constructor
     *
     * @param licensePlate license plate of vehicle
     * @param maxWeight    maximum weight that the vehicle can hold
     */
    //============================================================================
    public CargoPlane(String licensePlate, double maxWeight) {
        super(licensePlate, maxWeight);
    } // end CargoPlane

    //============================================================================

    /**
     * Overides its superclass method. Instead, after each iteration, the range will
     * increase by 10.
     *
     * @param warehousePackages List of packages to add from
     */
    @Override
    public void fill(ArrayList<Package> warehousePackages) {
        Vehicle vehicle = new Vehicle();
        // adds the packages with zero range
        for (int i = 0; i < warehousePackages.size(); i++) {
            if (warehousePackages.get(i).getDestination().getZipCode() - vehicle.getZipDest() == 0) {
                if (this.currentWeight != this.maxWeight) {
                    vehicle.getPackages().add(warehousePackages.get(i));
                    this.currentWeight += warehousePackages.get(i).getWeight();
                } // end if
            } // end if
        } // end for

        // adds the packages with other zipcodes
        for (int i = 0; i < warehousePackages.size(); i++) {
            if (warehousePackages.get(i).getDestination().getZipCode() - vehicle.getZipDest() != 0) {
                if (this.currentWeight != this.maxWeight) {
                    vehicle.getPackages().add(warehousePackages.get(i));
                    this.currentWeight += warehousePackages.get(i).getWeight();
                    this.range += Math.abs(vehicle.getZipDest() -
                            warehousePackages.get(i).getDestination().getZipCode());
                } // end if
            } // end if
        } // end for

    }

    /*
     * =============================================================================
     * | Methods from Profitable Interface
     * =============================================================================
     */

    /**
     * Returns the profits generated by the packages currently in the Cargo Plane.
     * <p>
     * &sum;p<sub>price</sub> - (range<sub>max</sub> &times; 2.33)
     * </p>
     */
    @Override
    public double getProfit() {
        Package packages = new Package();
        Vehicle vehicle = new Vehicle();

        return packages.getPrice() - (this.range * this.gasRate);

    }

    /**
     * Generates a String of the Cargo Plane report. Cargo plane report includes:
     * <ul>
     * <li>License Plate No.</li>
     * <li>Destination</li>
     * <li>Current Weight/Maximum Weight</li>
     * <li>Net Profit</li>
     * <li>Shipping labels of all packages in cargo plane</li>
     * </ul>
     *
     * @return Cargo Plane Report
     */
    @Override
    public String report() {
        Vehicle vehicle = new Vehicle();
        String shippingLabels = "";

        for (int i = 0; i < vehicle.getPackages().size(); i++) {
            shippingLabels += this.getPackages().get(i).shippingLabel();
        } // end for

        return "==========Truck Report==========\n" +
                "License Plate No.: " + this.licensePlate +
                "Destination: " + vehicle.getZipDest() +
                "Weight Load: " + vehicle.getCurrentWeight() + "/" + this.maxWeight +
                "Net Profit: $" + getProfit() +
                "=====Shipping Labels=====\n" +
                shippingLabels + "\n" +
                "==============================";
    }

    public String getType() {
        return "Cargo Plane";
    }

}