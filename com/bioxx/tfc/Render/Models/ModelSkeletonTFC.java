/*    */ package com.bioxx.tfc.Render.Models;
/*    */ 
/*    */ import com.bioxx.tfc.Entities.Mobs.EntitySkeletonTFC;
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import net.minecraft.client.model.ModelBase;
/*    */ import net.minecraft.client.model.ModelRenderer;
/*    */ import net.minecraft.client.model.ModelZombie;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class ModelSkeletonTFC
/*    */   extends ModelZombie
/*    */ {
/*    */   public ModelSkeletonTFC() {
/* 17 */     this(0.0F);
/*    */   }
/*    */ 
/*    */   
/*    */   public ModelSkeletonTFC(float par1) {
/* 22 */     super(par1, 0.0F, 64, 32);
/* 23 */     this.field_78112_f = new ModelRenderer((ModelBase)this, 40, 16);
/* 24 */     this.field_78112_f.func_78790_a(-1.0F, -2.0F, -1.0F, 2, 12, 2, par1);
/* 25 */     this.field_78112_f.func_78793_a(-5.0F, 2.0F, 0.0F);
/* 26 */     this.field_78113_g = new ModelRenderer((ModelBase)this, 40, 16);
/* 27 */     this.field_78113_g.field_78809_i = true;
/* 28 */     this.field_78113_g.func_78790_a(-1.0F, -2.0F, -1.0F, 2, 12, 2, par1);
/* 29 */     this.field_78113_g.func_78793_a(5.0F, 2.0F, 0.0F);
/* 30 */     this.field_78123_h = new ModelRenderer((ModelBase)this, 0, 16);
/* 31 */     this.field_78123_h.func_78790_a(-1.0F, 0.0F, -1.0F, 2, 12, 2, par1);
/* 32 */     this.field_78123_h.func_78793_a(-2.0F, 12.0F, 0.0F);
/* 33 */     this.field_78124_i = new ModelRenderer((ModelBase)this, 0, 16);
/* 34 */     this.field_78124_i.field_78809_i = true;
/* 35 */     this.field_78124_i.func_78790_a(-1.0F, 0.0F, -1.0F, 2, 12, 2, par1);
/* 36 */     this.field_78124_i.func_78793_a(2.0F, 12.0F, 0.0F);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_78086_a(EntityLivingBase par1EntityLivingBase, float par2, float par3, float par4) {
/* 46 */     this.field_78118_o = (((EntitySkeletonTFC)par1EntityLivingBase).getSkeletonType() == 1);
/* 47 */     super.func_78086_a(par1EntityLivingBase, par2, par3, par4);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_78087_a(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
/* 58 */     super.func_78087_a(par1, par2, par3, par4, par5, par6, par7Entity);
/*    */   }
/*    */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Render\Models\ModelSkeletonTFC.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */