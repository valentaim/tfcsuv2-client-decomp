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


public class BlockOre2
  extends BlockOre
{
  public BlockOre2(Material mat) {
    super(mat);
    this.blockNames = Global.ORE_MINERAL;
  }



  public int func_149692_a(int dmg) {
    return dmg + Global.ORE_METAL.length;
  }



  public int quantityDropped(int meta, int fortune, Random random) {
    if (meta == 13)
      return 1 + random.nextInt(3);
    return 1;
  }



  public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
    ArrayList<ItemStack> ret = new ArrayList<>();

    int count = quantityDropped(metadata, fortune, world.field_73012_v);
    for (int i = 0; i < count; i++) {

      ItemStack itemstack = new ItemStack(TFCItems.oreChunk, 1, func_149692_a(metadata));

      if (metadata == 5) {
        itemstack = kimberliteGemSpawn();
      } else if (metadata == 13) {
        itemstack = new ItemStack(TFCItems.powder, 1, 4);
      }
      if (itemstack != null)
      {
        ret.add(itemstack);
      }
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
        Random random = new Random();

        ItemStack itemstack = new ItemStack(TFCItems.oreChunk, 1, func_149692_a(meta));

        if (meta == 5) {
          itemstack = kimberliteGemSpawn();
        } else if (meta == 13) {
          itemstack = new ItemStack(TFCItems.powder, 1 + random.nextInt(3), 4);
        }
        if (itemstack != null) {
          func_149642_a(world, x, y, z, itemstack);
        }
      }
    }
    return world.func_147468_f(x, y, z);
  }


  public ItemStack kimberliteGemSpawn() {
    int quality = 0;
    Random random = new Random();
    if (random.nextInt(50) == 0) {
      quality = 1;
    } else if (random.nextInt(75) == 0) {
      quality = 2;
    } else if (random.nextInt(150) == 0) {
      quality = 3;
    } else if (random.nextInt(300) == 0) {
      quality = 4;
    }
    return new ItemStack(TFCItems.gemDiamond, 1, quality);
  }
















































  public void onBlockExploded(World world, int x, int y, int z, Explosion exp) {
    Random random = new Random();

    int meta = world.func_72805_g(x, y, z);
    ItemStack itemstack = new ItemStack(TFCItems.oreChunk, 1, meta + 16);

    if (meta == 5) {
      itemstack = kimberliteGemSpawn();
    } else if (meta == 13) {
      itemstack = new ItemStack(TFCItems.powder, 1 + random.nextInt(3), 4);
    }
    if (itemstack != null) {
      func_149642_a(world, x, y, z, itemstack);
    }
    func_149723_a(world, x, y, z, exp);
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Blocks\Terrain\BlockOre2.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */