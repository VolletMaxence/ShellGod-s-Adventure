package element;

public class CaseFactory {
    public static Case createCase(String type, int x, int y) {
        switch(type) {
            case "mur":
            case "wall":
                return new WallCase(x, y, "src/asset/Rock_Wall.png");
            case "salle":
            case "room":
                return new SalleCase(x, y, "src/asset/Salle.png");
            case "couloir":
            case "corridor":
                return new CouloirCase(x, y, "src/asset/Couloirs.png");
            default:
                throw new IllegalArgumentException("Ce type de Case n'existe pas, " +
                        "franchement c'est toi qui a fait le code, tu peux pas faire un effort merde ?");
        }
    }
}