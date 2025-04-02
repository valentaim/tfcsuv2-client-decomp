package com.bioxx.tfc.TileEntities;

import com.bioxx.tfc.Blocks.Flora.BlockFruitLeaves;
import com.bioxx.tfc.Blocks.Flora.BlockFruitWood;
import com.bioxx.tfc.Core.TFC_Climate;
import com.bioxx.tfc.Core.TFC_Time;
import com.bioxx.tfc.Food.FloraIndex;
import com.bioxx.tfc.Food.FloraManager;
import com.bioxx.tfc.api.TFCBlocks;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TEFruitTreeWood
  extends TileEntity
  implements IInventory {
  public boolean isTrunk;
  public int height;
  public long birthTimeWood;
  public long birthTimeLeaves;
  private static final long LEAF_GROWTH_RATE = 20L;
  private static final long TRUNK_GROW_TIME = (long)(1.5F * TFC_Time.daysInMonth);
  private static final long BRANCH_GROW_TIME = TFC_Time.daysInMonth;

  private static final long TEMP_TIMER_RATE = 10L;
  float temp;
  float temp_timer = 0.0F;



  public TEFruitTreeWood() {
    this.height = 0;
    this.isTrunk = false;
    this.birthTimeWood = 0L;
    this.birthTimeLeaves = 0L;
  }


  public void initBirth() {
    this.birthTimeWood = TFC_Time.getTotalDays();
    this.birthTimeLeaves = TFC_Time.getTotalDays();
  }


  public void setBirthWood(long t) {
    this.birthTimeWood = t;
  }


  public void increaseBirthWood(long t) {
    this.birthTimeWood += t;
  }


  public void setBirthLeaves(long t) {
    this.birthTimeLeaves = t;
  }

  public void increaseBirthLeaves(long t) {
    this.birthTimeLeaves += t;
  }


  public void setTrunk(boolean b) {
    this.isTrunk = b;
  }


  public void setHeight(int h) {
    this.height = h;
  }


  public void setupBirth(boolean isTrunk, int h, long woodBirth, long leafBirth) {
    setTrunk(isTrunk);
    setHeight(h);
    initBirth();
    setBirthWood(woodBirth);
    setBirthLeaves(leafBirth);
  }



  public void func_145845_h() {
    if (!this.field_145850_b.field_72995_K) {

      FloraManager manager = FloraManager.getInstance();
      FloraIndex fi = manager.findMatchingIndex(BlockFruitWood.getType(this.field_145850_b.func_72805_g(this.field_145851_c, this.field_145848_d, this.field_145849_e)));

      int meta = this.field_145850_b.func_72805_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);

      this.temp_timer--;
      if (this.temp_timer <= 0.0F) {
        this.temp = TFC_Climate.getHeightAdjustedTemp(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e);
        this.temp_timer = 10.0F;
      }

      int month = TFC_Time.getSeasonAdjustedMonth(this.field_145849_e);
      if (month < 9 && fi != null && this.temp >= fi.minTemp && this.temp < fi.maxTemp) {

        int t = 1;
        if (month < 3) {
          t = 2;
        }

        if (this.birthTimeWood + TRUNK_GROW_TIME < TFC_Time.getTotalDays() && this.height < 3 && this.isTrunk && this.field_145850_b.field_73012_v.nextInt(16 / t) == 0 && (this.field_145850_b
          .func_147437_c(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e) || this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e) == TFCBlocks.fruitTreeLeaves || this.field_145850_b
          .func_147439_a(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e) == TFCBlocks.fruitTreeLeaves2)) {

          this.field_145850_b.func_147465_d(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e, TFCBlocks.fruitTreeWood, meta, 2);
          if (this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e) instanceof TEFruitTreeWood)
          {
            TEFruitTreeWood trunkTE = (TEFruitTreeWood)this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e);
            trunkTE.setupBirth(true, this.height + 1, this.birthTimeWood + TRUNK_GROW_TIME, this.birthTimeLeaves);

            increaseBirthWood(TRUNK_GROW_TIME);
            this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
          }

        }
        else if (this.birthTimeWood + BRANCH_GROW_TIME < TFC_Time.getTotalDays() && this.height == 2 && this.isTrunk && this.field_145850_b.field_73012_v.nextInt(16 / t) == 0 && this.field_145850_b
          .func_147439_a(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e) == TFCBlocks.fruitTreeWood) {

          int r = this.field_145850_b.field_73012_v.nextInt(4);
          if (r == 0 && this.field_145850_b.func_72899_e(this.field_145851_c + 1, this.field_145848_d, this.field_145849_e) && (this.field_145850_b.func_147437_c(this.field_145851_c + 1, this.field_145848_d, this.field_145849_e) || this.field_145850_b
            .func_147439_a(this.field_145851_c + 1, this.field_145848_d, this.field_145849_e) == TFCBlocks.fruitTreeLeaves || this.field_145850_b.func_147439_a(this.field_145851_c + 1, this.field_145848_d, this.field_145849_e) == TFCBlocks.fruitTreeLeaves2)) {

            this.field_145850_b.func_147465_d(this.field_145851_c + 1, this.field_145848_d, this.field_145849_e, TFCBlocks.fruitTreeWood, meta, 2);
            if (this.field_145850_b.func_147438_o(this.field_145851_c + 1, this.field_145848_d, this.field_145849_e) instanceof TEFruitTreeWood)
            {
              TEFruitTreeWood branchTE = (TEFruitTreeWood)this.field_145850_b.func_147438_o(this.field_145851_c + 1, this.field_145848_d, this.field_145849_e);
              branchTE.setupBirth(false, this.height, this.birthTimeWood + BRANCH_GROW_TIME, this.birthTimeLeaves);
            }

          } else if (r == 1 && this.field_145850_b.func_72899_e(this.field_145851_c, this.field_145848_d, this.field_145849_e - 1) && (this.field_145850_b.func_147437_c(this.field_145851_c, this.field_145848_d, this.field_145849_e - 1) || this.field_145850_b
            .func_147439_a(this.field_145851_c, this.field_145848_d, this.field_145849_e - 1) == TFCBlocks.fruitTreeLeaves || this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d, this.field_145849_e - 1) == TFCBlocks.fruitTreeLeaves2)) {

            this.field_145850_b.func_147465_d(this.field_145851_c, this.field_145848_d, this.field_145849_e - 1, TFCBlocks.fruitTreeWood, meta, 2);
            if (this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d, this.field_145849_e - 1) instanceof TEFruitTreeWood)
            {
              TEFruitTreeWood branchTE = (TEFruitTreeWood)this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d, this.field_145849_e - 1);
              branchTE.setupBirth(false, this.height, this.birthTimeWood + BRANCH_GROW_TIME, this.birthTimeLeaves);
            }

          } else if (r == 2 && this.field_145850_b.func_72899_e(this.field_145851_c - 1, this.field_145848_d, this.field_145849_e) && (this.field_145850_b.func_147437_c(this.field_145851_c - 1, this.field_145848_d, this.field_145849_e) || this.field_145850_b
            .func_147439_a(this.field_145851_c - 1, this.field_145848_d, this.field_145849_e) == TFCBlocks.fruitTreeLeaves || this.field_145850_b.func_147439_a(this.field_145851_c - 1, this.field_145848_d, this.field_145849_e) == TFCBlocks.fruitTreeLeaves2)) {

            this.field_145850_b.func_147465_d(this.field_145851_c - 1, this.field_145848_d, this.field_145849_e, TFCBlocks.fruitTreeWood, meta, 2);
            if (this.field_145850_b.func_147438_o(this.field_145851_c - 1, this.field_145848_d, this.field_145849_e) instanceof TEFruitTreeWood)
            {
              TEFruitTreeWood branchTE = (TEFruitTreeWood)this.field_145850_b.func_147438_o(this.field_145851_c - 1, this.field_145848_d, this.field_145849_e);
              branchTE.setupBirth(false, this.height, this.birthTimeWood + BRANCH_GROW_TIME, this.birthTimeLeaves);
            }

          } else if (r == 3 && this.field_145850_b.func_72899_e(this.field_145851_c, this.field_145848_d, this.field_145849_e + 1) && (this.field_145850_b.func_147437_c(this.field_145851_c, this.field_145848_d, this.field_145849_e + 1) || this.field_145850_b
            .func_147439_a(this.field_145851_c, this.field_145848_d, this.field_145849_e + 1) == TFCBlocks.fruitTreeLeaves || this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d, this.field_145849_e + 1) == TFCBlocks.fruitTreeLeaves2)) {

            this.field_145850_b.func_147465_d(this.field_145851_c, this.field_145848_d, this.field_145849_e + 1, TFCBlocks.fruitTreeWood, meta, 2);
            if (this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d, this.field_145849_e + 1) instanceof TEFruitTreeWood) {

              TEFruitTreeWood branchTE = (TEFruitTreeWood)this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d, this.field_145849_e + 1);
              branchTE.setupBirth(false, this.height, this.birthTimeWood + BRANCH_GROW_TIME, this.birthTimeLeaves);
            }
          }

          increaseBirthWood(BRANCH_GROW_TIME);
          this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
        }

        if (this.birthTimeLeaves + 2L < TFC_Time.getTotalDays() && this.field_145850_b.field_73012_v.nextInt(20) == 0 && this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d + 2, this.field_145849_e) != TFCBlocks.fruitTreeWood) {

          int m = meta & 0x7;
          Block bid = (meta < 8) ? TFCBlocks.fruitTreeLeaves : TFCBlocks.fruitTreeLeaves2;

          if (checkLeaves(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e)) {

            this.field_145850_b.func_147465_d(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e, bid, m, 2);
            this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e);
          }
          else if (checkLeaves(this.field_145851_c + 1, this.field_145848_d, this.field_145849_e)) {

            this.field_145850_b.func_147465_d(this.field_145851_c + 1, this.field_145848_d, this.field_145849_e, bid, m, 2);
            this.field_145850_b.func_147471_g(this.field_145851_c + 1, this.field_145848_d, this.field_145849_e);
          }
          else if (checkLeaves(this.field_145851_c - 1, this.field_145848_d, this.field_145849_e)) {

            this.field_145850_b.func_147465_d(this.field_145851_c - 1, this.field_145848_d, this.field_145849_e, bid, m, 2);
            this.field_145850_b.func_147471_g(this.field_145851_c - 1, this.field_145848_d, this.field_145849_e);
          }
          else if (checkLeaves(this.field_145851_c, this.field_145848_d, this.field_145849_e + 1)) {

            this.field_145850_b.func_147465_d(this.field_145851_c, this.field_145848_d, this.field_145849_e + 1, bid, m, 2);
            this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e + 1);
          }
          else if (checkLeaves(this.field_145851_c, this.field_145848_d, this.field_145849_e - 1)) {

            this.field_145850_b.func_147465_d(this.field_145851_c, this.field_145848_d, this.field_145849_e - 1, bid, m, 2);
            this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e - 1);
          }
          else if (checkLeaves(this.field_145851_c + 1, this.field_145848_d, this.field_145849_e - 1)) {

            this.field_145850_b.func_147465_d(this.field_145851_c + 1, this.field_145848_d, this.field_145849_e - 1, bid, m, 2);
            this.field_145850_b.func_147471_g(this.field_145851_c + 1, this.field_145848_d, this.field_145849_e - 1);
          }
          else if (checkLeaves(this.field_145851_c + 1, this.field_145848_d, this.field_145849_e + 1)) {

            this.field_145850_b.func_147465_d(this.field_145851_c + 1, this.field_145848_d, this.field_145849_e + 1, bid, m, 2);
            this.field_145850_b.func_147471_g(this.field_145851_c + 1, this.field_145848_d, this.field_145849_e + 1);
          }
          else if (checkLeaves(this.field_145851_c - 1, this.field_145848_d, this.field_145849_e - 1)) {

            this.field_145850_b.func_147465_d(this.field_145851_c - 1, this.field_145848_d, this.field_145849_e - 1, bid, m, 2);
            this.field_145850_b.func_147471_g(this.field_145851_c - 1, this.field_145848_d, this.field_145849_e - 1);
          }
          else if (checkLeaves(this.field_145851_c - 1, this.field_145848_d, this.field_145849_e + 1)) {

            this.field_145850_b.func_147465_d(this.field_145851_c - 1, this.field_145848_d, this.field_145849_e + 1, bid, m, 2);
            this.field_145850_b.func_147471_g(this.field_145851_c - 1, this.field_145848_d, this.field_145849_e + 1);
          }

          increaseBirthLeaves(2L);
          this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
        }
      }
    }
  }


  private boolean checkLeaves(int xCoord, int yCoord, int zCoord) {
    return (this.field_145850_b.func_72899_e(xCoord, yCoord, zCoord) && this.field_145850_b.func_147437_c(xCoord, yCoord, zCoord) && this.field_145850_b
      .func_147437_c(xCoord, yCoord + 1, zCoord) && BlockFruitLeaves.canStay(this.field_145850_b, xCoord, yCoord, zCoord));
  }




  public void func_70305_f() {}



  public int func_70297_j_() {
    return 1;
  }



  public String func_145825_b() {
    return "Fruit Tree Wood";
  }



  public ItemStack func_70304_b(int var1) {
    return null;
  }



  public boolean func_70300_a(EntityPlayer entityplayer) {
    return false;
  }




  public void func_70295_k_() {}



  public void func_145839_a(NBTTagCompound nbttagcompound) {
    super.func_145839_a(nbttagcompound);
    this.birthTimeWood = nbttagcompound.func_74763_f("birthTime");
    this.birthTimeLeaves = nbttagcompound.func_74763_f("birthTimeLeaves");
    this.isTrunk = nbttagcompound.func_74767_n("isTrunk");
    this.height = nbttagcompound.func_74762_e("height");

    this.temp = nbttagcompound.func_74760_g("temp");
  }



  public void func_145841_b(NBTTagCompound nbttagcompound) {
    super.func_145841_b(nbttagcompound);
    nbttagcompound.func_74772_a("birthTime", this.birthTimeWood);
    nbttagcompound.func_74772_a("birthTimeLeaves", this.birthTimeLeaves);
    nbttagcompound.func_74757_a("isTrunk", this.isTrunk);
    nbttagcompound.func_74768_a("height", this.height);
    nbttagcompound.func_74768_a("height", this.height);

    nbttagcompound.func_74776_a("temp", this.temp);
  }



  public Packet func_145844_m() {
    NBTTagCompound nbt = new NBTTagCompound();
    func_145841_b(nbt);
    return (Packet)new S35PacketUpdateTileEntity(this.field_145851_c, this.field_145848_d, this.field_145849_e, 1, nbt);
  }



  public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
    func_145839_a(pkt.func_148857_g());
  }




  public int func_70302_i_() {
    return 0;
  }



  public ItemStack func_70301_a(int var1) {
    return null;
  }



  public ItemStack func_70298_a(int var1, int var2) {
    return null;
  }




  public void func_70299_a(int var1, ItemStack var2) {}



  public boolean func_145818_k_() {
    return false;
  }



  public boolean func_94041_b(int i, ItemStack itemstack) {
    return false;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\TileEntities\TEFruitTreeWood.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */