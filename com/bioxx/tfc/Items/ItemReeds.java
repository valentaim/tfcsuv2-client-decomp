package com.bioxx.tfc.Items;

import com.bioxx.tfc.api.TFCBlocks;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemReeds
  extends Item {
  private Block reeds = TFCBlocks.reeds;










  
  public boolean func_77648_a(ItemStack is, EntityPlayer player, World world, int x, int y, int z, int side, float par8, float par9, float par10) {
    Block block = world.func_147439_a(x, y, z);
    
    if (block == Blocks.field_150431_aC && (world.func_72805_g(x, y, z) & 0x7) < 1) {
      
      side = 1;
    }
    else if (block != TFCBlocks.vine && block != TFCBlocks.tallGrass && block != Blocks.field_150330_I) {
      
      if (side == 0) y--; 
      if (side == 1) y++; 
      if (side == 2) z--; 
      if (side == 3) z++; 
      if (side == 4) x--; 
      if (side == 5) x++;
    
    } 
    if (!player.func_82247_a(x, y, z, side, is))
    {
      return false;
    }
    if (is.field_77994_a == 0)
    {
      return false;
    }

    
    if (world.func_147472_a(this.reeds, x, y, z, false, side, (Entity)null, is)) {
      
      int i1 = this.reeds.func_149660_a(world, x, y, z, side, par8, par9, par10, 0);
      if (world.func_147465_d(x, y, z, this.reeds, i1, 3)) {
        
        if (world.func_147439_a(x, y, z) == this.reeds) {
          
          this.reeds.func_149689_a(world, x, y, z, (EntityLivingBase)player, is);
          this.reeds.func_149714_e(world, x, y, z, i1);
        } 
        world.func_72908_a((x + 0.5F), (y + 0.5F), (z + 0.5F), this.reeds.field_149762_H.func_150496_b(), (this.reeds.field_149762_H.func_150497_c() + 1.0F) / 2.0F, this.reeds.field_149762_H.func_150494_d() * 0.8F);
        is.field_77994_a--;
      } 
    } 
    return true;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Items\ItemReeds.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */