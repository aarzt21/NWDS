package controller;

import common.MessageInfo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPController extends GenericController {

  public TCPController() {
    super();
  }

  @Override
  public void receiveMessages(int port, int timeout) {

    ServerSocket server_socket;
    Socket socket;
    BufferedReader incoming;
    try {
      // create server socket and bind it to port
      server_socket = new ServerSocket(port);
      System.out.println("[TCP Server] Waiting for client at port " + port);
      // wait for connection req by client -> create new socket and bind to port
      socket = server_socket.accept();
      socket.setSoTimeout(timeout);
      System.out.println("Connected with client!");
      // get sockets input stream only -> one directional communication
      incoming = new BufferedReader(new InputStreamReader(socket.getInputStream()));

    } catch (IOException e) {
      throw new RuntimeException(
          "TCP server: Not able to create socket or establish connection to TCP client -> " + e);
    }

    String input;
    while (true) {
      try {
        input = incoming.readLine();
      } catch (IOException e) {
        throw new RuntimeException(
            "TCP server: I/O exception occured during data reading process -> " + e);
      }
      MessageInfo msg_info = null;
      try {
        msg_info = new MessageInfo(input);
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
      saveMessage(msg_info);
      print_received_package_info("TCP Controller", msg_info);
      if (counter == expected) {
        break;
      }
    }

    printStats();

    try {
      incoming.close();
      socket.close();
      server_socket.close();
    } catch (IOException err) {
      throw new RuntimeException("TCP Server: Could not close socket or connection -> " + err);
    }
  }
}
