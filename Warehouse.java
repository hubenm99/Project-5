import java.io.File;
import java.util.Scanner;

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



        //2) Show menu and handle user inputs
        System.out.println("==========Options==========\n" +
                "1) Add Package\n" +
                "2) Add Vehicle\n" +
                "3) Activate Prime Day\n" +
                "4) Send Vehicle\n" +
                "5) Print Statistics\n" +
                "6) Exit\n" +
                "===========================");
        int input = s.nextInt();

        if (input < 0 || input > 6) {
            System.out.println("Error: Option not available.");
        } else if (input == 1) {

            System.out.println("Enter package ID:");
            String packageID = s.nextLine();

            System.out.println("Enter Product Name:");
            String productName = s.nextLine();

            System.out.println("Enter Weight:");
            double weight = s.nextInt();

            System.out.println("Enter Price:");
            double price = s.nextInt();

            System.out.println("Enter Buyer Name:");
            String buyerName = s.nextLine();

            System.out.println("Enter Address:");
            String address = s.nextLine();

            System.out.println("Enter City:");
            String city = s.nextLine();

            System.out.println("Enter State:");
            String state = s.nextLine();

            System.out.println("Enter ZIP Code:");
            String zipCode = s.nextLine();

        } else if (input == 2) {
            System.out.println("Vehicle Options:\n" +
                    "1) Truck\n" +
                    "2) Drone\n" +
                    "3) Cargo Plane");
        }



        //3) save data (vehicle, packages, profits, packages shipped and primeday) to files (overwriting them) using DatabaseManager


    }


}