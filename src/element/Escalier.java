package element;

public class Escalier extends SalleCaseDecorator {

    public Escalier(int X, int Y) {
        super(X, Y, "src/asset/Escalier.png");
    }

    public void setOccupied(boolean b) {
        this.Occupied = b;
        //System.out.println("Joueur sur Escalier");
    }

    public boolean isEscalier() {
        return true;
    }
}
