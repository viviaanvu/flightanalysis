/****************************************************************************
 * Project 7 - Flight Analysis
 *
 * This program will analyze data read from a file, remove items not needed
 * in the file, calculate the best fare, and calculate the best fare amongst
 * distance of Flight objects.
 *
 * Vivian Vu
 * CMSC 255
 * 11/23/2019
 ****************************************************************************/

package Project7;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class FlightAnalysis {

    //main method
    public static void main(String[] args) throws FileNotFoundException {

        //variables for input and output filenames
        String inputFilename, outputFilename;

        //parse filenames
        if (args == null) {
            Scanner in = new Scanner(System.in);
            System.out.println("Enter input filename");
            inputFilename = in.nextLine();
            System.out.println("Enter output filename");
            outputFilename = in.nextLine();
        }
        else {
            inputFilename = args[0];
            outputFilename = args[1];
        }

        //create ArrayLists of allFlights and removeItems and files
        ArrayList<Flight> allFlights = new ArrayList();
        ArrayList<Flight> removeItems = new ArrayList<>();
        File flightData = new File(inputFilename);
        File removeData = new File("dataToRemove.txt");

        //try-catch block handling correct file names
        try {
            allFlights = parseData(flightData);
            removeItems = parseData(removeData);

            System.out.println("Input file correct");
        }
        catch (FileNotFoundException e) {
            System.out.println("Incorrect input filename");
        }

        //call removeData method
        removeData(allFlights, removeItems);

        Flight bestFare = calcBestFare(allFlights);
        Flight bestFarePerMile = calcBestFarePerMile(allFlights);

        //try-catch block
        try {
            writeOutData(allFlights, new File(outputFilename));
            System.out.println("Output file correct");
        }
        catch (Exception e){
            System.out.println("Incorrect output filename");
        }

    }



    //parseData method returns ArrayList with flight objects read from inputFile
    public static ArrayList<Flight> parseData (File inputFile) throws FileNotFoundException {

        Scanner in = new Scanner(inputFile);
        ArrayList<Flight> theseFlights = new ArrayList<>();

        //read data by each line in file

        in.nextLine();
        while (in.hasNextLine()) {

            //declare line
            String line = in.nextLine();
            //split line at ; declaring them variables
            String [] info = line.split(";");

            //set each variable equal to corresponding indexes
            String year = info[0];
            String city1 = info[1];
            String city2 = info[2];
            double price;
            int distance;

            //try-catch block to see if price and distance are numeric or negative
            try{
                price = Double.parseDouble(info[3]);
                if(price < 0){
                    throw new NumberFormatException();
                }
            }
            catch (NumberFormatException ex){
                price = 0.0;
            }
            try{
                distance = Integer.parseInt(info[4]);
                if(distance < 0){
                    throw new NumberFormatException();
                }
            }
            catch (NumberFormatException ex){
                distance = 0;
            }

            //create a new Flight object and add it to the ArrayList allFlights
            Flight newFlight = new Flight(year, city1, city2, price, distance);
            theseFlights.add(newFlight);

        }

        //return ArrayList
        return theseFlights;

    }

    //method removes data
    public static void removeData (ArrayList<Flight> allFlights, ArrayList<Flight> removeItems) {
        //iterate through the items to be removed
        for(int i = 0; i < removeItems.size(); i++) {
            Flight removeFlights = removeItems.get(i);
            String removeFlightNames = removeFlights.toString();

            //iterate through allFlights
            for(int j = 0; j < allFlights.size(); j++){
                Flight flights = allFlights.get(j);
                String flightNames = flights.toString();

                //if a flight to be removed equals a flight object in Flight Array remove it
                if(removeFlightNames.equals(flightNames)){
                    allFlights.remove(j);
                }
            }
        }
    }


    //calculates the lowest fare among the ArrayList allFlights
    public static Flight calcBestFare (ArrayList<Flight> allFlights) {

        //bestFlight is equal to the allFlights first index
        Flight bestFlight = allFlights.get(0);

        //for each flight object in allFlights
        for(Flight a : allFlights){

            //if price in Flight object a is less than bestFlight's price then bestFlight equals Flight object a
            if(a.getPrice() < bestFlight.getPrice()){
                bestFlight = a;
            }
        }

        //return the bestFlight
        return bestFlight;

    }

    //calculates best fare per mile
    public static Flight calcBestFarePerMile (ArrayList<Flight> allFlights) {

        //bestFlight is equal to the allFlights first index
        Flight bestFlight = allFlights.get(0);

        //if price in Flight object a is less than bestFlight's price then bestFlight equals Flight object a
        for(Flight a : allFlights){
            double farePerMile = bestFlight.getPrice()/bestFlight.getDistance();
            double testFarePerMile = a.getPrice()/a.getDistance();

            if(testFarePerMile < farePerMile){
                bestFlight = a;
            }
        }

        //return the bestFlight
        return bestFlight;
    }


    public static void writeOutData (ArrayList<Flight> allFlights, File out) throws FileNotFoundException {

        PrintWriter pw = new PrintWriter(out);

        for(Flight a : allFlights) {
            pw.print(a.toString()+"\n");
        }

        pw.close();
    }

}