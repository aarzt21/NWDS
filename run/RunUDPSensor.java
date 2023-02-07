package run;

import sensor.Sensor;
import sensor.UDPSensor;

public class RunUDPSensor {
    public static void main (String[] args) throws Exception {

        if (args.length < 2) {
            System.out.println("Usage: ./UDPensor.sh <address> <port>");
            return;
        }

        String address = args[0]; String port = args[1]; // Parse args
        UDPSensor udp_sensor = new UDPSensor(address, port); // Instantiate new UDP Sensor
        udp_sensor.run(100); // And run it
    }
}
