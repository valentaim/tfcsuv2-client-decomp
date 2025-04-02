package com.bioxx.tfc.Items.ItemBlocks;

import com.bioxx.tfc.Core.Metal.Alloy;
import com.bioxx.tfc.Core.Metal.AlloyManager;
import com.bioxx.tfc.Core.Metal.AlloyMetal;
import com.bioxx.tfc.Core.Metal.MetalPair;
import com.bioxx.tfc.Core.Metal.MetalRegistry;
import com.bioxx.tfc.Items.ItemTerra;
import com.bioxx.tfc.api.Constant.Global;
import com.bioxx.tfc.api.Enums.EnumSize;
import com.bioxx.tfc.api.Enums.EnumWeight;
import com.bioxx.tfc.api.Interfaces.ISize;
import com.bioxx.tfc.api.Metal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumChatFormatting;

public class ItemCrucible
  extends ItemTerraBlock
  implements ISize
{
  public Map<String, MetalPair> metals = new HashMap<>();
  private Alloy currentAlloy;

  public ItemCrucible(Block par1) {
    super(par1);
  }



  public void func_77624_a(ItemStack is, EntityPlayer player, List<String> arraylist, boolean flag) {
    ItemTerra.addSizeInformation(is, arraylist);

    readFromItemNBT(is.func_77978_p(), arraylist);
  }


  public void readFromItemNBT(NBTTagCompound nbt, List<String> arraylist) {
    this.currentAlloy = null;
    this.metals = new HashMap<>();
    if (nbt != null && nbt.func_74764_b("Metals")) {

      NBTTagList nbttaglist = nbt.func_150295_c("Metals", 9);

      for (int i = 0; i < nbttaglist.func_74745_c(); i++) {

        NBTTagCompound nbttagcompound1 = nbttaglist.func_150305_b(i);
        int id = nbttagcompound1.func_74762_e("ID");
        float amount = nbttagcompound1.func_74760_g("AmountF");

        Metal m = MetalRegistry.instance.getMetalFromItem(Item.func_150899_d(id));
        addMetal(m, amount);
      }
    }


    if (this.currentAlloy != null)
    {
      for (int c = 0; c < this.currentAlloy.alloyIngred.size(); c++) {

        double m = ((AlloyMetal)this.currentAlloy.alloyIngred.get(c)).metal;
        m = Math.round(m * 100.0D) / 100.0D;
        if (((AlloyMetal)this.currentAlloy.alloyIngred.get(c)).metalType != null)
        {
          arraylist.add(EnumChatFormatting.DARK_GRAY + ((AlloyMetal)this.currentAlloy.alloyIngred.get(c)).metalType.name + " " + EnumChatFormatting.DARK_GREEN + m + "%");
        }
      }
    }
  }


  public boolean addMetal(Metal m, float amt) {
    if (getTotalMetal() + amt <= 3000.0F && !"Unknown".equals(m.name)) {

      if (this.metals.containsKey(m.name)) {

        ((MetalPair)this.metals.get(m.name)).amount += amt;
      }
      else {

        this.metals.put(m.name, new MetalPair(m, amt));
      }

      updateCurrentAlloy();

      return true;
    }
    return false;
  }


  public float getTotalMetal() {
    Iterator<MetalPair> iter = this.metals.values().iterator();
    float totalAmount = 0.0F;
    while (iter.hasNext()) {

      MetalPair m = iter.next();
      if (m != null)
      {
        totalAmount += m.amount;
      }
    }
    return totalAmount;
  }


  private void updateCurrentAlloy() {
    List<AlloyMetal> a = new ArrayList<>();

    Iterator<MetalPair> iter = this.metals.values().iterator();
    float totalAmount = getTotalMetal();

    iter = this.metals.values().iterator();
    while (iter.hasNext()) {

      MetalPair m = iter.next();
      if (m != null)
      {
        a.add(new AlloyMetal(m.type, m.amount / totalAmount * 100.0F));
      }
    }

    Metal match = AlloyManager.INSTANCE.matchesAlloy(a, Alloy.EnumTier.TierV);
    if (match != null) {

      this.currentAlloy = new Alloy(match, totalAmount);
      this.currentAlloy.alloyIngred = a;
    }
    else {

      this.currentAlloy = new Alloy(Global.UNKNOWN, totalAmount);
      this.currentAlloy.alloyIngred = a;
    }
  }




  public EnumSize getSize(ItemStack is) {
    return EnumSize.HUGE;
  }



  public boolean canStack() {
    return false;
  }



  public EnumWeight getWeight(ItemStack is) {
    return EnumWeight.HEAVY;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Items\ItemBlocks\ItemCrucible.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */