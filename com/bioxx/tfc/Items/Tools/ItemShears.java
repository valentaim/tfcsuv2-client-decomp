package com.bioxx.tfc.Items.Tools;

import com.bioxx.tfc.api.Enums.EnumSize;
import com.bioxx.tfc.api.Enums.EnumWeight;
import com.google.common.collect.Sets;
import fof.tfcsu.utils.ExpBonus;
import fof.tfcsu.utils.Utils;
import java.util.ArrayList;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;

public class ItemShears
  extends ItemTerraTool {
  int ticks;
  long lasttick = 0L;


  public ItemShears(float dam, Item.ToolMaterial par3) {
    super(dam, par3, Sets.newHashSet((Object[])new Block[0]));
  }



  public void func_94581_a(IIconRegister registerer) {
    this.field_77791_bV = registerer.func_94245_a("minecraft:" + func_77658_a().replace("item.", ""));
  }



  public EnumSize getSize(ItemStack is) {
    return EnumSize.SMALL;
  }



  public EnumWeight getWeight(ItemStack is) {
    return EnumWeight.LIGHT;
  }



  public boolean func_150894_a(ItemStack is, World world, Block block, int x, int y, int z, EntityLivingBase entity) {
    if (block.func_149688_o() != Material.field_151584_j && block != Blocks.field_150321_G && block != Blocks.field_150329_H && block != Blocks.field_150395_bd && block != Blocks.field_150473_bD && !(block instanceof IShearable))
    {
      return super.func_150894_a(is, world, block, x, y, z, entity);
    }


    return true;
  }




  public boolean func_150897_b(Block block) {
    return (block == Blocks.field_150321_G || block == Blocks.field_150488_af || block == Blocks.field_150473_bD);
  }



  public float func_150893_a(ItemStack is, Block block) {
    return (block != Blocks.field_150321_G && block.func_149688_o() != Material.field_151584_j) ? ((block == Blocks.field_150325_L) ? 5.0F : super.func_150893_a(is, block)) : 15.0F;
  }






  public boolean func_111207_a(ItemStack itemstack, EntityPlayer player, EntityLivingBase entity) {
    if (entity.field_70170_p.field_72995_K)
    {
      return false;
    }
    if (entity instanceof IShearable) {

      IShearable target = (IShearable)entity;
      if (target.isShearable(itemstack, (IBlockAccess)entity.field_70170_p, (int)entity.field_70165_t, (int)entity.field_70163_u, (int)entity.field_70161_v)) {

        if (System.currentTimeMillis() > this.lasttick + 500L)
        { this.ticks++;
          this.lasttick = System.currentTimeMillis();
          Utils.sendClientMessageToolTip("§2Хорошо", player); }
        else { this.ticks = 0; Utils.sendClientMessageToolTip("§4Плохо!", player); }
         if (this.ticks < 10) return true;
        ExpBonus.SHEAR.give(player);
        ArrayList<ItemStack> drops = target.onSheared(itemstack, (IBlockAccess)entity.field_70170_p, (int)entity.field_70165_t, (int)entity.field_70163_u, (int)entity.field_70161_v,
            EnchantmentHelper.func_77506_a(Enchantment.field_77346_s.field_77352_x, itemstack));
        Random rand = new Random();
        for (ItemStack stack : drops) {

          EntityItem ent = entity.func_70099_a(stack, 1.0F);
          ent.field_70181_x += (rand.nextFloat() * 0.05F);
          ent.field_70159_w += ((rand.nextFloat() - rand.nextFloat()) * 0.1F);
          ent.field_70179_y += ((rand.nextFloat() - rand.nextFloat()) * 0.1F);
        }
        itemstack.func_77972_a(1, entity);
      }
      this.ticks = 0;
      return true;
    }  this.ticks = 0;
    return true;
  }



  public boolean onBlockStartBreak(ItemStack itemstack, int x, int y, int z, EntityPlayer player) {
    if (player.field_70170_p.field_72995_K)
    {
      return false;
    }
    Block block = player.field_70170_p.func_147439_a(x, y, z);
    if (block instanceof IShearable) {

      IShearable target = (IShearable)block;
      if (target.isShearable(itemstack, (IBlockAccess)player.field_70170_p, x, y, z)) {

        ArrayList<ItemStack> drops = target.onSheared(itemstack, (IBlockAccess)player.field_70170_p, x, y, z,
            EnchantmentHelper.func_77506_a(Enchantment.field_77346_s.field_77352_x, itemstack));
        Random rand = new Random();

        for (ItemStack stack : drops) {

          float f = 0.7F;
          double d = (rand.nextFloat() * f) + (1.0F - f) * 0.5D;
          double d1 = (rand.nextFloat() * f) + (1.0F - f) * 0.5D;
          double d2 = (rand.nextFloat() * f) + (1.0F - f) * 0.5D;
          EntityItem entityitem = new EntityItem(player.field_70170_p, x + d, y + d1, z + d2, stack);
          entityitem.field_145804_b = 10;
          player.field_70170_p.func_72838_d((Entity)entityitem);
        }

        itemstack.func_77972_a(1, (EntityLivingBase)player);
        player.func_71064_a(StatList.field_75934_C[Block.func_149682_b(block)], 1);
      }
    }
    return false;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Items\Tools\ItemShears.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */