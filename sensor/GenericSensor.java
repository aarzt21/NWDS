package sensor;

/*
 * You can add/change/delete class attributes if you think it would be
 * appropriate.
 * You can also add helper methods and change the implementation of those
 * provided if you think it would be appropriate, as long as you DO NOT
 * CHANGE the provided interface.
 *
 */

import common.MessageInfo;

import java.math.BigDecimal;
import java.util.Random;

public abstract class GenericSensor implements Sensor {

  protected static final int max_measure = 50;
  protected static final int min_measure = 10;
  protected static final int buffsize = 2048;
  protected float measurement;
  protected int totMsg;

  protected String port;
  protected String address;

  protected byte[] buffer;

  public GenericSensor(String address, String port) {

    this.address = address;
    this.port = port;
    measurement = 0;
  }

  @Override
  public void run(int N) {
    totMsg = N;
    for (int i = 0; i < N; i++) {
      measurement = getMeasurement();
      MessageInfo message_info = new MessageInfo(N, i + 1, measurement);
      print_info(N, i + 1, measurement);
      try {
        sendMessage(this.address, this.port, message_info);
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    }
  }

  public void print_info(int total_msgs, int curr_msg, float value) {
    System.out.println(
        "Sending Message " + curr_msg + " out of " + total_msgs + ". Measure = " + value);
  }

  @Override
  public float getMeasurement() {
    Random r = new Random();
    measurement = r.nextFloat() * (max_measure - min_measure) + min_measure;
    float r_measurement = BigDecimal.valueOf(measurement).setScale(2,BigDecimal.ROUND_HALF_DOWN).floatValue();
    return r_measurement;
  }
}
