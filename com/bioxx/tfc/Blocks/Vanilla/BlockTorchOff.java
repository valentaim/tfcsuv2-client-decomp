package com.bioxx.tfc.Blocks.Vanilla;

import com.bioxx.tfc.Core.TFC_Time;
import com.bioxx.tfc.TileEntities.TELightEmitter;
import com.bioxx.tfc.api.TFCBlocks;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.Random;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;





public class BlockTorchOff
  extends BlockTorch
{
  public BlockTorchOff() {
    func_149647_a(null);
    func_149675_a(false);
    func_149715_a(0.0F);
  }



  public int getLightValue(IBlockAccess world, int x, int y, int z) {
    return 0;
  }



  @SideOnly(Side.CLIENT)
  public IIcon func_149691_a(int side, int meta) {
    return this.offIcon;
  }



  public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
    if (!world.field_72995_K)
    {
      if (player.field_71071_by.func_70448_g() != null && player.field_71071_by
        .func_70448_g().func_77973_b() == Item.func_150898_a(TFCBlocks.torch)) {

        int meta = world.func_72805_g(x, y, z);
        world.func_147465_d(x, y, z, TFCBlocks.torch, meta, 3);
        if (world.func_147438_o(x, y, z) instanceof TELightEmitter) {

          TELightEmitter te = (TELightEmitter)world.func_147438_o(x, y, z);
          te.hourPlaced = (int)TFC_Time.getTotalHours();
        }
      }
    }
    return true;
  }




  public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
    return new ArrayList<>();
  }

  @SideOnly(Side.CLIENT)
  public void func_149734_b(World world, int x, int y, int z, Random rand) {}
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Blocks\Vanilla\BlockTorchOff.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */