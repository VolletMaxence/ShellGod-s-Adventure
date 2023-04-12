import element.Donjon;
import ui.VueDonjon;

public class Main {
    public static void main(String[] args) {
        Donjon donjon = new Donjon(20, 20, 0);
        VueDonjon vueDonjon = new VueDonjon(donjon);
        vueDonjon.setVisible(true);
    }
}