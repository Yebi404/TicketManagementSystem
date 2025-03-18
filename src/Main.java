import view.AgentView;
import controller.TicketController;

public class Main {
    public static void main(String[] args) {
        TicketController controller = new TicketController();
        new AgentView(controller).setVisible(true);  // Or new CustomerView(controller, userId);
    }
}