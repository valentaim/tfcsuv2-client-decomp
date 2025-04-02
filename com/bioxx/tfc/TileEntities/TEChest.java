package com.bioxx.tfc.TileEntities;

import com.bioxx.tfc.Containers.ContainerChestTFC;
import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.api.TFCBlocks;
import com.bioxx.tfc.api.TFC_ItemHeat;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Iterator;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryLargeChest;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;


public class TEChest
  extends TileEntityChest
  implements IInventory
{
  private ItemStack[] chestContents = new ItemStack[18];

  public int type;
  private int ticksSinceSync;
  public int cooldown = 5;

  private boolean typeSynced;
  public boolean isDoubleChest;
  private Boolean needCheck = null;


  private boolean hasTemp = false;


  public TEChest() {}


  public TEChest(int i, boolean isDouble) {
    this.type = i;
    this.isDoubleChest = isDouble;
  }



  public int func_70302_i_() {
    return 18;
  }



  public ItemStack func_70301_a(int par1) {
    return this.chestContents[par1];
  }



  public ItemStack func_70298_a(int par1, int par2) {
    if (this.chestContents[par1] != null) {


      if ((this.chestContents[par1]).field_77994_a <= par2) {

        ItemStack itemStack = this.chestContents[par1];
        this.chestContents[par1] = null;
        func_70296_d();
        return itemStack;
      }


      ItemStack var3 = this.chestContents[par1].func_77979_a(par2);
      if ((this.chestContents[par1]).field_77994_a == 0)
        this.chestContents[par1] = null;
      func_70296_d();
      return var3;
    }


    return null;
  }



  public ItemStack func_70304_b(int par1) {
    if (this.chestContents[par1] != null) {

      ItemStack var2 = this.chestContents[par1];
      this.chestContents[par1] = null;
      return var2;
    }

    return null;
  }



  public void func_70299_a(int par1, ItemStack par2ItemStack) {
    boolean check = true;
    if (this.chestContents[par1] != null && par2ItemStack != null &&
      this.chestContents[par1].func_77973_b().equals(par2ItemStack.func_77973_b()) && TFC_ItemHeat.hasTemp(this.chestContents[par1]) == TFC_ItemHeat.hasTemp(par2ItemStack)) check = false;
    this.chestContents[par1] = par2ItemStack;
    if (par2ItemStack != null && par2ItemStack.field_77994_a > func_70297_j_())
      par2ItemStack.field_77994_a = func_70297_j_();
    func_70296_d();
    if (check)
    {
      checkItems();
    }
  }




  public String func_145825_b() {
    return "container.chest";
  }



  public void func_145839_a(NBTTagCompound nbt) {
    super.func_145839_a(nbt);
    NBTTagList var2 = nbt.func_150295_c("Items", 10);
    this.chestContents = new ItemStack[func_70302_i_()];
    this.type = nbt.func_74762_e("woodtype");
    for (int var3 = 0; var3 < var2.func_74745_c(); var3++) {

      NBTTagCompound var4 = var2.func_150305_b(var3);
      int var5 = var4.func_74771_c("Slot") & 0xFF;
      if (var5 >= 0 && var5 < this.chestContents.length) {
        this.chestContents[var5] = ItemStack.func_77949_a(var4);
      }
    }
  }


  public void func_145841_b(NBTTagCompound nbt) {
    super.func_145841_b(nbt);
    NBTTagList var2 = new NBTTagList();

    for (int var3 = 0; var3 < this.chestContents.length; var3++) {

      if (this.chestContents[var3] != null) {

        NBTTagCompound var4 = new NBTTagCompound();
        var4.func_74774_a("Slot", (byte)var3);
        this.chestContents[var3].func_77955_b(var4);
        var2.func_74742_a((NBTBase)var4);
      }
    }

    nbt.func_74782_a("Items", (NBTBase)var2);
    nbt.func_74768_a("woodtype", this.type);
  }



  public Packet func_145844_m() {
    NBTTagCompound nbt = new NBTTagCompound();
    nbt.func_74768_a("woodtype", this.type);
    return (Packet)new S35PacketUpdateTileEntity(this.field_145851_c, this.field_145848_d, this.field_145849_e, 1, nbt);
  }



  public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
    this.type = pkt.func_148857_g().func_74762_e("woodtype");

    this.field_145984_a = false;
    this.typeSynced = true;
    this.cooldown = 0;
  }



  public int func_70297_j_() {
    return 64;
  }



  public boolean func_70300_a(EntityPlayer par1EntityPlayer) {
    return (this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d, this.field_145849_e) != this) ? false : ((par1EntityPlayer.func_70092_e(this.field_145851_c + 0.5D, this.field_145848_d + 0.5D, this.field_145849_e + 0.5D) <= 64.0D));
  }


  public boolean checkType(IBlockAccess access, int x, int y, int z) {
    TileEntity te = access.func_147438_o(x, y, z);
    if (te instanceof TEChest) {

      TEChest chest = (TEChest)access.func_147438_o(x, y, z);
      if (chest.type == this.type && this.cooldown == 0 && chest.cooldown == 0)
        return true;
    }
    return false;
  }



  public void func_145979_i() {
    if (!this.field_145984_a) {

      this.field_145984_a = true;
      this.field_145992_i = null;
      this.field_145990_j = null;
      this.field_145991_k = null;
      this.field_145988_l = null;

      if (isChest(this.field_145851_c - 1, this.field_145848_d, this.field_145849_e)) {
        this.field_145991_k = (TEChest)this.field_145850_b.func_147438_o(this.field_145851_c - 1, this.field_145848_d, this.field_145849_e);
      }
      if (isChest(this.field_145851_c + 1, this.field_145848_d, this.field_145849_e)) {
        this.field_145990_j = (TEChest)this.field_145850_b.func_147438_o(this.field_145851_c + 1, this.field_145848_d, this.field_145849_e);
      }
      if (isChest(this.field_145851_c, this.field_145848_d, this.field_145849_e - 1)) {
        this.field_145992_i = (TEChest)this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d, this.field_145849_e - 1);
      }
      if (isChest(this.field_145851_c, this.field_145848_d, this.field_145849_e + 1)) {
        this.field_145988_l = (TEChest)this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d, this.field_145849_e + 1);
      }




      if (this.field_145992_i != null)
      {
        ((TEChest)this.field_145992_i).updateSide(this, 0);
      }

      if (this.field_145988_l != null)
      {
        ((TEChest)this.field_145988_l).updateSide(this, 2);
      }

      if (this.field_145990_j != null)
      {
        ((TEChest)this.field_145990_j).updateSide(this, 1);
      }

      if (this.field_145991_k != null)
      {
        ((TEChest)this.field_145991_k).updateSide(this, 3);
      }
    }
  }


  private boolean isChest(int x, int y, int z) {
    return (this.field_145850_b.func_147439_a(x, y, z) == TFCBlocks.chest && checkType((IBlockAccess)this.field_145850_b, x, y, z));
  }


  private void updateSide(TileEntityChest teChest, int side) {
    if (teChest.func_145837_r()) {

      this.field_145984_a = false;
    }
    else if (this.field_145984_a) {

      switch (side) {

        case 0:
          if (this.field_145988_l != teChest)
          {
            this.field_145984_a = false;
          }
          break;

        case 1:
          if (this.field_145991_k != teChest)
          {
            this.field_145984_a = false;
          }
          break;

        case 2:
          if (this.field_145992_i != teChest)
          {
            this.field_145984_a = false;
          }
          break;

        case 3:
          if (this.field_145990_j != teChest)
          {
            this.field_145984_a = false;
          }
          break;
      }
    }
  }





  public void func_145845_h() {
    if (this.needCheck == null) { checkItems(); }
    else if (this.needCheck.booleanValue()) { TFC_Core.handleItemTicking(this, this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e); }
     if (this.hasTemp && this.field_145850_b.func_82737_E() % 600L == 0L) checkItems();
    if (this.needCheck.booleanValue() && this.field_145850_b.func_82737_E() % 1200L == 0L) checkItems();

    if (this.type == 0 && !this.typeSynced) {

      if (this.cooldown == 0)
      {
        this.field_145984_a = false;
        this.isDoubleChest = false;
        this.typeSynced = true;
      }
      else if (this.cooldown > 0)
      {
        this.cooldown--;
      }

    } else if (this.cooldown != 0) {

      this.cooldown = 0;
    }

    func_145979_i();
    this.ticksSinceSync++;

    if (!this.field_145850_b.field_72995_K && this.field_145987_o != 0 && (this.ticksSinceSync + this.field_145851_c + this.field_145848_d + this.field_145849_e) % 200 == 0) {

      this.field_145987_o = 0;
      float f = 5.0F;
      List<EntityPlayer> list = this.field_145850_b.func_72872_a(EntityPlayer.class, AxisAlignedBB.func_72330_a((this.field_145851_c - f), (this.field_145848_d - f), (this.field_145849_e - f), ((this.field_145851_c + 1) + f), ((this.field_145848_d + 1) + f), ((this.field_145849_e + 1) + f)));
      Iterator<EntityPlayer> iterator = list.iterator();

      while (iterator.hasNext()) {

        EntityPlayer entityplayer = iterator.next();

        if (entityplayer.field_71070_bA instanceof ContainerChestTFC) {

          IInventory iinventory = ((ContainerChestTFC)entityplayer.field_71070_bA).getLowerChestInventory();

          if (iinventory == this || (iinventory instanceof InventoryLargeChest && ((InventoryLargeChest)iinventory).func_90010_a(this)))
          {
            this.field_145987_o++;
          }
        }
      }
    }

    this.field_145986_n = this.field_145989_m;
    float var1 = 0.1F;


    if (this.field_145987_o > 0 && this.field_145989_m == 0.0F && this.field_145992_i == null && this.field_145991_k == null) {

      double var2 = this.field_145851_c + 0.5D;
      double var4 = this.field_145849_e + 0.5D;
      if (this.field_145988_l != null)
        var4 += 0.5D;
      if (this.field_145990_j != null)
        var2 += 0.5D;
      this.field_145850_b.func_72908_a(var2, this.field_145848_d + 0.5D, var4, "random.chestopen", 0.5F, this.field_145850_b.field_73012_v.nextFloat() * 0.1F + 0.9F);
    }

    if ((this.field_145987_o == 0 && this.field_145989_m > 0.0F) || (this.field_145987_o > 0 && this.field_145989_m < 1.0F)) {

      if (this.field_145987_o > 0) {
        this.field_145989_m += var1;
      } else {
        this.field_145989_m -= var1;
      }
      if (this.field_145989_m > 1.0F) {
        this.field_145989_m = 1.0F;
      }
      float var8 = this.field_145989_m;
      float var3 = 0.5F;
      if (this.field_145989_m < var3 && var8 >= var3 && this.field_145992_i == null && this.field_145991_k == null) {

        double var4 = this.field_145851_c + 0.5D;
        double var6 = this.field_145849_e + 0.5D;
        if (this.field_145988_l != null)
          var6 += 0.5D;
        if (this.field_145990_j != null)
          var4 += 0.5D;
        this.field_145850_b.func_72908_a(var4, this.field_145848_d + 0.5D, var6, "random.chestclosed", 0.5F, this.field_145850_b.field_73012_v.nextFloat() * 0.1F + 0.9F);
      }

      if (this.field_145989_m < 0.0F) {
        this.field_145989_m = 0.0F;
      }
    }
  }

  private void checkItems() {
    for (int i = 0; i < func_70302_i_(); i++) {
      ItemStack is = func_70301_a(i);
      if (is != null) {
        if (TFC_ItemHeat.hasTemp(is)) {
          this.needCheck = Boolean.valueOf(true);
          this.hasTemp = true;

          return;
        }
        Item item = is.func_77973_b();
        if (item instanceof com.bioxx.tfc.api.Interfaces.IFood || item instanceof com.bioxx.tfc.Food.ItemFoodTFC) {
          this.needCheck = Boolean.valueOf(true);

          return;
        }
      }
    }

    this.hasTemp = false;
    this.needCheck = Boolean.valueOf(false);
  }



  @SideOnly(Side.CLIENT)
  public AxisAlignedBB getRenderBoundingBox() {
    return AxisAlignedBB.func_72330_a((this.field_145851_c - 1), this.field_145848_d, (this.field_145849_e - 1), (this.field_145851_c + 2), (this.field_145848_d + 2), (this.field_145849_e + 2));
  }



  public void func_70295_k_() {
    this.field_145987_o++;
    this.field_145850_b.func_147452_c(this.field_145851_c, this.field_145848_d, this.field_145849_e, TFCBlocks.chest, 1, this.field_145987_o);
  }



  public void func_70305_f() {
    this.field_145987_o--;
    this.field_145850_b.func_147452_c(this.field_145851_c, this.field_145848_d, this.field_145849_e, TFCBlocks.chest, 1, this.field_145987_o);
  }



  public boolean func_145818_k_() {
    return false;
  }



  public boolean func_94041_b(int i, ItemStack itemstack) {
    return true;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\TileEntities\TEChest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */