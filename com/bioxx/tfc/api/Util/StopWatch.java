package com.bioxx.tfc.api.Util;


public class StopWatch
{
  private long startTime;
  private long stopTime;
  private boolean running;
  
  public void start() {
    this.startTime = System.currentTimeMillis();
    this.running = true;
  }

  
  public void stop() {
    this.stopTime = System.currentTimeMillis();
    this.running = false;
  }


  
  public long getElapsedTime() {
    long elapsed;
    if (this.running) {
      elapsed = System.currentTimeMillis() - this.startTime;
    } else {
      
      elapsed = this.stopTime - this.startTime;
    } 
    return elapsed;
  }


  
  public long getElapsedTimeSecs() {
    long elapsed;
    if (this.running) {
      elapsed = (System.currentTimeMillis() - this.startTime) / 1000L;
    } else {
      
      elapsed = (this.stopTime - this.startTime) / 1000L;
    } 
    return elapsed;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\api\Util\StopWatch.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */