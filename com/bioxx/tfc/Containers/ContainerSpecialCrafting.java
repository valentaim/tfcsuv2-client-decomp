package com.bioxx.tfc.Containers;

import com.bioxx.tfc.Containers.Slots.SlotSpecialCraftingOutput;
import com.bioxx.tfc.Core.Player.PlayerInfo;
import com.bioxx.tfc.Core.Player.PlayerInventory;
import com.bioxx.tfc.Core.Player.PlayerManagerTFC;
import com.bioxx.tfc.Core.TFC_Achievements;
import com.bioxx.tfc.Items.Tools.ItemMiscToolHead;
import com.bioxx.tfc.api.Crafting.CraftingManagerTFC;
import com.bioxx.tfc.api.TFCBlocks;
import com.bioxx.tfc.api.TFCItems;
import fof.tfcsu.utils.ExpBonus;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCraftResult;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatBase;
import net.minecraft.world.World;


public class ContainerSpecialCrafting
  extends ContainerTFC
{
  public InventoryCrafting craftMatrix = new InventoryCrafting(this, 5, 5);

  private SlotSpecialCraftingOutput outputSlot;

  private boolean decreasedStack;

  private boolean gived = false;
  public IInventory craftResult = (IInventory)new InventoryCraftResult();
  private World worldObj;
  private InventoryPlayer invPlayer;
  private boolean isConstructing;

  public ContainerSpecialCrafting(InventoryPlayer inventoryplayer, ItemStack is, World world, int x, int y, int z) {
    this.invPlayer = inventoryplayer;
    this.worldObj = world;
    this.decreasedStack = false;
    this.isConstructing = true;
    this.bagsSlotNum = inventoryplayer.field_70461_c;
    for (int j1 = 0; j1 < 25; j1++) {

      if (is != null) {
        this.craftMatrix.func_70299_a(j1, is.func_77946_l());
      }
    }
    this.outputSlot = new SlotSpecialCraftingOutput(this, inventoryplayer.field_70458_d, (IInventory)this.craftMatrix, this.craftResult, 0, 128, 44);
    func_75146_a((Slot)this.outputSlot);

    PlayerInventory.buildInventoryLayout(this, inventoryplayer, 8, 108, true, true);

    func_75130_a((IInventory)this.craftMatrix);
    this.isConstructing = false;
  }



  public void func_75134_a(EntityPlayer player) {
    super.func_75134_a(player);
    if (!this.worldObj.field_72995_K) {

      ItemStack is = this.craftResult.func_70304_b(0);
      if (is != null) {
        player.func_70099_a(is, 0.0F);
      }
    }
  }





  public void func_75130_a(IInventory ii) {
    ItemStack result = CraftingManagerTFC.getInstance().findMatchingRecipe(this.craftMatrix, this.worldObj);


    if (!this.decreasedStack && !this.isConstructing) {

      PlayerInfo pi = PlayerManagerTFC.getInstance().getPlayerInfoFromPlayer(this.invPlayer.field_70458_d);


      if (pi.specialCraftingType.func_77973_b() == TFCItems.flatClay) {

        if (result != null) {

          setDecreasedStack(Boolean.valueOf(true));
          if (!this.worldObj.field_72995_K && (this.invPlayer.func_70448_g()).field_77994_a >= 5) {
            this.invPlayer.func_70298_a(this.invPlayer.field_70461_c, 5);
          } else {

            setDecreasedStack(Boolean.valueOf(false));


            return;
          }
        }
      } else if (hasPieceBeenRemoved(pi)) {

        setDecreasedStack(Boolean.valueOf(true));
        if (!this.worldObj.field_72995_K) {
          this.invPlayer.func_146026_a(this.invPlayer.func_70448_g().func_77973_b());
        }
      }
    }

    if (this.decreasedStack) {

      this.craftResult.func_70299_a(0, result);


      if (result != null && this.invPlayer.field_70458_d != null) {

        Item item = result.func_77973_b();
        if (!this.gived && !item.func_77658_a().toLowerCase().contains("stone")) { ExpBonus.KNAPPING.give(this.invPlayer.field_70458_d); this.gived = true; }
         if (item instanceof ItemMiscToolHead && ((ItemMiscToolHead)item).getMaterial() != null && (((ItemMiscToolHead)item)
          .getMaterial() == TFCItems.igInToolMaterial || ((ItemMiscToolHead)item)
          .getMaterial() == TFCItems.sedToolMaterial || ((ItemMiscToolHead)item)
          .getMaterial() == TFCItems.igExToolMaterial || ((ItemMiscToolHead)item)
          .getMaterial() == TFCItems.mMToolMaterial)) {

          this.invPlayer.field_70458_d.func_71029_a((StatBase)TFC_Achievements.achStoneAge);
          if (item == TFCItems.stoneKnifeHead && result.field_77994_a == 2) {
            this.invPlayer.field_70458_d.func_71029_a((StatBase)TFC_Achievements.achTwoKnives);
          }
        } else if (item == Item.func_150898_a(TFCBlocks.crucible)) {
          this.invPlayer.field_70458_d.func_71029_a((StatBase)TFC_Achievements.achCrucible);
        }
      }
    }
  }






  public ItemStack transferStackInSlotTFC(EntityPlayer player, int slotNum) {
    ItemStack origStack = null;
    Slot slot = this.field_75151_b.get(slotNum);

    if (slot != null && slot instanceof SlotSpecialCraftingOutput && slot.func_75216_d()) {

      ItemStack slotStack = slot.func_75211_c();
      origStack = slotStack.func_77946_l();

      if (slotNum < 1 && !func_75135_a(slotStack, 1, this.field_75151_b.size(), true)) {
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



  public boolean func_75145_c(EntityPlayer player) {
    return true;
  }



  public boolean hasPieceBeenRemoved(PlayerInfo pi) {
    for (int i = 0; i < this.craftMatrix.func_70302_i_(); i++) {

      if (this.craftMatrix.func_70301_a(i) == null) {
        return true;
      }
    }

    setDecreasedStack(Boolean.valueOf(false));
    return false;
  }


  public void setDecreasedStack(Boolean b) {
    this.decreasedStack = b.booleanValue();
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Containers\ContainerSpecialCrafting.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */