package com.bioxx.tfc.api.Events;

import cpw.mods.fml.common.eventhandler.Cancelable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.EntityEvent;

@Cancelable
public class PlayerSkillEvent
  extends EntityEvent {
  protected PlayerSkillEvent(EntityPlayer entity) {
    super((Entity)entity);
  }

  @Cancelable
  public static class Increase
    extends PlayerSkillEvent
  {
    public final int skillGain;
    public final String skillName;

    public Increase(EntityPlayer entity, String name, int skill) {
      super(entity);
      this.skillGain = skill;
      this.skillName = name;
    }
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\api\Events\PlayerSkillEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */