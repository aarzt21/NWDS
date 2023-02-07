package controller;

import common.MessageInfo;

import java.util.ArrayList;
import java.util.List;

/*
 * You can add/change/delete class attributes if you think it would be
 * appropriate.
 * You can also add helper methods and change the implementation of those
 * provided if you think it would be appropriate, as long as you DO NOT
 * CHANGE the provided interface.
 *
 */

public abstract class GenericController implements Controller {

  protected int timeout = 50000;
  protected int buffsize = 2048;

  protected int expected = 0;
  protected int counter = 0;

  protected List<MessageInfo> receivedMessages;

  public GenericController() {
    receivedMessages = new ArrayList<MessageInfo>();
  }

  @Override
  public abstract void receiveMessages(int port, int timeout) throws Exception;

  @Override
  public void saveMessage(MessageInfo msg) {
    receivedMessages.add(msg);
    expected = msg.getTotalMessages();
    counter++;
  }

  public void print_received_package_info(String controller_type, MessageInfo msg) {
    System.out.println(
        "["
            + controller_type
            + "] "
            + "Message "
            + counter
            + " out of "
            + expected
            + " received. Value = "
            + msg.getMessage());
  }

  @Override
  public void printStats() {
    int missing_msgs = expected - counter;
    System.out.println("=========================================================");
    System.out.println("Total Missing Messages = " + missing_msgs + " out of 100");
    System.out.println("=========================================================");
  }
}
