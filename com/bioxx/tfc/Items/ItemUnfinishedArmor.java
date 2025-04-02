package com.bioxx.tfc.Items;

import com.bioxx.tfc.Core.Metal.MetalRegistry;
import com.bioxx.tfc.Core.TFCTabs;
import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.api.Enums.EnumSize;
import com.bioxx.tfc.api.Interfaces.ISmeltable;
import com.bioxx.tfc.api.Metal;
import java.util.List;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;



public class ItemUnfinishedArmor
  extends ItemTerra
  implements ISmeltable
{
  public int metalID;
  private String metal;
  private short metalAmount;
  private short metalAmount2;
  private boolean smeltable = true;

  public ItemUnfinishedArmor() {
    this.field_77787_bX = true;
    func_77656_e(0);
    func_77637_a(TFCTabs.TFC_MISC);
    setFolder("armor/");
    setSize(EnumSize.LARGE);
  }




  public ItemUnfinishedArmor(String tex) {}



  public void func_94581_a(IIconRegister registerer) {
    this.field_77791_bV = registerer.func_94245_a("terrafirmacraft:" + this.textureFolder + func_77658_a().replace("item.", "").replace("Unfinished ", "").replace("Stage2 ", ""));
  }



  public void addExtraInformation(ItemStack is, EntityPlayer player, List<String> arraylist) {
    if (is.func_77960_j() == 0) {
      arraylist.add(TFC_Core.translate("gui.stage1"));
    } else {
      arraylist.add(TFC_Core.translate("gui.stage2"));
    }
  }


  public String func_77653_i(ItemStack itemstack) {
    return super.func_77653_i(itemstack);
  }









  public int func_77639_j() {
    if (canStack()) {
      return (getSize((ItemStack)null)).stackSize;
    }
    return 1;
  }




  public boolean canStack() {
    return true;
  }



  public Metal getMetalType(ItemStack is) {
    if (this.metal == null)
    {
      return MetalRegistry.instance.getMetalFromItem(this);
    }


    return MetalRegistry.instance.getMetalFromString(this.metal);
  }




  public short getMetalReturnAmount(ItemStack is) {
    if (is.func_77960_j() == 1)
    {
      return (short)(this.metalAmount2 / 2); }
    return (short)(this.metalAmount / 2);
  }



  public boolean isSmeltable(ItemStack is) {
    return this.smeltable;
  }



  public ISmeltable.EnumTier getSmeltTier(ItemStack is) {
    return ISmeltable.EnumTier.TierI;
  }


  public ItemTerra setMetal(String m, int slot) {
    this.metal = m;
    if (slot == 0) {

      this.metalAmount = 200;
      this.metalAmount2 = 400;
    }
    else if (slot == 1) {

      this.metalAmount = 400;
      this.metalAmount2 = 800;
    }
    else if (slot == 2) {

      this.metalAmount = 400;
      this.metalAmount2 = 600;
    }
    else if (slot == 3) {

      this.metalAmount = 200;
      this.metalAmount2 = 200;
    }
    return this;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Items\ItemUnfinishedArmor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */