/*     */ package com.bioxx.tfc.Render;
/*     */ 
/*     */ import com.bioxx.tfc.Core.TFC_Core;
/*     */ import com.bioxx.tfc.Entities.Mobs.EntityFishTFC;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.client.model.ModelBase;
/*     */ import net.minecraft.client.renderer.entity.RenderLiving;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ 
/*     */ 
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class RenderFishTFC
/*     */   extends RenderLiving
/*     */ {
/*  22 */   private static final ResourceLocation TEXTURE = new ResourceLocation("terrafirmacraft", "textures/mob/Bass.png");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RenderFishTFC(ModelBase par1ModelBase, float par2) {
/*  29 */     super(par1ModelBase, par2);
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
/*     */   protected void func_77041_b(EntityLivingBase par1EntityLiving, float par2) {
/*  41 */     preRenderScale((EntityFishTFC)par1EntityLiving, par2);
/*  42 */     Block blockBelow = par1EntityLiving.field_70170_p.func_147439_a((int)par1EntityLiving.field_70165_t, (int)par1EntityLiving.field_70163_u - 1, (int)par1EntityLiving.field_70161_v);
/*     */ 
/*     */ 
/*     */     
/*  46 */     if (!par1EntityLiving.func_70090_H() && !TFC_Core.isWater(blockBelow)) {
/*     */       
/*  48 */       GL11.glRotatef(((EntityFishTFC)par1EntityLiving).currentRenderRoll, 0.0F, 0.0F, 1.0F);
/*  49 */       GL11.glTranslatef(-0.15F, 1.5F, -0.8F);
/*     */     } else {
/*     */       
/*  52 */       ((EntityFishTFC)par1EntityLiving).currentRenderRoll = 0.0F;
/*  53 */       GL11.glTranslatef(0.0F, 1.2F, -0.5F);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_77043_a(EntityLivingBase par1EntityLivingBase, float par2, float par3, float par4) {
/*  60 */     float temp = ((EntityFishTFC)par1EntityLivingBase).currentRenderYaw;
/*  61 */     GL11.glRotatef(temp, 0.0F, 1.0F, 0.0F);
/*     */     
/*  63 */     if (par1EntityLivingBase.field_70725_aQ > 0) {
/*     */       
/*  65 */       float f3 = (par1EntityLivingBase.field_70725_aQ + par4 - 1.0F) / 20.0F * 1.6F;
/*  66 */       f3 = MathHelper.func_76129_c(f3);
/*     */       
/*  68 */       if (f3 > 1.0F)
/*     */       {
/*  70 */         f3 = 1.0F;
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void preRenderScale(EntityFishTFC par1EntityBass, float par2) {
/*  79 */     GL11.glScalef(0.4F, 0.4F, 0.4F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected float func_77044_a(EntityLivingBase par1EntityLiving, float par2) {
/*  89 */     return 1.0F;
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
/*     */   public void func_76986_a(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
/* 101 */     this.field_76989_e = 0.35F;
/* 102 */     super.func_76986_a(par1Entity, par2, par4, par6, par8, par9);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected ResourceLocation func_110775_a(Entity entity) {
/* 109 */     return TEXTURE;
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Render\RenderFishTFC.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */