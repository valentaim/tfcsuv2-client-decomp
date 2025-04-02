package com.bioxx.tfc.Blocks.Liquids;

import com.bioxx.tfc.Blocks.Vanilla.BlockCustomLilyPad;
import com.bioxx.tfc.Core.TFC_Climate;
import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.WorldGen.DataLayer;
import com.bioxx.tfc.api.TFCBlocks;
import com.bioxx.tfc.api.TFCOptions;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDynamicLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidBlock;


public abstract class BlockCustomLiquid
  extends BlockDynamicLiquid
  implements IFluidBlock
{
  protected Fluid fluidType;
  protected IIcon[] icons;
  protected int sourceBlockCount;
  protected boolean[] canFlowDirections = new boolean[4];
  protected int[] flowPriorities = new int[4];


  public BlockCustomLiquid(Fluid fluid, Material mat) {
    super(mat);
    float f = 0.0F;
    float f1 = 0.0F;
    func_149676_a(0.0F + f1, 0.0F + f, 0.0F + f1, 1.0F + f1, 1.0F + f, 1.0F + f1);
    func_149675_a(true);
    this.fluidType = fluid;
  }




  public boolean func_149646_a(IBlockAccess world, int x, int y, int z, int side) {
    Block block = world.func_147439_a(x, y, z);
    if (block.func_149688_o() == func_149688_o())
    {
      return false;
    }

    return super.func_149646_a(world, x, y, z, side);
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







  @SideOnly(Side.CLIENT)
  public int func_149720_d(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
    if (this.field_149764_J != Material.field_151586_h) {
      return 16777215;
    }
    return 3493173;
  }







  public void func_149726_b(World world, int x, int y, int z) {
    if (world.func_147439_a(x, y, z) == this)
    {
      world.func_147464_a(x, y, z, (Block)this, func_149738_a(world));
    }
    checkForHarden(world, x, y, z);
  }








  public void func_149695_a(World world, int x, int y, int z, Block block) {
    checkForHarden(world, x, y, z);
  }





  private void checkForHarden(World world, int x, int y, int z) {
    if (world.func_147439_a(x, y, z) == this &&
      this.field_149764_J == Material.field_151587_i) {

      boolean flag = false;

      if (flag || world.func_147439_a(x, y, z - 1).func_149688_o() == Material.field_151586_h) {
        flag = true;
      }
      if (flag || world.func_147439_a(x, y, z + 1).func_149688_o() == Material.field_151586_h) {
        flag = true;
      }
      if (flag || world.func_147439_a(x - 1, y, z).func_149688_o() == Material.field_151586_h) {
        flag = true;
      }
      if (flag || world.func_147439_a(x + 1, y, z).func_149688_o() == Material.field_151586_h) {
        flag = true;
      }
      if (flag || world.func_147439_a(x, y + 1, z).func_149688_o() == Material.field_151586_h) {
        flag = true;
      }
      if (flag) {

        int l = world.func_72805_g(x, y, z);

        if (l == 0) {
          setBlockforLava(world, x, y, z, 0);
        } else if (l <= 4) {
          setBlockforLava(world, x, y, z, 1);
        }
      }
    }
  }






















  public boolean canSustainPlant(IBlockAccess world, int x, int y, int z, ForgeDirection direction, IPlantable plantable) {
    boolean isBeach, hasWater;
    Block plant = plantable.getPlant(world, x, y + 1, z);
    if (plant == TFCBlocks.cactus && this == TFCBlocks.cactus)
    {
      return true;
    }
    if (plant == TFCBlocks.reeds && this == TFCBlocks.reeds)
    {
      return true;
    }

    int meta = world.func_72805_g(x, y, z);
    if (plant instanceof BlockCustomLilyPad && ((BlockCustomLilyPad)plant).canThisPlantGrowOnThisBlock((Block)this, meta))
    {
      return true;
    }

    EnumPlantType plantType = plantable.getPlantType(world, x, y + 1, z);
    switch (plantType) {
      case Desert:
        return TFC_Core.isSand((Block)this);
      case Nether: return (this == Blocks.field_150425_aM);
      case Crop: return TFC_Core.isFarmland((Block)this);
      case Cave: return isSideSolid(world, x, y, z, ForgeDirection.UP);
      case Plains: return (this == TFCBlocks.grass || this == TFCBlocks.grass2 || this == TFCBlocks.dirt || this == TFCBlocks.dirt2);
      case Water: return (world.func_147439_a(x, y, z).func_149688_o() == Material.field_151586_h && world.func_72805_g(x, y, z) == 0);
      case Beach:
        isBeach = TFC_Core.isGround((Block)this);




        hasWater = (world.func_147439_a(x - 1, y, z).func_149688_o() == Material.field_151586_h || world.func_147439_a(x + 1, y, z).func_149688_o() == Material.field_151586_h || world.func_147439_a(x, y, z - 1).func_149688_o() == Material.field_151586_h || world.func_147439_a(x, y, z + 1).func_149688_o() == Material.field_151586_h);
        return (isBeach && hasWater);
    }

    return false;
  }


  public void setBlockforLava(World world, int x, int y, int z, int typeOfLava) {
    DataLayer rockLayer3 = TFC_Climate.getCacheManager(world).getRockLayerAt(x, y, 2);

    int meta = rockLayer3.data2;
    Random rand = new Random();
    boolean felsicLava = true;

    if (this == TFCBlocks.stoneIgIn && (meta == 2 || meta == 1)) {
      felsicLava = false;
    } else if (this == TFCBlocks.stoneIgEx && (meta == 1 || meta == 2)) {
      felsicLava = false;
    }  if (typeOfLava == 0 || typeOfLava == 2) {

      if (felsicLava) {

        if (rand.nextInt(10) == 0 && typeOfLava == 0) {
          world.func_147449_b(x, y, z, Blocks.field_150343_Z);
        } else {

          world.func_147449_b(x, y, z, TFCBlocks.stoneIgEx);
          world.func_72921_c(x, y, z, 0, 0);
        }

      } else {

        world.func_147449_b(x, y, z, TFCBlocks.stoneIgEx);
        world.func_72921_c(x, y, z, 1, 0);
      }

    } else if (typeOfLava == 1) {

      world.func_147449_b(x, y, z, TFCBlocks.stoneIgExCobble);
      if (felsicLava) {
        world.func_72921_c(x, y, z, 0, 0);
      } else {
        world.func_72921_c(x, y, z, 1, 0);
      }
    }
  }


  public int func_149738_a(World world) {
    if (func_149688_o() == Material.field_151587_i)
      return 10;
    return 3;
  }



  public void func_149651_a(IIconRegister register) {
    if (this.field_149764_J == Material.field_151587_i) {

      getFluid().setIcons(register.func_94245_a("lava_still"), register.func_94245_a("lava_flow"));
    }
    else {

      getFluid().setIcons(register.func_94245_a("water_still"), register.func_94245_a("water_flow"));
    }
    this.icons = new IIcon[] { getFluid().getStillIcon(), getFluid().getFlowingIcon() };
  }



  @SideOnly(Side.CLIENT)
  public IIcon func_149691_a(int side, int meta) {
    return (side != 0 && side != 1) ? this.icons[1] : this.icons[0];
  }
  public Fluid getFluid() {
    return this.fluidType;
  }

  public FluidStack drain(World world, int x, int y, int z, boolean doDrain) {
    return null;
  }

  public boolean canDrain(World world, int x, int y, int z) {
    return false;
  }

  public float getFilledPercentage(World world, int x, int y, int z) {
    return 1.0F;
  }


  public boolean canStay(World world, int x, int y, int z) {
    Block block = world.func_147439_a(x, y, z);
    String name = block.getClass().getSimpleName().toLowerCase();
    if (name.equals("blocktablestorage") || name.equals("blockpreptable")) return true;
    if (block == TFCBlocks.thatch)
    {
      return false;
    }
    return func_149807_p(world, x, y, z);
  }


  protected int getLiquidHeight(World world, int x, int y, int z, int count) {
    int liquidHeight = getMetaData(world, x, y, z);


    if (liquidHeight < 0)
    {
      return count;
    }



    if (liquidHeight == 0) {
      this.sourceBlockCount++;
    } else if (liquidHeight >= 8) {
      liquidHeight = 0;
    }
    return (count >= 0 && liquidHeight >= count) ? count : liquidHeight;
  }




  public void func_149674_a(World world, int x, int y, int z, Random rand) {
    int meta = getMetaData(world, x, y, z);
    byte b0 = 1;

    if (this.field_149764_J == Material.field_151587_i && !world.field_73011_w.field_76575_d)
    {
      b0 = 2;
    }

    boolean flag = true;
    int tickRate = func_149738_a(world);


    if (meta > 0) {

      this.sourceBlockCount = 0;
      int liquidHeight = getLiquidHeight(world, x - 1, y, z, -100);
      liquidHeight = getLiquidHeight(world, x + 1, y, z, liquidHeight);
      liquidHeight = getLiquidHeight(world, x, y, z - 1, liquidHeight);
      liquidHeight = getLiquidHeight(world, x, y, z + 1, liquidHeight);
      int newHeight = liquidHeight + b0;

      if (newHeight >= 8 || liquidHeight < 0)
      {
        newHeight = -1;
      }


      if (getMetaData(world, x, y + 1, z) >= 0) {

        int metaAbove = getMetaData(world, x, y + 1, z);

        if (metaAbove >= 8) {

          newHeight = metaAbove;
        }
        else {

          newHeight = metaAbove + 8;
        }
      }

      if (this.sourceBlockCount >= 2 && this.field_149764_J == Material.field_151586_h && !TFCOptions.enableFiniteWater)
      {

        if (world.func_147439_a(x, y - 1, z).func_149688_o().func_76220_a() || (world
          .func_147439_a(x, y - 1, z).func_149688_o() == this.field_149764_J && world.func_72805_g(x, y - 1, z) == 0))
        {
          newHeight = 0;
        }
      }

      if (this.field_149764_J == Material.field_151587_i && meta < 8 && newHeight < 8 && newHeight > meta && rand.nextInt(4) != 0)
      {
        tickRate *= 4;
      }

      if (newHeight == meta) {

        if (flag)
        {
          convertFlowingToSource(world, x, y, z);
        }
      }
      else {

        meta = newHeight;

        if (newHeight < 0)
        {
          world.func_147468_f(x, y, z);
        }
        else
        {
          world.func_72921_c(x, y, z, newHeight, 2);
          world.func_147464_a(x, y, z, (Block)this, tickRate);
          world.func_147459_d(x, y, z, (Block)this);
        }

      }
    } else {

      convertFlowingToSource(world, x, y, z);
    }

    if (canReplace(world, x, y - 1, z)) {

      if (this.field_149764_J == Material.field_151587_i && world.func_147439_a(x, y - 1, z).func_149688_o() == Material.field_151586_h) {

        setBlockforLava(world, x, y - 1, z, 2);
        func_149799_m(world, x, y - 1, z);

        return;
      }
      if (meta >= 8)
      {
        flow(world, x, y - 1, z, meta);
      }
      else
      {
        flow(world, x, y - 1, z, meta + 8);
      }

    } else if (meta >= 0 && (meta == 0 || canStay(world, x, y - 1, z))) {

      int newHeight = meta + b0;

      if (meta >= 8)
      {
        newHeight = 1;
      }

      if (newHeight >= 8) {
        return;
      }


      boolean[] flowDirections = getFlowDirections(world, x, y, z);
      if (flowDirections[0])
      {
        flow(world, x - 1, y, z, newHeight);
      }

      if (flowDirections[1])
      {
        flow(world, x + 1, y, z, newHeight);
      }

      if (flowDirections[2])
      {
        flow(world, x, y, z - 1, newHeight);
      }

      if (flowDirections[3])
      {
        flow(world, x, y, z + 1, newHeight);
      }
    }
  }


  private void flow(World world, int x, int y, int z, int meta) {
    if (canReplace(world, x, y, z)) {

      Block block = world.func_147439_a(x, y, z);

      if (this.field_149764_J == Material.field_151587_i) {

        setBlockforLava(world, x, y, z, 0);
        func_149799_m(world, x, y - 1, z);
      }
      else {

        block.func_149697_b(world, x, y, z, world.func_72805_g(x, y, z), 0);
      }

      world.func_147465_d(x, y, z, (Block)this, meta, 3);
    }
  }


  private boolean canReplace(World world, int x, int y, int z) {
    Block b = world.func_147439_a(x, y, z);
    Material material = b.func_149688_o();
    if (b == Blocks.field_150448_aq || b == Blocks.field_150408_cc || b == Blocks.field_150319_E || b == Blocks.field_150318_D) return false;
    if (material == this.field_149764_J || material == Material.field_151587_i) {
      return false;
    }
    return !canStay(world, x, y, z);
  }




  private boolean[] getFlowDirections(World world, int x, int y, int z) {
    int side;
    for (side = 0; side < 4; side++) {

      this.flowPriorities[side] = 1000;
      int i = x;
      int newZ = z;

      if (side == 0) {
        i = x - 1;
      } else if (side == 1) {
        i++;
      } else if (side == 2) {
        newZ = z - 1;
      } else if (side == 3) {
        newZ++;
      }
      if (!canStay(world, i, y, newZ) && (world.func_147439_a(i, y, newZ).func_149688_o() != this.field_149764_J || world.func_72805_g(i, y, newZ) != 0))
      {
        if (canStay(world, i, y - 1, newZ)) {

          this.flowPriorities[side] = getFlowPriorities(world, i, y, newZ, 1, side);
        }
        else {

          this.flowPriorities[side] = 0;
        }
      }
    }

    side = this.flowPriorities[0];
    int newX;
    for (newX = 1; newX < 4; newX++) {

      if (this.flowPriorities[newX] < side)
      {
        side = this.flowPriorities[newX];
      }
    }

    for (newX = 0; newX < 4; newX++)
    {
      this.canFlowDirections[newX] = (this.flowPriorities[newX] == side);
    }

    return this.canFlowDirections;
  }


  private void convertFlowingToSource(World world, int x, int y, int z) {
    int meta = world.func_72805_g(x, y, z);
    world.func_147465_d(x, y, z, Block.func_149729_e(Block.func_149682_b((Block)this) + 1), meta, 2);
  }


  private int getFlowPriorities(World world, int x, int y, int z, int distance, int side) {
    int priority = 1000;

    for (int side2 = 0; side2 < 4; side2++) {

      if ((side2 != 0 || side != 1) && (side2 != 1 || side != 0) && (side2 != 2 || side != 3) && (side2 != 3 || side != 2)) {

        int xCoord = x;
        int zCoord = z;

        if (side2 == 0) {
          xCoord = x - 1;
        } else if (side2 == 1) {
          xCoord++;
        } else if (side2 == 2) {
          zCoord = z - 1;
        } else if (side2 == 3) {
          zCoord++;
        }
        if (!canStay(world, xCoord, y, zCoord) && (world.func_147439_a(xCoord, y, zCoord).func_149688_o() != this.field_149764_J || world.func_72805_g(xCoord, y, zCoord) != 0)) {

          if (!canStay(world, xCoord, y - 1, zCoord))
          {
            return distance;
          }

          if (distance < 4) {

            int newDistance = getFlowPriorities(world, xCoord, y, zCoord, distance + 1, side2);

            if (newDistance < priority)
            {
              priority = newDistance;
            }
          }
        }
      }
    }

    return priority;
  }


  protected int getMetaData(World world, int x, int y, int z) {
    return (world.func_147439_a(x, y, z).func_149688_o() == this.field_149764_J) ? world.func_72805_g(x, y, z) : -1;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Blocks\Liquids\BlockCustomLiquid.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */