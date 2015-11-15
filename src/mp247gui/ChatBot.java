package mp247gui;

import PircBot.*;
import java.util.ArrayList;
import javax.swing.*;

public class ChatBot extends PircBot {

    private String name;
    private String oAuth;
    private String lastMessage;

    public ChatBot(String name, String oauth) {
        this.name = name;
        this.oAuth = oauth;
        this.setMessageDelay(1000);
        this.setMaxMessageQueueLength(500);
        this.setVerbose(true);
        lastMessage = "";
        connect();
    }

    public String getBotName() {
        return name;
    }

    public void setBotName(String name) {
        this.name = name;
    }

    public String getoAuth() {
        return oAuth;
    }

    public void setoAuth(String oAuth) {
        this.oAuth = oAuth;
    }

    public final void connect() {
        this.setName(this.name);
        try {
            this.connect("199.9.253.120", 6667, this.oAuth);
            this.sendRawLine("CAP REQ :twitch.tv/commands");
            System.err.println("Connected to Twitch");
        } catch (Exception ex) {
            System.err.println("[WARNING] Failed to connect! " + ex);
            JOptionPane.showMessageDialog(null, "Error connecting to the IRC Server!\nCheck that your Twitch Username and OAuth is correct and try again.", "Error Connecting", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void sendMessage(String channel, String message) {
        lastMessage = message;
        super.sendMessage(name, message);
    }

    @Override
    public void onWhisper(User sender, String target, String message) {
        if (sender.getNick().equalsIgnoreCase("mp247")) {
            if (message.startsWith("you have $")) {
                Main.g.updateBottomPanel("<html>" + message.replace("(", "<br>(") + "</html>");
            }
            if (message.startsWith("Held ") || message.startsWith("Sold ")) {
                ArrayList<Bet> bets = new ArrayList<>();
                Character lastCharacter = null;
                Type lastType = null;
                while (message.length() >= 1) {
                    Character character = null;
                    if (!message.startsWith("$")) {
                        try {
                            character = Character.convert(message.split("Held ", 2)[1].split(" bets:", 2)[0]);
                        } catch (ArrayIndexOutOfBoundsException ex) {
                            try {
                                character = Character.convert(message.split("Sold ", 2)[1].split(" bets:", 2)[0]);
                            } catch (Exception ex2) {
                                character = null;
                            }
                        }
                    }
                    if (character != null) {
                        lastCharacter = character;
                    } else {
                        character = lastCharacter;
                    }
                    int amount = Integer.parseInt(message.split("\\$", 2)[1].split("x", 2)[0]);
                    int qty = Integer.parseInt(message.split("x", 2)[1].split(" ", 2)[0]);
                    Type type = Type.UNKNOWN;
                    if (message.startsWith("Held")) {
                        type = Type.BUY;
                    } else if (message.startsWith("Sold")) {
                        type = Type.SELL;
                    }
                    if (type != Type.UNKNOWN) {
                        lastType = type;
                    } else {
                        type = lastType;
                    }
                    bets.add(new Bet(type, character, amount, qty));
                    try {
                        message = message.split("x", 2)[1].split(" ", 2)[1];
                    } catch (Exception ex) {
                        break;
                    }
                }
                Main.g.getBets().clear();
                for (Bet el : bets) {
                    Main.g.addBet(el);
                }
                Main.g.updateBetTable();
            }
            if (lastMessage.contains("orders") && (message.contains("Mario: ") || message.contains("Yoshi: ") || message.contains("Peach: ") || message.contains("Wario: "))) {
                Character lastCharacter = null;
                while (message.length() >= 1) {
                    if (java.lang.Character.isLetter(message.charAt(0))) {
                        Character character = Character.convert(message.substring(0, 5));
                        lastCharacter = character;
                        int id = Integer.parseInt(message.split("#", 2)[1].split("\\$", 2)[0]);
                        int amount = Integer.parseInt(message.split("\\$", 2)[1].split("x", 2)[0]);
                        int qty = Integer.parseInt(message.split("x", 2)[1].split(" ", 2)[0]);
                        for (Order el : Main.g.getOrdersList()) {
                            if (amount == el.getAmount() && qty == el.getQuantity() && character == el.getCharacter() && el.getID() == 1) {
                                el.setID(id);
                                Main.g.updateOrderTable();
                                break;
                            }
                        }
                        try {
                            message = message.split("x", 2)[1].split(" ", 2)[1];
                        } catch (Exception ex) {
                            break;
                        }
                    } else if (message.charAt(0) == '#') {
                        Character character = lastCharacter;
                        int id = Integer.parseInt(message.split("#", 2)[1].split("\\$", 2)[0]);
                        int amount = Integer.parseInt(message.split("\\$", 2)[1].split("x", 2)[0]);
                        int qty = Integer.parseInt(message.split("x", 2)[1].split(" ", 2)[0]);
                        for (Order el : Main.g.getOrdersList()) {
                            if (amount == el.getAmount() && qty == el.getQuantity() && character == el.getCharacter() && el.getID() == 1) {
                                el.setID(id);
                                Main.g.updateOrderTable();
                                break;
                            }
                        }
                        try {
                            message = message.split("x", 2)[1].split(" ", 2)[1];
                        } catch (Exception ex) {
                            break;
                        }
                    }
                }
            }
//            if (message.startsWith("you purchased ")) {
//
//            }
            if (message.startsWith("your order to ") && message.contains("has been completed")) {
                Type type = Type.convert(message.split(" to ", 2)[1].split(" ", 2)[0]);
                Character character = Character.convert(message.split(" on ", 2)[1].split(" ", 2)[0]);
                ArrayList<Order> ordersList = new ArrayList<>();
                for (Order el : Main.g.getOrdersList()) {
                    if (el.getType() == type && el.getCharacter() == character) {
                        Bet b = el.toBet();
                        Main.g.addBet(b);
                    } else {
                        ordersList.add(el);
                    }
                }
                Main.g.clearOrders();
                for (Order el : ordersList) {
                    Main.g.addOrder(el);
                }
            }
            if (message.startsWith("No orders")) {
                Main.g.getOrdersList().clear();
                Main.g.updateOrderTable();
            }
            if (message.startsWith("No bets")) {
                Main.g.getBets().clear();
                Main.g.updateBetTable();
            }
        }
    }

    @Override
    public void onMessage(String channel, User sender, String message) {
        if (sender.getNick().equalsIgnoreCase("mp247")) {
            if (message.contains("has won!") && message.contains("marioparty247.tv/")) {
                Main.g.clearOrders();
                Main.g.clearBets();
            }
        }
    }
}
