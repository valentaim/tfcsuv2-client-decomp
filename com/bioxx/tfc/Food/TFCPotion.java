/*    */ package com.bioxx.tfc.Food;
/*    */ 
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import net.minecraft.potion.Potion;
/*    */ 
/*    */ 
/*    */ public class TFCPotion
/*    */   extends Potion
/*    */ {
/*    */   public static Potion bleed;
/*    */   public static Potion nausea;
/* 13 */   private String name = "";
/*    */ 
/*    */   
/* 16 */   private int statusIconIndex = -1;
/*    */ 
/*    */   
/*    */   public TFCPotion(int par1, boolean par2, int par3) {
/* 20 */     super(par1, par2, par3);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public TFCPotion setIconIndex(int par1, int par2) {
/* 26 */     this.statusIconIndex = par1 + par2 * 8;
/* 27 */     return this;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public TFCPotion setPotionName(String par1Str) {
/* 33 */     this.name = par1Str;
/* 34 */     return this;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public boolean func_76400_d() {
/* 45 */     return (this.statusIconIndex >= 0);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String func_76393_a() {
/* 51 */     return this.name;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public int func_76392_e() {
/* 58 */     return this.statusIconIndex;
/*    */   }
/*    */ 
/*    */   
/*    */   public static void setup() {
/* 63 */     bleed = (new TFCPotion(20, true, 16711680)).setPotionName("effect.bleed").setIconIndex(4, 0).func_76404_a(0.25D);
/*    */   }
/*    */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Food\TFCPotion.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */