package com.bioxx.tfc.Items.Pottery;

import com.bioxx.tfc.Core.TFC_Core;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;





public class ItemPotteryMold
  extends ItemPotteryBase
{
  private IIcon copperIcon;
  private IIcon bronzeIcon;
  private IIcon bismuthBronzeIcon;
  private IIcon blackBronzeIcon;

  public ItemPotteryMold() {
    func_77656_e(401);
  }



  public boolean func_77645_m() {
    return (func_77612_l() > 0);
  }



  public void addItemInformation(ItemStack is, EntityPlayer player, List<String> arraylist) {
    if (is.func_77960_j() > 5) {

      int units = 100 - (is.func_77960_j() - 2) / 4;
      arraylist.add(TFC_Core.translate("gui.units") + ": " + units + " / 100");
    }
  }



  public boolean isDamaged(ItemStack stack) {
    return (stack.func_77960_j() > 5);
  }



  public void func_94581_a(IIconRegister registerer) {
    this.clayIcon = registerer.func_94245_a("terrafirmacraft:" + this.textureFolder + this.metaNames[0]);
    this.ceramicIcon = registerer.func_94245_a("terrafirmacraft:" + this.textureFolder + this.metaNames[1]);
    if (this.metaNames.length > 2) {

      this.copperIcon = registerer.func_94245_a("terrafirmacraft:" + this.textureFolder + this.metaNames[2]);
      this.bronzeIcon = registerer.func_94245_a("terrafirmacraft:" + this.textureFolder + this.metaNames[3]);
      this.bismuthBronzeIcon = registerer.func_94245_a("terrafirmacraft:" + this.textureFolder + this.metaNames[4]);
      this.blackBronzeIcon = registerer.func_94245_a("terrafirmacraft:" + this.textureFolder + this.metaNames[5]);
    }
  }


  public String func_77667_c(ItemStack par1ItemStack) {
    if (par1ItemStack != null && par1ItemStack.func_77960_j() > 5) {

      int damage = (par1ItemStack.func_77960_j() - 2) % 4 + 2;
      return super.func_77667_c(par1ItemStack) + "." + this.metaNames[damage];
    }
    return super.func_77667_c(par1ItemStack);
  }



  public IIcon func_77617_a(int damage) {
    if (damage > 5)
    {
      damage = (damage - 2) % 4 + 2;
    }
    if (damage == 0) return this.clayIcon;
    if (damage == 1) return this.ceramicIcon;
    if (damage == 2) return this.copperIcon;
    if (damage == 3) return this.bronzeIcon;
    if (damage == 4) return this.bismuthBronzeIcon;
    if (damage == 5) return this.blackBronzeIcon;

    return this.clayIcon;
  }



  @SideOnly(Side.CLIENT)
  public void func_150895_a(Item item, CreativeTabs tabs, List<ItemStack> list) {
    list.add(new ItemStack(item, 1, 0));
    list.add(new ItemStack(item, 1, 1));
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Items\Pottery\ItemPotteryMold.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */