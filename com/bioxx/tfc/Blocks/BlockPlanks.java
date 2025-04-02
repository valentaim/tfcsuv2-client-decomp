package com.bioxx.tfc.Blocks;

import com.bioxx.tfc.Core.TFCTabs;
import com.bioxx.tfc.api.Constant.Global;
import com.bioxx.tfc.api.TFCBlocks;
import com.bioxx.tfc.api.Tools.IToolChisel;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;





public class BlockPlanks
  extends BlockTerra
{
  protected String[] woodNames;
  protected IIcon[] icons;

  public BlockPlanks(Material material) {
    super(Material.field_151575_d);
    func_149647_a(TFCTabs.TFC_BUILDING);
    this.woodNames = new String[16];
    System.arraycopy(Global.WOOD_ALL, 0, this.woodNames, 0, 16);
    this.icons = new IIcon[this.woodNames.length];
  }







  @SideOnly(Side.CLIENT)
  public void func_149666_a(Item par1, CreativeTabs par2CreativeTabs, List<ItemStack> list) {
    for (int i = 0; i < this.woodNames.length; i++) {
      list.add(new ItemStack(this, 1, i));
    }
  }


  public int func_149692_a(int j) {
    return j;
  }



  public IIcon func_149691_a(int i, int j) {
    if (j < 0)
      return this.icons[0];
    if (j < this.icons.length)
      return this.icons[j];
    return TFCBlocks.planks2.func_149691_a(i, j - 16);
  }



  public void func_149651_a(IIconRegister registerer) {
    for (int i = 0; i < this.woodNames.length; i++)
      this.icons[i] = registerer.func_94245_a("terrafirmacraft:wood/" + this.woodNames[i] + " Plank");
    Blocks.field_150344_f.func_149651_a(registerer);
  }






  public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer entityplayer, int side, float par7, float par8, float par9) {
    boolean hasHammer = false;
    for (int i = 0; i < 9; i++) {
      if (entityplayer.field_71071_by.field_70462_a[i] != null && entityplayer.field_71071_by.field_70462_a[i].func_77973_b() instanceof com.bioxx.tfc.Items.Tools.ItemHammer)
        hasHammer = true;
    }  if (!world.field_72995_K && entityplayer.func_71045_bC() != null && entityplayer
      .func_71045_bC().func_77973_b() instanceof IToolChisel && hasHammer && ((IToolChisel)entityplayer
      .func_71045_bC().func_77973_b()).canChisel(entityplayer, x, y, z)) {

      Block block = world.func_147439_a(x, y, z);
      byte meta = (byte)world.func_72805_g(x, y, z);

      return ((IToolChisel)entityplayer.func_71045_bC().func_77973_b()).onUsed(world, entityplayer, x, y, z, block, meta, side, par7, par8, par9);
    }
    return false;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Blocks\BlockPlanks.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */