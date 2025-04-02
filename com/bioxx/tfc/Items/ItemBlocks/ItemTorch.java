package com.bioxx.tfc.Items.ItemBlocks;

import com.bioxx.tfc.TileEntities.TELogPile;
import com.bioxx.tfc.TileEntities.TEPottery;
import com.bioxx.tfc.api.TFCBlocks;
import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;


public class ItemTorch
  extends ItemTerraBlock
{
  public ItemTorch(Block b) {
    super(b);
  }



  public boolean onEntityItemUpdate(EntityItem entityItem) {
    if (entityItem.field_70170_p.func_147439_a((int)Math.floor(entityItem.field_70165_t), (int)Math.floor(entityItem.field_70163_u) - 1, (int)Math.floor(entityItem.field_70161_v)) == TFCBlocks.logPile) {

      int count = entityItem.getEntityData().func_74762_e("torchCount");
      if (count > 160) {

        TELogPile te = (TELogPile)entityItem.field_70170_p.func_147438_o((int)Math.floor(entityItem.field_70165_t), (int)Math.floor(entityItem.field_70163_u) - 1, (int)Math.floor(entityItem.field_70161_v));
        te.activateCharcoal();
        te.lightNeighbors();
        entityItem.func_70106_y();
      }
      else {

        if (entityItem.field_70170_p.field_73012_v.nextInt(10) < 2)
          entityItem.field_70170_p.func_72869_a("lava", entityItem.field_70165_t, entityItem.field_70163_u, entityItem.field_70161_v, (-0.5F + entityItem.field_70170_p.field_73012_v.nextFloat()), (-0.5F + entityItem.field_70170_p.field_73012_v.nextFloat()), (-0.5F + entityItem.field_70170_p.field_73012_v.nextFloat()));
        entityItem.getEntityData().func_74768_a("torchCount", count + 1);
      }
    }
    if (entityItem.field_70170_p.func_147439_a((int)Math.floor(entityItem.field_70165_t), (int)Math.floor(entityItem.field_70163_u) - 1, (int)Math.floor(entityItem.field_70161_v)) == TFCBlocks.pottery) {

      int count = entityItem.getEntityData().func_74762_e("torchCount");
      if (count > 80) {

        TEPottery tepot = (TEPottery)entityItem.field_70170_p.func_147438_o((int)Math.floor(entityItem.field_70165_t), (int)Math.floor(entityItem.field_70163_u) - 1, (int)Math.floor(entityItem.field_70161_v));
        if (!entityItem.field_70170_p.field_72995_K && tepot.wood == 8 && tepot.burnStart == 0L) {
          tepot.startPitFire();
        }
      } else {

        if (entityItem.field_70170_p.field_73012_v.nextInt(10) < 2)
          entityItem.field_70170_p.func_72869_a("lava", entityItem.field_70165_t, entityItem.field_70163_u, entityItem.field_70161_v, (-0.5F + entityItem.field_70170_p.field_73012_v.nextFloat()), (-0.5F + entityItem.field_70170_p.field_73012_v.nextFloat()), (-0.5F + entityItem.field_70170_p.field_73012_v.nextFloat()));
        entityItem.getEntityData().func_74768_a("torchCount", count + 1);
      }
    }
    return false;
  }



  public boolean func_77648_a(ItemStack is, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
    int xCoord = x, yCoord = y, zCoord = z;
    if (side == 0) yCoord--;
    if (side == 1) yCoord++;
    if (side == 2) zCoord--;
    if (side == 3) zCoord++;
    if (side == 4) xCoord--;
    if (side == 5) xCoord++;
    Block block = world.func_147439_a(xCoord, yCoord, zCoord);
    if (block != TFCBlocks.torch && block != TFCBlocks.torchOff) {
      return super.func_77648_a(is, player, world, x, y, z, side, hitX, hitY, hitZ);
    }
    return false;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Items\ItemBlocks\ItemTorch.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */