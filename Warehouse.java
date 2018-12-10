import javax.xml.crypto.Data;
import java.io.File;
import java.sql.SQLOutput;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;
import java.util.ArrayList;


/**
 * CS 180 - Project 5
 *
 * This is the Warehouse Class
 *
 * @author Nick Huber, Jon Bradbury, Gabe Efsits
 * @version 12/9/18
 */

public class Warehouse {
    final static String FOLDER_PATH = "files/";
    final static File VEHICLE_FILE = new File(FOLDER_PATH + "VehicleList.csv");
    final static File PACKAGE_FILE = new File(FOLDER_PATH + "PackageList.csv");
    final static File PROFIT_FILE = new File(FOLDER_PATH + "Profit.txt");
    final static File N_PACKAGES_FILE = new File(FOLDER_PATH + "NumberOfPackages.txt");
    final static File PRIME_DAY_FILE = new File(FOLDER_PATH + "PrimeDay.txt");
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

        while (input != 6) {
            //2) Show menu and handle user inputs
            if (isPrimeDay == false) {
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
                    String packageID = s.next();

                    System.out.println("Enter Product Name:");
                    String productName = s.next();

                    System.out.println("Enter Weight:");
                    double weight = s.nextDouble();

                    System.out.println("Enter Price:");
                    double price = s.nextDouble();
                    if (isPrimeDay) {
                        price = price * (1.00 - PRIME_DAY_DISCOUNT);
                    }

                    System.out.println("Enter Buyer Name:");
                    String buyerName = s.next();

                    System.out.println("Enter Address:");
                    String address = s.next();

                    System.out.println("Enter City:");
                    String city = s.next();

                    System.out.println("Enter State:");
                    String state = s.next();

                    System.out.println("Enter ZIP Code:");
                    int zipCode = s.nextInt();

                    Package pack = new Package(packageID, productName, weight, price,
                            new ShippingAddress(buyerName, address, city, state, zipCode));

                    warehousePackages.add(pack);
                    pack.shippingLabel();

                    break;
                case 2:
                    boolean flag = false;

                    while (flag == false) {
                        System.out.println("Vehicle Options:\n" +
                                "1) Truck\n" +
                                "2) Drone\n" +
                                "3) Cargo Plane");
                        int input2 = s.nextInt();

                        switch (input2) {
                            case 1:
                                System.out.println("Enter License Plate No.:");
                                String licensePlateTruck = s.next();
                                System.out.println("Enter Maximum Carry Weight:");
                                double carryWeightTruck = s.nextDouble();
                                //Does this work? Or do we need to do Vehicle truckAddition =
                                // new Truck(licensePlateTruck, carryWeightTruck), and then add that to the array
                                vehicles.add(new Truck(licensePlateTruck, carryWeightTruck));
                                flag = true;
                                break;

                            case 2:
                                System.out.println("Enter License Plate No.:");
                                String licensePlateDrone = s.next();
                                System.out.println("Enter Maximum Carry Weight:");
                                double carryWeightDrone = s.nextDouble();
                                vehicles.add(new Drone(licensePlateDrone, carryWeightDrone));
                                flag = true;
                                break;

                            case 3:
                                System.out.println("Enter License Plate No.:");
                                String licensePlatePlane = s.next();
                                System.out.println("Enter Maximum Carry Weight:");
                                double carryWeightPlane = s.nextDouble();
                                vehicles.add(new CargoPlane(licensePlatePlane, carryWeightPlane));
                                flag = true;
                                break;

                            default:
                                System.out.println("Error: Option not available.");
                                break;
                        }
                    }

                    break;

                case 3:
                    if (isPrimeDay) {
                        isPrimeDay = false;
                    }
                    else if (isPrimeDay == false) {
                        isPrimeDay = true;
                    }
                    break;

                case 4:

                    if (vehicles.size() == 0) {
                        System.out.println("Error: No vehicles available.");
                        break;
                    }
                    if (warehousePackages.size() == 0) {
                        System.out.println("Error: No packages available.");
                        break;
                    }

                    int chosenVehicle = -1;
                    boolean flag2 = false;

                    while (flag2 == false) {
                        System.out.println("Options:\n" +
                                "1) Send Truck\n" +
                                "2) Send Drone\n" +
                                "3) Send Cargo Plane\n" +
                                "4) Send First Available");
                        int input4 = s.nextInt();

                        switch (input4) {
                            case 1:
                                for (int i = 0; i < vehicles.size(); i++) {
                                    if (vehicles.get(i).getType().equals("Truck")) {
                                        chosenVehicle = i;
                                        break;
                                    }
                                }
                                flag2 = true;
                                if (chosenVehicle == -1) {
                                    System.out.println("Error: No vehicles of selected type are available.");
                                    flag2 = false;
                                }
                                break;

                            case 2:
                                for (int i = 0; i < vehicles.size(); i++) {
                                    if (vehicles.get(i).getType().equals("Drone")) {
                                        chosenVehicle = i;
                                        break;
                                    }
                                }
                                flag2 = true;
                                if (chosenVehicle == -1) {
                                    System.out.println("Error: No vehicles of selected type are available.");
                                    flag2 = false;
                                }
                                break;

                            case 3:
                                for (int i = 0; i < vehicles.size(); i++) {
                                    if (vehicles.get(i).getType().equals("Cargo Plane")) {
                                        chosenVehicle = i;
                                        break;
                                    }
                                }
                                flag2 = true;
                                if (chosenVehicle == -1) {
                                    System.out.println("Error: No vehicles of selected type are available.");
                                    flag2 = false;
                                }
                                break;

                            case 4:
                                chosenVehicle = 0;
                                flag2 = true;
                                break;

                            default:
                                System.out.println("Error: Option not available.");
                                break;
                        }
                    }

                    boolean flag3 = false;
                    while (flag3 == false) {
                        System.out.println("ZIP Code Options:\n" +
                                "1) Send to first ZIP Code\n" +
                                "2) Send to mode of ZIP Codes");
                        int input44 = s.nextInt();
                        switch (input44) {
                            case 1:
                                int zipCode2 = warehousePackages.get(0).getDestination().getZipCode();
                                vehicles.get(chosenVehicle).setZipDest(zipCode2);
                                flag3 = true;
                                break;

                            case 2:
                                int modeZipcode;
                                int modeZipcode2 = 0;
                                int count = 1;
                                int count2 = 0;

                                for (int i = 0; i < warehousePackages.size(); i++) {
                                    modeZipcode =  warehousePackages.get(0).getDestination().getZipCode();
                                    count = 1;

                                    for (int j = i; j < warehousePackages.size(); j++) {
                                        if (warehousePackages.get(i).getDestination().getZipCode() ==
                                                warehousePackages.get(j).getDestination().getZipCode()) {
                                            count++;
                                        }
                                    }

                                    if (count > count2) {
                                        modeZipcode2 = modeZipcode;
                                        count2 = count;
                                    }
                                }
                                vehicles.get(chosenVehicle).setZipDest(modeZipcode2);
                                flag3 = true;
                                break;

                            default:
                                System.out.println("Error: Option not available.");
                                break;
                        }
                    }

                    vehicles.get(chosenVehicle).fill(warehousePackages);
                    vehicles.get(chosenVehicle).report();
                    profit = profit + vehicles.get(chosenVehicle).getProfit();
                    npackagesShipped = npackagesShipped + vehicles.get(chosenVehicle).getPackages().size();

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
        System.out.println("==========Statistics==========\n" +
                "Profits:" + String.format("%1$26s", formatter.format(profits)) +
                "\nPackages Shipped:" + String.format("%1$17s", packagesShipped) +
                "\nPackages in Warehouse:" + String.format("%1$12s", numberOfPackages) +
                "\n==============================\n");
    }
}