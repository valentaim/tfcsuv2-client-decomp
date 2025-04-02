package com.bioxx.tfc.Containers;

import com.bioxx.tfc.api.TFC_ItemHeat;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;



public class ContainerTFC
  extends Container
{
  public int bagsSlotNum;
  public EntityPlayer player;
  protected boolean isLoading;
  protected boolean doItemSaving;

  public boolean func_75145_c(EntityPlayer var1) {
    return true;
  }






  public void saveContents(ItemStack is) {}






  public ItemStack loadContents(int slot) {
    return null;
  }



  public ItemStack func_75144_a(int par1, int par2, int par3, EntityPlayer par4EntityPlayer) {
    ItemStack is = super.func_75144_a(par1, par2, par3, par4EntityPlayer);
    saveContents(is);
    return is;
  }



  protected boolean func_75135_a(ItemStack is, int slotStart, int slotFinish, boolean par4) {
    boolean merged = false;
    int slotIndex = slotStart;

    if (par4) {
      slotIndex = slotFinish - 1;
    }



    if (is.func_77985_e())
    {
      while (is.field_77994_a > 0 && ((!par4 && slotIndex < slotFinish) || (par4 && slotIndex >= slotStart))) {

        Slot slot = this.field_75151_b.get(slotIndex);
        ItemStack slotstack = slot.func_75211_c();

        if (slotstack != null && slotstack
          .func_77973_b() == is.func_77973_b() && is

          .func_77960_j() == slotstack.func_77960_j() &&
          ItemStack.func_77970_a(is, slotstack) && slotstack.field_77994_a < slot
          .func_75219_a()) {

          int mergedStackSize = is.field_77994_a + getSmaller(slotstack.field_77994_a, slot.func_75219_a());


          if (mergedStackSize <= is.func_77976_d() && mergedStackSize <= slot.func_75219_a()) {

            is.field_77994_a = 0;
            slotstack.field_77994_a = mergedStackSize;
            slot.func_75218_e();
            merged = true;
          }
          else if (slotstack.field_77994_a < is.func_77976_d() && slotstack.field_77994_a < slot.func_75219_a()) {


            if (slot.func_75219_a() >= is.func_77976_d()) {

              is.field_77994_a -= is.func_77976_d() - slotstack.field_77994_a;
              slotstack.field_77994_a = is.func_77976_d();
              slot.func_75218_e();
              merged = true;

            }
            else if (slot.func_75219_a() < is.func_77976_d()) {

              is.field_77994_a -= slot.func_75219_a() - slotstack.field_77994_a;
              slotstack.field_77994_a = slot.func_75219_a();
              slot.func_75218_e();
              merged = true;
            }
          }
        }

        if (par4) {
          slotIndex--; continue;
        }
        slotIndex++;
      }
    }

    if (is.field_77994_a > 0) {

      if (par4) {
        slotIndex = slotFinish - 1;
      } else {
        slotIndex = slotStart;
      }
      while ((!par4 && slotIndex < slotFinish) || (par4 && slotIndex >= slotStart)) {

        Slot slot = this.field_75151_b.get(slotIndex);
        ItemStack slotstack = slot.func_75211_c();
        if (slotstack == null && slot.func_75214_a(is) && slot.func_75219_a() < is.field_77994_a) {

          ItemStack copy = is.func_77946_l();
          copy.field_77994_a = slot.func_75219_a();
          is.field_77994_a -= slot.func_75219_a();
          slot.func_75215_d(copy);
          slot.func_75218_e();
          merged = true;

          break;
        }
        if (slotstack == null && slot.func_75214_a(is)) {

          slot.func_75215_d(is.func_77946_l());
          slot.func_75218_e();
          is.field_77994_a = 0;
          merged = true;

          break;
        }
        if (par4) {
          slotIndex--; continue;
        }
        slotIndex++;
      }
    }

    return merged;
  }


  protected int getSmaller(int i, int j) {
    if (i < j) {
      return i;
    }
    return j;
  }




  public void func_75142_b() {
    boolean shouldSave = false;
    boolean shouldReload = false;
    int i;
    for (i = 0; i < this.field_75151_b.size(); i++) {

      ItemStack itemstack = ((Slot)this.field_75151_b.get(i)).func_75211_c();
      ItemStack itemstack1 = this.field_75153_a.get(i);

      if (!areItemStacksEqual(itemstack1, itemstack)) {

        if (this.doItemSaving && i < this.field_75153_a.size() - 36 && !this.isLoading) {
          shouldSave = true;
        }
        itemstack1 = (itemstack == null) ? null : itemstack.func_77946_l();
        if (itemstack1 != null && itemstack1.field_77994_a == 0)
          itemstack1 = null;
        this.field_75153_a.set(i, itemstack1);

        if (shouldSave) {

          int slotNum = this.bagsSlotNum + this.field_75153_a.size() - 36;
          saveContents(this.field_75153_a.get(slotNum));
          this.player.field_71071_by.func_70299_a(this.bagsSlotNum, this.field_75153_a.get(slotNum));
          for (int k = 0; k < this.field_75149_d.size(); k++) {
            ((ICrafting)this.field_75149_d.get(k)).func_71111_a(this, slotNum, this.field_75153_a.get(slotNum));
          }
        }
        for (int j = 0; j < this.field_75149_d.size(); j++) {
          ((ICrafting)this.field_75149_d.get(j)).func_71111_a(this, i, itemstack1);
        }
      }
    }
    for (i = 0; i < this.field_75151_b.size() - 36; i++)
    {





      shouldReload = true;
    }


    if (shouldReload && !this.isLoading) {
      reloadContainer();
    }
    this.isLoading = false;
  }





  public void reloadContainer() {}




  public static boolean areItemStacksEqual(ItemStack is1, ItemStack is2) {
    return (is1 == null && is2 == null) ? true : ((is1 != null && is2 != null) ? isItemStackEqual(is1, is2) : false);
  }


  public static boolean isItemStackEqual(ItemStack is1, ItemStack is2) {
    return (is1.field_77994_a != is2.field_77994_a) ? false : (
      (is1.func_77973_b() != is2.func_77973_b()) ? false : (
      (is1.func_77960_j() != is2.func_77960_j()) ? false : ((is1.field_77990_d == null && is2.field_77990_d != null) ? false : ((is1.field_77990_d == null ||

      areCompoundsEqual(is1, is2))))));
  }


  public static boolean areCompoundsEqual(ItemStack is1, ItemStack is2) {
    ItemStack is3 = is1.func_77946_l();
    ItemStack is4 = is2.func_77946_l();
    NBTTagCompound is3Tags = is3.func_77978_p();
    NBTTagCompound is4Tags = is4.func_77978_p();

    if (is3Tags == null) {
      return (is4Tags == null || is4Tags.func_82582_d());
    }
    if (is4Tags == null) {
      return is3Tags.func_82582_d();
    }
    float temp3 = TFC_ItemHeat.getTemp(is1);
    float temp4 = TFC_ItemHeat.getTemp(is2);
    is3Tags.func_82580_o("temp");
    is4Tags.func_82580_o("temp");

    return (is3Tags.equals(is4Tags) && Math.abs(temp3 - temp4) < 5.0F);
  }


  public ItemStack transferStackInSlotTFC(EntityPlayer entityplayer, int slotNum) {
    return super.func_82846_b(entityplayer, slotNum);
  }



  public final ItemStack func_82846_b(EntityPlayer entityplayer, int slotNum) {
    Slot slot = this.field_75151_b.get(slotNum);
    ItemStack is = transferStackInSlotTFC(entityplayer, slotNum);


    if (!slot.func_75216_d() && entityplayer instanceof EntityPlayerMP && !entityplayer.field_70170_p.field_72995_K) {

      EntityPlayerMP mp = (EntityPlayerMP)entityplayer;
      mp.func_71111_a(this, slot.field_75222_d, slot.func_75211_c());
    }

    return is;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Containers\ContainerTFC.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */