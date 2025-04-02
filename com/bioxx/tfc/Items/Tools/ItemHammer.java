package com.bioxx.tfc.Items.Tools;

import com.bioxx.tfc.Core.TFC_Achievements;
import com.bioxx.tfc.TileEntities.TEAnvil;
import com.bioxx.tfc.api.Crafting.AnvilManager;
import com.bioxx.tfc.api.Enums.EnumDamageType;
import com.bioxx.tfc.api.Enums.EnumItemReach;
import com.bioxx.tfc.api.Enums.EnumSize;
import com.bioxx.tfc.api.Interfaces.ICausesDamage;
import com.bioxx.tfc.api.TFCBlocks;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;
import java.util.Set;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatBase;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class ItemHammer extends ItemTerraTool implements ICausesDamage {
  private static final Set<Block> BLOCKS = Sets.newHashSet((Object[])new Block[0]);

  private float damageVsEntity;

  public ItemHammer(Item.ToolMaterial e, float damage) {
    super(0.0F, e, BLOCKS);
    this.damageVsEntity = damage;
  }



  public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
    Block id2 = player.field_70170_p.func_147439_a(x, y, z);
    int meta2 = player.field_70170_p.func_72805_g(x, y, z);

    if (id2 == TFCBlocks.stoneIgEx || id2 == TFCBlocks.stoneIgIn)
    {
      if (side == 1) {

        world.func_147449_b(x, y, z, TFCBlocks.anvil);
        player.func_71029_a((StatBase)TFC_Achievements.achAnvil);
        TEAnvil te = (TEAnvil)world.func_147438_o(x, y, z);
        if (te == null)
          world.func_147455_a(x, y, z, (TileEntity)new TEAnvil());
        if (te != null) {

          te.stonePair[0] = Block.func_149682_b(id2);
          te.stonePair[1] = meta2;
          te.func_145829_t();
        }

        return true;
      }
    }
    return false;
  }


  public boolean onBlockDestroyed(ItemStack stack, EntityPlayer player, World world, int i, int j, int k, int side, EntityLiving entity) {
    return false;
  }



  public EnumSize getSize(ItemStack is) {
    return EnumSize.SMALL;
  }



  public EnumDamageType getDamageType() {
    return EnumDamageType.CRUSHING;
  }



  public Multimap getAttributeModifiers(ItemStack is) {
    HashMultimap hashMultimap = HashMultimap.create();
    hashMultimap.put(SharedMonsterAttributes.field_111264_e.func_111108_a(), new AttributeModifier(field_111210_e, "Tool modifier", getWeaponDamage(is), 0));
    return (Multimap)hashMultimap;
  }


  public double getWeaponDamage(ItemStack is) {
    return Math.floor((this.damageVsEntity + this.damageVsEntity * AnvilManager.getDamageBuff(is)));
  }



  public int getMaxDamage(ItemStack is) {
    return (int)Math.floor((func_77612_l() + func_77612_l() * AnvilManager.getDurabilityBuff(is)));
  }



  public EnumItemReach getReach(ItemStack is) {
    return EnumItemReach.MEDIUM;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Items\Tools\ItemHammer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */