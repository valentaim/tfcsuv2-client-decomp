package com.bioxx.tfc.api.Events;

import cpw.mods.fml.common.eventhandler.Cancelable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.event.entity.EntityEvent;
















@Cancelable
public class AnvilCraftEvent
  extends EntityEvent
{
  public ItemStack input1;
  public ItemStack input2;
  public ItemStack result;
  public TileEntity anvilTE;

  public AnvilCraftEvent(EntityPlayer entityplayer, TileEntity te, ItemStack i1, ItemStack i2, ItemStack r) {
    super((Entity)entityplayer);
    this.input1 = i1;
    this.input2 = i2;
    this.result = r;
    this.anvilTE = te;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\api\Events\AnvilCraftEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */