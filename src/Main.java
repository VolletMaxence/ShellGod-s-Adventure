import element.Donjon;
import ui.Menu;
import ui.MenuV2;
import ui.VueDonjon;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Menu menu = new Menu();
        MenuV2 menu2 = MenuV2.getInstance();
        menu2.setVisible(true);
    }
}