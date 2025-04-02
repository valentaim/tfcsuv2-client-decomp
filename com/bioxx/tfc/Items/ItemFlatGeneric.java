package com.bioxx.tfc.Items;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;



public class ItemFlatGeneric
  extends ItemTerra
{
  public IIcon[] icons;

  public ItemFlatGeneric() {
    this.field_77787_bX = false;
    func_77656_e(0);
    this.field_77777_bU = 25;
    func_77637_a(null);
  }




  public ItemFlatGeneric(int id, String tex) {}




  public void func_77663_a(ItemStack par1ItemStack, World par2World, Entity par3Entity, int par4, boolean par5) {
    ((EntityPlayer)par3Entity).field_71071_by.func_70299_a(par4, null);
  }



  public boolean onDroppedByPlayer(ItemStack item, EntityPlayer player) {
    return false;
  }



  public void func_94581_a(IIconRegister registerer) {
    if (this.metaNames == null) {
      this.field_77791_bV = registerer.func_94245_a("terrafirmacraft:" + this.textureFolder + func_77658_a().replace("item.", ""));
    } else {

      this.icons = new IIcon[this.metaNames.length];
      for (int i = 0; i < this.metaNames.length; i++)
      {
        this.icons[i] = registerer.func_94245_a("terrafirmacraft:" + this.textureFolder + this.metaNames[i]);
      }
    }
  }



  public IIcon func_77617_a(int damage) {
    if (this.metaNames == null) {
      return this.field_77791_bV;
    }
    return this.icons[damage];
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Items\ItemFlatGeneric.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */