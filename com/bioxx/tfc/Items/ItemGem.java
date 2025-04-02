package com.bioxx.tfc.Items;

import java.util.List;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;



public class ItemGem
  extends ItemTerra
{
  private IIcon[] icons = new IIcon[5];


  public ItemGem() {
    func_77656_e(0);
    func_77627_a(true);
    this.metaNames = new String[] { "Chipped", "Flawed", "Normal", "Flawless", "Exquisite" };
  }



  public void func_150895_a(Item par1, CreativeTabs par2CreativeTabs, List<ItemStack> list) {
    list.add(new ItemStack(this, 1, 0));
    list.add(new ItemStack(this, 1, 1));
    list.add(new ItemStack(this, 1, 2));
    list.add(new ItemStack(this, 1, 3));
    list.add(new ItemStack(this, 1, 4));
  }



  public IIcon func_77617_a(int i) {
    return this.icons[i];
  }



  public void func_94581_a(IIconRegister registerer) {
    this.icons[0] = registerer.func_94245_a("terrafirmacraft:gems/" + this.metaNames[0] + " " + func_77658_a().replace("item.", ""));
    this.icons[1] = registerer.func_94245_a("terrafirmacraft:gems/" + this.metaNames[1] + " " + func_77658_a().replace("item.", ""));
    this.icons[2] = registerer.func_94245_a("terrafirmacraft:gems/" + this.metaNames[2] + " " + func_77658_a().replace("item.", ""));
    this.icons[3] = registerer.func_94245_a("terrafirmacraft:gems/" + this.metaNames[3] + " " + func_77658_a().replace("item.", ""));
    this.icons[4] = registerer.func_94245_a("terrafirmacraft:gems/" + this.metaNames[4] + " " + func_77658_a().replace("item.", ""));
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Items\ItemGem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */