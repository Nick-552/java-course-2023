package edu.hw3.ex6;

public class Stock {

    private String id;

    private String companyName;

    private double price;

    public Stock(String id, String companyName, double price) {
        this.id = id;
        this.companyName = companyName;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    @Override public String toString() {
        return "Stock{"
            + "id='" + id + '\''
            + ", companyName='" + companyName + '\''
            + ", price=" + price
            + '}';
    }
}
