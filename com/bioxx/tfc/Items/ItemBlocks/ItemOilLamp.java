package com.bioxx.tfc.Items.ItemBlocks;

import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.api.Constant.Global;
import com.bioxx.tfc.api.Enums.EnumSize;
import com.bioxx.tfc.api.Enums.EnumWeight;
import com.bioxx.tfc.api.Interfaces.ISmeltable;
import com.bioxx.tfc.api.Metal;
import com.bioxx.tfc.api.TFCBlocks;
import com.bioxx.tfc.api.TFCFluids;
import com.bioxx.tfc.api.TFCOptions;
import com.bioxx.tfc.api.Util.Helper;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidContainerItem;


public class ItemOilLamp
  extends ItemTerraBlock
  implements ISmeltable, IFluidContainerItem
{
  public ItemOilLamp(Block par1) {
    super(par1);
    this.metaNames = new String[] { "Gold", "Platinum", "RoseGold", "Silver", "SterlingSilver", "BlueSteel" };
  }


  
  public int getDisplayDamage(ItemStack is) {
    FluidStack fuel = FluidStack.loadFluidStackFromNBT(is.func_77978_p());
    int amt = (fuel != null) ? fuel.amount : 0;
    return getMaxDamage(is) - amt;
  }


  
  public boolean isDamaged(ItemStack is) {
    return is.func_77942_o();
  }


  
  public int getMaxDamage(ItemStack is) {
    return 250;
  }

  
  public EnumSize getSize(ItemStack is) {
    return EnumSize.SMALL;
  }

  
  public EnumWeight getWeight(ItemStack is) {
    return EnumWeight.LIGHT;
  }


  
  public int getItemStackLimit(ItemStack is) {
    return 1;
  }


  
  public short getMetalReturnAmount(ItemStack is) {
    return 100;
  }

  
  public boolean isSmeltable(ItemStack is) {
    FluidStack fuel = FluidStack.loadFluidStackFromNBT(is.func_77978_p());
    return (fuel == null || fuel.amount == 0);
  }


  
  public ISmeltable.EnumTier getSmeltTier(ItemStack is) {
    return ISmeltable.EnumTier.TierI;
  }


  
  public Metal getMetalType(ItemStack is) {
    int meta = is.func_77960_j();
    switch (meta) {
      case 0:
        return Global.GOLD;
      case 1: return Global.PLATINUM;
      case 2: return Global.ROSEGOLD;
      case 3: return Global.SILVER;
      case 4: return Global.STERLINGSILVER;
      case 5: return Global.BLUESTEEL;
    }  return Global.UNKNOWN;
  }



  
  public boolean func_77648_a(ItemStack is, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
    MovingObjectPosition mop = func_77621_a(world, player, !is.func_77942_o());
    if (mop != null && is.func_77960_j() == 5 && world.func_147439_a(mop.field_72311_b, mop.field_72312_c, mop.field_72309_d) == TFCBlocks.lavaStationary) {
      
      if (!is.func_77942_o()) {
        
        FluidStack fs = new FluidStack(TFCFluids.LAVA, 250);
        is.func_77982_d(fs.writeToNBT(new NBTTagCompound()));
      } 
      return false;
    } 
    
    int yCoord = y;
    if (side == 0) {
      yCoord--;
    } else if (side == 1) {
      yCoord++;
    } else {
      return false;
    } 
    int xCoord = x;
    int zCoord = z;
    
    if (world.func_147437_c(xCoord, yCoord, zCoord))
    {
      return super.func_77648_a(is, player, world, xCoord, yCoord, zCoord, side, hitX, hitY, hitZ);
    }
    
    return false;
  }


  
  public boolean placeBlockAt(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int metadata) {
    FluidStack fs = FluidStack.loadFluidStackFromNBT(stack.func_77978_p());
    if (fs == null || (fs.getFluid() != TFCFluids.OLIVEOIL && fs.getFluid() != TFCFluids.LAVA))
    {
      metadata += 8;
    }
    
    return super.placeBlockAt(stack, player, world, x, y, z, side, hitX, hitY, hitZ, metadata);
  }


  
  public void func_77624_a(ItemStack is, EntityPlayer player, List<String> arraylist, boolean flag) {
    super.func_77624_a(is, player, arraylist, flag);
    if (is.func_77942_o()) {
      
      FluidStack fs = FluidStack.loadFluidStackFromNBT(is.func_77978_p());
      if (fs != null && fs.getFluid() == TFCFluids.OLIVEOIL)
        arraylist.add((fs.amount * TFCOptions.oilLampFuelMult) + " " + TFC_Core.translate("gui.hoursRemaining") + " (" + Helper.roundNumber(fs.amount / 250.0F * 100.0F, 10.0F) + "%)"); 
      if (fs != null && fs.getFluid() == TFCFluids.LAVA) {
        arraylist.add(TFC_Core.translate("gui.infinite") + " " + TFC_Core.translate("gui.hoursRemaining"));
      }
    } 
  }
  
  public static ItemStack getFullLamp(int meta) {
    ItemStack is = new ItemStack(TFCBlocks.oilLamp, 1, meta);
    FluidStack fs = new FluidStack(TFCFluids.OLIVEOIL, 250);
    is.func_77982_d(fs.writeToNBT(new NBTTagCompound()));
    return is;
  }


  
  public FluidStack getFluid(ItemStack container) {
    return FluidStack.loadFluidStackFromNBT(container.func_77978_p());
  }


  
  public int getCapacity(ItemStack container) {
    return 250;
  }


  
  public int fill(ItemStack container, FluidStack resource, boolean doSim) {
    FluidStack fs = getFluid(container);
    int inAmt = 0;
    if (fs != null) {
      
      int max = getCapacity(container) - fs.amount;
      if (max > 0 && fs.isFluidEqual(resource)) {
        
        inAmt = Math.min(max, resource.amount);
        if (doSim)
        {
          fs.amount += inAmt;
          if (container.func_77978_p() == null)
            container.func_77982_d(new NBTTagCompound()); 
          fs.writeToNBT(container.func_77978_p());
        }
      
      } 
    } else {
      
      inAmt = Math.min(getCapacity(container), resource.amount);
      if (doSim) {
        
        fs = resource.copy();
        fs.amount = inAmt;
        if (container.func_77978_p() == null)
          container.func_77982_d(new NBTTagCompound()); 
        fs.writeToNBT(container.func_77978_p());
      } 
    } 
    return inAmt;
  }


  
  public FluidStack drain(ItemStack container, int maxDrain, boolean doSim) {
    FluidStack fs = getFluid(container);
    FluidStack fsOut = fs.copy();
    fsOut.amount = Math.min(maxDrain, fs.amount);
    
    if (doSim)
    {
      if (fs.amount - fsOut.amount <= 0) {
        
        container.field_77990_d = null;
      }
      else {
        
        fs.amount -= fsOut.amount;
        if (container.func_77978_p() == null)
          container.func_77982_d(new NBTTagCompound()); 
        fs.writeToNBT(container.func_77978_p());
      } 
    }
    return fsOut;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Items\ItemBlocks\ItemOilLamp.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */