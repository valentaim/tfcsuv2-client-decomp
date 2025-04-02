package com.bioxx.tfc.Blocks.Terrain;

import com.bioxx.tfc.Blocks.BlockTerra;
import com.bioxx.tfc.api.Tools.IToolChisel;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;


public class BlockSmooth
  extends BlockTerra
{
  protected String[] names;
  protected IIcon[] icons;

  protected BlockSmooth(Material material) {
    super(material);
  }









  @SideOnly(Side.CLIENT)
  public void func_149666_a(Item par1, CreativeTabs par2CreativeTabs, List<ItemStack> list) {
    for (int i = 0; i < this.names.length; i++) {
      list.add(new ItemStack((Block)this, 1, i));
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
      this.icons[i] = iconRegisterer.func_94245_a("terrafirmacraft:rocks/" + this.names[i] + " Smooth");
    }
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
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Blocks\Terrain\BlockSmooth.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */