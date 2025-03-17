/*     */ package com.bioxx.tfc.Items.Tools;
/*     */ 
/*     */ import com.bioxx.tfc.api.Enums.EnumSize;
/*     */ import com.bioxx.tfc.api.Enums.EnumWeight;
/*     */ import com.google.common.collect.Sets;
/*     */ import fof.tfcsu.utils.ExpBonus;
/*     */ import fof.tfcsu.utils.Utils;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.enchantment.Enchantment;
/*     */ import net.minecraft.enchantment.EnchantmentHelper;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.stats.StatList;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.IShearable;
/*     */ 
/*     */ public class ItemShears
/*     */   extends ItemTerraTool {
/*     */   int ticks;
/*  30 */   long lasttick = 0L;
/*     */ 
/*     */   
/*     */   public ItemShears(float dam, Item.ToolMaterial par3) {
/*  34 */     super(dam, par3, Sets.newHashSet((Object[])new Block[0]));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_94581_a(IIconRegister registerer) {
/*  40 */     this.field_77791_bV = registerer.func_94245_a("minecraft:" + func_77658_a().replace("item.", ""));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public EnumSize getSize(ItemStack is) {
/*  46 */     return EnumSize.SMALL;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public EnumWeight getWeight(ItemStack is) {
/*  52 */     return EnumWeight.LIGHT;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_150894_a(ItemStack is, World world, Block block, int x, int y, int z, EntityLivingBase entity) {
/*  58 */     if (block.func_149688_o() != Material.field_151584_j && block != Blocks.field_150321_G && block != Blocks.field_150329_H && block != Blocks.field_150395_bd && block != Blocks.field_150473_bD && !(block instanceof IShearable))
/*     */     {
/*  60 */       return super.func_150894_a(is, world, block, x, y, z, entity);
/*     */     }
/*     */ 
/*     */     
/*  64 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_150897_b(Block block) {
/*  71 */     return (block == Blocks.field_150321_G || block == Blocks.field_150488_af || block == Blocks.field_150473_bD);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float func_150893_a(ItemStack is, Block block) {
/*  77 */     return (block != Blocks.field_150321_G && block.func_149688_o() != Material.field_151584_j) ? ((block == Blocks.field_150325_L) ? 5.0F : super.func_150893_a(is, block)) : 15.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_111207_a(ItemStack itemstack, EntityPlayer player, EntityLivingBase entity) {
/*  86 */     if (entity.field_70170_p.field_72995_K)
/*     */     {
/*  88 */       return false;
/*     */     }
/*  90 */     if (entity instanceof IShearable) {
/*     */       
/*  92 */       IShearable target = (IShearable)entity;
/*  93 */       if (target.isShearable(itemstack, (IBlockAccess)entity.field_70170_p, (int)entity.field_70165_t, (int)entity.field_70163_u, (int)entity.field_70161_v)) {
/*     */         
/*  95 */         if (System.currentTimeMillis() > this.lasttick + 500L)
/*  96 */         { this.ticks++;
/*  97 */           this.lasttick = System.currentTimeMillis();
/*  98 */           Utils.sendClientMessageToolTip("§2Хорошо", player); }
/*  99 */         else { this.ticks = 0; Utils.sendClientMessageToolTip("§4Плохо!", player); }
/* 100 */          if (this.ticks < 10) return true; 
/* 101 */         ExpBonus.SHEAR.give(player);
/* 102 */         ArrayList<ItemStack> drops = target.onSheared(itemstack, (IBlockAccess)entity.field_70170_p, (int)entity.field_70165_t, (int)entity.field_70163_u, (int)entity.field_70161_v, 
/* 103 */             EnchantmentHelper.func_77506_a(Enchantment.field_77346_s.field_77352_x, itemstack));
/* 104 */         Random rand = new Random();
/* 105 */         for (ItemStack stack : drops) {
/*     */           
/* 107 */           EntityItem ent = entity.func_70099_a(stack, 1.0F);
/* 108 */           ent.field_70181_x += (rand.nextFloat() * 0.05F);
/* 109 */           ent.field_70159_w += ((rand.nextFloat() - rand.nextFloat()) * 0.1F);
/* 110 */           ent.field_70179_y += ((rand.nextFloat() - rand.nextFloat()) * 0.1F);
/*     */         } 
/* 112 */         itemstack.func_77972_a(1, entity);
/*     */       } 
/* 114 */       this.ticks = 0;
/* 115 */       return true;
/* 116 */     }  this.ticks = 0;
/* 117 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean onBlockStartBreak(ItemStack itemstack, int x, int y, int z, EntityPlayer player) {
/* 123 */     if (player.field_70170_p.field_72995_K)
/*     */     {
/* 125 */       return false;
/*     */     }
/* 127 */     Block block = player.field_70170_p.func_147439_a(x, y, z);
/* 128 */     if (block instanceof IShearable) {
/*     */       
/* 130 */       IShearable target = (IShearable)block;
/* 131 */       if (target.isShearable(itemstack, (IBlockAccess)player.field_70170_p, x, y, z)) {
/*     */         
/* 133 */         ArrayList<ItemStack> drops = target.onSheared(itemstack, (IBlockAccess)player.field_70170_p, x, y, z, 
/* 134 */             EnchantmentHelper.func_77506_a(Enchantment.field_77346_s.field_77352_x, itemstack));
/* 135 */         Random rand = new Random();
/*     */         
/* 137 */         for (ItemStack stack : drops) {
/*     */           
/* 139 */           float f = 0.7F;
/* 140 */           double d = (rand.nextFloat() * f) + (1.0F - f) * 0.5D;
/* 141 */           double d1 = (rand.nextFloat() * f) + (1.0F - f) * 0.5D;
/* 142 */           double d2 = (rand.nextFloat() * f) + (1.0F - f) * 0.5D;
/* 143 */           EntityItem entityitem = new EntityItem(player.field_70170_p, x + d, y + d1, z + d2, stack);
/* 144 */           entityitem.field_145804_b = 10;
/* 145 */           player.field_70170_p.func_72838_d((Entity)entityitem);
/*     */         } 
/*     */         
/* 148 */         itemstack.func_77972_a(1, (EntityLivingBase)player);
/* 149 */         player.func_71064_a(StatList.field_75934_C[Block.func_149682_b(block)], 1);
/*     */       } 
/*     */     } 
/* 152 */     return false;
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Items\Tools\ItemShears.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */