package run;

import sensor.TCPSensor;

public class RunTCPSensor {
  public static void main(String[] args) throws Exception {

    if (args.length < 2) {
      System.out.println("Usage: ./TCPSensor.sh <address> <port>");
      return;
    }

    String address = args[0]; // Parse args
    String port = args[1];
    TCPSensor tcp_sensor = new TCPSensor(address, port); // Instantiate TCP sensor
    tcp_sensor.run(500); // And run it
    tcp_sensor.close_connection(); // Finally, close connection
  }
}
