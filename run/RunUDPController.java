package run;

import controller.Controller;
import controller.UDPController;

public class RunUDPController {
  public static void main(String[] args) {
    Controller controller = new UDPController();

    if (args.length < 1) {
      System.out.println("Usage: ./UDPController.sh <port>");
      return;
    }

    String port = args[0]; // Parse args

    UDPController udp_controller = new UDPController(); // Instantiate UDP controller
    try {
      udp_controller.receiveMessages(Integer.parseInt(port), 50000); // Ready to receive
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
