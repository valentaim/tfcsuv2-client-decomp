package com.bioxx.tfc.Items;

import com.bioxx.tfc.Core.Player.PlayerInfo;
import com.bioxx.tfc.Core.Player.PlayerManagerTFC;
import com.bioxx.tfc.Core.TFCTabs;
import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.TerraFirmaCraft;
import java.util.List;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;





public class ItemClay
  extends ItemLooseRock
{
  public ItemClay() {
    func_77637_a(TFCTabs.TFC_POTTERY);
    this.icons = new IIcon[2];
  }




  public ItemStack func_77659_a(ItemStack itemstack, World world, EntityPlayer entityplayer) {
    if (itemstack.field_77994_a >= 5) {

      PlayerInfo pi = PlayerManagerTFC.getInstance().getPlayerInfoFromPlayer(entityplayer);
      pi.specialCraftingType = new ItemStack(this.specialCraftingType, 1, 0);

      if (this.specialCraftingTypeAlternate != null) {
        pi.specialCraftingTypeAlternate = this.specialCraftingTypeAlternate;
      }
      if (itemstack.func_77960_j() == 1) {

        pi.specialCraftingType = new ItemStack(this.specialCraftingType, 1, 2);
        pi.specialCraftingTypeAlternate = new ItemStack(this.specialCraftingType, 1, 3);
      }

      entityplayer.openGui(TerraFirmaCraft.instance, 28, entityplayer.field_70170_p, (int)entityplayer.field_70165_t, (int)entityplayer.field_70163_u, (int)entityplayer.field_70161_v);
    }
    return itemstack;
  }




  public void addExtraInformation(ItemStack is, EntityPlayer player, List<String> arraylist) {
    if (TFC_Core.showShiftInformation()) {

      arraylist.add(TFC_Core.translate("gui.Help"));
      arraylist.add(TFC_Core.translate("gui.Clay.Inst0"));
    }
    else {

      arraylist.add(TFC_Core.translate("gui.ShowHelp"));
    }
  }



  public IIcon func_77617_a(int meta) {
    return this.icons[meta];
  }



  public void func_94581_a(IIconRegister registerer) {
    this.icons[0] = registerer.func_94245_a("terrafirmacraft:Clay");
    this.icons[1] = registerer.func_94245_a("terrafirmacraft:Fire Clay");
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Items\ItemClay.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */