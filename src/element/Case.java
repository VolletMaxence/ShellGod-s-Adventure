package element;

public interface Case {
    boolean Occupied = false;

    void CaseCreation(int posX, int posY, String imagePath);

    void setPosition(int x, int y);

    void setImage(String imagePath);

    int getX();
    int getY();
    boolean isOccupied();
    void setOccupied(boolean b);
}

