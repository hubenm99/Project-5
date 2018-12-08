import javax.xml.crypto.Data;
import java.io.File;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;
import java.util.ArrayList;


/**
 * <h1>Warehouse</h1>
 */

public class Warehouse {
    final static String folderPath = "files/";
    final static File VEHICLE_FILE = new File(folderPath + "VehicleList.csv");
    final static File PACKAGE_FILE = new File(folderPath + "PackageList.csv");
    final static File PROFIT_FILE = new File(folderPath + "Profit.txt");
    final static File N_PACKAGES_FILE = new File(folderPath + "NumberOfPackages.txt");
    final static File PRIME_DAY_FILE = new File(folderPath + "PrimeDay.txt");
    final static double PRIME_DAY_DISCOUNT = .15;

    /**
     * Main Method
     *
     * @param args list of command line arguements
     */
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        //1) load data (vehicle, packages, profits, packages shipped and primeday) from files using DatabaseManager
        ArrayList<Vehicle> vehicles = DatabaseManager.loadVehicles(VEHICLE_FILE);
        ArrayList<Package> warehousePackages = DatabaseManager.loadPackages(PACKAGE_FILE);
        double profit = DatabaseManager.loadProfit(PROFIT_FILE);
        int npackagesShipped = DatabaseManager.loadPackagesShipped(N_PACKAGES_FILE);
        boolean isPrimeDay = DatabaseManager.loadPrimeDay(PRIME_DAY_FILE);
        int input = 0;

        while(input != 5) {
            //2) Show menu and handle user inputs
            if (isPrimeDay = false) {
                System.out.println("==========Options==========\n" +
                        "1) Add Package\n" +
                        "2) Add Vehicle\n" +
                        "3) Activate Prime Day\n" +
                        "4) Send Vehicle\n" +
                        "5) Print Statistics\n" +
                        "6) Exit\n" +
                        "===========================");
            }
            if (isPrimeDay) {
                System.out.println("==========Options==========\n" +
                        "1) Add Package\n" +
                        "2) Add Vehicle\n" +
                        "3) Deactivate Prime Day\n" +
                        "4) Send Vehicle\n" +
                        "5) Print Statistics\n" +
                        "6) Exit\n" +
                        "===========================");
            }
            input = s.nextInt();

            switch (input) {
                case 1:

                    System.out.println("Enter package ID:");
                    String packageID = s.nextLine();

                    System.out.println("Enter Product Name:");
                    String productName = s.nextLine();

                    System.out.println("Enter Weight:");
                    double weight = s.nextInt();

                    System.out.println("Enter Price:");
                    double price = s.nextInt();
                    if (isPrimeDay) {
                        price = price * (1.00 - PRIME_DAY_DISCOUNT);
                    }

                    System.out.println("Enter Buyer Name:");
                    String buyerName = s.nextLine();

                    System.out.println("Enter Address:");
                    String address = s.nextLine();

                    System.out.println("Enter City:");
                    String city = s.nextLine();

                    System.out.println("Enter State:");
                    String state = s.nextLine();

                    System.out.println("Enter ZIP Code:");
                    int zipCode = Integer.parseInt(s.nextLine());

                    Package pack = new Package(packageID, productName, weight, price, new ShippingAddress(buyerName, address, city, state, zipCode));

                    warehousePackages.add(pack);
                    pack.shippingLabel();

                    break;
                case 2:
                    System.out.println("Vehicle Options:\n" +
                            "1) Truck\n" +
                            "2) Drone\n" +
                            "3) Cargo Plane");
                    int choice = s.nextInt();

                    switch (choice) {
                        case 1:
                            System.out.println("Enter License Plate No.:");
                            String licensePlateTruck = s.nextLine();
                            System.out.println("Enter Maximum Carry Weight:");
                            double carryWeightTruck = s.nextDouble();
                            //Does this work? Or do we need to do Vehicle truckAddition = new Truck(licensePlateTruck, carryWeightTruck), and then add that to the array
                            vehicles.add(new Truck(licensePlateTruck, carryWeightTruck));
                            break;

                        case 2:
                            System.out.println("Enter License Plate No.:");
                            String licensePlateDrone = s.nextLine();
                            System.out.println("Enter Maximum Carry Weight:");
                            double carryWeightDrone = s.nextDouble();
                            vehicles.add(new Drone(licensePlateDrone, carryWeightDrone));
                            break;

                        case 3:
                            System.out.println("Enter License Plate No.:");
                            String licensePlatePlane = s.nextLine();
                            System.out.println("Enter Maximum Carry Weight:");
                            double carryWeightPlane = s.nextDouble();
                            vehicles.add(new CargoPlane(licensePlatePlane, carryWeightPlane));
                            break;

                        default:
                            System.out.println("Error: Option not available.");
                            break;
                    }

                    break;

                case 3:
                    if (isPrimeDay) {
                        isPrimeDay = false;
                    }
                    if (isPrimeDay == false) {
                        isPrimeDay = true;
                    }
                    break;

                case 4:

                        break;

                case 5:
                    printStatisticsReport(profit, npackagesShipped, warehousePackages.size());
                    break;

                case 6:
                    DatabaseManager.saveVehicles(VEHICLE_FILE, vehicles);
                    DatabaseManager.savePackages(PACKAGE_FILE, warehousePackages);
                    DatabaseManager.saveProfit(PROFIT_FILE, profit);
                    DatabaseManager.savePackagesShipped(N_PACKAGES_FILE, npackagesShipped);
                    DatabaseManager.savePrimeDay(PRIME_DAY_FILE, isPrimeDay);
                    break;

                default:
                    System.out.println("Error: Option not available.");
                    break;
            }

            
        }
    }

    public static void printStatisticsReport(double profits, int packagesShipped, int numberOfPackages) {
        NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.US);
        System.out.println("==========Statistics==========\nProfits: " + formatter.format(profits) + "\nPackages Shipped: " + packagesShipped + "\nPackages in Warehouse: " + numberOfPackages + "\n==============================\n");
    }
}