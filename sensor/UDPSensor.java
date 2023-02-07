package sensor;

import common.MessageInfo;

import java.io.IOException;
import java.net.*;

public class UDPSensor extends GenericSensor {

  DatagramSocket socket;

  public UDPSensor(String address, String port) {
    super(address, port);
    try {
      this.socket = new DatagramSocket();
    } catch (SocketException err) {
      throw new RuntimeException("UDP client: NOT able to create socket -> " + err);
    }
  }

  @Override
  public void sendMessage(String dstAddress, String port, MessageInfo msg) {

    String message = msg.toString();
    buffer = message.getBytes();

    InetAddress address;
    try {
      address = InetAddress.getByName(dstAddress);
    } catch (UnknownHostException err) {
      throw new RuntimeException("UDP client: NOT able to create destination address -> " + err);
    }
    int i_port = Integer.parseInt(port);
    DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, i_port);

    try {
      socket.send(packet);
    } catch (IOException err) {
      throw new RuntimeException("UDP client: NOT able to send packet -> " + err);
    }
  }
}
