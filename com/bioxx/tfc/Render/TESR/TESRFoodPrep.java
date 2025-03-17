/*     */ package com.bioxx.tfc.Render.TESR;
/*     */ 
/*     */ import com.bioxx.tfc.Core.TFC_Core;
/*     */ import com.bioxx.tfc.TileEntities.TEFoodPrep;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.entity.RenderManager;
/*     */ import net.minecraft.client.renderer.texture.TextureMap;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import org.lwjgl.opengl.GL11;
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
/*     */ public class TESRFoodPrep
/*     */   extends TESRBase
/*     */ {
/*     */   public void renderAt(TEFoodPrep te, double d, double d1, double d2, float f) {
/*  28 */     if (te.func_145831_w() != null) {
/*     */       
/*  30 */       EntityItem customitem = new EntityItem(this.field_147501_a.field_147550_f);
/*  31 */       customitem.field_70290_d = 0.0F;
/*  32 */       float blockScale = 0.6F;
/*  33 */       float timeD = (float)(360.0D * (System.currentTimeMillis() & 0x3FFFL) / 16383.0D);
/*     */       
/*  35 */       d1 += 0.2D;
/*     */       
/*  37 */       if (RenderManager.field_78727_a.field_78733_k.field_74347_j) {
/*     */         
/*  39 */         if (te.func_70301_a(0) != null) {
/*     */           
/*  41 */           GL11.glPushMatrix();
/*  42 */           GL11.glTranslatef((float)d + 0.25F, (float)d1 + 0.0F, (float)d2 + 0.25F);
/*  43 */           GL11.glRotatef(timeD, 0.0F, 1.0F, 0.0F);
/*  44 */           GL11.glScalef(blockScale, blockScale, blockScale);
/*  45 */           customitem.func_92058_a(te.func_70301_a(0));
/*  46 */           itemRenderer.func_76986_a(customitem, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F);
/*  47 */           GL11.glPopMatrix();
/*     */         } 
/*  49 */         if (te.func_70301_a(1) != null) {
/*     */           
/*  51 */           GL11.glPushMatrix();
/*  52 */           GL11.glTranslatef((float)d + 0.75F, (float)d1 + 0.0F, (float)d2 + 0.25F);
/*  53 */           GL11.glRotatef(timeD, 0.0F, 1.0F, 0.0F);
/*  54 */           GL11.glScalef(blockScale, blockScale, blockScale);
/*  55 */           customitem.func_92058_a(te.func_70301_a(1));
/*  56 */           itemRenderer.func_76986_a(customitem, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F);
/*  57 */           GL11.glPopMatrix();
/*     */         } 
/*  59 */         if (te.func_70301_a(2) != null) {
/*     */           
/*  61 */           GL11.glPushMatrix();
/*  62 */           GL11.glTranslatef((float)d + 0.25F, (float)d1 + 0.0F, (float)d2 + 0.5F);
/*  63 */           GL11.glRotatef(timeD, 0.0F, 1.0F, 0.0F);
/*  64 */           GL11.glScalef(blockScale, blockScale, blockScale);
/*  65 */           customitem.func_92058_a(te.func_70301_a(2));
/*  66 */           itemRenderer.func_76986_a(customitem, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F);
/*  67 */           GL11.glPopMatrix();
/*     */         } 
/*  69 */         if (te.func_70301_a(3) != null) {
/*     */           
/*  71 */           GL11.glPushMatrix();
/*  72 */           GL11.glTranslatef((float)d + 0.75F, (float)d1 + 0.0F, (float)d2 + 0.5F);
/*  73 */           GL11.glRotatef(timeD, 0.0F, 1.0F, 0.0F);
/*  74 */           GL11.glScalef(blockScale, blockScale, blockScale);
/*  75 */           customitem.func_92058_a(te.func_70301_a(3));
/*  76 */           itemRenderer.func_76986_a(customitem, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F);
/*  77 */           GL11.glPopMatrix();
/*     */         } 
/*  79 */         if (te.func_70301_a(4) != null) {
/*     */           
/*  81 */           GL11.glPushMatrix();
/*  82 */           GL11.glTranslatef((float)d + 0.25F, (float)d1 + 0.0F, (float)d2 + 0.75F);
/*  83 */           GL11.glRotatef(timeD, 0.0F, 1.0F, 0.0F);
/*  84 */           GL11.glScalef(blockScale, blockScale, blockScale);
/*  85 */           customitem.func_92058_a(te.func_70301_a(4));
/*  86 */           itemRenderer.func_76986_a(customitem, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F);
/*  87 */           GL11.glPopMatrix();
/*     */         } 
/*  89 */         if (te.func_70301_a(6) != null)
/*     */         {
/*  91 */           GL11.glPushMatrix();
/*  92 */           GL11.glTranslatef((float)d + 0.75F, (float)d1 + 0.0F, (float)d2 + 0.75F);
/*  93 */           GL11.glRotatef(timeD, 0.0F, 1.0F, 0.0F);
/*  94 */           GL11.glScalef(blockScale, blockScale, blockScale);
/*  95 */           customitem.func_92058_a(te.func_70301_a(6));
/*  96 */           itemRenderer.func_76986_a(customitem, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F);
/*  97 */           GL11.glPopMatrix();
/*     */         }
/*     */       
/*     */       } else {
/*     */         
/* 102 */         GL11.glPushMatrix();
/* 103 */         GL11.glTranslated(d, d1 + 0.001D, d2);
/* 104 */         drawItem(te, 0, 0.05D, 0.35D, 0.05D, 0.35D);
/* 105 */         drawItem(te, 1, 0.65D, 0.95D, 0.05D, 0.35D);
/* 106 */         drawItem(te, 2, 0.05D, 0.35D, 0.35D, 0.65D);
/* 107 */         drawItem(te, 3, 0.65D, 0.95D, 0.35D, 0.65D);
/* 108 */         drawItem(te, 4, 0.05D, 0.35D, 0.65D, 0.95D);
/* 109 */         drawItem(te, 6, 0.65D, 0.95D, 0.65D, 0.95D);
/* 110 */         GL11.glPopMatrix();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void drawItem(TEFoodPrep te, int index, double minX, double maxX, double minZ, double maxZ) {
/* 117 */     if (te.storage[index] != null && !(te.storage[index].func_77973_b() instanceof net.minecraft.item.ItemBlock)) {
/*     */       
/* 119 */       TFC_Core.bindTexture(TextureMap.field_110576_c);
/* 120 */       float minU = te.storage[index].func_77954_c().func_94209_e();
/* 121 */       float maxU = te.storage[index].func_77954_c().func_94212_f();
/* 122 */       float minV = te.storage[index].func_77954_c().func_94206_g();
/* 123 */       float maxV = te.storage[index].func_77954_c().func_94210_h();
/* 124 */       Tessellator tessellator = Tessellator.field_78398_a;
/* 125 */       tessellator.func_78382_b();
/* 126 */       tessellator.func_78375_b(0.0F, 1.0F, 0.0F);
/* 127 */       tessellator.func_78374_a(minX, 0.0D, maxZ, minU, maxV);
/* 128 */       tessellator.func_78374_a(maxX, 0.0D, maxZ, maxU, maxV);
/* 129 */       tessellator.func_78374_a(maxX, 0.0D, minZ, maxU, minV);
/* 130 */       tessellator.func_78374_a(minX, 0.0D, minZ, minU, minV);
/* 131 */       tessellator.func_78381_a();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_147500_a(TileEntity par1TileEntity, double par2, double par4, double par6, float par8) {
/* 138 */     renderAt((TEFoodPrep)par1TileEntity, par2, par4, par6, par8);
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Render\TESR\TESRFoodPrep.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */