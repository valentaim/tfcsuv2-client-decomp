package com.bioxx.tfc.Items.Tools;

import com.bioxx.tfc.Core.TFCTabs;
import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.Core.TFC_Textures;
import com.bioxx.tfc.Items.ItemTerra;
import com.bioxx.tfc.api.Crafting.AnvilManager;
import com.bioxx.tfc.api.Enums.EnumDamageType;
import com.bioxx.tfc.api.Enums.EnumItemReach;
import com.bioxx.tfc.api.Enums.EnumSize;
import com.bioxx.tfc.api.Enums.EnumWeight;
import com.bioxx.tfc.api.Interfaces.ICausesDamage;
import com.bioxx.tfc.api.Interfaces.ISize;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraftforge.common.ForgeHooks;


public class ItemCustomAxe
  extends ItemAxe
  implements ISize, ICausesDamage
{
  private float toolDamage;

  public ItemCustomAxe(Item.ToolMaterial e, float damage) {
    super(e);
    func_77656_e(e.func_77997_a());
    this.toolDamage = damage;
    func_77637_a(TFCTabs.TFC_TOOLS);
    setNoRepair();
  }



  public void func_94581_a(IIconRegister registerer) {
    String name = func_77658_a().replace("item.", "");
    name = name.replace("IgIn ", "");
    name = name.replace("IgEx ", "");
    name = name.replace("Sed ", "");
    name = name.replace("MM ", "");
    this.field_77791_bV = registerer.func_94245_a("terrafirmacraft:tools/" + name);
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
    arraylist.add(EnumChatFormatting.AQUA + TFC_Core.translate(getDamageType().toString()));
    ItemTerraTool.addSmithingBonusInformation(is, arraylist);
  }



  public int func_77639_j() {
    return 1;
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



  public EnumDamageType getDamageType() {
    return EnumDamageType.SLASHING;
  }



  public Multimap getAttributeModifiers(ItemStack is) {
    HashMultimap hashMultimap = HashMultimap.create();
    hashMultimap.put(SharedMonsterAttributes.field_111264_e.func_111108_a(), new AttributeModifier(field_111210_e, "Tool modifier", getWeaponDamage(is), 0));
    return (Multimap)hashMultimap;
  }


  public double getWeaponDamage(ItemStack is) {
    return Math.floor((this.toolDamage + this.toolDamage * AnvilManager.getDamageBuff(is)));
  }



  public int getMaxDamage(ItemStack is) {
    return (int)Math.floor((func_77612_l() + func_77612_l() * AnvilManager.getDurabilityBuff(is)));
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


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Items\Tools\ItemCustomAxe.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */