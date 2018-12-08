import java.util.ArrayList;
import java.io.*;

/**
 * <h1>Database Manager</h1>
 *
 * Used to locally save and retrieve data.
 */
public class DatabaseManager {

    /**
     * Creates an ArrayList of Vehicles from the passed CSV file. The values are in
     * the CSV file as followed:
     * <ol>
     * <li>Vehicle Type (Truck/Drone/Cargo Plane)</li>
     * <li>Vehicle License Plate</li>
     * <li>Maximum Carry Weight</li>
     * </ol>
     * If filePath does not exist, a blank ArrayList will be returned.
     *
     * @param file CSV File
     * @return ArrayList of vehicles
     */

    public static ArrayList<Vehicle> loadVehicles(File file) {
        ArrayList<Vehicle> vehicles = new ArrayList<>();
        ArrayList<String> lines = new ArrayList<>();
        FileReader fileReader;
        BufferedReader bufferedReader;

        try {
            fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);

            String line;

            try {
                while ((line = bufferedReader.readLine()) != null) {
                    lines.add(line);
                } // end while loop
                bufferedReader.close();

            } catch (IOException e) {
                return vehicles;
            } // end catch

            for (int i = 0; i < lines.size(); i++) {
                String type = lines.get(i).split(",")[0];
                String plate = lines.get(i).split(",")[1];
                double carryWeight = Double.parseDouble(lines.get(i).split(",")[2]);
                if(type.equals("Truck")) {
                    vehicles.add(new Truck(plate, carryWeight));
                }
                if(type.equals("Drone")){
                    vehicles.add(new Drone(plate, carryWeight));
                }
                if(type.equals("Cargo Plane")){
                    vehicles.add(new CargoPlane(plate, carryWeight));
                }
            }


        } catch (FileNotFoundException e) {
            return vehicles;
        } // end catch


        return vehicles;
    } // end loadVehicles





    /**
     * Creates an ArrayList of Packages from the passed CSV file. The values are in
     * the CSV file as followed:
     * <ol>
     * <li>ID</li>
     * <li>Product Name</li>
     * <li>Weight</li>
     * <li>Price</li>
     * <li>Address Name</li>
     * <li>Address</li>
     * <li>City</li>
     * <li>State</li>
     * <li>ZIP Code</li>
     * </ol>
     *
     * If filePath does not exist, a blank ArrayList will be returned.
     *
     * @param file CSV File
     * @return ArrayList of packages
     */
    public static ArrayList<Package> loadPackages(File file) {
        ArrayList<Package> packages = new ArrayList<>();
        ArrayList<String> lines = new ArrayList<>();
        FileReader fileReader;
        BufferedReader bufferedReader;

        try {
            fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);

            String line;

            try {
                while ((line = bufferedReader.readLine()) != null) {
                    lines.add(line);
                } // end while loop
                bufferedReader.close();

            } catch (IOException e) {
                return packages;
            } // end catch

            for (int i = 0; i < lines.size(); i++) {
                String id = lines.get(i).split(",")[0];
                String product = lines.get(i).split(",")[1];
                double weight = Double.parseDouble(lines.get(i).split(",")[2]);
                double price = Double.parseDouble(lines.get(i).split(",")[3]);
                String name = lines.get(i).split(",")[4];
                String address = lines.get(i).split(",")[5];
                String city =  lines.get(i).split(",")[6];
                String state =  lines.get(i).split(",")[7];
                int zipCode = Integer.parseInt(lines.get(i).split(",")[8]);

                packages.add(new Package(id, product, weight, price, new ShippingAddress(name, address, city, state, zipCode)));
            } // end for loop


        } catch (FileNotFoundException e) {
            return packages;
        } // end catch

        return packages;
    } // end loadPackages







    /**
     * Returns the total Profits from passed text file. If the file does not exist 0
     * will be returned.
     *
     * @param file file where profits are stored
     * @return profits from file
     */
    public static double loadProfit(File file) {
        FileReader fileReader;
        BufferedReader bufferedReader;
        double profit = 0;

        try {
            fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);

            try {
                profit = Double.parseDouble(bufferedReader.readLine());
                bufferedReader.close();

            } catch (IOException e) {
                return 0;
            } // end catch
        }catch(FileNotFoundException e){
            return 0;
        } // end catch

        return profit;
    }





    /**
     * Returns the total number of packages shipped stored in the text file. If the
     * file does not exist 0 will be returned.
     *
     * @param file file where number of packages shipped are stored
     * @return number of packages shipped from file
     */
    public static int loadPackagesShipped(File file) {
        FileReader fileReader;
        BufferedReader bufferedReader;
        int packagesShipped = 0;

        try {
            fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);

            try {
                packagesShipped = Integer.parseInt(bufferedReader.readLine());
                bufferedReader.close();

            } catch (IOException e) {
                return 0;
            } // end catch
        }catch(FileNotFoundException e){
            return 0;
        }

        return packagesShipped;
    }




    /**
     * Returns whether or not it was Prime Day in the previous session. If file does
     * not exist, returns false.
     *
     * @param file file where prime day is stored
     * @return whether or not it is prime day
     */
    public static boolean loadPrimeDay(File file) {
        FileReader fileReader;
        BufferedReader bufferedReader;
        boolean isPrimeDay;
        int isPrimeDayInt;

        try {
            fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);

            try {
                isPrimeDayInt = Integer.parseInt(bufferedReader.readLine());
                bufferedReader.close();

            } catch (IOException e) {
                return false;
            } // end catch
        }catch(FileNotFoundException e){
            return false;
        }

        if(isPrimeDayInt == 1){
            isPrimeDay = true;
        }else if(isPrimeDayInt == 0){
            isPrimeDay = false;
        }else{isPrimeDay = false;}

        return isPrimeDay;
    }





    /**
     * Saves (writes) vehicles from ArrayList of vehicles to file in CSV format one vehicle per line.
     * Each line (vehicle) has following fields separated by comma in the same order.
     * <ol>
     * <li>Vehicle Type (Truck/Drone/Cargo Plane)</li>
     * <li>Vehicle License Plate</li>
     * <li>Maximum Carry Weight</li>
     * </ol>
     *
     * @param file     File to write vehicles to
     * @param vehicles ArrayList of vehicles to save to file
     */
    //NOT FINISHED, vehicle type again
    public static void saveVehicles(File file, ArrayList<Vehicle> vehicles) {
        //TODO
        FileWriter fileWriter;
        BufferedWriter bufferedWriter;

        try {
            fileWriter = new FileWriter(file);
            bufferedWriter = new BufferedWriter(fileWriter);

            for (int i = 0 ; i < vehicles.size() ; i ++){
                String vehicleType = vehicles.get(i).getType();
                String licensePlate = vehicles.get(i).getLicensePlate();
                String carryWeight = Double.toString(vehicles.get(i).getMaxWeight());

                bufferedWriter.write(vehicleType + "," + licensePlate + "," + carryWeight + "\n");
            }
            bufferedWriter.close();

        }catch(IOException e){
            e.printStackTrace();
        }
    }




    /**
     * Saves (writes) packages from ArrayList of package to file in CSV format one package per line.
     * Each line (package) has following fields separated by comma in the same order.
     * <ol>
     * <li>ID</li>
     * <li>Product Name</li>
     * <li>Weight</li>
     * <li>Price</li>
     * <li>Address Name</li>
     * <li>Address</li>
     * <li>City</li>
     * <li>State</li>
     * <li>ZIP Code</li>
     * </ol>
     *
     * @param file     File to write packages to
     * @param packages ArrayList of packages to save to file
     */
    public static void savePackages(File file, ArrayList<Package> packages) {
        //TODO
        FileWriter fileWriter;
        BufferedWriter bufferedWriter;

        try {
            fileWriter = new FileWriter(file);
            bufferedWriter = new BufferedWriter(fileWriter);

            for (int i = 0 ; i < packages.size() ; i ++){
                String id = packages.get(i).getID();
                String productName = packages.get(i).getProduct();
                String weight = Double.toString(packages.get(i).getWeight());
                String price = Double.toString(packages.get(i).getPrice());
                String addressName = packages.get(i).getDestination().getName();
                String address = packages.get(i).getDestination().getAddress();
                String city = packages.get(i).getDestination().getCity();
                String state = packages.get(i).getDestination().getState();
                String zipCode = Integer.toString(packages.get(i).getDestination().getZipCode());

                bufferedWriter.write(id + "," + productName + "," + weight + "," + price + ","
                        + addressName + "," + address + "," + city + "," + state + "," + zipCode + "\n");
            }
            bufferedWriter.close();

        }catch(IOException e){
            e.printStackTrace();
        }
    }




    /**
     * Saves profit to text file.
     *
     * @param file   File to write profits to
     * @param profit Total profits
     */

    public static void saveProfit(File file, double profit) {
        FileWriter fileWriter;
        BufferedWriter bufferedWriter;

        try {
            fileWriter = new FileWriter(file);
            bufferedWriter = new BufferedWriter(fileWriter);

            // changes it to a string
            String stringProfit = Double.toString(profit);

            bufferedWriter.write(stringProfit);

            bufferedWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }





    /**
     * Saves number of packages shipped to text file.
     *
     * @param file      File to write profits to
     * @param nPackages Number of packages shipped
     */

    public static void savePackagesShipped(File file, int nPackages) {
        FileWriter fileWriter;
        BufferedWriter bufferedWriter;

        try {
            fileWriter = new FileWriter(file);
            bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.write(nPackages);

            bufferedWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }






    /**
     * Saves status of prime day to text file. If it is primeDay "1" will be
     * writtern, otherwise "0" will be written.
     *
     * @param file     File to write profits to
     * @param primeDay Whether or not it is Prime Day
     */

    public static void savePrimeDay(File file, boolean primeDay) {
        FileWriter fileWriter;
        BufferedWriter bufferedWriter;

        try {
            fileWriter = new FileWriter(file);
            bufferedWriter = new BufferedWriter(fileWriter);

            if(primeDay){
                bufferedWriter.write(1);
            }else{bufferedWriter.write(0);}

            bufferedWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}