package com.bioxx.tfc.Items;

import com.bioxx.tfc.Core.Player.PlayerInfo;
import com.bioxx.tfc.Core.Player.PlayerManagerTFC;
import com.bioxx.tfc.Core.TFCTabs;
import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.TerraFirmaCraft;
import com.bioxx.tfc.api.Constant.Global;
import java.util.List;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;





public class ItemLooseRock
  extends ItemTerra
{
  protected IIcon[] icons;
  protected Item specialCraftingType;
  protected ItemStack specialCraftingTypeAlternate;

  public ItemLooseRock() {
    this.field_77787_bX = true;
    func_77656_e(0);
    func_77637_a(TFCTabs.TFC_MATERIALS);
    this.metaNames = Global.STONE_ALL;
    this.icons = new IIcon[this.metaNames.length];
  }



  public ItemTerra setMetaNames(String[] metanames) {
    this.metaNames = (String[])metanames.clone();
    if (this.metaNames != null)
      this.icons = new IIcon[this.metaNames.length];
    return this;
  }


  public ItemTerra setSpecialCraftingType(Item i) {
    this.specialCraftingType = i;
    return this;
  }


  public ItemTerra setSpecialCraftingType(Item i, Item j) {
    this.specialCraftingType = i;
    this.specialCraftingTypeAlternate = new ItemStack(j);
    return this;
  }


  public ItemTerra setSpecialCraftingType(Item i, ItemStack is) {
    this.specialCraftingType = i;
    this.specialCraftingTypeAlternate = is;
    return this;
  }


  public Item getSpecialCraftingType() {
    return this.specialCraftingType;
  }


  public ItemStack getSpecialCraftingTypeAlternate() {
    return this.specialCraftingTypeAlternate;
  }



  public ItemStack func_77659_a(ItemStack is, World world, EntityPlayer player) {
    PlayerInfo pi = PlayerManagerTFC.getInstance().getPlayerInfoFromPlayer(player);
    pi.specialCraftingType = new ItemStack(this.specialCraftingType, 1, is.func_77960_j());
    if (this.specialCraftingTypeAlternate != null) {
      pi.specialCraftingTypeAlternate = this.specialCraftingTypeAlternate;
    } else {
      pi.specialCraftingTypeAlternate = null;
    }
    if (is.field_77994_a > 1)
    {
      player.openGui(TerraFirmaCraft.instance, 28, player.field_70170_p, (int)player.field_70165_t, (int)player.field_70163_u, (int)player.field_70161_v);
    }
    return is;
  }




  public void addExtraInformation(ItemStack is, EntityPlayer player, List<String> arraylist) {
    if (TFC_Core.showShiftInformation()) {

      arraylist.add(TFC_Core.translate("gui.Help"));
      arraylist.add(TFC_Core.translate("gui.LooseRock.Inst0"));
    }
    else {

      arraylist.add(TFC_Core.translate("gui.ShowHelp"));
    }
  }





  public void func_77663_a(ItemStack is, World world, Entity entity, int par4, boolean par5) {}




  public IIcon func_77617_a(int meta) {
    return this.icons[meta];
  }




  public void func_94581_a(IIconRegister registerer) {
    for (int i = 0; i < this.metaNames.length; i++) {
      this.icons[i] = registerer.func_94245_a("terrafirmacraft:rocks/" + this.metaNames[i] + " Rock");
    }
  }


  public void func_150895_a(Item item, CreativeTabs tabs, List<ItemStack> list) {
    for (int i = 0; i < this.metaNames.length; i++)
    {
      list.add(new ItemStack(this, 1, i));
    }
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Items\ItemLooseRock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */