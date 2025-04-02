package com.bioxx.tfc.TileEntities;

import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.Entities.Mobs.EntityCowTFC;
import com.bioxx.tfc.Food.ItemFoodTFC;
import com.bioxx.tfc.TerraFirmaCraft;
import com.bioxx.tfc.api.Crafting.QuernManager;
import com.bioxx.tfc.api.Crafting.QuernRecipe;
import com.bioxx.tfc.api.Food;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
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
import net.minecraft.util.AxisAlignedBB;

public class TEQuern
  extends NetworkTileEntity
  implements IInventory {
  public ItemStack[] storage = new ItemStack[3];

  public int rotation;

  public boolean shouldRotate;
  public int rotatetimer;
  public boolean hasQuern;

  public void func_145845_h() {
    if (!this.field_145850_b.field_72995_K) {
      TFC_Core.handleItemTicking(this, this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e);
    }
    this.hasQuern = (this.storage[2] != null);

    if (this.shouldRotate) {

      this.rotatetimer++;
      if (this.rotatetimer == 90) {

        this.rotatetimer = 0;
        this.shouldRotate = false;
        if (!this.field_145850_b.field_72995_K)
        {
          if (processItem() && this.storage[2] != null) {
            damageStackInSlot(2);
          }
        }
      }
    }
  }


  @SideOnly(Side.CLIENT)
  public AxisAlignedBB getRenderBoundingBox() {
    return AxisAlignedBB.func_72330_a(this.field_145851_c, this.field_145848_d, this.field_145849_e, (this.field_145851_c + 1), (this.field_145848_d + 1), (this.field_145849_e + 1));
  }


  public boolean processItem() {
    if (this.storage[0] != null) {

      QuernRecipe qr = QuernManager.getInstance().findMatchingRecipe(this.storage[0]);
      if (qr == null) {

        TerraFirmaCraft.LOG.warn("QUERN RECIPE NOT FOUND! This is a BUG! -- " + this.storage[0].func_77973_b().func_77658_a());
        return false;
      }


      if (this.storage[1] != null && (this.storage[1].func_77973_b() != qr.getResult().func_77973_b() || this.storage[1].func_77960_j() != qr.getResult().func_77960_j())) {

        ejectItem(this.storage[1]);
        this.storage[1] = null;
      }

      if (qr.getInItem().func_77973_b() instanceof com.bioxx.tfc.api.Interfaces.IFood) {

        if (this.storage[1] != null) {

          float slot0Weight = Food.getWeight(this.storage[0]);
          float slot1Weight = Food.getWeight(this.storage[1]);
          float newWeight = slot0Weight + slot1Weight;

          if (newWeight > 160.0F) {

            Food.setWeight(this.storage[1], newWeight - 160.0F);

            ItemStack tossStack = this.storage[1].func_77946_l();
            Food.setWeight(tossStack, 160.0F);
            ejectItem(tossStack);
          }
          else {

            Food.setWeight(this.storage[1], newWeight);
          }
          this.storage[0] = null;
          this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
          return true;
        }

        if (this.storage[1] == null)
        {
          this.storage[1] = qr.getResult().func_77946_l();
          float flourWeight = Food.getWeight(this.storage[0]);
          float flourDecay = Food.getDecay(this.storage[0]);
          ItemFoodTFC.createTag(this.storage[1], flourWeight, flourDecay);
          this.storage[0] = null;
          this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
          return true;
        }

      } else {

        if ((this.storage[0]).field_77994_a == (qr.getInItem()).field_77994_a) {

          this.storage[0] = null;
          this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
        } else {

          (this.storage[0]).field_77994_a -= (qr.getInItem()).field_77994_a;
        }
        if (this.storage[1] == null) {
          this.storage[1] = qr.getResult().func_77946_l();
        } else if ((this.storage[1]).field_77994_a < this.storage[1].func_77976_d()) {

          if ((qr.getResult()).field_77994_a + (this.storage[1]).field_77994_a > this.storage[1].func_77976_d()) {

            int amountleft = (qr.getResult()).field_77994_a - this.storage[1].func_77976_d() - (this.storage[1]).field_77994_a;
            ItemStack tossStack = qr.getResult().func_77946_l();
            tossStack.field_77994_a = tossStack.func_77976_d();
            ejectItem(tossStack);
            ItemStack remainStack = qr.getResult().func_77946_l();
            remainStack.field_77994_a = amountleft;
            this.storage[1] = remainStack;
          } else {

            (this.storage[1]).field_77994_a += (qr.getResult()).field_77994_a;
          }
        } else {

          ejectItem(this.storage[1]);
          this.storage[1] = qr.getResult().func_77946_l();
        }
        return true;
      }
    }
    return false;
  }


  public void damageStackInSlot(int slot) {
    if (this.storage[slot] != null) {


      this.storage[slot].func_77972_a(slot, (EntityLivingBase)new EntityCowTFC(this.field_145850_b));
      if ((this.storage[slot]).field_77994_a == 0 || this.storage[slot].func_77960_j() == this.storage[slot].func_77958_k()) {

        func_70299_a(slot, (ItemStack)null);
        this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
      }
    }
  }



  public ItemStack func_70298_a(int slot, int amount) {
    if (this.storage[slot] != null) {

      if ((this.storage[slot]).field_77994_a <= amount) {

        ItemStack itemstack = this.storage[slot];
        func_70299_a(slot, (ItemStack)null);
        return itemstack;
      }
      ItemStack itemstack1 = this.storage[slot].func_77979_a(amount);
      if ((this.storage[slot]).field_77994_a == 0)
        func_70299_a(slot, (ItemStack)null);
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

      if (this.storage[i] != null) {

        EntityItem entityitem = new EntityItem(this.field_145850_b, (this.field_145851_c + f), (this.field_145848_d + f1), (this.field_145849_e + f2), this.storage[i]);
        entityitem.field_70159_w = ((float)rand.nextGaussian() * f3);
        entityitem.field_70181_x = ((float)rand.nextGaussian() * f3 + 0.2F);
        entityitem.field_70179_y = ((float)rand.nextGaussian() * f3);
        this.field_145850_b.func_72838_d((Entity)entityitem);
      }
    }
  }


  public void ejectItem(ItemStack is) {
    float f3 = 0.05F;

    Random rand = new Random();
    float f = rand.nextFloat() * 0.8F + 0.1F;
    float f1 = rand.nextFloat() * 2.0F + 0.4F;
    float f2 = rand.nextFloat() * 0.8F + 0.1F;

    if (this.storage[1] != null) {

      EntityItem entityitem = new EntityItem(this.field_145850_b, (this.field_145851_c + f), (this.field_145848_d + f1), (this.field_145849_e + f2), is);
      entityitem.field_70159_w = ((float)rand.nextGaussian() * f3);
      entityitem.field_70181_x = ((float)rand.nextGaussian() * f3 + 0.05F);
      entityitem.field_70179_y = ((float)rand.nextGaussian() * f3);
      this.field_145850_b.func_72838_d((Entity)entityitem);
    }
  }



  public int func_70302_i_() {
    return this.storage.length;
  }



  public ItemStack func_70301_a(int slot) {
    return this.storage[slot];
  }



  public void func_70299_a(int slot, ItemStack is) {
    this.storage[slot] = is;
    if (is != null && is.field_77994_a > func_70297_j_()) {
      is.field_77994_a = func_70297_j_();
    }
  }


  public String func_145825_b() {
    return "Quern";
  }



  public int func_70297_j_() {
    return 64;
  }



  public boolean func_70300_a(EntityPlayer player) {
    return false;
  }




  public void func_70295_k_() {}




  public void func_70305_f() {}



  public ItemStack func_70304_b(int slot) {
    return null;
  }



  public boolean func_145818_k_() {
    return false;
  }



  public boolean func_94041_b(int slot, ItemStack is) {
    return false;
  }



  public void func_145839_a(NBTTagCompound nbttagcompound) {
    super.func_145839_a(nbttagcompound);
    NBTTagList nbttaglist = nbttagcompound.func_150295_c("Items", 10);
    this.storage = new ItemStack[func_70302_i_()];
    for (int i = 0; i < nbttaglist.func_74745_c(); i++) {

      NBTTagCompound nbttagcompound1 = nbttaglist.func_150305_b(i);
      byte byte0 = nbttagcompound1.func_74771_c("Slot");
      if (byte0 >= 0 && byte0 < this.storage.length)
        this.storage[byte0] = ItemStack.func_77949_a(nbttagcompound1);
    }
    this.hasQuern = nbttagcompound.func_74767_n("hasQuern");
    this.shouldRotate = nbttagcompound.func_74767_n("shouldRotate");
  }



  public void func_145841_b(NBTTagCompound nbttagcompound) {
    super.func_145841_b(nbttagcompound);
    NBTTagList nbttaglist = new NBTTagList();
    for (int i = 0; i < this.storage.length; i++) {

      if (this.storage[i] != null) {

        NBTTagCompound nbttagcompound1 = new NBTTagCompound();
        nbttagcompound1.func_74774_a("Slot", (byte)i);
        this.storage[i].func_77955_b(nbttagcompound1);
        nbttaglist.func_74742_a((NBTBase)nbttagcompound1);
      }
    }
    nbttagcompound.func_74782_a("Items", (NBTBase)nbttaglist);
    nbttagcompound.func_74757_a("hasQuern", this.hasQuern);
    nbttagcompound.func_74757_a("shouldRotate", this.shouldRotate);
  }



  public Packet func_145844_m() {
    NBTTagCompound nbt = new NBTTagCompound();
    func_145841_b(nbt);
    return (Packet)new S35PacketUpdateTileEntity(this.field_145851_c, this.field_145848_d, this.field_145849_e, 1, nbt);
  }



  public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
    func_145839_a(pkt.func_148857_g());
  }



  public void handleInitPacket(NBTTagCompound nbt) {
    this.hasQuern = nbt.func_74767_n("hasQuern");
    this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
  }





  public void handleDataPacket(NBTTagCompound nbt) {}




  public void createDataNBT(NBTTagCompound nbt) {}




  public void createInitNBT(NBTTagCompound nbt) {
    nbt.func_74757_a("hasQuern", this.hasQuern);
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\TileEntities\TEQuern.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */