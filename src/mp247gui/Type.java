package mp247gui;

public enum Type {

    BUY, SELL, UNKNOWN;  
    public static Type convert(String input){
        input = input.toLowerCase().split(" ",2)[0];
        switch(input){
            case "buy":
                return BUY;
            case "sell":
                return SELL;
            default:
                return UNKNOWN;
        }
    }
}
