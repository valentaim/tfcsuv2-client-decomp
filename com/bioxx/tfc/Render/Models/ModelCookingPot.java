/*    */ package com.bioxx.tfc.Render.Models;
/*    */ 
/*    */ import net.minecraft.client.model.ModelBase;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ModelCookingPot
/*    */   extends ModelBase
/*    */ {
/* 10 */   public ModelRendererTFC renderer = new ModelRendererTFC(this, 0, 0); public ModelCookingPot() {
/* 11 */     float x = 8.0F;
/* 12 */     float y = 8.0F;
/* 13 */     float z = 8.0F;
/* 14 */     int baseHeight = 4;
/* 15 */     int baseWidth = 7;
/* 16 */     int baseDepth = 15;
/* 17 */     Object[] basicVesselData = { { 0.5F + x, y, z + 0.5F, 8.0F, 0.01F, 8.0F, 8.0F }, { 0.5F + x, y, z + 0.5F, 8.0F, 2.0F, 8.0F, 18.0F }, { 0.5F + x, y, z + 0.5F, 8.0F, 4.0F, 8.0F, 20.0F }, { 0.5F + x, y, z + 0.5F, 8.0F, 7.0F, 8.0F, 20.0F }, { 0.5F + x, y, z + 0.5F, 8.0F, 10.0F, 8.0F, 18.0F }, { 0.5F + x, y, z + 0.5F, 8.0F, 12.0F, 8.0F, 14.0F }, { 0.5F + x, y, z + 0.5F, 8.0F, 14.0F, 8.0F, 14.0F }, { 0.5F + x, y, z + 0.5F, 8.0F, 14.0F, 8.0F, 12.0F } };
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 27 */     this.renderer.field_78804_l.add(new ModelPotteryBase(this.renderer, this.renderer.field_78803_o, this.renderer.field_78813_p, 5.5F + x, y, z + 5.5F, baseWidth, baseHeight, baseDepth, 0.0F, basicVesselData, false));
/*    */   }
/*    */ 
/*    */   
/*    */   public void renderPot() {
/* 32 */     this.renderer.func_78785_a(0.03125F);
/*    */   }
/*    */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Render\Models\ModelCookingPot.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */