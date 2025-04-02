package com.bioxx.tfc.Items.Tools;

import com.bioxx.tfc.Items.ItemTerra;
import com.bioxx.tfc.api.Enums.EnumItemReach;
import com.bioxx.tfc.api.Enums.EnumSize;
import com.bioxx.tfc.api.TFCBlocks;
import com.bioxx.tfc.api.Util.Helper;
import cpw.mods.fml.common.eventhandler.Event;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.FillBucketEvent;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidBlock;



public class ItemSteelBucket
  extends ItemTerra
{
  protected Block bucketContents;

  public ItemSteelBucket(Block par2) {
    this.bucketContents = par2;
    setFolder("tools/");
  }



  public EnumSize getSize(ItemStack is) {
    return EnumSize.LARGE;
  }



  public boolean canStack() {
    return false;
  }







  public ItemStack func_77659_a(ItemStack is, World world, EntityPlayer player) {
    boolean var11 = (this.bucketContents == Blocks.field_150350_a);
    MovingObjectPosition mop = Helper.getMovingObjectPositionFromPlayer(world, (EntityLivingBase)player, var11);

    if (mop == null)
    {
      return is;
    }


    FillBucketEvent event = new FillBucketEvent(player, is, world, mop);
    if (MinecraftForge.EVENT_BUS.post((Event)event))
    {
      return is;
    }

    if (event.getResult() == Event.Result.ALLOW) {

      if (player.field_71075_bZ.field_75098_d)
      {
        return is;
      }

      if (--is.field_77994_a <= 0)
      {
        return event.result;
      }

      if (!player.field_71071_by.func_70441_a(event.result))
      {
        player.func_71019_a(event.result, false);
      }

      return is;
    }
    if (mop.field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK) {

      int i = mop.field_72311_b;
      int j = mop.field_72312_c;
      int k = mop.field_72309_d;



      if (!world.func_72962_a(player, i, j, k))
      {
        return is;
      }

      if (this.bucketContents == Blocks.field_150350_a) {

        if (!player.func_82247_a(i, j, k, mop.field_72310_e, is) || !(world.func_147439_a(i, j, k) instanceof IFluidBlock))
        {
          return is;
        }

        Fluid fluid = ((IFluidBlock)world.func_147439_a(i, j, k)).getFluid();
        if (fluid != null) {

          ItemStack filledIS = FluidContainerRegistry.fillFluidContainer(new FluidStack(fluid, 1000), is);
          if (filledIS != null)
          {
            world.func_147468_f(i, j, k);

            if (--is.field_77994_a <= 0)
            {
              return filledIS;
            }

            if (!player.field_71071_by.func_70441_a(filledIS))
            {
              player.func_70099_a(filledIS, 0.0F);
            }

            return is;
          }

        }
      } else {

        if (this.bucketContents == Blocks.field_150350_a)
        {
          return getContainerItem(is);
        }

        if (mop.field_72310_e == 0) j--;
        if (mop.field_72310_e == 1) j++;
        if (mop.field_72310_e == 2) k--;
        if (mop.field_72310_e == 3) k++;
        if (mop.field_72310_e == 4) i--;
        if (mop.field_72310_e == 5) i++;

        if (!player.func_82247_a(i, j, k, mop.field_72310_e, is))
        {
          return is;
        }

        if (tryPlaceContainedLiquid(world, i, j, k) && !player.field_71075_bZ.field_75098_d)
        {
          return getContainerItem(is);
        }
      }
    }

    return is;
  }





  public boolean tryPlaceContainedLiquid(World world, int x, int y, int z) {
    if (this.bucketContents == Blocks.field_150350_a)
    {
      return false;
    }


    Material material = world.func_147439_a(x, y, z).func_149688_o();
    boolean flag = !material.func_76220_a();

    if (!world.func_147437_c(x, y, z) && !flag)
    {
      return false;
    }


    if (world.field_73011_w.field_76575_d && (this.bucketContents == TFCBlocks.freshWater || this.bucketContents == TFCBlocks.saltWater)) {

      world.func_72908_a((x + 0.5F), (y + 0.5F), (z + 0.5F), "random.fizz", 0.5F, 2.6F + (world.field_73012_v.nextFloat() - world.field_73012_v.nextFloat()) * 0.8F);

      for (int l = 0; l < 8; l++)
      {
        world.func_72869_a("largesmoke", x + Math.random(), y + Math.random(), z + Math.random(), 0.0D, 0.0D, 0.0D);
      }
    }
    else {

      if (!world.field_72995_K && flag && !material.func_76224_d())
      {
        world.func_147480_a(x, y, z, true);
      }

      world.func_147465_d(x, y, z, this.bucketContents, 0, 3);
    }

    return true;
  }





  public EnumItemReach getReach(ItemStack is) {
    return EnumItemReach.SHORT;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Items\Tools\ItemSteelBucket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */