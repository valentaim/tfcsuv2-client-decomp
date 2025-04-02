package com.bioxx.tfc.Items;

import com.bioxx.tfc.Core.TFCTabs;
import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.TerraFirmaCraft;
import com.bioxx.tfc.api.Enums.EnumAmmo;
import com.bioxx.tfc.api.Interfaces.IEquipable;
import com.bioxx.tfc.api.Interfaces.IQuiverAmmo;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;




public class ItemQuiver
  extends ItemTerra
  implements IEquipable
{
  public ItemQuiver() {
    func_77637_a(TFCTabs.TFC_ARMOR);
  }





  public IIcon func_77618_c(int par1, int par2) {
    return this.field_77791_bV;
  }











  public void func_77663_a(ItemStack is, World world, Entity entity, int i, boolean isSelected) {
    super.func_77663_a(is, world, entity, i, isSelected);
  }



  public ItemStack func_77659_a(ItemStack itemstack, World world, EntityPlayer entityplayer) {
    entityplayer.openGui(TerraFirmaCraft.instance, 40, entityplayer.field_70170_p, (int)entityplayer.field_70165_t, (int)entityplayer.field_70163_u, (int)entityplayer.field_70161_v);
    return itemstack;
  }



  public void func_94581_a(IIconRegister registerer) {
    this.field_77791_bV = registerer.func_94245_a("terrafirmacraft:quiver");
  }

  public int getQuiverArrows(ItemStack item) {
    int n = 0;
    ItemStack[] inventory = loadInventory(item);
    for (ItemStack i : inventory) {

      if (i != null && (i.func_77973_b() instanceof ItemArrow || (i
        .func_77973_b() instanceof IQuiverAmmo && ((IQuiverAmmo)i.func_77973_b()).getAmmoType() == EnumAmmo.ARROW)))
        n += i.field_77994_a;
    }
    return n;
  }


  public int getQuiverJavelins(ItemStack item) {
    int n = 0;
    ItemStack[] inventory = loadInventory(item);
    for (ItemStack i : inventory) {
      if (i != null && i.func_77973_b() instanceof com.bioxx.tfc.Items.Tools.ItemJavelin)
        n += i.field_77994_a;
    }
    return n;
  }




  public List[] getQuiverJavelinTypes(ItemStack item) {
    ArrayList[] pair = new ArrayList[2];
    ArrayList<String> list = new ArrayList<>();
    ArrayList<Integer> listNum = new ArrayList<>();
    ItemStack[] inventory = loadInventory(item);
    for (ItemStack i : inventory) {

      if (i != null && i.func_77973_b() instanceof com.bioxx.tfc.Items.Tools.ItemJavelin) {

        String s = i.func_77973_b().func_77653_i(i);
        if (!list.contains(s))
          list.add(s);
        int n = list.indexOf(s);
        if (listNum.size() == n)
          listNum.add(Integer.valueOf(0));
        listNum.set(n, Integer.valueOf(((Integer)listNum.get(n)).intValue() + 1));
      }
    }
    pair[0] = list;
    pair[1] = listNum;
    return (List[])pair;
  }



  public void func_77624_a(ItemStack is, EntityPlayer player, List<String> arraylist, boolean flag) {
    ItemTerra.addSizeInformation(is, arraylist);

    if (TFC_Core.showShiftInformation()) {






      arraylist.add(EnumChatFormatting.WHITE + TFC_Core.translate("gui.Advanced") + ":");
      arraylist.add(EnumChatFormatting.ITALIC + TFC_Core.translate("gui.Bow.Arrows") + ": " + EnumChatFormatting.YELLOW + getQuiverArrows(is));
      arraylist.add(EnumChatFormatting.ITALIC + TFC_Core.translate("gui.Bow.Javelins") + ": " + EnumChatFormatting.YELLOW + getQuiverJavelins(is));
      List[] javData = getQuiverJavelinTypes(is);
      for (int i = 0; i < javData[0].size(); i++) {

        String s = javData[0].get(i);
        int n = ((Integer)javData[1].get(i)).intValue();
        arraylist.add(EnumChatFormatting.ITALIC + "  -" + s + ": " + EnumChatFormatting.YELLOW + n);
      }
      if (is.func_77942_o()) {

        NBTTagCompound stackTagCompound = is.func_77978_p();
        if (stackTagCompound.func_74764_b("creator")) {
          arraylist.add(EnumChatFormatting.ITALIC + TFC_Core.translate("gui.Armor.ForgedBy") + " " + stackTagCompound.func_74779_i("creator"));
        }
      }
    } else {
      arraylist.add(EnumChatFormatting.DARK_GRAY + TFC_Core.translate("gui.Advanced") + ": (" + TFC_Core.translate("gui.Hold") + " " + EnumChatFormatting.GRAY + TFC_Core.translate("gui.Shift") + EnumChatFormatting.DARK_GRAY + ")");
    }
  }



  public ItemStack addItem(ItemStack quiver, ItemStack ammo) {
    ItemStack[] inventory = loadInventory(quiver);
    for (int i = 0; i < inventory.length && ammo != null; i++) {

      if (inventory[i] != null && inventory[i].func_77969_a(ammo)) {

        if (ammo.field_77994_a + (inventory[i]).field_77994_a <= ammo.func_77976_d())
        {
          (inventory[i]).field_77994_a += ammo.field_77994_a;
          ammo = null;
        }
        else if (ammo.field_77994_a + (inventory[i]).field_77994_a > ammo.func_77976_d())
        {
          int diff = ammo.func_77976_d() - (inventory[i]).field_77994_a;
          (inventory[i]).field_77994_a = ammo.func_77976_d();
          ammo.field_77994_a -= diff;
        }

      } else if (inventory[i] == null) {

        inventory[i] = ammo.func_77946_l();
        ammo = null;
      }
    }
    saveInventory(quiver, inventory);
    return ammo;
  }


  public ItemStack consumeAmmo(ItemStack quiver, EnumAmmo ammoType, boolean shouldConsume) {
    ItemStack[] inventory = loadInventory(quiver);
    for (int i = 0; i < inventory.length; i++) {

      if (inventory[i] != null && inventory[i].func_77973_b() instanceof IQuiverAmmo && ((IQuiverAmmo)inventory[i].func_77973_b()).getAmmoType() == ammoType) {

        ItemStack out = inventory[i].func_77946_l();
        out.field_77994_a = 1;
        if (shouldConsume)
          (inventory[i]).field_77994_a--;
        if ((inventory[i]).field_77994_a <= 0)
          inventory[i] = null;
        saveInventory(quiver, inventory);
        return out;
      }
    }
    return null;
  }


  public ItemStack[] loadInventory(ItemStack quiver) {
    ItemStack[] inventory = new ItemStack[8];
    NBTTagCompound nbt = quiver.func_77978_p();
    if (nbt != null && nbt.func_74764_b("Items")) {

      NBTTagList nbttaglist = nbt.func_150295_c("Items", 10);

      for (int i = 0; i < nbttaglist.func_74745_c(); i++) {

        NBTTagCompound nbttagcompound1 = nbttaglist.func_150305_b(i);
        byte byte0 = nbttagcompound1.func_74771_c("Slot");
        if (byte0 >= 0 && byte0 < 8)
          inventory[byte0] = ItemStack.func_77949_a(nbttagcompound1);
      }
    }
    return inventory;
  }


  public void saveInventory(ItemStack quiver, ItemStack[] inventory) {
    NBTTagList nbttaglist = new NBTTagList();
    for (int i = 0; i < inventory.length; i++) {

      if (inventory[i] != null) {

        NBTTagCompound nbttagcompound1 = new NBTTagCompound();
        nbttagcompound1.func_74774_a("Slot", (byte)i);
        inventory[i].func_77955_b(nbttagcompound1);
        nbttaglist.func_74742_a((NBTBase)nbttagcompound1);
      }
    }
    if (quiver != null) {

      if (!quiver.func_77942_o())
        quiver.func_77982_d(new NBTTagCompound());
      quiver.func_77978_p().func_74782_a("Items", (NBTBase)nbttaglist);
    }
  }



  public IEquipable.EquipType getEquipType(ItemStack is) {
    return IEquipable.EquipType.BACK;
  }



  public void onEquippedRender() {}



  public boolean getTooHeavyToCarry(ItemStack is) {
    return false;
  }



  public int func_77639_j() {
    return 1;
  }



  public boolean canStack() {
    return false;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Items\ItemQuiver.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */