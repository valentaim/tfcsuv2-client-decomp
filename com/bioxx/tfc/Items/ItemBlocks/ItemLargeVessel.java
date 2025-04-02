package com.bioxx.tfc.Items.ItemBlocks;

import com.bioxx.tfc.Core.TFCTabs;
import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.Items.ItemTerra;
import com.bioxx.tfc.TileEntities.TEPottery;
import com.bioxx.tfc.TileEntities.TEVessel;
import com.bioxx.tfc.api.Enums.EnumSize;
import com.bioxx.tfc.api.Enums.EnumWeight;
import com.bioxx.tfc.api.Interfaces.IEquipable;
import com.bioxx.tfc.api.TFCBlocks;
import com.bioxx.tfc.api.TFCFluids;
import com.bioxx.tfc.api.Util.Helper;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidBlock;
import org.lwjgl.opengl.GL11;




public class ItemLargeVessel
  extends ItemTerraBlock
  implements IEquipable
{
  private static final int MAX_LIQUID = 5000;

  public ItemLargeVessel(Block block) {
    super(block);
    func_77656_e(0);
    func_77627_a(true);
    func_77637_a(TFCTabs.TFC_POTTERY);
  }



  public EnumSize getSize(ItemStack is) {
    return EnumSize.LARGE;
  }



  public EnumWeight getWeight(ItemStack is) {
    return EnumWeight.HEAVY;
  }



  public int getItemStackLimit(ItemStack is) {
    if (is.func_77942_o())
      return 1;
    return super.getItemStackLimit(is);
  }


  public void createTooltip(NBTTagCompound nbt, List<String> arraylist) {
    if (nbt != null) {

      boolean addFluid = false;
      if (nbt.func_74764_b("fluidNBT")) {

        FluidStack fluid = FluidStack.loadFluidStackFromNBT(nbt.func_74775_l("fluidNBT"));
        if (fluid != null) {

          addFluid = true;
          arraylist.add(EnumChatFormatting.BLUE + fluid.getFluid().getLocalizedName(fluid));
        }
      }

      if (!addFluid && nbt.func_74764_b("Items")) {

        NBTTagList nbttaglist = nbt.func_150295_c("Items", 10);
        if (nbttaglist != null) {

          int numItems = nbttaglist.func_74745_c();
          boolean showMoreText = false;
          if (numItems > 4 && !TFC_Core.showShiftInformation()) {

            numItems = 3;
            showMoreText = true;
          }
          for (int i = 0; i < numItems; i++) {

            NBTTagCompound nbttagcompound1 = nbttaglist.func_150305_b(i);
            if (nbttagcompound1 != null) {

              ItemStack onlyItem = ItemStack.func_77949_a(nbttagcompound1);
              if (onlyItem != null)
              {
                arraylist.add(EnumChatFormatting.GOLD + Integer.toString(onlyItem.field_77994_a) + "x " + onlyItem.func_82833_r());
              }
            }
          }
          if (showMoreText)
          {
            arraylist.add(TFC_Core.translate("gui.Barrel.MoreItems"));
          }
        }
      }
    }
  }




  public void func_77624_a(ItemStack is, EntityPlayer player, List<String> arraylist, boolean flag) {
    ItemTerra.addSizeInformation(is, arraylist);
    createTooltip(is.func_77978_p(), arraylist);
    if (TFC_Core.showShiftInformation()) {

      arraylist.add(TFC_Core.translate("gui.Help"));
      arraylist.add(TFC_Core.translate("gui.PotteryBase.Inst0"));
    } else {

      arraylist.add(TFC_Core.translate("gui.ShowHelp"));
    }
  }


  public boolean func_77648_a(ItemStack is, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
    MovingObjectPosition mop = Helper.getMovingObjectPositionFromPlayer(world, (EntityLivingBase)player, true);

    if (mop == null)
    {
      return super.func_77648_a(is, player, world, x, y, z, side, hitX, hitY, hitZ);
    }


    if (mop.field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK) {

      int i = mop.field_72311_b;
      int j = mop.field_72312_c;
      int k = mop.field_72309_d;

      if (!player.func_82247_a(i, j, k, mop.field_72310_e, is) || !(world.func_147439_a(i, j, k) instanceof IFluidBlock) || is.func_77942_o() || is.func_77960_j() == 0)
      {
        return super.func_77648_a(is, player, world, x, y, z, side, hitX, hitY, hitZ);
      }

      Fluid fluid = ((IFluidBlock)world.func_147439_a(i, j, k)).getFluid();
      int temp = fluid.getTemperature();
      int volume = 0;

      if (temp < 385 && fluid != TFCFluids.HOTWATER) {

        world.func_147468_f(i, j, k);
        if (fluid == TFCFluids.FRESHWATER || fluid == TFCFluids.SALTWATER) {

          volume = 5000;
        }
        else {

          volume = 1000;
        }

        if (is.field_77994_a == 1) {

          ItemBarrels.fillItemBarrel(is, new FluidStack(fluid, volume), 5000);
        }
        else {

          is.field_77994_a--;
          ItemStack outIS = is.func_77946_l();
          outIS.field_77994_a = 1;
          ItemBarrels.fillItemBarrel(outIS, new FluidStack(fluid, volume), 5000);
          if (!player.field_71071_by.func_70441_a(outIS))
          {
            player.func_70099_a(outIS, 0.0F);
          }
        }
      }
      return true;
    }


    return super.func_77648_a(is, player, world, x, y, z, side, hitX, hitY, hitZ);
  }



  public boolean placeBlockAt(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int metadata) {
    if (metadata > 0) {

      if (!world.func_147465_d(x, y, z, this.field_150939_a, metadata & 0xF, 3))
      {
        return false;
      }

      if (world.func_147439_a(x, y, z) == this.field_150939_a)
      {
        this.field_150939_a.func_149689_a(world, x, y, z, (EntityLivingBase)player, stack);
        this.field_150939_a.func_149714_e(world, x, y, z, 0);

        TEVessel te = (TEVessel)world.func_147438_o(x, y, z);
        te.barrelType = metadata;
        return true;
      }

    } else if (metadata == 0 && side == 1 && player.func_70093_af()) {

      Block base = world.func_147439_a(x, y - 1, z);
      if (base != TFCBlocks.pottery && world.func_147437_c(x, y, z)) {


        if (!world.isSideSolid(x, y - 1, z, ForgeDirection.UP))
          return false;
        world.func_147449_b(x, y, z, TFCBlocks.pottery);
      }
      else {

        return false;
      }

      if (world.func_147438_o(x, y, z) instanceof TEPottery) {

        TEPottery te = (TEPottery)world.func_147438_o(x, y, z);
        if (te.canAddItem(0)) {

          te.inventory[0] = stack.func_77946_l();
          (te.inventory[0]).field_77994_a = 1;
          world.func_147471_g(x, y, z);
          return true;
        }
      }
    }

    return false;
  }



  public IEquipable.EquipType getEquipType(ItemStack is) {
    return IEquipable.EquipType.BACK;
  }



  public void onEquippedRender() {
    GL11.glTranslatef(0.0F, 0.0F, -0.2F);
  }



  public boolean getTooHeavyToCarry(ItemStack is) {
    return is.func_77942_o();
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Items\ItemBlocks\ItemLargeVessel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */