package com.bioxx.tfc.Items;

import com.bioxx.tfc.api.Enums.EnumItemReach;
import com.bioxx.tfc.api.Enums.EnumSize;
import com.bioxx.tfc.api.Enums.EnumWeight;
import com.bioxx.tfc.api.Interfaces.ISize;
import com.bioxx.tfc.api.TFCBlocks;
import java.util.Iterator;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLeashKnot;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemLead;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;





public class ItemCustomLeash
  extends ItemLead
  implements ISize
{
  public String textureFolder = "";







  public boolean func_77648_a(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10) {
    Block b = par3World.func_147439_a(par4, par5, par6);
    if (TFCBlocks.isBlockAFence(b)) {

      if (par3World.field_72995_K)
      {
        return true;
      }


      tryLeash(par2EntityPlayer, par3World, par4, par5, par6);
      return true;
    }



    return false;
  }



  public static boolean tryLeash(EntityPlayer par0EntityPlayer, World par1World, int par2, int par3, int par4) {
    EntityLeashKnot entityleashknot = EntityLeashKnot.func_110130_b(par1World, par2, par3, par4);
    boolean flag = false;
    double d0 = 7.0D;
    List list = par1World.func_72872_a(EntityLiving.class, AxisAlignedBB.func_72330_a(par2 - d0, par3 - d0, par4 - d0, par2 + d0, par3 + d0, par4 + d0));

    if (list != null) {

      Iterator<EntityLiving> iterator = list.iterator();
      while (iterator.hasNext()) {

        EntityLiving entityliving = iterator.next();
        if (entityliving.func_110167_bD() && entityliving.func_110166_bE() == par0EntityPlayer) {

          if (entityleashknot == null)
            entityleashknot = EntityLeashKnot.func_110129_a(par1World, par2, par3, par4);
          entityliving.func_110162_b((Entity)entityleashknot, true);
          flag = true;
        }
      }
    }
    return flag;
  }



  public IIcon func_77617_a(int meta) {
    return this.field_77791_bV;
  }




  public void func_77624_a(ItemStack is, EntityPlayer player, List<String> arraylist, boolean flag) {
    ItemTerra.addSizeInformation(is, arraylist);
  }



  public void func_94581_a(IIconRegister registerer) {
    this.field_77791_bV = registerer.func_94245_a("terrafirmacraft:" + this.textureFolder + func_77658_a().replace("item.", ""));
  }



  public EnumSize getSize(ItemStack is) {
    return EnumSize.MEDIUM;
  }



  public EnumWeight getWeight(ItemStack is) {
    return EnumWeight.MEDIUM;
  }



  public EnumItemReach getReach(ItemStack is) {
    return EnumItemReach.FAR;
  }



  public boolean canStack() {
    return true;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Items\ItemCustomLeash.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */