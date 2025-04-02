package com.bioxx.tfc.Containers;

import com.bioxx.tfc.Containers.Slots.SlotChest;
import com.bioxx.tfc.Containers.Slots.SlotForShowOnly;
import com.bioxx.tfc.Core.Player.PlayerInventory;
import com.bioxx.tfc.TileEntities.TEBarrel;
import com.bioxx.tfc.api.Enums.EnumSize;
import com.bioxx.tfc.api.TFCBlocks;
import cpw.mods.fml.common.FMLLog;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;


public class ContainerBarrel
  extends ContainerTFC
{
  public TEBarrel barrel;
  public float liquidLevel;
  public int liquidID;
  public int sealedTime = -1;

  public int guiTab;

  public ContainerBarrel(InventoryPlayer inventoryplayer, TEBarrel tileentitybarrel, World world, int x, int y, int z, int tab) {
    this.barrel = tileentitybarrel;
    this.liquidLevel = 0.0F;
    this.liquidID = -1;
    this.guiTab = tab;

    buildLayout();

    PlayerInventory.buildInventoryLayout(this, inventoryplayer, 8, 90, false, true);
  }



  public static List<Item> getExceptions() {
    List<Item> exceptions = new ArrayList<>();
    exceptions.add(Item.func_150898_a(TFCBlocks.barrel));
    exceptions.add(Item.func_150898_a(TFCBlocks.vessel));
    return exceptions;
  }


  protected void buildLayout() {
    if (this.guiTab == 0) {


      if (!this.barrel.getSealed()) {
        func_75146_a((Slot)(new SlotChest((IInventory)this.barrel, 0, 80, 29)).setSize(EnumSize.LARGE).addItemException(getExceptions()));
      } else {
        func_75146_a((Slot)new SlotForShowOnly((IInventory)this.barrel, 0, 80, 29));
      }
    } else if (this.guiTab == 1) {

      for (int i = 0; i < 4; i++) {

        for (int k = 0; k < 3; k++) {

          if (!this.barrel.getSealed()) {
            func_75146_a((Slot)(new SlotChest((IInventory)this.barrel, k + i * 3, 53 + i * 18, 17 + k * 18)).setSize(EnumSize.LARGE).addItemException(ContainerChestTFC.getExceptions()));
          } else {
            func_75146_a((Slot)new SlotForShowOnly((IInventory)this.barrel, k + i * 3, 53 + i * 18, 17 + k * 18));
          }
        }
      }
    }
  }


  public boolean func_75145_c(EntityPlayer entityplayer) {
    return true;
  }



  public ItemStack transferStackInSlotTFC(EntityPlayer player, int slotNum) {
    if (this.barrel.func_145831_w() instanceof cuchaz.ships.ShipWorld) {
      if (!player.field_70170_p.field_72995_K) FMLLog.getLogger().warn("Ship Duper found: " + player.getDisplayName());
      return null;
    }
    ItemStack origStack = null;
    Slot slot = this.field_75151_b.get(slotNum);

    if (!this.barrel.getSealed() && slot != null && slot.func_75216_d()) {

      ItemStack slotStack = slot.func_75211_c();
      origStack = slotStack.func_77946_l();


      if (slotNum < 1 && this.guiTab == 0) {

        if (!func_75135_a(slotStack, 1, this.field_75151_b.size(), true)) {
          return null;
        }
      }
      else if (slotNum < 12 && this.guiTab == 1) {

        if (!func_75135_a(slotStack, 12, this.field_75151_b.size(), true)) {
          return null;

        }

      }
      else if (this.guiTab == 1) {

        if (!func_75135_a(slotStack, 0, 12, false)) {
          return null;
        }
      }
      else if (this.guiTab == 0) {

        if (!func_75135_a(slotStack, 0, 1, false)) {
          return null;
        }
      }

      if (slotStack.field_77994_a <= 0) {
        slot.func_75215_d(null);
      } else {
        slot.func_75218_e();
      }
      if (slotStack.field_77994_a == origStack.field_77994_a) {
        return null;
      }
      slot.func_82870_a(player, slotStack);
    }

    return origStack;
  }




  public void func_75142_b() {
    super.func_75142_b();

    for (int var1 = 0; var1 < this.field_75149_d.size() && this.guiTab == 0; var1++) {

      ICrafting var2 = this.field_75149_d.get(var1);

      if (this.barrel.getFluidStack() != null && this.liquidID != this.barrel.getFluidStack().getFluidID()) {

        this.liquidID = this.barrel.getFluidStack().getFluidID();
        var2.func_71112_a(this, 0, this.barrel.getFluidStack().getFluidID());
      }
      if (this.liquidLevel != this.barrel.getFluidLevel()) {

        this.liquidLevel = this.barrel.getFluidLevel();
        var2.func_71112_a(this, 1, this.barrel.getFluidLevel());
      }
    }
  }




  public void func_75137_b(int id, int val) {
    if (id == 0) {

      if (this.barrel.fluid != null) {

        this.barrel.fluid = new FluidStack(val, this.barrel.fluid.amount);
      }
      else {

        this.barrel.fluid = new FluidStack(val, 1000);
      }
      this.barrel.processItems();
    }
    else if (id == 1) {

      if (this.barrel.fluid != null)
        this.barrel.fluid.amount = val;
    }
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Containers\ContainerBarrel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */