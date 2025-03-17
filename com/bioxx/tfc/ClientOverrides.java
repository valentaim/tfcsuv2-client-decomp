/*     */ package com.bioxx.tfc;
/*     */ 
/*     */ import com.bioxx.tfc.Core.WeatherManager;
/*     */ import com.bioxx.tfc.api.Util.Helper;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.nio.IntBuffer;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.multiplayer.WorldClient;
/*     */ import net.minecraft.client.particle.EntityFX;
/*     */ import net.minecraft.client.particle.EntityRainFX;
/*     */ import net.minecraft.client.particle.EntitySmokeFX;
/*     */ import net.minecraft.client.renderer.RenderGlobal;
/*     */ import net.minecraft.client.renderer.WorldRenderer;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class ClientOverrides {
/*     */   private static int rainSoundCounter;
/*     */   
/*     */   public static void loadRenderers() {
/*  29 */     RenderGlobal renderG = (Minecraft.func_71410_x()).field_71438_f;
/*  30 */     Object obj = Helper.getObject(renderG, "t", "field_72767_j", "worldRenderersToUpdate", TFCASMLoadingPlugin.runtimeDeobf);
/*  31 */     if (!(obj instanceof List))
/*     */       return; 
/*  33 */     int k = 0, j = 0;
/*  34 */     int renderChunksWide = Helper.getInteger(renderG, "w", "field_72766_m", "renderChunksWide", TFCASMLoadingPlugin.runtimeDeobf);
/*  35 */     int renderChunksTall = Helper.getInteger(renderG, "x", "field_72763_n", "renderChunksTall", TFCASMLoadingPlugin.runtimeDeobf);
/*  36 */     int renderChunksDeep = Helper.getInteger(renderG, "y", "field_72764_o", "renderChunksDeep", TFCASMLoadingPlugin.runtimeDeobf);
/*  37 */     int glRenderListBase = Helper.getInteger(renderG, "z", "field_72778_p", "glRenderListBase", TFCASMLoadingPlugin.runtimeDeobf);
/*  38 */     WorldRenderer[] worldRenderers = (WorldRenderer[])Helper.getObject(renderG, "v", "field_72765_l", "worldRenderers", TFCASMLoadingPlugin.runtimeDeobf);
/*  39 */     WorldRenderer[] sortedWorldRenderers = (WorldRenderer[])Helper.getObject(renderG, "u", "field_72768_k", "sortedWorldRenderers", TFCASMLoadingPlugin.runtimeDeobf);
/*     */     
/*  41 */     List<WorldRenderer> worldRenderersToUpdate = (List<WorldRenderer>)Helper.getObject(renderG, "t", "field_72767_j", "worldRenderersToUpdate", TFCASMLoadingPlugin.runtimeDeobf);
/*  42 */     World world = (World)Helper.getObject(renderG, "r", "field_72769_h", "theWorld", TFCASMLoadingPlugin.runtimeDeobf);
/*     */     
/*  44 */     worldRenderersToUpdate.clear();
/*  45 */     renderG.field_147598_a.clear();
/*     */     
/*  47 */     for (int l = 0; l < renderChunksWide; l++) {
/*     */       
/*  49 */       for (int i1 = renderChunksTall - 1; i1 >= 0; i1--) {
/*     */         
/*  51 */         for (int j1 = 0; j1 < renderChunksDeep; j1++) {
/*     */           
/*  53 */           worldRenderers[(j1 * renderChunksTall + i1) * renderChunksWide + l] = new WorldRenderer(world, renderG.field_147598_a, l * 16, i1 * 16, j1 * 16, glRenderListBase + j);
/*     */           
/*  55 */           if (Helper.getBoolean(renderG, "D", "field_72774_t", "occlusionEnabled", TFCASMLoadingPlugin.runtimeDeobf))
/*     */           {
/*  57 */             (worldRenderers[(j1 * renderChunksTall + i1) * renderChunksWide + l]).field_78934_v = ((IntBuffer)Helper.getObject(renderG, "C", "field_72775_s", "glOcclusionQueryBase", TFCASMLoadingPlugin.runtimeDeobf)).get(k);
/*     */           }
/*     */           
/*  60 */           (worldRenderers[(j1 * renderChunksTall + i1) * renderChunksWide + l]).field_78935_u = false;
/*  61 */           (worldRenderers[(j1 * renderChunksTall + i1) * renderChunksWide + l]).field_78936_t = true;
/*  62 */           (worldRenderers[(j1 * renderChunksTall + i1) * renderChunksWide + l]).field_78927_l = true;
/*  63 */           (worldRenderers[(j1 * renderChunksTall + i1) * renderChunksWide + l]).field_78937_s = k++;
/*  64 */           worldRenderers[(j1 * renderChunksTall + i1) * renderChunksWide + l].func_78914_f();
/*  65 */           sortedWorldRenderers[(j1 * renderChunksTall + i1) * renderChunksWide + l] = worldRenderers[(j1 * renderChunksTall + i1) * renderChunksWide + l];
/*  66 */           worldRenderersToUpdate.add(worldRenderers[(j1 * renderChunksTall + i1) * renderChunksWide + l]);
/*  67 */           j += 3;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void doRainClient(Random random, int rendererUpdateCount) {
/*  77 */     float f = (Minecraft.func_71410_x()).field_71441_e.func_72867_j(1.0F);
/*     */     
/*  79 */     if (!(Minecraft.func_71410_x()).field_71474_y.field_74347_j)
/*     */     {
/*  81 */       f /= 2.0F;
/*     */     }
/*     */     
/*  84 */     if (f != 0.0F) {
/*     */       
/*  86 */       random.setSeed(rendererUpdateCount * 312987231L);
/*  87 */       EntityLivingBase entitylivingbase = (Minecraft.func_71410_x()).field_71451_h;
/*  88 */       WorldClient worldclient = (Minecraft.func_71410_x()).field_71441_e;
/*  89 */       int i = MathHelper.func_76128_c(entitylivingbase.field_70165_t);
/*  90 */       int j = MathHelper.func_76128_c(entitylivingbase.field_70163_u);
/*  91 */       int k = MathHelper.func_76128_c(entitylivingbase.field_70161_v);
/*  92 */       byte b0 = 10;
/*  93 */       double d0 = 0.0D;
/*  94 */       double d1 = 0.0D;
/*  95 */       double d2 = 0.0D;
/*  96 */       int l = 0;
/*  97 */       int i1 = (int)(100.0F * f * f);
/*     */       
/*  99 */       if ((Minecraft.func_71410_x()).field_71474_y.field_74362_aa == 1) {
/*     */         
/* 101 */         i1 >>= 1;
/*     */       }
/* 103 */       else if ((Minecraft.func_71410_x()).field_71474_y.field_74362_aa == 2) {
/*     */         
/* 105 */         i1 = 0;
/*     */       } 
/*     */       
/* 108 */       for (int j1 = 0; j1 < i1; j1++) {
/*     */         
/* 110 */         int x = i + random.nextInt(b0) - random.nextInt(b0);
/* 111 */         int z = k + random.nextInt(b0) - random.nextInt(b0);
/* 112 */         int y = worldclient.func_72874_g(x, z);
/* 113 */         Block b = worldclient.func_147439_a(x, y - 1, z);
/* 114 */         if (!WeatherManager.canSnow((World)(Minecraft.func_71410_x()).field_71441_e, x, y, z))
/*     */         {
/* 116 */           if (y <= j + b0 && y >= j - b0) {
/*     */             
/* 118 */             float f1 = random.nextFloat();
/* 119 */             float f2 = random.nextFloat();
/*     */             
/* 121 */             if (!b.isAir((IBlockAccess)worldclient, x, y - 1, z))
/*     */             {
/* 123 */               if (b.func_149688_o() == Material.field_151587_i) {
/*     */                 
/* 125 */                 (Minecraft.func_71410_x()).field_71452_i.func_78873_a((EntityFX)new EntitySmokeFX((World)worldclient, (x + f1), (y + 0.1F) - b.func_149665_z(), (z + f2), 0.0D, 0.0D, 0.0D));
/*     */               }
/*     */               else {
/*     */                 
/* 129 */                 l++;
/*     */                 
/* 131 */                 if (random.nextInt(l) == 0) {
/*     */                   
/* 133 */                   d0 = (x + f1);
/* 134 */                   d1 = (y + 0.1F) - b.func_149665_z();
/* 135 */                   d2 = (z + f2);
/*     */                 } 
/*     */                 
/* 138 */                 (Minecraft.func_71410_x()).field_71452_i.func_78873_a((EntityFX)new EntityRainFX((World)worldclient, (x + f1), (y + 0.1F) - b.func_149665_z(), (z + f2)));
/*     */               } 
/*     */             }
/*     */           } 
/*     */         }
/*     */       } 
/*     */       
/* 145 */       if (l > 0 && random.nextInt(3) < rainSoundCounter++) {
/*     */         
/* 147 */         rainSoundCounter = 0;
/*     */         
/* 149 */         if (d1 > entitylivingbase.field_70163_u + 1.0D && worldclient.func_72874_g(MathHelper.func_76128_c(entitylivingbase.field_70165_t), MathHelper.func_76128_c(entitylivingbase.field_70161_v)) > MathHelper.func_76128_c(entitylivingbase.field_70163_u)) {
/*     */           
/* 151 */           (Minecraft.func_71410_x()).field_71441_e.func_72980_b(d0, d1, d2, "ambient.weather.rain", 0.1F, 0.5F, false);
/*     */         }
/*     */         else {
/*     */           
/* 155 */           (Minecraft.func_71410_x()).field_71441_e.func_72980_b(d0, d1, d2, "ambient.weather.rain", 0.2F, 1.0F, false);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\ClientOverrides.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */