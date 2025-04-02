package com.bioxx.tfc.Items;

import com.bioxx.tfc.Core.TFCTabs;
import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.TerraFirmaCraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;



public class ItemCustomNameTag
  extends ItemTerra
{
  public ItemCustomNameTag() {
    func_77656_e(0);
    func_77627_a(true);
    func_77655_b("Nametag");
    func_77637_a(TFCTabs.TFC_TOOLS);
    setFolder("tools/");
  }



  public boolean func_77651_p() {
    return true;
  }



  public IIcon getIcon(ItemStack stack, int pass) {
    return Items.field_151057_cb.getIcon(stack, pass);
  }



  public IIcon func_77617_a(int damage) {
    return Items.field_151057_cb.func_77617_a(damage);
  }




  public ItemStack func_77659_a(ItemStack stack, World world, EntityPlayer player) {
    if (stack.field_77990_d == null)
    {
      stack.field_77990_d = new NBTTagCompound();
    }
    if (stack.field_77990_d != null && !stack.field_77990_d.func_74764_b("ItemName"))
    {
      player.openGui(TerraFirmaCraft.instance, 48, player.field_70170_p, (int)player.field_70165_t, (int)player.field_70163_u, (int)player.field_70161_v);
    }

    return stack;
  }




  public void func_94581_a(IIconRegister registerer) {}




  public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
    return false;
  }



  public String func_77653_i(ItemStack is) {
    if (is.func_77942_o() && is.field_77990_d.func_74764_b("ItemName"))
      return is.field_77990_d.func_74779_i("ItemName");
    return TFC_Core.translate("gui.Nametag");
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Items\ItemCustomNameTag.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */