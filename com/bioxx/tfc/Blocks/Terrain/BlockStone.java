package com.bioxx.tfc.Blocks.Terrain;

import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.api.TFCItems;
import com.bioxx.tfc.api.Tools.IToolChisel;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fof.tfcsu.utils.ExpBonus;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public abstract class BlockStone extends BlockCollapsible {
  protected String[] names;
  public IIcon[] icons;
  protected int looseStart;
  protected int gemChance;

  public BlockStone(Material material) {
    super(material);
  }











  @SideOnly(Side.CLIENT)
  public void func_149666_a(Item par1, CreativeTabs par2CreativeTabs, List<ItemStack> par3List) {
    for (int i = 0; i < this.names.length; i++) {
      par3List.add(new ItemStack(par1, 1, i));
    }
  }





  public int func_149692_a(int i) {
    return i;
  }



  public IIcon func_149691_a(int i, int j) {
    if ((j & 0x7) >= this.icons.length)
      return this.icons[0];
    return this.icons[j & 0x7];
  }



  public void func_149651_a(IIconRegister iconRegisterer) {
    for (int i = 0; i < this.names.length; i++) {
      this.icons[i] = iconRegisterer.func_94245_a("terrafirmacraft:rocks/" + this.names[i] + " Raw");
    }
  }


  public Item func_149650_a(int i, Random random, int j) {
    return TFCItems.looseRock;
  }






  public int func_149745_a(Random rand) {
    return rand.nextInt(2) + 1;
  }



  public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
    ArrayList<ItemStack> ret = new ArrayList<>();
    int meta = this.looseStart + metadata;

    int count = func_149745_a(world.field_73012_v);
    for (int i = 0; i < count; i++) {

      Item item = func_149650_a(meta, world.field_73012_v, fortune);
      if (item != null)
      {
        ret.add(new ItemStack(item, 1, func_149692_a(meta)));
      }
    }

    ItemStack gemStack = TFC_Core.randomGem(world.field_73012_v, this.gemChance);
    if (gemStack != null) {
      ret.add(gemStack);
    }
    return ret;
  }



  public void func_149723_a(World world, int i, int j, int k, Explosion ex) {
    if (!world.field_72995_K) {


      Random random = new Random();

      ItemStack is = null;

      is = TFC_Core.randomGem(random, 0);

      if (is != null) {

        EntityItem item = new EntityItem(world, i, j, k, is);
        world.func_72838_d((Entity)item);
      }
    }
  }




  public void onBlockExploded(World world, int x, int y, int z, Explosion explosion) {
    if (world.field_73012_v.nextInt(100) < 30) {


      world.func_147465_d(x, y, z, this.dropBlock, world.func_72805_g(x, y, z) + 8, 2);
    }
    else {

      super.onBlockExploded(world, x, y, z, explosion);
    }
  }



  public void func_149695_a(World world, int i, int j, int k, Block l) {
    dropCarvedStone(world, i, j, k);
  }



  public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer entityplayer, int side, float par7, float par8, float par9) {
    boolean hasHammer = false;
    for (int i = 0; i < 9; i++) {
      if (entityplayer.field_71071_by.field_70462_a[i] != null && entityplayer.field_71071_by.field_70462_a[i].func_77973_b() instanceof com.bioxx.tfc.Items.Tools.ItemHammer)
        hasHammer = true;
    }
    if (entityplayer.func_71045_bC() != null && entityplayer.func_71045_bC().func_77973_b() instanceof IToolChisel && hasHammer && !world.field_72995_K && ((IToolChisel)entityplayer
      .func_71045_bC().func_77973_b()).canChisel(entityplayer, x, y, z)) {

      Block id = world.func_147439_a(x, y, z);
      byte meta = (byte)world.func_72805_g(x, y, z);
      return ((IToolChisel)entityplayer.func_71045_bC().func_77973_b()).onUsed(world, entityplayer, x, y, z, id, meta, side, par7, par8, par9);
    }
    return false;
  }



  public void func_149636_a(World world, EntityPlayer entityplayer, int i, int j, int k, int l) {
    func_149642_a(world, i, j, k, new ItemStack(TFCItems.looseRock, world.field_73012_v.nextInt(2) + 1, l + this.looseStart));

    super.func_149636_a(world, entityplayer, i, j, k, l);
  }



  public void func_149664_b(World world, int i, int j, int k, int l) {
    if (!world.field_72995_K) {

      Random random = new Random();
      ItemStack is = null;

      is = TFC_Core.randomGem(random, this.gemChance);

      if (is != null) {

        EntityItem item = new EntityItem(world, i, j, k, is);
        world.func_72838_d((Entity)item);
        EntityPlayer p = world.func_72890_a((Entity)item, 5.0D);
        if (p != null) ExpBonus.FIND_GEM.give(p);
      }
    }
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Blocks\Terrain\BlockStone.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */