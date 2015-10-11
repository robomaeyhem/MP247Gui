package mp247gui;

import java.util.Objects;

public class Bet {

    private Character character;
    private int amount;
    private int quantity;
    private Type type;

    public Bet(Type type, Character character, int amount, int quantity) {
        this.character = character;
        this.amount = amount;
        this.quantity = quantity;
        this.type = type;
    }

    public Character getCharacter() {
        return character;
    }

    public int getAmount() {
        return amount;
    }

    public int getQuantity() {
        return quantity;
    }
    
    public Type getType() {
        return type;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.character);
        hash = 53 * hash + this.amount;
        hash = 53 * hash + this.quantity;
        hash = 53 * hash + Objects.hashCode(this.type);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Bet other = (Bet) obj;
        if (this.character != other.character) {
            return false;
        }
        if (this.amount != other.amount) {
            return false;
        }
        if (this.quantity != other.quantity) {
            return false;
        }
        return this.type == other.type;
    }

    @Override
    public String toString() {
        return quantity + "x $" + amount + " " + type.name().toLowerCase() + "s on " + character;
    }    

}
