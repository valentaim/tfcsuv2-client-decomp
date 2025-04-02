package com.bioxx.tfc.Blocks.Liquids;

import com.bioxx.tfc.Core.TFC_Climate;
import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.api.TFCBlocks;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidBlock;




public class BlockLiquidStatic
  extends BlockLiquid
  implements IFluidBlock
{
  private Block flowing;
  protected Fluid fluidType;
  protected IIcon[] icons;

  public BlockLiquidStatic(Fluid fluid, Material material, Block f) {
    super(material);
    this.flowing = f;
    this.fluidType = fluid;
    func_149675_a(true);
    fluid.setBlock((Block)this);
  }



  public Fluid getFluid() {
    return this.fluidType;
  }



  public FluidStack drain(World world, int x, int y, int z, boolean doDrain) {
    if (!isDrainableSourceBlock((IBlockAccess)world, x, y, z))
    {
      return null;
    }

    if (doDrain)
    {
      world.func_147449_b(x, y, z, Blocks.field_150350_a);
    }

    return new FluidStack(this.fluidType, 1000);
  }



  public boolean canDrain(World world, int x, int y, int z) {
    return isDrainableSourceBlock((IBlockAccess)world, x, y, z);
  }



  public float getFilledPercentage(World world, int x, int y, int z) {
    return 1.0F;
  }



  @SideOnly(Side.CLIENT)
  public int func_149720_d(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
    if (this.field_149764_J != Material.field_151586_h) {
      return 16777215;
    }
    return 3493173;
  }



  public void func_149670_a(World world, int x, int y, int z, Entity e) {
    if (this.field_149764_J == Material.field_151587_i)
    {
      if (e instanceof net.minecraft.entity.item.EntityItem)
      {
        e.func_70015_d(15);
      }
    }
  }







  public void func_149695_a(World world, int x, int y, int z, Block b) {
    super.func_149695_a(world, x, y, z, b);

    if (world.func_147439_a(x, y, z) == this)
    {
      setNotStationary(world, x, y, z);
    }
  }



  public void func_149674_a(World world, int x, int y, int z, Random rand) {
    if (!world.field_72995_K) {

      if (world.func_147437_c(x, y + 1, z))
      {
        world.field_73011_w.canBlockFreeze(x, y, z, false);
      }


      if (world.func_147439_a(x, y, z) == TFCBlocks.freshWaterStationary && world
        .func_147437_c(x, y + 1, z) &&
        TFC_Climate.getHeightAdjustedTemp(world, x, y + 1, z) > 2.0F)
      {
        if (rand.nextInt(100) < 25 && world.func_72957_l(x, y, z) < 7)
        {

          for (int x1 = x - 3; x1 < x + 3; x1++) {

            for (int z1 = z - 3; z1 < z + 3; z1++) {

              if (TFC_Core.isGrass(world.func_147439_a(x1, y, z1)) || world.func_147439_a(x1, y - 1, z1) == TFCBlocks.waterPlant) {

                float mod = rand.nextFloat();
                world.func_72908_a(x, y, z, "terrafirmacraft:mob.frog", (mod < 0.55F) ? mod : 0.55F, (mod < 0.41F) ? (mod + 0.8F) : 0.8F);

                // Byte code: goto -> 228
              }
            }
          }
        }
      }
      if (func_149688_o() == Material.field_151587_i)
      {
        if (world.func_147439_a(x, y + 1, z).func_149688_o() == Material.field_151579_a) {

          int i = x - 2 + rand.nextInt(5);
          int j = y + 1 + rand.nextInt(4);
          int k = z - 2 + rand.nextInt(5);

          if (world.func_147439_a(i, j, k).func_149688_o() == Material.field_151579_a)
          {
            if (isFlammable(world, i - 1, j, k) || isFlammable(world, i + 1, j, k) ||
              isFlammable(world, i, j, k - 1) || isFlammable(world, i, j, k + 1) ||
              isFlammable(world, i, j - 1, k) || isFlammable(world, i, j + 1, k)) {

              world.func_147449_b(i, j, k, (Block)Blocks.field_150480_ab);
            }
            else if (world.isSideSolid(i, j + 1, k, ForgeDirection.DOWN) || world.isSideSolid(i, j - 1, k, ForgeDirection.UP) || world
              .isSideSolid(i - 1, j, k, ForgeDirection.EAST) || world.isSideSolid(i + 1, j, k, ForgeDirection.WEST) || world
              .isSideSolid(i, j, k + 1, ForgeDirection.NORTH) || world.isSideSolid(i, j, k - 1, ForgeDirection.SOUTH)) {


              if (world.field_73012_v.nextFloat() < 0.2F) world.func_147465_d(i, j, k, TFCBlocks.sulfur, world.field_73012_v.nextInt(4), 3);

            }
          }

          int distance = rand.nextInt(3);

          int xCoord;
          for (xCoord = 0; xCoord < distance; xCoord++) {

            x += rand.nextInt(3) - 1;
            y++;
            z += rand.nextInt(3) - 1;
            Block block = world.func_147439_a(x, y, z);

            if (block.func_149688_o() == Material.field_151579_a) {

              if (isFlammable(world, x - 1, y, z) || isFlammable(world, x + 1, y, z) ||
                isFlammable(world, x, y, z - 1) || isFlammable(world, x, y, z + 1) ||
                isFlammable(world, x, y - 1, z) || isFlammable(world, x, y + 1, z)) {

                world.func_147449_b(x, y, z, (Block)Blocks.field_150480_ab);

                return;
              }
            } else if (block.func_149688_o().func_76230_c()) {
              return;
            }
          }


          if (distance == 0) {

            xCoord = x;
            int zCoord = z;

            for (int c = 0; c < 3; c++) {

              x = xCoord + rand.nextInt(3) - 1;
              z = zCoord + rand.nextInt(3) - 1;

              if (world.func_147437_c(x, y + 1, z) && isFlammable(world, x, y, z))
              {
                world.func_147449_b(x, y + 1, z, (Block)Blocks.field_150480_ab);
              }
            }
          }
        }
      }
    }

    super.func_149674_a(world, x, y, z, rand);
  }






  private boolean isFlammable(World world, int x, int y, int z) {
    return (Blocks.field_150480_ab.getFlammability(world.func_147439_a(x, y, z)) > 0);
  }





  private void setNotStationary(World world, int x, int y, int z) {
    int l = world.func_72805_g(x, y, z);
    world.func_147465_d(x, y, z, this.flowing, l, 2);
    world.func_147464_a(x, y, z, this.flowing, func_149738_a(world));
  }



  @SideOnly(Side.CLIENT)
  public void func_149651_a(IIconRegister registerer) {
    if (func_149688_o() == Material.field_151587_i) {
      this.icons = new IIcon[] { registerer.func_94245_a("lava_still"), registerer.func_94245_a("lava_flow") };
    } else {
      this.icons = new IIcon[] { registerer.func_94245_a("water_still"), registerer.func_94245_a("water_flow") };
    }  getFluid().setIcons(this.icons[0], this.icons[1]);
  }



  @SideOnly(Side.CLIENT)
  public IIcon func_149691_a(int side, int meta) {
    return (side != 0 && side != 1) ? this.icons[1] : this.icons[0];
  }


  public boolean isDrainableSourceBlock(IBlockAccess world, int x, int y, int z) {
    Block block = world.func_147439_a(x, y, z);
    return (!TFC_Core.isHotWater(block) && block == this && world.func_72805_g(x, y, z) == 0);
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Blocks\Liquids\BlockLiquidStatic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */