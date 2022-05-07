package org.payconiq.exception;

public class StockNotFoundException extends RuntimeException{

    private String message;

    public StockNotFoundException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
