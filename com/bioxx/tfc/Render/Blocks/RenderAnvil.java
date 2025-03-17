/*     */ package com.bioxx.tfc.Render.Blocks;
/*     */ 
/*     */ import com.bioxx.tfc.Blocks.Devices.BlockAnvil;
/*     */ import com.bioxx.tfc.TileEntities.TEAnvil;
/*     */ import com.bioxx.tfc.api.Crafting.AnvilReq;
/*     */ import com.bioxx.tfc.api.TFCBlocks;
/*     */ import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.client.renderer.RenderBlocks;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ 
/*     */ 
/*     */ public class RenderAnvil
/*     */   implements ISimpleBlockRenderingHandler
/*     */ {
/*     */   public static boolean renderAnvil(Block block, int i, int j, int k, RenderBlocks renderblocks) {
/*  18 */     IBlockAccess blockAccess = renderblocks.field_147845_a;
/*     */     
/*  20 */     int meta = blockAccess.func_72805_g(i, j, k);
/*  21 */     int direction = BlockAnvil.getDirectionFromMetadata(meta);
/*  22 */     boolean breaking = false;
/*  23 */     if (renderblocks.field_147840_d != null) {
/*  24 */       breaking = true;
/*     */     }
/*  26 */     TEAnvil te = (TEAnvil)blockAccess.func_147438_o(i, j, k);
/*  27 */     if (te.anvilTier != AnvilReq.STONE.Tier) {
/*     */       
/*  29 */       if (direction == 0)
/*     */       {
/*     */         
/*  32 */         renderblocks.func_147782_a(0.30000001192092896D, 0.4000000059604645D, 0.10000000149011612D, 0.699999988079071D, 0.6000000238418579D, 0.8999999761581421D);
/*  33 */         renderblocks.func_147784_q(block, i, j, k);
/*     */ 
/*     */         
/*  36 */         renderblocks.func_147782_a(0.3499999940395355D, 0.0D, 0.15000000596046448D, 0.6499999761581421D, 0.4000000059604645D, 0.8500000238418579D);
/*  37 */         renderblocks.func_147784_q(block, i, j, k);
/*     */ 
/*     */         
/*  40 */         renderblocks.func_147782_a(0.25D, 0.0D, 0.10000000149011612D, 0.75D, 0.20000000298023224D, 0.8999999761581421D);
/*  41 */         renderblocks.func_147784_q(block, i, j, k);
/*  42 */         renderblocks.func_147782_a(0.20000000298023224D, 0.0D, 0.0D, 0.800000011920929D, 0.10000000149011612D, 1.0D);
/*  43 */         renderblocks.func_147784_q(block, i, j, k);
/*     */ 
/*     */       
/*     */       }
/*  47 */       else if (direction == 1)
/*     */       {
/*     */         
/*  50 */         renderblocks.func_147782_a(0.10000000149011612D, 0.4000000059604645D, 0.30000001192092896D, 0.8999999761581421D, 0.6000000238418579D, 0.699999988079071D);
/*  51 */         renderblocks.func_147784_q(block, i, j, k);
/*     */ 
/*     */         
/*  54 */         renderblocks.func_147782_a(0.15000000596046448D, 0.0D, 0.3499999940395355D, 0.8500000238418579D, 0.4000000059604645D, 0.6499999761581421D);
/*  55 */         renderblocks.func_147784_q(block, i, j, k);
/*     */ 
/*     */         
/*  58 */         renderblocks.func_147782_a(0.10000000149011612D, 0.0D, 0.25D, 0.8999999761581421D, 0.20000000298023224D, 0.75D);
/*  59 */         renderblocks.func_147784_q(block, i, j, k);
/*  60 */         renderblocks.func_147782_a(0.0D, 0.0D, 0.20000000298023224D, 1.0D, 0.10000000149011612D, 0.800000011920929D);
/*  61 */         renderblocks.func_147784_q(block, i, j, k);
/*     */       
/*     */       }
/*     */     
/*     */     }
/*     */     else {
/*     */       
/*  68 */       Block b = Block.func_149729_e(te.stonePair[0]);
/*  69 */       if (b != null) {
/*     */         
/*  71 */         if (!breaking)
/*     */         {
/*  73 */           renderblocks.field_147840_d = b.func_149691_a(0, te.stonePair[1]);
/*     */         }
/*  75 */         renderblocks.func_147782_a(0.0D, 0.0D, 0.0D, 1.0D, 0.8999999761581421D, 1.0D);
/*  76 */         renderblocks.func_147784_q(block, i, j, k);
/*  77 */         renderblocks.func_147771_a();
/*     */       } 
/*     */     } 
/*  80 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {
/*  86 */     if (modelId == TFCBlocks.anvilRenderId) {
/*     */       
/*  88 */       renderer.func_147782_a(0.30000001192092896D, 0.4000000059604645D, 0.10000000149011612D, 0.699999988079071D, 0.6000000238418579D, 0.8999999761581421D);
/*  89 */       renderInvBlock(block, metadata, renderer);
/*     */ 
/*     */       
/*  92 */       renderer.func_147782_a(0.3499999940395355D, 0.0D, 0.15000000596046448D, 0.6499999761581421D, 0.4000000059604645D, 0.8500000238418579D);
/*  93 */       renderInvBlock(block, metadata, renderer);
/*     */ 
/*     */       
/*  96 */       renderer.func_147782_a(0.25D, 0.0D, 0.10000000149011612D, 0.75D, 0.20000000298023224D, 0.8999999761581421D);
/*  97 */       renderInvBlock(block, metadata, renderer);
/*  98 */       renderer.func_147782_a(0.20000000298023224D, 0.0D, 0.0D, 0.800000011920929D, 0.10000000149011612D, 1.0D);
/*  99 */       renderInvBlock(block, metadata, renderer);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
/* 106 */     if (modelId == TFCBlocks.anvilRenderId)
/* 107 */       return renderAnvil(block, x, y, z, renderer); 
/* 108 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean shouldRender3DInInventory(int modelId) {
/* 114 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRenderId() {
/* 120 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void renderInvBlock(Block block, int m, RenderBlocks renderer) {
/* 125 */     Tessellator var14 = Tessellator.field_78398_a;
/* 126 */     int meta = m;
/* 127 */     if (meta >= 8) {
/* 128 */       meta -= 8;
/*     */     }
/* 130 */     var14.func_78382_b();
/* 131 */     var14.func_78375_b(0.0F, -1.0F, 0.0F);
/* 132 */     renderer.func_147768_a(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(0, meta));
/* 133 */     var14.func_78381_a();
/* 134 */     var14.func_78382_b();
/* 135 */     var14.func_78375_b(0.0F, 1.0F, 0.0F);
/* 136 */     renderer.func_147806_b(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(1, meta));
/* 137 */     var14.func_78381_a();
/* 138 */     var14.func_78382_b();
/* 139 */     var14.func_78375_b(0.0F, 0.0F, -1.0F);
/* 140 */     renderer.func_147798_e(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(2, meta));
/* 141 */     var14.func_78381_a();
/* 142 */     var14.func_78382_b();
/* 143 */     var14.func_78375_b(0.0F, 0.0F, 1.0F);
/* 144 */     renderer.func_147764_f(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(3, meta));
/* 145 */     var14.func_78381_a();
/* 146 */     var14.func_78382_b();
/* 147 */     var14.func_78375_b(-1.0F, 0.0F, 0.0F);
/* 148 */     renderer.func_147761_c(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(4, meta));
/* 149 */     var14.func_78381_a();
/* 150 */     var14.func_78382_b();
/* 151 */     var14.func_78375_b(1.0F, 0.0F, 0.0F);
/* 152 */     renderer.func_147734_d(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(5, meta));
/* 153 */     var14.func_78381_a();
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Render\Blocks\RenderAnvil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */