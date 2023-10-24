package edu.hw3.ex6;

import java.util.PriorityQueue;

public class PoMOEX implements StockMarket {
    PriorityQueue<Stock> stocks = new PriorityQueue<>((o1, o2) -> Double.compare(o2.getPrice(), o1.getPrice()));

    @Override
    public void add(Stock stock) {
        stocks.add(stock);
    }

    @Override
    public void remove(Stock stock) {
        stocks.remove(stock);
    }

    @Override
    public Stock mostValuableStock() {
        return stocks.peek();
    }
}
