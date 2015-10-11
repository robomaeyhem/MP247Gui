package mp247gui;

public enum Character {

    Mario, Peach, Wario, Yoshi;

    public static Character convert(String input) {
        input = input.toLowerCase().split(" ", 2)[0];
        switch (input) {
            case "mario":
                return Mario;
            case "peach":
                return Peach;
            case "wario":
                return Wario;
            case "yoshi":
                return Yoshi;
            default:
                return Mario;
        }
    }
}
