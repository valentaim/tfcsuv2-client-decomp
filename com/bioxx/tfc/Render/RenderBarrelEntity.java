/*    */ package com.bioxx.tfc.Render;
/*    */ 
/*    */ import com.bioxx.tfc.Core.TFC_Core;
/*    */ import com.bioxx.tfc.Entities.EntityBarrel;
/*    */ import com.bioxx.tfc.api.TFCBlocks;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.client.renderer.RenderBlocks;
/*    */ import net.minecraft.client.renderer.entity.Render;
/*    */ import net.minecraft.client.renderer.texture.TextureMap;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RenderBarrelEntity
/*    */   extends Render
/*    */ {
/*    */   public void func_76986_a(Entity e, double x, double y, double z, float par8, float par9) {
/* 21 */     EntityBarrel entity = (EntityBarrel)e;
/* 22 */     GL11.glPushMatrix();
/* 23 */     GL11.glTranslated(x + 0.5D, y, z + 0.5D);
/* 24 */     Block block = TFCBlocks.barrel;
/* 25 */     TFC_Core.bindTexture(TextureMap.field_110575_b);
/* 26 */     RenderBlocks.getInstance().func_147800_a(block, entity.getBarrelType(), 1.0F);
/*    */     
/* 28 */     GL11.glPopMatrix();
/*    */   }
/*    */ 
/*    */   
/*    */   protected ResourceLocation func_110775_a(Entity entity) {
/* 33 */     return null;
/*    */   }
/*    */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Render\RenderBarrelEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */