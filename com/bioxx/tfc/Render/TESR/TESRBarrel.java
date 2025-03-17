/*    */ package com.bioxx.tfc.Render.TESR;
/*    */ 
/*    */ import com.bioxx.tfc.TileEntities.TEBarrel;
/*    */ import net.minecraft.tileentity.TileEntity;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraftforge.client.model.AdvancedModelLoader;
/*    */ import net.minecraftforge.client.model.IModelCustom;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TESRBarrel
/*    */   extends TESRBase
/*    */ {
/* 15 */   private static final IModelCustom model = AdvancedModelLoader.loadModel(new ResourceLocation("terrafirmacraft", "models/barrel.obj"));
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void renderAt(TEBarrel te, double x, double y, double z, float f5) {
/* 24 */     if (te.func_145831_w() != null) {
/*    */ 
/*    */       
/* 27 */       GL11.glPushMatrix();
/* 28 */       GL11.glTranslatef((float)x, (float)y + 0.79F, (float)z);
/* 29 */       GL11.glScalef(1.0F, 1.0F, 1.0F);
/*    */ 
/*    */       
/* 32 */       model.renderAll();
/*    */       
/* 34 */       if (te.fluid != null && TESRBase.renderBlocks.field_147840_d == null) {
/* 35 */         int color = te.fluid.getFluid().getColor(te.fluid);
/* 36 */         float f = (color >> 16 & 0xFF) / 255.0F;
/* 37 */         float f1 = (color >> 8 & 0xFF) / 255.0F;
/* 38 */         float f2 = (color & 0xFF) / 255.0F;
/* 39 */         float h = 0.75F * te.fluid.amount / 10000.0F;
/* 40 */         double size = 0.7D;
/* 41 */         GL11.glBegin(7);
/*    */         
/* 43 */         GL11.glColor3f(f, f1, f2);
/* 44 */         GL11.glVertex3d(-size / 2.0D, size / 2.0D, -size / 2.0D);
/* 45 */         GL11.glVertex3d(-size / 2.0D, size / 2.0D, size / 2.0D);
/* 46 */         GL11.glVertex3d(size / 2.0D, size / 2.0D, size / 2.0D);
/* 47 */         GL11.glVertex3d(size / 2.0D, size / 2.0D, -size / 2.0D);
/* 48 */         GL11.glEnd();
/*    */       } 
/*    */ 
/*    */       
/* 52 */       GL11.glPopMatrix();
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_147500_a(TileEntity par1TileEntity, double par2, double par4, double par6, float par8) {
/* 60 */     renderAt((TEBarrel)par1TileEntity, par2, par4, par6, par8);
/*    */   }
/*    */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Render\TESR\TESRBarrel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */