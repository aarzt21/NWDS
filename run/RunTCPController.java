package run;

import controller.Controller;
import controller.TCPController;

public class RunTCPController {
  public static void main(String[] args) throws Exception {

    if (args.length < 1) {
      System.out.println("Usage: ./TCPController <TCP rcv port>");
      return;
    }

    String port = args[0]; // parse args
    Controller tcpController = new TCPController(); // instantiate server
    tcpController.receiveMessages(Integer.parseInt(port), 50000); // start listening & receiving
  }
}
