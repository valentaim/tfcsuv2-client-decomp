package com.bioxx.tfc.TileEntities;

import com.bioxx.tfc.Entities.EntityStand;
import java.util.List;
import java.util.Random;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;









public class TEStand
  extends TileEntity
  implements IInventory
{
  public ItemStack[] items = new ItemStack[5];
  public int highlightedSlot = -1;


  public EntityStand entity;

  public float yaw;

  public boolean isTop;

  private boolean hasEntity;


  public void func_70295_k_() {}


  public void func_70305_f() {}


  public void destroy() {
    if (!this.isTop && this.entity != null) {
      this.entity.func_70106_y();
    }
  }


  public ItemStack func_70298_a(int i, int j) {
    if (this.items[i] != null && !this.isTop) {

      if ((this.items[i]).field_77994_a <= j) {

        ItemStack itemstack2 = this.items[i];
        this.items[i] = null;
        return itemstack2;
      }
      ItemStack itemstack1 = this.items[i].func_77979_a(j);
      if ((this.items[i]).field_77994_a == 0)
        this.items[i] = null;
      return itemstack1;
    }


    return null;
  }



  public void ejectContents() {
    if (!this.isTop) {

      float f3 = 0.05F;

      Random rand = new Random();
      float f = rand.nextFloat() * 0.8F + 0.1F;
      float f1 = rand.nextFloat() * 2.0F + 0.4F;
      float f2 = rand.nextFloat() * 0.8F + 0.1F;

      for (int i = 0; i < func_70302_i_(); i++) {

        if (this.items[i] != null) {

          EntityItem entityitem = new EntityItem(this.field_145850_b, (this.field_145851_c + f), (this.field_145848_d + f1), (this.field_145849_e + f2), this.items[i]);
          entityitem.field_70159_w = ((float)rand.nextGaussian() * f3);
          entityitem.field_70181_x = ((float)rand.nextGaussian() * f3 + 0.2F);
          entityitem.field_70179_y = ((float)rand.nextGaussian() * f3);
          this.field_145850_b.func_72838_d((Entity)entityitem);
        }
      }
    }
  }



  public int func_70297_j_() {
    return 1;
  }



  public String func_145825_b() {
    return "Stand";
  }



  public int func_70302_i_() {
    return 5;
  }



  public ItemStack func_70301_a(int i) {
    if (!this.isTop)
      return this.items[i];
    return null;
  }



  public ItemStack func_70304_b(int var1) {
    return null;
  }



  public boolean func_70300_a(EntityPlayer entityplayer) {
    return false;
  }



  public void func_70299_a(int i, ItemStack itemstack) {
    if (!this.isTop) {

      if (this.items[i] == null || itemstack == null) {
        this.items[i] = itemstack;
      }
      if (this.items[i] != null && (this.items[i]).field_77994_a > func_70297_j_()) {
        (this.items[i]).field_77994_a = func_70297_j_();
      }
    }
  }



  public void func_145845_h() {
    if (!this.field_145850_b.field_72995_K && !this.isTop) {

      if (func_145830_o() && !this.hasEntity) {


        this.entity.func_70012_b(this.field_145851_c, this.field_145848_d, this.field_145849_e, this.yaw, 0.0F);
        this.field_145850_b.func_72838_d((Entity)this.entity);

        this.hasEntity = true;
      }

      if (this.hasEntity && this.entity == null) {

        List<EntityStand> list = this.field_145850_b.func_72872_a(EntityStand.class, AxisAlignedBB.func_72330_a(this.field_145851_c, this.field_145848_d, this.field_145849_e, (this.field_145851_c + 1), (this.field_145848_d + 2), (this.field_145849_e + 1)));



        if (!list.isEmpty()) {
          this.entity = list.get(0);
        } else {
          this.hasEntity = false;
        }
      }
    }
  }


  public boolean func_94041_b(int i, ItemStack itemstack) {
    return false;
  }



  public boolean func_145818_k_() {
    return false;
  }



  public void func_145841_b(NBTTagCompound nbttagcompound) {
    super.func_145841_b(nbttagcompound);
    NBTTagList nbttaglist = new NBTTagList();
    nbttagcompound.func_74757_a("hasEntity", this.hasEntity);
    nbttagcompound.func_74776_a("yaw", this.yaw);
    nbttagcompound.func_74757_a("isTop", this.isTop);
    for (int i = 0; i < this.items.length; i++) {

      if (this.items[i] != null) {

        NBTTagCompound nbttagcompound1 = new NBTTagCompound();
        nbttagcompound1.func_74774_a("Slot", (byte)i);
        this.items[i].func_77955_b(nbttagcompound1);
        nbttaglist.func_74742_a((NBTBase)nbttagcompound1);
      }
    }
    nbttagcompound.func_74782_a("Items", (NBTBase)nbttaglist);
  }



  public void func_145839_a(NBTTagCompound nbttagcompound) {
    super.func_145839_a(nbttagcompound);
    this.hasEntity = nbttagcompound.func_74767_n("hasEntity");
    this.yaw = nbttagcompound.func_74760_g("yaw");
    this.isTop = nbttagcompound.func_74767_n("isTop");
    this.items = new ItemStack[func_70302_i_()];
    NBTTagList nbttaglist = nbttagcompound.func_150295_c("Items", 10);
    for (int i = 0; i < nbttaglist.func_74745_c(); i++) {

      NBTTagCompound nbttagcompound1 = nbttaglist.func_150305_b(i);
      byte byte0 = nbttagcompound1.func_74771_c("Slot");
      if (byte0 >= 0 && byte0 < this.items.length) {
        this.items[byte0] = ItemStack.func_77949_a(nbttagcompound1);
      }
    }
  }


  public Packet func_145844_m() {
    NBTTagCompound nbt = new NBTTagCompound();
    func_145841_b(nbt);
    return (Packet)new S35PacketUpdateTileEntity(this.field_145851_c, this.field_145848_d, this.field_145849_e, 1, nbt);
  }



  public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
    func_145839_a(pkt.func_148857_g());
  }

  public void updateGui() {}
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\TileEntities\TEStand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */