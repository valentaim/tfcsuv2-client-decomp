package com.bioxx.tfc.Items.Tools;

import com.bioxx.tfc.Core.TFCTabs;
import com.bioxx.tfc.Core.TFC_Time;
import com.bioxx.tfc.Entities.EntityFishHookTFC;
import com.bioxx.tfc.Items.ItemTerra;
import com.bioxx.tfc.api.Enums.EnumItemReach;
import com.bioxx.tfc.api.Enums.EnumSize;
import com.bioxx.tfc.api.Enums.EnumWeight;
import com.bioxx.tfc.api.Interfaces.ISize;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemFishingRod;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;



public class ItemCustomFishingRod
  extends ItemFishingRod
  implements ISize
{
  @SideOnly(Side.CLIENT)
  private IIcon[] uncastIconArray;
  private IIcon[] castIconArray;

  public ItemCustomFishingRod() {
    func_77656_e(64);
    func_77625_d(1);
    func_77637_a(TFCTabs.TFC_TOOLS);
    setNoRepair();
  }







  @SideOnly(Side.CLIENT)
  public boolean func_77662_d() {
    return true;
  }








  @SideOnly(Side.CLIENT)
  public boolean func_77629_n_() {
    return true;
  }



  public boolean onEntitySwing(EntityLivingBase entityLiving, ItemStack is) {
    if (is.field_77990_d != null && is.field_77990_d.func_74764_b("swing") && is.field_77990_d.func_74767_n("swing")) {
      is.field_77990_d.func_74757_a("swing", false);
      return false;
    }
    return true;
  }






  public ItemStack func_77659_a(ItemStack is, World world, EntityPlayer player) {
    if (is.field_77990_d != null && is.field_77990_d.func_74764_b("tickReeledIn")) {

      long tickReeledIn = is.field_77990_d.func_74763_f("tickReeledIn");
      if (TFC_Time.getTotalTicks() <= tickReeledIn + 20L) {
        return is;
      }
    }
    if (player.field_71104_cf != null) {



      if (player.field_71104_cf instanceof EntityFishHookTFC)
      {
        ((EntityFishHookTFC)player.field_71104_cf).reelInBobber((Entity)player, is);
      }
      else
      {
        player.func_71008_a(is, 1);
      }

    }
    else {

      if (is.field_77990_d == null) {
        is.func_77982_d(new NBTTagCompound());
      }
      player.func_71008_a(is, func_77626_a(is));
    }

    return is;
  }



  public boolean func_77648_a(ItemStack is, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
    if (player.field_71104_cf instanceof EntityFishHookTFC)
    {
      ((EntityFishHookTFC)player.field_71104_cf).reelInBobber((Entity)player, is);
    }





    return false;
  }



  public void func_77615_a(ItemStack is, World world, EntityPlayer player, int inUseCount) {
    if (player.field_71104_cf == null && is.field_77990_d != null) {
      world.func_72956_a((Entity)player, "random.bow", 0.5F, 0.4F / (field_77697_d.nextFloat() * 0.4F + 0.8F));
      if (!world.field_72995_K) {
        world.func_72838_d((Entity)new EntityFishHookTFC(world, player, is.func_77988_m() - inUseCount));

        is.field_77990_d.func_74757_a("fishing", true);
      }

      is.field_77990_d.func_74757_a("swing", true);
      player.func_71038_i();
      is.field_77990_d.func_74757_a("fishing", true);
      is.field_77990_d.func_74768_a("usedUses", 0);
    }
    else if (is.field_77990_d != null) {
      is.field_77990_d.func_74757_a("fishing", true);
    }
  }






  public int func_77626_a(ItemStack is) {
    return 72000;
  }




  @SideOnly(Side.CLIENT)
  public void func_94581_a(IIconRegister par1IconRegister) {
    this.uncastIconArray = new IIcon[3];
    this.castIconArray = new IIcon[8]; int i;
    for (i = 0; i < this.castIconArray.length; i++)
      this.castIconArray[i] = par1IconRegister.func_94245_a("terrafirmacraft:" + func_111208_A() + "_cast_" + i);
    for (i = 0; i < this.uncastIconArray.length; i++)
      this.uncastIconArray[i] = par1IconRegister.func_94245_a("terrafirmacraft:" + func_111208_A() + "_uncast_" + i);
    this.field_77791_bV = this.uncastIconArray[0];
  }


  @SideOnly(Side.CLIENT)
  public IIcon getItemIconForUseDuration(int par1, boolean cast) {
    par1 = Math.min(Math.max(par1, 0), 7);
    if (cast)
      return this.castIconArray[par1];
    return this.uncastIconArray[par1];
  }



  public IIcon getIcon(ItemStack is, int renderPass, EntityPlayer player, ItemStack usingItem, int useRemaining) {
    boolean cast = (player.field_71104_cf != null);

    if (!is.func_77942_o()) {
      is.func_77982_d(new NBTTagCompound());
    }
    is.field_77990_d.func_74757_a("fishing", cast);
    if (usingItem == null) {
      useRemaining = func_77626_a(is);
    }

    if (!cast) {
      int j = Math.max(Math.min(func_77626_a(is) - useRemaining + 10, 60) / 20 - 1, 0);
      if (!is.func_77942_o()) {
        is.func_77982_d(new NBTTagCompound());
      }
      is.field_77990_d.func_74768_a("usedUses", func_77626_a(is) - useRemaining);
      return getItemIconForUseDuration(Math.min(j, this.uncastIconArray.length - 1), cast);
    }

    int tension = 0;
    if (is.func_77942_o() && is.field_77990_d.func_74764_b("tension")) {
      tension = is.field_77990_d.func_74762_e("tension");
    }
    int originalTex = tension / 100;
    int texShift = (tension % 100 + 1) % 31;
    return getItemIconForUseDuration(Math.min(originalTex + ((texShift == 10) ? 1 : 0), this.castIconArray.length - 1), cast);
  }




  public IIcon getIcon(ItemStack is, int renderPass) {
    if (is.func_77942_o() && is.field_77990_d.func_74764_b("fishing") && is.field_77990_d.func_74767_n("fishing")) {
      return this.castIconArray[0];
    }
    return this.uncastIconArray[0];
  }



  public void func_77624_a(ItemStack is, EntityPlayer player, List arraylist, boolean flag) {
    ItemTerra.addSizeInformation(is, arraylist);
  }



  public EnumSize getSize(ItemStack is) {
    return EnumSize.TINY;
  }



  public EnumWeight getWeight(ItemStack is) {
    return EnumWeight.LIGHT;
  }






  public EnumAction func_77661_b(ItemStack is) {
    if (is.field_77990_d != null && is.field_77990_d.func_74764_b("fishing") && is.field_77990_d.func_74767_n("fishing"))
    {
      return EnumAction.bow;
    }
    return EnumAction.none;
  }



  public EnumItemReach getReach(ItemStack is) {
    return EnumItemReach.FAR;
  }



  public boolean canStack() {
    return true;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Items\Tools\ItemCustomFishingRod.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */