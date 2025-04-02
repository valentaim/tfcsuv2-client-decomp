package com.bioxx.tfc.Blocks.Terrain;

import com.bioxx.tfc.api.Constant.Global;
import com.bioxx.tfc.api.TFCItems;
import java.util.ArrayList;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;


public class BlockOre3
  extends BlockOre
{
  public BlockOre3(Material material) {
    super(material);
    this.blockNames = Global.ORE_MINERAL2;
  }



  public int func_149692_a(int dmg) {
    return dmg + Global.ORE_METAL.length + Global.ORE_MINERAL.length;
  }



  public int quantityDropped(int meta, int fortune, Random random) {
    return 1;
  }



  public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
    ArrayList<ItemStack> ret = new ArrayList<>();

    int count = quantityDropped(metadata, fortune, world.field_73012_v);
    for (int i = 0; i < count; i++) {

      ItemStack itemstack = new ItemStack(TFCItems.oreChunk, 1, func_149692_a(metadata));
      ret.add(itemstack);
    }
    return ret;
  }



  public boolean removedByPlayer(World world, EntityPlayer player, int x, int y, int z) {
    if (!world.field_72995_K) {

      boolean dropOres = false;
      if (player != null) {

        player.func_71064_a(StatList.field_75934_C[func_149682_b((Block)this)], 1);
        player.func_71020_j(0.075F);
        dropOres = player.func_146099_a((Block)this);
      }

      if (player == null || dropOres) {

        int meta = world.func_72805_g(x, y, z);
        ItemStack itemstack = new ItemStack(TFCItems.oreChunk, 1, func_149692_a(meta));
        func_149642_a(world, x, y, z, itemstack);
      }
    }
    return world.func_147468_f(x, y, z);
  }

















































  public void onBlockExploded(World world, int x, int y, int z, Explosion exp) {
    int meta = world.func_72805_g(x, y, z);
    ItemStack itemstack = new ItemStack(TFCItems.oreChunk, 1, meta + 32);
    func_149642_a(world, x, y, z, itemstack);
    func_149723_a(world, x, y, z, exp);
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Blocks\Terrain\BlockOre3.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */