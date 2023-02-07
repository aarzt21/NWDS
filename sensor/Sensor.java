package sensor;

import common.MessageInfo;

public interface Sensor {
    /* Sends N measurements to the Controller */
    public void run(int N) throws Exception;

    /* Sends a single measurement to the Controller */
    public void sendMessage(String dstAddress, String port, MessageInfo msg) throws Exception;

    /* Sense a single measurement */
    public float getMeasurement();
}
