package com.bioxx.tfc.Core;

import com.bioxx.tfc.Items.ItemBlocks.ItemTerraBlock;
import com.bioxx.tfc.Items.ItemTerra;
import com.bioxx.tfc.api.TFC_ItemHeat;
import com.google.common.collect.Queues;
import java.util.concurrent.ConcurrentLinkedQueue;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;





public class HandleItemsTickingManager
  extends Thread
{
  private static final Logger logger = LogManager.getLogger();

  private final MinecraftServer ms;
  StringBuilder message;
  private final ConcurrentLinkedQueue<ItemTickingSlot> slots_base = Queues.newConcurrentLinkedQueue();

  public HandleItemsTickingManager(MinecraftServer serv) {
    this.ms = serv;
    this.message = (new StringBuilder()).append("[HITM] ");
  }

  public void add_handleItemTicking(Object iinv, World world, int x, int y, int z) {
    add_handleItemTicking(iinv, world, x, y, z, 1.0F);
  }

  public void add_handleItemTicking(Object iinv, World world, int x, int y, int z, float environmentalDecayFactor) {
    ItemTickingSlot slot = new ItemTickingSlot(6, iinv, world, x, y, z, environmentalDecayFactor, -1.0F);
    this.slots_base.add(slot);
  }

  public void add_handleItemTicking(IInventory iinv, World world, int x, int y, int z, float environmentalDecayFactor, float baseDecayMod) {
    ItemTickingSlot slot = new ItemTickingSlot(7, iinv, world, x, y, z, environmentalDecayFactor, baseDecayMod);
    this.slots_base.add(slot);
  }

  private void run_slot(ItemTickingSlot slot) {
    if (slot.ItemList instanceof IInventory) { handleItemTicking((IInventory)slot.ItemList, slot.world, slot.x, slot.y, slot.z, slot.environmentalDecayFactor); }
    else { handleItemTicking((ItemStack[])slot.ItemList, slot.world, slot.x, slot.y, slot.z, slot.environmentalDecayFactor); }

  }
  private boolean process() {
    if (!this.slots_base.isEmpty())
    { ItemTickingSlot slot = this.slots_base.poll();
      if (slot != null)
        if (slot.args == 6) { run_slot(slot); }
        else { handleItemTicking((IInventory)slot.ItemList, slot.world, slot.x, slot.y, slot.z, slot.environmentalDecayFactor, slot.baseDecayMod); }
          }
    else {
      try { Thread.sleep(5L); } catch (InterruptedException e) { return true; }  }
     return false;
  }



  public void run() {
    log("Items Spawner Thread Started");
    boolean interrupted = false;

    while (!interrupted && !Thread.currentThread().isInterrupted() && this.ms.func_71278_l() && !this.ms.func_71241_aa()) {

      try {
        interrupted = process();
      }
      catch (Exception e) {
        this.slots_base.clear();
        log("ERROR ERROR ERROR ERROR ERROR ERROR");
        e.printStackTrace();
      }
    }

    if (isInterrupted() || interrupted) {
      log("Interrupted by manager.");
      return;
    }
    if (!this.ms.func_71278_l() || this.ms.func_71241_aa()) {
      log("Game stopped ! Shutting down thread.");

      return;
    }
    log("UNKNOWN. Thread Stopped.");
  }


  private void log(String str) {
    logger.warn(this.message + str);
  }







  public void handleItemTicking(IInventory iinv, World world, int x, int y, int z, float environmentalDecayFactor) {
    for (int i = 0; !world.field_72995_K && i < iinv.func_70302_i_(); i++) {

      ItemStack is = iinv.func_70301_a(i);
      if (is != null && is.field_77994_a <= 0) { iinv.func_70299_a(i, null); }

      else if (is != null)

      { if (is.field_77994_a == 0) {

          iinv.func_70299_a(i, null);

        }
        else if ((!(is.func_77973_b() instanceof ItemTerra) || !((ItemTerra)is.func_77973_b()).onUpdate(is, world, x, y, z)) && (
          !(is.func_77973_b() instanceof ItemTerraBlock) || !((ItemTerraBlock)is.func_77973_b()).onUpdate(is, world, x, y, z))) {


          if (is != null && (is.func_77973_b() instanceof com.bioxx.tfc.Items.ItemBlocks.ItemBarrels || is.func_77973_b() instanceof com.bioxx.tfc.Items.ItemBlocks.ItemLargeVessel)) {
            if (is.func_77942_o()) {
              ItemStack[] inv = TFC_Core.getIsFromNbt(is);
              if (inv != null) {
                TFC_Core.handleItemTicking(inv, world, x, y, z, environmentalDecayFactor);
                NBTTagCompound nbt = is.func_77978_p();
                nbt.func_82580_o("Items");
                TFC_Core.writeToNBT(nbt, inv);
              }
            }
          } else {
            is = TFC_Core.tickDecay(is, world, x, y, z, environmentalDecayFactor, 1.0F);
            if (is != null) TFC_ItemHeat.handleItemHeat(is);
            iinv.func_70299_a(i, is);
          }
        }  }

    }
  }







  public void handleItemTicking(ItemStack[] iinv, World world, int x, int y, int z, float environmentalDecayFactor) {
    for (int i = 0; !world.field_72995_K && i < iinv.length; i++) {

      ItemStack is = iinv[i];
      if (is != null && is.field_77994_a <= 0) { iinv[i] = null; }

      else if (is != null)

      { if ((!(is.func_77973_b() instanceof ItemTerra) || !((ItemTerra)is.func_77973_b()).onUpdate(is, world, x, y, z)) && (
          !(is.func_77973_b() instanceof ItemTerraBlock) || !((ItemTerraBlock)is.func_77973_b()).onUpdate(is, world, x, y, z))) {
          is = TFC_Core.tickDecay(is, world, x, y, z, environmentalDecayFactor, 1.0F);
          if (is != null) TFC_ItemHeat.handleItemHeat(is);
          iinv[i] = is;
        }  }

    }
  }




  public void handleItemTicking(IInventory iinv, World world, int x, int y, int z, float environmentalDecayFactor, float baseDecayMod) {
    for (int i = 0; !world.field_72995_K && i < iinv.func_70302_i_(); i++) {

      ItemStack is = iinv.func_70301_a(i);
      if (is != null && is.field_77994_a <= 0) { iinv.func_70299_a(i, null); }

      else if (is != null)

      { if ((!(is.func_77973_b() instanceof ItemTerra) || !((ItemTerra)is.func_77973_b()).onUpdate(is, world, x, y, z)) && (
          !(is.func_77973_b() instanceof ItemTerraBlock) || !((ItemTerraBlock)is.func_77973_b()).onUpdate(is, world, x, y, z))) {
          is = TFC_Core.tickDecay(is, world, x, y, z, environmentalDecayFactor, baseDecayMod);
          if (is != null) TFC_ItemHeat.handleItemHeat(is);
          iinv.func_70299_a(i, is);
        }  }

    }
  }
  class ItemTickingSlot { int args;
    Object ItemList;
    World world;
    int x;
    int y;
    int z;
    float environmentalDecayFactor;
    float baseDecayMod;

    public ItemTickingSlot(int args, Object ItemList, World w, int x, int y, int z, float environmentalDecayFactor, float baseDecayMod) {
      this.args = args;
      this.ItemList = ItemList;
      this.world = w;
      this.x = x;
      this.y = y;
      this.z = z;
      this.environmentalDecayFactor = environmentalDecayFactor;
      this.baseDecayMod = baseDecayMod;
    } }

}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Core\HandleItemsTickingManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */