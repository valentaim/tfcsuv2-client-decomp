package com.bioxx.tfc.Containers;

import com.bioxx.tfc.Containers.Slots.SlotBlocked;
import com.bioxx.tfc.Containers.Slots.SlotMoldTool;
import com.bioxx.tfc.Containers.Slots.SlotMoldTool2;
import com.bioxx.tfc.Core.Player.PlayerInfo;
import com.bioxx.tfc.Core.Player.PlayerInventory;
import com.bioxx.tfc.Core.Player.PlayerManagerTFC;
import com.bioxx.tfc.api.Crafting.CraftingManagerTFC;
import com.bioxx.tfc.api.TFCItems;
import com.bioxx.tfc.api.TFC_ItemHeat;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCraftResult;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;





public class ContainerMold
  extends ContainerTFC
{
  private World world;
  private EntityPlayer player;
  public InventoryCrafting containerInv = new InventoryCrafting(this, 2, 1);
  public IInventory craftResult = (IInventory)new InventoryCraftResult();


  public ContainerMold(InventoryPlayer playerinv, World world, int x, int y, int z) {
    this.player = playerinv.field_70458_d;
    this.world = world;



    layoutContainer((IInventory)playerinv, 0, 0);
    PlayerInventory.buildInventoryLayout(this, playerinv, 8, 90, false, true);
    PlayerInfo pi = PlayerManagerTFC.getInstance().getPlayerInfoFromPlayer(playerinv.field_70458_d);
    this.containerInv.func_70299_a(0, pi.specialCraftingType);
  }






  public void func_75134_a(EntityPlayer player) {
    super.func_75134_a(player);
    if (!this.world.field_72995_K) {

      ItemStack itemstack = this.craftResult.func_70304_b(0);
      ItemStack itemstack2 = this.containerInv.func_70304_b(0);
      ItemStack itemstack3 = this.containerInv.func_70304_b(1);
      if (itemstack != null)
        player.func_70099_a(itemstack, 0.0F);
      if (itemstack2 != null)
        player.func_70099_a(itemstack2, 0.0F);
      if (itemstack3 != null) {
        player.func_70099_a(itemstack3, 0.0F);
      }
    }
  }


  public boolean func_75145_c(EntityPlayer var1) {
    return true;
  }


  protected void layoutContainer(IInventory playerInventory, int xSize, int ySize) {
    func_75146_a((Slot)new SlotMoldTool((IInventory)this.containerInv, 0, 41, 34));
    func_75146_a((Slot)new SlotMoldTool2((IInventory)this.containerInv, 1, 94, 34));
    func_75146_a((Slot)new SlotBlocked(this.craftResult, 0, 116, 34));
  }



  public void func_75137_b(int id, int value) {
    if (id == 0) {

      PlayerInfo pi = PlayerManagerTFC.getInstance().getPlayerInfoFromPlayer(this.player);
      pi.moldTransferTimer = (short)value;
    }
  }



  public void func_75142_b() {
    super.func_75142_b();
    if (this.craftResult.func_70301_a(0) == null) {

      PlayerInfo pi = PlayerManagerTFC.getInstance().getPlayerInfoFromPlayer(this.player);
      short oldTransferTimer = pi.moldTransferTimer;
      ItemStack sourceStack = this.containerInv.func_70301_a(0);
      ItemStack inputStack = this.containerInv.func_70301_a(1);

      if (sourceStack != null && inputStack != null) {

        Item sourceItem = sourceStack.func_77973_b();
        Item inputItem = inputStack.func_77973_b();
        int inputDamage = inputStack.func_77960_j();

        if (sourceItem instanceof com.bioxx.tfc.Items.ItemMeltedMetal && inputItem == TFCItems.ceramicMold && inputDamage == 1 && TFC_ItemHeat.getIsLiquid(sourceStack).booleanValue()) {

          ItemStack is = sourceStack.func_77946_l();
          is.func_77964_b(100);
          this.containerInv.func_70299_a(1, is);
          pi.moldTransferTimer = 100;
        }
        else if (sourceItem instanceof com.bioxx.tfc.Items.ItemMeltedMetal && inputItem instanceof com.bioxx.tfc.Items.ItemMeltedMetal && sourceItem == inputItem && inputDamage != 0) {

          pi.moldTransferTimer = 100;
        }
      }

      if (inputStack != null && pi.moldTransferTimer < 100 &&
        CraftingManagerTFC.getInstance().findMatchingRecipe(this.containerInv, this.world) != null)
      {
        pi.moldTransferTimer = (short)(pi.moldTransferTimer + 1);
      }

      if (sourceStack != null && inputStack != null && pi.moldTransferTimer == 1000)
      {
        pi.moldTransferTimer = 0;
      }

      if (sourceStack == null || inputStack == null)
      {
        pi.moldTransferTimer = 1000;
      }

      if (sourceStack != null && inputStack != null && pi.moldTransferTimer == 100) {

        Item sourceItem = sourceStack.func_77973_b();
        Item inputItem = inputStack.func_77973_b();
        int newSourceDamage = sourceStack.func_77960_j() + 1;
        int inputDamage = inputStack.func_77960_j();
        ItemStack recipeOutput = CraftingManagerTFC.getInstance().findMatchingRecipe(this.containerInv, this.world);

        if (sourceItem instanceof com.bioxx.tfc.Items.ItemMeltedMetal && inputItem instanceof com.bioxx.tfc.Items.ItemMeltedMetal) {

          if (sourceItem == inputItem && inputDamage != 0)
          {
            sourceStack.func_77964_b(newSourceDamage);
            inputStack.func_77964_b(inputDamage - 1);
            if (newSourceDamage >= sourceStack.func_77958_k() - 1)
            {
              this.containerInv.func_70299_a(0, new ItemStack(TFCItems.ceramicMold, 1, 1));
            }
          }

        } else if (recipeOutput != null) {

          recipeOutput.func_77982_d(inputStack.field_77990_d);
          this.craftResult.func_70299_a(0, recipeOutput);
          this.containerInv.func_70299_a(1, null);
          this.containerInv.func_70299_a(1, new ItemStack(TFCItems.ceramicMold, 1, 1));
          this.containerInv.func_70299_a(0, null);
        }
      }

      for (int i = 0; i < this.field_75149_d.size(); i++) {

        ICrafting player = this.field_75149_d.get(i);
        if (pi.moldTransferTimer != oldTransferTimer) {
          player.func_71112_a(this, 0, pi.moldTransferTimer);
        }
      }
    }
  }


  public ItemStack transferStackInSlotTFC(EntityPlayer player, int slotNum) {
    ItemStack origStack = null;
    Slot slot = this.field_75151_b.get(slotNum);

    if (slot != null && slot.func_75216_d()) {

      ItemStack slotStack = slot.func_75211_c();
      origStack = slotStack.func_77946_l();


      if (slotNum < 3) {

        if (!func_75135_a(slotStack, 3, this.field_75151_b.size(), true)) {
          return null;

        }
      }
      else if (!func_75135_a(slotStack, 0, 3, false)) {
        return null;
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


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Containers\ContainerMold.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */