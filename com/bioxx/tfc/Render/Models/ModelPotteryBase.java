/*     */ package com.bioxx.tfc.Render.Models;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import net.minecraft.client.model.ModelBox;
/*     */ import net.minecraft.client.model.ModelRenderer;
/*     */ import net.minecraft.client.model.PositionTextureVertex;
/*     */ import net.minecraft.client.model.TexturedQuad;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ModelPotteryBase
/*     */   extends ModelBox
/*     */ {
/*     */   private Object[] rings;
/*     */   protected List<TexturedQuad> polygons;
/*     */   
/*     */   public ModelPotteryBase(ModelRenderer renderer, int textureOffsetX, int textureOffsetY, float originX, float originY, float originZ, int width, int height, int depth, float scale, Object[] dataArray, boolean connectTop) {
/*  25 */     super(renderer, textureOffsetX, textureOffsetY, originX, originY, originZ, width, height, depth, scale);
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
/*     */ 
/*     */ 
/*     */     
/*  69 */     this.rings = new Object[dataArray.length];
/*     */     
/*  71 */     for (int i = 0; i < dataArray.length; i++) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  79 */       float[] data = (float[])dataArray[i];
/*  80 */       this.rings[i] = newRing(data[0], data[1], data[2], data[3], data[4], data[5], data[6]);
/*     */     } 
/*     */     
/*  83 */     this.polygons = buildSides(this.rings, renderer, textureOffsetX, textureOffsetY, originX, originY, originZ, width, height, depth, scale, connectTop);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PositionTextureVertex[] newRing(float originX, float originY, float originZ, float offsetX, float offsetY, float offsetZ, float width) {
/*  90 */     PositionTextureVertex[] vert = new PositionTextureVertex[8];
/*  91 */     float widthX = width / (MathHelper.func_76129_c(2.0F) + 2.0F);
/*  92 */     vert[0] = new PositionTextureVertex(originX + offsetX - width / 2.0F - widthX, originY + offsetY, originZ + offsetZ - width / 2.0F, 0.0F, 0.0F);
/*  93 */     vert[1] = new PositionTextureVertex(originX + offsetX + width / 2.0F - widthX, originY + offsetY, originZ + offsetZ - width / 2.0F, 0.0F, 0.0F);
/*  94 */     vert[2] = new PositionTextureVertex(originX + offsetX + width / 2.0F, originY + offsetY, originZ + offsetZ - width / 2.0F - widthX, 0.0F, 0.0F);
/*  95 */     vert[3] = new PositionTextureVertex(originX + offsetX + width / 2.0F, originY + offsetY, originZ + offsetZ + width / 2.0F - widthX, 0.0F, 0.0F);
/*  96 */     vert[4] = new PositionTextureVertex(originX + offsetX + width / 2.0F - widthX, originY + offsetY, originZ + offsetZ + width / 2.0F, 0.0F, 0.0F);
/*  97 */     vert[5] = new PositionTextureVertex(originX + offsetX - width / 2.0F - widthX, originY + offsetY, originZ + offsetZ + width / 2.0F, 0.0F, 0.0F);
/*  98 */     vert[6] = new PositionTextureVertex(originX + offsetX - width / 2.0F, originY + offsetY, originZ + offsetZ + width / 2.0F - widthX, 0.0F, 0.0F);
/*  99 */     vert[7] = new PositionTextureVertex(originX + offsetX - width / 2.0F, originY + offsetY, originZ + offsetZ - width / 2.0F - widthX, 0.0F, 0.0F);
/* 100 */     return vert;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<TexturedQuad> buildSides(Object[] vertices, ModelRenderer renderer, int textureOffsetX, int textureOffsetY, float originX, float originY, float originZ, int width, int height, int depth, float scale, boolean connectTopFace) {
/* 107 */     ArrayList<TexturedQuad> aList = new ArrayList<>();
/* 108 */     for (int i = 0; i < vertices.length - 1; i++) {
/* 109 */       for (int j = 0; j < 8; j++) {
/* 110 */         aList.add(new TexturedQuad(new PositionTextureVertex[] { ((PositionTextureVertex[])vertices[i + 1])[j], ((PositionTextureVertex[])vertices[i + 1])[(j + 1) % 8], ((PositionTextureVertex[])vertices[i])[(j + 1) % 8], ((PositionTextureVertex[])vertices[i])[j] }, textureOffsetX + depth + width, textureOffsetY + depth, textureOffsetX + depth + width + depth, textureOffsetY + depth + height, renderer.field_78801_a, renderer.field_78799_b));
/*     */       } 
/*     */     } 
/*     */     
/* 114 */     PositionTextureVertex[] baseRing = (PositionTextureVertex[])vertices[0];
/* 115 */     aList.add(new TexturedQuad(new PositionTextureVertex[] { baseRing[1], baseRing[2], baseRing[3], baseRing[0] }));
/* 116 */     aList.add(new TexturedQuad(new PositionTextureVertex[] { baseRing[0], baseRing[3], baseRing[4], baseRing[7] }));
/* 117 */     aList.add(new TexturedQuad(new PositionTextureVertex[] { baseRing[5], baseRing[6], baseRing[7], baseRing[4] }));
/*     */     
/* 119 */     if (connectTopFace) {
/* 120 */       PositionTextureVertex[] topRing = (PositionTextureVertex[])vertices[vertices.length - 1];
/* 121 */       aList.add(new TexturedQuad(new PositionTextureVertex[] { topRing[1], topRing[2], topRing[3], topRing[0] }));
/* 122 */       aList.add(new TexturedQuad(new PositionTextureVertex[] { topRing[0], topRing[3], topRing[4], topRing[7] }));
/* 123 */       aList.add(new TexturedQuad(new PositionTextureVertex[] { topRing[5], topRing[6], topRing[7], topRing[4] }));
/*     */     } 
/* 125 */     return aList;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_78245_a(Tessellator par1Tessellator, float par2) {
/* 136 */     GL11.glDisable(2884);
/* 137 */     for (TexturedQuad quad : this.polygons) {
/* 138 */       quad.func_78236_a(par1Tessellator, par2);
/*     */     }
/* 140 */     GL11.glEnable(2884);
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Render\Models\ModelPotteryBase.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */