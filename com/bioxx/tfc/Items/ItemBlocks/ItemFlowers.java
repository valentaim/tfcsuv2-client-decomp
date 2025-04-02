package com.bioxx.tfc.Items.ItemBlocks;

import com.bioxx.tfc.api.Enums.EnumWeight;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;



public class ItemFlowers
  extends ItemTerraBlock
{
  public ItemFlowers(Block b) {
    super(b);
    this.metaNames = new String[] { "flower_dandelion", "flower_nasturtium", "flower_meads_milkweed", "flower_tropical_milkweed", "flower_butterfly_milkweed", "flower_calendula" };
  }


  
  @SideOnly(Side.CLIENT)
  public IIcon func_77617_a(int par1) {
    return this.field_150939_a.func_149691_a(0, par1);
  }


  
  public EnumWeight getWeight(ItemStack is) {
    return EnumWeight.LIGHT;
  }





  
  public ItemStack func_77659_a(ItemStack is, World world, EntityPlayer player) {
    MovingObjectPosition mop = func_77621_a(world, player, true);
    
    if (mop == null)
    {
      return is;
    }

    
    if (mop.field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK) {
      
      int i = mop.field_72311_b;
      int j = mop.field_72312_c;
      int k = mop.field_72309_d;
      
      if (!world.func_72962_a(player, i, j, k)) {
        return is;
      }
      if (!player.func_82247_a(i, j, k, mop.field_72310_e, is)) {
        return is;
      }
      if (this.field_150939_a.func_149718_j(world, i, j + 1, k) && world.func_147437_c(i, j + 1, k)) {
        
        world.func_147465_d(i, j + 1, k, this.field_150939_a, is.func_77960_j(), 3);
        world.func_72908_a((i + 0.5F), (j + 0.5F), (k + 0.5F), this.field_150939_a.field_149762_H.func_150496_b(), (this.field_150939_a.field_149762_H.func_150497_c() + 1.0F) / 2.0F, this.field_150939_a.field_149762_H.func_150494_d() * 0.8F);
        if (!player.field_71075_bZ.field_75098_d) is.field_77994_a--; 
      } 
    } 
    return is;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Items\ItemBlocks\ItemFlowers.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */