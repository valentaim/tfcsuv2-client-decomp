/*     */ package com.bioxx.tfc.Render.TESR;
/*     */ 
/*     */ import com.bioxx.tfc.TileEntities.TEWorldItem;
/*     */ import java.util.Random;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.renderer.RenderBlocks;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.entity.RenderManager;
/*     */ import net.minecraft.client.renderer.texture.TextureMap;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ 
/*     */ public class TESRWorldItem
/*     */   extends TESRBase
/*     */ {
/*  19 */   public static Random rand = new Random();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderAt(TEWorldItem te, double d, double d1, double d2, float f) {
/*  29 */     if (te.func_145831_w() != null) {
/*     */ 
/*     */       
/*  32 */       if (te.renderItem == null) {
/*     */         
/*  34 */         rand.setSeed(((te.field_145851_c + te.field_145849_e) * te.field_145851_c));
/*  35 */         te.renderItem = new EntityItem(this.field_147501_a.field_147550_f);
/*  36 */         te.renderItem.func_70082_c(rand.nextFloat() * 360.0F, 90.0F);
/*  37 */         te.renderItem.field_70290_d = 0.0F;
/*  38 */         te.renderItem.func_92058_a(te.storage[0]);
/*     */       } 
/*     */       
/*  41 */       if (te.storage[0] != null) {
/*     */         
/*  43 */         float minU = te.storage[0].func_77954_c().func_94209_e();
/*  44 */         float maxU = te.storage[0].func_77954_c().func_94212_f();
/*  45 */         float minV = te.storage[0].func_77954_c().func_94206_g();
/*  46 */         float maxV = te.storage[0].func_77954_c().func_94210_h();
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*  51 */         if (te.storage[0].func_94608_d() == 0) {
/*     */           
/*  53 */           func_147499_a(TextureMap.field_110575_b);
/*     */         }
/*     */         else {
/*     */           
/*  57 */           func_147499_a(TextureMap.field_110576_c);
/*     */         } 
/*     */         
/*  60 */         boolean fancy = RenderManager.field_78727_a.field_78733_k.field_74347_j;
/*  61 */         RenderBlocks renderer = RenderBlocks.getInstance();
/*  62 */         if (renderer.field_147845_a == null) {
/*  63 */           renderer.field_147845_a = (IBlockAccess)(Minecraft.func_71410_x()).field_71441_e;
/*     */         }
/*     */         
/*  66 */         GL11.glPushMatrix();
/*     */         
/*  68 */         if (RenderManager.field_78727_a.field_78733_k.field_74347_j) {
/*     */           
/*  70 */           GL11.glPushMatrix();
/*  71 */           GL11.glTranslatef((float)d + 0.5F, (float)d1 + 0.021F, (float)d2 + 0.5F);
/*  72 */           GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
/*     */           
/*  74 */           itemRenderer.func_76986_a(te.renderItem, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F);
/*  75 */           GL11.glPopMatrix();
/*     */         }
/*     */         else {
/*     */           
/*  79 */           GL11.glPushMatrix();
/*  80 */           GL11.glTranslated(d, d1 + 0.001D, d2);
/*  81 */           Tessellator tessellator = Tessellator.field_78398_a;
/*  82 */           tessellator.func_78382_b();
/*  83 */           tessellator.func_78375_b(0.0F, 1.0F, 0.0F);
/*  84 */           tessellator.func_78374_a(0.2D, 0.0D, 0.8D, minU, maxV);
/*  85 */           tessellator.func_78374_a(0.8D, 0.0D, 0.8D, maxU, maxV);
/*  86 */           tessellator.func_78374_a(0.8D, 0.0D, 0.2D, maxU, minV);
/*  87 */           tessellator.func_78374_a(0.2D, 0.0D, 0.2D, minU, minV);
/*  88 */           tessellator.func_78381_a();
/*  89 */           GL11.glPopMatrix();
/*     */         } 
/*  91 */         GL11.glPopMatrix();
/*  92 */         RenderManager.field_78727_a.field_78733_k.field_74347_j = fancy;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_147500_a(TileEntity par1TileEntity, double par2, double par4, double par6, float par8) {
/* 100 */     renderAt((TEWorldItem)par1TileEntity, par2, par4, par6, par8);
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Render\TESR\TESRWorldItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */