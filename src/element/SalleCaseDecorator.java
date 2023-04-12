package element;

abstract class SalleCaseDecorator extends SalleCase {
    protected Case SalleCase;

    public SalleCaseDecorator(int X, int Y, String imagePath) {
        super(X, Y, imagePath);
    }

    public Case getDecorated() {
        return SalleCase;
    }
}
