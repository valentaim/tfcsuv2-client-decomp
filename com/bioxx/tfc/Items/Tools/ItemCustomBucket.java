/*     */ package com.bioxx.tfc.Items.Tools;
/*     */ 
/*     */ import com.bioxx.tfc.Core.TFC_Core;
/*     */ import com.bioxx.tfc.Entities.Mobs.EntityCowTFC;
/*     */ import com.bioxx.tfc.Items.ItemTerra;
/*     */ import com.bioxx.tfc.api.Entities.IAnimal;
/*     */ import com.bioxx.tfc.api.Enums.EnumItemReach;
/*     */ import com.bioxx.tfc.api.Enums.EnumSize;
/*     */ import com.bioxx.tfc.api.TFCBlocks;
/*     */ import com.bioxx.tfc.api.TFCItems;
/*     */ import cpw.mods.fml.common.eventhandler.Event;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.MinecraftForge;
/*     */ import net.minecraftforge.event.entity.player.FillBucketEvent;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ItemCustomBucket
/*     */   extends ItemTerra
/*     */ {
/*     */   private Block bucketContents;
/*     */   
/*     */   public ItemCustomBucket(Block contents) {
/*  33 */     this.bucketContents = contents;
/*  34 */     setFolder("tools/");
/*  35 */     setSize(EnumSize.MEDIUM);
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemCustomBucket(Block contents, Item container) {
/*  40 */     this(contents);
/*  41 */     func_77642_a(container);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canStack() {
/*  47 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack func_77659_a(ItemStack is, World world, EntityPlayer player) {
/*  56 */     boolean isEmpty = (this.bucketContents == Blocks.field_150350_a);
/*  57 */     MovingObjectPosition mop = func_77621_a(world, player, isEmpty);
/*     */     
/*  59 */     if (mop == null)
/*     */     {
/*  61 */       return is;
/*     */     }
/*     */ 
/*     */     
/*  65 */     if (mop.field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK) {
/*     */       
/*  67 */       int x = mop.field_72311_b;
/*  68 */       int y = mop.field_72312_c;
/*  69 */       int z = mop.field_72309_d;
/*     */       
/*  71 */       if (!world.func_72962_a(player, x, y, z)) {
/*  72 */         return is;
/*     */       }
/*  74 */       if (isEmpty) {
/*     */         
/*  76 */         if (!player.func_82247_a(x, y, z, mop.field_72310_e, is)) {
/*  77 */           return is;
/*     */         }
/*  79 */         FillBucketEvent event = new FillBucketEvent(player, is, world, mop);
/*  80 */         if (MinecraftForge.EVENT_BUS.post((Event)event) || event.isCanceled()) {
/*  81 */           return is;
/*     */         }
/*  83 */         if (event.getResult() == Event.Result.ALLOW) {
/*  84 */           return event.result;
/*     */         }
/*  86 */         if (TFC_Core.isFreshWater(world.func_147439_a(x, y, z))) {
/*     */           
/*  88 */           world.func_147468_f(x, y, z);
/*  89 */           if (player.field_71075_bZ.field_75098_d)
/*  90 */             return is; 
/*  91 */           return new ItemStack(TFCItems.woodenBucketWater);
/*     */         } 
/*  93 */         if (TFC_Core.isSaltWater(world.func_147439_a(x, y, z))) {
/*     */           
/*  95 */           world.func_147468_f(x, y, z);
/*  96 */           if (player.field_71075_bZ.field_75098_d)
/*  97 */             return is; 
/*  98 */           return new ItemStack(TFCItems.woodenBucketSaltWater);
/*     */         } 
/*     */ 
/*     */         
/* 102 */         int flowX = x;
/* 103 */         int flowY = y;
/* 104 */         int flowZ = z;
/* 105 */         switch (mop.field_72310_e) {
/*     */           
/*     */           case 0:
/* 108 */             flowY = y - 1;
/*     */             break;
/*     */           case 1:
/* 111 */             flowY = y + 1;
/*     */             break;
/*     */           case 2:
/* 114 */             flowZ = z - 1;
/*     */             break;
/*     */           case 3:
/* 117 */             flowZ = z + 1;
/*     */             break;
/*     */           case 4:
/* 120 */             flowX = x - 1;
/*     */             break;
/*     */           case 5:
/* 123 */             flowX = x + 1;
/*     */             break;
/*     */         } 
/*     */         
/* 127 */         if (TFC_Core.isFreshWater(world.func_147439_a(flowX, flowY, flowZ))) {
/*     */           
/* 129 */           world.func_147468_f(flowX, flowY, flowZ);
/* 130 */           if (player.field_71075_bZ.field_75098_d)
/* 131 */             return is; 
/* 132 */           return new ItemStack(TFCItems.woodenBucketWater);
/*     */         } 
/* 134 */         if (TFC_Core.isSaltWater(world.func_147439_a(flowX, flowY, flowZ)))
/*     */         {
/* 136 */           world.func_147468_f(flowX, flowY, flowZ);
/* 137 */           if (player.field_71075_bZ.field_75098_d)
/* 138 */             return is; 
/* 139 */           return new ItemStack(TFCItems.woodenBucketSaltWater);
/*     */         }
/*     */       
/*     */       } else {
/*     */         
/* 144 */         return new ItemStack(TFCItems.woodenBucketEmpty);
/*     */       }
/*     */     
/* 147 */     } else if (this.bucketContents == Blocks.field_150350_a && mop.field_72308_g instanceof EntityCowTFC && ((EntityCowTFC)mop.field_72308_g).getGender() == IAnimal.GenderEnum.FEMALE) {
/*     */       
/* 149 */       return new ItemStack(TFCItems.woodenBucketMilk);
/*     */     } 
/* 151 */     return is;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_77648_a(ItemStack is, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
/* 158 */     boolean isEmpty = (this.bucketContents == Blocks.field_150350_a);
/* 159 */     int[][] map = { { 0, -1, 0 }, { 0, 1, 0 }, { 0, 0, -1 }, { 0, 0, 1 }, { -1, 0, 0 }, { 1, 0, 0 } };
/*     */     
/* 161 */     if (!isEmpty && world.func_147439_a(x, y, z) != Blocks.field_150383_bp && world.func_147437_c(x + map[side][0], y + map[side][1], z + map[side][2])) {
/*     */       
/* 163 */       world.func_147465_d(x + map[side][0], y + map[side][1], z + map[side][2], TFCBlocks.freshWater, 2, 1);
/* 164 */       player.func_70062_b(0, new ItemStack(TFCItems.woodenBucketEmpty));
/* 165 */       player.field_71071_by.func_70441_a(new ItemStack(TFCItems.woodenBucketEmpty));
/* 166 */       return true;
/*     */     } 
/*     */     
/* 169 */     if (!isEmpty && world.func_147439_a(x, y, z) == Blocks.field_150383_bp) {
/*     */       
/* 171 */       int meta = world.func_72805_g(x, y, z);
/* 172 */       if (meta < 3) {
/*     */         
/* 174 */         if (!player.field_71075_bZ.field_75098_d)
/*     */         {
/* 176 */           player.func_70062_b(0, new ItemStack(TFCItems.woodenBucketEmpty));
/*     */         }
/* 178 */         world.func_72921_c(x, y, z, MathHelper.func_76125_a(3, 0, 3), 2);
/* 179 */         world.func_147453_f(x, y, z, (Block)Blocks.field_150383_bp);
/*     */         
/* 181 */         return true;
/*     */       } 
/*     */     } 
/*     */     
/* 185 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public EnumItemReach getReach(ItemStack is) {
/* 191 */     return EnumItemReach.SHORT;
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Items\Tools\ItemCustomBucket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */