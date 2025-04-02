package com.bioxx.tfc.Items;

import com.bioxx.tfc.Core.TFCTabs;
import java.util.Random;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;




public class ItemHoneyComb
  extends ItemTerra
{
  public ItemHoneyComb() {
    func_77637_a(TFCTabs.TFC_FOODS);
  }


  public void func_94581_a(IIconRegister par1IIconRegister) {
    this.field_77791_bV = par1IIconRegister.func_94245_a("terrafirmacraft:" + this.textureFolder + func_77658_a().replace("item.", ""));
  }



  public ItemStack func_77659_a(ItemStack p_77659_1_, World p_77659_2_, EntityPlayer p_77659_3_) {
    Random r = new Random();

    return null;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Items\ItemHoneyComb.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */