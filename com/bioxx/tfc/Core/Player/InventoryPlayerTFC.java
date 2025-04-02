package com.bioxx.tfc.Core.Player;

import com.bioxx.tfc.Core.TFC_Core;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class InventoryPlayerTFC extends InventoryPlayer {
  public ItemStack[] extraEquipInventory = new ItemStack[TFC_Core.getExtraEquipInventorySize()];

  public InventoryPlayerTFC(EntityPlayer par1EntityPlayer) {
    super(par1EntityPlayer);
    this.field_70458_d = par1EntityPlayer;
  }



  public void func_70449_g(float par1) {
    par1 /= 4.0F;
    if (par1 < 1.0F) {
      par1 = 1.0F;
    }
    for (int i = 0; i < this.field_70460_b.length; i++) {

      if (this.field_70460_b[i] != null && this.field_70460_b[i].func_77973_b() instanceof net.minecraft.item.ItemArmor) {

        this.field_70460_b[i].func_77972_a((int)par1, (EntityLivingBase)this.field_70458_d);
        if ((this.field_70460_b[i]).field_77994_a == 0) {
          this.field_70460_b[i] = null;
        }
      }
    }
  }


  public int func_70302_i_() {
    return this.field_70462_a.length + this.field_70460_b.length + this.extraEquipInventory.length;
  }



  public void func_70443_b(NBTTagList par1NBTTagList) {
    super.func_70443_b(par1NBTTagList);
    this.extraEquipInventory = new ItemStack[TFC_Core.getExtraEquipInventorySize()];

    NBTTagList extraList = this.field_70458_d.getEntityData().func_150295_c("ExtraInventory", 10);

    for (int i = 0; i < extraList.func_74745_c(); i++) {

      NBTTagCompound nbttagcompound = extraList.func_150305_b(i);
      ItemStack itemstack = ItemStack.func_77949_a(nbttagcompound);
      if (itemstack != null)
      {
        this.extraEquipInventory[i] = itemstack;
      }
    }
  }






  public ItemStack func_70301_a(int par1) {
    ItemStack[] aitemstack = this.field_70462_a;

    if (par1 >= aitemstack.length + this.field_70460_b.length) {

      par1 -= aitemstack.length + this.field_70460_b.length;
      aitemstack = this.extraEquipInventory;
    } else if (par1 >= aitemstack.length) {

      par1 -= aitemstack.length;
      aitemstack = this.field_70460_b;
    }
    return aitemstack[par1];
  }



  public ItemStack func_70304_b(int par1) {
    ItemStack[] aitemstack = this.field_70462_a;

    if (par1 >= aitemstack.length + this.field_70460_b.length) {

      par1 -= aitemstack.length + this.field_70460_b.length;
      aitemstack = this.extraEquipInventory;
    } else if (par1 >= aitemstack.length) {

      par1 -= aitemstack.length;
      aitemstack = this.field_70460_b;
    }
    if (aitemstack[par1] != null) {

      ItemStack itemstack = aitemstack[par1];
      aitemstack[par1] = null;
      return itemstack;
    }


    return null;
  }




  public int func_146027_a(Item item, int meta) {
    for (int i = 0; i < this.extraEquipInventory.length; i++) {

      if (this.extraEquipInventory[i] != null && (item == null || this.extraEquipInventory[i].func_77973_b() == item) && (meta <= -1 || this.extraEquipInventory[i]
        .func_77960_j() == meta))
      {
        this.extraEquipInventory[i] = null;
      }
    }
    return super.func_146027_a(item, meta);
  }



  public void func_70429_k() {
    for (int i = 0; i < this.extraEquipInventory.length; i++) {

      if (this.extraEquipInventory[i] != null)
      {
        this.extraEquipInventory[i].func_77945_a(this.field_70458_d.field_70170_p, (Entity)this.field_70458_d, i, (this.field_70461_c == i));
      }
    }
    super.func_70429_k();
  }



  public ItemStack func_70298_a(int par1, int par2) {
    ItemStack[] aitemstack = this.field_70462_a;

    if (par1 >= aitemstack.length + this.field_70460_b.length) {

      par1 -= aitemstack.length + this.field_70460_b.length;
      aitemstack = this.extraEquipInventory;
    } else if (par1 >= aitemstack.length) {

      par1 -= aitemstack.length;
      aitemstack = this.field_70460_b;
    }


    if (aitemstack[par1] != null) {



      if ((aitemstack[par1]).field_77994_a <= par2) {

        ItemStack itemStack = aitemstack[par1];
        aitemstack[par1] = null;
        return itemStack;
      }


      ItemStack itemstack = aitemstack[par1].func_77979_a(par2);

      if ((aitemstack[par1]).field_77994_a == 0)
      {
        aitemstack[par1] = null;
      }

      return itemstack;
    }



    return null;
  }






  public void func_70436_m() {
    for (int i = 0; i < this.extraEquipInventory.length; i++) {

      if (this.extraEquipInventory[i] != null) {

        this.field_70458_d.func_146097_a(this.extraEquipInventory[i], true, false);
        this.extraEquipInventory[i] = null;
      }
    }
    super.func_70436_m();
  }





  public boolean func_70431_c(ItemStack par1ItemStack) {
    for (int i = 0; i < this.extraEquipInventory.length; i++) {

      if (this.extraEquipInventory[i] != null && this.extraEquipInventory[i].func_77969_a(par1ItemStack))
      {
        return true;
      }
    }
    return super.func_70431_c(par1ItemStack);
  }




  public void func_70299_a(int par1, ItemStack par2ItemStack) {
    ItemStack[] aitemstack = this.field_70462_a;

    if (par1 >= aitemstack.length + this.field_70460_b.length) {

      par1 -= aitemstack.length + this.field_70460_b.length;
      aitemstack = this.extraEquipInventory;
    } else if (par1 >= aitemstack.length) {

      par1 -= aitemstack.length;
      aitemstack = this.field_70460_b;
    }

    aitemstack[par1] = par2ItemStack;
  }










  public void func_70455_b(InventoryPlayer par1InventoryPlayer) {
    if (par1InventoryPlayer instanceof InventoryPlayerTFC) {
      copyInventoryTFC((InventoryPlayerTFC)par1InventoryPlayer);
    } else {

      super.func_70455_b(par1InventoryPlayer);
    }
  }




  public void copyInventoryTFC(InventoryPlayerTFC par1InventoryPlayer) {
    for (int i = 0; i < this.extraEquipInventory.length; i++)
    {
      this.extraEquipInventory[i] = ItemStack.func_77944_b(par1InventoryPlayer.extraEquipInventory[i]);
    }
    super.func_70455_b(par1InventoryPlayer);
  }



  public NBTTagList func_70442_a(NBTTagList par1NBTTagList) {
    super.func_70442_a(par1NBTTagList);



    NBTTagList tagList = new NBTTagList();
    for (int i = 0; i < this.extraEquipInventory.length; i++) {

      ItemStack is = this.extraEquipInventory[i];
      if (is != null) {

        NBTTagCompound nbt = new NBTTagCompound();
        nbt.func_74774_a("Slot", (byte)i);
        is.func_77955_b(nbt);
        tagList.func_74742_a((NBTBase)nbt);
      }
    }
    this.field_70458_d.getEntityData().func_74782_a("ExtraInventory", (NBTBase)tagList);
    return par1NBTTagList;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Core\Player\InventoryPlayerTFC.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */