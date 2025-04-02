package com.bioxx.tfc.WAILA;

import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.Core.TFC_Time;
import com.bioxx.tfc.api.Entities.IAnimal;
import fof.tfcsu.Entity.EntityEnhancedCow;
import java.util.List;
import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaEntityAccessor;
import mcp.mobius.waila.api.IWailaEntityProvider;
import mcp.mobius.waila.api.IWailaRegistrar;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;






public class WMobs
  implements IWailaEntityProvider
{
  public Entity getWailaOverride(IWailaEntityAccessor accessor, IWailaConfigHandler config) {
    return null;
  }



  public List<String> getWailaHead(Entity entity, List<String> currenttip, IWailaEntityAccessor accessor, IWailaConfigHandler config) {
    String head = currenttip.get(0);

    if (entity instanceof IAnimal) {

      IAnimal animal = (IAnimal)entity;

      if (config.getConfig("tfc.baby"))
      {
        if (!animal.isAdult()) {
          head = EnumChatFormatting.WHITE + TFC_Core.translate("gui.baby") + " " + head;
        }
      }
      if (config.getConfig("tfc.gender"))
      {
        if (animal.getGender() == IAnimal.GenderEnum.MALE) {
          head = head + " ♂";
        } else if (animal.getGender() == IAnimal.GenderEnum.FEMALE) {
          head = head + " ♀";
        }
      }
    }
    currenttip.set(0, head);

    return currenttip;
  }



  public List<String> getWailaBody(Entity entity, List<String> currenttip, IWailaEntityAccessor accessor, IWailaConfigHandler config) {
    IAnimal animal = (IAnimal)entity;
    NBTTagCompound nbt = accessor.getNBTData();

    int familiarity = nbt.func_74762_e("Familiarity");
    boolean familiarizedToday = animal.getFamiliarizedToday();
    boolean pregnant = animal.isPregnant();

    if (pregnant)
    {
      currenttip.add(TFC_Core.translate("entity.pregnant") + " : " + TFC_Time.getDateStringFromHours(animal.getDueDay() * 24));
    }

    if (config.getConfig("tfc.familiarToday") && animal.canFamiliarize())
    {
      if (familiarizedToday) {
        currenttip.add(TFC_Core.translate("gui.familiarized") + EnumChatFormatting.GREEN.toString() + " ✔");
      } else {
        currenttip.add(TFC_Core.translate("gui.familiarized") + EnumChatFormatting.RED.toString() + " ✘");
      }
    }
    if (config.getConfig("tfc.familiarity"))
    {
      currenttip.add(TFC_Core.translate("gui.familiarity") + " : " + familiarity + "%");
    }

    if (animal instanceof EntityEnhancedCow && animal.getGender() == IAnimal.GenderEnum.FEMALE && animal.isAdult()) {

      EntityEnhancedCow cow = (EntityEnhancedCow)entity;
      if (cow.isMilkable()) {
        currenttip.add(TFC_Core.translate("fluid.milk") + EnumChatFormatting.GREEN.toString() + " ✔");
      } else {
        currenttip.add(TFC_Core.translate("fluid.milk") + EnumChatFormatting.RED.toString() + " ✘");
      }
    }
    return currenttip;
  }



  public List<String> getWailaTail(Entity entity, List<String> currenttip, IWailaEntityAccessor accessor, IWailaConfigHandler config) {
    return currenttip;
  }



  public NBTTagCompound getNBTData(EntityPlayerMP player, Entity ent, NBTTagCompound tag, World world) {
    return tag;
  }


  public static void callbackRegister(IWailaRegistrar reg) {
    reg.addConfig("TerraFirmaCraft", "tfc.baby");
    reg.addConfig("TerraFirmaCraft", "tfc.gender");
    reg.addConfig("TerraFirmaCraft", "tfc.familiarToday");
    reg.addConfig("TerraFirmaCraft", "tfc.familiarity", false);

    reg.registerHeadProvider(new WMobs(), IAnimal.class);
    reg.registerBodyProvider(new WMobs(), IAnimal.class);
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\WAILA\WMobs.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */