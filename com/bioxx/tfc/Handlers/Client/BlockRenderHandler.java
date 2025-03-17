/*     */ package com.bioxx.tfc.Handlers.Client;
/*     */ 
/*     */ import com.bioxx.tfc.Render.Blocks.RenderAnvil;
/*     */ import com.bioxx.tfc.Render.Blocks.RenderCrop;
/*     */ import com.bioxx.tfc.Render.Blocks.RenderDetailed;
/*     */ import com.bioxx.tfc.Render.Blocks.RenderGrass;
/*     */ import com.bioxx.tfc.Render.TFC_CoreRender;
/*     */ import com.bioxx.tfc.api.TFCBlocks;
/*     */ import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.client.renderer.RenderBlocks;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BlockRenderHandler
/*     */   implements ISimpleBlockRenderingHandler
/*     */ {
/*     */   public boolean renderWorldBlock(IBlockAccess world, int i, int j, int k, Block block, int modelId, RenderBlocks renderer) {
/*  24 */     if (modelId == TFCBlocks.sulfurRenderId)
/*     */     {
/*  26 */       return TFC_CoreRender.renderSulfur(block, i, j, k, renderer);
/*     */     }
/*  28 */     if (modelId == TFCBlocks.moltenRenderId)
/*     */     {
/*  30 */       return TFC_CoreRender.renderMolten(block, i, j, k, renderer);
/*     */     }
/*  32 */     if (modelId == TFCBlocks.grassRenderId)
/*     */     {
/*  34 */       return RenderGrass.render(block, i, j, k, renderer);
/*     */     }
/*  36 */     if (modelId == TFCBlocks.clayGrassRenderId)
/*     */     {
/*  38 */       return RenderGrass.renderClay(block, i, j, k, renderer);
/*     */     }
/*  40 */     if (modelId == TFCBlocks.peatGrassRenderId)
/*     */     {
/*  42 */       return RenderGrass.renderPeat(block, i, j, k, renderer);
/*     */     }
/*  44 */     if (modelId == TFCBlocks.looseRockRenderId)
/*     */     {
/*  46 */       return TFC_CoreRender.renderLooseRock(block, i, j, k, renderer);
/*     */     }
/*  48 */     if (modelId == TFCBlocks.snowRenderId)
/*     */     {
/*  50 */       return TFC_CoreRender.renderSnow(block, i, j, k, renderer);
/*     */     }
/*  52 */     if (modelId == TFCBlocks.firepitRenderId)
/*     */     {
/*  54 */       return TFC_CoreRender.renderFirepit(block, i, j, k, renderer);
/*     */     }
/*  56 */     if (modelId == TFCBlocks.forgeRenderId)
/*     */     {
/*  58 */       return TFC_CoreRender.renderForge(block, i, j, k, renderer);
/*     */     }
/*  60 */     if (modelId == TFCBlocks.anvilRenderId)
/*     */     {
/*  62 */       return RenderAnvil.renderAnvil(block, i, j, k, renderer);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  72 */     if (modelId == TFCBlocks.sluiceRenderId)
/*     */     {
/*  74 */       return TFC_CoreRender.renderSluice(block, i, j, k, renderer);
/*     */     }
/*  76 */     if (modelId == TFCBlocks.woodFruitRenderId)
/*     */     {
/*  78 */       return TFC_CoreRender.renderWoodTrunk(block, i, j, k, renderer);
/*     */     }
/*  80 */     if (modelId == TFCBlocks.leavesFruitRenderId)
/*     */     {
/*  82 */       return TFC_CoreRender.renderFruitLeaves(block, i, j, k, renderer);
/*     */     }
/*  84 */     if (modelId == TFCBlocks.stairRenderId)
/*     */     {
/*  86 */       return TFC_CoreRender.renderBlockStairs(block, i, j, k, renderer);
/*     */     }
/*  88 */     if (modelId == TFCBlocks.slabRenderId)
/*     */     {
/*  90 */       return TFC_CoreRender.renderBlockSlab(block, i, j, k, renderer);
/*     */     }
/*  92 */     if (modelId == TFCBlocks.cropRenderId)
/*     */     {
/*  94 */       return RenderCrop.render(block, i, j, k, renderer);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 104 */     if (modelId == TFCBlocks.detailedRenderId)
/*     */     {
/* 106 */       return RenderDetailed.renderBlockDetailed(block, i, j, k, renderer);
/*     */     }
/* 108 */     if (modelId == TFCBlocks.waterPlantRenderId)
/*     */     {
/* 110 */       return TFC_CoreRender.renderSeaPlant(block, i, j, k, renderer);
/*     */     }
/* 112 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean shouldRender3DInInventory(int i) {
/* 118 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRenderId() {
/* 124 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
/* 130 */     IIcon[] icons = new IIcon[6];
/*     */     
/* 132 */     if (modelID == TFCBlocks.peatGrassRenderId) {
/*     */       
/* 134 */       for (int i = 0; i < 6; i++)
/* 135 */         icons[i] = TFCBlocks.peat.func_149733_h(i); 
/* 136 */       renderInvBlock(block, renderer, icons);
/*     */     }
/* 138 */     else if (modelID == TFCBlocks.grassRenderId) {
/*     */       
/* 140 */       for (int i = 0; i < 6; i++) {
/*     */         
/* 142 */         if (block == TFCBlocks.dirt) {
/* 143 */           icons[i] = TFCBlocks.dirt.func_149733_h(i);
/*     */         } else {
/* 145 */           icons[i] = TFCBlocks.dirt2.func_149733_h(i);
/*     */         } 
/* 147 */       }  renderInvBlock(block, renderer, icons);
/*     */     }
/* 149 */     else if (modelID == TFCBlocks.clayGrassRenderId) {
/*     */       
/* 151 */       for (int i = 0; i < 6; i++) {
/*     */         
/* 153 */         if (block == TFCBlocks.clay) {
/* 154 */           icons[i] = TFCBlocks.clay.func_149733_h(i);
/*     */         } else {
/* 156 */           icons[i] = TFCBlocks.clay2.func_149733_h(i);
/*     */         } 
/* 158 */       }  renderInvBlock(block, renderer, icons);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void renderInvBlock(Block block, RenderBlocks renderer, IIcon[] icons) {
/* 164 */     Tessellator tessellator = Tessellator.field_78398_a;
/* 165 */     GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
/* 166 */     tessellator.func_78382_b();
/* 167 */     tessellator.func_78375_b(0.0F, -1.0F, 0.0F);
/* 168 */     renderer.func_147768_a(block, 0.0D, 0.0D, 0.0D, icons[0]);
/* 169 */     tessellator.func_78381_a();
/* 170 */     tessellator.func_78382_b();
/* 171 */     tessellator.func_78375_b(0.0F, 1.0F, 0.0F);
/* 172 */     renderer.func_147806_b(block, 0.0D, 0.0D, 0.0D, icons[1]);
/* 173 */     renderer.func_147806_b(block, 0.0D, 0.0D, 0.0D, block.func_149733_h(1));
/* 174 */     tessellator.func_78381_a();
/* 175 */     tessellator.func_78382_b();
/* 176 */     tessellator.func_78375_b(0.0F, 0.0F, -1.0F);
/* 177 */     renderer.func_147798_e(block, 0.0D, 0.0D, 0.0D, icons[2]);
/* 178 */     renderer.func_147798_e(block, 0.0D, 0.0D, 0.0D, block.func_149733_h(2));
/* 179 */     tessellator.func_78381_a();
/* 180 */     tessellator.func_78382_b();
/* 181 */     tessellator.func_78375_b(0.0F, 0.0F, 1.0F);
/* 182 */     renderer.func_147764_f(block, 0.0D, 0.0D, 0.0D, icons[3]);
/* 183 */     renderer.func_147764_f(block, 0.0D, 0.0D, 0.0D, block.func_149733_h(3));
/* 184 */     tessellator.func_78381_a();
/* 185 */     tessellator.func_78382_b();
/* 186 */     tessellator.func_78375_b(-1.0F, 0.0F, 0.0F);
/* 187 */     renderer.func_147761_c(block, 0.0D, 0.0D, 0.0D, icons[4]);
/* 188 */     renderer.func_147761_c(block, 0.0D, 0.0D, 0.0D, block.func_149733_h(4));
/* 189 */     tessellator.func_78381_a();
/* 190 */     tessellator.func_78382_b();
/* 191 */     tessellator.func_78375_b(1.0F, 0.0F, 0.0F);
/* 192 */     renderer.func_147734_d(block, 0.0D, 0.0D, 0.0D, icons[5]);
/* 193 */     renderer.func_147734_d(block, 0.0D, 0.0D, 0.0D, block.func_149733_h(5));
/* 194 */     tessellator.func_78381_a();
/* 195 */     GL11.glTranslatef(0.5F, 0.5F, 0.5F);
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Handlers\Client\BlockRenderHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */