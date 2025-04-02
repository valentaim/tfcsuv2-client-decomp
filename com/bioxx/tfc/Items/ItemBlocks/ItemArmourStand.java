package com.bioxx.tfc.Items.ItemBlocks;

import com.bioxx.tfc.Entities.EntityStand;
import com.bioxx.tfc.api.Constant.Global;
import com.bioxx.tfc.api.Enums.EnumSize;
import com.bioxx.tfc.api.Enums.EnumWeight;
import com.bioxx.tfc.api.TFCBlocks;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;


public class ItemArmourStand
  extends ItemTerraBlock
{
  public ItemArmourStand(Block i) {
    super(i);
    this.metaNames = new String[16];
    System.arraycopy(Global.WOOD_ALL, 0, this.metaNames, 0, 16);
  }


  public boolean isValid(World world, int i, int j, int k) {
    return (world.func_72872_a(Entity.class, AxisAlignedBB.func_72330_a(i, j - 0.1D, k, (i + 1), (j + 2), (k + 1))).size() == 0);
  }


  protected boolean createStand(ItemStack itemstack, EntityPlayer entityplayer, World world, int x, int y, int z, int side, int l) {
    int dir = MathHelper.func_76128_c((entityplayer.field_70177_z * 4.0F / 360.0F) + 0.5D) & 0x3;
    if (side == 1 && world.func_147437_c(x, y + 1, z) && isValid(world, x, y + 1, z) && world.func_147445_c(x, y, z, false)) {

      EntityStand es = new EntityStand(world, (dir * 90 - 180), itemstack.func_77960_j() + getOffset());
      es.func_70012_b(x + 0.5D, (y + 1), z + 0.5D, (dir * 90 - 180), 0.0F);
      world.func_72838_d((Entity)es);
      return true;
    }


    return false;
  }



  protected int getOffset() {
    return 0;
  }




  public boolean placeBlockAt(ItemStack itemstack, EntityPlayer entityplayer, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int metadata) {
    if (!world.field_72995_K) {

      int dir = MathHelper.func_76128_c((entityplayer.field_70177_z * 4.0F / 360.0F) + 0.5D) & 0x3;
      if (createStand(itemstack, entityplayer, world, x, y - 1, z, side, dir)) {
        world.func_72908_a(x + 0.5D, y + 0.5D, z + 0.5D, TFCBlocks.logNatural.field_149762_H.func_150496_b(), (TFCBlocks.logNatural.field_149762_H.func_150497_c() + 1.0F) / 2.0F, TFCBlocks.logNatural.field_149762_H.func_150494_d() * 0.8F);
      }
      return true;
    }
    return false;
  }



  public void func_94581_a(IIconRegister registerer) {}



  public EnumSize getSize(ItemStack is) {
    return EnumSize.VERYLARGE;
  }


  public EnumWeight getWeight(ItemStack is) {
    return EnumWeight.HEAVY;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Items\ItemBlocks\ItemArmourStand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */