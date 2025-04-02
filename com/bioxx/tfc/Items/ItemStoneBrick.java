package com.bioxx.tfc.Items;

import com.bioxx.tfc.Core.TFCTabs;
import com.bioxx.tfc.api.Constant.Global;
import java.util.List;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;




public class ItemStoneBrick
  extends ItemTerra
{
  private IIcon[] icons;

  public ItemStoneBrick() {
    this.field_77787_bX = true;
    func_77656_e(0);
    func_77637_a(TFCTabs.TFC_MATERIALS);
    this.metaNames = Global.STONE_ALL;
    this.icons = new IIcon[this.metaNames.length];
  }


























  public ItemStoneBrick(int id, String tex) {}

























  public void func_77663_a(ItemStack par1ItemStack, World par2World, Entity par3Entity, int par4, boolean par5) {}

























  public IIcon func_77617_a(int meta) {
    return this.icons[meta];
  }



  public void func_94581_a(IIconRegister registerer) {
    for (int i = 0; i < this.metaNames.length; i++) {
      this.icons[i] = registerer.func_94245_a("terrafirmacraft:rocks/" + this.metaNames[i] + " Brick");
    }
  }



  public void func_150895_a(Item par1, CreativeTabs par2CreativeTabs, List<ItemStack> list) {
    for (int i = 0; i < this.metaNames.length; i++)
      list.add(new ItemStack(this, 1, i));
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Items\ItemStoneBrick.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */