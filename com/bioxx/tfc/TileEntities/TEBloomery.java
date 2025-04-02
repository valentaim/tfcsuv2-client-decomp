package com.bioxx.tfc.TileEntities;

import com.bioxx.tfc.Blocks.Devices.BlockEarlyBloomery;
import com.bioxx.tfc.Core.TFC_Time;
import com.bioxx.tfc.Items.ItemOre;
import com.bioxx.tfc.api.Constant.Global;
import com.bioxx.tfc.api.Interfaces.ISmeltable;
import com.bioxx.tfc.api.TFCBlocks;
import com.bioxx.tfc.api.TFCItems;
import com.bioxx.tfc.api.TFCOptions;
import java.util.Iterator;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;



public class TEBloomery
  extends NetworkTileEntity
{
  public boolean isFlipped;
  public boolean bloomeryLit;
  private int validationCheck = 60;

  public int charcoalCount;

  public long fuelTimeLeft;

  public int oreCount;
  public int outCount;

  public TEBloomery() {
    this.isFlipped = false;
    this.bloomeryLit = false;

    this.charcoalCount = 0;
    this.oreCount = 0;
    this.outCount = 0;
  }


  public void swapFlipped() {
    if (this.isFlipped) { this.isFlipped = false; }
    else { this.isFlipped = true; }
     if (!this.field_145850_b.field_72995_K) {
      this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
    }
  }

  public boolean isStackValid(int i, int j, int k) {
    Block yNegBlock = this.field_145850_b.func_147439_a(i, j - 1, k);
    if (yNegBlock != TFCBlocks.molten && this.field_145850_b
      .func_147439_a(i, j - 1, k).func_149688_o() != Material.field_151576_e &&
      !this.field_145850_b.func_147439_a(i, j - 1, k).func_149721_r() && yNegBlock != TFCBlocks.charcoal)
    {

      return false;
    }
    return ((BlockEarlyBloomery)TFCBlocks.bloomery).checkStack(this.field_145850_b, this.field_145851_c, j, this.field_145849_e, this.field_145850_b.func_72805_g(this.field_145851_c, this.field_145848_d, this.field_145849_e) & 0x3);
  }


  public boolean addOreToFire(ItemStack is) {
    if (((ISmeltable)is.func_77973_b()).getMetalType(is) == Global.PIGIRON || ((ISmeltable)is.func_77973_b()).getMetalType(is) == Global.WROUGHTIRON) {

      this.outCount += ((ISmeltable)is.func_77973_b()).getMetalReturnAmount(is);
      return true;
    }
    return false;
  }


  public boolean canLight() {
    if (!this.field_145850_b.field_72995_K) {

      if (this.charcoalCount < this.oreCount || this.oreCount == 0) {
        return false;
      }

      int meta = this.field_145850_b.func_72805_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
      int[] direction = BlockEarlyBloomery.BLOOMERY_TO_STACK_MAP[getCharcoalDir(meta)];
      int x = this.field_145851_c + direction[0];
      int z = this.field_145849_e + direction[1];
      Block bid = this.field_145850_b.func_147439_a(x, this.field_145848_d, z);
      if (bid == TFCBlocks.charcoal && this.field_145850_b
        .func_72805_g(x, this.field_145848_d, z) >= 7 && !this.bloomeryLit) {

        this.bloomeryLit = true;
        this.fuelTimeLeft = (long)((float)TFC_Time.getTotalTicks() + 1000.0F * TFCOptions.bloomeryBurnTime);
        if ((meta & 0x4) == 0)
          this.field_145850_b.func_72921_c(this.field_145851_c, this.field_145848_d, this.field_145849_e, meta + 4, 3);
        return true;
      }
    }
    return false;
  }


  private int getCharcoalDir(int meta) {
    return meta & 0x3;
  }




  public void func_145845_h() {
    if (!this.field_145850_b.field_72995_K) {


      int count = this.charcoalCount + this.oreCount;

      int moltenCount = (count > 0 && count < 8) ? 1 : (count / 8);
      int validCount = 0;
      int maxCount = 0;


      int meta = this.field_145850_b.func_72805_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
      int[] direction = BlockEarlyBloomery.BLOOMERY_TO_STACK_MAP[getCharcoalDir(meta)];

      int x = this.field_145851_c + direction[0];
      int z = this.field_145849_e + direction[1];

      if (this.field_145850_b.func_72899_e(x, this.field_145848_d, z)) {

        if (this.bloomeryLit && TFC_Time.getTotalTicks() > this.fuelTimeLeft) {

          if (this.field_145850_b.func_147439_a(x, this.field_145848_d, z) == TFCBlocks.molten)
          {
            if (this.field_145850_b.func_147449_b(x, this.field_145848_d, z, TFCBlocks.bloom)) {

              this.bloomeryLit = false;






              this.oreCount = 0;
              this.charcoalCount = 0;
              ((TEBloom)this.field_145850_b.func_147438_o(x, this.field_145848_d, z)).setSize(this.outCount);
              this.outCount = 0;
            }
          }

          if ((meta & 0x4) != 0)
          {
            this.field_145850_b.func_72921_c(this.field_145851_c, this.field_145848_d, this.field_145849_e, meta & 0x3, 3);
          }
        }

        if (this.outCount < 0)
          this.outCount = 0;
        if (this.oreCount < 0)
          this.oreCount = 0;
        if (this.charcoalCount < 0) {
          this.charcoalCount = 0;
        }

        if (isStackValid(x, this.field_145848_d + 1, z)) {

          maxCount = 8;

          if (isStackValid(x, this.field_145848_d + 2, z)) {

            maxCount = 16;

            if (isStackValid(x, this.field_145848_d + 3, z))
            {
              maxCount = 24;
            }
          }
        }

        int moltenHeight = Math.max(count / 2 - 1, 0);

        for (int i = this.bloomeryLit ? 0 : 1, j = this.bloomeryLit ? (moltenHeight + 7) : moltenHeight; j > 0; i++, j -= 8) {

          Block bid = this.field_145850_b.func_147439_a(x, this.field_145848_d + i, z);

          if ((bid.isAir((IBlockAccess)this.field_145850_b, x, this.field_145848_d + i, z) || bid == TFCBlocks.molten || bid == TFCBlocks.charcoal) && this.field_145850_b
            .func_147439_a(x, this.field_145848_d - 1, z).func_149688_o() == Material.field_151576_e) {


            if (isStackValid(x, this.field_145848_d + i, z)) {
              validCount++;
            }
            if (i <= validCount) {

              int mMeta = this.field_145850_b.func_72805_g(this.field_145851_c, this.field_145848_d + i, this.field_145849_e);
              int m = (j > 7) ? 7 : j;
              if (this.bloomeryLit) {

                if ((bid == TFCBlocks.molten && (mMeta & 0x8) == 0) || bid
                  .isAir((IBlockAccess)this.field_145850_b, x, this.field_145848_d + i, z) || bid == TFCBlocks.charcoal)
                {

                  m += 8;
                  this.field_145850_b.func_147465_d(x, this.field_145848_d + i, z, TFCBlocks.molten, m, 2);

                }

              }
              else if (count > 0) {
                this.field_145850_b.func_147465_d(x, this.field_145848_d + i, z, TFCBlocks.molten, m, 2);
              } else {
                this.field_145850_b.func_147468_f(x, this.field_145848_d + i, z);
              }

            } else {

              this.field_145850_b.func_147468_f(x, this.field_145848_d + i, z);
            }
          }
        }

        if (!this.bloomeryLit && this.field_145850_b.func_147439_a(x, this.field_145848_d, z) == TFCBlocks.bloom) {

          if (isStackValid(x, this.field_145848_d + 3, z) &&
            isStackValid(x, this.field_145848_d + 2, z) &&
            isStackValid(x, this.field_145848_d + 1, z))
          {
            if (this.field_145850_b.func_147439_a(x, this.field_145848_d + 3, z) == TFCBlocks.molten) {
              this.field_145850_b.func_147468_f(x, this.field_145848_d + 3, z);
            }
          }
          if (isStackValid(x, this.field_145848_d + 2, z) &&
            isStackValid(x, this.field_145848_d + 1, z))
          {
            if (this.field_145850_b.func_147439_a(x, this.field_145848_d + 2, z) == TFCBlocks.molten) {
              this.field_145850_b.func_147468_f(x, this.field_145848_d + 2, z);
            }
          }
          if (isStackValid(x, this.field_145848_d + 1, z))
          {
            if (this.field_145850_b.func_147439_a(x, this.field_145848_d + 1, z) == TFCBlocks.molten) {
              this.field_145850_b.func_147468_f(x, this.field_145848_d + 1, z);
            }
          }
        }
        if (moltenCount == 0) {
          moltenCount = 1;
        }

        List<EntityItem> list = this.field_145850_b.func_72872_a(EntityItem.class, AxisAlignedBB.func_72330_a(x, this.field_145848_d, z, (x + 1), (this.field_145848_d + maxCount / 8) + 1.1D, (z + 1)));


        List<EntityItem> playerList = this.field_145850_b.func_72872_a(EntityPlayer.class, AxisAlignedBB.func_72330_a(x, this.field_145848_d, z, (x + 1), (this.field_145848_d + maxCount / 8) + 1.1D, (z + 1)));


        if (list != null && !list.isEmpty() && !this.bloomeryLit && (playerList == null || playerList.isEmpty()))
        {

          for (Iterator<EntityItem> iterator = list.iterator(); iterator.hasNext(); ) {

            EntityItem entity = iterator.next();
            if (entity.func_92059_d().func_77973_b() == TFCItems.coal && entity
              .func_92059_d().func_77960_j() == 1) {

              for (int c = 0; c < (entity.func_92059_d()).field_77994_a; c++) {

                if (this.charcoalCount + this.oreCount < 2 * maxCount && this.charcoalCount < maxCount) {

                  this.charcoalCount++;
                  (entity.func_92059_d()).field_77994_a--;
                }
              }
              if ((entity.func_92059_d()).field_77994_a == 0)
                entity.func_70106_y();
              continue;
            }
            if (entity.func_92059_d().func_77973_b() instanceof ItemOre && ((ItemOre)entity.func_92059_d().func_77973_b()).isSmeltable(entity.func_92059_d())) {

              int c = (entity.func_92059_d()).field_77994_a;
              while (c > 0) {

                if (this.charcoalCount + this.oreCount < 2 * maxCount && this.oreCount < maxCount && this.outCount < 1000)
                {
                  if (addOreToFire(new ItemStack(entity.func_92059_d().func_77973_b(), 1, entity.func_92059_d().func_77960_j()))) {

                    this.oreCount++;
                    c--;
                  }
                }
              }




              if (c == 0) {
                entity.func_70106_y(); continue;
              }
              (entity.func_92059_d()).field_77994_a = c; continue;
            }
            if (entity.func_92059_d().func_77973_b() instanceof ISmeltable && ((ISmeltable)entity
              .func_92059_d().func_77973_b()).isSmeltable(entity.func_92059_d())) {

              int c = (entity.func_92059_d()).field_77994_a;
              while (c > 0) {

                if (((ISmeltable)entity.func_92059_d().func_77973_b()).getMetalReturnAmount(entity.func_92059_d()) < 100 && this.oreCount < maxCount && this.outCount < 1000)
                {
                  if (addOreToFire(new ItemStack(entity.func_92059_d().func_77973_b(), 1, entity.func_92059_d().func_77960_j()))) {

                    this.oreCount++;
                    c--;
                  }
                }
              }





              if (c == 0) {
                entity.func_70106_y(); continue;
              }
              (entity.func_92059_d()).field_77994_a = c;
            }
          }
        }

        if (this.validationCheck <= 0) {

          if (((BlockEarlyBloomery)this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d, this.field_145849_e)).func_149718_j(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e)) {
            this.validationCheck = 600;
          } else {

            this.field_145850_b.func_147468_f(this.field_145851_c, this.field_145848_d, this.field_145849_e);
            this.field_145850_b.func_72838_d((Entity)new EntityItem(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e, new ItemStack(TFCBlocks.bloomery, 1)));
          }
        } else {

          this.validationCheck--;
        }
      }
    }
  }


  public void func_145841_b(NBTTagCompound nbttagcompound) {
    super.func_145841_b(nbttagcompound);
    nbttagcompound.func_74757_a("isFlipped", this.isFlipped);
    nbttagcompound.func_74772_a("fuelTimeLeft", this.fuelTimeLeft);
    nbttagcompound.func_74768_a("charcoalCount", this.charcoalCount);
    nbttagcompound.func_74768_a("outCount", this.outCount);
    nbttagcompound.func_74768_a("oreCount", this.oreCount);
    nbttagcompound.func_74757_a("isLit", this.bloomeryLit);
  }



  public void func_145839_a(NBTTagCompound nbttagcompound) {
    super.func_145839_a(nbttagcompound);
    this.isFlipped = nbttagcompound.func_74767_n("isFlipped");
    this.fuelTimeLeft = nbttagcompound.func_74763_f("fuelTimeLeft");
    this.charcoalCount = nbttagcompound.func_74762_e("charcoalCount");
    this.outCount = nbttagcompound.func_74762_e("outCount");
    this.oreCount = nbttagcompound.func_74762_e("oreCount");
    this.bloomeryLit = nbttagcompound.func_74767_n("isLit");
  }


  public void handleInitPacket(NBTTagCompound nbt) {
    this.isFlipped = nbt.func_74767_n("isFlipped");
    this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
  }




  public void handleDataPacket(NBTTagCompound nbt) {}




  public void createDataNBT(NBTTagCompound nbt) {}




  public void createInitNBT(NBTTagCompound nbt) {
    nbt.func_74757_a("isFlipped", this.isFlipped);
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\TileEntities\TEBloomery.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */