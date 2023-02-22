package io.ccodelex;

public record Trade(long tradeId, double price, double quantity, double quoteQty, long time, boolean isBuyerMaker,
                    boolean isBestMatch) {
}
