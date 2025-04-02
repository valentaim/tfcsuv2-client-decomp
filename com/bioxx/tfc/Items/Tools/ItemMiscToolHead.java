package com.bioxx.tfc.Items.Tools;

import com.bioxx.tfc.Core.TFCTabs;
import com.bioxx.tfc.Items.ItemTerra;
import com.bioxx.tfc.api.Constant.Global;
import com.bioxx.tfc.api.Enums.EnumSize;
import com.bioxx.tfc.api.Enums.EnumWeight;
import com.bioxx.tfc.api.Interfaces.ISmeltable;
import com.bioxx.tfc.api.Metal;
import com.bioxx.tfc.api.TFCItems;
import java.util.List;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;



public class ItemMiscToolHead
  extends ItemTerra
  implements ISmeltable
{
  private Item.ToolMaterial material;

  public ItemMiscToolHead() {
    func_77656_e(100);
    func_77625_d(4);
    func_77637_a(TFCTabs.TFC_MISC);
    setFolder("toolheads/");
    setWeight(EnumWeight.MEDIUM);
    setSize(EnumSize.SMALL);
  }


  public ItemMiscToolHead(Item.ToolMaterial m) {
    this();
    this.material = m;
  }


  public Item.ToolMaterial getMaterial() {
    return this.material;
  }



  public void func_94581_a(IIconRegister registerer) {
    String name = func_77658_a().replace("item.", "");
    name = name.replace("IgIn ", "");
    name = name.replace("IgEx ", "");
    name = name.replace("Sed ", "");
    name = name.replace("MM ", "");
    this.field_77791_bV = registerer.func_94245_a("terrafirmacraft:" + this.textureFolder + name);
  }



  public void addExtraInformation(ItemStack is, EntityPlayer player, List<String> arraylist) {
    ItemTerraTool.addSmithingBonusInformation(is, arraylist);
  }



  public Metal getMetalType(ItemStack is) {
    if (this == TFCItems.wroughtIronKnifeHead) {
      return Global.WROUGHTIRON;
    }
    return null;
  }



  public short getMetalReturnAmount(ItemStack is) {
    if (this == TFCItems.wroughtIronKnifeHead)
    {
      return 50;
    }
    return 0;
  }



  public boolean isSmeltable(ItemStack is) {
    return (this == TFCItems.wroughtIronKnifeHead);
  }



  public ISmeltable.EnumTier getSmeltTier(ItemStack is) {
    return ISmeltable.EnumTier.TierIII;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Items\Tools\ItemMiscToolHead.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */