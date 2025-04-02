package com.bioxx.tfc.Blocks.Vanilla;

import com.bioxx.tfc.api.TFCBlocks;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFlowerPot;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFlowerPot;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;















public class BlockCustomFlowerPot
  extends BlockFlowerPot
{
  public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
    ItemStack itemstack = player.field_71071_by.func_70448_g();

    if (itemstack != null) {

      TileEntityFlowerPot teFlowerPot = getTileEntity(world, x, y, z);

      if (teFlowerPot != null) {

        if (teFlowerPot.func_145965_a() != null)
        {
          return false;
        }

        Item item = itemstack.func_77973_b();
        int meta = itemstack.func_77960_j();

        if (validItem(item, meta)) {

          teFlowerPot.func_145964_a(item, meta);
          teFlowerPot.func_70296_d();

          if (!world.func_72921_c(x, y, z, meta, 2))
          {
            world.func_147471_g(x, y, z);
          }

          if (!player.field_71075_bZ.field_75098_d && --itemstack.field_77994_a <= 0)
          {
            player.field_71071_by.func_70299_a(player.field_71071_by.field_70461_c, (ItemStack)null);
          }

          return true;
        }


        return false;
      }



      return false;
    }



    return false;
  }



  private boolean validItem(Item item, int meta) {
    if (item instanceof net.minecraft.item.ItemBlock) {

      Block block = Block.func_149634_a(item);


      if (block == TFCBlocks.cactus || block == TFCBlocks.flora || block == TFCBlocks.flowers || block == TFCBlocks.flowers2 || block == TFCBlocks.fungi || block == TFCBlocks.sapling || block == TFCBlocks.sapling2 || (block == TFCBlocks.tallGrass && meta == 1))
      {
        return true;
      }

      if (block == Blocks.field_150327_N || block == Blocks.field_150328_O || block == Blocks.field_150434_aF || block == Blocks.field_150338_P || block == Blocks.field_150337_Q || block == Blocks.field_150345_g || block == Blocks.field_150330_I || (block == Blocks.field_150329_H && meta == 2))
      {
        return true;
      }
    }


    return false;
  }


  private TileEntityFlowerPot getTileEntity(World world, int x, int y, int z) {
    TileEntity tileentity = world.func_147438_o(x, y, z);
    return (tileentity != null && tileentity instanceof TileEntityFlowerPot) ? (TileEntityFlowerPot)tileentity : null;
  }






  @SideOnly(Side.CLIENT)
  public String func_149702_O() {
    return "terrafirmacraft:flower_pot";
  }



  public Item func_149650_a(int i, Random rand, int j) {
    return Item.func_150898_a(TFCBlocks.flowerPot);
  }



  public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z, EntityPlayer player) {
    TileEntityFlowerPot teFlowerPot = getTileEntity(world, x, y, z);

    if (teFlowerPot != null)
    {
      return new ItemStack(teFlowerPot.func_145965_a(), 1, teFlowerPot.func_145966_b());
    }
    return new ItemStack(TFCBlocks.flowerPot);
  }






  public int func_149645_b() {
    return TFCBlocks.flowerPotRenderId;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Blocks\Vanilla\BlockCustomFlowerPot.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */