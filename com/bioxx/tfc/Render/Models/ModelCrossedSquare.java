/*    */ package com.bioxx.tfc.Render.Models;
/*    */ 
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import net.minecraft.client.model.ModelBox;
/*    */ import net.minecraft.client.model.ModelRenderer;
/*    */ import net.minecraft.client.model.PositionTextureVertex;
/*    */ import net.minecraft.client.model.TexturedQuad;
/*    */ import net.minecraft.client.renderer.Tessellator;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ModelCrossedSquare
/*    */   extends ModelBox
/*    */ {
/*    */   private TexturedQuad[] quadList;
/*    */   
/*    */   public ModelCrossedSquare(ModelRenderer renderer, int textureOffsetX, int textureOffsetY, float originX, float originY, float originZ, int width, int height, int depth, float scale) {
/* 24 */     super(renderer, textureOffsetX, textureOffsetY, originX, originY, originZ, width, height, depth, scale);
/*    */ 
/*    */     
/* 27 */     this.quadList = new TexturedQuad[4];
/* 28 */     float maxX = originX + width;
/* 29 */     float maxY = originY + height;
/* 30 */     float maxZ = originZ + depth;
/* 31 */     originX -= scale;
/* 32 */     originY -= scale;
/* 33 */     originZ -= scale;
/* 34 */     maxX += scale;
/* 35 */     maxY += scale;
/* 36 */     maxZ += scale;
/*    */     
/* 38 */     PositionTextureVertex vert0 = new PositionTextureVertex(originX, originY, originZ, 0.0F, 0.0F);
/* 39 */     PositionTextureVertex vert1 = new PositionTextureVertex(maxX, originY, originZ, 0.0F, 8.0F);
/* 40 */     PositionTextureVertex vert2 = new PositionTextureVertex(maxX, maxY, originZ, 8.0F, 8.0F);
/* 41 */     PositionTextureVertex vert3 = new PositionTextureVertex(originX, maxY, originZ, 8.0F, 0.0F);
/* 42 */     PositionTextureVertex vert4 = new PositionTextureVertex(originX, originY, maxZ, 0.0F, 0.0F);
/* 43 */     PositionTextureVertex vert5 = new PositionTextureVertex(maxX, originY, maxZ, 0.0F, 8.0F);
/* 44 */     PositionTextureVertex vert6 = new PositionTextureVertex(maxX, maxY, maxZ, 8.0F, 8.0F);
/* 45 */     PositionTextureVertex vert7 = new PositionTextureVertex(originX, maxY, maxZ, 8.0F, 0.0F);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 56 */     this.quadList[0] = new TexturedQuad(new PositionTextureVertex[] { vert3, vert6, vert5, vert0 }, textureOffsetX + depth, textureOffsetY + depth, textureOffsetX + depth + width, textureOffsetY + depth + height, renderer.field_78801_a, renderer.field_78799_b);
/*    */     
/* 58 */     this.quadList[1] = new TexturedQuad(new PositionTextureVertex[] { vert2, vert7, vert4, vert1 }, textureOffsetX + depth + width + depth, textureOffsetY + depth, textureOffsetX + depth + width + depth + width, textureOffsetY + depth + height, renderer.field_78801_a, renderer.field_78799_b);
/*    */     
/* 60 */     this.quadList[2] = new TexturedQuad(new PositionTextureVertex[] { vert6, vert3, vert0, vert5 }, textureOffsetX + depth, textureOffsetY + depth, textureOffsetX + depth + width, textureOffsetY + depth + height, renderer.field_78801_a, renderer.field_78799_b);
/*    */     
/* 62 */     this.quadList[3] = new TexturedQuad(new PositionTextureVertex[] { vert7, vert2, vert1, vert4 }, textureOffsetX + depth + width + depth, textureOffsetY + depth, textureOffsetX + depth + width + depth + width, textureOffsetY + depth + height, renderer.field_78801_a, renderer.field_78799_b);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public void func_78245_a(Tessellator par1Tessellator, float par2) {
/* 70 */     for (int var3 = 0; var3 < this.quadList.length; var3++)
/*    */     {
/* 72 */       this.quadList[var3].func_78236_a(par1Tessellator, par2);
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Render\Models\ModelCrossedSquare.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */