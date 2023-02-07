package sensor;

import common.MessageInfo;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class TCPSensor extends GenericSensor {

  Socket socket;

  public TCPSensor(String address, String port) {
    super(address, port);
    try {
      this.socket = new Socket(address, Integer.parseInt(port));
    } catch (IOException err) {
      throw new RuntimeException("TCP client: Not able to create socket -> " + err);
    }
  }

  @Override
  public void sendMessage(String dstAddress, String port, MessageInfo msg) {
    String message = msg.toString();
    buffer = message.getBytes();
    OutputStream output;
    try {
      output = socket.getOutputStream();
    } catch (IOException err) {
      throw new RuntimeException("TCP client: Not able get output stream from socket -> " + err);
    }
    DataOutputStream socketDOS = new DataOutputStream(output);
    try {
      socketDOS.write(buffer);
    } catch (IOException err) {
      throw new RuntimeException("TCP client: Not able to write to buffer ->" + err);
    }
  }

  public void close_connection() {
    try {
      socket.close();
    } catch (IOException err) {
      throw new RuntimeException("TCP client: Not able to close socket ->" + err);
    }
  }
}
