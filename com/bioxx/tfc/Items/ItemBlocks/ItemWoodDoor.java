package com.bioxx.tfc.Items.ItemBlocks;

import com.bioxx.tfc.Core.TFCTabs;
import com.bioxx.tfc.Items.ItemTerra;
import com.bioxx.tfc.api.Enums.EnumSize;
import com.bioxx.tfc.api.Enums.EnumWeight;
import com.bioxx.tfc.api.TFCBlocks;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;



public class ItemWoodDoor
  extends ItemTerra
{
  private int woodType;

  public ItemWoodDoor(int woodID) {
    this.field_77777_bU = 1;
    func_77637_a(TFCTabs.TFC_DEVICES);
    this.woodType = woodID;
    setFolder("wood/");
  }







  public boolean func_77648_a(ItemStack is, EntityPlayer player, World world, int i, int j, int k, int side, float hitX, float hitY, float hitZ) {
    Block var11;
    if (side != 1)
    {
      return false;
    }


    j++;


    if (this.woodType > -1) {


      var11 = TFCBlocks.doors[this.woodType];
    }
    else {

      var11 = TFCBlocks.wattleDoor;
    }

    if (player.func_82247_a(i, j, k, side, is) && player.func_82247_a(i, j + 1, k, side, is)) {

      if (!var11.func_149742_c(world, i, j, k))
      {
        return false;
      }


      int var12 = MathHelper.func_76128_c(((player.field_70177_z + 180.0F) * 4.0F / 360.0F) - 0.5D) & 0x3;
      placeDoorBlock(world, i, j, k, var12, var11);
      is.field_77994_a--;
      return true;
    }



    return false;
  }




  public static void placeDoorBlock(World par0World, int par1, int par2, int par3, int par4, Block par5Block) {
    byte var6 = 0;
    byte var7 = 0;

    if (par4 == 0)
    {
      var7 = 1;
    }

    if (par4 == 1)
    {
      var6 = -1;
    }

    if (par4 == 2)
    {
      var7 = -1;
    }

    if (par4 == 3)
    {
      var6 = 1;
    }

    int var8 = (par0World.func_147439_a(par1 - var6, par2, par3 - var7).func_149721_r() ? 1 : 0) + (par0World.func_147439_a(par1 - var6, par2 + 1, par3 - var7).func_149721_r() ? 1 : 0);
    int var9 = (par0World.func_147439_a(par1 + var6, par2, par3 + var7).func_149721_r() ? 1 : 0) + (par0World.func_147439_a(par1 + var6, par2 + 1, par3 + var7).func_149721_r() ? 1 : 0);
    boolean var10 = (par0World.func_147439_a(par1 - var6, par2, par3 - var7) == par5Block || par0World.func_147439_a(par1 - var6, par2 + 1, par3 - var7) == par5Block);
    boolean var11 = (par0World.func_147439_a(par1 + var6, par2, par3 + var7) == par5Block || par0World.func_147439_a(par1 + var6, par2 + 1, par3 + var7) == par5Block);
    boolean var12 = false;

    if (var10 && !var11) {

      var12 = true;
    }
    else if (var9 > var8) {

      var12 = true;
    }

    par0World.func_147465_d(par1, par2, par3, par5Block, par4, 2);
    par0World.func_147465_d(par1, par2 + 1, par3, par5Block, 0x8 | (var12 ? 1 : 0), 2);
    par0World.func_147459_d(par1, par2, par3, par5Block);
    par0World.func_147459_d(par1, par2 + 1, par3, par5Block);
  }


  public EnumSize getSize(ItemStack is) {
    return EnumSize.HUGE;
  }


  public EnumWeight getWeight(ItemStack is) {
    return EnumWeight.HEAVY;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Items\ItemBlocks\ItemWoodDoor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */