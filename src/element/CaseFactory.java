package element;

public class CaseFactory {
    public static Case createCase(String type, int x, int y) {
        return switch (type) {
            case "mur", "wall" -> new WallCase(x, y, "src/asset/Wall.png");
            case "salle", "room" -> new SalleCase(x, y, "src/asset/Salle.png");
            case "couloir", "corridor" -> new CouloirCase(x, y, "src/asset/Salle.png");
            default -> throw new IllegalArgumentException("Ce type de Case n'existe pas, " +
                    "franchement c'est toi qui a fait le code, tu peux pas faire un effort merde ?");
        };
    }
}