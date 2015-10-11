package mp247gui;

import java.util.Objects;

public class Order implements Comparable {

    private int id;
    private Character character;
    private int amount;
    private int quantity;
    private boolean complete;
    private Type type;

    public Order(int id, Type type, Character character, int amount, int quantity) {
        this.id = id;
        this.character = character;
        this.amount = amount;
        this.quantity = quantity;
        this.type = type;
        this.complete = false;
    }

    public Order(int id, Type type, Character character, int amount, int quantity, boolean complete) {
        this.id = id;
        this.character = character;
        this.amount = amount;
        this.quantity = quantity;
        this.complete = complete;
        this.type = type;
    }

    public Order(Type type, Character character, int amount, int quantity, boolean complete) {
        this.id = -1;
        this.character = character;
        this.amount = amount;
        this.quantity = quantity;
        this.complete = complete;
        this.type = type;
    }

    public Order(String command) {
        // /w mp247 buy mario $30 1
        // /w mp247 sell mario $30 1
        this.id = Main.g.getBets().size();
        if (this.id <= 0) {
            this.id = 1;
        }
        this.character = Character.convert(command.split("mp247 ", 2)[1].split(" ", 2)[1].split(" ", 2)[0]);
        this.amount = Integer.parseInt(command.split("\\$", 2)[1].split(" ", 2)[0]);
        this.quantity = Integer.parseInt(command.split("\\$", 2)[1].split(" ", 2)[1].split(" ", 2)[0]);
        this.complete = false;
        this.type = Type.convert(command.split("mp247 ", 2)[1].split(" ", 2)[0]);
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

    public boolean isComplete() {
        return complete;
    }

    public int getID() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }

    public Type getType() {
        return type;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    public Bet toBet() {
        return new Bet(type, character, amount, quantity);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.character);
        hash = 53 * hash + this.amount;
        hash = 53 * hash + this.quantity;
        hash = 53 * hash + (this.complete ? 1 : 0);
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
        final Order other = (Order) obj;
        if (this.character != other.character) {
            return false;
        }
        if (this.amount != other.amount) {
            return false;
        }
        if (this.quantity != other.quantity) {
            return false;
        }
        if (this.complete != other.complete) {
            return false;
        }
        return this.type == other.type;
    }

    @Override
    public String toString() {
        return id + ") " + quantity + "x $" + amount + " " + type.name().toLowerCase() + "s on " + character + " " + (complete ? "(Completed)" : "");
    }

    @Override
    public int compareTo(Object o) {
        if (o instanceof Order) {
            final Order other = (Order) o;
            if (other.getID() > this.getID()) {
                return -1;
            } else if (this.getID() > other.getID()) {
                return 1;
            } else {
                return 0;
            }
        } else {
            return -1;
        }
    }

}
