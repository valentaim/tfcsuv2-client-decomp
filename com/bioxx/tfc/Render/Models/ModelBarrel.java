/*     */ package com.bioxx.tfc.Render.Models;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import net.minecraft.client.model.ModelBase;
/*     */ import net.minecraft.client.model.ModelRenderer;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class ModelBarrel
/*     */   extends ModelBase
/*     */ {
/*     */   public ModelRenderer barrel;
/*     */   public ModelRenderer barrel2;
/*     */   public ModelRenderer barrel3;
/*     */   public ModelRenderer chestBelow;
/*     */   public ModelRenderer chestKnob;
/*     */   
/*     */   public ModelBarrel(int type) {
/*  34 */     this.barrel = (new ModelRenderer(this, 0 + type * 56, 0)).func_78787_b(952, 76);
/*  35 */     this.barrel2 = (new ModelRenderer(this, 0 + type * 56, 44)).func_78787_b(952, 76);
/*  36 */     this.barrel3 = (new ModelRenderer(this, 0 + type * 56, 44)).func_78787_b(952, 76);
/*     */     
/*  38 */     this.barrel.func_78793_a(16.0F, 16.0F, 0.0F);
/*  39 */     this.barrel2.func_78793_a(0.0F, 0.0F, 0.0F);
/*  40 */     this.barrel3.func_78793_a(0.0F, 0.0F, 0.0F);
/*     */ 
/*     */     
/*  43 */     this.barrel.func_78790_a(1.0F, 0.0F, 1.0F, 14, 16, 14, 0.0F);
/*  44 */     this.barrel.field_78808_h = -3.1415927F;
/*  45 */     this.barrel2.func_78790_a(2.0F, 0.0F, 2.0F, 12, 16, 0, 0.0F);
/*  46 */     this.barrel2.func_78790_a(2.0F, 0.0F, 2.0F, 0, 16, 12, 0.0F);
/*  47 */     this.barrel2.func_78790_a(2.0F, 0.0F, 14.0F, 12, 16, 0, 0.0F);
/*  48 */     this.barrel2.func_78790_a(14.0F, 0.0F, 2.0F, 0, 16, 12, 0.0F);
/*  49 */     this.barrel3.func_78790_a(2.0F, 2.0F, 2.0F, 12, 12, 12, 0.0F);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void drawBox(AxisAlignedBB par1AxisAlignedBB) {
/*  72 */     Tessellator var2 = Tessellator.field_78398_a;
/*     */ 
/*     */     
/*  75 */     var2.func_78371_b(7);
/*  76 */     var2.func_78377_a(par1AxisAlignedBB.field_72340_a + 0.1D, par1AxisAlignedBB.field_72337_e, par1AxisAlignedBB.field_72339_c + 0.1D);
/*  77 */     var2.func_78377_a(par1AxisAlignedBB.field_72336_d - 0.1D, par1AxisAlignedBB.field_72337_e, par1AxisAlignedBB.field_72339_c + 0.1D);
/*  78 */     var2.func_78377_a(par1AxisAlignedBB.field_72336_d - 0.1D, par1AxisAlignedBB.field_72337_e, par1AxisAlignedBB.field_72334_f - 0.1D);
/*  79 */     var2.func_78377_a(par1AxisAlignedBB.field_72340_a + 0.1D, par1AxisAlignedBB.field_72337_e, par1AxisAlignedBB.field_72334_f - 0.1D);
/*  80 */     var2.func_78381_a();
/*     */ 
/*     */     
/*  83 */     var2.func_78371_b(7);
/*  84 */     var2.func_78377_a(par1AxisAlignedBB.field_72340_a, par1AxisAlignedBB.field_72338_b, par1AxisAlignedBB.field_72339_c);
/*  85 */     var2.func_78377_a(par1AxisAlignedBB.field_72336_d, par1AxisAlignedBB.field_72338_b, par1AxisAlignedBB.field_72339_c);
/*  86 */     var2.func_78377_a(par1AxisAlignedBB.field_72336_d, par1AxisAlignedBB.field_72338_b, par1AxisAlignedBB.field_72334_f);
/*  87 */     var2.func_78377_a(par1AxisAlignedBB.field_72340_a, par1AxisAlignedBB.field_72338_b, par1AxisAlignedBB.field_72334_f);
/*  88 */     var2.func_78381_a();
/*     */ 
/*     */     
/*  91 */     var2.func_78371_b(7);
/*  92 */     var2.func_78377_a(par1AxisAlignedBB.field_72340_a, par1AxisAlignedBB.field_72338_b, par1AxisAlignedBB.field_72339_c);
/*  93 */     var2.func_78377_a(par1AxisAlignedBB.field_72340_a + 0.1D, par1AxisAlignedBB.field_72337_e, par1AxisAlignedBB.field_72339_c + 0.1D);
/*  94 */     var2.func_78377_a(par1AxisAlignedBB.field_72340_a + 0.1D, par1AxisAlignedBB.field_72337_e, par1AxisAlignedBB.field_72334_f - 0.1D);
/*  95 */     var2.func_78377_a(par1AxisAlignedBB.field_72340_a, par1AxisAlignedBB.field_72338_b, par1AxisAlignedBB.field_72334_f);
/*  96 */     var2.func_78381_a();
/*     */ 
/*     */     
/*  99 */     var2.func_78371_b(7);
/* 100 */     var2.func_78377_a(par1AxisAlignedBB.field_72336_d, par1AxisAlignedBB.field_72338_b, par1AxisAlignedBB.field_72334_f);
/* 101 */     var2.func_78377_a(par1AxisAlignedBB.field_72336_d - 0.1D, par1AxisAlignedBB.field_72337_e, par1AxisAlignedBB.field_72334_f - 0.1D);
/* 102 */     var2.func_78377_a(par1AxisAlignedBB.field_72336_d - 0.1D, par1AxisAlignedBB.field_72337_e, par1AxisAlignedBB.field_72339_c + 0.1D);
/* 103 */     var2.func_78377_a(par1AxisAlignedBB.field_72336_d, par1AxisAlignedBB.field_72338_b, par1AxisAlignedBB.field_72339_c);
/* 104 */     var2.func_78381_a();
/*     */ 
/*     */     
/* 107 */     var2.func_78371_b(7);
/* 108 */     var2.func_78377_a(par1AxisAlignedBB.field_72340_a, par1AxisAlignedBB.field_72338_b, par1AxisAlignedBB.field_72339_c);
/* 109 */     var2.func_78377_a(par1AxisAlignedBB.field_72340_a + 0.1D, par1AxisAlignedBB.field_72337_e, par1AxisAlignedBB.field_72339_c + 0.1D);
/* 110 */     var2.func_78377_a(par1AxisAlignedBB.field_72336_d - 0.1D, par1AxisAlignedBB.field_72337_e, par1AxisAlignedBB.field_72339_c + 0.1D);
/* 111 */     var2.func_78377_a(par1AxisAlignedBB.field_72336_d, par1AxisAlignedBB.field_72338_b, par1AxisAlignedBB.field_72339_c);
/* 112 */     var2.func_78381_a();
/*     */ 
/*     */     
/* 115 */     var2.func_78371_b(7);
/* 116 */     var2.func_78377_a(par1AxisAlignedBB.field_72336_d, par1AxisAlignedBB.field_72338_b, par1AxisAlignedBB.field_72334_f);
/* 117 */     var2.func_78377_a(par1AxisAlignedBB.field_72336_d - 0.1D, par1AxisAlignedBB.field_72337_e, par1AxisAlignedBB.field_72334_f - 0.1D);
/* 118 */     var2.func_78377_a(par1AxisAlignedBB.field_72340_a + 0.1D, par1AxisAlignedBB.field_72337_e, par1AxisAlignedBB.field_72334_f - 0.1D);
/* 119 */     var2.func_78377_a(par1AxisAlignedBB.field_72340_a, par1AxisAlignedBB.field_72338_b, par1AxisAlignedBB.field_72334_f);
/* 120 */     var2.func_78381_a();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderBarrel() {
/* 130 */     this.barrel.func_78785_a(0.0625F);
/* 131 */     this.barrel2.func_78785_a(0.0625F);
/* 132 */     this.barrel3.func_78785_a(0.0625F);
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Render\Models\ModelBarrel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */