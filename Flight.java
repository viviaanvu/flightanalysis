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

public class Flight {

    //private instance variables
    private String year;
    private String startCity;
    private String endCity;
    private double price;
    private int distance;

    //default constructor
    public Flight() {
        year = "1970";
        startCity = "Boston";
        endCity = "Richmond";
        price = 40.0;
        distance = 900;
    }

    //parameterized constructor
    public Flight(String aYear, String aStartCity, String aEndCity, double aPrice, int aDistance) {
        this.year = aYear;
        this.startCity = aStartCity;
        this.endCity = aEndCity;
        this.price = aPrice;
        this.distance = aDistance;
    }

    //getters
    public String getYear() {
        return year;
    }

    public String getStartCity() {
        return startCity;
    }

    public String getEndCity() {
        return endCity;
    }

    public double getPrice() {
        return price;
    }

    public int getDistance() {
        return distance;
    }

    //setters
    public void setYear(String year) {
        this.year = year;
    }

    public void setStartCity(String startCity) {
        this.startCity = startCity;
    }

    public void setEndCity(String endCity) {
        this.endCity = endCity;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    //toString method returns String of each variable separated by a space
    @Override
    public String toString() {
        return year + " " + startCity + " " + endCity + " " + price + " " + distance;
    }

    //equals method
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Flight) {
            Flight f = (Flight) obj;
            if (year.equals(f.getYear()) && startCity.equals(f.getStartCity()) && endCity.equals(f.getEndCity()) && price == getPrice() && distance == getDistance()) {
                return true;
            }

        }
        return false;
    }
}

