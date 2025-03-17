/*    */ package com.bioxx.tfc.Core;
/*    */ 
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import net.minecraft.creativetab.CreativeTabs;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ 
/*    */ public class TFCTabs
/*    */   extends CreativeTabs {
/* 11 */   public static final CreativeTabs TFC_BUILDING = new TFCTabs("TFCBuilding");
/* 12 */   public static final CreativeTabs TFC_DECORATION = new TFCTabs("TFCDecoration");
/* 13 */   public static final CreativeTabs TFC_DEVICES = new TFCTabs("TFCDevices");
/* 14 */   public static final CreativeTabs TFC_POTTERY = new TFCTabs("TFCPottery");
/* 15 */   public static final CreativeTabs TFC_MISC = new TFCTabs("TFCMisc");
/* 16 */   public static final CreativeTabs TFC_FOODS = new TFCTabs("TFCFoods");
/* 17 */   public static final CreativeTabs TFC_TOOLS = new TFCTabs("TFCTools");
/* 18 */   public static final CreativeTabs TFC_WEAPONS = new TFCTabs("TFCWeapons");
/* 19 */   public static final CreativeTabs TFC_ARMOR = new TFCTabs("TFCArmor");
/* 20 */   public static final CreativeTabs TFC_MATERIALS = new TFCTabs("TFCMaterials");
/*    */ 
/*    */   
/*    */   private ItemStack is;
/*    */ 
/*    */   
/*    */   public TFCTabs(String par2Str) {
/* 27 */     super(par2Str);
/*    */   }
/*    */ 
/*    */   
/*    */   public TFCTabs(String par2Str, int icon) {
/* 32 */     super(par2Str);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public Item func_78016_d() {
/* 40 */     return this.is.func_77973_b();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setTabIconItem(Item i) {
/* 45 */     this.is = new ItemStack(i);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ItemStack func_151244_d() {
/* 51 */     return this.is;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setTabIconItemStack(ItemStack stack) {
/* 56 */     this.is = stack;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public String func_78024_c() {
/* 63 */     return TFC_Core.translate("itemGroup." + func_78013_b());
/*    */   }
/*    */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Core\TFCTabs.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */