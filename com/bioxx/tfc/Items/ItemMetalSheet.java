package com.bioxx.tfc.Items;

import com.bioxx.tfc.Core.Metal.MetalRegistry;
import com.bioxx.tfc.Core.TFCTabs;
import com.bioxx.tfc.TileEntities.TEMetalSheet;
import com.bioxx.tfc.api.Enums.EnumSize;
import com.bioxx.tfc.api.Enums.EnumWeight;
import com.bioxx.tfc.api.Interfaces.ISmeltable;
import com.bioxx.tfc.api.Metal;
import com.bioxx.tfc.api.TFCBlocks;
import com.bioxx.tfc.api.TFC_ItemHeat;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class ItemMetalSheet
  extends ItemTerra
  implements ISmeltable {
  protected int[][] sidesMap = new int[][] { { 0, -1, 0 }, { 0, 1, 0 }, { 0, 0, -1 }, { 0, 0, 1 }, { -1, 0, 0 }, { 1, 0, 0 } };

  public int metalID;

  private String metal;
  protected short metalAmount;
  private boolean smeltable = true;

  public ItemMetalSheet(int mID) {
    func_77656_e(0);
    func_77637_a(TFCTabs.TFC_MATERIALS);
    setFolder("ingots/");
    setWeight(EnumWeight.MEDIUM);
    setSize(EnumSize.MEDIUM);
    this.metalID = mID;
    this.metalAmount = 200;
  }


  public ItemTerra setMetal(String m, int amt) {
    this.metal = m;
    this.metalAmount = (short)amt;
    return this;
  }



  public boolean func_77648_a(ItemStack itemstack, EntityPlayer entityplayer, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
    boolean isSuccessful = false;
    if (!world.field_72995_K) {


      if (itemstack.func_77942_o()) {
        return false;
      }
      TEMetalSheet te = null;
      int[] sides = this.sidesMap[side];


      if (world.func_147439_a(x, y, z) == TFCBlocks.metalSheet && isValid(world, x, y, z)) {

        te = (TEMetalSheet)world.func_147438_o(x, y, z);
        switch (side) {

          case 0:
            if (!te.bottomExists()) {

              te.toggleBottom(true);
              isSuccessful = true;
            }
            break;
          case 1:
            if (!te.topExists()) {

              te.toggleTop(true);
              isSuccessful = true;
            }
            break;
          case 2:
            if (!te.northExists()) {

              te.toggleNorth(true);
              isSuccessful = true;
            }
            break;
          case 3:
            if (!te.southExists()) {

              te.toggleSouth(true);
              isSuccessful = true;
            }
            break;
          case 4:
            if (!te.eastExists()) {

              te.toggleEast(true);
              isSuccessful = true;
            }
            break;
          case 5:
            if (!te.westExists()) {

              te.toggleWest(true);
              isSuccessful = true;
            }
            break;
        }


        if (isSuccessful) {
          world.func_147471_g(x, y, z);
        }
      }
      else if (world.func_147439_a(x, y, z) != TFCBlocks.metalSheet && isValid(world, sides[0] + x, sides[1] + y, sides[2] + z)) {

        world.func_147449_b(sides[0] + x, sides[1] + y, sides[2] + z, TFCBlocks.metalSheet);
        te = (TEMetalSheet)world.func_147438_o(sides[0] + x, sides[1] + y, sides[2] + z);
        te.metalID = this.metalID;
        te.sheetStack = itemstack.func_77946_l();
        te.sheetStack.field_77994_a = 1;
        te.toggleBySide(flipSide(side), true);
        isSuccessful = true;
      }
      else {

        isSuccessful = false;
      }

      if (isSuccessful)
      {
        itemstack.field_77994_a--;
      }
    }

    return isSuccessful;
  }

  public int flipSide(int side) {
    switch (side) {
      case 0:
        return 1;
      case 1: return 0;
      case 2: return 3;
      case 3: return 2;
      case 4: return 5;
      case 5: return 4;
    }
    return 0;
  }


  public boolean isValid(World world, int i, int j, int k) {
    Block block = world.func_147439_a(i, j, k);
    if (block.isAir((IBlockAccess)world, i, j, k))
      return true;
    if (block == TFCBlocks.metalSheet && world.func_147438_o(i, j, k) instanceof TEMetalSheet) {

      TEMetalSheet te = (TEMetalSheet)world.func_147438_o(i, j, k);
      if (te.metalID == this.metalID)
        return true;
    }
    return false;
  }



  public Metal getMetalType(ItemStack is) {
    if (this.metal == null)
    {
      return MetalRegistry.instance.getMetalFromItem(this);
    }


    return MetalRegistry.instance.getMetalFromString(this.metal);
  }





  public short getMetalReturnAmount(ItemStack is) {
    return this.metalAmount;
  }




  public boolean isSmeltable(ItemStack is) {
    return this.smeltable;
  }




  public ISmeltable.EnumTier getSmeltTier(ItemStack is) {
    return ISmeltable.EnumTier.TierI;
  }




  public int getItemStackLimit(ItemStack is) {
    if (is.func_77942_o()) {

      NBTTagCompound tag = is.func_77978_p();
      if (TFC_ItemHeat.hasTemp(is) || tag.func_74764_b("itemCraftingValue") || tag.func_74764_b("itemCraftingRule1"))
      {
        return 1;
      }
    }

    return super.getItemStackLimit(is);
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Items\ItemMetalSheet.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */