package com.bioxx.tfc.Containers;

import com.bioxx.tfc.Containers.Slots.SlotForShowOnly;
import com.bioxx.tfc.Containers.Slots.SlotLiquidVessel;
import com.bioxx.tfc.Core.Metal.MetalRegistry;
import com.bioxx.tfc.Core.TFC_Achievements;
import com.bioxx.tfc.api.HeatRegistry;
import com.bioxx.tfc.api.Metal;
import com.bioxx.tfc.api.TFCItems;
import com.bioxx.tfc.api.TFC_ItemHeat;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.stats.StatBase;
import net.minecraft.world.World;





public class ContainerLiquidVessel
  extends ContainerTFC
{
  private World world;
  private EntityPlayer player;
  public InventoryCrafting containerInv = new InventoryCrafting(this, 1, 1);

  public int bagsSlotNum;

  public int metalAmount;

  public ContainerLiquidVessel(InventoryPlayer playerinv, World world, int x, int y, int z) {
    this.player = playerinv.field_70458_d;
    this.world = world;



    this.bagsSlotNum = this.player.field_71071_by.field_70461_c;
    layoutContainer((IInventory)playerinv);
  }



  public void func_75134_a(EntityPlayer player) {
    super.func_75134_a(player);
    if (!this.world.field_72995_K) {

      ItemStack itemstack2 = this.containerInv.func_70304_b(0);

      if (itemstack2 != null) {
        player.func_70099_a(itemstack2, 0.0F);
      }
    }
  }


  public boolean func_75145_c(EntityPlayer var1) {
    return true;
  }


  private void layoutContainer(IInventory playerInventory) {
    func_75146_a((Slot)new SlotLiquidVessel((IInventory)this.containerInv, 0, 80, 34));


    int row;

    for (row = 0; row < 9; row++) {

      if (row == this.bagsSlotNum) {
        func_75146_a((Slot)new SlotForShowOnly(playerInventory, row, 8 + row * 18, 148));
      } else {
        func_75146_a(new Slot(playerInventory, row, 8 + row * 18, 148));
      }
    }
    for (row = 0; row < 3; row++) {

      for (int col = 0; col < 9; col++) {
        func_75146_a(new Slot(playerInventory, col + row * 9 + 9, 8 + col * 18, 90 + row * 18));
      }
    }
  }



  public void func_75142_b() {
    ItemStack stack = this.player.field_71071_by.func_70301_a(this.bagsSlotNum);

    NBTTagCompound nbt = (stack != null && stack.func_77942_o()) ? stack.func_77978_p() : null;

    if (nbt != null) {

      ItemStack input = this.containerInv.func_70301_a(0);


      if (input != null && input.func_77973_b() instanceof com.bioxx.tfc.Items.Pottery.ItemPotteryMold && input.func_77960_j() > 1 && input.func_77960_j() <= 5) {
        this.player.func_71029_a((StatBase)TFC_Achievements.achCopperAge);
      }
      Metal m = MetalRegistry.instance.getMetalFromString(nbt.func_74779_i("MetalType"));
      this.metalAmount = nbt.func_74762_e("MetalAmount");

      if (!this.world.field_72995_K && m != null && stack != null)
      {
        if (input != null && input.func_77973_b() == TFCItems.ceramicMold && input.func_77960_j() == 1 && input.field_77994_a == 1 && this.metalAmount > 0) {

          int amt = 99;
          ItemStack is = new ItemStack(m.meltedItem, 1, amt);
          TFC_ItemHeat.setTemp(is, (short)(int)(HeatRegistry.getInstance().getMeltingPoint(is) * 1.5F));
          this.containerInv.func_70299_a(0, is);
          if (this.metalAmount - 1 <= 0) {

            nbt.func_82580_o("MetalType");
            nbt.func_82580_o("MetalAmount");
            nbt.func_82580_o("TempTimer");
            stack.func_77964_b(1);
          }
          else {

            nbt.func_74768_a("MetalAmount", this.metalAmount - 2);
          }

          stack.func_77982_d(nbt);
        }
        else if (input != null && input.func_77973_b() == m.meltedItem && input.func_77960_j() > 0) {

          input.func_77964_b(input.func_77960_j() - 1);
          TFC_ItemHeat.setTemp(input, (short)(int)(HeatRegistry.getInstance().getMeltingPoint(input) * 1.5F));
          if (this.metalAmount - 1 <= 0)
          {
            nbt.func_82580_o("MetalType");
            nbt.func_82580_o("MetalAmount");
            nbt.func_82580_o("TempTimer");
            stack.func_77964_b(1);
          }
          else
          {
            nbt.func_74768_a("MetalAmount", this.metalAmount - 1);
          }

        } else if (input != null && input.func_77973_b() instanceof com.bioxx.tfc.Items.Pottery.ItemPotteryMold && input.func_77960_j() == 1 && input.field_77994_a == 1 && this.metalAmount > 0 && ("Copper"
          .equals(m.name) || "Bronze".equals(m.name) || "Bismuth Bronze".equals(m.name) || "Black Bronze".equals(m.name))) {

          int amt = -1;
          if ("Copper".equals(m.name)) {
            amt = 398;
          } else if ("Bronze".equals(m.name)) {
            amt = 399;
          } else if ("Bismuth Bronze".equals(m.name)) {
            amt = 400;
          } else if ("Black Bronze".equals(m.name)) {
            amt = 401;
          }
          ItemStack is = new ItemStack(input.func_77973_b(), 1, amt);
          TFC_ItemHeat.setTemp(is, (short)(int)(HeatRegistry.getInstance().getMeltingPoint(is) * 1.5F));
          this.containerInv.func_70299_a(0, is);
          if (this.metalAmount - 1 <= 0) {

            nbt.func_82580_o("MetalType");
            nbt.func_82580_o("MetalAmount");
            nbt.func_82580_o("TempTimer");
            stack.func_77964_b(1);
          }
          else {

            nbt.func_74768_a("MetalAmount", this.metalAmount - 2);
          }

          stack.func_77982_d(nbt);
        }
        else if (input != null && input.func_77973_b() instanceof com.bioxx.tfc.Items.Pottery.ItemPotteryMold && input.func_77960_j() > 1) {

          boolean correctMetalFlag = false;
          if ("Copper".equals(m.name) && (input.func_77960_j() - 2) % 4 == 0) {
            correctMetalFlag = true;
          } else if ("Bronze".equals(m.name) && (input.func_77960_j() - 2) % 4 == 1) {
            correctMetalFlag = true;
          } else if ("Bismuth Bronze".equals(m.name) && (input.func_77960_j() - 2) % 4 == 2) {
            correctMetalFlag = true;
          } else if ("Black Bronze".equals(m.name) && (input.func_77960_j() - 2) % 4 == 3) {
            correctMetalFlag = true;
          }
          if (correctMetalFlag)
          {
            if (input.func_77960_j() > 5) {

              input.func_77964_b(input.func_77960_j() - 4);
              TFC_ItemHeat.setTemp(input, (short)(int)(HeatRegistry.getInstance().getMeltingPoint(input) * 1.5F));
              if (this.metalAmount - 1 <= 0) {

                nbt.func_82580_o("MetalType");
                nbt.func_82580_o("MetalAmount");
                nbt.func_82580_o("TempTimer");
                stack.func_77964_b(1);
              }
              else {

                nbt.func_74768_a("MetalAmount", this.metalAmount - 1);
              }
            }
          }
        }
      }
    }
    super.func_75142_b();
  }



  public ItemStack transferStackInSlotTFC(EntityPlayer player, int slotNum) {
    ItemStack origStack = null;
    Slot slot = this.field_75151_b.get(slotNum);
    Slot outputSlot = this.field_75151_b.get(0);

    if (slot != null && slot.func_75216_d()) {

      ItemStack slotStack = slot.func_75211_c();
      origStack = slotStack.func_77946_l();


      if (slotNum < 1) {

        if (!func_75135_a(slotStack, 1, this.field_75151_b.size(), true)) {
          return null;
        }
      } else if (!outputSlot.func_75216_d() && ((slotStack
        .func_77973_b() == TFCItems.ceramicMold && slotStack.func_77960_j() == 1) || (slotStack
        .func_77973_b() instanceof com.bioxx.tfc.Items.ItemMeltedMetal && slotStack.func_77960_j() > 1) || (slotStack
        .func_77973_b() instanceof com.bioxx.tfc.Items.Pottery.ItemPotteryMold && slotStack.func_77960_j() > 0))) {

        ItemStack stack = slotStack.func_77946_l();
        stack.field_77994_a = 1;
        outputSlot.func_75215_d(stack);
        slotStack.field_77994_a--;
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
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Containers\ContainerLiquidVessel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */