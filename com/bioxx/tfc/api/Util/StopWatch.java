/*    */ package com.bioxx.tfc.api.Util;
/*    */ 
/*    */ 
/*    */ public class StopWatch
/*    */ {
/*    */   private long startTime;
/*    */   private long stopTime;
/*    */   private boolean running;
/*    */   
/*    */   public void start() {
/* 11 */     this.startTime = System.currentTimeMillis();
/* 12 */     this.running = true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void stop() {
/* 17 */     this.stopTime = System.currentTimeMillis();
/* 18 */     this.running = false;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public long getElapsedTime() {
/*    */     long elapsed;
/* 25 */     if (this.running) {
/* 26 */       elapsed = System.currentTimeMillis() - this.startTime;
/*    */     } else {
/*    */       
/* 29 */       elapsed = this.stopTime - this.startTime;
/*    */     } 
/* 31 */     return elapsed;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public long getElapsedTimeSecs() {
/*    */     long elapsed;
/* 38 */     if (this.running) {
/* 39 */       elapsed = (System.currentTimeMillis() - this.startTime) / 1000L;
/*    */     } else {
/*    */       
/* 42 */       elapsed = (this.stopTime - this.startTime) / 1000L;
/*    */     } 
/* 44 */     return elapsed;
/*    */   }
/*    */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\api\Util\StopWatch.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */