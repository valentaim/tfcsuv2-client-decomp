package com.bioxx.tfc.Items.ItemBlocks;

import com.bioxx.tfc.api.Constant.Global;
import com.bioxx.tfc.api.TFCBlocks;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;



public class ItemFungi
  extends ItemTerraBlock
{
  public ItemFungi(Block b) {
    super(b);
    this.metaNames = Global.FUNGI_META_NAMES;
  }



  @SideOnly(Side.CLIENT)
  public IIcon func_77617_a(int par1) {
    return TFCBlocks.fungi.func_149691_a(0, par1);
  }






  public ItemStack func_77659_a(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
    MovingObjectPosition movingobjectposition = func_77621_a(par2World, par3EntityPlayer, true);

    if (movingobjectposition == null)
    {
      return par1ItemStack;
    }


    if (movingobjectposition.field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK) {

      int i = movingobjectposition.field_72311_b;
      int j = movingobjectposition.field_72312_c;
      int k = movingobjectposition.field_72309_d;

      if (!par2World.func_72962_a(par3EntityPlayer, i, j, k)) {
        return par1ItemStack;
      }
      if (!par3EntityPlayer.func_82247_a(i, j, k, movingobjectposition.field_72310_e, par1ItemStack)) {
        return par1ItemStack;
      }
      if (TFCBlocks.fungi.func_149718_j(par2World, i, j + 1, k) && par2World.func_147437_c(i, j + 1, k)) {

        par2World.func_147465_d(i, j + 1, k, TFCBlocks.fungi, par1ItemStack.func_77960_j(), 3);
        par2World.func_72908_a((i + 0.5F), (j + 0.5F), (k + 0.5F), TFCBlocks.fungi.field_149762_H.func_150496_b(), (TFCBlocks.fungi.field_149762_H.func_150497_c() + 1.0F) / 2.0F, TFCBlocks.fungi.field_149762_H.func_150494_d() * 0.8F);
        if (!par3EntityPlayer.field_71075_bZ.field_75098_d) par1ItemStack.field_77994_a--;
      }
    }
    return par1ItemStack;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Items\ItemBlocks\ItemFungi.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */