package io.ccodelex;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String csvFile = "src/main/resources/ETHUSDT-trades-2023-01-01.csv";
        List<Trade> trades = new ArrayList<>();
        String line;

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null) {
                String[] tradeData = line.split(",");
                long tradeId = Long.parseLong(tradeData[0]);
                double price = Double.parseDouble(tradeData[1]);
                double quantity = Double.parseDouble(tradeData[2]);
                double quoteQty = Double.parseDouble(tradeData[3]);
                long time = Long.parseLong(tradeData[4]);
                boolean isBuyerMaker = Boolean.parseBoolean(tradeData[5]);
                boolean isBestMatch = Boolean.parseBoolean(tradeData[6]);
                trades.add(new Trade(tradeId, price, quantity, quoteQty, time, isBuyerMaker,
                                     isBestMatch));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        double totalEth = 0;
        double totalEthUsdt = 0;
        for (Trade trade : trades) {
            totalEth += trade.quantity();
            totalEthUsdt += trade.price() * trade.quantity();
        }

        double avgPrice = totalEthUsdt / totalEth;

        System.out.println(avgPrice);
    }
}