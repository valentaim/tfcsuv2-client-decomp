package com.bioxx.tfc.Items.Tools;

import com.bioxx.tfc.Core.TFCTabs;
import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.Core.TFC_Textures;
import com.bioxx.tfc.Items.ItemTerra;
import com.bioxx.tfc.api.Crafting.AnvilManager;
import com.bioxx.tfc.api.Enums.EnumItemReach;
import com.bioxx.tfc.api.Enums.EnumSize;
import com.bioxx.tfc.api.Enums.EnumWeight;
import com.bioxx.tfc.api.Interfaces.ICausesDamage;
import com.bioxx.tfc.api.Interfaces.ISize;
import com.bioxx.tfc.api.Util.Helper;
import java.util.List;
import java.util.Set;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraftforge.common.ForgeHooks;




public class ItemTerraTool
  extends ItemTool
  implements ISize
{
  public ItemTerraTool(float par2, Item.ToolMaterial par3, Set<Block> par4) {
    super(par2, par3, par4);
    func_77637_a(TFCTabs.TFC_TOOLS);
    setNoRepair();
  }





  public void func_77624_a(ItemStack is, EntityPlayer player, List<String> arraylist, boolean flag) {
    ItemTerra.addSizeInformation(is, arraylist);
    ItemTerra.addHeatInformation(is, arraylist);

    if (is.func_77973_b() instanceof ICausesDamage) {
      arraylist.add(EnumChatFormatting.AQUA + TFC_Core.translate(((ICausesDamage)this).getDamageType().toString()));
    }
    addSmithingBonusInformation(is, arraylist);
    addExtraInformation(is, player, arraylist);
  }


  public static void addSmithingBonusInformation(ItemStack is, List<String> arraylist) {
    if (AnvilManager.getDurabilityBuff(is) > 0.0F) {
      arraylist.add(TFC_Core.translate("gui.SmithingBonus") + " : +" + Helper.roundNumber(AnvilManager.getDurabilityBuff(is) * 100.0F, 10.0F) + "%");
    }
  }



  public void addExtraInformation(ItemStack is, EntityPlayer player, List<String> arraylist) {}


  public int func_77639_j() {
    if (canStack()) {
      return (getSize((ItemStack)null)).stackSize * (getWeight((ItemStack)null)).multiplier;
    }
    return 1;
  }



  public void func_94581_a(IIconRegister registerer) {
    this.field_77791_bV = registerer.func_94245_a("terrafirmacraft:tools/" + func_77658_a().replace("item.", ""));
    if (TFC_Textures.brokenItem == null) TFC_Textures.brokenItem = registerer.func_94245_a("terrafirmacraft:tools/Broken Item");
    if (TFC_Textures.wip == null) TFC_Textures.wip = registerer.func_94245_a("terrafirmacraft:wip");

  }


  public EnumItemReach getReach(ItemStack is) {
    return EnumItemReach.SHORT;
  }



  public EnumSize getSize(ItemStack is) {
    return EnumSize.LARGE;
  }



  public boolean canStack() {
    return false;
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



  public boolean func_77623_v() {
    return true;
  }



  public IIcon getIcon(ItemStack stack, int pass) {
    NBTTagCompound nbt = stack.func_77978_p();
    if (pass == 1 && nbt != null && nbt.func_74764_b("broken")) {
      return TFC_Textures.brokenItem;
    }
    return func_77618_c(stack.func_77960_j(), pass);
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Items\Tools\ItemTerraTool.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */