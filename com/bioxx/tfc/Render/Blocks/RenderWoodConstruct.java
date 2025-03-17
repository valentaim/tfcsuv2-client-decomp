/*     */ package com.bioxx.tfc.Render.Blocks;
/*     */ 
/*     */ import com.bioxx.tfc.TileEntities.TEWoodConstruct;
/*     */ import com.bioxx.tfc.api.TFCBlocks;
/*     */ import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.client.renderer.RenderBlocks;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RenderWoodConstruct
/*     */   implements ISimpleBlockRenderingHandler
/*     */ {
/*     */   public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {}
/*     */   
/*     */   public boolean renderWorldBlock(IBlockAccess world, int i, int j, int k, Block block, int modelId, RenderBlocks renderer) {
/*  21 */     renderOld(i, j, k, block, renderer);
/*  22 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   private void renderOld(int i, int j, int k, Block block, RenderBlocks renderer) {
/*  27 */     TEWoodConstruct te = (TEWoodConstruct)renderer.field_147845_a.func_147438_o(i, j, k);
/*     */ 
/*     */     
/*  30 */     int d = TEWoodConstruct.plankDetailLevel;
/*  31 */     int dd = TEWoodConstruct.plankDetailLevel * TEWoodConstruct.plankDetailLevel;
/*  32 */     int dd2 = dd * 2;
/*     */     
/*  34 */     float div = 1.0F / d;
/*     */     
/*  36 */     boolean breaking = false;
/*  37 */     if (renderer.field_147840_d != null) {
/*  38 */       breaking = true;
/*     */     }
/*  40 */     float minX = 0.0F;
/*  41 */     float maxX = 1.0F;
/*  42 */     float minY = 0.0F;
/*  43 */     float maxY = 1.0F;
/*  44 */     float minZ = 0.0F;
/*  45 */     float maxZ = 1.0F;
/*  46 */     boolean render = false; int index;
/*  47 */     for (index = 0; index < dd; ) {
/*     */       
/*  49 */       int in3 = index >> 3;
/*  50 */       if (te.solidCheck[in3]) {
/*     */         
/*  52 */         minX = 0.0F;
/*  53 */         maxX = 1.0F;
/*  54 */         minY = 0.0F;
/*  55 */         maxY = 1.0F;
/*  56 */         minZ = div * in3;
/*  57 */         maxZ = minZ + div;
/*  58 */         if (!breaking)
/*  59 */           renderer.field_147840_d = TFCBlocks.woodConstruct.func_149691_a(0, te.woodTypes[index]); 
/*  60 */         index++;
/*  61 */         render = true;
/*     */       }
/*  63 */       else if (te.solidCheck[in3 + 24]) {
/*     */         
/*  65 */         minX = 0.0F;
/*  66 */         maxX = 1.0F;
/*  67 */         minY = div * ((index & 0x7) + in3);
/*  68 */         maxY = minY + div;
/*  69 */         minZ = 0.0F;
/*  70 */         maxZ = 1.0F;
/*  71 */         if (!breaking)
/*  72 */           renderer.field_147840_d = TFCBlocks.woodConstruct.func_149691_a(0, te.woodTypes[index]); 
/*  73 */         index += 8;
/*  74 */         render = true;
/*     */       }
/*  76 */       else if (te.data.get(index)) {
/*     */         
/*  78 */         minX = 0.0F;
/*  79 */         maxX = 1.0F;
/*  80 */         minY = div * (index & 0x7);
/*  81 */         maxY = minY + div;
/*  82 */         minZ = div * in3;
/*  83 */         maxZ = minZ + div;
/*  84 */         if (!breaking)
/*  85 */           renderer.field_147840_d = TFCBlocks.woodConstruct.func_149691_a(0, te.woodTypes[index]); 
/*  86 */         index++;
/*  87 */         render = true;
/*     */       }
/*     */       else {
/*     */         
/*  91 */         index++;
/*  92 */         render = false;
/*     */       } 
/*     */       
/*  95 */       if (render) {
/*     */         
/*  97 */         renderer.field_147867_u = 3;
/*  98 */         renderer.field_147865_v = 3;
/*  99 */         renderer.func_147782_a((minX + 3.0E-5F), (minY + 3.0E-5F), (minZ + 3.0E-5F), (maxX - 3.0E-5F), (maxY - 3.0E-5F), (maxZ - 3.0E-5F));
/* 100 */         renderer.func_147736_d(block, i, j, k, 1.0F, 1.0F, 1.0F);
/*     */       } 
/*     */     } 
/*     */     
/* 104 */     renderer.field_147867_u = 0;
/* 105 */     renderer.field_147865_v = 0;
/* 106 */     for (index = 0; index < dd; ) {
/*     */       
/* 108 */       if (te.solidCheck[dd + index >> 3]) {
/*     */         
/* 110 */         minX = 0.0F;
/* 111 */         maxX = 1.0F;
/* 112 */         minY = 0.0F;
/* 113 */         maxY = 1.0F;
/* 114 */         minZ = div * (index >> 3);
/* 115 */         maxZ = minZ + div;
/* 116 */         if (!breaking)
/* 117 */           renderer.field_147840_d = TFCBlocks.woodConstruct.func_149691_a(0, te.woodTypes[index + dd]); 
/* 118 */         index += 8;
/* 119 */         render = true;
/*     */       }
/* 121 */       else if (te.data.get(index + dd)) {
/*     */         
/* 123 */         minX = div * (index & 0x7);
/* 124 */         maxX = minX + div;
/* 125 */         minY = 0.0F;
/* 126 */         maxY = 1.0F;
/* 127 */         minZ = div * (index >> 3);
/* 128 */         maxZ = minZ + div;
/* 129 */         if (!breaking)
/* 130 */           renderer.field_147840_d = TFCBlocks.woodConstruct.func_149691_a(0, te.woodTypes[index + dd]); 
/* 131 */         index++;
/* 132 */         render = true;
/*     */       }
/*     */       else {
/*     */         
/* 136 */         index++;
/* 137 */         render = false;
/*     */       } 
/*     */       
/* 140 */       if (render) {
/*     */         
/* 142 */         renderer.field_147869_t = 1;
/* 143 */         renderer.field_147871_s = 1;
/* 144 */         renderer.field_147875_q = 1;
/* 145 */         renderer.field_147873_r = 1;
/* 146 */         renderer.func_147782_a(minX, minY, minZ, maxX, maxY, maxZ);
/* 147 */         renderer.func_147736_d(block, i, j, k, 1.0F, 1.0F, 1.0F);
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 153 */     renderer.field_147869_t = 0;
/* 154 */     renderer.field_147871_s = 0;
/* 155 */     renderer.field_147875_q = 0;
/* 156 */     renderer.field_147873_r = 0;
/*     */     
/* 158 */     for (index = 0; index < dd; ) {
/*     */       
/* 160 */       if (te.solidCheck[dd2 + index >> 3]) {
/*     */         
/* 162 */         minX = 0.0F;
/* 163 */         maxX = 1.0F;
/* 164 */         minY = div * (index >> 3);
/* 165 */         maxY = minY + div;
/* 166 */         minZ = 0.0F;
/* 167 */         maxZ = 1.0F;
/* 168 */         if (!breaking)
/* 169 */           renderer.field_147840_d = TFCBlocks.woodConstruct.func_149691_a(0, te.woodTypes[index + dd2]); 
/* 170 */         index += 8;
/* 171 */         render = true;
/*     */       }
/* 173 */       else if (te.data.get(index + dd2)) {
/*     */         
/* 175 */         minX = div * (index & 0x7);
/* 176 */         maxX = minX + div;
/* 177 */         minY = div * (index >> 3);
/* 178 */         maxY = minY + div;
/* 179 */         minZ = 0.0F;
/* 180 */         maxZ = 1.0F;
/* 181 */         if (!breaking)
/* 182 */           renderer.field_147840_d = TFCBlocks.woodConstruct.func_149691_a(0, te.woodTypes[index + dd2]); 
/* 183 */         index++;
/* 184 */         render = true;
/*     */       }
/*     */       else {
/*     */         
/* 188 */         index++;
/* 189 */         render = false;
/*     */       } 
/*     */       
/* 192 */       if (render) {
/*     */         
/* 194 */         renderer.field_147867_u = 1;
/* 195 */         renderer.field_147865_v = 1;
/* 196 */         renderer.func_147782_a((minX + 1.0E-5F), (minY + 1.0E-5F), (minZ + 1.0E-5F), (maxX - 1.0E-5F), (maxY - 1.0E-5F), (maxZ - 1.0E-5F));
/* 197 */         renderer.func_147736_d(block, i, j, k, 1.0F, 1.0F, 1.0F);
/*     */       } 
/*     */     } 
/* 200 */     renderer.field_147867_u = 0;
/* 201 */     renderer.field_147865_v = 0;
/* 202 */     renderer.func_147771_a();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean shouldRender3DInInventory(int modelId) {
/* 208 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRenderId() {
/* 214 */     return 0;
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Render\Blocks\RenderWoodConstruct.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */