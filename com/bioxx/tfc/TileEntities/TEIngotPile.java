package com.bioxx.tfc.TileEntities;

import com.bioxx.tfc.api.TFCBlocks;
import com.bioxx.tfc.api.TFCItems;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.AxisAlignedBB;

public class TEIngotPile
  extends NetworkTileEntity implements IInventory {
  public ItemStack[] storage;
  public String type;
  public static final Item[] INGOTS = new Item[] { TFCItems.bismuthIngot, TFCItems.bismuthBronzeIngot, TFCItems.blackBronzeIngot, TFCItems.blackSteelIngot, TFCItems.blueSteelIngot, TFCItems.brassIngot, TFCItems.bronzeIngot, TFCItems.copperIngot, TFCItems.goldIngot, TFCItems.wroughtIronIngot, TFCItems.leadIngot, TFCItems.nickelIngot, TFCItems.pigIronIngot, TFCItems.platinumIngot, TFCItems.redSteelIngot, TFCItems.roseGoldIngot, TFCItems.silverIngot, TFCItems.steelIngot, TFCItems.sterlingSilverIngot, TFCItems.tinIngot, TFCItems.zincIngot, TFCItems.unknownIngot };








  public boolean canUpdate() {
    return false;
  }


  public TEIngotPile() {
    this.storage = new ItemStack[1];
    this.type = "Copper";
  }

  public static Item[] getIngots() {
    return (Item[])INGOTS.clone();
  }


  public void setType(String i) {
    this.type = i;
  }



  @SideOnly(Side.CLIENT)
  public AxisAlignedBB getRenderBoundingBox() {
    return AxisAlignedBB.func_72330_a(this.field_145851_c, this.field_145848_d, this.field_145849_e, (this.field_145851_c + 1), (this.field_145848_d + 1), (this.field_145849_e + 1));
  }


  public int getStack() {
    return (this.storage[0]).field_77994_a;
  }


  public String getType() {
    return this.type;
  }


  public void addContents(int index, ItemStack is) {
    if (this.storage[index] == null)
      this.storage[index] = is;
    updateNeighbours();
  }


  public void clearContents() {
    this.storage[0] = null;
    updateNeighbours();
  }



  public void func_70305_f() {}



  public boolean contentsMatch(int index, ItemStack is) {
    if (this.storage[index] != null)
    {
      if ((this.storage[index]).field_77994_a == 0) {
        return true;
      }
    }
    return (this.storage[index].func_77973_b() == is.func_77973_b() && this.storage[index].func_77973_b() == is.func_77973_b() && (this.storage[index]).field_77994_a + 1 <=
      func_70297_j_());
  }



  public ItemStack func_70298_a(int i, int j) {
    if (this.storage[i] != null) {

      if ((this.storage[i]).field_77994_a <= j) {

        ItemStack itemstack = this.storage[i];
        this.storage[i] = null;
        updateNeighbours();
        return itemstack;
      }
      ItemStack itemstack1 = this.storage[i].func_77979_a(j);
      if ((this.storage[i]).field_77994_a == 0)
        this.storage[i] = null;
      updateNeighbours();
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
        this.storage[i] = null;
      }
    }
    updateNeighbours();
  }



  public int func_70297_j_() {
    return 64;
  }



  public String func_145825_b() {
    return "Ingot Pile";
  }



  public int func_70302_i_() {
    return this.storage.length;
  }



  public ItemStack func_70301_a(int i) {
    return this.storage[i];
  }



  public ItemStack func_70304_b(int var1) {
    return null;
  }


  public void injectContents(int index, int count) {
    if (this.storage[index] != null)
    {
      if ((this.storage[index]).field_77994_a > 0) {

        this.storage[index] = new ItemStack(this.storage[index].func_77973_b(), (this.storage[index]).field_77994_a + count, this.storage[index].func_77960_j());
        this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
      }
    }
    updateNeighbours();
  }



  public boolean func_70300_a(EntityPlayer entityplayer) {
    return false;
  }




  public void func_70295_k_() {}



  public void func_70299_a(int i, ItemStack itemstack) {
    this.storage[i] = itemstack;
    if (itemstack != null && itemstack.field_77994_a > func_70297_j_()) {
      itemstack.field_77994_a = func_70297_j_();
    }
  }

  public void updateNeighbours() {
    if (this.field_145850_b.func_72899_e(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e) && !this.field_145850_b.func_147437_c(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e)) {
      this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e).func_149695_a(this.field_145850_b, this.field_145851_c, this.field_145848_d + 1, this.field_145849_e, TFCBlocks.ingotPile);
    }
  }


  public boolean func_145818_k_() {
    return false;
  }



  public boolean func_94041_b(int i, ItemStack itemstack) {
    return false;
  }



  public void func_145839_a(NBTTagCompound nbttagcompound) {
    super.func_145839_a(nbttagcompound);

    this.type = nbttagcompound.func_74779_i("type");
    NBTTagList nbttaglist = nbttagcompound.func_150295_c("Items", 10);
    this.storage = new ItemStack[func_70302_i_()];
    for (int i = 0; i < nbttaglist.func_74745_c(); i++) {

      NBTTagCompound nbttagcompound1 = nbttaglist.func_150305_b(i);
      byte byte0 = nbttagcompound1.func_74771_c("Slot");
      if (byte0 >= 0 && byte0 < this.storage.length) {
        this.storage[byte0] = ItemStack.func_77949_a(nbttagcompound1);
      }
    }
  }


  public void func_145841_b(NBTTagCompound nbttagcompound) {
    super.func_145841_b(nbttagcompound);
    nbttagcompound.func_74778_a("type", this.type);
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
  }



  public void handleInitPacket(NBTTagCompound nbt) {
    this.type = nbt.func_74779_i("type");
    this.storage[0] = ItemStack.func_77949_a(nbt);
    updateNeighbours();
    this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
  }



  public void handleDataPacket(NBTTagCompound nbt) {}



  public void createDataNBT(NBTTagCompound nbt) {}


  public void createInitNBT(NBTTagCompound nbt) {
    if (this.type != null)
      nbt.func_74778_a("type", this.type);
    if (this.storage[0] != null) {

      ItemStack is = this.storage[0].func_77946_l();
      is.field_77990_d = null;
      is.func_77955_b(nbt);
    }
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\TileEntities\TEIngotPile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */