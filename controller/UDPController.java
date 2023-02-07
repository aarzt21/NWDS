package controller;

import common.MessageInfo;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.net.SocketTimeoutException;

/*
 * You can add/change/delete class attributes if you think it would be
 * appropriate.
 * You can also add helper methods and change the implementation of those
 * provided if you think it would be appropriate, as long as you DO NOT
 * CHANGE the provided interface.
 *
 */

public class UDPController extends GenericController {

  public UDPController() {
    super();
  }

  @Override
  public void receiveMessages(int port, int timeout) {
    this.timeout = timeout;

    DatagramSocket socket;
    try {
      socket = new DatagramSocket(port);
      socket.setSoTimeout(timeout);
    } catch (SocketException err) {
      throw new RuntimeException("UDP server: NOT able to create socket -> " + err);
    }
    System.out.println("Listening on port " + port);

    byte[] buffer = new byte[buffsize];
    DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

    while (true) {
      try {
        socket.receive(packet);
      } catch (SocketTimeoutException err) {
        throw new RuntimeException("UDP server: Time out ->" + err);
      } catch (IOException err) {
        throw new RuntimeException(
            "UPD server: I/O exception occured when receiving packet ->" + err);
      }

      String msg = new String(packet.getData());
      MessageInfo msg_info;
      try {
        msg_info = new MessageInfo(msg);
      } catch (Exception err) {
        throw new RuntimeException("UDP server: Runtime exception occured -> " + err);
      }

      saveMessage(msg_info);
      print_received_package_info("UDP Controller", msg_info);

      if (counter == expected) {
        printStats();
        break;
      }
    }
  }
}
