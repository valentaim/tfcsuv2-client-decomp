package com.bioxx.tfc.api.Events;

public class EntityArmorCalcEvent extends EntityEvent {
  public float incomingDamage;
  public final EventType eventType;

  public enum EventType {
    PRE, POST;
  }







  public EntityArmorCalcEvent(EntityLivingBase p, float damage, EventType eType) {
    super((Entity)p);
    this.incomingDamage = damage;
    this.eventType = eType;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\api\Events\EntityArmorCalcEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */