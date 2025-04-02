package com.bioxx.tfc.Containers;

import com.bioxx.tfc.TileEntities.TEWorkbench;
import com.bioxx.tfc.api.TFCBlocks;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCraftResult;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.world.World;


public class ContainerWorkbench
  extends ContainerTFC
{
  public InventoryCrafting craftMatrix = new InventoryCrafting(this, 3, 3);


  public IInventory craftResult = (IInventory)new InventoryCraftResult();

  private World worldObj;

  private int posX;
  private int posY;
  private int posZ;

  public ContainerWorkbench(InventoryPlayer par1InventoryPlayer, TEWorkbench wb, World par2World, int par3, int par4, int par5) {
    this.worldObj = par2World;
    this.posX = par3;
    this.posY = par4;
    this.posZ = par5;
    func_75146_a((Slot)new SlotCrafting(par1InventoryPlayer.field_70458_d, (IInventory)this.craftMatrix, this.craftResult, 0, 124, 35));

    int var6;

    for (var6 = 0; var6 < 3; var6++) {

      for (int var7 = 0; var7 < 3; var7++) {
        func_75146_a(new Slot((IInventory)this.craftMatrix, var7 + var6 * 3, 30 + var7 * 18, 17 + var6 * 18));
      }
    }
    for (var6 = 0; var6 < 3; var6++) {

      for (int var7 = 0; var7 < 9; var7++) {
        func_75146_a(new Slot((IInventory)par1InventoryPlayer, var7 + var6 * 9 + 9, 8 + var7 * 18, 84 + var6 * 18));
      }
    }
    for (var6 = 0; var6 < 9; var6++) {
      func_75146_a(new Slot((IInventory)par1InventoryPlayer, var6, 8 + var6 * 18, 142));
    }
    func_75130_a((IInventory)this.craftMatrix);
  }






  public void func_75130_a(IInventory par1IInventory) {
    this.craftResult.func_70299_a(0, CraftingManager.func_77594_a().func_82787_a(this.craftMatrix, this.worldObj));
  }






  public void func_75134_a(EntityPlayer player) {
    super.func_75134_a(player);

    if (!this.worldObj.field_72995_K)
    {
      for (int i = 0; i < 9; i++) {

        ItemStack is = this.craftMatrix.func_70304_b(i);
        if (is != null) {
          player.func_70099_a(is, 0.0F);
        }
      }
    }
  }


  public boolean func_75145_c(EntityPlayer par1EntityPlayer) {
    return (this.worldObj.func_147439_a(this.posX, this.posY, this.posZ) != TFCBlocks.workbench) ? false : ((par1EntityPlayer.func_70092_e(this.posX + 0.5D, this.posY + 0.5D, this.posZ + 0.5D) <= 64.0D));
  }



  public ItemStack transferStackInSlotTFC(EntityPlayer par1EntityPlayer, int par2) {
    ItemStack var3 = null;
    Slot var4 = this.field_75151_b.get(par2);

    if (var4 != null && var4.func_75216_d()) {

      ItemStack var5 = var4.func_75211_c();
      var3 = var5.func_77946_l();

      if (par2 == 0) {

        if (!func_75135_a(var5, 10, 46, true)) {
          return null;
        }
        var4.func_75220_a(var5, var3);
      }
      else if (par2 >= 10 && par2 < 37) {

        if (!func_75135_a(var5, 37, 46, false)) {
          return null;
        }
      } else if (par2 >= 37 && par2 < 46) {

        if (!func_75135_a(var5, 10, 37, false)) {
          return null;
        }
      } else if (!func_75135_a(var5, 10, 46, false)) {

        return null;
      }

      if (var5.field_77994_a == 0) {
        var4.func_75215_d((ItemStack)null);
      } else {
        var4.func_75218_e();
      }
      if (var5.field_77994_a == var3.field_77994_a) {
        return null;
      }
      var4.func_82870_a(par1EntityPlayer, var5);
    }
    return var3;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Containers\ContainerWorkbench.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */