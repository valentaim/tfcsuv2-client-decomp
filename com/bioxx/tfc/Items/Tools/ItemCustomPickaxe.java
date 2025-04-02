package com.bioxx.tfc.Items.Tools;

import com.bioxx.tfc.Core.TFCTabs;
import com.bioxx.tfc.Core.TFC_Textures;
import com.bioxx.tfc.Items.ItemTerra;
import com.bioxx.tfc.api.Crafting.AnvilManager;
import com.bioxx.tfc.api.Enums.EnumItemReach;
import com.bioxx.tfc.api.Enums.EnumSize;
import com.bioxx.tfc.api.Enums.EnumWeight;
import com.bioxx.tfc.api.Interfaces.ISize;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import net.minecraftforge.common.ForgeHooks;


public class ItemCustomPickaxe
  extends ItemPickaxe
  implements ISize
{
  public ItemCustomPickaxe(Item.ToolMaterial e) {
    super(e);
    func_77637_a(TFCTabs.TFC_TOOLS);
    setNoRepair();
  }


  
  public void func_94581_a(IIconRegister registerer) {
    this.field_77791_bV = registerer.func_94245_a("terrafirmacraft:tools/" + func_77658_a().replace("item.", ""));
  }


  
  public IIcon getIcon(ItemStack stack, int pass) {
    NBTTagCompound nbt = stack.func_77978_p();
    if (pass == 1 && nbt != null && nbt.func_74764_b("broken")) {
      return TFC_Textures.brokenItem;
    }
    return func_77618_c(stack.func_77960_j(), pass);
  }


  
  public void func_77624_a(ItemStack is, EntityPlayer player, List<String> arraylist, boolean flag) {
    ItemTerra.addSizeInformation(is, arraylist);
    ItemTerraTool.addSmithingBonusInformation(is, arraylist);
  }


  
  public EnumSize getSize(ItemStack is) {
    return EnumSize.LARGE;
  }


  
  public boolean canStack() {
    return false;
  }


  
  public int func_77639_j() {
    if (canStack()) {
      return (getSize((ItemStack)null)).stackSize * (getWeight((ItemStack)null)).multiplier;
    }
    return 1;
  }


  
  public EnumWeight getWeight(ItemStack is) {
    return EnumWeight.MEDIUM;
  }


  
  public int getMaxDamage(ItemStack stack) {
    return (int)(func_77612_l() + func_77612_l() * AnvilManager.getDurabilityBuff(stack));
  }


  
  public float getDigSpeed(ItemStack stack, Block block, int meta) {
    float digSpeed = super.getDigSpeed(stack, block, meta);
    
    if (ForgeHooks.isToolEffective(stack, block, meta))
    {
      return digSpeed + digSpeed * AnvilManager.getDurabilityBuff(stack);
    }
    return digSpeed;
  }


  
  public EnumItemReach getReach(ItemStack is) {
    return EnumItemReach.MEDIUM;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Items\Tools\ItemCustomPickaxe.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */