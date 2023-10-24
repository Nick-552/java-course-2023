package edu.hw3.ex6;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class PoMOEXTest {
    private PoMOEX poMOEX ;

    @BeforeEach
    public void setUp() {
        poMOEX = new PoMOEX();
    }

    @Test
    @DisplayName("Add and mostValuableStock")
    public void testAddStock() {
        Stock stock1 = new Stock("SBER", "Сбер Банк", 270.51);
        Stock stock2 = new Stock("ASTR", "Астра", 501.2);
        Stock stock3 = new Stock("GAZP", "Газпром", 170.15);
        poMOEX.add(stock1);
        poMOEX.add(stock2);
        poMOEX.add(stock3);
        assertThat(poMOEX.mostValuableStock()).isEqualTo(stock2);
    }

    @Test
    @DisplayName("Delete")
    public void testRemoveStock() {
        Stock stock1 = new Stock("SBER", "Сбер Банк", 270.51);
        Stock stock2 = new Stock("ASTR", "Астра", 501.2);
        Stock stock3 = new Stock("GAZP", "Газпром", 170.15);
        poMOEX.add(stock1);
        poMOEX.add(stock2);
        poMOEX.add(stock3);
        poMOEX.remove(stock2);
        assertThat(poMOEX.mostValuableStock()).isEqualTo(stock1);
    }

    @Test
    @DisplayName("No stocks")
    public void testMostValuableStockEmpty() {
        assertThat(poMOEX.mostValuableStock()).isNull();
    }
}
