/*    */ package com.bioxx.tfc.Render;
/*    */ 
/*    */ import com.bioxx.tfc.Entities.Mobs.EntitySquidTFC;
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import net.minecraft.client.model.ModelBase;
/*    */ import net.minecraft.client.renderer.entity.RenderSquid;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.passive.EntitySquid;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ 
/*    */ 
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class RenderSquidTFC
/*    */   extends RenderSquid
/*    */ {
/*    */   public RenderSquidTFC(ModelBase par1ModelBase, float par2) {
/* 20 */     super(par1ModelBase, par2);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void func_77043_a(EntitySquid par1EntitySquid, float par2, float par3, float par4) {
/* 29 */     float f3 = par1EntitySquid.field_70862_e + (par1EntitySquid.field_70861_d - par1EntitySquid.field_70862_e) * par4;
/* 30 */     float f4 = par1EntitySquid.field_70860_g + (par1EntitySquid.field_70859_f - par1EntitySquid.field_70860_g) * par4;
/* 31 */     GL11.glTranslatef(0.0F, 0.5F, 0.0F);
/* 32 */     GL11.glRotatef(180.0F - par3, 0.0F, 1.0F, 0.0F);
/* 33 */     GL11.glRotatef(f3, 1.0F, 0.0F, 0.0F);
/* 34 */     GL11.glRotatef(f4, 0.0F, 1.0F, 0.0F);
/* 35 */     GL11.glTranslatef(0.0F, -1.2F, 0.0F);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected float func_77044_a(EntitySquid par1EntitySquid, float par2) {
/* 41 */     return par1EntitySquid.field_70865_by + (par1EntitySquid.field_70866_j - par1EntitySquid.field_70865_by) * par2;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void func_77041_b(EntityLivingBase par1EntityLiving, float par2) {
/* 47 */     preRenderScale((EntitySquidTFC)par1EntityLiving, par2);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void preRenderScale(EntitySquidTFC par1EntitySquid, float par2) {
/* 52 */     GL11.glScalef(0.5F, 0.5F, 0.5F);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected float func_77044_a(EntityLivingBase par1EntityLiving, float par2) {
/* 61 */     return func_77044_a((EntitySquid)par1EntityLiving, par2);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void func_77043_a(EntityLivingBase par1EntityLiving, float par2, float par3, float par4) {
/* 67 */     func_77043_a((EntitySquid)par1EntityLiving, par2, par3, par4);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_76986_a(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
/* 79 */     func_76986_a((EntitySquid)par1Entity, par2, par4, par6, par8, par9);
/*    */   }
/*    */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Render\RenderSquidTFC.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */