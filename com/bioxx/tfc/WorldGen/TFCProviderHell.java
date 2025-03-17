/*    */ package com.bioxx.tfc.WorldGen;
/*    */ 
/*    */ import com.bioxx.tfc.Core.TFC_Climate;
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import net.minecraft.util.ChunkCoordinates;
/*    */ import net.minecraft.util.Vec3;
/*    */ import net.minecraft.world.chunk.IChunkProvider;
/*    */ import net.minecraft.world.gen.ChunkProviderHell;
/*    */ import net.minecraft.world.storage.WorldInfo;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TFCProviderHell
/*    */   extends TFCProvider
/*    */ {
/*    */   protected void func_76572_b() {
/* 18 */     this.field_76578_c = new TFCWorldChunkManagerHell(TFCBiome.HELL, 1.0F, 1.0F, this.field_76579_a);
/* 19 */     if (this.field_76579_a.field_72995_K) {
/* 20 */       TFC_Climate.worldPair.put(this.field_76579_a, new WorldCacheManager(this.field_76579_a));
/*    */     } else {
/* 22 */       TFC_Climate.worldPair.put(this.field_76579_a, new WorldCacheManager(this.field_76579_a));
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   protected void func_76556_a() {
/* 28 */     float var1 = 0.1F;
/* 29 */     for (int var2 = 0; var2 <= 15; var2++) {
/*    */       
/* 31 */       float var3 = 1.0F - var2 / 15.0F;
/* 32 */       this.field_76573_f[var2] = (1.0F - var3) / (var3 * 3.0F + 1.0F) * (1.0F - var1) + var1;
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public IChunkProvider func_76555_c() {
/* 39 */     return (IChunkProvider)new ChunkProviderHell(this.field_76579_a, this.field_76579_a.func_72905_C());
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ChunkCoordinates getSpawnPoint() {
/* 45 */     WorldInfo info = this.field_76579_a.func_72912_H();
/* 46 */     return new ChunkCoordinates(info.func_76079_c(), info.func_76075_d(), info.func_76074_e());
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean func_76569_d() {
/* 52 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean func_76566_a(int par1, int par2) {
/* 58 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public float func_76563_a(long par1, float par3) {
/* 64 */     return 0.5F;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean func_76567_e() {
/* 70 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public boolean func_76568_b(int par1, int par2) {
/* 77 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String func_80007_l() {
/* 83 */     return "Nether";
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Vec3 func_76562_b(float par1, float par2) {
/* 89 */     return Vec3.func_72443_a(0.20000000298023224D, 0.029999999329447746D, 0.029999999329447746D);
/*    */   }
/*    */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\WorldGen\TFCProviderHell.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */