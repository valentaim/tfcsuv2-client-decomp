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
import com.bioxx.tfc.api.TFCBlocks;
import com.bioxx.tfc.api.Util.Helper;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;

public class ItemWeapon
  extends ItemSword
  implements ISize, ICausesDamage {
  private float weaponBaseDamage;
  private final Item.ToolMaterial toolMat;
  public EnumDamageType damageType = EnumDamageType.SLASHING;


  public ItemWeapon(Item.ToolMaterial par2, float damage) {
    super(par2);
    func_77656_e(par2.func_77997_a());
    this.weaponBaseDamage = damage;
    this.toolMat = par2;
    func_77637_a(TFCTabs.TFC_WEAPONS);
    setNoRepair();
  }



  public float func_150931_i() {
    return this.toolMat.func_78000_c();
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
    ItemTerra.addHeatInformation(is, arraylist);

    if (is.func_77973_b() instanceof ICausesDamage)
      arraylist.add(EnumChatFormatting.AQUA + TFC_Core.translate(super.getDamageType().toString()));
    ItemTerraTool.addSmithingBonusInformation(is, arraylist);
    addExtraInformation(is, player, arraylist);
  }



  public void addExtraInformation(ItemStack is, EntityPlayer player, List<String> arraylist) {}



  public void func_94581_a(IIconRegister registerer) {
    this.field_77791_bV = registerer.func_94245_a("terrafirmacraft:tools/" + func_77658_a().replace("item.", ""));
  }



  public int func_77639_j() {
    if (canStack()) {
      return (getSize((ItemStack)null)).stackSize * (getWeight((ItemStack)null)).multiplier;
    }
    return 1;
  }






  public ItemStack func_77659_a(ItemStack is, World world, EntityPlayer player) {
    MovingObjectPosition mop = Helper.getMouseOverObject((EntityLivingBase)player, player.field_70170_p);

    if (mop != null && world.func_147439_a(mop.field_72311_b, mop.field_72312_c, mop.field_72309_d) == TFCBlocks.toolRack) {
      return is;
    }
    player.func_71008_a(is, func_77626_a(is));
    return is;
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
    return this.damageType;
  }


  public double getWeaponDamage(ItemStack is) {
    return Math.floor((this.weaponBaseDamage + this.weaponBaseDamage * AnvilManager.getDamageBuff(is)));
  }



  public Multimap getAttributeModifiers(ItemStack stack) {
    HashMultimap hashMultimap = HashMultimap.create();
    hashMultimap.put(SharedMonsterAttributes.field_111264_e.func_111108_a(), new AttributeModifier(field_111210_e, "Weapon modifier", getWeaponDamage(stack), 0));
    return (Multimap)hashMultimap;
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


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Items\Tools\ItemWeapon.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */