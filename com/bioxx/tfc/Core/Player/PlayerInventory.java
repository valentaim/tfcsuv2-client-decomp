package com.bioxx.tfc.Core.Player;

import com.bioxx.tfc.Containers.Slots.SlotExtraEquipable;
import com.bioxx.tfc.Containers.Slots.SlotForShowOnly;
import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.api.Interfaces.IEquipable;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;



public class PlayerInventory
{
  public static int invXSize = 176;
  public static int invYSize = 87;
  private static ResourceLocation invTexture = new ResourceLocation("terrafirmacraft", "textures/gui/gui_inventory_lower.png");

  public static InventoryCrafting containerInv;
  private static int index;

  public static void buildInventoryLayout(Container container, InventoryPlayer inventory, int x, int y, boolean freezeSlot, boolean toolBarAfterMainInv) {
    index = 0;
    if (!toolBarAfterMainInv) {
      addToolbarSlots(container, inventory, x, y, freezeSlot);
    }
    for (int i = 0; i < 3; i++) {

      for (int k = 0; k < 9; k++) {

        index = k + (i + 1) * 9;
        addSlotToContainer(container, new Slot((IInventory)inventory, index, x + k * 18, y + i * 18));
      }
    }

    if (toolBarAfterMainInv) {
      addToolbarSlots(container, inventory, x, y, freezeSlot);
    }
  }


















  public static void loadBagInventory(ItemStack is, Container c) {
    if (is != null && is.func_77942_o()) {

      NBTTagList nbttaglist = is.func_77978_p().func_150295_c("Items", 10);
      containerInv = new InventoryCrafting(c, 4, 2);
      for (int i = 0; i < nbttaglist.func_74745_c(); i++) {

        NBTTagCompound nbttagcompound1 = nbttaglist.func_150305_b(i);
        byte byte0 = nbttagcompound1.func_74771_c("Slot");
        if (byte0 >= 0 && byte0 < 8)
          containerInv.func_70299_a(byte0, ItemStack.func_77949_a(nbttagcompound1));
      }
    }
  }

  public static void addExtraEquipables(Container container, InventoryPlayer inventory, int x, int y, boolean freezeSlot) {
    int index = 40;
    addSlotToContainer(container, (Slot)new SlotExtraEquipable((IInventory)inventory, index, 26, 26, IEquipable.EquipType.BACK));
  }


  private static void addToolbarSlots(Container container, InventoryPlayer inventory, int x, int y, boolean freezeSlot) {
    for (int j = 0; j < 9; j++) {

      if (freezeSlot && j == inventory.field_70461_c) {
        addSlotToContainer(container, (Slot)new SlotForShowOnly((IInventory)inventory, j, x + j * 18, y + 58));
      } else {
        addSlotToContainer(container, new Slot((IInventory)inventory, j, x + j * 18, y + 58));
      }
    }
  }

  public static void buildInventoryLayout(Container container, InventoryPlayer inventory, int x, int y, boolean freezeSlot) {
    buildInventoryLayout(container, inventory, x, y, false, false);
  }


  public static void buildInventoryLayout(Container container, InventoryPlayer inventory, int x, int y) {
    buildInventoryLayout(container, inventory, x, y, false);
  }



  protected static Slot addSlotToContainer(Container container, Slot par1Slot) {
    par1Slot.field_75222_d = container.field_75151_b.size();
    container.field_75151_b.add(par1Slot);
    container.field_75153_a.add(null);
    return par1Slot;
  }


  public static void drawInventory(GuiContainer container, int screenWidth, int screenHeight, int upperGuiHeight) {
    TFC_Core.bindTexture(invTexture);
    GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    int l = (screenWidth - invXSize) / 2;
    int i1 = (screenHeight - upperGuiHeight + invYSize) / 2 + upperGuiHeight;
    container.func_73729_b(l, i1, 0, 0, invXSize, invYSize);
  }









  public static InventoryPlayerTFC getInventory(EntityPlayer p) {
    return (InventoryPlayerTFC)p.field_71071_by;
  }


  public static void upgradePlayerCrafting(EntityPlayer player) {
    if (player.getEntityData().func_74764_b("craftingTable")) {

      (player.field_71069_bz.func_75139_a(45)).field_75223_e += 50000;
      (player.field_71069_bz.func_75139_a(46)).field_75223_e += 50000;
      (player.field_71069_bz.func_75139_a(47)).field_75223_e += 50000;
      (player.field_71069_bz.func_75139_a(48)).field_75223_e += 50000;
      (player.field_71069_bz.func_75139_a(49)).field_75223_e += 50000;
    }
  }



  public static ItemStack transferStackInSlot(EntityPlayer player, ItemStack stackToXfer) {
    return null;
  }

  public static void putItemToExtraSlot(EntityPlayer p, ItemStack is) {
    ((InventoryPlayerTFC)p.field_71071_by).extraEquipInventory[0] = is;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Core\Player\PlayerInventory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */