package com.bioxx.tfc.Items;

import com.bioxx.tfc.Core.Player.PlayerInfo;
import com.bioxx.tfc.Core.Player.PlayerManagerTFC;
import com.bioxx.tfc.Core.TFCTabs;
import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.TerraFirmaCraft;
import java.util.List;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;






public class ItemLeather
  extends ItemLooseRock
{
  public ItemLeather() {
    func_77637_a(TFCTabs.TFC_MATERIALS);
    this.metaNames = null;
  }




  public ItemStack func_77659_a(ItemStack itemstack, World par2World, EntityPlayer entityplayer) {
    PlayerInfo pi = PlayerManagerTFC.getInstance().getPlayerInfoFromPlayer(entityplayer);
    pi.specialCraftingType = new ItemStack(this.specialCraftingType, 1, itemstack.func_77960_j());

    boolean hasKnife = false;
    for (int i = 0; i < entityplayer.field_71071_by.field_70462_a.length; i++) {

      if (entityplayer.field_71071_by.field_70462_a[i] != null && entityplayer.field_71071_by.field_70462_a[i].func_77973_b() instanceof com.bioxx.tfc.Items.Tools.ItemKnife) {
        hasKnife = true;
      }
    }
    if (hasKnife) {

      if (this.specialCraftingTypeAlternate != null) {
        pi.specialCraftingTypeAlternate = this.specialCraftingTypeAlternate;
      } else {
        pi.specialCraftingTypeAlternate = null;
      }  entityplayer.openGui(TerraFirmaCraft.instance, 28, entityplayer.field_70170_p, (int)entityplayer.field_70165_t, (int)entityplayer.field_70163_u, (int)entityplayer.field_70161_v);
    }
    return itemstack;
  }




  public void addExtraInformation(ItemStack is, EntityPlayer player, List<String> arraylist) {
    if (TFC_Core.showShiftInformation()) {

      arraylist.add(TFC_Core.translate("gui.Help"));
      arraylist.add(TFC_Core.translate("gui.Leather.Inst0"));
    }
    else {

      arraylist.add(TFC_Core.translate("gui.ShowHelp"));
    }
  }





  public void func_77663_a(ItemStack par1ItemStack, World par2World, Entity par3Entity, int par4, boolean par5) {}




  public IIcon func_77617_a(int meta) {
    return this.field_77791_bV;
  }




  public void func_94581_a(IIconRegister registerer) {
    this.field_77791_bV = registerer.func_94245_a("terrafirmacraft:" + this.textureFolder + func_77658_a().replace("item.", ""));
  }



  public void func_150895_a(Item par1, CreativeTabs par2CreativeTabs, List<ItemStack> list) {
    list.add(new ItemStack(this, 1, 0));
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Items\ItemLeather.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */