/*    */ package com.bioxx.tfc.Render;
/*    */ 
/*    */ import com.bioxx.tfc.Core.TFC_Core;
/*    */ import com.bioxx.tfc.Entities.Mobs.EntityPigTFC;
/*    */ import com.bioxx.tfc.api.Entities.IAnimal;
/*    */ import net.minecraft.client.model.ModelBase;
/*    */ import net.minecraft.client.renderer.entity.RenderPig;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RenderPigTFC
/*    */   extends RenderPig
/*    */ {
/*    */   public RenderPigTFC(ModelBase par1ModelBase, ModelBase par2ModelBase, float par3) {
/* 22 */     super(par1ModelBase, par2ModelBase, par3);
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
/* 34 */     this.field_76989_e = 0.35F + TFC_Core.getPercentGrown((IAnimal)par1Entity) * 0.35F;
/* 35 */     super.func_76986_a(par1Entity, par2, par4, par6, par8, par9);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void func_77041_b(EntityLivingBase par1EntityLivingBase, float par2) {
/* 41 */     float scale = ((EntityPigTFC)par1EntityLivingBase).getSizeMod() / 2.0F + 0.5F;
/* 42 */     GL11.glScalef(scale, scale, scale);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected ResourceLocation func_110775_a(Entity entity) {
/* 48 */     return ((EntityPigTFC)entity).isAdult() ? new ResourceLocation("terrafirmacraft", "textures/mob/boara.png") : new ResourceLocation("terrafirmacraft", "textures/mob/boarb.png");
/*    */   }
/*    */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Render\RenderPigTFC.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */