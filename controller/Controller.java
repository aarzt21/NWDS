package controller;

import common.MessageInfo;

public interface Controller {
    /* Save message into local data structure*/
    public void saveMessage(MessageInfo msg);

    /* Listen for messages on port 'port' */
    public void receiveMessages(int port, int timeout) throws Exception;

    /* Print statistics */
    public void printStats();
}
