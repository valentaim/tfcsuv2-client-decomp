package com.bioxx.tfc.TileEntities;

import com.bioxx.tfc.Core.TFC_Time;
import com.bioxx.tfc.Entities.Mobs.EntityChickenTFC;
import com.bioxx.tfc.Food.ItemFoodTFC;
import com.bioxx.tfc.api.Entities.IAnimal;
import com.bioxx.tfc.api.TFCOptions;
import com.bioxx.tfc.api.Util.Helper;
import java.util.List;
import java.util.Random;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;

public class TENestBox
  extends TileEntity implements IInventory {
  public ItemStack[] inventory = new ItemStack[4];






  public boolean hasBird() {
    return (getBird() != null);
  }




  public int getBirdsNum() {
    List<EntityChickenTFC> list = this.field_145850_b.func_72872_a(EntityChickenTFC.class, AxisAlignedBB.func_72330_a((this.field_145851_c - 20), (this.field_145848_d - 20), (this.field_145849_e - 20), (this.field_145851_c + 20), (this.field_145848_d + 20), (this.field_145849_e + 20)));


    return list.size();
  }




  public EntityAnimal getBird() {
    List<EntityChickenTFC> list = this.field_145850_b.func_72872_a(EntityChickenTFC.class, AxisAlignedBB.func_72330_a(this.field_145851_c + 0.1D, this.field_145848_d, this.field_145849_e + 0.1D, this.field_145851_c + 0.9D, this.field_145848_d + 1.1D, this.field_145849_e + 0.9D));













    if (!list.isEmpty())
    {
      for (EntityChickenTFC e : list) {

        if (((EntityChickenTFC)e).getGender() == IAnimal.GenderEnum.FEMALE && ((EntityChickenTFC)e).isAdult() && ((EntityChickenTFC)e)
          .getAnimalTypeID() == Helper.stringToInt("chicken"))
        {
          return (EntityAnimal)e;
        }
      }
    }

    return null;
  }



  public EntityAnimal getRooster() {
    List<EntityChickenTFC> list = this.field_145850_b.func_72872_a(EntityChickenTFC.class, AxisAlignedBB.func_72330_a((this.field_145851_c - 5), this.field_145848_d, (this.field_145849_e - 5), (this.field_145851_c + 5), (this.field_145848_d + 2), (this.field_145849_e + 5)));



    if (!list.isEmpty())
    {
      for (EntityChickenTFC e : list) {

        if (((EntityChickenTFC)e).getGender() == IAnimal.GenderEnum.MALE && ((EntityChickenTFC)e).isAdult())
          return (EntityAnimal)e;
      }
    }
    return null;
  }




  public void func_70305_f() {}



  public ItemStack func_70298_a(int i, int j) {
    if (this.inventory[i] != null) {

      if ((this.inventory[i]).field_77994_a <= j) {

        ItemStack itemstack = this.inventory[i];
        this.inventory[i] = null;
        return itemstack;
      }
      ItemStack itemstack1 = this.inventory[i].func_77979_a(j);
      if ((this.inventory[i]).field_77994_a == 0)
        this.inventory[i] = null;
      return itemstack1;
    }


    return null;
  }



  public void ejectContents() {
    float f3 = 0.05F;

    Random rand = new Random();
    float f = rand.nextFloat() * 0.8F + 0.1F;
    float f1 = rand.nextFloat() * 2.0F + 0.4F;
    float f2 = rand.nextFloat() * 0.8F + 0.1F;

    for (int i = 0; i < func_70302_i_(); i++) {

      if (this.inventory != null) {

        EntityItem entityitem = new EntityItem(this.field_145850_b, (this.field_145851_c + f), (this.field_145848_d + f1), (this.field_145849_e + f2), this.inventory[i]);
        entityitem.field_70159_w = ((float)rand.nextGaussian() * f3);
        entityitem.field_70181_x = ((float)rand.nextGaussian() * f3 + 0.2F);
        entityitem.field_70179_y = ((float)rand.nextGaussian() * f3);
        this.field_145850_b.func_72838_d((Entity)entityitem);
      }
    }
  }



  public int func_70297_j_() {
    return 64;
  }



  public String func_145825_b() {
    return "NestBox";
  }



  public int func_70302_i_() {
    return 4;
  }



  public ItemStack func_70301_a(int i) {
    return this.inventory[i];
  }



  public ItemStack func_70304_b(int var1) {
    return null;
  }



  public boolean func_70300_a(EntityPlayer entityplayer) {
    return true;
  }




  public void func_70295_k_() {}



  public void func_70299_a(int i, ItemStack itemstack) {
    this.inventory[i] = itemstack;
    if (itemstack != null && itemstack.field_77994_a > func_70297_j_()) {
      itemstack.field_77994_a = func_70297_j_();
    }
  }


  public void func_145845_h() {
    if (!this.field_145850_b.field_72995_K) {

      EntityAnimal bird = getBird();
      if (bird != null) {

        ItemStack item = ((EntityChickenTFC)bird).getEggs();

        EntityChickenTFC father = (EntityChickenTFC)getRooster();
        for (int j = 0; item != null && j < func_70302_i_(); j++) {

          if (this.inventory[j] == null) {

            ItemFoodTFC.createTag(item, 2.0F);
            if (father != null) {

              NBTTagCompound nbt = item.func_77978_p();
              nbt.func_74772_a("Fertilized", TFC_Time.getTotalTicks() + (long)(TFCOptions.animalTimeMultiplier * (float)TFC_Time.ticksInMonth * 0.75F));
              nbt.func_74782_a("Genes", (NBTBase)createGenes((EntityChickenTFC)bird, father));
              item.func_77982_d(nbt);
            }
            this.field_145850_b.func_72956_a((Entity)bird, "mob.chicken.plop", 1.0F, (this.field_145850_b.field_73012_v.nextFloat() - this.field_145850_b.field_73012_v.nextFloat()) * 0.2F + 1.0F);
            func_70299_a(j, item);

            break;
          }
        }
      }

      for (int i = 0; i < func_70302_i_(); i++) {

        if (this.inventory[i] != null)
        {
          if (this.inventory[i].func_77978_p() != null && this.inventory[i].func_77978_p().func_74764_b("Fertilized")) {

            long time = this.inventory[i].func_77978_p().func_74763_f("Fertilized");
            if (time <= TFC_Time.getTotalTicks()) {

              if (getBirdsNum() < 20) {

                EntityChickenTFC chick = new EntityChickenTFC(this.field_145850_b, this.field_145851_c + 0.5D, (this.field_145848_d + 1), this.field_145849_e + 0.5D, (NBTTagCompound)this.inventory[i].func_77978_p().func_74781_a("Genes"));
                chick.func_70012_b(this.field_145851_c + 0.5D, (this.field_145848_d + 1), this.field_145849_e + 0.5D, 0.0F, 0.0F);
                chick.field_70759_as = chick.field_70177_z;
                chick.field_70761_aq = chick.field_70177_z;
                this.field_145850_b.func_72838_d((Entity)chick);
              }  this.inventory[i] = null;
            }
          }
        }
      }
    }
  }



  public boolean func_145818_k_() {
    return false;
  }



  public boolean func_94041_b(int i, ItemStack itemstack) {
    return false;
  }


  public NBTTagCompound createGenes(EntityChickenTFC mother, EntityChickenTFC father) {
    NBTTagCompound nbt = new NBTTagCompound();
    nbt.func_74776_a("m_size", mother.getSizeMod());
    nbt.func_74776_a("f_size", father.getSizeMod());
    return nbt;
  }



  public void func_145841_b(NBTTagCompound nbttagcompound) {
    super.func_145841_b(nbttagcompound);
    NBTTagList nbttaglist = new NBTTagList();
    for (int i = 0; i < this.inventory.length; i++) {

      if (this.inventory[i] != null) {

        NBTTagCompound nbttagcompound1 = new NBTTagCompound();
        nbttagcompound1.func_74774_a("Slot", (byte)i);
        this.inventory[i].func_77955_b(nbttagcompound1);
        nbttaglist.func_74742_a((NBTBase)nbttagcompound1);
      }
    }
    nbttagcompound.func_74782_a("Items", (NBTBase)nbttaglist);
  }



  public void func_145839_a(NBTTagCompound nbttagcompound) {
    super.func_145839_a(nbttagcompound);
    NBTTagList nbttaglist = nbttagcompound.func_150295_c("Items", 10);
    this.inventory = new ItemStack[func_70302_i_()];
    for (int i = 0; i < nbttaglist.func_74745_c(); i++) {

      NBTTagCompound nbttagcompound1 = nbttaglist.func_150305_b(i);
      byte byte0 = nbttagcompound1.func_74771_c("Slot");
      if (byte0 >= 0 && byte0 < this.inventory.length)
        this.inventory[byte0] = ItemStack.func_77949_a(nbttagcompound1);
    }
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\TileEntities\TENestBox.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */