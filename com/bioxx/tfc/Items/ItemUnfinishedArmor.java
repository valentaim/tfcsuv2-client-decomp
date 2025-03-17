/*     */ package com.bioxx.tfc.Items;
/*     */ 
/*     */ import com.bioxx.tfc.Core.Metal.MetalRegistry;
/*     */ import com.bioxx.tfc.Core.TFCTabs;
/*     */ import com.bioxx.tfc.Core.TFC_Core;
/*     */ import com.bioxx.tfc.api.Enums.EnumSize;
/*     */ import com.bioxx.tfc.api.Interfaces.ISmeltable;
/*     */ import com.bioxx.tfc.api.Metal;
/*     */ import java.util.List;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.ItemStack;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ItemUnfinishedArmor
/*     */   extends ItemTerra
/*     */   implements ISmeltable
/*     */ {
/*     */   public int metalID;
/*     */   private String metal;
/*     */   private short metalAmount;
/*     */   private short metalAmount2;
/*     */   private boolean smeltable = true;
/*     */   
/*     */   public ItemUnfinishedArmor() {
/*  27 */     this.field_77787_bX = true;
/*  28 */     func_77656_e(0);
/*  29 */     func_77637_a(TFCTabs.TFC_MISC);
/*  30 */     setFolder("armor/");
/*  31 */     setSize(EnumSize.LARGE);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemUnfinishedArmor(String tex) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_94581_a(IIconRegister registerer) {
/*  42 */     this.field_77791_bV = registerer.func_94245_a("terrafirmacraft:" + this.textureFolder + func_77658_a().replace("item.", "").replace("Unfinished ", "").replace("Stage2 ", ""));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void addExtraInformation(ItemStack is, EntityPlayer player, List<String> arraylist) {
/*  48 */     if (is.func_77960_j() == 0) {
/*  49 */       arraylist.add(TFC_Core.translate("gui.stage1"));
/*     */     } else {
/*  51 */       arraylist.add(TFC_Core.translate("gui.stage2"));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public String func_77653_i(ItemStack itemstack) {
/*  57 */     return super.func_77653_i(itemstack);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int func_77639_j() {
/*  69 */     if (canStack()) {
/*  70 */       return (getSize((ItemStack)null)).stackSize;
/*     */     }
/*  72 */     return 1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canStack() {
/*  79 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Metal getMetalType(ItemStack is) {
/*  85 */     if (this.metal == null)
/*     */     {
/*  87 */       return MetalRegistry.instance.getMetalFromItem(this);
/*     */     }
/*     */ 
/*     */     
/*  91 */     return MetalRegistry.instance.getMetalFromString(this.metal);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public short getMetalReturnAmount(ItemStack is) {
/*  98 */     if (is.func_77960_j() == 1)
/*     */     {
/* 100 */       return (short)(this.metalAmount2 / 2); } 
/* 101 */     return (short)(this.metalAmount / 2);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isSmeltable(ItemStack is) {
/* 107 */     return this.smeltable;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ISmeltable.EnumTier getSmeltTier(ItemStack is) {
/* 113 */     return ISmeltable.EnumTier.TierI;
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemTerra setMetal(String m, int slot) {
/* 118 */     this.metal = m;
/* 119 */     if (slot == 0) {
/*     */       
/* 121 */       this.metalAmount = 200;
/* 122 */       this.metalAmount2 = 400;
/*     */     }
/* 124 */     else if (slot == 1) {
/*     */       
/* 126 */       this.metalAmount = 400;
/* 127 */       this.metalAmount2 = 800;
/*     */     }
/* 129 */     else if (slot == 2) {
/*     */       
/* 131 */       this.metalAmount = 400;
/* 132 */       this.metalAmount2 = 600;
/*     */     }
/* 134 */     else if (slot == 3) {
/*     */       
/* 136 */       this.metalAmount = 200;
/* 137 */       this.metalAmount2 = 200;
/*     */     } 
/* 139 */     return this;
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Items\ItemUnfinishedArmor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */