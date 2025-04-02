package com.bioxx.tfc.Blocks.Flora;

import com.bioxx.tfc.Blocks.BlockTerra;
import com.bioxx.tfc.api.Constant.Global;
import com.bioxx.tfc.api.TFCItems;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;

public class BlockLogNatural
  extends BlockTerra
{
  protected String[] woodNames;
  private int searchDist = 10;

  private static int damage;
  private static int logs;
  private boolean isStone;
  public IIcon[] sideIcons;
  public IIcon[] innerIcons;
  public IIcon[] rotatedSideIcons;

  public BlockLogNatural() {
    super(Material.field_151575_d);
    func_149675_a(true);
    this.woodNames = new String[16];
    System.arraycopy(Global.WOOD_ALL, 0, this.woodNames, 0, 16);
    this.sideIcons = new IIcon[this.woodNames.length];
    this.innerIcons = new IIcon[this.woodNames.length];
    this.rotatedSideIcons = new IIcon[this.woodNames.length];
  }



  public void func_149674_a(World world, int x, int y, int z, Random rand) {
    if (!world.field_72995_K)
    {
      if (!world.func_147439_a(x, y - 1, z).func_149662_c())
      {
        if (noLogsNearby(world, x + 1, y, z) && noLogsNearby(world, x - 1, y, z) &&
          noLogsNearby(world, x, y, z + 1) && noLogsNearby(world, x, y, z - 1) &&
          noLogsNearby(world, x + 1, y, z + 1) && noLogsNearby(world, x + 1, y, z - 1) &&
          noLogsNearby(world, x - 1, y, z + 1) && noLogsNearby(world, x - 1, y, z - 1) &&
          noLogsNearby(world, x + 1, y - 1, z) && noLogsNearby(world, x - 1, y - 1, z) &&
          noLogsNearby(world, x, y - 1, z + 1) && noLogsNearby(world, x, y - 1, z - 1) &&
          noLogsNearby(world, x + 1, y - 1, z + 1) && noLogsNearby(world, x + 1, y - 1, z - 1) &&
          noLogsNearby(world, x - 1, y - 1, z + 1) && noLogsNearby(world, x - 1, y - 1, z - 1)) {
          world.func_147465_d(x, y, z, Blocks.field_150350_a, 0, 2);
        }
      }
    }
  }

  private boolean noLogsNearby(World world, int x, int y, int z) {
    return (world.func_72899_e(x, y, z) && world.func_147439_a(x, y, z) != this);
  }







  @SideOnly(Side.CLIENT)
  public void func_149666_a(Item item, CreativeTabs tabs, List<ItemStack> list) {
    for (int i = 0; i < this.woodNames.length; i++) {
      list.add(new ItemStack((Block)this, 1, i));
    }
  }


  public float func_149712_f(World world, int x, int y, int z) {
    return this.field_149782_v;
  }


  private boolean checkOut(World world, int x, int y, int z, int meta) {
    return (world.func_147439_a(x, y, z) == this && world.func_72805_g(x, y, z) == meta);
  }



  public int func_149692_a(int dmg) {
    return dmg;
  }



  @SideOnly(Side.CLIENT)
  public IIcon func_149691_a(int side, int meta) {
    if (side == 0 || side == 1)
      return this.innerIcons[meta];
    return this.sideIcons[meta];
  }



  public void func_149651_a(IIconRegister reg) {
    for (int i = 0; i < this.woodNames.length; i++) {

      this.sideIcons[i] = reg.func_94245_a("terrafirmacraft:wood/trees/" + this.woodNames[i] + " Log");
      this.innerIcons[i] = reg.func_94245_a("terrafirmacraft:wood/trees/" + this.woodNames[i] + " Log Top");
      this.rotatedSideIcons[i] = reg.func_94245_a("terrafirmacraft:wood/trees/" + this.woodNames[i] + " Log Side");
    }
  }




  public void func_149636_a(World world, EntityPlayer entityplayer, int x, int y, int z, int meta) {
    boolean isAxe = false;
    boolean isHammer = false;
    ItemStack equip = entityplayer.func_71045_bC();
    if (!world.field_72995_K)
    {
      if (equip != null) {

        int[] equipIDs = OreDictionary.getOreIDs(equip);
        for (int id : equipIDs) {

          String name = OreDictionary.getOreName(id);
          if (name.startsWith("itemAxe")) {

            isAxe = true;
            if (name.startsWith("itemAxeStone")) {

              this.isStone = true;

              break;
            }
          } else if (name.startsWith("itemHammer")) {

            isHammer = true;

            break;
          }
        }
        if (isAxe) {

          damage = -1;
          processTree(world, x, y, z, meta, equip);

          if (damage + equip.func_77960_j() > equip.func_77958_k()) {

            int ind = entityplayer.field_71071_by.field_70461_c;
            entityplayer.field_71071_by.func_70299_a(ind, null);
            world.func_147465_d(x, y, z, (Block)this, meta, 2);
          } else {

            equip.func_77972_a(damage, (EntityLivingBase)entityplayer);
          }
          int smallStack = logs % 16;
          func_149642_a(world, x, y, z, new ItemStack(TFCItems.logs, smallStack, func_149692_a(meta)));
          logs -= smallStack;


          while (logs > 0)
          {
            func_149642_a(world, x, y, z, new ItemStack(TFCItems.logs, 16, func_149692_a(meta)));
            logs -= 16;
          }

        }
        else if (isHammer) {

          EntityItem item = new EntityItem(world, x + 0.5D, y + 0.5D, z + 0.5D, new ItemStack(TFCItems.stick, 1 + world.field_73012_v.nextInt(3)));
          world.func_72838_d((Entity)item);
        }
      } else {

        world.func_147465_d(x, y, z, (Block)this, meta, 2);
      }
    }
  }


  public void func_149681_a(World world, int x, int y, int z, int side, EntityPlayer entityplayer) {
    int meta = world.func_72805_g(x, y, z);
    func_149636_a(world, entityplayer, x, y, z, meta);
  }



  public boolean func_149718_j(World world, int x, int y, int z) {
    return true;
  }



  public boolean canBeReplacedByLeaves(IBlockAccess world, int x, int y, int z) {
    return false;
  }



  public void func_149723_a(World world, int x, int y, int z, Explosion ex) {
    processTree(world, x, y, z, world.func_72805_g(x, y, z), (ItemStack)null);
  }








  @Deprecated
  private void processTree(World world, int x, int y, int z, int meta, ItemStack is) {
    boolean[][][] checkArray = new boolean[this.searchDist * 2 + 1][256][this.searchDist * 2 + 1];
    scanLogs(world, x, y, z, meta, checkArray, (byte)0, (byte)0, (byte)0, is);
  }



  public Item func_149650_a(int i, Random random, int j) {
    return TFCItems.logs;
  }



  public void func_149695_a(World world, int x, int y, int z, Block block) {
    int meta = world.func_72805_g(x, y, z);
    boolean check = false;
    for (int h = -2; h <= 2; h++) {

      for (int g = -2; g <= 2; g++) {

        for (int f = -2; f <= 2; f++) {

          if (world.func_147439_a(x + h, y + g, z + f) == this && world.func_72805_g(x + h, y + g, z + f) == meta)
            check = true;
        }
      }
    }
    if (!check) {

      world.func_147468_f(x, y, z);
      func_149642_a(world, x, y, z, new ItemStack(TFCItems.logs, 1, meta));
    }
  }



  private void scanLogs(World world, int i, int j, int k, int meta, boolean[][][] checkArray, byte x, byte y, byte z, ItemStack stack) {
    if (y >= 0 && j + y < 256) {

      int offsetX = 0, offsetY = 0, offsetZ = 0;
      checkArray[x + this.searchDist][y][z + this.searchDist] = true;

      for (offsetX = -3; offsetX <= 3; offsetX++) {

        for (offsetZ = -3; offsetZ <= 3; offsetZ++) {

          for (offsetY = 0; offsetY <= 2; offsetY++) {

            if (Math.abs(x + offsetX) <= this.searchDist && j + y + offsetY < 256 && Math.abs(z + offsetZ) <= this.searchDist)
            {
              if (checkOut(world, i + x + offsetX, j + y + offsetY, k + z + offsetZ, meta) && (offsetX != 0 || offsetY != 0 || offsetZ != 0) && !checkArray[x + offsetX + this.searchDist][y + offsetY][z + offsetZ + this.searchDist])
              {

                scanLogs(world, i, j, k, meta, checkArray, (byte)(x + offsetX), (byte)(y + offsetY), (byte)(z + offsetZ), stack);
              }
            }
          }
        }
      }
      damage++;
      if (stack != null) {

        if (damage + stack.func_77960_j() <= stack.func_77958_k())
        {
          world.func_147465_d(i + x, j + y, k + z, Blocks.field_150350_a, 0, 2);
          if (!this.isStone || world.field_73012_v.nextInt(10) != 0)
            logs++;
          if (logs >= 16) {

            func_149642_a(world, i + x, j + y, k + z, new ItemStack(TFCItems.logs, 16, func_149692_a(meta)));
            logs -= 16;
          }
          notifyLeaves(world, i + x, j + y, k + z);
        }

      } else {

        world.func_147468_f(i, j, k);
        logs++;
        if (logs >= 16) {

          func_149642_a(world, i, j, k, new ItemStack(TFCItems.logs, 16, func_149692_a(meta)));
          logs -= 16;
        }
        notifyLeaves(world, i + x, j + y, k + z);
      }
    }
  }


  private void notifyLeaves(World world, int x, int y, int z) {
    world.func_147460_e(x + 1, y, z, Blocks.field_150350_a);
    world.func_147460_e(x - 1, y, z, Blocks.field_150350_a);
    world.func_147460_e(x, y, z + 1, Blocks.field_150350_a);
    world.func_147460_e(x, y, z - 1, Blocks.field_150350_a);
    world.func_147460_e(x, y + 1, z, Blocks.field_150350_a);
    world.func_147460_e(x, y - 1, z, Blocks.field_150350_a);
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Blocks\Flora\BlockLogNatural.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */