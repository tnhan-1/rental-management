import controller.RentalController;
import view.ConsoleView;

public class Main {
    public static void main(String[] args) {
        RentalController controller = new RentalController();
        ConsoleView view = new ConsoleView(controller);
        view.start();
    }
}
