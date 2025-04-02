package com.bioxx.tfc.WAILA;

import com.bioxx.tfc.Core.Metal.Alloy;
import com.bioxx.tfc.Core.Metal.AlloyManager;
import com.bioxx.tfc.Core.Metal.AlloyMetal;
import com.bioxx.tfc.Core.Metal.MetalPair;
import com.bioxx.tfc.Core.Metal.MetalRegistry;
import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.TileEntities.TECrucible;
import com.bioxx.tfc.api.Constant.Global;
import com.bioxx.tfc.api.Metal;
import com.bioxx.tfc.api.TFC_ItemHeat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaDataAccessor;
import mcp.mobius.waila.api.IWailaDataProvider;
import mcp.mobius.waila.api.IWailaRegistrar;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;


public class WCrucible
  implements IWailaDataProvider
{
  private Map<String, MetalPair> metals = new HashMap<>();

  private Alloy currentAlloy;


  public ItemStack getWailaStack(IWailaDataAccessor accessor, IWailaConfigHandler config) {
    return null;
  }



  public List<String> getWailaHead(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
    return currenttip;
  }




  public List<String> getWailaBody(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
    this.metals.clear();
    this.currentAlloy = null;

    if (accessor.getTileEntity() instanceof TECrucible) {

      NBTTagCompound tag = accessor.getNBTData();
      NBTTagList taglist = tag.func_150295_c("Metals", 10);


      for (int i = 0; i < taglist.func_74745_c(); i++) {

        NBTTagCompound nbt = taglist.func_150305_b(i);
        int id = nbt.func_74762_e("ID");
        float amount = nbt.func_74765_d("Amount");
        float amountF = amount + nbt.func_74760_g("AmountF");
        Metal metal = MetalRegistry.instance.getMetalFromItem(Item.func_150899_d(id));
        addMetal(metal, amountF);
      }


      if (this.currentAlloy != null) {

        String metalTypeUnits = EnumChatFormatting.UNDERLINE + TFC_Core.translate("gui.metal.Unknown");
        if (this.currentAlloy.outputType != null)
        {
          metalTypeUnits = EnumChatFormatting.UNDERLINE + TFC_Core.translate("gui.metal." + this.currentAlloy.outputType.name.replace(" ", ""));
        }

        int output = Math.round(this.currentAlloy.outputAmount);
        metalTypeUnits = metalTypeUnits + "· " + TFC_Core.translate("gui.units") + " : " + output;

        currenttip.add(metalTypeUnits);

        for (int c = 0; c < this.currentAlloy.alloyIngred.size(); c++) {

          double m = ((AlloyMetal)this.currentAlloy.alloyIngred.get(c)).metal;
          m = Math.round(m * 100.0D) / 100.0D;
          if (((AlloyMetal)this.currentAlloy.alloyIngred.get(c)).metalType != null)
          {
            currenttip.add("· " + TFC_Core.translate("gui.metal." + ((AlloyMetal)this.currentAlloy.alloyIngred.get(c)).metalType.name.replace(" ", "")) + " : " + m + "%");
          }
        }
      }



      int temperature = tag.func_74762_e("temp");
      String temp = TFC_ItemHeat.getHeatColor(temperature, 2.14748365E9F);
      if (temperature > 0)
      {
        currenttip.add(temp);
      }
    }

    return currenttip;
  }



  public List<String> getWailaTail(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
    return currenttip;
  }



  public NBTTagCompound getNBTData(EntityPlayerMP player, TileEntity te, NBTTagCompound tag, World world, int x, int y, int z) {
    if (te != null)
      te.func_145841_b(tag);
    return tag;
  }


  public static void callbackRegister(IWailaRegistrar reg) {
    reg.registerBodyProvider(new WCrucible(), TECrucible.class);
    reg.registerNBTProvider(new WCrucible(), TECrucible.class);
  }


  public boolean addMetal(Metal m, float amt) {
    if (getTotalMetal() + amt <= 3000.0F && !"Unknown".equals(m.name)) {

      if (this.metals.containsKey(m.name)) {
        ((MetalPair)this.metals.get(m.name)).amount += amt;
      } else {
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
        totalAmount += m.amount;
    }
    return totalAmount;
  }


  private void updateCurrentAlloy() {
    List<AlloyMetal> a = new ArrayList<>();
    Iterator<MetalPair> iter = this.metals.values().iterator();
    float totalAmount = getTotalMetal();
    while (iter.hasNext()) {

      MetalPair m = iter.next();
      if (m != null) {
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
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\WAILA\WCrucible.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */