package com.bioxx.tfc.Items.Tools;

import com.bioxx.tfc.api.TFCItems;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;



public class ItemSteelBucketRed
  extends ItemSteelBucket
{
  public ItemSteelBucketRed(Block par2) {
    super(par2);
  }



  public void func_94581_a(IIconRegister registerer) {
    String name = func_77658_a().replace("item.", "");
    name = name.replace("Salt ", "");
    this.field_77791_bV = registerer.func_94245_a("terrafirmacraft:" + this.textureFolder + name);
  }



  public boolean func_77648_a(ItemStack is, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
    if (this.bucketContents != Blocks.field_150350_a && world.func_147439_a(x, y, z) == Blocks.field_150383_bp) {

      int meta = world.func_72805_g(x, y, z);
      if (meta < 3) {

        if (!player.field_71075_bZ.field_75098_d)
        {
          player.func_70062_b(0, new ItemStack(TFCItems.redSteelBucketEmpty));
        }
        world.func_72921_c(x, y, z, MathHelper.func_76125_a(3, 0, 3), 2);
        world.func_147453_f(x, y, z, (Block)Blocks.field_150383_bp);

        return true;
      }
    }

    return false;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Items\Tools\ItemSteelBucketRed.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */