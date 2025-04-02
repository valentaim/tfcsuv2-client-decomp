package com.bioxx.tfc.Blocks.Vanilla;

import com.bioxx.tfc.Blocks.BlockTerra;
import com.bioxx.tfc.Core.TFC_Climate;
import com.bioxx.tfc.api.TFCBlocks;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.stats.StatList;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;




public class BlockCustomSnow
  extends BlockTerra
{
  public BlockCustomSnow() {
    super(Material.field_151597_y);
    func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 0.125F, 1.0F);
    func_149675_a(true);
  }



  public boolean func_149742_c(World world, int i, int j, int k) {
    Block block = world.func_147439_a(i, j - 1, k);

    if (block == TFCBlocks.ice || block == TFCBlocks.pottery)
      return false;
    if (block == TFCBlocks.leaves || block == TFCBlocks.leaves2 || block == TFCBlocks.thatch)
      return true;
    return World.func_147466_a((IBlockAccess)world, i, j - 1, k);
  }



  public AxisAlignedBB func_149668_a(World world, int x, int y, int z) {
    float f = 0.125F;
    return AxisAlignedBB.func_72330_a(x + this.field_149759_B, y + this.field_149760_C, z + this.field_149754_D, x + this.field_149755_E, (y + f), z + this.field_149757_G);
  }



  public int func_149645_b() {
    return TFCBlocks.snowRenderId;
  }



  public void func_149636_a(World world, EntityPlayer player, int x, int y, int z, int meta) {
    func_149697_b(world, x, y, z, meta, 0);
    player.func_71064_a(StatList.field_75934_C[func_149682_b((Block)this)], 1);
  }



  public Item func_149650_a(int i, Random r, int j) {
    return Items.field_151126_ay;
  }



  public boolean func_149662_c() {
    return false;
  }



  public boolean canBeReplacedByLeaves(IBlockAccess world, int x, int y, int z) {
    return true;
  }







  public void func_149670_a(World world, int x, int y, int z, Entity entity) {
    int meta = world.func_72805_g(x, y, z) & 0x7;
    double speed = 0.98D - 0.125D * meta;
    entity.field_70159_w *= speed;
    entity.field_70179_y *= speed;
  }



  public void func_149695_a(World world, int x, int y, int z, Block b) {
    if (!func_149742_c(world, x, y, z))
    {
      world.func_147465_d(x, y, z, Blocks.field_150350_a, 0, 2);
    }
  }



  public int func_149745_a(Random r) {
    return 1;
  }



  public boolean func_149686_d() {
    return false;
  }



  public void func_149719_a(IBlockAccess bAccess, int x, int y, int z) {
    int meta = bAccess.func_72805_g(x, y, z) & 0x7;
    float top = (meta + 1) / 8.0F;
    func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, top, 1.0F);
  }



  public int func_149738_a(World world) {
    return 50;
  }



  public void func_149674_a(World world, int x, int y, int z, Random r) {
    if (!func_149742_c(world, x, y, z)) {

      world.func_147465_d(x, y, z, Blocks.field_150350_a, 0, 2);

      return;
    }
    int meta = world.func_72805_g(x, y, z) & 0x7;

    if (world.func_72972_b(EnumSkyBlock.Block, x, y, z) > 11)
    {
      if (r.nextInt(5) == 0)
      {
        if (meta > 0) {
          world.func_72921_c(x, y, z, meta - 1, 2);
        } else {
          world.func_147465_d(x, y, z, Blocks.field_150350_a, 0, 2);
        }
      }
    }
    float temp = TFC_Climate.getHeightAdjustedTemp(world, x, y, z);

    if (temp <= 0.0F && world.func_72896_J()) {

      if (r.nextInt(20) == 0)
      {
        int max = (world.func_147439_a(x, y - 1, z).func_149688_o() == Material.field_151584_j) ? 3 : 7;
        if (meta < max && canAddSnow(world, x, y, z, meta))
        {
          world.func_72921_c(x, y, z, meta + 1, 2);
        }
      }

    } else if (temp > 10.0F) {

      world.func_147465_d(x, y, z, Blocks.field_150350_a, 0, 2);
    }
    else if (temp > 0.0F && world.func_72896_J()) {

      if (r.nextInt(5) == 0)
      {
        if (meta > 0) {
          world.func_72921_c(x, y, z, meta - 1, 2);
        } else {
          world.func_147465_d(x, y, z, Blocks.field_150350_a, 0, 2);
        }
      }
    } else if (temp > 0.0F) {

      if (r.nextInt(20) == 0)
      {
        if (meta > 0) {
          world.func_72921_c(x, y, z, meta - 1, 2);
        } else {
          world.func_147465_d(x, y, z, Blocks.field_150350_a, 0, 2);
        }
      }
    }
  }


  public void func_149651_a(IIconRegister registerer) {
    this.field_149761_L = registerer.func_94245_a("terrafirmacraft:snow");
  }


  private boolean canAddSnowCheckNeighbors(World world, int x, int y, int z, int meta) {
    Block block = world.func_147439_a(x, y, z);

    if (block.func_149688_o() == Material.field_151597_y)
      return (meta <= (world.func_72805_g(x, y, z) & 0x7));
    if (block == TFCBlocks.leaves || block == TFCBlocks.leaves2)
      return (meta < 3);
    if (block.func_149721_r()) {
      return (meta < 6);
    }
    return false;
  }


  private boolean canAddSnow(World world, int x, int y, int z, int meta) {
    if (!canAddSnowCheckNeighbors(world, x + 1, y, z, meta))
      return false;
    if (!canAddSnowCheckNeighbors(world, x - 1, y, z, meta))
      return false;
    if (!canAddSnowCheckNeighbors(world, x, y, z + 1, meta))
      return false;
    return canAddSnowCheckNeighbors(world, x, y, z - 1, meta);
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Blocks\Vanilla\BlockCustomSnow.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */