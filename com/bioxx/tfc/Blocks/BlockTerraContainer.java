package com.bioxx.tfc.Blocks;

import com.bioxx.tfc.TerraFirmaCraft;
import com.bioxx.tfc.api.TFCOptions;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public abstract class BlockTerraContainer
  extends BlockContainer
{
  public BlockTerraContainer() {
    super(Material.field_151576_e);
  }


  public BlockTerraContainer(Material material) {
    super(material);
  }




  public void func_149689_a(World world, int i, int j, int k, EntityLivingBase entityliving, ItemStack is) {
    if (TFCOptions.enableDebugMode && world.field_72995_K) {

      int metadata = world.func_72805_g(i, j, k);
      TerraFirmaCraft.LOG.info("Meta=" + func_149739_a() + ":" + metadata);
    }
  }



  public boolean canBeReplacedByLeaves(IBlockAccess w, int x, int y, int z) {
    return false;
  }



  public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer entityplayer, int par6, float par7, float par8, float par9) {
    if (TFCOptions.enableDebugMode && world.field_72995_K) {

      int metadata = world.func_72805_g(x, y, z);
      TerraFirmaCraft.LOG.info("Meta = " + func_149739_a() + ":" + metadata);
    }
    return false;
  }



  public TileEntity func_149915_a(World var1, int var2) {
    return null;
  }



  public void func_149749_a(World world, int x, int y, int z, Block block, int metadata) {
    TileEntity te = world.func_147438_o(x, y, z);
    if (te != null)
    {
      if (te instanceof IInventory)
      {
        for (int i = 0; i < ((IInventory)te).func_70302_i_(); i++) {

          if (((IInventory)te).func_70301_a(i) != null) {

            EntityItem ei = new EntityItem(world, x + 0.5D, y + 0.5D, z + 0.5D, ((IInventory)te).func_70301_a(i));
            ei.field_70159_w = 0.0D;
            ei.field_70181_x = 0.0D;
            ei.field_70179_y = 0.0D;
            world.func_72838_d((Entity)ei);
          }
        }
      }
    }
    super.func_149749_a(world, x, y, z, block, metadata);
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Blocks\BlockTerraContainer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */