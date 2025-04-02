package com.bioxx.tfc.Blocks.Flora;

import com.bioxx.tfc.Blocks.BlockTerraContainer;
import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.TileEntities.TEFruitTreeWood;
import com.bioxx.tfc.api.Constant.Global;
import com.bioxx.tfc.api.TFCBlocks;
import com.bioxx.tfc.api.TFCItems;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;



public class BlockFruitWood
  extends BlockTerraContainer
{
  private IIcon[] icons = new IIcon[Global.FRUIT_META_NAMES.length];


  public BlockFruitWood() {
    super(Material.field_151575_d);
    func_149676_a(0.3F, 0.0F, 0.3F, 0.7F, 1.0F, 0.7F);
  }


  private boolean checkOut(World world, int i, int j, int k, int l) {
    return (world.func_147439_a(i, j, k) == this && world.func_72805_g(i, j, k) == l);
  }



  public int func_149692_a(int j) {
    return j;
  }



  public IIcon func_149691_a(int i, int j) {
    return this.icons[j];
  }



  public void func_149651_a(IIconRegister registerer) {
    for (int i = 0; i < 9; i++) {
      this.icons[i] = registerer.func_94245_a("terrafirmacraft:wood/fruit trees/" + Global.FRUIT_META_NAMES[i] + " Wood");
    }
  }



  public void func_149636_a(World world, EntityPlayer entityplayer, int i, int j, int k, int l) {
    boolean isAxeorSaw = false;
    ItemStack equip = entityplayer.func_71045_bC();
    if (equip != null) {

      int[] equipIDs = OreDictionary.getOreIDs(equip);
      for (int id : equipIDs) {

        String name = OreDictionary.getOreName(id);
        if (name.startsWith("itemAxe") || name.startsWith("itemSaw")) {

          isAxeorSaw = true;
          break;
        }
      }
    }
    if (isAxeorSaw) {

      int x = i;
      int y = 0;
      int z = k;


      if (world.func_147439_a(i, j + 1, k) == this || world.func_147439_a(i, j - 1, k) == this) {


        boolean[][][] checkArray = new boolean[11][50][11];

        if (TFC_Core.isGrass(world.func_147439_a(i, j + y - 1, k)) || TFC_Core.isDirt(world.func_147439_a(i, j + y - 1, k))) {

          boolean reachedTop = false;
          while (!reachedTop)
          {
            if (world.func_147437_c(x, j + y + 1, z))
            {
              reachedTop = true;
            }
            scanLogs(world, i, j + y, k, l, checkArray, 6, y, 6);
            y++;
          }

        }
      } else if (world.func_147439_a(i + 1, j, k) == this || world
        .func_147439_a(i - 1, j, k) == this || world
        .func_147439_a(i, j, k + 1) == this || world
        .func_147439_a(i, j, k - 1) == this) {

        Random r = new Random();
        if (r.nextInt(100) > 50 && isAxeorSaw) {

          if (l < 8 && (world
            .func_147439_a(i + 1, j, k) == TFCBlocks.fruitTreeLeaves2 || world
            .func_147439_a(i - 1, j, k) == TFCBlocks.fruitTreeLeaves2 || world
            .func_147439_a(i, j, k + 1) == TFCBlocks.fruitTreeLeaves2 || world
            .func_147439_a(i, j, k - 1) == TFCBlocks.fruitTreeLeaves2 || world
            .func_147439_a(i, j + 1, k) == TFCBlocks.fruitTreeLeaves2 || world
            .func_147439_a(i, j - 1, k) == TFCBlocks.fruitTreeLeaves2))
          {
            l += 8;
          }
          func_149642_a(world, i, j, k, new ItemStack(TFCItems.fruitTreeSapling, 1, l));
        }
      }
    }
  }







  public Item func_149650_a(int i, Random random, int j) {
    return TFCItems.logs;
  }



  public void func_149695_a(World world, int i, int j, int k, Block block) {
    boolean check = false;
    for (int h = -1; h <= 1; h++) {

      for (int g = -1; g <= 1; g++) {

        for (int f = -1; f <= 1; f++) {

          if (world.func_147439_a(i + h, j + g, k + f) == this && world.func_72805_g(i + h, j + g, k + f) == world.func_72805_g(i, j, k))
            check = true;
        }
      }
    }
    if (!check) {
      world.func_147468_f(i, j, k);
    }
  }

  private void scanLogs(World world, int i, int j, int k, int l, boolean[][][] checkArray, int x, int y, int z) {
    if (y >= 0) {

      checkArray[x][y][z] = true;
      int offsetX = 0;
      int offsetY = 0;
      int offsetZ = 0;

      for (offsetY = 0; offsetY <= 1; offsetY++) {

        for (offsetX = -1; offsetX <= 1; offsetX++) {

          for (offsetZ = -1; offsetZ <= 1; offsetZ++) {

            if (x + offsetX < 11 && x + offsetX >= 0 && z + offsetZ < 11 && z + offsetZ >= 0 && y + offsetY < 50 && y + offsetY >= 0)
            {
              if (checkOut(world, i + offsetX, j + offsetY, k + offsetZ, l) && !checkArray[x + offsetX][y + offsetY][z + offsetZ])
                scanLogs(world, i + offsetX, j + offsetY, k + offsetZ, l, checkArray, x + offsetX, y + offsetY, z + offsetZ);
            }
          }
        }
      }
      world.func_147468_f(i, j, k);
    }
  }



  public int func_149645_b() {
    return TFCBlocks.woodFruitRenderId;
  }



  public boolean func_149662_c() {
    return false;
  }



  public boolean func_149686_d() {
    return false;
  }



  public boolean func_149646_a(IBlockAccess par1iBlockAccess, int par2, int par3, int par4, int par5) {
    return true;
  }



  public AxisAlignedBB func_149668_a(World world, int i, int j, int k) {
    if (world.func_147439_a(i, j - 1, k) == this || world.func_147439_a(i, j - 1, k).func_149662_c())
      return AxisAlignedBB.func_72330_a(i + 0.3D, j, k + 0.3D, i + 0.7D, (j + 1), k + 0.7D);
    return AxisAlignedBB.func_72330_a(i, j + 0.4D, k, (i + 1), j + 0.6D, (k + 1));
  }



  public AxisAlignedBB func_149633_g(World world, int i, int j, int k) {
    if (world.func_147439_a(i, j - 1, k) == this || world.func_147439_a(i, j - 1, k).func_149662_c())
      return AxisAlignedBB.func_72330_a(i + 0.3D, j, k + 0.3D, i + 0.7D, (j + 1), k + 0.7D);
    return AxisAlignedBB.func_72330_a(i, j + 0.4D, k, (i + 1), j + 0.6D, (k + 1));
  }






  public void func_149719_a(IBlockAccess world, int i, int j, int k) {
    if (world.func_147439_a(i, j - 1, k) == this || world.func_147439_a(i, j - 1, k).func_149662_c()) {
      func_149676_a(0.3F, 0.0F, 0.3F, 0.7F, 1.0F, 0.7F);
    } else {
      func_149676_a(0.0F, 0.4F, 0.0F, 1.0F, 0.6F, 1.0F);
    }
  }




































































  public void func_149674_a(World world, int i, int j, int k, Random rand) {}




































































  public static String getType(int meta) {
    switch (meta) {
      case 0:
        return Global.FRUIT_META_NAMES[0];
      case 1: return Global.FRUIT_META_NAMES[1];
      case 2: return Global.FRUIT_META_NAMES[2];
      case 3: return Global.FRUIT_META_NAMES[3];
      case 4: return Global.FRUIT_META_NAMES[4];
      case 5: return Global.FRUIT_META_NAMES[5];
      case 6: return Global.FRUIT_META_NAMES[6];
      case 7: return Global.FRUIT_META_NAMES[7];
      case 8: return Global.FRUIT_META_NAMES[8];
    }
    return "";
  }



  public TileEntity func_149915_a(World var1, int var2) {
    return (TileEntity)new TEFruitTreeWood();
  }



  public void func_149749_a(World world, int x, int y, int z, Block block, int metadata) {
    if (!world.field_72995_K && checkOut(world, x, y - 1, z, metadata) && world.func_147438_o(x, y - 1, z) != null)
      ((TEFruitTreeWood)world.func_147438_o(x, y - 1, z)).initBirth();
    super.func_149749_a(world, x, y, z, block, metadata);
  }



  public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
    return null;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Blocks\Flora\BlockFruitWood.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */