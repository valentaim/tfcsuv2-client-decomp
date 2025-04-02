package com.bioxx.tfc.Items;

import com.bioxx.tfc.Core.TFCTabs;
import com.bioxx.tfc.TileEntities.TELogPile;
import com.bioxx.tfc.api.Constant.Global;
import com.bioxx.tfc.api.Enums.EnumSize;
import com.bioxx.tfc.api.Enums.EnumWeight;
import com.bioxx.tfc.api.TFCBlocks;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;





























































public class ItemLogs
  extends ItemTerra
{
  private IIcon[] icons;

  public void func_150895_a(Item par1, CreativeTabs par2CreativeTabs, List<ItemStack> list) {
    for (int i = 0; i < Global.WOOD_ALL.length; i++)
      list.add(new ItemStack(this, 1, i));
  }

  private boolean createPile(ItemStack itemstack, EntityPlayer entityplayer, World world, int x, int y, int z, int side, int l) {
    TELogPile te = null;
    if (world.func_147437_c(x, y, z) && isValid(world, x, y, z)) {
      world.func_147465_d(x, y, z, TFCBlocks.logPile, l, 3);
      te = (TELogPile)world.func_147438_o(x, y, z);
    } else {
      return false;
    }
    if (te != null) {
      te.storage[0] = new ItemStack(this, 1, itemstack.func_77960_j());
      if (entityplayer.field_71075_bZ.field_75098_d) {
        te.storage[0] = new ItemStack(this, 4, itemstack.func_77960_j());
        te.storage[1] = new ItemStack(this, 4, itemstack.func_77960_j());
        te.storage[2] = new ItemStack(this, 4, itemstack.func_77960_j());
        te.storage[3] = new ItemStack(this, 4, itemstack.func_77960_j());
      }
    }
    return true;
  }

  public ItemLogs() {
    this.icons = new IIcon[Global.WOOD_ALL.length]; func_77656_e(0); func_77627_a(true);
    func_77637_a(TFCTabs.TFC_MATERIALS);
    this.metaNames = (String[])Global.WOOD_ALL.clone();
    setWeight(EnumWeight.MEDIUM);
    setSize(EnumSize.MEDIUM); } public void func_94581_a(IIconRegister registerer) { for (int i = 0; i < Global.WOOD_ALL.length; i++) {
      this.icons[i] = registerer.func_94245_a("terrafirmacraft:wood/" + Global.WOOD_ALL[i] + " Log");
    } }





  public boolean func_77648_a(ItemStack itemstack, EntityPlayer entityplayer, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
    if (!world.field_72995_K) {

      if (entityplayer.func_70093_af() && (world.func_147439_a(x, y, z) != TFCBlocks.logPile || (side != 1 && side != 0))) {

        int dir = MathHelper.func_76128_c((entityplayer.field_70177_z * 4.0F / 360.0F) + 0.5D) & 0x3;
        if (side == 0) {
          y--;
        } else if (side == 1) {
          y++;
        } else if (side == 2) {
          z--;
        } else if (side == 3) {
          z++;
        } else if (side == 4) {
          x--;
        } else if (side == 5) {
          x++;
        }  if (world.func_147472_a(TFCBlocks.logPile, x, y, z, false, side, (Entity)entityplayer, itemstack) &&
          createPile(itemstack, entityplayer, world, x, y, z, side, dir)) {

          itemstack.field_77994_a--;
          playSound(world, x, y, z);
        }
        return true;
      }
      if (world.func_147439_a(x, y, z) == TFCBlocks.logPile) {

        TELogPile te = (TELogPile)world.func_147438_o(x, y, z);
        if (te != null)
        {
          if (te.storage[0] != null && te.contentsMatch(0, itemstack)) {
            te.injectContents(0, 1);
          } else if (te.storage[0] == null) {
            te.addContents(0, new ItemStack(this, 1, itemstack.func_77960_j()));
          } else if (te.storage[1] != null && te.contentsMatch(1, itemstack)) {
            te.injectContents(1, 1);
          } else if (te.storage[1] == null) {
            te.addContents(1, new ItemStack(this, 1, itemstack.func_77960_j()));
          } else if (te.storage[2] != null && te.contentsMatch(2, itemstack)) {
            te.injectContents(2, 1);
          } else if (te.storage[2] == null) {
            te.addContents(2, new ItemStack(this, 1, itemstack.func_77960_j()));
          } else if (te.storage[3] != null && te.contentsMatch(3, itemstack)) {
            te.injectContents(3, 1);
          } else if (te.storage[3] == null) {
            te.addContents(3, new ItemStack(this, 1, itemstack.func_77960_j()));
          } else {

            int dir = MathHelper.func_76128_c((entityplayer.field_70177_z * 4.0F / 360.0F) + 0.5D) & 0x3;
            if (side == 0) {
              y--;
            } else if (side == 1) {
              y++;
            } else if (side == 2) {
              z--;
            } else if (side == 3) {
              z++;
            } else if (side == 4) {
              x--;
            } else if (side == 5) {
              x++;
            }  if (!createPile(itemstack, entityplayer, world, x, y, z, side, dir)) {
              return true;
            }
          }

          playSound(world, x, y, z);
          itemstack.field_77994_a--;
          return true;
        }

      }
      else {

        int m = itemstack.func_77960_j();
        Block block = (m > 15) ? TFCBlocks.woodVert2 : TFCBlocks.woodVert;

        if (side == 0 && block.func_149742_c(world, x, y - 1, z) && world.func_147472_a(TFCBlocks.woodVert, x, y - 1, z, false, side, null, itemstack)) {

          world.func_147465_d(x, y - 1, z, block, m, 2);
          itemstack.field_77994_a--;
          playSound(world, x, y, z);
        }
        else if (side == 1 && block.func_149742_c(world, x, y + 1, z) && world.func_147472_a(TFCBlocks.woodVert, x, y + 1, z, false, side, null, itemstack)) {

          world.func_147465_d(x, y + 1, z, block, m, 2);
          itemstack.field_77994_a--;
          playSound(world, x, y, z);
        }
        else if (side == 2 && block.func_149742_c(world, x, y, z - 1) && world.func_147472_a(TFCBlocks.woodVert, x, y, z - 1, false, side, null, itemstack)) {

          setSide(world, itemstack, m, side, x, y, z - 1);
        }
        else if (side == 3 && block.func_149742_c(world, x, y, z + 1) && world.func_147472_a(TFCBlocks.woodVert, x, y, z + 1, false, side, null, itemstack)) {

          setSide(world, itemstack, m, side, x, y, z + 1);
        }
        else if (side == 4 && block.func_149742_c(world, x - 1, y, z) && world.func_147472_a(TFCBlocks.woodVert, x - 1, y, z, false, side, null, itemstack)) {

          setSide(world, itemstack, m, side, x - 1, y, z);
        }
        else if (side == 5 && block.func_149742_c(world, x + 1, y, z) && world.func_147472_a(TFCBlocks.woodVert, x + 1, y, z, false, side, null, itemstack)) {

          setSide(world, itemstack, m, side, x + 1, y, z);
        }
        return true;
      }
    }
    return false;
  }
  public boolean isValid(World world, int i, int j, int k) { if (world.isSideSolid(i, j - 1, k, ForgeDirection.UP)) { TileEntity te = world.func_147438_o(i, j - 1, k); if (te instanceof TELogPile) { TELogPile lp = (TELogPile)te; if (lp.storage[0] == null || (lp.storage[0]).field_77994_a < 4)
          return false;  if (lp.storage[1] == null || (lp.storage[1]).field_77994_a < 4)
          return false;  if (lp.storage[2] == null || (lp.storage[2]).field_77994_a < 4)
          return false;  if (lp.storage[3] == null || (lp.storage[3]).field_77994_a < 4)
          return false;  }  return true; }  return false; } public IIcon func_77617_a(int meta) { if (meta < this.icons.length)
      return this.icons[meta];  return this.icons[0]; } public void setSide(World world, ItemStack itemstack, int m, int side, int x, int y, int z) { int meta = m % 8;
    Block log = TFCBlocks.woodHoriz;
    switch (m / 8) {
      case 1:
        log = TFCBlocks.woodHoriz2;
        break;
      case 2:
        log = TFCBlocks.woodHoriz3;
        break;
    }




    if (side == 2 || side == 3) {
      world.func_147465_d(x, y, z, log, meta, 2);
      itemstack.field_77994_a--;
      playSound(world, x, y, z);
    }
    else if (side == 4 || side == 5) {
      world.func_147465_d(x, y, z, log, meta | 0x8, 2);
      itemstack.field_77994_a--;
      playSound(world, x, y, z);
    }  }



  private void playSound(World world, int x, int y, int z) {
    world.func_72908_a(x + 0.5D, y + 0.5D, z + 0.5D, TFCBlocks.logNatural.field_149762_H.func_150496_b(), (TFCBlocks.logNatural.field_149762_H.func_150497_c() + 1.0F) / 2.0F, TFCBlocks.logNatural.field_149762_H.func_150494_d() * 0.8F);
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Items\ItemLogs.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */