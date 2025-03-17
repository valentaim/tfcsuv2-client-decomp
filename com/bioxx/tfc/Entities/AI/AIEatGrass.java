/*     */ package com.bioxx.tfc.Entities.AI;
/*     */ 
/*     */ import com.bioxx.tfc.Core.TFC_Core;
/*     */ import com.bioxx.tfc.api.Entities.IAnimal;
/*     */ import com.bioxx.tfc.api.TFCBlocks;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.ai.EntityAIBase;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AIEatGrass
/*     */   extends EntityAIBase
/*     */ {
/*     */   private EntityLiving theEntity;
/*     */   private World theWorld;
/*     */   private int eatGrassTick;
/*     */   
/*     */   public AIEatGrass(IAnimal animal) {
/*  24 */     this.theEntity = (EntityLiving)animal;
/*  25 */     this.theWorld = this.theEntity.field_70170_p;
/*  26 */     func_75248_a(7);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_75250_a() {
/*  32 */     IAnimal animal = (IAnimal)this.theEntity;
/*  33 */     if (animal.getHunger() < 144000 && this.theWorld.field_73012_v.nextInt(10) == 0) {
/*     */       
/*  35 */       int i = MathHelper.func_76128_c(this.theEntity.field_70165_t);
/*  36 */       int j = MathHelper.func_76128_c(this.theEntity.field_70163_u);
/*  37 */       int k = MathHelper.func_76128_c(this.theEntity.field_70161_v);
/*     */       
/*  39 */       if (isBush(i, j, k)) return false;
/*     */       
/*  41 */       boolean isGrass = TFC_Core.isLushGrass(this.theWorld.func_147439_a(i, j - 1, k));
/*  42 */       boolean isTallGrass = (this.theWorld.func_147439_a(i, j, k) == TFCBlocks.tallGrass && this.theWorld.func_72805_g(i, j, k) == 1);
/*  43 */       return (isGrass || isTallGrass);
/*     */     } 
/*  45 */     return false;
/*     */   }
/*     */   
/*     */   private boolean isBush(int x, int y, int z) {
/*  49 */     Block block = this.theWorld.func_147439_a(x, y, z);
/*  50 */     if (!block.func_149739_a().toLowerCase().contains("bush")) {
/*  51 */       block = this.theWorld.func_147439_a(x, y + 1, z);
/*  52 */       if (!block.func_149739_a().toLowerCase().contains("bush")) {
/*  53 */         block = this.theWorld.func_147439_a(x, y - 1, z);
/*  54 */         if (!block.func_149739_a().toLowerCase().contains("bush")) return false; 
/*     */       } 
/*     */     } 
/*  57 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_75249_e() {
/*  66 */     this.eatGrassTick = 40;
/*  67 */     this.theWorld.func_72960_a((Entity)this.theEntity, (byte)10);
/*  68 */     this.theEntity.func_70661_as().func_75499_g();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_75251_c() {
/*  77 */     this.eatGrassTick = 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_75253_b() {
/*  86 */     return (this.eatGrassTick > 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getEatGrassTick() {
/*  91 */     return this.eatGrassTick;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_75246_d() {
/* 100 */     this.eatGrassTick = Math.max(0, this.eatGrassTick - 1);
/*     */     
/* 102 */     if (this.eatGrassTick == 1) {
/*     */       
/* 104 */       int i = MathHelper.func_76128_c(this.theEntity.field_70165_t);
/* 105 */       int j = MathHelper.func_76128_c(this.theEntity.field_70163_u);
/* 106 */       int k = MathHelper.func_76128_c(this.theEntity.field_70161_v);
/*     */       
/* 108 */       if (isBush(i, j, k))
/*     */         return; 
/* 110 */       Block grass = this.theWorld.func_147439_a(i, j - 1, k);
/*     */       
/* 112 */       if (this.theWorld.func_147439_a(i, j, k) == TFCBlocks.tallGrass) {
/*     */         
/* 114 */         this.theWorld.func_147480_a(i, j, k, false);
/* 115 */         this.theEntity.func_70615_aA();
/*     */       }
/* 117 */       else if (TFC_Core.isLushGrass(grass)) {
/*     */         
/* 119 */         this.theWorld.func_72926_e(2001, i, j - 1, k, Block.func_149682_b((Block)Blocks.field_150349_c));
/* 120 */         TFC_Core.convertGrassToDirt(this.theWorld, i, j - 1, k);
/* 121 */         this.theEntity.func_70615_aA();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Entities\AI\AIEatGrass.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */