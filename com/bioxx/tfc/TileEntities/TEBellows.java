/*     */ package com.bioxx.tfc.TileEntities;
/*     */ 
/*     */ import com.bioxx.tfc.api.TileEntities.TEFireEntity;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.Random;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ 
/*     */ 
/*     */ public class TEBellows
/*     */   extends NetworkTileEntity
/*     */ {
/*  15 */   private static final int[][] BLOCK_MAP = new int[][] { { 0, 1 }, { -1, 0 }, { 0, -1 }, { 1, 0 } };
/*     */   
/*     */   public boolean shouldBlow;
/*     */   
/*     */   public int blowTimer;
/*     */   
/*     */   public int blowDirection;
/*     */   
/*     */   public void func_145845_h() {
/*  24 */     if (this.shouldBlow)
/*     */     {
/*  26 */       if (this.blowDirection == 0) {
/*     */         
/*  28 */         this.blowTimer++;
/*  29 */         if (this.field_145850_b.field_72995_K)
/*  30 */           generateSmoke(); 
/*  31 */         if (this.blowTimer == 5) {
/*     */           
/*  33 */           this.blowDirection = 1;
/*  34 */           if (!this.field_145850_b.field_72995_K) {
/*  35 */             giveAir();
/*     */           }
/*     */         } 
/*     */       } else {
/*     */         
/*  40 */         this.blowTimer--;
/*  41 */         if (this.blowTimer == -3) {
/*     */           
/*  43 */           this.blowDirection = 0;
/*  44 */           this.shouldBlow = false;
/*  45 */           this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public AxisAlignedBB getRenderBoundingBox() {
/*  55 */     return AxisAlignedBB.func_72330_a(this.field_145851_c, this.field_145848_d, this.field_145849_e, (this.field_145851_c + 1), (this.field_145848_d + 1), (this.field_145849_e + 1));
/*     */   }
/*     */ 
/*     */   
/*     */   public void generateSmoke() {
/*  60 */     int meta = this.field_145850_b.func_72805_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*  61 */     int x = BLOCK_MAP[meta][0];
/*  62 */     int z = BLOCK_MAP[meta][1];
/*  63 */     Random random = new Random();
/*     */     
/*  65 */     float f = this.field_145851_c + x + 0.5F;
/*  66 */     float f1 = this.field_145848_d + 0.1F + random.nextFloat() * 6.0F / 16.0F;
/*  67 */     float f2 = this.field_145849_e + z + 0.5F;
/*     */     
/*  69 */     float f4 = random.nextFloat() * 0.6F;
/*  70 */     float f5 = random.nextFloat() * -0.6F;
/*     */     
/*  72 */     this.field_145850_b.func_72869_a("smoke", (f + f4 - 0.3F), f1, (f2 + f5 + 0.3F), 0.0D, 0.0D, 0.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   public void giveAir() {
/*  77 */     int meta = this.field_145850_b.func_72805_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*  78 */     int x = BLOCK_MAP[meta][0];
/*  79 */     int z = BLOCK_MAP[meta][1];
/*  80 */     if (this.field_145850_b.func_72899_e(this.field_145851_c + x, this.field_145848_d, this.field_145849_e + z)) {
/*     */       
/*  82 */       TileEntity te = this.field_145850_b.func_147438_o(this.field_145851_c + x, this.field_145848_d, this.field_145849_e + z);
/*  83 */       TileEntity te2 = this.field_145850_b.func_147438_o(this.field_145851_c + x, this.field_145848_d - 1, this.field_145849_e + z);
/*  84 */       TEFireEntity tileentityfirepit = null;
/*     */       
/*  86 */       if (te instanceof TEFireEntity) {
/*  87 */         tileentityfirepit = (TEFireEntity)te;
/*  88 */       } else if (te2 instanceof TEForge) {
/*  89 */         tileentityfirepit = (TEFireEntity)te2;
/*     */       } 
/*  91 */       if (tileentityfirepit != null) {
/*  92 */         tileentityfirepit.receiveAirFromBellows();
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_145839_a(NBTTagCompound nbt) {
/*  99 */     super.func_145839_a(nbt);
/* 100 */     this.shouldBlow = nbt.func_74767_n("shouldBlow");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_145841_b(NBTTagCompound nbt) {
/* 106 */     super.func_145841_b(nbt);
/* 107 */     nbt.func_74757_a("shouldBlow", this.shouldBlow);
/*     */   }
/*     */ 
/*     */   
/*     */   public void handleInitPacket(NBTTagCompound nbt) {
/* 112 */     this.shouldBlow = nbt.func_74767_n("shouldBlow");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void handleDataPacket(NBTTagCompound nbt) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void createDataNBT(NBTTagCompound nbt) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void createInitNBT(NBTTagCompound nbt) {
/* 129 */     nbt.func_74757_a("shouldBlow", this.shouldBlow);
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\TileEntities\TEBellows.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */