/*     */ package com.bioxx.tfc.Items.Tools;
/*     */ 
/*     */ import com.bioxx.tfc.Core.TFCTabs;
/*     */ import com.bioxx.tfc.Core.TFC_Core;
/*     */ import com.bioxx.tfc.Items.ItemTerra;
/*     */ import com.bioxx.tfc.TileEntities.TEPottery;
/*     */ import com.bioxx.tfc.api.Enums.EnumItemReach;
/*     */ import com.bioxx.tfc.api.Enums.EnumSize;
/*     */ import com.bioxx.tfc.api.TFCBlocks;
/*     */ import com.bioxx.tfc.api.TFCItems;
/*     */ import fof.tfcsu.utils.ExpBonus;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.item.EnumAction;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ItemFirestarter
/*     */   extends ItemTerra
/*     */ {
/*     */   private boolean canBeUsed;
/*     */   private boolean isCoal;
/*     */   private boolean isPottery;
/*     */   
/*     */   public ItemFirestarter() {
/*  38 */     func_77656_e(8);
/*  39 */     this.field_77787_bX = false;
/*  40 */     func_77637_a(TFCTabs.TFC_TOOLS);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public EnumSize getSize(ItemStack is) {
/*  46 */     return EnumSize.SMALL;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getPlacedBlockMetadata(int i) {
/*  51 */     return i;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canStack() {
/*  57 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public EnumAction func_77661_b(ItemStack is) {
/*  63 */     return EnumAction.bow;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int func_77626_a(ItemStack is) {
/*  69 */     return 20;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onUsingTick(ItemStack stack, EntityPlayer player, int count) {
/*  75 */     setFlags(player);
/*  76 */     MovingObjectPosition mop = func_77621_a(player.field_70170_p, player, true);
/*  77 */     if (mop != null && mop.field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK) {
/*     */       
/*  79 */       World world = player.field_70170_p;
/*  80 */       int x = mop.field_72311_b;
/*  81 */       int y = mop.field_72312_c;
/*  82 */       int z = mop.field_72309_d;
/*  83 */       double hitX = mop.field_72307_f.field_72450_a;
/*  84 */       double hitY = mop.field_72307_f.field_72448_b;
/*  85 */       double hitZ = mop.field_72307_f.field_72449_c;
/*  86 */       int chance = world.field_73012_v.nextInt(100);
/*     */       
/*  88 */       if (world.func_147439_a(x, y + 1, z) == TFCBlocks.firepit) {
/*  89 */         player.func_71034_by();
/*     */       }
/*  91 */       if (count > 0 && world.field_72995_K) {
/*     */         
/*  93 */         Boolean genSmoke = Boolean.valueOf((this.canBeUsed || this.isCoal || this.isPottery));
/*     */         
/*  95 */         if (genSmoke.booleanValue() && chance > 70) {
/*  96 */           world.func_72869_a("smoke", hitX, hitY, hitZ, 0.0D, 0.10000000149011612D, 0.0D);
/*     */         }
/*  98 */         if (count < 4 && chance > 70) {
/*  99 */           world.func_72869_a("flame", hitX, hitY, hitZ, 0.0D, 0.0D, 0.0D);
/*     */         }
/* 101 */         if (count < func_77626_a((ItemStack)null) - 4 && count % 3 == 1) {
/* 102 */           player.func_85030_a("terrafirmacraft:item.firestarter", 0.5F, 0.05F);
/*     */         }
/* 104 */       } else if (!world.field_72995_K && count == 1) {
/*     */         
/* 106 */         if (this.canBeUsed) {
/*     */           
/* 108 */           List list = world.func_72872_a(EntityItem.class, AxisAlignedBB.func_72330_a(x, (y + 1), z, (x + 1), (y + 2), (z + 1)));
/* 109 */           int numsticks = 0;
/* 110 */           int hasStraw = 0;
/*     */           
/* 112 */           if (list != null && !list.isEmpty()) {
/*     */             
/* 114 */             for (Iterator<EntityItem> iterator = list.iterator(); iterator.hasNext(); ) {
/*     */               
/* 116 */               EntityItem entity = iterator.next();
/* 117 */               if (entity.func_92059_d().func_77973_b() == TFCItems.straw) {
/* 118 */                 hasStraw = 40; continue;
/* 119 */               }  if (entity.func_92059_d().func_77973_b() == TFCItems.stick) {
/* 120 */                 numsticks += (entity.func_92059_d()).field_77994_a;
/*     */               }
/*     */             } 
/* 123 */             if (chance > 70 - hasStraw && numsticks >= 3) {
/*     */               
/* 125 */               for (Iterator<EntityItem> iterator1 = list.iterator(); iterator1.hasNext(); ) {
/*     */                 
/* 127 */                 EntityItem entity = iterator1.next();
/* 128 */                 if (entity.func_92059_d().func_77973_b() == TFCItems.stick || entity.func_92059_d().func_77973_b() == TFCItems.straw)
/* 129 */                   entity.func_70106_y(); 
/*     */               } 
/* 131 */               world.func_147465_d(x, y + 1, z, TFCBlocks.firepit, 1, 2);
/* 132 */               ExpBonus.SET_FIRE_TO.give(player);
/*     */             } 
/*     */           } 
/*     */           
/* 136 */           stack.func_77972_a(1, (EntityLivingBase)player);
/* 137 */           if (stack.func_77960_j() >= stack.func_77958_k()) {
/* 138 */             stack.field_77994_a = 0;
/*     */           }
/* 140 */         } else if (this.isCoal) {
/*     */           
/* 142 */           if (chance > 70) {
/* 143 */             world.func_147465_d(x, y, z, TFCBlocks.forge, 1, 2);
/* 144 */             ExpBonus.SET_FIRE_TO.give(player);
/*     */           } 
/* 146 */           stack.func_77972_a(1, (EntityLivingBase)player);
/*     */         }
/* 148 */         else if (this.isPottery) {
/*     */           
/* 150 */           if (chance > 70) {
/*     */             
/* 152 */             TEPottery te = (TEPottery)world.func_147438_o(x, y, z);
/* 153 */             te.startPitFire();
/* 154 */             ExpBonus.SET_FIRE_TO.give(player);
/*     */           } 
/* 156 */           stack.func_77972_a(1, (EntityLivingBase)player);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack func_77659_a(ItemStack is, World world, EntityPlayer player) {
/* 165 */     if (this.canBeUsed || this.isCoal || this.isPottery)
/* 166 */       player.func_71008_a(is, func_77626_a(is)); 
/* 167 */     return is;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean onItemUseFirst(ItemStack is, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
/* 173 */     setFlags(player);
/* 174 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public EnumItemReach getReach(ItemStack is) {
/* 180 */     return EnumItemReach.SHORT;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setFlags(EntityPlayer player) {
/* 185 */     MovingObjectPosition mop = func_77621_a(player.field_70170_p, player, true);
/* 186 */     if (mop != null && mop.field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK) {
/*     */       
/* 188 */       World world = player.field_70170_p;
/* 189 */       int x = mop.field_72311_b;
/* 190 */       int y = mop.field_72312_c;
/* 191 */       int z = mop.field_72309_d;
/* 192 */       int side = mop.field_72310_e;
/*     */       
/* 194 */       Block block = world.func_147439_a(x, y, z);
/* 195 */       boolean surroundSolids = TFC_Core.isSurroundedSolid(world, x, y, z);
/* 196 */       boolean surroundRock = TFC_Core.isSurroundedStone(world, x, y, z);
/* 197 */       this
/*     */ 
/*     */ 
/*     */         
/* 201 */         .canBeUsed = (side == 1 && TFC_Core.isTopFaceSolid(world, x, y, z) && block.func_149688_o() != Material.field_151575_d && block.func_149688_o() != Material.field_151580_n && world.func_147437_c(x, y + 1, z) && block != TFCBlocks.charcoal && block != Blocks.field_150402_ci && block != TFCBlocks.pottery);
/*     */ 
/*     */ 
/*     */       
/* 205 */       this.isCoal = (((block == TFCBlocks.charcoal && world.func_72805_g(x, y, z) > 6) || block == Blocks.field_150402_ci) && surroundRock && surroundSolids);
/* 206 */       this.isPottery = (block == TFCBlocks.pottery && surroundSolids);
/* 207 */       if (this.isPottery) {
/*     */         
/* 209 */         TEPottery te = (TEPottery)world.func_147438_o(x, y, z);
/* 210 */         this.isPottery = (!te.isLit() && te.wood == 8);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Items\Tools\ItemFirestarter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */